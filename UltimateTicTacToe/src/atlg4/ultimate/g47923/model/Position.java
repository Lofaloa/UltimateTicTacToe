package atlg4.ultimate.g47923.model;

import atlg4.ultimate.g47923.dto.PositionDTO;
import atlg4.ultimate.g47923.exception.IllegalPositionException;
import java.util.Objects;

/**
 * Represents a position.
 *
 * @author Logan Farci (47923)
 */
class Position {

    static final int MAX_COORDINATE = 2;

    private final int row;
    private final int column;

    /**
     * Constructs a position.
     *
     * @param row is the row of this position.
     * @param column is the column of this position.
     */
    Position(int row, int column) {
        this.row = requireValidCoordinate(row, 1, row + " is not a valid position "
                + "row.");
        this.column = requireValidCoordinate(column, 2, column + " is not a valid "
                + "position column.");
    }

    /**
     * Constructs a copy of the given position.
     *
     * @param other is the position to copy.
     */
    Position(Position other) {
        this(other.getRow(), other.getColumn());
    }

    /**
     * Constructs a default position.
     */
    Position() {
        this(0, 0);
    }

    /**
     * Constructs a position with the specified data transfer object.
     *
     * @param dto is the specified data transfer object.
     */
    Position(PositionDTO dto) {
        this.row = requireValidCoordinate(dto.getRow(), 9, dto.getRow() + " is "
                + "not a valid position row.");
        this.column = requireValidCoordinate(dto.getColumn(), 10, dto.getColumn()
                + " is not a valid position column.");
    }

    /**
     * Gets this position row.
     *
     * @return this position row.
     */
    int getRow() {
        return row;
    }

    /**
     * Gets this position column.
     *
     * @return this position column.
     */
    int getColumn() {
        return column;
    }

    static int requireValidCoordinate(int coo, int id, String msg) {
        if (coo < 0 || MAX_COORDINATE < coo) {
            throw new IllegalPositionException(id, msg);
        }
        return coo;
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
        if (!Position.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final Position other = (Position) obj;
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

    /**
     * Converts this position to a data transfer object;
     *
     * @return a data transfer object.
     */
    PositionDTO toDTO() {
        return new PositionDTO(row, column);
    }

}
