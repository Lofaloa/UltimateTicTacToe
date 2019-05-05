package atlg4.client.g47923.view;

import atlg4.client.g47923.AnagramClient;
import atlg4.g47923.anagram.message.Message;
import atlg4.g47923.anagram.message.Type;
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

/**
 * AnagramWindow is the window used to play with the Anagram client. It shows a
 * shuffled word to the player and waits a anagram proposal.
 *
 * @author Logan Farci (47923)
 */
public class AnagramWindow extends BorderPane implements Observer {

    private static final String FXML_PATH = "/fxml/AnagramWindow.fxml";

    @FXML
    private TextField proposal;

    @FXML
    private Label anagram;

    private final AnagramClient client;

    /**
     * Constructs an instance of AnagramWindow.
     *
     * @param client is the client to manage the view for.
     * @throws IOException is thrown when the FXML file cannot be loaded.
     */
    public AnagramWindow(AnagramClient client) throws IOException {
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
        System.out.println("CHECK");
        try {
            String proposal = this.proposal.getText();
            client.sendProposal(proposal);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    private void pass(ActionEvent event) {
        System.out.println("PASS");
        // Il faut rajouter un message qui signal au serveur de passer au mot
        // suivant
    }

    @FXML
    private void disconnect(ActionEvent event) {
        System.out.println("DISCONNECT");
        try {
            client.quit();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    private void quit(ActionEvent event) {
        System.out.println("QUIT");
        // Vérifier que la connection soit fermer avant de quitter?
        System.exit(0);
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("UPDATE");
        if (arg != null) {
            Message message = (Message) arg;
            if (message.getType() == Type.WORD) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("WORD UPDATED - BEGIN");
                        String word = (String) message.getContent();
                        anagram.setText(word);
                        System.out.println("WORD: " + word);
                        System.out.println("WORD UPDATED - END");
                    }
                });

            }
        }
    }

}
