package atlg4.ultimate.g47923.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * Tests a game that is over.
 *
 * @author Logan Farci (47923)
 */
public class EndOfAGameTest {

    /**
     * Getting the winner when the game is over should return the owner of the
     * board.
     */
    @Test
    public void getWinner_oOwnsTheBoard() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        game.getX().setWithDrawn(false);
        game.getO().setWithDrawn(false);
        game.getBoard().setOwner(Player.O);
        assertEquals(Marker.O, game.getWinner().getMarker());
    }

    /**
     * Getting the winner when the game is over should return the owner of the
     * board.
     */
    @Test
    public void getWinner_xOwnsTheBoard() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        game.getX().setWithDrawn(false);
        game.getO().setWithDrawn(false);
        game.getBoard().setOwner(Player.X);
        assertEquals(Marker.X, game.getWinner().getMarker());
    }

    /**
     * Getting the winner when O has withdrawn should return X.
     */
    @Test
    public void getWinner_OHasWithdrawn() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        game.getX().setWithDrawn(false);
        game.getO().setWithDrawn(true);
        assertEquals(Marker.X, game.getWinner().getMarker());
    }

    /**
     * Getting the winner when X has withdrawn should return O.
     */
    @Test
    public void getWinner_XHasWithdrawn() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        game.getX().setWithDrawn(true);
        game.getO().setWithDrawn(false);
        assertEquals(Marker.O, game.getWinner().getMarker());
    }

    /**
     * Trying to get the winner before the end of the game causes an exception.
     */
    @Test(expected = IllegalStateException.class)
    public void getWinner_duringAGame() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        game.getWinner();
    }

    /**
     * Trying to get the winner when the game is over but the board is full.
     */
    @Test(expected = IllegalStateException.class)
    public void getWinner_gameIsEven() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        game.getX().setWithDrawn(false);
        game.getO().setWithDrawn(false);
        game.getBoard().fillWith(Player.O);
        game.getWinner();
    }

    /**
     * A game should be over when the board is owned.
     */
    @Test
    public void isOver_boardHasAnOwner() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        game.getBoard().setOwner(Player.O);
        assertTrue(game.isOver());
    }

    /**
     * A game should be over when the board is full.
     */
    @Test
    public void isOver_boardIsFull() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        game.getBoard().fillWith(Player.O);
        assertTrue(game.isOver());
    }

    /**
     * A game should be over when one of the players withdraws.
     */
    @Test
    public void isOver_aPlayerWithDraws() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        game.getX().setWithDrawn(true);
        assertTrue(game.isOver());
        game.getX().setWithDrawn(false);
    }

    /**
     * A call to withdraw should withdraw the expected player.
     */
    @Test
    public void withdraw() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        game.getX().setWithDrawn(false);
        game.getO().setWithDrawn(false);
        game.withdraw();
        assertTrue(game.getCurrentPlayer().isWithDrawn());
    }

    /**
     * The game should be over after calling withdraw and the second player
     * should be the winner.
     */
    @Test
    public void withdraw_gameIsOver() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        game.getX().setWithDrawn(false);
        game.getO().setWithDrawn(false);
        game.withdraw();
        assertTrue(game.isOver());
    }

}
