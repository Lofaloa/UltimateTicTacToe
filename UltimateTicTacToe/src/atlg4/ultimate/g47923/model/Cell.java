package atlg4.ultimate.g47923.model;

/**
 * Represents a cell.
 * 
 * @author Logan Farci (47923)
 */
public class Cell {
    
    private int row;
    private int column;

    /**
     * Constructs this cell with the given coordinates.
     * 
     * @param row is the row of this cell.
     * @param column is the column of this cell.
     */
    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Gets this cell row.
     * 
     * @return this cell row. 
     */
    public int getRow() {
        return row;
    }

    /**
     * Gets this cell column.
     * 
     * @return this cell column. 
     */
    public int getColumn() {
        return column;
    }
    
}
