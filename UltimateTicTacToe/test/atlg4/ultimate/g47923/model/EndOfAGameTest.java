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
        game.withdraw();
        assertTrue(game.getCurrentPlayer().isWithdrawn());
    }

    /**
     * The game should be over after calling withdraw and the second player
     * should be the winner.
     */
    @Test
    public void withdraw_gameIsOver() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        game.withdraw();
        assertTrue(game.isOver());
    }

    /**
     * If the user of X wins the game he should have a victory added and the
     * user of O should have a defeat added.
     */
    @Test
    public void updateUsersStatistics_xWins() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        game.getX().setUser(new User("Bob", 0, 0, 0));
        game.getO().setUser(new User("Patrick", 0, 0, 0));
        game.getBoard().setOwner(Player.X);
        game.updateUsersStatistics();
        assertEquals(1, game.getX().getUser().getNbOfVictories());
        assertEquals(1, game.getO().getUser().getNbOfDefeats());
    }

    /**
     * If the user of X wins because the user of O has withdrawn. X should have
     * a victory added and the user of O should have a defeat added.
     */
    @Test
    public void updateUsersStatistics_xWinsOWithdraws() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        game.getX().setUser(new User("Bob", 0, 0, 0));
        game.getO().setUser(new User("Patrick", 0, 0, 0));
        game.setIsXCurrentPlayer(false);
        game.withdraw();
        game.updateUsersStatistics();
        assertEquals(1, game.getX().getUser().getNbOfVictories());
        assertEquals(1, game.getO().getUser().getNbOfDefeats());
    }

    /**
     * If the user of O wins the game he should have a victory added and the
     * user of X should have a defeat added.
     */
    @Test
    public void updateUsersStatistics_oWins() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        game.getX().setUser(new User("Bob", 0, 0, 0));
        game.getO().setUser(new User("Patrick", 0, 0, 0));
        game.getBoard().setOwner(Player.O);
        game.updateUsersStatistics();
        assertEquals(1, game.getO().getUser().getNbOfVictories());
        assertEquals(1, game.getX().getUser().getNbOfDefeats());
    }

    /**
     * If the user of O wins because the user of X has withdrawn. O should have
     * a victory added and the user of X should have a defeat added.
     */
    @Test
    public void updateUsersStatistics_oWinsXWithdraws() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        game.getX().setUser(new User("Bob", 0, 0, 0));
        game.getO().setUser(new User("Patrick", 0, 0, 0));
        game.withdraw();
        game.updateUsersStatistics();
        assertEquals(1, game.getO().getUser().getNbOfVictories());
        assertEquals(1, game.getX().getUser().getNbOfDefeats());
    }

    /**
     * Both users should have an ex aequo added after en ex aequo.
     */
    @Test
    public void updateUsersStatistics_exaequo() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        game.getX().setUser(new User("Bob", 0, 0, 0));
        game.getO().setUser(new User("Patrick", 0, 0, 0));
        game.getBoard().fillWith(Player.O);
        game.updateUsersStatistics();
        assertEquals(1, game.getO().getUser().getNbOfExaequos());
        assertEquals(1, game.getX().getUser().getNbOfExaequos());
    }

    /**
     * Trying to update the users statistics when the game is not over should
     * causes an exception.
     */
    @Test(expected = IllegalStateException.class)
    public void updateUsersStatistics() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        game.updateUsersStatistics();
    }

    /**
     * When the board of game is full and has no owner, the game should be tied.
     */
    @Test
    public void isTied_fullWithoutOwner() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        game.getBoard().fillWith(Player.O);
        assertTrue(game.isTied());
    }

    /**
     * Calling the isTied method should cause an exception if the game is not
     * over.
     */
    @Test(expected = IllegalStateException.class)
    public void isTied_theGameIsNotOver() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        game.isTied();
    }

}
