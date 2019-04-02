package atlg4.ultimate.g47923.model;

import atlg4.ultimate.g47923.exception.CellException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests the cell class.
 *
 * @author Logan Farci (47923)
 */
public class CellTest {

    public CellTest() {
    }

    /**
     * A cell is constructed without errors.
     */
    @Test
    public void test_case_0_0() {
        Cell cell = new Cell(0, 0);
        assertNull(cell.getOwner());
        assertEquals(0, cell.getRow());
        assertEquals(0, cell.getColumn());
    }

    /**
     * A cell is not constructed when the row is negative.
     */
    @Test(expected = CellException.class)
    public void test_case_0_1() {
        Cell cell = new Cell(-1, 0);
    }

    /**
     * A cell is not constructed when the row is greater than MAX_COORDINATE.
     */
    @Test(expected = CellException.class)
    public void test_case_0_2() {
        Cell cell = new Cell(3, 0);
    }

    /**
     * A cell is not constructed when the column is negative.
     */
    @Test(expected = CellException.class)
    public void test_case_0_3() {
        Cell cell = new Cell(0, -1);
    }

    /**
     * A cell is not constructed when the column is greater than MAX_COORDINATE.
     */
    @Test(expected = CellException.class)
    public void test_case_0_4() {
        Cell cell = new Cell(0, 3);
    }

    /**
     * The owner setter sets the cell owner as expected.
     */
    @Test
    public void test_case_1_0() {
        Cell cell = new Cell(0, 0);
        Player owner = new Player(Marker.X);
        cell.setOwner(owner);
        assertEquals(owner, cell.getOwner());
    }

    /**
     * The owner cannot be set twice.
     */
    @Test(expected = CellException.class)
    public void test_case_1_1() {
        Cell cell = new Cell(0, 0);
        Player owner = new Player(Marker.X);
        cell.setOwner(owner);
        cell.setOwner(owner);
    }

    /**
     * An empty cell has no owner.
     */
    @Test
    public void test_case_1_2() {
        Cell cell = new Cell(0, 0);
        Player owner = new Player(Marker.X);
        cell.setOwner(owner);
        assertTrue(cell.hasOwner());
    }

    /**
     * A cell has an owner after setting it.
     */
    @Test
    public void test_case_1_3() {
        Cell cell = new Cell(0, 0);
        assertFalse(cell.hasOwner());
    }

    /**
     * The cell equals method is reflexive.
     */
    @Test
    public void test_case_2_0() {
        Cell cell = new Cell(0, 0);
        assertTrue(cell.equals(cell));
    }

    /**
     * The cell equals method is symmetric.
     */
    @Test
    public void test_case_2_1() {
        Cell cellA = new Cell(0, 0);
        Cell cellB = new Cell(0, 0);
        assertTrue(cellA.equals(cellB));
        assertTrue(cellB.equals(cellA));
    }

    /**
     * The cell equals method is transitive.
     */
    @Test
    public void test_case_2_2() {
        Cell cellA = new Cell(0, 0);
        Cell cellB = new Cell(0, 0);
        Cell cellC = new Cell(0, 0);
        assertTrue(cellA.equals(cellB));
        assertTrue(cellB.equals(cellC));
        assertTrue(cellA.equals(cellC));
    }

}
