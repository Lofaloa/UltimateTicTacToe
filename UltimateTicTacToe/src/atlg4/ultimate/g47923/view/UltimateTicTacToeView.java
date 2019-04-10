package atlg4.ultimate.g47923.view;

import atlg4.ultimate.g47923.model.Game;
import java.io.IOException;
import static java.util.Objects.requireNonNull;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Logan
 */
public class UltimateTicTacToeView extends VBox implements View {

    private static final String TITLE = "Ultimate Tic Tac Toe Menu";
    private static final String FXML_PATH = "/fxml/GameMenuWindow.fxml";

    private final Game game;
    private final Stage stage;
    private final GameWindow gameWindow;

    @FXML
    private Button newgame;

    @FXML
    private Button quit;

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
        this.gameWindow = new GameWindow(game);
        load();
    }

    private void load() throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(FXML_PATH));
            loader.setRoot(this);
            loader.setController(this);
            loader.load();
        } catch (IOException exception) {
            throw new IOException(FXML_PATH + " cannot be loaded!", exception);
        }
    }

    private void initializeStage() {
        stage.setTitle(TITLE);
        stage.setResizable(true);
        Scene scene = new Scene(this);
        stage.setScene(scene);
    }

    @FXML
    private void startNewGame(ActionEvent event) {
        System.out.println("new gameeeee");
        showGameWindow();
    }

    @FXML
    private void quit(ActionEvent event) {
        System.exit(0);
    }

    @Override
    public void showMenuWindow() {
        initializeStage();
        stage.show();
    }

    @Override
    public void closesMenuWindow() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void showGameWindow() {
        stage.close();
        gameWindow.show();
    }

    @Override
    public void closeGameWindow() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
