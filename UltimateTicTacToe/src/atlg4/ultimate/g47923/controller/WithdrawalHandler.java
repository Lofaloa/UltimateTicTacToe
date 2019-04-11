package atlg4.ultimate.g47923.controller;

import atlg4.ultimate.g47923.model.Game;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 *
 * @author Logan Farci (47923)
 */
public class WithdrawalHandler implements EventHandler<ActionEvent> {

    private final Game game;

    /**
     * Constructs an instance of a WithdrawalHandler with the specified game.
     *
     * @param game is the specified game.
     */
    public WithdrawalHandler(Game game) {
        this.game = game;
    }

    /**
     *
     * @param t
     */
    @Override
    public void handle(ActionEvent t) {
        // Withdraw the current player of the game
        System.out.println("Withdrawal!");
    }

}
