package atlg4.composant.g47923;

import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        MyTicTacToe root = new MyTicTacToe();
        
        root.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            Node node = (Node) e.getTarget();
            System.out.println(node + " is at " + GridPane.getColumnIndex(node)
            + "; " + GridPane.getRowIndex(node));
        });
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/css/style.css");
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
