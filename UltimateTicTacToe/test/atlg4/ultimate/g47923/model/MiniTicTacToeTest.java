package atlg4.ultimate.g47923.model;

import atlg4.ultimate.g47923.exception.IllegalMoveException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests the <code>MiniTicTacToe</code> class.
 *
 * @author Logan Farci (47923)
 */
public class MiniTicTacToeTest {

    @Test
    public void construction() {
        Position position = new Position();
        Grid m = new MiniTicTacToe(position);
        assertFalse(m.hasOwner());
        assertFalse(m.isFull());
        assertFalse(m.hasFullColumnOwnedBy(Player.O));
        assertFalse(m.hasFullColumnOwnedBy(Player.X));
        assertFalse(m.hasFullRowOwnedBy(Player.O));
        assertFalse(m.hasFullRowOwnedBy(Player.X));
        assertFalse(m.hasFullDiagonalOwnedBy(Player.O));
        assertFalse(m.hasFullDiagonalOwnedBy(Player.X));
    }

    /**
     * A <code>MiniTicTacToe</code> is initialized as expected.
     */
    @Test
    public void initialization() {
        Position position = new Position();
        Grid m = new MiniTicTacToe(position);
        int current = 0;
        for (int row = 0; row < MiniTicTacToe.SIZE; row++) {
            for (int column = 0; column < MiniTicTacToe.SIZE; column++) {
                Grid grid = (Grid) m.getCells().get(current);
                assertFalse(grid.hasOwner());
                assertEquals(new Position(row, column), grid.getPosition());
                current++;
            }
        }
        assertEquals(9, m.getCells().size());
    }

    /**
     * Trying to get a cell at a given position should get the expected cell.
     */
    @Test
    public void getCellAt() {
        Position position = new Position();
        Grid m = new MiniTicTacToe(position);
        assertEquals(new Position(), m.getCellAt(new Position()).getPosition());
        assertEquals(new Position(2, 2), m.getCellAt(new Position(2, 2)).getPosition());
        assertEquals(new Position(2, 1), m.getCellAt(new Position(2, 1)).getPosition());
        assertEquals(new Position(0, 1), m.getCellAt(new Position(0, 1)).getPosition());
    }

    /**
     * Trying to get a cell at a null position causes an exception.
     */
    @Test(expected = NullPointerException.class)
    public void getCellAt_nullPosition() {
        Position position = new Position();
        Grid m = new MiniTicTacToe(position);
        m.getCellAt(null);
    }

    /**
     * Setting an null owner causes an exception.
     */
    @Test(expected = NullPointerException.class)
    public void setOwnerAt_nullOwner() {
        Position position = new Position();
        MiniTicTacToe m = new MiniTicTacToe(position);
        m.setOwnerAt(null, position);
    }

    /**
     * Setting an owner at a null position causes an exception.
     */
    @Test(expected = NullPointerException.class)
    public void setOwnerAt_nullPosition() {
        Position position = new Position();
        MiniTicTacToe m = new MiniTicTacToe(position);
        m.setOwnerAt(Player.O, null);
    }

    /**
     * Setting an owner should set the owner at expected position.
     */
    @Test
    public void setOwnerAt_noErrors() {
        Position position = new Position();
        MiniTicTacToe m = new MiniTicTacToe(position);
        Position dest = new Position(1, 2);
        Player owner = Player.O;
        m.setOwnerAt(owner, dest);
        assertEquals(owner, m.getCellAt(dest).getOwner());
        assertFalse(m.hasOwner());
        assertFalse(m.isOwnedBy(owner));
    }

    /**
     * After setting a full row of an owner, the <code>MiniTicTacToe</code>
     * owner should be set to the same owner.
     */
    @Test
    public void setOwnerAt_miniTicTacToeSetAfterSettingAFullRow() {
        Position position = new Position();
        MiniTicTacToe m = new MiniTicTacToe(position);
        m.setOwnerAt(Player.O, new Position(1, 0));
        m.setOwnerAt(Player.O, new Position(1, 1));
        m.setOwnerAt(Player.O, new Position(1, 2));
        assertTrue(m.hasFullRowOwnedBy(Player.O));
        assertTrue(m.isOwnedBy(Player.O));
        assertTrue(m.hasOwner());
        assertEquals(Player.O, m.getOwner());
    }

    /**
     * Trying to override a MiniTicTacToe Cell should cause an exception.
     */
    @Test(expected = IllegalMoveException.class)
    public void setOwnerAt_overridingOwner() {
        Position position = new Position();
        MiniTicTacToe m = new MiniTicTacToe(position);
        m.setOwnerAt(Player.O, new Position(1, 1));
        m.setOwnerAt(Player.X, new Position(1, 1));
    }

