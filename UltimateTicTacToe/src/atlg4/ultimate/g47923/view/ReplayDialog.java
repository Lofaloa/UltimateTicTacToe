package atlg4.ultimate.g47923.view;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

/**
 * Dialog asking the user if he/ she wants to replay a new game or quit the
 * game.
 *
 * @author Logan Farci (47923)
 */
public class ReplayDialog extends Dialog<Boolean> {

    private static final String TITLE = "Replay a game";
    private static final String HEADER = "The game is over, do you want to replay?";
    private static final String CONTENT = "Click replay to play a new game or click "
            + "quit to quit the game.";
    private static final String REPLAY_BUTTON = "Replay";
    private static final String QUIT_BUTTON = "Quit";

    private final ButtonType replay;
    private final ButtonType quit;

    public ReplayDialog() {
        this.replay = new ButtonType(REPLAY_BUTTON);
        this.quit = new ButtonType(QUIT_BUTTON);
        setContent();
    }

    private void setContent() {
        setTitle(TITLE);
        setHeaderText(HEADER);
        setContentText(CONTENT);
        getDialogPane().getButtonTypes().addAll(replay, quit);
    }

}
