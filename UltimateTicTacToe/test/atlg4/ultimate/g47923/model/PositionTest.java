package atlg4.ultimate.g47923.model;

import atlg4.ultimate.g47923.exception.IllegalPositionException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests the position class.
 *
 * @author Logan Farci (47923)
 */
public class PositionTest {

    public PositionTest() {
    }

    /**
     * A position is constructed without errors.
     */
    @Test
    public void construction_without_errors() {
        Position position = new Position(0, 0);
        assertEquals(0, position.getRow());
        assertEquals(0, position.getColumn());
    }

    /**
     * A position is not constructed when the row is negative.
     */
    @Test(expected = IllegalPositionException.class)
    public void construction_with_negative_row() {
        new Position(-1, 0);
    }

    /**
     * A position is not constructed when the row is greater than
     * MAX_COORDINATE.
     */
    @Test(expected = IllegalPositionException.class)
    public void construction_with_greater_row() {
        new Position(3, 0);
    }

    /**
     * A position is not constructed when the column is negative.
     */
    @Test(expected = IllegalPositionException.class)
    public void construction_with_negative_column() {
        new Position(0, -1);
    }

    /**
     * A position is not constructed when the column is greater than
     * MAX_COORDINATE.
     */
    @Test(expected = IllegalPositionException.class)
    public void construction_with_greater_column() {
        new Position(0, 3);
    }

    /**
     * The position equals method is reflexive.
     */
    @Test
    public void equals_reflexive() {
        Position position = new Position(0, 0);
        assertTrue(position.equals(position));
    }

    /**
     * The position equals method is symmetric.
     */
    @Test
    public void equals_symmetric() {
        Position positionA = new Position(0, 0);
        Position positionB = new Position(0, 0);
        assertTrue(positionA.equals(positionB));
        assertTrue(positionB.equals(positionA));
    }

    /**
     * The position equals method is transitive.
     */
    @Test
    public void equals_transitive() {
        Position positionA = new Position(0, 0);
        Position positionB = new Position(0, 0);
        Position positionC = new Position(0, 0);
        assertTrue(positionA.equals(positionB));
        assertTrue(positionB.equals(positionC));
        assertTrue(positionA.equals(positionC));
    }

    /**
     * The position equals method return false when the given object is not of
     * the same type.
     */
    @Test
    public void equals_falseWithDifferentClass() {
        Position position = new Position(0, 0);
        assertFalse(position.equals("position"));
    }
    
    /**
     * The position equals method return false when the given object is null.
     */
    @Test
    public void equals_falseWithNullObject() {
        Position position = new Position(0, 0);
        assertFalse(position.equals(null));
    }
    
    /**
     * Two position that are equal should have the same hash code.
     */
    @Test
    public void hashCode_equalPositionHaveTheSameHashCode() {
        Position positionA = new Position(0, 0);
        Position positionB = new Position(0, 0);
        assertEquals(positionA.hashCode(), positionB.hashCode());
    }

}
