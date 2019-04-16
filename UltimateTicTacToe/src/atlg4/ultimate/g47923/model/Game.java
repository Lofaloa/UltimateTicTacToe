package atlg4.ultimate.g47923.model;

import atlg4.ultimate.g47923.dto.MoveDTO;
import atlg4.ultimate.g47923.dto.PlayerDTO;
import atlg4.ultimate.g47923.dto.PositionDTO;
import atlg4.ultimate.g47923.dto.UserDTO;
import atlg4.ultimate.g47923.exception.IllegalMoveException;
import java.util.Observer;

/**
 * The user of this interface can manage the logic of a game.
 *
 * @author Logan Farci (47923)
 */
public interface Game {

    /**
     * Gets the game current player.
     *
     * @return the current player.
     */
    PlayerDTO getCurrentPlayer();

    /**
     * Gets the winner of the game.
     *
     * @return the winner.
     * @throws IllegalStateException if the game is not over or when the game is
     * over but even.
     */
    PlayerDTO getWinner();

    /**
     * Gets this game last move.
     *
     * @return this game last move.
     */
    MoveDTO getLastMove();

    /**
     * Tells if this game has an user for the given marker.
     *
     * @param marker is the given marker.
     * @return true if this game has two users.
     */
    boolean hasUserFor(Marker marker);

    /**
     * Gets the player corresponding to the given marker.
     *
     * @param marker is the given marker.
     * @return the corresponding player.
     */
    PlayerDTO getPlayer(Marker marker);

    /**
     * Tells if this game has moves.
     *
     * @return true if this game has moves.
     */
    boolean hasMoves();

    /**
     * Tells if this game is over. A game is over when its board is full or one
     * of the two players owns the board.
     *
     * @return true if this game is over.
     */
    boolean isOver();

    /**
     * Starts a game.
     */
    void start();

    /**
     * Sets the given user of one of the two player randomly. If one of the two
     * players has an user, then the given user is set for the opponent.
     *
     * @param user is the given user.
     * @throws IllegalStateException when setting a using during the game.
     * @throws IllegalArgumentException when the player is already set.
     */
    void setUser(UserDTO user);

    /**
     * Selects the position where the next move will take place.
     *
     * @param parent is the position of the MiniTicTacToe where to execute the
     * move.
     * @param child is the position of the cell where to execute the move.
     * @throws IllegalMoveException is thrown in the following cases:
     * <ul>
     * <li>... the selected MiniTicTacToe is not playable (is full or owned).
     * </li>
     * <li>... the selected MiniTicTacToe is not the expected one even though it
     * is playable (is full or owned). The expected MiniTicTacToe is the one
     * located at the position of the last Cell selected by the previous player.
     * </li>
     * <li>... the selected Cell has already an owner.
     * </li>
     * </ul>
     */
    void select(PositionDTO parent, PositionDTO child);

    /**
     * Plays the current player at the selected position.
     *
     * @throws IllegalStateException if no position has been previously selected
     * with <code>select</code> method by the current player.
     */
    void play();

    /**
     * Withdraws the current player of the game. The other player wins the game.
     */
    void withdraw();

    /**
     * Passes to the next player.
     *
     * @throws IllegalStateException if the current player is not done playing.
     */
    void nextPlayer();

    /**
     *
     * @throws IllegalStateException if the game is not over.
     */
    void updateUsersStatistics();

    /**
     * Adds an observer to this game.
     *
     * @param observer is the observer to add.
     */
    void addObserver(Observer observer);

}
