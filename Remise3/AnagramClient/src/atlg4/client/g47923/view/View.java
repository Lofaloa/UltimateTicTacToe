package atlg4.client.g47923.view;

import atlg4.client.g47923.AnagramClient;

/**
 * The interface is used to manage the view of an application composed of one
 * main window and needing to display information or error dialogs.
 *
 * @author Logan Farci (47923)
 */
public interface View {

    void setClient(AnagramClient client);
    
    /**
     * Shows the login dialog. The dialog is used to connect the client to the
     * server with the data specified by the user.
     * 
     * On success a new client is constructed and connect to the server.
     */
    AnagramClient showAndWaitLoginDialog();

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
