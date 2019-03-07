package atlg4.composant.g47923;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import static javafx.scene.layout.GridPane.getColumnIndex;
import static javafx.scene.layout.GridPane.getRowIndex;

public class MyTicTacToe extends GridPane {

    private final int SIZE = 3;

    /**
     * Constructs this MyTicTacToe with 9 empty clickable cells.
     */
    public MyTicTacToe() {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/MyTicTacToe.fxml")
        );
        try {
           loader.setRoot(this);
           loader.load();
        } catch (IOException exception) {
            System.err.println(exception.getMessage());
        }
    }

    /**
     * Gets the ImageView at the given position in this TicTacToe.
     *
     * @param row the row of the ImageView to get.
     * @param column the column of the ImageView to get.
     * @return the ImageView at the given position.
     */
    private ImageView getImageViewAt(int row, int column) {
        for (Node node : getChildren()) {
            if (getColumnIndex(node) == column && getRowIndex(node) == row) {
                return (ImageView) node;
            }
        }
        return null;
    }

    /**
     * Sets the given marker at the given position.
     *
     * @param row is the row of the marker.
     * @param column is the column of the marker.
     * @param marker is an image representing a marker.
     */
    public void setMarker(int row, int column, Image marker) {
        getImageViewAt(row, column).setImage(marker);
    }

    /**
     * Initializes this TicTacToe markers to a default value;
     *
     * @param defaultValue is the value to initialize this TictTacToe with.
     */
    public final void initialize(Image defaultValue) {
        getChildren().forEach((imageView) -> {
            ((ImageView) imageView).setImage(defaultValue);
        });
    }

    /**
     * Displays the given marker over this TicTacToe.
     *
     * @param winnerMarker is the marker to display.
     */
    public void displayWinner(String winnerMarker) {
        throw new UnsupportedOperationException();
    }

}
