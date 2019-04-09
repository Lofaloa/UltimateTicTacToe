package atlg4.ultimate.g47923.view;

import atlg4.ultimate.g47923.model.Game;
import java.io.IOException;
import static java.util.Objects.requireNonNull;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Logan
 */
public class UltimateTicTacToeView implements View {

    private static final String TITLE = "Ultimate Tic Tac Toe";

    private final Game game;
    private final Stage stage;
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
        this.gameWindow = new GameWindow(game, stage);
    }

    private void initializeStage() {
        stage.setTitle(TITLE);
        stage.setResizable(true);
        Scene scene = new Scene(gameWindow);
        game.addObserver(gameWindow);
        stage.setScene(scene);
    }

    @Override
    public void showMenuWindow() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void closesMenuWindow() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void showGameWindow() {
        initializeStage();
        stage.show();
    }

    @Override
    public void closeGameWindow() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
