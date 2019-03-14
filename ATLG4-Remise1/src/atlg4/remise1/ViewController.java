package atlg4.remise1;

import atlg4.composant.g47923.MyTicTacToe;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;

/**
 *
 * @author Logan Farci (47923)
 */
public class ViewController implements Initializable {

    private final String CROSS_IMG_PATH = "/images/cross.png";
    private final String CIRCLE_IMG_PATH = "/images/circle.png";

    @FXML
    private MyTicTacToe tictactoe;

    @FXML
    private RadioButton cross;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                addPlaceMarkerHandlerAt(row, column);
            }
        }
    }

}
