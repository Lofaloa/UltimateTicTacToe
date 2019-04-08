package atlg4.ultimate.g47923.model;

import atlg4.ultimate.g47923.dto.PlayerDTO;
import atlg4.ultimate.g47923.dto.PositionDTO;
import atlg4.ultimate.g47923.exception.IllegalMoveException;
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
    private final List<Move> executedMoves;

    public UltimateTicTacToeGame() {
        this.X = Player.X;
        this.O = Player.O;
        this.board = new UltimateTicTacToe();
        this.isXCurrentPlayer = true;
        this.currentMove = null;
        this.executedMoves = new ArrayList<>();
    }

    UltimateTicTacToe getBoard() {
        return board;
    }

    Move getCurrentMove() {
        return currentMove;
    }

    List<Move> getExecutedMoves() {
        return executedMoves;
    }

    boolean isFirstRound() {
        return executedMoves.size() <= 2;
    }

    @Override
    public PlayerDTO getCurrentPlayer() {
        return isXCurrentPlayer ? X.toDTO() : O.toDTO();
    }

    @Override
    public PlayerDTO getWinner() {
        if (!isOver()) {
            throw new IllegalStateException("No winner yet as the game is not "
                    + "over.");
        }
        return board.getOwner().toDTO();
    }

    @Override
    public boolean isOver() {
        return board.hasOwner() || board.isFull();
    }

    @Override
    public void start() {
        this.board.initialize();
        this.isXCurrentPlayer = true;
        this.currentMove = null;
        this.executedMoves.clear();
    }

    Move getLastExecutedMove() {
        if (executedMoves.isEmpty()) {
            throw new IllegalStateException("No executed moves yet.");
        }
        return executedMoves.get(executedMoves.size() - 1);
    }

    boolean isValidMove(Move move) {
        Grid mini = board.getCellAt(move.getMiniTicTacToePosition());
        return (isFirstRound() || !mini.isFull() || !mini.hasOwner())
                || getLastExecutedMove().getCellPosition()
                == move.getMiniTicTacToePosition();
    }

    Move requireValidMove(Move move) {
        if (!isValidMove(move)) {
            throw new IllegalMoveException(12, "This move cannot be done.");
        }
        return move;
    }

    @Override
    public void select(PositionDTO miniTicTacToe, PositionDTO cell) {
        Position miniPosition = new Position(miniTicTacToe);
        Position cellPosition = new Position(cell);
        Player current = isXCurrentPlayer ? X : O;
        Move move = new Move(current, miniPosition, cellPosition, board);
        this.currentMove = requireValidMove(move);
    }

    @Override
    public void play() {
        currentMove.execute();
        executedMoves.add(currentMove);
    }

    @Override
    public void nextPlayer() {
        this.isXCurrentPlayer = !isXCurrentPlayer;
    }

}
