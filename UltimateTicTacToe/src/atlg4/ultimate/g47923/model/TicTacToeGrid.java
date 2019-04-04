package atlg4.ultimate.g47923.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a tic tac toe.
 *
 * @author Logan Farci (47923)
 * @param <CellType> is the type of this tic tac toe grid content.
 */
class TicTacToeGrid<CellType extends Grid> implements Grid {

    private final List<CellType> cells;
    private final Player owner;
    private final int size;

    /**
     * Constructs a tic tac toe grid of the given size.
     *
     * @param size is the size of this tic tac toe grid.
     */
    public TicTacToeGrid(int size) {
        this.cells = new ArrayList<>();
        this.owner = null;
        this.size = size;
    }
    
    private void initialize() {
        for (int i = 0; i < this.size; i++) {
            CellType get = cells.get(i);
            
        }
    }

    @Override
    public boolean hasOwner() {
        return owner != null;
    }

    @Override
    public boolean hasFullRowOf(Object value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean hasFullColumnOf(Object value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean hasFullDiagonalOf(Object value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
