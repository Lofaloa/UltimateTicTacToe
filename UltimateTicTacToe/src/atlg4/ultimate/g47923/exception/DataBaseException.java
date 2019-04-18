package atlg4.ultimate.g47923.exception;

/**
 * Thrown when an error occurred in the persistence layer.
 *
 * @author Logan Farci (47923)
 */
public class DataBaseException extends UltimateTicTacToeException {

    public DataBaseException(String message) {
        super(0 , message);
    }

}
