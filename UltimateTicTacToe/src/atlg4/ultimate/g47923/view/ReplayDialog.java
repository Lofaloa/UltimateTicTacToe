package atlg4.ultimate.g47923.view;

import atlg4.ultimate.g47923.controller.ReplayDialogController;
import atlg4.ultimate.g47923.model.Game;
import atlg4.ultimate.g47923.model.Marker;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;

/**
 * Dialog asking the user if he/ she wants to replay a new game or quit the
 * game. Besides, it is possible for the user to change the users for the next
 * game.
 *
 * @author Logan Farci (47923)
 */
class ReplayDialog extends Dialog<ButtonType> implements Observer {

    private static final String FXML_PATH = "/fxml/ReplayDialogPane.fxml";

    private final Game game;
    private final View view;

    ReplayDialog(Game game, View view) throws IOException {
        this.game = game;
        this.view = view;
        load();
        game.addObserver(this);
    }

    private void load() throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(FXML_PATH));
            loader.setController(new ReplayDialogController(game, view));
            DialogPane root = (DialogPane) loader.load();
            setDialogPane(root);
        } catch (IOException e) {
            throw new IOException(FXML_PATH + " cannot be loaded", e);
        }
    }

    private Label getPlayerLabel(Marker marker) {
        if (marker == Marker.X) {
            return (Label) getDialogPane().lookup("#firstUserPseudonym");
        } else {
            return (Label) getDialogPane().lookup("#secondUserPseudonym");
        }
    }

    private void setPseudonymLabelFor(Marker marker) {
        if (game.hasUserFor(marker)) {
            String pseudonym = game.getPlayer(marker).getUser().getPseudonym();
            getPlayerLabel(marker).setText(pseudonym);
        }
    }

    @Override
    public void update(Observable o, Object o1) {
        setPseudonymLabelFor(Marker.X);
        setPseudonymLabelFor(Marker.O);
    }

}
