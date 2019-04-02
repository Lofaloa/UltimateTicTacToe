package atlg4.ultimate.g47923.model;

import atlg4.ultimate.g47923.exception.CellException;
import java.util.Objects;

/**
 * Represents a <i>MiniTicTacToe</i> cell.
 *
 * @author Logan Farci (47923)
 */
class Cell {

    static final int MAX_COORDINATE = 2;

    private Player owner;
    private final int row;
    private final int column;

    /**
     * Constructs this cell with the given coordinates. This cell is constructed
     * without an owner.
     *
     * @param row is the row of this cell.
     * @param column is the column of this cell.
     * @throws CellException is one of the two given coordinate are not valid.
     */
    public Cell(int row, int column) {
        this.owner = null;
        this.row = requireValidCoordinate(row, row + " is not a valid row.");
        this.column = requireValidCoordinate(column, column + " is not a valid "
                + "column.");
    }

    private int requireValidCoordinate(int coo, String msg) {
        if (coo < 0 || MAX_COORDINATE < coo) {
            throw new CellException(msg);
        }
        return coo;
    }

    /**
     * Gets this cell row.
     *
     * @return this cell row.
     */
    int getRow() {
        return row;
    }

    /**
     * Gets this cell column.
     *
     * @return this cell column.
     */
    int getColumn() {
        return column;
    }

    /**
     * Gets this cell owner.
     *
     * @return this cell owner.
     */
    Player getOwner() {
        return owner;
    }

    /**
     * Sets this cell owner.
     *
     * @param owner is the owner of this cell.
     */
    void setOwner(Player owner) {
        this.owner = owner;
    }

    /**
     * Tells if this cell is equal to the given object.
     *
     * @param obj is the object equal to this cell.
     * @return true if this cell is equal to the given object.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!Cell.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final Cell other = (Cell) obj;
        return this.row == other.row && this.column == other.column;
    }

    /**
     * Gets a hash code based on this cell row and column.
     *
     * @return a hash code based on this cell row and column.
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.row, this.column);
    }

}
