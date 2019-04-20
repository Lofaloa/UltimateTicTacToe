/**
 * doc du package principal
 */
package atlg4.ultimate.g47923;

import atlg4.ultimate.g47923.model.Game;
import atlg4.ultimate.g47923.model.UltimateTicTacToeGame;
import atlg4.ultimate.g47923.view.ErrorAlert;
import atlg4.ultimate.g47923.view.UltimateTicTacToeView;
import atlg4.ultimate.g47923.view.View;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 * Is the entry point of the <i>Ultimate Tic Tac Toe</i> game.
 *
 * @author Logan Farci (47923)
 */
public class Main extends Application {
    
    static final String TITLE = "Ultimate TicTacToe";
    static final String FXML_PATH = "/fxml/GameScreen.fxml";

    @Override
    public void start(Stage primaryStage) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        try {
            Game game = new UltimateTicTacToeGame();
            View view = new UltimateTicTacToeView(game, primaryStage);
            view.showGameMenuWindow();
        } catch (IllegalStateException | IOException e) {
            Alert error = new ErrorAlert(e);
            error.show();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
