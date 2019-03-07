package atlg4.composant.g47923;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;

/**
 * Represents a MyTicTacToe cell.
 *
 * @author Logan Farci
 */
public class Cell extends Region {

    /**
     * Is the size of this cell.
     */
    private static final int SIZE = 100;
    
    /**
     * Is the style class of this cell.
     */
    private static final String STYLE_CLASS = "cell";

    /**
     * Is the marker of this cell.
     */
    private final ImageView marker;

    /**
     * Constructs an empty Cell.
     */
    public Cell() {
        this.marker = new ImageView();
        addChildren();
        setStyle();
    }

    /**
     * Sets this cell marker.
     * 
     * @param marker is the marker of this Cell. 
     */
    final void setMarker(Image marker) {
        this.marker.setImage(marker);
    }

    /**
     * Sets the style of this cell.
     */
    private void setStyle() {
        marker.setFitWidth(SIZE);
        marker.setFitHeight(SIZE);
        getStyleClass().add(STYLE_CLASS);
    }

    /**
     * Adds a ImageView to this Cell Children.
     */
    private void addChildren() {
        getChildren().add(marker);
    }

}
