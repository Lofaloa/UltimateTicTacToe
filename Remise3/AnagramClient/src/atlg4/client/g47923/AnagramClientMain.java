/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atlg4.client.g47923;

import atlg4.client.g47923.view.AnagramView;
import atlg4.client.g47923.view.View;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * This class is the entry point of the Anagram client.
 * 
 * @author Logan Farci (47923)
 */
public class AnagramClientMain extends Application {

    private static final String DEFAULT_HOST = "localhost";
    private static final int DEFAULT_PORT = 12345;

    @Override
    public void start(Stage primaryStage) {
        AnagramClient client = null;
        View view = null;
        try {
            client = new AnagramClient(DEFAULT_HOST, DEFAULT_PORT, "Logan", "");
            view = new AnagramView(primaryStage, client);
            view.showMainWindow();
        } catch (IOException e) {
            Logger.getLogger(getClass().getName()).log(
                    Level.SEVERE,
                    "Main error",
                    e
            );
            try {
                client.quit();
            } catch (NullPointerException | IOException clientEx) {
                Logger.getLogger(
                        getClass().getName()).log(
                        Level.SEVERE,
                        "Quit client error",
                        clientEx);
            }
            System.exit(0);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
