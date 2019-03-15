package atlg4.remise1;

import atlg4.composant.g47923.MyTicTacToe;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

public class ViewController implements Initializable {

    private final String CROSS_IMG_PATH = "/images/cross.png";
    private final String CIRCLE_IMG_PATH = "/images/circle.png";
    private final String STYLESHEET_PATH = "/css/MyTicTacToeDemo.css";

    @FXML
    private VBox container;

    @FXML
    private MyTicTacToe tictactoe;

    @FXML
    private RadioButton cross;

    @FXML
    private void displayWinner() {
        tictactoe.clearEventHandlers();
        tictactoe.displayWinner(getCurrentMarker());
    }

    @FXML
    private void clearTicTacToe() {
        tictactoe.initialize(null);
        tictactoe.displayWinner(null);
        initializeEventHandlers();
    }

    @FXML
    private void fillTicTacToe() {
        tictactoe.clearEventHandlers();
        tictactoe.initialize(getCurrentMarker());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeEventHandlers();
        tictactoe.prefWidthProperty().bind(container.heightProperty());
        tictactoe.prefHeightProperty().bind(container.widthProperty());
        container.getStylesheets().add(STYLESHEET_PATH);
    }

    private final Image getCurrentMarker() {
        if (cross.isSelected()) {
            return new Image(CROSS_IMG_PATH);
        } else {
            return new Image(CIRCLE_IMG_PATH);
        }
    }

    private void addPlaceMarkerHandlerAt(final int row, final int column) {
        tictactoe.addEventHandlerAt(row, column, new EventHandler() {
            @Override
            public void handle(Event event) {
                tictactoe.setMarker(row, column, getCurrentMarker());
                tictactoe.removeEventHandlerAt(row, column);
            }
        });
    }

    private void initializeEventHandlers() {
        for (int row = 0; row < MyTicTacToe.SIZE; row++) {
            for (int column = 0; column < MyTicTacToe.SIZE; column++) {
                addPlaceMarkerHandlerAt(row, column);
            }
        }
    }

}
