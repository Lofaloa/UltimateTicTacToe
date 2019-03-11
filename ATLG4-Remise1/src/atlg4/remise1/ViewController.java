package atlg4.remise1;

import atlg4.composant.g47923.MyTicTacToe;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Logan
 */
public class ViewController implements Initializable {

    @FXML
    private MyTicTacToe tictactoe;
    @FXML
    private ToggleGroup marker;

    private Image getCurrentMarker() {
        if (marker.getSelectedToggle().equals("X")) {
            return new Image("/images/cross.png");
        } else {
            return new Image("/images/circle.png");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tictactoe.addEventHandler(e -> {
            Node node = (Node) e.getTarget();
            int row = GridPane.getRowIndex(node);
            int column = GridPane.getColumnIndex(node);
            tictactoe.setMarker(row, column, getCurrentMarker());
        });
    }

}
