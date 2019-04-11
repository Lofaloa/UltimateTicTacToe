package atlg4.ultimate.g47923.view;

import javafx.scene.control.Alert;

/**
 * Confirmation alert asking if the user is sure about its current action.
 * 
 * @author Logan Farci (47923)
 */
class ConfirmationAlert extends Alert {
 
    static final String TITLE = "Confirmation";
    static final String HEADER = "Are you sure?";
    static private final int MIN_WIDTH = 300;
    static private final int MIN_HEIGHT = 200;
    
    private final String message;

    ConfirmationAlert(String message) {
        super(AlertType.CONFIRMATION);
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
