package atlg4.ultimate.g47923.model;

import atlg4.ultimate.g47923.exception.GridException;
import static java.util.Objects.requireNonNull;

/**
 * Represents an 3 by 3 ultimate tic tac toe.
 *
 * @author Logan Farci (47923)
 */
class UltimateTicTacToe extends Grid<MiniTicTacToe> {

    static final int SIZE = 3;

    /**
     * Constructs an empty ultimate tic tac toe.
     */
    UltimateTicTacToe() {
        super(new Position(), SIZE);
        initialize();
    }

    /**
     * Initializes this <i>UlimateTicTacToe</i> as empty.
     */
    @Override
    final void initialize() {
        cells.clear();
        for (int row = 0; row < SIZE; row++) {
            for (int column = 0; column < SIZE; column++) {
                cells.add(new MiniTicTacToe(new Position(row, column)));
            }
        }
    }

    /**
     * Sets the given owner in the specified <code>Cell</code> of the specified
     * <code>MiniTicTacToe</code> in this <code>UltimateTicTacToe</code>.
     *
     * @param owner is the given owner.
     * @param miniTicTacToe is the specified <code>MiniTicTacToe</code>.
     * @param cell is the specified <code>Cell</code>.
     * @throws NullPointerException if one of the arguments is null.
     * @throws GridException if the <code>MiniTicTacToe</code> to set the owner
     * for has already an owner or is full.
     */
    void setOwnerAt(Player owner, Position miniTicTacToe, Position cell) {
        requireNonNull(owner, "Setting a null player in an UltimateTicTacToe.");
        requireNonNull(miniTicTacToe, "Setting a player in an UltimateTicTacToe "
                + "at a null MiniTicTacToe position");
        requireNonNull(cell, "Setting to set a player in an "
                + "UltimateTicTacToe at a null Cell position");
        MiniTicTacToe mini = (MiniTicTacToe) getCellAt(miniTicTacToe);
        if (mini.hasOwner()) {
            throw new GridException(12, "This MiniTicTacToe is already owned!");
        } else if (mini.isFull()) {
            throw new GridException(12, "This MiniTicTacToe is full!");
        } else {
            mini.setOwnerAt(owner, cell);
        }
        if (isOwnedBy(owner)) {
            this.owner = owner;
        }
    }

}
