package atlg4.ultimate.g47923.view;

import atlg4.ultimate.g47923.controller.GameMenuWindowController;
import atlg4.ultimate.g47923.model.Game;
import atlg4.ultimate.g47923.model.Marker;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import static java.util.Objects.requireNonNull;
import java.util.Observable;
import java.util.Observer;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Is the game menu.
 *
 * @author Logan Farci (47923)
 */
public class GameMenuWindow extends VBox implements Observer {

    private static final String FXML_PATH = "/fxml/GameMenuWindow.fxml";

    private final Game game;
    private final View view;

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
            loader.setController(new GameMenuWindowController(game, view));
            loader.load();
        } catch (IOException exception) {
            throw new IOException(FXML_PATH + " cannot be loaded!", exception);
        }
    }

    private Label getPlayerLabel(Marker marker) {
        if (marker == Marker.X) {
            return (Label) lookup("#firstUserPseudonym");
        } else {
            return (Label) lookup("#secondUserPseudonym");
        }
    }

    private void showPlayerDeleteButton(Marker marker) {
        if (marker == Marker.X) {
            ((Button) lookup("#firstUserDelete")).setVisible(true);
        } else {
            ((Button) lookup("#secondUserDelete")).setVisible(true);
        }
    }

    private void setPseudonymLabelFor(Marker marker) {
        if (game.hasUserFor(marker)) {
            String pseudonym = game.getPlayer(marker).getUser().getPseudonym();
            getPlayerLabel(marker).setText(pseudonym);
        }
    }

    private void updateUsers() {
        if (game.hasUserFor(Marker.X)) {
            setPseudonymLabelFor(Marker.X);
            showPlayerDeleteButton(Marker.X);
        } else {
            getPlayerLabel(Marker.X).setText("no user");
        }
        if (game.hasUserFor(Marker.O)) {
            setPseudonymLabelFor(Marker.O);
            showPlayerDeleteButton(Marker.O);
        } else {
            getPlayerLabel(Marker.O).setText("no user");
        }
    }

    @Override
    public void update(Observable o, Object o1) {
        updateUsers();
    }

}
