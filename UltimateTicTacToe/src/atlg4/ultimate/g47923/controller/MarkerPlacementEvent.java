package atlg4.ultimate.g47923.controller;

import atlg4.ultimate.g47923.model.Game;
import atlg4.ultimate.g47923.view.GameView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 *
 *
 * @author Logan Farci (47923)
 */
public class MarkerPlacementEvent implements EventHandler<MouseEvent> {

    private final String CROSS_IMG_PATH = "/images/cross.png";
    private final String CIRCLE_IMG_PATH = "/images/circle.png";

    private final Game game;
    private final GameView view;
    private final int row;
    private final int col;

    public MarkerPlacementEvent(Game game, GameView view, int row, int col) {
        this.game = game;
        this.view = view;
        this.row = row;
        this.col = col;
    }

    @Override
    public void handle(MouseEvent event) {
    }

}
