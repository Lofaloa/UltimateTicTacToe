package atlg4.ultimate.g47923.model;

/**
 * Represents a tic tac toe grid.
 *
 * @author Logan Farci (47923)
 */
interface Grid<ContentType> {

    /**
     * Tells if this grid has an owner.
     * 
     * @return true if this winner has an owner.
     */
    boolean hasOwner();
    
    /**
     * Tells if this grid has a full row filled with the given marker.
     *
     * @param value is the marker of the full row.
     * @return true if this grid has a full row of the given marker.
     */
    boolean hasFullRowOf(ContentType value);

    /**
     * Tells if this grid has a full column filled with the given marker.
     *
     * @param value is the marker of the full column.
     * @return true if this grid has a full column of the given marker.
     */
    boolean hasFullColumnOf(ContentType value);

    /**
     * Tells if this grid has a full diagonal filled with the given marker.
     *
     * @param value is the marker of the full diagonal.
     * @return true if this grid has a full diagonal of the given marker.
     */
    boolean hasFullDiagonalOf(ContentType value);

}
