package atlg4.client.g47923;

import atlg4.client.g47923.view.AnagramView;
import atlg4.client.g47923.view.View;
import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * This class is the entry point of the Anagram client.
 *
 * @author Logan Farci (47923)
 */
public class AnagramClientMain extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        AnagramClient client = new AnagramClient();
        View view = new AnagramView(primaryStage, client);
        view.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
