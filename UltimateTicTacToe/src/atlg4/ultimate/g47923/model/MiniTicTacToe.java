package atlg4.ultimate.g47923.model;

/**
 * Represents a <i>MiniTicTacToe</i>.
 *
 * @author Logan Farci (47923)
 */
class MiniTicTacToe extends TicTacToe<Grid> {

    private static final int SIZE = 3;

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
    final void initialize() {
        for (int row = 0; row < SIZE; row++) {
            for (int column = 0; column < SIZE; column++) {
                cells.add(new Cell(new Position(row, column)));
            }
        }
    }

}
