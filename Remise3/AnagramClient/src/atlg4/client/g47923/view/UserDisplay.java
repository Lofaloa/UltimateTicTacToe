package atlg4.client.g47923.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * This class is used to represent a user. It shows its name and the number of
 * words they actually found.
 *
 * @author Logan Farci (47923)
 */
public class UserDisplay extends VBox implements Initializable {

    private static final String FXML_PATH = "/fxml/UserDisplay.fxml";

    @FXML
    private Label nameLabel;

    @FXML
    private Label nbFoundWordsLabel;
    
    private final String name;
    private final int nbFoundWords;

    UserDisplay(String name, int nbFoundWords) throws IOException {
        this.name = name;
        this.nbFoundWords = nbFoundWords;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameLabel.setText(name);
        nbFoundWordsLabel.setText(Integer.toString(nbFoundWords));
    }

}
