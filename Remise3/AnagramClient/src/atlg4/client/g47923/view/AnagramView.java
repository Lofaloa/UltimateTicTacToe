package atlg4.client.g47923.view;

import atlg4.client.g47923.AnagramClient;
import java.io.IOException;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This class is used to manage the view of the Anagram client application.
 *
 * @author Logan Farci (47923)
 */
public class AnagramView implements View {

    private static final String TITLE = "Anagram";
    private static final int MIN_WIDTH = 500;
    private static final int MIN_HEIGHT = 300;

    private final AnagramMainWindow main;
    private final Stage stage;
    private final Scene scene;

    /**
     * Constructs an instance of the AnagramView with specified view and client.
     *
     * @param stage is the specified stage.
     * @param client is the specified client.
     * @throws IOException when the loading of a FXML files fails.
     */
    public AnagramView(Stage stage, AnagramClient client) throws IOException {
        this.main = new AnagramMainWindow(client);
        this.stage = stage;
        this.scene = new Scene(main);
        this.inititialize();
    }

    private void inititialize() {
        stage.setTitle(TITLE);
        stage.setMinWidth(MIN_WIDTH);
        stage.setMinHeight(MIN_HEIGHT);
        stage.setScene(scene);
    }

    @Override
    public void showMainWindow() {
        stage.show();
    }

    @Override
    public void showInformation(String header, String message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void showError(String header, String message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
