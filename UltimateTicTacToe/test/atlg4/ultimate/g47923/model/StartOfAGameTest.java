package atlg4.ultimate.g47923.model;

import atlg4.ultimate.g47923.dto.PositionDTO;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * Tests the start of a game.
 *
 * @author Logan Farci (47923)
 */
public class StartOfAGameTest {

     private void setPlayersUsers(UltimateTicTacToeGame game) {
        game.getX().setUser(new User("Willie", 1, 2, 3));
        game.getO().setUser(new User("Nazari", 1, 2, 3));
    }

    /**
     * A game should be constructed as expected.
     */
    @Test
    public void construction() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        assertEquals(Marker.X, game.getCurrentPlayer().getMarker());
        assertFalse(game.isOver());
        assertFalse(game.hasAPlayerWithdrawn());
    }

    /**
     * When starting a game, the board should be empty.
     */
    @Test
    public void start_theBoardIsEmpty() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        game.start();
        assertTrue(game.getBoard().isEmpty());
    }

    /**
     * When starting a game, the players should not be withdrawn.
     */
    @Test
    public void start_playersAreNotWithDrawn() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        game.start();
        assertFalse(game.hasAPlayerWithdrawn());
    }

    /**
     * When starting a game, X should be the current player.
     */
    @Test
    public void start_xIsTheCurrentPlayer() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        game.start();
        assertEquals(Marker.X, game.getCurrentPlayer().getMarker());
    }

    /**
     * When starting a game, no moves should have been executed.
     */
    @Test
    public void start_noMovesAreExecuted() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        game.start();
        assertTrue(game.getExecutedMoves().isEmpty());
    }

    /**
     * When starting a game after the game was full, the game should not be over
     * anymore.
     */
    @Test
    public void start_afterGameIsOver() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        game.getBoard().fillWith(Player.O);
        game.start();
        assertFalse(game.isOver());
    }

    /**
     * When starting a game after the game had an owner, the game should not be
     * over anymore.
     */
    @Test
    public void start_afterGameHadOwner() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        game.getBoard().setOwner(Player.O);
        game.start();
        assertFalse(game.isOver());
    }

    /**
     * When starting a game after the game had an withdrawer, the game should
     * not be over anymore.
     */
    @Test
    public void start_afterGameHadWithdrawer() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        game.withdraw();
        game.start();
        assertFalse(game.isOver());
    }

    /**
     * Right after construction, the game should be at first turn.
     */
    @Test
    public void isFirstTurn_rightAfterConstruction() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        assertTrue(game.isFirstTurn());
    }

    /**
     * When the first turn has been played, the game should not be at first turn
     * anymore.
     */
    @Test
    public void isFirstTurn_afterFirstTurnWasPlayed() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();

        setPlayersUsers(game);

        game.select(new PositionDTO(2, 2), new PositionDTO(0, 0));
        game.play();

        game.clearUsers();

        assertFalse(game.isFirstTurn());
    }

}
