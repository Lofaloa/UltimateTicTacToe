package atlg4.client.g47923.view;

import atlg4.client.g47923.AnagramClient;
import atlg4.g47923.anagram.message.Message;
import atlg4.g47923.anagram.message.Type;
import java.io.IOException;
import java.util.Objects;
import java.util.Observable;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * This class is used to manage the view of the Anagram client application.
 *
 * @author Logan Farci (47923)
 */
public class AnagramView implements View {

    private static final String TITLE = "Anagramme";
    private static final String INFO_TITLE = "Information";
    private static final String ERROR_TITLE = "Erreur";

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

    private final AnagramClient client;
    private final AnagramLoginBox loginBox;
    private final AnagramMainWindow main;
    private final Stage stage;
    private final Scene scene;

    /**
     * Constructs an instance of the AnagramView with specified view and client.
     *
     * @param stage is the specified stage.
     * @param client is the client to represent.
     * @throws IOException when the loading of a FXML files fails.
     */
    public AnagramView(Stage stage, AnagramClient client) throws IOException {
        this.client = client;
        this.loginBox = new AnagramLoginBox(this, client);
        this.main = new AnagramMainWindow(this, client);
        this.stage = stage;
        this.scene = new Scene(loginBox);
        this.inititialize();
    }

    private void inititialize() {
        stage.setTitle(TITLE);
        stage.setScene(scene);
        client.addObserver(this);
        addOnCloseHandler();
    }

    @Override
    public void show() {
        stage.show();
    }

    @Override
    public void showLoginBox() {
        scene.setRoot(loginBox);
        stage.sizeToScene();
        stage.centerOnScreen();
    }

    @Override
    public void showMainWindow() {
        scene.setRoot(main);
        stage.sizeToScene();
        stage.centerOnScreen();
    }

    @Override
    public void showInformation(String header, String message) {
        getDialog(AlertType.INFORMATION, header, message).show();
    }

    @Override
    public void showError(String header, String message) {
        getDialog(AlertType.ERROR, header, message).show();
    }

    private void addOnCloseHandler() {
        stage.setOnCloseRequest((WindowEvent t) -> {
            Platform.exit();
            System.exit(0);
        });
    }

    @Override
    public void update(Observable o, Object arg) {
        showMessage(arg);
    }

    private void showMessage(Object arg) {
        if (arg != null) {
            Message message = (Message) arg;
            Platform.runLater(() -> {
                if (message.getType() == Type.LOGIN_VALIDATION) {
                    boolean isValidLogin = (boolean) message.getContent();
                    if (isValidLogin) {
                        showMainWindow();
                    } else {
                        loginBox.update(arg);
                    }
                } else {
                    main.update(arg);
                }
            });
        }
    }

}
