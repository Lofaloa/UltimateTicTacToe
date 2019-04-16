package atlg4.ultimate.g47923.view;

import atlg4.ultimate.g47923.controller.StatisticsWindowController;
import atlg4.ultimate.g47923.model.Game;
import java.io.IOException;
import static java.util.Objects.requireNonNull;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

/**
 * Presents the number of victories, ex aequos and defeats for each players.
 *
 * @author Logan Farci (47923)
 */
public class StatisticsWindow extends VBox {

    private static final String FXML_PATH = "/fxml/StatisticsWindow.fxml";

    private final Game game;
    private final View view;

    /**
     * Constructs an instance of this StatisticsWindow with the specified game
     * and a new stage.
     *
     * @param game is the game to represent.
     */
    StatisticsWindow(Game game, View view) throws IOException {
        this.game = requireNonNull(game, "Constructing a StatisticsWindow with a null "
                + "game.");
        this.view = requireNonNull(view, "Constructing a StatisticsWindow with a null "
                + "view.");
        load();
    }

    private void load() throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(FXML_PATH));
            loader.setRoot(this);
            loader.setController(new StatisticsWindowController(game, view));
            loader.load();
        } catch (IOException exception) {
            throw new IOException(FXML_PATH + " cannot be loaded!", exception);
        }
    }

}
