package atlg4.ultimate.g47923.model;

/**
 * Represents a <i>MiniTicTacToe</i> cell.
 *
 * @author Logan Farci (47923)
 */
class Cell extends TicTacToe<Grid> {

    static final int SIZE = 1;

    /**
     * Constructs an empty <i>Cell</i>.
     *
     * @param position is the position of this cell.
     */
    public Cell(Position position) {
        super(position, SIZE);
    }

}
