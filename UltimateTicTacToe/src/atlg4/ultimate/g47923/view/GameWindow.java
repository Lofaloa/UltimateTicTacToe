package atlg4.ultimate.g47923.view;

import atlg4.composant.g47923.MyTicTacToe;
import atlg4.ultimate.g47923.controller.GameWindowController;
import atlg4.ultimate.g47923.dto.MoveDTO;
import atlg4.ultimate.g47923.dto.PositionDTO;
import atlg4.ultimate.g47923.model.Game;
import atlg4.ultimate.g47923.model.Marker;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import static java.util.Objects.requireNonNull;
import javafx.scene.control.Label;
import static javafx.scene.layout.GridPane.getRowIndex;
import static javafx.scene.layout.GridPane.getColumnIndex;

/**
 * Is the main window of the game. The game is played in this window.
 *
 * @author Logan Farci (47923)
 */
public final class GameWindow extends VBox implements Observer {

    private static final String CROSS_IMG_PATH = "/images/cross.png";
    private static final String CIRCLE_IMG_PATH = "/images/circle.png";
    private static final String FXML_PATH = "/fxml/GameWindow.fxml";

    private final Game game;
    private final View view;

    private final GridPane board;

    /**
     * Constructs an instance of this GameWindow with the specified game to
     * represent and the managing view.
     *
     * @param game is the specified game.
     * @param view is the specified view.
     * @throws IOException if this window FXML file cannot be loaded.
     */
    GameWindow(Game game, View view) throws IOException {
        this.game = requireNonNull(game, "Constructing a GameWindow with a null "
                + "game.");
        this.view = requireNonNull(view, "Constructing a GameWindow with a null "
                + "view.");
        load();
        this.board = requireNonNull(getBoard(), "No board has been found");
    }

    private void load() throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(FXML_PATH));
            loader.setRoot(this);
            loader.setController(new GameWindowController(game, view));
            loader.load();
        } catch (IOException exception) {
            throw new IOException(FXML_PATH + " cannot be loaded!", exception);
        }
    }

    private GridPane getBoard() {
        return (GridPane) lookup("#board");
    }

    private Label getPlayerLabel(Marker marker) {
        if (marker == Marker.X) {
            return (Label) lookup("#playerLabelX");
        } else {
            return (Label) lookup("#playerLabelO");
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

    private void showLastMove() {
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
        updatePlayable(cell, marker == Marker.X ? Marker.O : Marker.X);
    }

    void clearBoard() {
        for (Node node : board.getChildren()) {
            MyTicTacToe tictactoe = (MyTicTacToe) node;
            tictactoe.initialize(null);
            tictactoe.displayWinner(null);
            tictactoe.getStyleClass().clear();
        }
    }

    private void updateBoard() {
        if (game.hasMoves()) {
            showLastMove();
        } else {
            clearBoard();
        }
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

    private void updateUsers() {
        if (game.hasUserFor(Marker.X)) {
            String XUserPseudonym = game.getPlayer(Marker.X).getUser().getPseudonym();
            getPlayerLabel(Marker.X).setText(XUserPseudonym);
        }
        if (game.hasUserFor(Marker.O)) {
            String OUserPseudonym = game.getPlayer(Marker.O).getUser().getPseudonym();
            getPlayerLabel(Marker.O).setText(OUserPseudonym);
        }
    }

    @Override
    public void update(Observable o, Object o1) {
        updateBoard();
        updateUsers();
    }

}
