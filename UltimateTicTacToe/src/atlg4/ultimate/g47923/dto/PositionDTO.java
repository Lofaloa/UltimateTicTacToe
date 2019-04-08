package atlg4.ultimate.g47923.dto;

/**
 * Represents a position.
 *
 * @author Logan Farci (47923)
 */
public class PositionDTO {

    private final int row;
    private final int column;

    /**
     * Constructs a position data transfer object.
     *
     * @param row is the row of this position.
     * @param column is the column of this position.
     */
    public PositionDTO(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Gets this position row.
     *
     * @return this position row.
     */
    public int getRow() {
        return row;
    }

    /**
     * Gets this position column.
     *
     * @return this position column.
     */
    public int getColumn() {
        return column;
    }

}
