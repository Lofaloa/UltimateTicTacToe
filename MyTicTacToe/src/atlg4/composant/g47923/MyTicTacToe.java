package atlg4.composant.g47923;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class MyTicTacToe extends GridPane {

    private final int SIZE = 3;

    private boolean isClickable;

    public MyTicTacToe() {
        isClickable = true;
        setup();
        setGridLinesVisible(true);
        initialize("default");
    }

    /**
     * Sets isClickable value;
     *
     * @param value is the value of isClickable;
     */
    public void setIsClickable(boolean value) {
        isClickable = value;
    }

    /**
     * Sets the given marker at the given position.
     *
     * @param row is the row of the marker.
     * @param column is the column of the marker.
     * @param marker is the value of the marker.
     */
    public void setMarker(int row, int column, String marker) {
        throw new UnsupportedOperationException();
    }

    /**
     * Adds a new Label at the given position.
     *
     * @param row is the row of the new Label.
     * @param column is the column of the new Label.
     */
    private final void addLabelAt(int row, int column) {
        add(new Label(), column, row);
    }

    /**
     * Sets this TicTacToe labels.
     */
    private final void setup() {
        for (int row = 0; row < SIZE; row++) {
            for (int column = 0; column < 10; column++) {
                addLabelAt(row, column);
            }
        }
    }

    /**
     * Initializes this TicTacToe markers to a default value;
     *
     * @param defaultValue is the value to initialize this TictTacToe with.
     */
    public final void initialize(String defaultValue) {
        getChildren().forEach((child) -> {
            ((Label) child).setText(defaultValue);
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
