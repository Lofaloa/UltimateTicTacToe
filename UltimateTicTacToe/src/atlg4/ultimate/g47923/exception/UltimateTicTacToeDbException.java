package atlg4.ultimate.g47923.exception;

/**
 * Thrown when an error occurred in the persistence layer.
 *
 * @author Logan Farci (47923)
 */
public class UltimateTicTacToeDbException extends UltimateTicTacToeException {

    public UltimateTicTacToeDbException(int id, String message) {
        super(id, message);
    }

}
