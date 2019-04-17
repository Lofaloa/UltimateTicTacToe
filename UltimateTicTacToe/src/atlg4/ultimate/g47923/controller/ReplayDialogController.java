package atlg4.ultimate.g47923.controller;

import atlg4.ultimate.g47923.exception.UltimateTicTacToeDbException;
import atlg4.ultimate.g47923.model.Game;
import atlg4.ultimate.g47923.persistence.business.AdminFacade;
import atlg4.ultimate.g47923.view.View;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;

/**
 *
 *
 * @author Logan Farci (47923)
 */
public class ReplayDialogController implements Initializable {

    private final Game game;
    private final View view;

    @FXML
    private DialogPane dialog;

    @FXML
    private ButtonType replay;

    @FXML
    private ButtonType menu;

    @FXML
    private ButtonType quit;

    public ReplayDialogController(Game game, View view) {
        this.game = game;
        this.view = view;
    }

    private void setUser(String pseudonym) {
        try {
            if (pseudonym != null) {
                game.setUser(AdminFacade.getUser(pseudonym));
            }
        } catch (UltimateTicTacToeDbException e) {
            view.showWarning("Impossible to set the user!", e.getMessage());
        }
    }

    @FXML
    private void setCrossesUser(ActionEvent event) {
        String pseudonym = view.askPseudonym();
        setUser(pseudonym);
    }

    @FXML
    private void setCirclesUser(ActionEvent event) {
        String pseudonym = view.askPseudonym();
        setUser(pseudonym);
    }

    private void setEventFilter() {
        Button replayButton = (Button) dialog.lookupButton(replay);
        replayButton.addEventFilter(ActionEvent.ACTION, e -> {
            game.start();
        });
        Button menuButton = (Button) dialog.lookupButton(menu);
        menuButton.addEventFilter(ActionEvent.ACTION, e -> {
            game.start();
            view.showGameMenuWindow();
        });
        Button quitButton = (Button) dialog.lookupButton(quit);
        quitButton.addEventFilter(ActionEvent.ACTION, e -> {
            System.exit(0);
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setEventFilter();
    }

}
