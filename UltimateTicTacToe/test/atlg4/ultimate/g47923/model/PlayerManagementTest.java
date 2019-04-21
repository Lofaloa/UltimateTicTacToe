package atlg4.ultimate.g47923.model;

import atlg4.ultimate.g47923.dto.PositionDTO;
import atlg4.ultimate.g47923.dto.UserDTO;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * Tests the management of the players during a game.
 *
 * @author Logan
 */
public class PlayerManagementTest {

    private void setPlayersUsers(UltimateTicTacToeGame game) {
        game.getX().setUser(new User("Willie", 1, 2, 3));
        game.getO().setUser(new User("Nazari", 1, 2, 3));
    }

    /**
     * Getting the X player should get X player.
     */
    @Test
    public void getPlayer_gettingXPlayer() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        assertEquals(Marker.X, game.getPlayer(Marker.X).getMarker());
    }

    /**
     * Getting the O player should get O player.
     */
    @Test
    public void getPlayer_gettingOPlayer() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        assertEquals(Marker.O, game.getPlayer(Marker.O).getMarker());
    }

    /**
     * Getting the X player should get X player.
     */
    /**
     * Setting the user of the X player should set the expected user of the X
     * player.
     */
    @Test
    public void setUser_xPlayerHasAUser() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        game.getX().setUser(new User("Pinguino", 2, 3, 4));
        assertEquals("Pinguino", game.getX().getUser().getPseudonym());
    }

    /**
     * Setting the user of the O player should set the expected user of the O
     * player.
     */
    @Test
    public void setUser_oPlayerHasAUser() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        game.getO().setUser(new User("Anatra", 2, 3, 4));
        assertEquals("Anatra", game.getO().getUser().getPseudonym());
    }

    /**
     * Setting a user during a game should cause an exception.
     */
    @Test(expected = IllegalStateException.class)
    public void setUser_settingDuringAGame() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        Move move = new Move(Player.X, new Position(), new Position(), game.getBoard());
        for (int m = 0; m < 5; m++) {
            game.getExecutedMoves().add(move);
        }
        game.setUser(new UserDTO("Cuaddu", 2, 3, 4));
    }

    /**
     * Setting a user twice should cause an exception.
     */
    @Test(expected = IllegalArgumentException.class)
    public void setUser_overridingAUser() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        setPlayersUsers(game);
        game.setUser(new UserDTO("Willie", 1, 2, 3));
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

    /**
     * When a player has a user, the game should know that it has one.
     */
    @Test
    public void hasUserFor() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        game.setUser(new UserDTO("Charlie"));
        assertTrue(game.hasUserFor(Marker.X) || game.hasUserFor(Marker.O));
    }

    /**
     * Removing X player user should remove it as expected.
     */
    @Test
    public void removeUserFor_X() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        setPlayersUsers(game);
        game.removeUserFor(Marker.X);
        assertFalse(game.hasUserFor(Marker.X));
    }

    /**
     * Removing O player user should remove it as expected.
     */
    @Test
    public void removeUserFor_O() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        setPlayersUsers(game);
        game.removeUserFor(Marker.O);
        assertFalse(game.hasUserFor(Marker.O));
    }

    /**
     * Calling removeUserFor when the game has started should cause an exception.
     */
    @Test(expected = IllegalStateException.class)
    public void removeUserFor_gameIsStarted() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        setPlayersUsers(game);
        game.select(new PositionDTO(0, 0), new PositionDTO(0, 0));
        game.play();
        game.removeUserFor(Marker.O);
    }
    
}
