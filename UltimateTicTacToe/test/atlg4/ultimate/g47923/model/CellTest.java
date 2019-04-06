package atlg4.ultimate.g47923.model;

import atlg4.ultimate.g47923.exception.GridException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests cell class.
 *
 * @author Logan Farci (47923)
 */
public class CellTest {

    /**
     * A cell is constructed as expected.
     */
    @Test
    public void construction() {
        Position position = new Position();
        Grid cell = new Cell(position);
        assertEquals(position, cell.getPosition());
        assertFalse(cell.hasOwner());
    }

    /**
     * Setting the owner of a cell should set it as expected.
     */
    @Test
    public void setOwner() {
        Position position = new Position();
        Player owner = Player.O;
        Cell cell = new Cell(position);
        cell.setOwner(owner);
        assertEquals(position, cell.getPosition());
        assertTrue(cell.hasOwner());
        assertEquals(owner, cell.getOwner());
    }

    /**
     * Trying to initialize a cell causes an exception.
     */
    @Test(expected = GridException.class)
    public void initialization() {
        Position position = new Position();
        Grid cell = new Cell(position);
        cell.initialize();
    }

    /**
     * Trying to get a cell at a position in a cell should return null.
     */
    @Test
    public void getCellAt() {
        Position position = new Position();
        Cell cell = new Cell(position);
        assertNull(cell.getCellAt(new Position()));
    }

    /**
     * Trying to get a cell at a null position causes an exception.
     */
    @Test(expected = NullPointerException.class)
    public void getCellAt_nullPosition() {
        Position position = new Position();
        Cell cell = new Cell(position);
        cell.getCellAt(null);
    }

    /**
     * Calling hasFullRowOwnedBy should return false for both players.
     */
    @Test
    public void hasFullRowOwnedBy() {
        Position position = new Position();
        Cell cell = new Cell(position);
        assertFalse(cell.hasFullRowOwnedBy(Player.O));
        assertFalse(cell.hasFullRowOwnedBy(Player.X));
    }

    /**
     * Calling hasFullColumnOwnedBy should return false for both players.
     */
    @Test
    public void hasFullColumnOwnedBy() {
        Position position = new Position();
        Cell cell = new Cell(position);
        assertFalse(cell.hasFullRowOwnedBy(Player.O));
        assertFalse(cell.hasFullRowOwnedBy(Player.X));
    }

    /**
     * Calling hasFullDiagonalOwnedBy should return false for both players.
     */
    @Test
    public void hasFullDiagonalOwnedBy() {
        Position position = new Position();
        Cell cell = new Cell(position);
        assertFalse(cell.hasFullDiagonalOwnedBy(Player.O));
        assertFalse(cell.hasFullDiagonalOwnedBy(Player.X));
    }

}
