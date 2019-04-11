package atlg4.ultimate.g47923.view;

import atlg4.composant.g47923.MyTicTacToe;
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
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import static java.util.Objects.requireNonNull;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import static javafx.scene.layout.GridPane.getRowIndex;
import static javafx.scene.layout.GridPane.getColumnIndex;

/**
 * Is the game screen.
 *
 * @author Logan Farci (47923)
 */
class GameWindow extends VBox implements Initializable, Observer {

    private static final String TITLE = "Ultimate Tic Tac Toe";
    private final String CROSS_IMG_PATH = "/images/cross.png";
    private final String CIRCLE_IMG_PATH = "/images/circle.png";
    private static final String FXML_PATH = "/fxml/GameWindow.fxml";

    private final Game game;
    private final Stage stage;

    @FXML
    private GridPane board;

    /**
     * Constructs an instance of this GameWindow with the specified game and a
     * new stage.
     *
     * @param game is the game to represent.
     */
    GameWindow(Game game) throws IOException {
        this.game = requireNonNull(game, "Constructing a GameWindow with a null "
                + "game.");
        this.stage = new Stage();
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

    private void initializeStage() {
        stage.setTitle(TITLE);
        stage.setResizable(true);
        Scene scene = new Scene(this);
        stage.setScene(scene);
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

    void show() {
        game.addObserver(this);
        initializeStage();
        stage.show();
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

    private void addHandlers() {
        for (Node child : board.getChildren()) {
            MyTicTacToe tictactoe = (MyTicTacToe) child;
            addHandlersTo(tictactoe);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addHandlers();
    }

    private void showIllegalMoveAlert(String msg) {
        Alert warning = new IllegalMoveAlert(msg);
        warning.show();
    }

    private void showEnd() {
        Dialog replay = new ReplayDialog(game);
        replay.showAndWait();
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
