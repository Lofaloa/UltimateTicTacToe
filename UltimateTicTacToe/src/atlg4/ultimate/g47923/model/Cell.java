package atlg4.ultimate.g47923.model;

import atlg4.ultimate.g47923.exception.GridException;

/**
 * Represents a cell. A cell is a grid of size 1 that does not have child cells.
 *
 * @author Logan Farci (47923)
 */
class Cell extends Grid<Grid> {

    static final int SIZE = 1;

    /**
     * Constructs an instance of an empty <code>Cell</code> with the specified
     * position.
     *
     * @param position the specified position.
     */
    public Cell(Position position) {
        super(position, SIZE);
    }

    @Override
    void initialize() {
        throw new GridException(7, "A cell is not initializable as it has no"
                + "sub grids.");
    }

    /**
     * Sets this cell owner.
     *
     * @param owner is this cell owner.
     */
    void setOwner(Player owner) {
        if (hasOwner()) {
            throw new GridException(6, "trying to override this cell owner.");
        }
        this.owner = owner;
    }

}
