/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atlg4.client.g47923;

import atlg4.client.g47923.view.AnagramWindow;
import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author logan
 */
public class AnagramClientMain extends Application {

    private static final String TITLE = "Anagram";

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = new AnagramWindow();
            Scene scene = new Scene(root);
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
