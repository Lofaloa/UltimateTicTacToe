package atlg4.ultimate.g47923.model;

import atlg4.ultimate.g47923.dto.PlayerDTO;
import atlg4.ultimate.g47923.dto.PositionDTO;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a game of <code>UltimateTicTacToe</code>.
 *
 * @author Logan Farci (47923)
 */
public class UltimateTicTacToeGame implements Game {

    private final Player X;
    private final Player O;
    private final UltimateTicTacToe board;
    private boolean isXCurrentPlayer;
    private Move currentMove;
    private final List<Move> moves;

    public UltimateTicTacToeGame() {
        this.X = Player.X;
        this.O = Player.O;
        this.board = new UltimateTicTacToe();
        this.isXCurrentPlayer = true;
        this.currentMove = null;
        this.moves = new ArrayList<>();
    }

    @Override
    public PlayerDTO getCurrentPlayer() {
        return isXCurrentPlayer ? X.toDTO() : O.toDTO();
    }

    @Override
    public PlayerDTO getWinner() {
        return board.getOwner().toDTO();
    }

    @Override
    public boolean isOver() {
        return board.isOwnedBy(O) || board.isOwnedBy(X); // Or is full
    }

    @Override
    public void start() {
        this.board.initialize();
        this.isXCurrentPlayer = true;
        this.currentMove = null;
        this.moves.clear();
    }

    @Override
    public void select(PositionDTO miniTicTacToe, PositionDTO cell) {
        Position miniPosition = new Position(miniTicTacToe);
        Position cellPosition = new Position(cell);
        Player current = isXCurrentPlayer ? X : O;
        this.currentMove = new Move(current, miniPosition, cellPosition, board);
    }

    @Override
    public void play() {
        currentMove.execute();
    }

    @Override
    public void nextPlayer() {
        this.isXCurrentPlayer = !isXCurrentPlayer;
    }

}
