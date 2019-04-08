package atlg4.ultimate.g47923.view;

import atlg4.composant.g47923.MyTicTacToe;
import atlg4.ultimate.g47923.model.Game;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Is the game screen.
 *
 * @author Logan Farci (47923)
 */
public class GameView extends VBox implements Initializable {

    private static final String TITLE = "Ultimate Tic Tac Toe";
    private static final String FXML_PATH = "/fxml/GameScreen.fxml";

    private Game game;
    private Stage stage;

    @FXML
    private GridPane board;

    /**
     * Constructs this MyTicTacToe with 9 empty cells. The cell are initially
     * not clickable.
     */
    public GameView(Game game) {
        this.game = game;
        this.stage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(FXML_PATH));
            loader.setRoot(this);
            loader.setController(this);
            loader.load();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private void initializeStage() {
        stage.setTitle(TITLE);
        stage.setResizable(true);
        Scene scene = new Scene(this);
        stage.setScene(scene);
    }

    /**
     * Shows this game screen.
     */
    public void show() {
        initializeStage();
        stage.show();
    }

    private void addHandler(MyTicTacToe t, final int row, final int column) {
        t.addEventHandlerAt(row, column, new EventHandler() {
            @Override
            public void handle(Event event) {
                // handles a click on a cell.
            }
        });
    }

    private void addHandlers(MyTicTacToe t) {
        for (int row = 0; row < MyTicTacToe.SIZE; row++) {
            for (int column = 0; column < MyTicTacToe.SIZE; column++) {
                addHandler(t, row, column);
            }
        }
    }

    private void addHandlers() {
        for (Node child : board.getChildren()) {
            MyTicTacToe tictactoe = (MyTicTacToe) child;
            addHandlers(tictactoe);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addHandlers();
    }

}
