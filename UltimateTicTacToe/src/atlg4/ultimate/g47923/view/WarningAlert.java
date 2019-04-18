package atlg4.ultimate.g47923.view;

import javafx.scene.control.Alert;

/**
 * Is a general warning.
 *
 * @author Logan Farci (47923)
 */
class WarningAlert extends Alert {

    static private final String TITLE = "Warning";
    static private final int MIN_WIDTH = 300;
    static private final int MIN_HEIGHT = 200;

    private final String header;
    private final String message;

    WarningAlert(String header, String message) {
        super(AlertType.WARNING);
        this.header = header;
        this.message = message;
        getDialogPane().setMinWidth(MIN_WIDTH);
        getDialogPane().setMinHeight(MIN_HEIGHT);
        getDialogPane().getStylesheets().add("/css/UltimateTicTacToeTheme.css");
        setText();
    }

    private void setText() {
        setTitle(TITLE);
        setHeaderText(header);
        setContentText(message);
    }

}
