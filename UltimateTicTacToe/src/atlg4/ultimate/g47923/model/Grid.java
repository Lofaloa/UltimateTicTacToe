package atlg4.ultimate.g47923.model;

import atlg4.ultimate.g47923.exception.GridException;
import java.util.ArrayList;
import java.util.List;
import static java.util.Objects.requireNonNull;
import static atlg4.ultimate.g47923.model.Position.requireValidCoordinate;
import java.util.Collections;

/**
 * Represents a tic tac toe.
 *
 * @author Logan Farci (47923)
 * @param <CellType> is the type of this tic tac toe grid cells.
 */
abstract class Grid<CellType extends Grid> {

    /**
     * Are the cells contained in this grid.
     */
    protected final List<CellType> cells;

    /**
     * Is the position of this grid in its parent grid.
     */
    protected final Position position;

    /**
     * Is this grid owner. A grid is owned by a player whenever a full row, a
     * full column or a full diagonal is owned by the same player.
     */
    protected Player owner;

    /**
     * Is this grid size.
     */
    protected final int size;

    /**
     * Constructs a tic tac toe grid of the given size. This tic tac toe is
     * constructed without an owner.
     *
     * @param size is the size of this tic tac toe grid.
     */
    Grid(Position position, int size) {
        this.cells = new ArrayList<>();
        this.position = new Position(position);
        this.owner = null;
        this.size = requireValidSize(size);
    }

    /**
     * Makes sure the given size is valid. A valid size is positive.
     *
     * @param size the given size.
     * @return the given size if it is valid.
     * @throws GridException is the given size is negative.
     */
    static int requireValidSize(int size) {
        if (size < 0) {
            throw new GridException(5, size + " is not valid grid size, it "
                    + "should be positive.");
        }
        return size;
    }

    /**
     * Initializes this grid cells.
     */
    abstract void initialize();

    /**
     * Gets this grid position in its parent.
     *
     * @return this grid position in its parent.
     */
    Position getPosition() {
        return new Position(position);
    }

    /**
     * Tells if this grid has an owner.
     *
     * @return this grid owner.
     */
    boolean hasOwner() {
        return owner != null;
    }

    /**
     * Tells if this grid is full. A grid is full when all its cells are full.
     * If this grid has no cells, it is full if it has an owner.
     *
     * @returntrue if this grid is full.
     */
    boolean isFull() {
        return cells.isEmpty() ? hasOwner()
                : cells.stream().allMatch(cell -> cell.isFull());
    }
    
    boolean isEmpty() {
        return cells.isEmpty() ? !hasOwner()
                : cells.stream().allMatch(cell -> cell.isEmpty());
    }

    /**
     * Tells if this grid is playable. A grid is playable when it is not owned
     * and is not full.
     *
     * @return true if this grid is playable.
     */
    boolean isPlayable() {
        return !isFull() && !hasOwner();
    }

    /**
     * Gets this grid owner.
     *
     * @return this grid owner.
     */
    Player getOwner() {
        return owner;
    }

    /**
     * Gets this grid cells.
     *
     * @return this grid cells.
     */
    List<CellType> getCells() {
        return Collections.unmodifiableList(cells);
    }

    /**
     * Sets this grid owner. /!\ This method should only be used in a unit test.
     *
     * @param owner the owner of this grid.
     */
    void setOwner(Player owner) {
        this.owner = owner;
    }

    /**
     * Fills this grid. /!\ This method should only be used in a unit test /!\.
     */
    void fillWith(Player owner) {
        if (cells.isEmpty()) {
            setOwner(owner);
        } else {
            cells.forEach(cell -> cell.fillWith(owner));
        }
     }

    /**
     * Gets the cell located at the given position in this grid.
     *
     * @param position is the position of the cell to get.
     * @return the cell located at the given position in this grid.
     * @throws NullPointerException is the given position is null.
     */
    final Grid getCellAt(Position position) {
        requireNonNull(position, "Trying to get a cell but the given "
                + "position is null.");
        Grid target = null;
        for (Grid cell : cells) {
            if (position.equals(cell.getPosition())) {
                target = cell;
            }
        }
        return target;
    }

