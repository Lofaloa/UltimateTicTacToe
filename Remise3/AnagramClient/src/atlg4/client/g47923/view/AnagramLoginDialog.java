package atlg4.client.g47923.view;

import atlg4.client.g47923.AnagramClient;
import atlg4.g47923.anagram.players.Credentials;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

/**
 * This dialog is used to log a client in to the Anagram server.
 *
 * @author Logan Farci (47923)
 */
public class AnagramLoginDialog extends Dialog<Credentials> {

    private static final String FXML_PATH = "/fxml/LoginDialogPane.fxml";
    private static final String CHECKED_ICON = "/images/checked.png";
    private static final String UNCHECKED_ICON = "/images/unchecked.png";
    static final String PSEUDONYM_PATTERN = "^(?=.{8,20}$)(?![_.])(?!.*[_.]{2})"
            + "[a-zA-Z0-9]+(?<![_.])$";
    static final String ADDRESS_PATTERN
            = "^(?:[0-9]{1,3}\\.){3}[0-9]{1,3}$"
            + "|"
            + "localhost";
    static final String PORT_PATTERN = "[0-9]{5}";

    @FXML
    private TextField login;

    @FXML
    private ImageView loginValidationIcon;

    @FXML
    private TextField address;

    @FXML
    private ImageView addressValidationIcon;

    @FXML
    private TextField port;

    @FXML
    private ImageView portValidationIcon;

    @FXML
    private ButtonType connect;

    @FXML
    private ButtonType quit;

    private final View view;

    public AnagramLoginDialog(View view) throws IOException {
        this.view = view;
        this.load();
        this.addConnectEventFilter();
        this.addQuitEventFilter();
        this.setResultConverter((ButtonType button) -> {
            Credentials credentials = null;
            if (button == connect) {
                credentials = new Credentials(
                        address.getText(),
                        login.getText(),
                        Integer.parseInt(port.getText())
                );
            }
            return credentials;
        });
    }

    private void load() throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(FXML_PATH));
            loader.setController(this);
            setDialogPane(loader.load());
        } catch (IOException exception) {
            throw new IOException(FXML_PATH + " cannot be loaded!", exception);
        }
    }

    private boolean isValidLogin() {
        String login = this.login.getText();
        Pattern validLogin = Pattern.compile(PSEUDONYM_PATTERN);
        Matcher matcher = validLogin.matcher(login);
        return matcher.matches();
    }

    private boolean isValidAddress() {
        String address = this.address.getText();
        Pattern validAddress = Pattern.compile(ADDRESS_PATTERN);
        Matcher matcher = validAddress.matcher(address);
        return matcher.matches();
    }

    private boolean isValidPort() {
        String port = this.port.getText();
        Pattern validPort = Pattern.compile(PORT_PATTERN);
        Matcher matcher = validPort.matcher(port);
        return matcher.matches();
    }

    private void addConnectEventFilter() {
        Button connectButton = (Button) getDialogPane().lookupButton(connect);
        connectButton.addEventFilter(ActionEvent.ACTION, e -> {
            if (!isValidLogin()) {
                Image img = new Image(UNCHECKED_ICON);
                loginValidationIcon.setImage(img);
                e.consume();
            } else {
                Image img = new Image(CHECKED_ICON);
                loginValidationIcon.setImage(img);
            }

            if (!isValidAddress()) {
                Image img = new Image(UNCHECKED_ICON);
                addressValidationIcon.setImage(img);
                e.consume();
            } else {
                Image img = new Image(CHECKED_ICON);
                addressValidationIcon.setImage(img);
            }

            if (!isValidPort()) {
                Image img = new Image(UNCHECKED_ICON);
                portValidationIcon.setImage(img);
                e.consume();
            } else {
                Image img = new Image(CHECKED_ICON);
                portValidationIcon.setImage(img);
            }
        });
    }

    private void addQuitEventFilter() {
        Button quitButton = (Button) getDialogPane().lookupButton(quit);
        quitButton.addEventFilter(ActionEvent.ACTION, e -> {
            Platform.exit();
            System.exit(0);
        });
    }

}
