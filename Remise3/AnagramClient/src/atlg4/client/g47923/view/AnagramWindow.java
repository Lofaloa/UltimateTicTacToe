package atlg4.client.g47923.view;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

/**
 * AnagramWindow is the window used to play Anagram. It shows a shuffled word to
 * the player and waits a anagram proposal.
 *
 * @author Logan Farci
 */
public class AnagramWindow extends BorderPane {

    private static final String FXML_PATH = "/fxml/AnagramWindow.fxml";

    /**
     * Constructs an instance of AnagramWindow.
     *
     * @throws IOException is thrown when the FXML file cannot be loaded.
     */
    public AnagramWindow() throws IOException {
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
    }

    @FXML
    private void pass(ActionEvent event) {
        System.out.println("PASS");
    }

    @FXML
    private void disconnect(ActionEvent event) {
        System.out.println("DISCONNECT");
    }

    @FXML
    private void quit(ActionEvent event) {
        System.out.println("QUIT");
    }

}
