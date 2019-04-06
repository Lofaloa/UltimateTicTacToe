package atlg4.ultimate.g47923.exception;

/**
 *
 * @author Logan Farci (47923)
 */
public class UltimateTicTacToeException extends RuntimeException {

    private final int id;

    /**
     * Constructs an instance of <code>UltimateTicTacToeException</code> with
     * the specified id and detail message.
     *
     * @param id the specified id.
     * @param message the detail message.
     */
    public UltimateTicTacToeException(int id, String message) {
        super(message);
        this.id = id;
    }

    /**
     * Constructs an instance of <code>UltimateTicTacToeException</code> with
     * the specified id.
     *
     * @param id the specified id.
     */
    public UltimateTicTacToeException(int id) {
        this.id = id;
    }

    /**
     * Gets this exception id.
     *
     * @return this exception id.
     */
    int getId() {
        return id;
    }

    @Override
    public String getMessage() {
        return id + ": " + super.getMessage();
    }



}
