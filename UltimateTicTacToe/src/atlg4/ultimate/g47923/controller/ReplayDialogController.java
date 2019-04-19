package atlg4.ultimate.g47923.controller;

import atlg4.ultimate.g47923.exception.DataBaseException;
import atlg4.ultimate.g47923.model.Game;
import atlg4.ultimate.g47923.persistence.business.UsersFacade;
import atlg4.ultimate.g47923.view.View;
import java.net.URL;
import static java.util.Objects.requireNonNull;
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

    /**
     * Constructs an instance of ReplayDialogController with the given game and view.
     * 
     * @param game is the given game.
     * @param view is the given view.
     */
    public ReplayDialogController(Game game, View view) {
        this.game = requireNonNull(game, "Trying to construct a "
                + "ReplayDialogController with a null game");
        this.view = requireNonNull(view, "Trying to construct a "
                + "ReplayDialogController with a null view");
    }

    private void setUser(String pseudonym) {
        try {
            if (pseudonym != null) {
                game.setUser(UsersFacade.getUser(pseudonym));
            }
        } catch (DataBaseException e) {
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
