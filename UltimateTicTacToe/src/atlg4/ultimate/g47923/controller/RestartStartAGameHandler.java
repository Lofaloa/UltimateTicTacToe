package atlg4.ultimate.g47923.controller;

import atlg4.ultimate.g47923.model.Game;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Restarts the given game.
 * 
 * @author Logan Farci (47923)
 */
public class RestartStartAGameHandler implements EventHandler<ActionEvent>{

    private final Game game;

    public RestartStartAGameHandler(Game game) {
        this.game = game;
    }
    
    @Override
    public void handle(ActionEvent t) {
        game.start();
    }
    
}
