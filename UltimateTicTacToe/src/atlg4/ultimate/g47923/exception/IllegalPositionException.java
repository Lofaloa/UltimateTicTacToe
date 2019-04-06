package atlg4.ultimate.g47923.exception;

/**
 * Thrown when a position integrity is not respected.
 *
 * @author Logan Farci (47923)
 */
public class IllegalPositionException extends UltimateTicTacToeException {

    /**
     * Constructs an instance of <code>UltimateTicTacToeException</code> with
     * the specified id and detail message.
     *
     * @param id the id.
     * @param message the detail message.
     */
    public IllegalPositionException(int id, String message) {
        super(id, message);
    }

}