    /**
     * A MiniTicTacToe should be full when all of its cells are owned and should
     * not have a owner if no full row, column or diagonal is owned.
     */
    @Test
    public void isFull_allCellsOwned_noOwner() {
        MiniTicTacToe m = new MiniTicTacToe(new Position());
        m.setOwnerAt(Player.O, new Position(0, 0));
        m.setOwnerAt(Player.X, new Position(0, 1));
        m.setOwnerAt(Player.O, new Position(0, 2));
        m.setOwnerAt(Player.X, new Position(1, 0));
        m.setOwnerAt(Player.O, new Position(1, 1));
        m.setOwnerAt(Player.X, new Position(1, 2));
        m.setOwnerAt(Player.X, new Position(2, 0));
        m.setOwnerAt(Player.O, new Position(2, 1));
        m.setOwnerAt(Player.X, new Position(2, 2));
        assertTrue(m.isFull());
        assertFalse(m.isOwnedBy(Player.O));
        assertFalse(m.isOwnedBy(Player.X));
        assertFalse(m.hasOwner());
    }

    /**
     * A MiniTicTacToe should be full when all of its cells are owned.
     */
    @Test
    public void isFull_allCellsOwned() {
        MiniTicTacToe m = new MiniTicTacToe(new Position());
        for (int row = 0; row < MiniTicTacToe.SIZE; row++) {
            for (int column = 0; column < MiniTicTacToe.SIZE; column++) {
                m.setOwnerAt(Player.O, new Position(row, column));
            }
        }
        assertTrue(m.isFull());
    }

    /**
     * Calling hasFullRowOwnedBy should return false for both players when the
     * grid is empty.
     */
    @Test
    public void hasFullRowOwnedBy_emptyGrid() {
        Position position = new Position();
        Grid m = new MiniTicTacToe(position);
        assertFalse(m.hasFullRowOwnedBy(Player.O));
        assertFalse(m.hasFullRowOwnedBy(Player.X));
    }

    /**
     * Calling hasFullRowOwnedBy should return true if a player owns a full row.
     */
    @Test
    public void hasFullRowOwnedBy_aFullRowIsOwned() {
        Position position = new Position();
        MiniTicTacToe m = new MiniTicTacToe(position);
        m.setOwnerAt(Player.O, new Position(1, 0));
        m.setOwnerAt(Player.O, new Position(1, 1));
        m.setOwnerAt(Player.O, new Position(1, 2));
        assertTrue(m.hasFullRowOwnedBy(Player.O));
    }

    /**
     * Calling hasFullColumnOwnedBy should return false for both players when
     * the grid is empty.
     */
    @Test
    public void hasFullColumnOwnedBy_emptyGrid() {
        Position position = new Position();
        Grid m = new MiniTicTacToe(position);
        assertFalse(m.hasFullRowOwnedBy(Player.O));
        assertFalse(m.hasFullRowOwnedBy(Player.X));
    }

    /**
     * Calling hasFullColumnOwnedBy should return true if a player owns a full
     * columns.
     */
    @Test
    public void hasFullColumnOwnedBy_aFullColumnIsOwned() {
        Position position = new Position();
        MiniTicTacToe m = new MiniTicTacToe(position);
        m.setOwnerAt(Player.O, new Position(0, 2));
        m.setOwnerAt(Player.O, new Position(1, 2));
        m.setOwnerAt(Player.O, new Position(2, 2));
        assertTrue(m.hasFullColumnOwnedBy(Player.O));
    }

    /**
     * Calling hasFullDiagonalOwnedBy should return false for both players when
     * the grid is empty
     */
    @Test
    public void hasFullDiagonalOwnedBy_emptyGrid() {
        Position position = new Position();
        Grid m = new MiniTicTacToe(position);
        assertFalse(m.hasFullDiagonalOwnedBy(Player.O));
        assertFalse(m.hasFullDiagonalOwnedBy(Player.X));
    }

    /**
     * Calling hasFullDiagonalOwnedBy should return true if a player owns the
     * ascending diagonal.
     */
    @Test
    public void hasFullDiagonalOwnedBy_aFullAscendingDiagonalIsOwned() {
        Position position = new Position();
        MiniTicTacToe m = new MiniTicTacToe(position);
        m.setOwnerAt(Player.O, new Position(0, 2));
        m.setOwnerAt(Player.O, new Position(1, 1));
        m.setOwnerAt(Player.O, new Position(2, 0));
        assertTrue(m.hasFullDiagonalOwnedBy(Player.O));
    }

    /**
     * Calling hasFullDiagonalOwnedBy should return true if a player owns the
     * descending diagonal.
     */
    @Test
    public void hasFullDiagonalOwnedBy_aFullDescendingDiagonalIsOwned() {
        Position position = new Position();
        MiniTicTacToe m = new MiniTicTacToe(position);
        m.setOwnerAt(Player.O, new Position(0, 0));
        m.setOwnerAt(Player.O, new Position(1, 1));
        m.setOwnerAt(Player.O, new Position(2, 2));
        assertTrue(m.hasFullDiagonalOwnedBy(Player.O));
    }

    /**
     * Calling isOwnedBy should return true if a player owns a full row.
     */
    @Test
    public void isOwnedBy() {
        Position position = new Position();
        MiniTicTacToe m = new MiniTicTacToe(position);
        m.setOwnerAt(Player.O, new Position(0, 0));
        m.setOwnerAt(Player.O, new Position(1, 1));
        m.setOwnerAt(Player.O, new Position(2, 2));
        assertTrue(m.isOwnedBy(Player.O));
    }

}
