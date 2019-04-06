package atlg4.ultimate.g47923.model;

/**
 * Represents an 3 by 3 ultimate tic tac toe.
 *
 * @author Logan Farci (47923)
 */
public class UltimateTicTacToe extends Grid<MiniTicTacToe> {

    private static final int SIZE = 3;

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
        for (int row = 0; row < SIZE; row++) {
            for (int column = 0; column < SIZE; column++) {
                cells.add(new MiniTicTacToe(new Position(row, column)));
            }
        }
    }

}
