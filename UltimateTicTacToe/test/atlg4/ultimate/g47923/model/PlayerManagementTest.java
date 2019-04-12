package atlg4.ultimate.g47923.model;

import atlg4.ultimate.g47923.dto.PositionDTO;
import atlg4.ultimate.g47923.dto.UserDTO;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Tests the management of the players during a game.
 *
 * @author Logan
 */
public class PlayerManagementTest {

    private void setPlayersUsers(Game game) {
        UserDTO xUser = new UserDTO("Willie", 1, 2, 3);
        UserDTO oUser = new UserDTO("Nazari", 1, 2, 3);
        game.setUserOf(Marker.X, xUser);
        game.setUserOf(Marker.O, oUser);
    }

    /**
     * Setting the user of the X player should set the expected user of the X
     * player.
     */
    @Test
    public void setUserOf_xPlayerHasAUser() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        game.setUserOf(Marker.X, new UserDTO("Pinguino", 2, 3, 4));
        assertEquals("Pinguino", game.getX().getUser().getPseudonym());
    }

    /**
     * Setting the user of the O player should set the expected user of the O
     * player.
     */
    @Test
    public void setUserOf_oPlayerHasAUser() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        game.setUserOf(Marker.O, new UserDTO("Anatra", 2, 3, 4));
        assertEquals("Anatra", game.getO().getUser().getPseudonym());
    }

    /**
     * Setting a user during a game should cause an exception.
     */
    @Test(expected = IllegalStateException.class)
    public void setUserOf() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        Move move = new Move(Player.X, new Position(), new Position(), game.getBoard());
        for (int m = 0; m < 5; m++) {
            game.getExecutedMoves().add(move);
        }
        game.setUserOf(Marker.O, new UserDTO("Cuaddu", 2, 3, 4));
    }

    /**
     * The game should pass to the next player as expected.
     */
    @Test
    public void nextPlayer() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        assertEquals(Marker.X, game.getCurrentPlayer().getMarker());

        setPlayersUsers(game);

        game.select(new PositionDTO(1, 1), new PositionDTO(0, 0));
        game.play();
        game.nextPlayer();

        game.clearUsers();

        assertEquals(Marker.O, game.getCurrentPlayer().getMarker());
    }

    /**
     * Calling nextPlayer methods when the first player has not selected nor
     * played causes an exception.
     */
    @Test(expected = IllegalStateException.class)
    public void nextPlayer_firstPlayerNotDone() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        game.nextPlayer();
    }

    /**
     * Calling nextPlayer methods when the current player is done causes an
     * exception.
     */
    @Test(expected = IllegalStateException.class)
    public void nextPlayer_currentPlayerNotDone() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        game.select(new PositionDTO(1, 1), new PositionDTO(0, 0));
        game.play();
        game.nextPlayer();
        game.select(new PositionDTO(0, 0), new PositionDTO(2, 1));
        game.nextPlayer();
    }

}
