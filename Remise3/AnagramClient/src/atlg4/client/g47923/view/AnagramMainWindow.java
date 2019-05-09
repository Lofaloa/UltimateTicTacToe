package atlg4.client.g47923.view;

import atlg4.client.g47923.AnagramClient;
import atlg4.g47923.anagram.message.Message;
import atlg4.g47923.anagram.message.StatisticsMessage;
import atlg4.g47923.anagram.players.GameStatistics;
import atlg4.g47923.anagram.players.User;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * AnagramWindow is the window used to play with the Anagram client. It shows a
 * shuffled word to the player and waits a anagram proposal.
 *
 * @author Logan Farci (47923)
 */
public class AnagramMainWindow extends BorderPane implements Observer {

    private static final String FXML_PATH = "/fxml/AnagramWindow.fxml";

    private static double getProgress(GameStatistics stats) {
        int readWords = stats.getNbWords() - stats.getNbRemaingingWords();
        return (double) readWords / stats.getNbWords();
    }

    @FXML
    private TextField proposal;

    @FXML
    private Label anagram;

    @FXML
    private Text nbProposal;

    @FXML
    private VBox players;

    @FXML
    private ProgressBar progress;
    
    @FXML
    private Button check;

    @FXML
    private Button pass;

    private final View view;
    private AnagramClient client;

    /**
     * Constructs an instance of AnagramWindow.
     *
     * @param view is the view managing the user interface of the client.
     * @throws IOException is thrown when the FXML file cannot be loaded.
     */
    public AnagramMainWindow(View view) throws IOException {
        this.view = view;
        this.client = null;
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

    void setClient(AnagramClient client) {
        this.client = client;
        this.client.addObserver(this);
    }

    @FXML
    private void check(ActionEvent event) {
        if (client == null) {
            throw new IllegalStateException("Pas de client.");
        }
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
        if (client == null) {
            throw new IllegalStateException("Pas de client.");
        }
        try {
            client.sendPassCurrentWord();
        } catch (IOException e) {
            view.showError("Erreur au moment de passer au mot suivant!",
                    e.getMessage());
        }
    }

    @FXML
    private void disconnect(ActionEvent event) {
        if (client == null) {
            throw new IllegalStateException("Pas de client.");
        }
        try {
            client.quit();
        } catch (IOException e) {
            view.showError("Erreur lors de la déconnexion!", e.getMessage());
        }
    }

    @FXML
    private void quit(ActionEvent event) {
        System.exit(0);
    }

    @Override
    public void update(Observable o, Object arg) {
        updatePlayers();
        if (arg != null) {
            Message message = (Message) arg;
            switch (message.getType()) {
                case WORD:
                    updateWord(message);
                    break;
                case ANSWER:
                    updateAnswer(message);
                    break;
                case STATISTICS:
                    updateStatistics(message);
                    break;
                case FAILURE:
                    System.out.println("UPDATING FAILURE");
                    showFailure(message);
                    break;
                case END_OF_GAME:
                    
                default:
                    view.showError(
                            "Message inattendu",
                            "Les messages de type " + message.getType() + " ne "
                            + " peuvent pas être mis à jour dans cette fenêtre."
                    );
            }
        }
    }

    private void updatePlayers() {
        if (client == null) {
            throw new IllegalStateException("Pas de client.");
        }
        Platform.runLater(() -> {
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
                        "Erreur lors de la mise à jour des joueurs",
                        e.getMessage()
                );
            }
        });

    }

    private void updateWord(Message message) {
        Platform.runLater(() -> {
            String word = (String) message.getContent();
            anagram.setText(word);
        });
    }

    private void updateAnswer(Message message) {
        Platform.runLater(() -> {
            String answer = (String) message.getContent();
            view.showInformation(
                    "Vous avez passé le mot",
                    "C'est dommage! Ce n'était pourtant pas bien"
                    + " compliqué, la réponse était \""
                    + answer + "\"."
            );
        });
    }

    private String getNbProposalText(GameStatistics stats) {
        StringBuilder text = new StringBuilder();
        if (stats.getNbProposal() == 0) {
            text.append("Vous n'avez pas encore fait de propositions,");
            text.append(" écrivez un mot dans le champ dédié et clickez sur");
            text.append(" \"Proposer\" pour en faire une.");
        } else if (1 <= stats.getNbProposal() && stats.getNbProposal() < 10) {
            text.append("Vous avez proposé ");
            text.append(stats.getNbProposal());
            text.append(" mots pour l'anagramme courant.");
        } else if (10 <= stats.getNbProposal()) {
            text.append("Vous avez déjà fait ");
            text.append(stats.getNbProposal());
            text.append(" propositions! Êtes-vous sûr de pouvoir trouver la");
            text.append(" réponse? N'oubliez pas qu'il est possible de passer");
            text.append(" l'anagramme courant à tout moment!");
        }
        return text.toString();
    }

    private void updateStatistics(Message message) {
        StatisticsMessage statisticsMessage = (StatisticsMessage) message;
        GameStatistics stats = (GameStatistics) statisticsMessage.getContent();
        Platform.runLater(() -> {
            String text = getNbProposalText(stats);
            nbProposal.setText(text);
            progress.setProgress(getProgress(stats));
        });
    }

    private void showFailure(Message message) {
        String wrongProposal = (String) message.getContent();
        Platform.runLater(() -> {
            view.showInformation(
                    "Mauvaise proposition",
                    "Vous avez proposé \"" + wrongProposal + "\" mais ce n'est pas"
                    + " le mot caché par l'anagramme courant! Réessayez!"
            );
        });
    }

    private void showEnd(Message message) {
        boolean isOver = (boolean) message.getContent();
        Platform.runLater(() -> {
            if (isOver) {
                view.showInformation(
                        "Le jeu est fini",
                        "Vous avez lu tous les mots, vous pouvez quitter "
                        + "l'application ou vous déconnecter."
                );
            }
        });
    }

}
