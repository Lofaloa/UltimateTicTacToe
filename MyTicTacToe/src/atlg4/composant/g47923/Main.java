package atlg4.composant.g47923;

import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        MyTicTacToe root = new MyTicTacToe();

        root.initialize(new Image("/images/cross.png"));

        Scene scene = new Scene(root);
        primaryStage.setTitle("MyTicTacToe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
