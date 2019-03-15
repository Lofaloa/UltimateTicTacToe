package atlg4.remise1;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    static final String TITLE = "MyTicTacToe Demo";
    static final String FXML_PATH = "/fxml/MyTicTacToeDemo.fxml";
    static final int MIN_WIDTH = 400;
    static final int MIN_HEIGHT = 500;

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(FXML_PATH));
            loader.setController(new ViewController());
            Scene scene = new Scene(loader.load(), MIN_WIDTH, MIN_HEIGHT);
            primaryStage.setTitle(TITLE);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
