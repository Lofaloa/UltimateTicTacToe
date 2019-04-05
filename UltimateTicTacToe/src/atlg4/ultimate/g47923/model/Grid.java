package atlg4.ultimate.g47923.model;

/**
 * Represents a tic tac toe grid.
 *
 * @author Logan Farci (47923)
 */
interface Grid<CellType> {

    /**
     * Gets this grid position in its parent grid.
     *
     * @return gets this grid position.
     */
    Position getPosition();

    /**
     * Tells if this grid has an owner.
     *
     * @return true if this winner has an owner.
     */
    boolean hasOwner();

    /**
     * Gets this grid owner.
     *
     * @return this grid owner.
     */
    Player getOwner();

    /**
     * Gets the cell located at the given position in this grid.
     *
     * @param position is the position of the cell to get.
     * @return the call located at the given position.
     */
    CellType getCellAt(Position position);

    /**
     * Tells if this grid has a full row filled with the given marker.
     *
     * @param value is the marker of the full row.
     * @return true if this grid has a full row of the given marker.
     */
    boolean hasFullRowOf(CellType value);

    /**
     * Tells if this grid has a full column filled with the given marker.
     *
     * @param value is the marker of the full column.
     * @return true if this grid has a full column of the given marker.
     */
    boolean hasFullColumnOf(CellType value);

    /**
     * Tells if this grid has a full diagonal filled with the given marker.
     *
     * @param value is the marker of the full diagonal.
     * @return true if this grid has a full diagonal of the given marker.
     */
    boolean hasFullDiagonalOf(CellType value);

}
