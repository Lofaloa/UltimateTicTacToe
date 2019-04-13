package atlg4.ultimate.g47923.view;

import atlg4.ultimate.g47923.controller.GameMenuWindowController;
import atlg4.ultimate.g47923.model.Game;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import static java.util.Objects.requireNonNull;

/**
 * Is the game menu.
 *
 * @author Logan Farci (47923)
 */
public class GameMenuWindow extends VBox {

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

}
