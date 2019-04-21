package atlg4.ultimate.g47923.view;

import java.io.PrintWriter;
import java.io.StringWriter;
import javafx.scene.control.Alert;
import static java.util.Objects.requireNonNull;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

/**
 * Error alert that displays its exception stack trace in the dialog details.
 *
 * @author Logan Farci (47923)
 */
public class ErrorAlert extends Alert {

    private static final String TITLE = "Error";
    private static final String HEADER = "An error occured and could not be "
            + "taken care of...";
    private static final int MIN_WIDTH = 300;
    private static final int MIN_HEIGHT = 400;

    private final Exception exception;

    /**
     * Constructs an instance of ErrorAlert with the specified exception.
     *
     * @param exception is the specified exception.
     */
    public ErrorAlert(Exception exception) {
        super(AlertType.ERROR);
        this.exception = requireNonNull(exception, "Constructing an ErrorAlert "
                + "with null exception.");
        setText();
        setStackTrace();
        Stage stage = (Stage) getDialogPane().getScene().getWindow();
        stage.setAlwaysOnTop(true);
        stage.setMinWidth(MIN_WIDTH);
        stage.setMinHeight(MIN_HEIGHT);
    }

    private void setText() {
        setTitle(TITLE);
        setHeaderText(HEADER);
        setContentText(exception.getMessage());
    }

    private void setStackTrace() {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        exception.printStackTrace(pw);
        String exceptionText = sw.toString();

        Label label = new Label("The following exception caused an error...");

        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

        getDialogPane().setExpandableContent(expContent);
    }

}
