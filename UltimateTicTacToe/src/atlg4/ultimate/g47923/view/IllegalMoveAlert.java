package atlg4.ultimate.g47923.view;

import javafx.scene.control.Alert;

/**
 * Alert that tells the user that the user is trying to make an illegal move.
 * The cause is explained and the solution to the issue exposed.
 *
 * @author Logan Farci (47923)
 */
public class IllegalMoveAlert extends Alert {

    static private final String TITLE = "Illegal move";
    static private final String HEADER = "You made an illegal move!";
    static private final int MIN_WIDTH = 300;
    static private final int MIN_HEIGHT = 200;

    private final String message;

    public IllegalMoveAlert(String message) {
        super(AlertType.WARNING);
        this.message = message;
        getDialogPane().setMinWidth(MIN_WIDTH);
        getDialogPane().setMinHeight(MIN_HEIGHT);
        setText();
    }

    private void setText() {
        setTitle(TITLE);
        setHeaderText(HEADER);
        setContentText(message);
    }

}
