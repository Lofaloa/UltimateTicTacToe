package atlg4.ultimate.g47923.model;

import atlg4.ultimate.g47923.exception.GridException;

/**
 * Represents a cell. A cell is a grid of size 1 that does not have child cells.
 *
 * @author Logan Farci (47923)
 */
class Cell extends Grid<Grid> {

    static final int SIZE = 1;

    Cell(Position position) {
        super(position, SIZE);
    }

    @Override
    void initialize() {
        throw new GridException("A cell is not initializable as it has no"
                + "sub grids.");
    }

    @Override
    void setOwner(Player owner) {
        if (hasOwner()) {
            throw new GridException("trying to override this cell owner.");
        }
        this.owner = owner;
    }

}
