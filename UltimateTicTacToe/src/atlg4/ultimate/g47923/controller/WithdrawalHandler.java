package atlg4.ultimate.g47923.controller;

import atlg4.ultimate.g47923.model.Game;
import atlg4.ultimate.g47923.view.GameWindow;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 *
 * @author Logan Farci (47923)
 */
public class WithdrawalHandler implements EventHandler<ActionEvent> {

    private final static String MESSAGE = "You are about to withdraw and grant "
            + "the victory to your opponent!";
    
    private final Game game;
    private final GameWindow view;

    /**
     * Constructs an instance of a WithdrawalHandler with the specified game.
     *
     * @param game is the specified game.
     */
    public WithdrawalHandler(Game game, GameWindow view) {
        this.game = game;
        this.view = view;
    }

    /**
     *
     * @param t
     */
    @Override
    public void handle(ActionEvent t) {
        if (view.askConfirmation(MESSAGE)) {
            game.withdraw();
            view.showEnd();
        }
    }

}
