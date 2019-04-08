package atlg4.ultimate.g47923;

import atlg4.ultimate.g47923.model.Game;
import atlg4.ultimate.g47923.model.UltimateTicTacToeGame;
import atlg4.ultimate.g47923.view.GameView;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;

public class Main extends Application {

    static final String TITLE = "Ultimate TicTacToe";
    static final String FXML_PATH = "/fxml/GameScreen.fxml";

    @Override
    public void start(Stage primaryStage) {
        Game game = new UltimateTicTacToeGame();
        GameView view = new GameView(game);
        view.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
