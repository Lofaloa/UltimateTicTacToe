package atlg4.client.g47923.view;

import atlg4.client.g47923.AnagramClient;
import atlg4.g47923.anagram.players.Credentials;

/**
 * The interface is used to manage the view of an application composed of one
 * main window and needing to display information or error dialogs.
 *
 * @author Logan Farci (47923)
 */
public interface View {

    void setClient(AnagramClient client);

    /**
     * Ask the connection credentials of the user.
     *
     * @return the credentials of the user.
     */
    Credentials askCredentials();

    /**
     * Shows the main window of the application.
     */
    void showMainWindow();

    /**
     * Shows an information dialog to the user.
     *
     * @param header is the header of the dialog.
     * @param message is the message of the dialog.
     */
    void showInformation(String header, String message);

    /**
     * Shows an error dialog to the user.
     *
     * @param header is the header of the dialog.
     * @param message is the message of the dialog.
     */
    void showError(String header, String message);

}
