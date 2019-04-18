package atlg4.ultimate.g47923.controller;

import atlg4.ultimate.g47923.model.Game;
import atlg4.ultimate.g47923.model.Marker;
import atlg4.ultimate.g47923.persistence.business.UsersFacade;
import atlg4.ultimate.g47923.view.View;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;

/**
 *
 * @author Logan Farci (47923)
 */
public class GameMenuWindowController implements Initializable {

    private final Game game;
    private final View view;

    @FXML
    private Label playerX;

    @FXML
    private Label playerO;

    @FXML
    private Button join;

    @FXML
    private Button newgame;

    @FXML
    private Button loadgame;

    @FXML
    private Button statistics;

    public GameMenuWindowController(Game game, View view) {
        this.game = game;
        this.view = view;
    }

    private void enable(Button button) {
        button.setDisable(false);
        button.getStyleClass().remove("unavailable");
        button.getStyleClass().add("available");
    }

    private void disable(Button button) {
        button.setDisable(true);
        button.getStyleClass().remove("available");
        button.getStyleClass().add("unavailable");
    }

    private boolean haveUsersBothBeenSet() {
        return game.hasUserFor(Marker.X) && game.hasUserFor(Marker.O);
    }

    @FXML
    private void join(ActionEvent event) {
        String pseudonym = view.askPseudonym();
        try {
            if (pseudonym != null) {
                game.setUser(UsersFacade.getUser(pseudonym));
                view.showInformation("Welcome " + pseudonym + "!", "You are now "
                        + "registered as " + pseudonym + ", your statistics will"
                                + " be updated after you are done playing!");
            }
            if (haveUsersBothBeenSet()) {
                enable(newgame);
                disable(join);
            }
        } catch (Exception e) {
            view.showWarning("Impossible to join!", e.getMessage());
        }
    }

    @FXML
    private void startNewGame(ActionEvent event) {
        view.showGameWindow();
    }

    @FXML
    private void showStatistics(ActionEvent event) {
        view.showStatistics();
    }

    @FXML
    private void quit(ActionEvent event) {
        System.exit(0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        join.setTooltip(new Tooltip("Join and start a game!"));
        newgame.setDisable(true);
        loadgame.setDisable(true);
    }

}
