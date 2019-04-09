package atlg4.ultimate.g47923;

import atlg4.ultimate.g47923.exception.UltimateTicTacToeException;
import atlg4.ultimate.g47923.model.Game;
import atlg4.ultimate.g47923.model.UltimateTicTacToeGame;
import atlg4.ultimate.g47923.view.UltimateTicTacToeView;
import atlg4.ultimate.g47923.view.View;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;

public class Main extends Application {

    static final String TITLE = "Ultimate TicTacToe";
    static final String FXML_PATH = "/fxml/GameScreen.fxml";

    @Override
    public void start(Stage primaryStage) {
        try {
            Game game = new UltimateTicTacToeGame();
            View view = new UltimateTicTacToeView(game, primaryStage);
            view.showGameWindow();
        } catch (UltimateTicTacToeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
