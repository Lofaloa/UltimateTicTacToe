package atlg4.ultimate.g47923.view;

import atlg4.ultimate.g47923.model.Game;
import java.io.IOException;
import static java.util.Objects.requireNonNull;
import java.util.Optional;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
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
    private final Dialog replayDialog;
    private final Dialog joinDialog;


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
        this.replayDialog = new ReplayDialog();
        this.joinDialog = new JoinDialog();
        System.out.println("observer added");
        game.addObserver(gameWindow);
        initializeStage();
    }

    private void initializeStage() {
        stage.setTitle(TITLE);
        stage.setResizable(true);
    }

    @Override
    public boolean askConfirmation(String message) {
        Alert confirmation = new ConfirmationAlert(message);
        Optional<ButtonType> result = confirmation.showAndWait();
        return result.get() == ButtonType.OK;
    }

    @Override
    public String askPseudonym() {
        Optional<String> result = joinDialog.showAndWait();
        return result.isPresent() ? result.get() : null;
    }

    @Override
    public boolean askReplay() {
        Optional<ButtonType> result = replayDialog.showAndWait();
        System.out.println(result);
        return result.get().getText().equals("Replay");
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

    @Override
    public void showWarning(String header, String message) {
        Warning warning = new Warning(header, message);
        warning.show();
    }

}
