package atlg4.ultimate.g47923.exception;

/**
 * Thrown when a player tries to execute a move that does not respect the rules.
 *
 * @author Logan Farci (47923)
 */
public class IllegalMoveException extends UltimateTicTacToeException {

    /**
     * Constructs an instance of <code>IllegalMoveException</code> with the
     * specified detail message.
     *
     * @param id the specified id.
     * @param message the detail message.
     */
    public IllegalMoveException(int id, String message) {
        super(id, message);
    }

}
