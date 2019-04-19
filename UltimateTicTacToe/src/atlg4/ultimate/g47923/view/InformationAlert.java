package atlg4.ultimate.g47923.view;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Is used to inform the user.
 *
 * @author Logan Farci (47923)
 */
class InformationAlert extends Alert {

    static private final String TITLE = "Information";
    static private final int MIN_WIDTH = 300;
    static private final int MIN_HEIGHT = 200;

    private final String header;
    private final String message;

    InformationAlert(String header, String message) {
        super(AlertType.INFORMATION);
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
