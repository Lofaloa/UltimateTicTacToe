package atlg4.ultimate.g47923.exception;

/**
 * Thrown when an error occurred in the persistence layer.
 *
 * @author Logan Farci (47923)
 */
public class DataBaseException extends UltimateTicTacToeException {

    /**
     * Constructs an instance of DataBaseException with the detailed message.
     * 
     * @param message is the detailed message. 
     */
    public DataBaseException(String message) {
        super(message);
    }

}
