package atlg4.ultimate.g47923.model;

import atlg4.ultimate.g47923.exception.GridException;
import static java.util.Objects.requireNonNull;

/**
 * Represents a <i>MiniTicTacToe</i>.
 *
 * @author Logan Farci (47923)
 */
class MiniTicTacToe extends Grid<Cell> {

    static final int SIZE = 3;

    /**
     * Constructs an empty <i>MiniTicTacToe</i>.
     */
    MiniTicTacToe(Position position) {
        super(position, SIZE);
        initialize();
    }

    /**
     * Initializes this <i>MiniTicTacToe</i> as empty.
     */
    @Override
    final void initialize() {
        for (int row = 0; row < SIZE; row++) {
            for (int column = 0; column < SIZE; column++) {
                cells.add(new Cell(new Position(row, column)));
            }
        }
    }

    /**
     * Sets the given owner of the <code>Cell</code> at the given position in
     * this <code>MiniTicTacToe</code>. If the given owner owns a row, a column
     * or a diagonal in this <code>MiniTicTacToe</code>, this
     * <code>MiniTicTacToe</code> is set to the given owner.
     *
     * @param owner is the owner of the cell to set.
     * @param position is the position of the cell to set.
     * @throws NullPointerException if one of the arguments is null.
     * @throws GridException if the cell to set the owner for has not been
     * found.
     */
    void setOwnerAt(Player owner, Position position) {
        requireNonNull(owner, "Trying to set a null player in a MiniTicTacToe.");
        requireNonNull(position, "Trying to set a player in a MiniTicTacToe at a"
                + " null position");
        ((Cell) getCellAt(position)).setOwner(owner);
        if (isOwnedBy(owner)) {
            this.owner = owner;
        }
    }

}
