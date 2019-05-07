package atlg4.client.g47923.view;

import atlg4.client.g47923.AnagramClient;
import atlg4.g47923.anagram.message.Message;
import atlg4.g47923.anagram.message.Type;
import atlg4.g47923.anagram.players.User;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * AnagramWindow is the window used to play with the Anagram client. It shows a
 * shuffled word to the player and waits a anagram proposal.
 *
 * @author Logan Farci (47923)
 */
public class AnagramMainWindow extends BorderPane implements Observer {

    private static final String FXML_PATH = "/fxml/AnagramWindow.fxml";

    @FXML
    private TextField proposal;

    @FXML
    private Label anagram;

    @FXML
    private VBox players;

    private final View view;
    private final AnagramClient client;

    /**
     * Constructs an instance of AnagramWindow.
     *
     * @param view is the view managing the user interface of the client.
     * @param client is the client to manage the view for.
     * @throws IOException is thrown when the FXML file cannot be loaded.
     */
    public AnagramMainWindow(View view, AnagramClient client) throws IOException {
        this.view = view;
        this.client = client;
        this.client.addObserver(this);
        load();
    }

    private void load() throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(FXML_PATH));
            loader.setRoot(this);
            loader.setController(this);
            loader.load();
        } catch (IOException exception) {
            throw new IOException(FXML_PATH + " cannot be loaded!", exception);
        }
    }

    @FXML
    private void check(ActionEvent event) {
        try {
            String proposal = this.proposal.getText();
            this.proposal.clear();
            client.sendProposal(proposal);
        } catch (IllegalArgumentException | IOException e) {
            view.showError("Erreur lors de la proposition!", e.getMessage());
        }
    }

    @FXML
    private void pass(ActionEvent event) {
        try {
            client.sendPassCurrentWord();
        } catch (IOException e) {
            view.showError("Erreur au moment de passer au mot suivant!",
                    e.getMessage());
        }
    }

    @FXML
    private void disconnect(ActionEvent event) {
        try {
            client.quit();
        } catch (IOException e) {
            view.showError("Erreur lors de la déconnexion!", e.getMessage());
        }
    }

    @FXML
    private void quit(ActionEvent event) {
        // Vérifier que la connection soit fermer avant de quitter?
        System.exit(0);
    }

    private void updatePlayers() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    players.getChildren().clear();
                    for (User player : client.getPlayers()) {
                        if (!player.equals(client.getMySelf())) {
                            UserDisplay display = new UserDisplay(
                                    player.getName(),
                                    player.getNbSolvedWords()
                            );
                            players.getChildren().add(display);
                        }
                    }
                } catch (IOException e) {
                    view.showError(
                            "Problème lors de la mise à jour des joueurs",
                            e.getMessage()
                    );
                }
            }
        });

    }

    @Override
    public void update(Observable o, Object arg) {
        updatePlayers();
        if (arg != null) {
            Message message = (Message) arg;
            if (message.getType() == Type.WORD) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        String word = (String) message.getContent();
                        anagram.setText(word);
                    }
                });
            }
            if (message.getType() == Type.ANSWER) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        String answer = (String) message.getContent();
                        view.showInformation(
                                "Vous avez passé le mot",
                                "C'est dommage! Ce n'était pourtant pas bien"
                                + " compliqué, la réponse était \""
                                + answer + "\"."
                        );
                    }
                });
            }
        }
    }

}
