package atlg4.ultimate.g47923.model;

import atlg4.ultimate.g47923.dto.MoveDTO;
import atlg4.ultimate.g47923.dto.PlayerDTO;
import atlg4.ultimate.g47923.dto.PositionDTO;
import atlg4.ultimate.g47923.exception.IllegalMoveException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Represents a game of <code>UltimateTicTacToe</code>.
 *
 * @author Logan Farci (47923)
 */
public class UltimateTicTacToeGame extends Observable implements Game {

    private final Player X;
    private final Player O;
    private final UltimateTicTacToe board;
    private boolean isXCurrentPlayer;
    private Move currentMove;
    private final List<Move> executedMoves;

    /**
     * Constructs a game Ultimate TicTacToe.
     */
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

    boolean isFirstTurn() {
        return executedMoves.isEmpty();
    }

    @Override
    public PlayerDTO getCurrentPlayer() {
        return isXCurrentPlayer ? X.toDTO() : O.toDTO();
    }

    @Override
    public PlayerDTO getWinner() {
        if (!board.hasOwner()) {
            throw new IllegalStateException("No winner yet as the game is not "
                    + "over or the board is full.");
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

    @Override
    public MoveDTO getLastMove() {
        if (isFirstTurn()) {
            throw new IllegalStateException("No executed moves yet.");
        }
        return executedMoves.get(executedMoves.size() - 1).toDTO();
    }

    boolean isInExpectedMiniTicTacToe(Move move) {
        if (isFirstTurn()) {
            throw new IllegalStateException("No executed moves yet.");
        }
        Position lastCellPosition = new Position(getLastMove().getCellPosition());
        return lastCellPosition.equals(move.getMiniTicTacToePosition());
    }

    private Move requireValidMove(Move move) {
        Grid selected = board.getCellAt(move.getMiniTicTacToePosition());
        if (!selected.isPlayable()) {
            throw new IllegalMoveException(14, "The selected MiniTicTacToe is"
                    + "not playable!");
        }
        if (!isFirstTurn()) {
            Position expectedMiniPos = new Position(getLastMove().getCellPosition());
            Grid expected = board.getCellAt(expectedMiniPos);
            if (expected.isPlayable() && !isInExpectedMiniTicTacToe(move)) {
                throw new IllegalMoveException(12, "The MiniTicTacToe should be "
                        + "at the same position than the Cell the last player "
                        + "selected");
            }
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
        notifyView();
    }

    @Override
    public void nextPlayer() {
        this.isXCurrentPlayer = !isXCurrentPlayer;
        notifyView();
    }

    @Override
    public void addObserver(Observer obsrvr) {
        super.addObserver(obsrvr);
    }

    private void notifyView() {
        setChanged();
        notifyObservers();
    }

}
