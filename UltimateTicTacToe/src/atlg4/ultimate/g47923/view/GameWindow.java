package atlg4.ultimate.g47923.view;

import atlg4.composant.g47923.MyTicTacToe;
import atlg4.ultimate.g47923.controller.RestartStartAGameHandler;
import atlg4.ultimate.g47923.controller.WithdrawalHandler;
import atlg4.ultimate.g47923.dto.MoveDTO;
import atlg4.ultimate.g47923.dto.PositionDTO;
import atlg4.ultimate.g47923.exception.UltimateTicTacToeException;
import atlg4.ultimate.g47923.model.Game;
import atlg4.ultimate.g47923.model.Marker;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import static java.util.Objects.requireNonNull;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.MenuItem;
import static javafx.scene.layout.GridPane.getRowIndex;
import static javafx.scene.layout.GridPane.getColumnIndex;

/**
 * Is the game window.
 *
 * @author Logan Farci (47923)
 */
public class GameWindow extends VBox implements Initializable, Observer {

    private final String CROSS_IMG_PATH = "/images/cross.png";
    private final String CIRCLE_IMG_PATH = "/images/circle.png";
    private static final String FXML_PATH = "/fxml/GameWindow.fxml";

    private final Game game;
    private final View view;

    @FXML
    private GridPane board;

    @FXML
    private MenuItem withdraw;

    @FXML
    private MenuItem newgame;

    @FXML
    private MenuItem quit;

    /**
     * Constructs an instance of this GameWindow with the specified game to 
     * represent and the managing view.
     *
     * @param game is the specified game.
     * @param view is the managing view.
     */
    GameWindow(Game game, View view) throws IOException {
        this.game = requireNonNull(game, "Constructing a GameWindow with a null "
                + "game.");
        this.view = requireNonNull(view, "Constructing a GameWindow with a null "
                + "view.");
        load();
    }

    private void load() throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(FXML_PATH));
            loader.setRoot(this);
            loader.setController(this);
            loader.load();
        } catch (IOException exception) {
            throw new IOException(FXML_PATH + " cannot be loaded!", exception);
        }
    }

    /**
     * Asks a confirmation to the user with the specified message.
     * 
     * @param message is the specified message.
     * @return true if the player confirmed.
     */
    public boolean askConfirmation(String message) {
        Alert confirmation = new ConfirmationAlert(message);
        Optional<ButtonType> result = confirmation.showAndWait();
        return result.get() == ButtonType.OK;
    }

    /**
     * Clears the content of the board.
     */
    public void clearBoard() {
        for (Node node : board.getChildren()) {
            MyTicTacToe tictactoe = (MyTicTacToe) node;
            tictactoe.initialize(null);
            tictactoe.displayWinner(null);
            tictactoe.getStyleClass().clear();
        }
    }
    
    private MyTicTacToe getTicTacToeAt(int row, int column) {
        MyTicTacToe target = null;
        for (Node node : board.getChildren()) {
            MyTicTacToe t = (MyTicTacToe) node;
            if (getRowIndex(node) == row && getColumnIndex(node) == column) {
                target = t;
            }
        }
        return target;
    }

    private void addHandlerAt(MyTicTacToe t, final int row, final int column) {
        t.addEventHandlerAt(row, column, event -> {
            try {
                PositionDTO m = new PositionDTO(getRowIndex(t), getColumnIndex(t));
                PositionDTO c = new PositionDTO(row, column);
                game.select(m, c);
                game.play();
                game.nextPlayer();
            } catch (UltimateTicTacToeException e) {
                showIllegalMoveAlert(e.getMessage());
            }
        });
    }

    private void addHandlersTo(MyTicTacToe t) {
        for (int row = 0; row < MyTicTacToe.SIZE; row++) {
            for (int column = 0; column < MyTicTacToe.SIZE; column++) {
                addHandlerAt(t, row, column);
            }
        }
    }

    private void addBoardHandlers() {
        for (Node child : board.getChildren()) {
            MyTicTacToe tictactoe = (MyTicTacToe) child;
            addHandlersTo(tictactoe);
        }
    }

    private void addMenuHandlers() {
        withdraw.setOnAction(new WithdrawalHandler(game, this));
        newgame.setOnAction(new RestartStartAGameHandler(game, this));
        quit.setOnAction((ActionEvent e) -> {
            if (askConfirmation("You are about to quit the game, all the "
                    + "advancement will be lost!")) {
                System.exit(0);
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addMenuHandlers();
        addBoardHandlers();
    }

    private void showIllegalMoveAlert(String msg) {
        Alert warning = new IllegalMoveAlert(msg);
        warning.show();
    }

    public void showEnd() {
        Dialog replay = new ReplayDialog(game, this);
        replay.show();
    }

    private void updateBoard() {
        // problème si aucun mouvement n'a eu lieu!
        MoveDTO move = game.getLastMove();
        PositionDTO mini = move.getMiniTicTacToePosition();
        PositionDTO cell = move.getCellPosition();
        Marker marker = move.getAuthor().getMarker();
        MyTicTacToe tictactoe = getTicTacToeAt(mini.getRow(), mini.getColumn());
        Image img = new Image(marker == Marker.X ? CROSS_IMG_PATH : CIRCLE_IMG_PATH);
        tictactoe.setMarker(cell.getRow(), cell.getColumn(), img);
        if (move.isWinning()) {
            tictactoe.displayWinner(img);
        }
        if (game.isOver()) {
            showEnd();
        }
        updatePlayable(cell, marker == Marker.X ? Marker.O : Marker.X);
    }

    private void setTicTacToeStyleClass(String styleClass) {
        for (int row = 0; row < MyTicTacToe.SIZE; row++) {
            for (int column = 0; column < MyTicTacToe.SIZE; column++) {
                getTicTacToeAt(row, column).getStyleClass().clear();
                getTicTacToeAt(row, column).getStyleClass().add(styleClass);
            }
        }
    }

    private void updatePlayable(PositionDTO playable, Marker nextPlayer) {
        MyTicTacToe t = getTicTacToeAt(playable.getRow(), playable.getColumn());
        setTicTacToeStyleClass("tictactoe");
        t.getStyleClass().clear();
        t.getStyleClass().add("playableBy" + nextPlayer);
    }

    @Override
    public void update(Observable o, Object o1) {
        updateBoard();
    }

}
