/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atlg4.client.g47923;

import atlg4.client.g47923.view.AnagramView;
import atlg4.client.g47923.view.View;
import atlg4.g47923.anagram.players.Credentials;
import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * This class is the entry point of the Anagram client.
 *
 * @author Logan Farci (47923)
 */
public class AnagramClientMain extends Application {

    private static final String CONNECTION_ERROR_HEADER = "Connexion impossible";

    private static final String CONNECTION_ERROR_MESSAGE = "Une erreur s'est produite"
            + " lors du traitement de vos données.";

    @Override
    public void start(Stage primaryStage) throws IOException {
        View view = new AnagramView(primaryStage);
        Credentials credentials = view.askCredentials();
        view.showMainWindow();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
