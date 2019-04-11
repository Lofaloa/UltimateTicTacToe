package atlg4.ultimate.g47923.view;

import atlg4.ultimate.g47923.controller.RestartStartAGameHandler;
import atlg4.ultimate.g47923.model.Game;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

/**
 * Dialog asking the user if he/ she wants to replay a new game or quit the
 * game.
 *
 * @author Logan Farci (47923)
 */
public class ReplayDialog extends Dialog<ButtonType> {

    private static final String TITLE = "Replay a game";
    private static final String HEADER = "The game is over, do you want to replay?";
    private static final String CONTENT = "Click replay to play a new game or click "
            + "quit to quit the game.";
    private static final String REPLAY_BUTTON = "Replay";
    private static final String QUIT_BUTTON = "Quit";

    private final Game game;
    private final GameWindow view;
    private final ButtonType replay;
    private final ButtonType quit;

    public ReplayDialog(Game game, GameWindow view) {
        this.game = game;
        this.view = view;
        this.replay = new ButtonType(REPLAY_BUTTON);
        this.quit = new ButtonType(QUIT_BUTTON);
        setContent();
        setEventFilter();
    }

    private void setContent() {
        setTitle(TITLE);
        setHeaderText(HEADER);
        setContentText(CONTENT);
        getDialogPane().getButtonTypes().addAll(replay, quit);
    }

    private void setEventFilter() {
        Button replayButton = (Button) getDialogPane().lookupButton(this.replay);
        replayButton.setOnAction(new RestartStartAGameHandler(game, view));
        Button quitButton = (Button) getDialogPane().lookupButton(this.quit);
        quitButton.addEventFilter(ActionEvent.ACTION, event -> {
            System.exit(0);
        });
    }

}
