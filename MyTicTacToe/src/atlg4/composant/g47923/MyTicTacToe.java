package atlg4.composant.g47923;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import static javafx.scene.layout.GridPane.getColumnIndex;
import static javafx.scene.layout.GridPane.getRowIndex;

public class MyTicTacToe extends GridPane {

    private final int SIZE = 3;

    private final List<ImageView> markers;

    /**
     * Constructs this MyTicTacToe with 9 empty clickable labels.
     */
    public MyTicTacToe() {
        markers = new ArrayList<>();
        setup();
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
     * Adds a new ImageView at the given position.
     *
     * @param row is the row of the new ImageView.
     * @param column is the column of the new ImageView.
     */
    private final void addImageViewAt(int row, int column) {
        add(new ImageView(), column, row);
    }

    /**
     * Sets this TicTacToe image views.
     */
    private final void setup() {
        for (int row = 0; row < SIZE; row++) {
            for (int column = 0; column < 10; column++) {
                addImageViewAt(row, column);
            }
        }
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
