package atlg4.ultimate.g47923.controller;

import atlg4.ultimate.g47923.model.Game;
import atlg4.ultimate.g47923.view.GameWindow;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Restarts the given game.
 *
 * @author Logan Farci (47923)
 */
public class RestartStartAGameHandler implements EventHandler<ActionEvent> {

    private static final String MESSAGE = "You are about to restart the game, all the "
                + "advancement will be lost!";
    
    private final Game game;
    private final GameWindow view;

    public RestartStartAGameHandler(Game game, GameWindow view) {
        this.game = game;
        this.view = view;
    }

    @Override
    public void handle(ActionEvent t) {
        if (game.isOver() || view.askConfirmation(MESSAGE)) {
            if (game.isOver()) System.out.println("the game is over: restarting");
            else System.out.println("the game is not over: restarting");
            game.start();
            view.clearBoard();
        }
    }

}
