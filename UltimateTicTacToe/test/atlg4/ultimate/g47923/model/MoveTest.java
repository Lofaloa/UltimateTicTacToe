package atlg4.ultimate.g47923.model;

import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Tests the <code>Move</code> class.
 *
 * @author Logan Farci (47923)
 */
public class MoveTest {

    /**
     * A move is constructed as expected.
     */
    @Test
    public void construction() {
        Position mini = new Position(2, 1);
        Position cell = new Position(1, 2);
        UltimateTicTacToe board = new UltimateTicTacToe();
        Move move = new Move(Player.O, mini, cell, board);
        assertEquals(Player.O, move.getAuthor());
        assertEquals(mini, move.getMiniTicTacToePosition());
        assertEquals(cell, move.getCellPosition());
        assertEquals(board, move.getBoard());
    }

}
