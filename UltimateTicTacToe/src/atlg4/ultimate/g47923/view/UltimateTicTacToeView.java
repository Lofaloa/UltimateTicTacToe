package atlg4.ultimate.g47923.view;

import atlg4.ultimate.g47923.model.Game;
import java.io.IOException;
import static java.util.Objects.requireNonNull;
import java.util.Optional;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Manages the view of the <i>Ultimate Tic Tac Toe</i> game.
 *
 * @author Logan Farci (47923)
 */
public class UltimateTicTacToeView implements View {

    private static final String TITLE = "Ultimate Tic Tac Toe Menu";
    private static final String ICON_PATH = "/images/icon.png";

    private final Stage stage;
    private final Game game;
    private final GameMenuWindow gameMenuWindow;
    private final GameWindow gameWindow;
    private final StatisticsWindow statisticsWindow;
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
        this.stage = requireNonNull(stage, "Constructing a view with a null "
                + "stage.");
        this.game = requireNonNull(game, "Constructing a view with a null "
                + "game.");
        this.gameMenuWindow = new GameMenuWindow(game, this);
        this.gameWindow = new GameWindow(game, this);
        this.statisticsWindow = new StatisticsWindow(game, this);
        this.replayDialog = new ReplayDialog(game, this);
        this.joinDialog = new JoinDialog();
        game.addObserver(gameMenuWindow);
        game.addObserver(gameWindow);
        initializeStage();

        gameWindow.prefWidthProperty().bind(stage.widthProperty());
        gameWindow.prefHeightProperty().bind(stage.heightProperty());

    }

    private void initializeStage() {
        stage.setTitle(TITLE);
        stage.getIcons().add(new Image(ICON_PATH));
        stage.setScene(new Scene(gameMenuWindow));
        stage.setResizable(true);
        stage.show();
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
        return result.get().getText().equals("Replay");
    }

    @Override
    public void showGameMenuWindow() {
        stage.getScene().setRoot(gameMenuWindow);
        stage.sizeToScene();
        stage.centerOnScreen();
    }

    @Override
    public void showGameWindow() {
        stage.getScene().setRoot(gameWindow);
        stage.sizeToScene();
        stage.centerOnScreen();
    }

    @Override
    public void showStatistics() {
        stage.getScene().setRoot(statisticsWindow);
        stage.sizeToScene();
        stage.centerOnScreen();
    }

    @Override
    public void showWarning(String header, String message) {
        WarningAlert warning = new WarningAlert(header, message);
        warning.show();
    }

    @Override
    public void showInformation(String header, String message) {
        InformationAlert information = new InformationAlert(header, message);
        information.show();
    }

}
