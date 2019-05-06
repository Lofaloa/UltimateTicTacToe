package atlg4.client.g47923.view;

import atlg4.client.g47923.AnagramClient;
import java.io.IOException;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 * This class is used to manage the view of the Anagram client application.
 *
 * @author Logan Farci (47923)
 */
public class AnagramView implements View {

    private static final String TITLE = "Anagramme";
    private static final String INFO_TITLE = "Information";
    private static final String ERROR_TITLE = "Erreur";
    private static final int MIN_WIDTH = 500;
    private static final int MIN_HEIGHT = 300;

    private static void setTitle(Dialog dialog, AlertType type) {
        if (null == dialog || null == type) {
            throw new IllegalArgumentException("No given dialog or type.");
        } else {
            switch (type) {
                case ERROR:
                    dialog.setTitle(ERROR_TITLE);
                    break;
                case INFORMATION:
                    dialog.setTitle(INFO_TITLE);
                    break;
                default:
                    throw new IllegalArgumentException(type + " is not a valid type.");
            }
        }
    }

    private static Dialog getDialog(AlertType type, String header, String message) {
        Dialog dialog = new Alert(type);
        setTitle(dialog, type);
        dialog.setHeaderText(header);
        dialog.setContentText(message);
        dialog.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        return dialog;
    }

    private final AnagramMainWindow main;
    private final Stage stage;
    private final Scene scene;

    /**
     * Constructs an instance of the AnagramView with specified view and client.
     *
     * @param stage is the specified stage.
     * @param client is the specified client.
     * @throws IOException when the loading of a FXML files fails.
     */
    public AnagramView(Stage stage, AnagramClient client) throws IOException {
        this.main = new AnagramMainWindow(this, client);
        this.stage = stage;
        this.scene = new Scene(main);
        this.inititialize();
    }

    private void inititialize() {
        stage.setTitle(TITLE);
        stage.setMinWidth(MIN_WIDTH);
        stage.setMinHeight(MIN_HEIGHT);
        stage.setScene(scene);
    }

    @Override
    public void showMainWindow() {
        stage.show();
    }

    @Override
    public void showInformation(String header, String message) {
        getDialog(AlertType.INFORMATION, header, message).show();
    }

    @Override
    public void showError(String header, String message) {
        getDialog(AlertType.ERROR, header, message).show();
    }

}
