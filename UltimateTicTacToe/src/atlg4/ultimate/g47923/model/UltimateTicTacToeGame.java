package atlg4.ultimate.g47923.model;

import atlg4.ultimate.g47923.dto.MoveDTO;
import atlg4.ultimate.g47923.dto.PlayerDTO;
import atlg4.ultimate.g47923.dto.PositionDTO;
import atlg4.ultimate.g47923.dto.UserDTO;
import atlg4.ultimate.g47923.exception.IllegalMoveException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

/**
 * Represents a game of <i>UltimateTicTacToe</i>.
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
    
    private boolean isSet(UserDTO user) {
        return X.hasUser() && user.getPseudonym().equals(X.getUser().getPseudonym())
                || O.hasUser() && user.getPseudonym().equals(O.getUser().getPseudonym());
    }

    private int getRandomNumberInRange(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }

    private Player getRandomPlayer() {
        int random = getRandomNumberInRange(0, 1);
        Player player = random == 1 ? X : O;
        if (!isOver() && player.hasUser()) {
            player = player == X ? O : X;
        }
        return player;
    }
    
    private boolean haveUsersBeenSet() {
        return X.hasUser() && O.hasUser();
    }
    
    boolean isInExpectedMiniTicTacToe(Move move) {
        if (isFirstTurn()) {
            throw new IllegalStateException("No executed moves yet.");
        }
        Position lastCellPosition = new Position(getLastMove().getCellPosition());
        return lastCellPosition.equals(move.getMiniTicTacToePosition());
    }
    
    @Override
    public PlayerDTO getCurrentPlayer() {
        return isXCurrentPlayer ? X.toDTO() : O.toDTO();
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
    public PlayerDTO getPlayer(Marker marker) {
        return marker == Marker.X ? X.toDTO() : O.toDTO();
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
    public void setUser(UserDTO user) {
        Objects.requireNonNull(user);
        if (!isFirstTurn() && !isOver()) {
            throw new IllegalStateException("Cannot set the users during the "
                    + "game.");
        }
        if (isSet(user)) {
            throw new IllegalArgumentException("The given user has already been set!");
        }
        getRandomPlayer().setUser(new User(user));
        notifyView();
    }
    
    @Override
    public void removeUserFor(Marker marker) {
        if (!isFirstTurn() && !isOver()) {
            throw new IllegalStateException("Cannot remove a user during a game.");
        }
        if (marker == Marker.X) {
            X.setUser(null);
        } else {
            O.setUser(null);
        }
        notifyView();
    }

    @Override
    public MoveDTO getLastMove() {
        if (isFirstTurn()) {
            throw new IllegalStateException("No executed moves yet.");
        }
        return executedMoves.get(executedMoves.size() - 1).toDTO();
    }

    private Move requireValidMove(Move move) {
        Grid selected = board.getCellAt(move.getMiniTicTacToePosition());
        if (!selected.isPlayable()) {
            throw new IllegalMoveException("The selected MiniTicTacToe is"
                    + "not playable!");
        }
        if (!isFirstTurn()) {
            Position expectedMiniPos = new Position(getLastMove().getCellPosition());
            Grid expected = board.getCellAt(expectedMiniPos);
            if (expected.isPlayable() && !isInExpectedMiniTicTacToe(move)) {
                throw new IllegalMoveException("The MiniTicTacToe should be "
                        + "at the same position than the Cell the last player "
                        + "selected");
            }
        }
        return move;
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

    private boolean hasCurrentPlayerSelectedAPosition() {
        if (currentMove == null) {
            throw new IllegalStateException("Select a position before playing!");
        }
        return currentMove.getAuthor().getMarker()
                == getCurrentPlayer().getMarker();
    }

    @Override
    public void play() {
        if (!hasCurrentPlayerSelectedAPosition()) {
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

    private boolean isTie() {
        return isOver() && board.isFull();
    }

    @Override
    public void updateUsersStatistics() {
        if (!isOver()) {
            throw new IllegalStateException("The game is not over yet.");
        }
        if (isTie()) {
            X.getUser().addATie();
            O.getUser().addATie();
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
