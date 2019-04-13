package atlg4.ultimate.g47923.controller;

import atlg4.ultimate.g47923.dto.UserDTO;
import atlg4.ultimate.g47923.model.Game;
import atlg4.ultimate.g47923.model.Marker;
import atlg4.ultimate.g47923.view.View;
import java.util.Random;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 *
 * @author Logan Farci (47923)
 */
public class GameMenuWindowController {

    private final Game game;
    private final View view;

    @FXML
    private Label playerX;

    @FXML
    private Label playerO;

    public GameMenuWindowController(Game game, View view) {
        this.game = game;
        this.view = view;
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
