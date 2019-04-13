package atlg4.ultimate.g47923.view;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Tooltip;

/**
 * Asks the user a pseudonym.
 *
 * @author Logan Farci (47923)
 */
public class JoinDialog extends TextInputDialog {

    static final String TITLE = "Join";
    static final String HEADER = "Please provide a pseudonym with to join the game";
    static final String CONTENT = "Your pseudonym...";
    static final String TIP = "Your pseudonym should be composed of \n"
            + "8 to 20 alphanumeric characters.\n";
    static final String PSEUDONYM_PATTERN = "^(?=.{8,20}$)(?![_.])(?!.*[_.]{2})"
            + "[a-zA-Z0-9]+(?<![_.])$";
    static private final int MIN_WIDTH = 300;
    static private final int MIN_HEIGHT = 200;

    private final Tooltip tip;

    JoinDialog() {
        this.tip = new Tooltip();
        setContent();
        setEventFilter();
        getDialogPane().setMinWidth(MIN_WIDTH);
        getDialogPane().setMinHeight(MIN_HEIGHT);
    }

    private void setContent() {
        setTitle(TITLE);
        setHeaderText(HEADER);
        setContentText(CONTENT);
        tip.setText(TIP);
        getEditor().setTooltip(new Tooltip(TIP));
    }

    private boolean isValidPseudonym() {
        String pseudonym = getEditor().getText();
        Pattern validPseudonym = Pattern.compile(PSEUDONYM_PATTERN);
        Matcher matcher = validPseudonym.matcher(pseudonym);
        return matcher.matches();
    }

    private void setEventFilter() {
        Button okButton = (Button) getDialogPane().lookupButton(ButtonType.OK);
        okButton.addEventFilter(ActionEvent.ACTION, e -> {
            if (!isValidPseudonym()) {
                e.consume();
            }
        });
    }

}
