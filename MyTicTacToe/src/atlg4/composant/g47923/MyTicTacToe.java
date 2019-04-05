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

/**
 * Component represent a tic tac toe grid.
 *
 * @author Logan Farci (47923)
 */
public class MyTicTacToe extends StackPane {

    public static final int SIZE = 3;
    private static final String FXML_PATH = "/fxml/MyTicTacToe.fxml";
    private static final String STYLESHEET_PATH = "/css/MyTicTacToe.css";

    @FXML
    private GridPane grid;

    @FXML
    private ImageView winner;

    /**
     * Constructs this MyTicTacToe with 9 empty cells. The cell are initially
     * not clickable.
     *
     * @throws IOException if this component FXML file cannot be loaded.
     */
    public MyTicTacToe() throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(FXML_PATH));
            loader.setRoot(this);
            loader.setController(this);
            loader.load();
        } catch (IOException exception) {
            throw new IOException(FXML_PATH + " cannot be loaded.", exception);
        }
        getStylesheets().add(STYLESHEET_PATH);
        doBindings();
    }

    /**
     * Sets the given marker at the given position. When marker is null the
     * image in the given cell is removed.
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
     * Initializes this TicTacToe markers to a default value. If default value
     * is null, all images are removed from this MyTicTacToe.
     *
     * @param defaultValue is the value to initialize this TictTacToe with.
     * @throws NullPointerException is the argument is null.
     */
    public final void initialize(Image defaultValue) {
        for (Node node : grid.getChildren()) {
            StackPane pane = (StackPane) node;
            ImageView imgv = (ImageView) pane.getChildren().get(0);
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
        winner.setImage(winnerMarker);
    }

    /**
     * Adds an event handler at the given cell.
     *
     * @param row is the row of the cell.
     * @param column is the column of the cell.
     * @param handler is the event handler to add.
     */
    public void addEventHandlerAt(int row, int column, EventHandler handler) {
        requireValidCoordinate(row, column);
        StackPane pane = requireNonNull(getPaneAt(row, column));
        pane.setOnMouseClicked(handler);
    }

    /**
     * Remove a previously added event handler at the given cell.
     *
     * @param row is the row of the cell.
     * @param column is the column of the cell.
     */
    public void removeEventHandlerAt(int row, int column) {
        requireValidCoordinate(row, column);
        StackPane pane = requireNonNull(getPaneAt(row, column));
        pane.setOnMouseClicked(null);
    }

    /**
     * Clears this MyTicTacToe cells event handlers.
     */
    public void clearEventHandlers() {
        for (int row = 0; row < SIZE; row++) {
            for (int column = 0; column < SIZE; column++) {
                removeEventHandlerAt(row, column);
            }
        }
    }

    static private boolean isValid(int row, int column) {
        return 0 <= row && row <= SIZE && 0 <= column && column <= SIZE;
    }

    static private void requireValidCoordinate(int row, int column) {
        if (!isValid(row, column)) {
            throw new IllegalArgumentException("Invalid coordinate at (" + row
                    + "; " + column + ").");
        }
    }

    private StackPane getPaneAt(int row, int column) {
        requireValidCoordinate(row, column);
        for (Node node : grid.getChildren()) {
            if (getColumnIndex(node) == column && getRowIndex(node) == row) {
                return (StackPane) node;
            }
        }
        return null;
    }

    private void doCellsBindings() {
        for (Node node : grid.getChildren()) {
            StackPane pane = (StackPane) node;
            pane.prefWidthProperty().bind(grid.widthProperty());
            pane.prefHeightProperty().bind(grid.heightProperty());
        }
    }

    private void doWinnerBindings() {
        winner.fitWidthProperty().bind(grid.widthProperty());
        winner.fitHeightProperty().bind(grid.heightProperty());
    }

    private void doBindings() {
        doWinnerBindings();
        doCellsBindings();
    }

}
