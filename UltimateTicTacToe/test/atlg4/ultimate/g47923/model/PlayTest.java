package atlg4.ultimate.g47923.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests the play method of the UltimateTicTacToe class.
 *
 * @author Logan Farci (47923)
 */
public class PlayTest {

    private void setPlayersUsers(UltimateTicTacToeGame game) {
        game.getX().setUser(new User("Willie", 1, 2, 3));
        game.getO().setUser(new User("Nazari", 1, 2, 3));
    }


    /**
     * A move that has a MiniTicTacToe position equals to the last move Cell
     * position should be in the in the expected MiniTicTacToe.
     */
    @Test
    public void isInExpectedMiniTicTacToe() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        Move lastMove = new Move(
                Player.X,
                new Position(0, 0),
                new Position(1, 2),
                game.getBoard()
        );
        Move currentMove = new Move(
                Player.O,
                new Position(1, 2),
                new Position(1, 1),
                game.getBoard()
        );
        game.getExecutedMoves().add(lastMove);
        assertTrue(game.isInExpectedMiniTicTacToe(currentMove));
    }

    /**
     * A move that has a MiniTicTacToe position different from the last move
     * Cell position should not be in the in the expected MiniTicTacToe.
     */
    @Test
    public void isNotInExpectedMiniTicTacToe() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        Move lastMove = new Move(
                Player.X,
                new Position(0, 0),
                new Position(1, 2),
                game.getBoard()
        );
        Move currentMove = new Move(
                Player.O,
                new Position(2, 2),
                new Position(1, 1),
                game.getBoard()
        );
        game.getExecutedMoves().add(lastMove);
        assertFalse(game.isInExpectedMiniTicTacToe(currentMove));
    }

    /**
     * Calling isInExpectedMiniTicTacToe during the first turn should cause an
     * exception.
     */
    @Test(expected = IllegalStateException.class)
    public void isInExpectedMiniTicTacToe_calledDuringFirstTurn() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        Move currentMove = new Move(
                Player.O,
                new Position(2, 2),
                new Position(1, 1),
                game.getBoard()
        );
        game.isInExpectedMiniTicTacToe(currentMove);
    }

    /**
     * Play method should set the owner at the selected position.
     */
    @Test
    public void play_setOwnerAtSelectedPosition() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        Position miniPosSelectedByX = new Position(1, 1);
        Position cellPosSelectedByX = new Position(1, 2);

        setPlayersUsers(game);

        game.select(miniPosSelectedByX.toDTO(), cellPosSelectedByX.toDTO());
        game.play();
        Grid mini = game.getBoard().getCellAt(miniPosSelectedByX);
        Grid cell = mini.getCellAt(cellPosSelectedByX);

        game.clearUsers();

        assertEquals(Player.X, cell.getOwner());
    }

    /**
     * At first turn, play method should throw an exception when the current
     * player has not selected a position.
     */
    @Test(expected = IllegalStateException.class)
    public void play_firstTurn_currentPlayerHasNotSelected() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        game.play();
    }

    /**
     * Play method should throw an exception when the current player has not
     * selected a position.
     */
    @Test(expected = IllegalStateException.class)
    public void play_currentPlayerHasNotSelected() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        Move last = new Move(
                Player.O,
                new Position(2, 2),
                new Position(1, 1),
                game.getBoard()
        );
        game.getExecutedMoves().add(last);
        game.play();
    }

}
