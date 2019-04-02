package atlg4.ultimate.g47923.model;

import atlg4.ultimate.g47923.exception.CellException;
import static atlg4.ultimate.g47923.model.Cell.requireValidCoordinate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a <i>MiniTicTacToe</i>.
 *
 * @author Logan Farci (47923)
 */
class MiniTicTacToe {

    private static final int SIZE = 3;

    private List<Cell> cells;

    /**
     * Constructs an empty <i>MiniTicTacToe</i>.
     */
    MiniTicTacToe() {
        cells = new ArrayList<>();
        inititialze();
    }

    /**
     * Initializes this <i>MiniTicTacToe</i> as empty.
     */
    final void inititialze() {
        for (int row = 0; row < SIZE; row++) {
            for (int column = 0; column < SIZE; column++) {
                cells.add(new Cell(row, column));
            }
        }
    }

    /**
     * Gets the cell located at the given coordinates in this
     * <i>MiniTicTacToe</i>.
     *
     * @param row is the row of the cell to get.
     * @param column is the column of the column to get.
     * @return cell located at the given coordinates in this
     * <i>MiniTicTacToe</i>.
     */
    Cell getCellAt(int row, int column) {
        Cell.requireValidCoordinate(row, row + " is not a valid row.");
        Cell.requireValidCoordinate(column, column + " is not a valid column.");
        Cell target = null;
        for (Cell cell : cells) {
            if (row == cell.getRow() && column == cell.getColumn()) {
                target = cell;
            }
        }
        return target;
    }

    /**
     * Sets the given owner at the given position in this <i>MiniTicTacToe</i>.
     *
     * @param row is the row of the owner.
     * @param column is the column of the owner.
     */
    void setOwnerAt(Player owner, int row, int column) {
        Cell cell = getCellAt(row, column);
        Cell.requireValidCoordinate(row, row + " is not a valid row.");
        Cell.requireValidCoordinate(column, column + " is not a valid column.");
        if (cell.hasOwner()) {
            throw new CellException("Trying to override a cell owner.");
        }
        cell.setOwner(owner);
    }

}
