package atlg4.ultimate.g47923.model;

import java.util.ArrayList;
import java.util.List;
import static java.util.Objects.requireNonNull;

/**
 * Represents a tic tac toe.
 *
 * @author Logan Farci (47923)
 * @param <CellType> is the type of this tic tac toe grid content.
 */
class TicTacToe<CellType extends Grid> implements Grid {

    protected final List<CellType> cells;
    protected final Position position;
    protected final Player owner;
    protected final int size;

    /**
     * Constructs a tic tac toe grid of the given size. This tic tac toe is
     * constructed without an owner.
     *
     * @param size is the size of this tic tac toe grid.
     */
    public TicTacToe(Position position, int size) {
        this.cells = new ArrayList<>();
        this.position = new Position(position);
        this.owner = null;
        this.size = size;
    }

    @Override
    public Position getPosition() {
        return new Position(position);
    }

    @Override
    public boolean hasOwner() {
        return owner != null;
    }

    @Override
    public Player getOwner() {
        return owner;
    }

    @Override
    public final Object getCellAt(Position position) {
        requireNonNull(position, "Trying to get a cell but the given "
                + "position is null.");
        Grid target = null;
        for (Grid cell : cells) {
            if (position == cell.getPosition()) {
                target = cell;
            }
        }
        return target;
    }

    @Override
    public final boolean hasFullRowOf(Object value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public final boolean hasFullColumnOf(Object value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public final boolean hasFullDiagonalOf(Object value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