    private boolean isARowOf(int row, Player owner) {
        requireNonNull(owner, "Impossible to tell if row " + row + " is a row of "
                + "null.");
        requireValidCoordinate(row, 3, row + " is not a valid row.");
        return cells.isEmpty() ? false : cells.stream()
                .filter(cell -> row == cell.getPosition().getRow())
                .allMatch(cell -> cell.getOwner() == owner);
    }

    private boolean isAColumnOf(int column, Player owner) {
        requireNonNull(owner, "Impossible to tell if column " + column + " is a "
                + "column of null.");
        requireValidCoordinate(column, 4, column + " is not a valid column.");
        return cells.isEmpty() ? false : cells.stream()
                .filter(cell -> column == cell.getPosition().getColumn())
                .allMatch(cell -> cell.getOwner() == owner);
    }

    private boolean isOnDescendingDiagonal(Grid cell) {
        requireNonNull(cell, "Impossible to tell if null is on the descending "
                + "diagonal.");
        return cell.getPosition().getRow() == cell.getPosition().getColumn();
    }

    private boolean hasDescendingDiagonalOf(Player owner) {
        requireNonNull(owner, "Impossible to tell if the descending diagonal"
                + " is a diagonal of null.");
        return cells.isEmpty() ? false : cells.stream()
                .filter(cell -> isOnDescendingDiagonal(cell))
                .allMatch(cell -> cell.getOwner() == owner);
    }

    private boolean isOnAscendingDiagonal(Grid cell) {
        requireNonNull(cell, "Impossible to tell if null is on the ascending "
                + "diagonal.");
        int row = cell.getPosition().getRow();
        int col = cell.getPosition().getColumn();
        return row == size - 1 - col && col == size - 1 - row;
    }

    private boolean hasAscendingDiagonalOf(Player owner) {
        requireNonNull(owner, "Impossible to tell if the ascending diagonal"
                + " is a diagonal of null.");
        return cells.isEmpty() ? false : cells.stream()
                .filter(cell -> isOnAscendingDiagonal(cell))
                .allMatch(cell -> cell.getOwner() == owner);
    }

    /**
     * Tells if this grid has a full row owned by the given owner.
     *
     * @param owner is the owner of a row.
     * @return true if this grid has a full row owned by the given owner.
     * @throws NullPointerException is the owner is null.
     */
    final boolean hasFullRowOwnedBy(Player owner) {
        requireNonNull(owner, "Impossible to tell if this grid has a full row "
                + "owned by null.");
        int row = 0;
        boolean hasFullRowOf = false;
        while (row < size && !hasFullRowOf) {
            hasFullRowOf = isARowOf(row, owner);
            row++;
        }
        return hasFullRowOf;
    }

    /**
     * Tells if this grid has a full column owned by the given owner.
     *
     * @param owner is the owner of a column.
     * @return true if this grid has a full column owned by the given owner.
     * @throws NullPointerException is the owner is null.
     */
    final boolean hasFullColumnOwnedBy(Player owner) {
        requireNonNull(owner, "Impossible to tell if this grid has a full column "
                + "owned by null.");
        int column = 0;
        boolean hasFullColumnOf = false;
        while (column < size && !hasFullColumnOf) {
            hasFullColumnOf = isAColumnOf(column, owner);
            column++;
        }
        return hasFullColumnOf;
    }

    /**
     * Tells if this grid has a full diagonal owned by the given owner.
     *
     * @param owner is the owner of a diagonal.
     * @return true if this grid has a full diagonal owned by the given owner.
     * @throws NullPointerException is the owner is null.
     */
    final boolean hasFullDiagonalOwnedBy(Player owner) {
        requireNonNull(owner, "Impossible to tell if this grid has a full "
                + "diagonal owned by null.");
        return hasAscendingDiagonalOf(owner) || hasDescendingDiagonalOf(owner);
    }

    /**
     * Tells if this grid is owned by the given player.
     *
     * @param owner is the given player.
     * @return true if this grid is owned by the given player.
     */
    final boolean isOwnedBy(Player owner) {
        return hasFullRowOwnedBy(owner)
                || hasFullColumnOwnedBy(owner)
                || hasFullDiagonalOwnedBy(owner);
    }

}
