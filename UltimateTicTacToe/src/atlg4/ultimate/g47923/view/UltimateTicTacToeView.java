package atlg4.ultimate.g47923.view;

import atlg4.ultimate.g47923.model.Game;
import java.io.IOException;
import static java.util.Objects.requireNonNull;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Manages the view of this application.
 * 
 * @author Logan Farci (47923)
 */
public class UltimateTicTacToeView implements View {

    private static final String TITLE = "Ultimate Tic Tac Toe Menu";
    private static final String FXML_PATH = "/fxml/GameMenuWindow.fxml";

    private final Game game;
    private final Stage stage;
    private final GameMenuWindow gameMenuWindow;
    private final GameWindow gameWindow;

    /**
     * Constructs this view with the specified game to represent.
     *
     * @param game the specified game.
     * @param stage is the stage of this window.
     * @throws java.io.IOException if one of the FXML files cannot be loaded.
     */
    public UltimateTicTacToeView(Game game, Stage stage) throws IOException {
        this.game = requireNonNull(game, "Constructing a view with a null game!");
        this.stage = requireNonNull(stage, "Constructing a view with a null "
                + "stage.");
        this.gameMenuWindow = new GameMenuWindow(game, this);
        this.gameWindow = new GameWindow(game, this);
        game.addObserver(gameWindow);
        initializeStage();
    }

    private void initializeStage() {
        stage.setTitle(TITLE);
        stage.setResizable(true);
    }
    
    @Override
    public void showGameMenuWindow() {
        stage.setScene(new Scene(gameMenuWindow));
        stage.show();
        stage.centerOnScreen();
    }

    @Override
    public void showGameWindow() {
        stage.setScene(new Scene(gameWindow));
        stage.centerOnScreen();
    }

}
