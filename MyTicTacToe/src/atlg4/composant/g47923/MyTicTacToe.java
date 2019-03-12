package atlg4.composant.g47923;

import java.io.IOException;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import static javafx.scene.layout.GridPane.getColumnIndex;
import static javafx.scene.layout.GridPane.getRowIndex;
import javafx.scene.layout.StackPane;
import static java.util.Objects.requireNonNull;

public class MyTicTacToe extends StackPane {

    private static final int SIZE = 3;
    private static final String FXML_PATH = "/MyTicTacToe.fxml";
    private static final String STYLESHEET_PATH = "/css/style.css";

    @FXML
    private GridPane grid;

    /**
     * Constructs this MyTicTacToe with 9 empty clickable cells.
     */
    public MyTicTacToe() {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(FXML_PATH)
        );
        try {
            loader.setRoot(this);
            loader.setController(this);
            loader.load();
        } catch (IOException exception) {
            System.err.println(exception.getMessage());
        }
        getStylesheets().add(STYLESHEET_PATH);
    }

    public GridPane getGrid() {
        return grid;
    }



    /**
     * Tells if the given coordinates are valid. A valid coordinate is inside
     * this tic tac toe bounds.
     *
     * @param row is the row of the coordinate.
     * @param column is the column of the coordinate.
     * @return true if the coordinate is valid.
     */
    static private boolean isValid(int row, int column) {
        return 0 <= row && row <= SIZE && 0 <= column && column <= SIZE;
    }

    /**
     * Requires a valid coordinate.
     *
     * @param row is the row of the coordinate.
     * @param column is the column of the coordinate.
     * @throws IllegalArgumentException is the coordinate is not valid.
     */
    static private void requireValidCoordinate(int row, int column) {
        if (!isValid(row, column)) {
            throw new IllegalArgumentException("Invalid coordinate at (" + row
                    + "; " + column + ").");
        }
    }

    /**
     * Gets the ImageView at the given position in this TicTacToe.
     *
     * @param row the row of the ImageView to get.
     * @param column the column of the ImageView to get.
     * @return the ImageView at the given position.
     * @throws IllegalArgumentException is the coordinate is not valid.
     */
    StackPane getPaneAt(int row, int column) {
        requireValidCoordinate(row, column);
        for (Node node : grid.getChildren()) {
            if (getColumnIndex(node) == column && getRowIndex(node) == row) {
                return (StackPane) node;
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
     * @throws IllegalArgumentException is the given coordinate is not valid.
     */
    public void setMarker(int row, int column, Image marker) {
        requireValidCoordinate(row, column);
        ((ImageView) getPaneAt(row, column).getChildren().get(0)).setImage(marker);
    }

    /**
     * Initializes this TicTacToe markers to a default value;
     *
     * @param defaultValue is the value to initialize this TictTacToe with.
     * @throws NullPointerException is the argument is null.
     */
    public final void initialize(Image defaultValue) {
        requireNonNull(defaultValue);
        for (Node node : grid.getChildren()) {
            StackPane pane = (StackPane) node;
            ImageView imgv = (ImageView) pane.getChildren().get(0);
            imgv.setFitHeight(50);
            imgv.setFitWidth(50);
            imgv.setImage(defaultValue);
        }
    }

    /**
     * Displays the given marker over this TicTacToe.
     *
     * @param winnerMarker is the marker to display.
     * @throws NullPointerException is the argument is null.
     */
    public void displayWinner(Image winnerMarker) {
        requireNonNull(winnerMarker);
        getChildren().add(new ImageView(winnerMarker));
    }

    public void addEventHandlerAt(int row, int column, EventHandler handler) {
        requireValidCoordinate(row, column);
        StackPane pane = requireNonNull(getPaneAt(row, column));
        pane.setOnMouseClicked(handler);
    }

    public void removeEventHandlerAt(int row, int column) {
        requireValidCoordinate(row, column);
        StackPane pane = requireNonNull(getPaneAt(row, column));
        pane.setOnMouseClicked(null);
    }

}
