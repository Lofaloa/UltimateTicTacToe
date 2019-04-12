package atlg4.ultimate.g47923.view;

import atlg4.ultimate.g47923.dto.UserDTO;
import atlg4.ultimate.g47923.model.Game;
import atlg4.ultimate.g47923.model.Marker;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import static java.util.Objects.requireNonNull;
import java.util.Random;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Is the game menu.
 *
 * @author Logan Farci (47923)
 */
public class GameMenuWindow extends VBox {

    private static final String TITLE = "Ultimate Tic Tac Toe";
    private static final String FXML_PATH = "/fxml/GameMenuWindow.fxml";

    private final Game game;
    private final View view;

    @FXML
    private Label playerX;

    @FXML
    private Label playerO;

    @FXML
    private Button newgame;

    @FXML
    private Button quit;

    /**
     * Constructs an instance of this GameWindow with the specified game and a
     * new stage.
     *
     * @param game is the game to represent.
     */
    GameMenuWindow(Game game, View view) throws IOException {
        this.game = requireNonNull(game, "Constructing a GameMenuWindow with a null "
                + "game.");
        this.view = requireNonNull(view, "Constructing a GameMenuWindow with a null "
                + "view.");
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

    private static int getRandomNumberInRange(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }

    @FXML
    private void join(ActionEvent event) {
        if (game.haveUsersBeenSet()) {
            view.showWarning("All users are set.", "All players are set but if"
                    + " you desire to change a player, click 'disconnect'");
        } else {
            String pseudonym = view.askPseudonym();
            if (pseudonym != null) {
                int random = getRandomNumberInRange(0, 1);
                Marker m = random == 1 ? Marker.X : Marker.O;
                game.setUserOf(m, new UserDTO(pseudonym, 0, 0, 0));
                if (m == Marker.X) {
                    playerX.setText(pseudonym);
                } else {
                    playerO.setText(pseudonym);
                }
            }
        }
    }

    @FXML
    private void startNewGame(ActionEvent event) {
        view.showGameWindow();
    }

    @FXML
    private void quit(ActionEvent event) {
        System.exit(0);
    }

}
