package atlg4.ultimate.g47923.model;

/**
 * Represents an 3 by 3 ultimate tic tac toe. 
 * 
 * @author Logan Farci (47923)
 */
public class UltimateTicTacToe {
    
    static private int SIZE = 3;
    
    private MiniTicTacToe[][] miniTicTacToes;

    /**
     * Constructs an empty ultimate tic tac toe. 
     */
    UltimateTicTacToe() {
        miniTicTacToes = new MiniTicTacToe[SIZE][SIZE];
    }
    
    /**
     * Initializes this ultimate tic tac toe with empty marker.
     */
    void initialize() {
        throw new UnsupportedOperationException();
    } 
    
    
   
}
