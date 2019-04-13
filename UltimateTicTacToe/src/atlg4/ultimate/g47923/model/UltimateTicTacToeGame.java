package atlg4.ultimate.g47923.model;

import atlg4.ultimate.g47923.dto.MoveDTO;
import atlg4.ultimate.g47923.dto.PlayerDTO;
import atlg4.ultimate.g47923.dto.PositionDTO;
import atlg4.ultimate.g47923.dto.UserDTO;
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
        getX().setWithDrawn(false);
        getO().setWithDrawn(false);
    }

    UltimateTicTacToe getBoard() {
        return board;
    }

    final Player getX() {
        return X;
    }

    final Player getO() {
        return O;
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

    boolean hasAPlayerWithdrawn() {
        return X.isWithdrawn() || O.isWithdrawn();
    }

    private Player getRemainingPlayer() {
        if (!hasAPlayerWithdrawn()) {
            throw new IllegalStateException("No player has withdrawn.");
        }
        return X.isWithdrawn() ? O : X;
    }

    void setIsXCurrentPlayer(boolean value) {
        isXCurrentPlayer = value;
    }

    @Override
    public PlayerDTO getWinner() {
        if (!isOver()) {
            throw new IllegalStateException("No winner yet as the game is not "
                    + "over or the board is full.");
        }
        if (hasAPlayerWithdrawn()) {
            return getRemainingPlayer().toDTO();
        } else if (board.hasOwner()) {
            return board.getOwner().toDTO();
        } else {
            throw new IllegalStateException("The game is even and has no winner");
        }
    }

    @Override
    public boolean hasUserFor(Marker marker) {
        return marker == Marker.X ? X.hasUser() : O.hasUser();
    }

    @Override
    public boolean isOver() {
        return board.hasOwner() || board.isFull() || hasAPlayerWithdrawn();
    }

    @Override
    public boolean hasMoves() {
        return !isFirstTurn();
    }

    @Override
    public void start() {
        this.board.initialize();
        this.isXCurrentPlayer = true;
        this.currentMove = null;
        this.X.setWithDrawn(false);
        this.O.setWithDrawn(false);
        this.executedMoves.clear();
        notifyView();
    }

    @Override
    public void setUserOf(Marker marker, UserDTO user) {
        if (!isFirstTurn()) {
            throw new IllegalStateException("Cannot set the users during the "
                    + "game.");
        }
        if (marker == Marker.X) {
            X.setUser(new User(user));
        } else {
            O.setUser(new User(user));
        }
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

    private boolean haveUsersBeenSet() {
        return X.hasUser() && O.hasUser();
    }

    @Override
    public void select(PositionDTO miniTicTacToe, PositionDTO cell) {
        if (!haveUsersBeenSet()) {
            throw new IllegalStateException("No users have been set.");
        }
        Position miniPosition = new Position(miniTicTacToe);
        Position cellPosition = new Position(cell);
        Player current = isXCurrentPlayer ? X : O;
        Move move = new Move(current, miniPosition, cellPosition, board);
        this.currentMove = requireValidMove(move);
    }

    private boolean currentPlayerHasSelected() {
        if (currentMove == null) {
            throw new IllegalStateException("Select a position before playing!");
        }
        return currentMove.getAuthor().getMarker()
                == getCurrentPlayer().getMarker();
    }

    @Override
    public void play() {
        if (!currentPlayerHasSelected()) {
            throw new IllegalStateException("Select a position before playing!");
        }
        currentMove.execute();
        executedMoves.add(currentMove);
        notifyView();
    }

    @Override
    public void withdraw() {
        if (isXCurrentPlayer) {
            X.setWithDrawn(true);
        } else {
            O.setWithDrawn(true);
        }
    }

    private boolean hasCurrentPlayerPlayed() {
        if (isFirstTurn()) {
            throw new IllegalStateException("The first player has not even "
                    + "selected nor played yet!");
        }
        return getLastMove().getAuthor().getMarker()
                == getCurrentPlayer().getMarker();
    }

    @Override
    public void nextPlayer() {
        if (!hasCurrentPlayerPlayed()) {
            throw new IllegalStateException("The current player has not played "
                    + "yet!");
        }
        this.isXCurrentPlayer = !isXCurrentPlayer;
    }

    private boolean isExaequo() {
        return isOver() && board.isFull();
    }

    @Override
    public void updateUsersStatistics() {
        if (!isOver()) {
            throw new IllegalStateException("The game is not over yet.");
        }
        if (isExaequo()) {
            X.getUser().addAnExaequo();
            O.getUser().addAnExaequo();
        } else {
            if (getWinner().getMarker() == Marker.X) {
                X.getUser().addAVictory();
                O.getUser().addADefeat();
            } else {
                X.getUser().addADefeat();
                O.getUser().addAVictory();
            }
        }
    }

    @Override
    public void addObserver(Observer obsrvr) {
        super.addObserver(obsrvr);
    }

    private void notifyView() {
        setChanged();
        notifyObservers();
    }

    void clearUsers() {
        X.setUser(null);
        O.setUser(null);
    }

}
