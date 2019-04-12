package atlg4.ultimate.g47923.view;

import javafx.scene.control.TextInputDialog;

/**
 * Asks the user a pseudonym.
 *
 * @author Logan Farci (47923)
 */
public class JoinDialog extends TextInputDialog {

    static final String TITLE = "Join";
    static final String HEADER = "Please provide a pseudonym to join the game";
    static final String CONTENT = "Your pseudonym...";

    JoinDialog() {
        setContent();
    }

    private void setContent() {
        setTitle(TITLE);
        setHeaderText(HEADER);
        setContentText(CONTENT);
    }

}
