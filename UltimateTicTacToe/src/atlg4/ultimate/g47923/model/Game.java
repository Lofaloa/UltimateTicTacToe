package atlg4.ultimate.g47923.model;

import atlg4.ultimate.g47923.dto.PlayerDTO;
import atlg4.ultimate.g47923.dto.PositionDTO;

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
     */
    PlayerDTO getWinner();

    /**
     * Tells if this game is over.
     *
     * @return true if this game is over.
     */
    boolean isOver();

    /**
     * Starts a game.
     */
    void start();

    /**
     * Selects the position where the next move will take place.
     *
     * @param parent is the
     * @param child
     */
    void select(PositionDTO parent, PositionDTO child);

    /**
     * Plays the current move.
     */
    void play();

    /**
     * Passes to the next player.
     */
    void nextPlayer();

}
