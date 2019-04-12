package atlg4.ultimate.g47923.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests the Player class.
 *
 * @author Logan Farci (47923)
 */
public class PlayerTest {

    @Test
    public void construction_x_player() {
        Player player = Player.X;
        assertEquals(Marker.X, player.getMarker());
        assertFalse(player.isWithdrawn());
    }

    @Test
    public void construction_o_player() {
        Player player = Player.O;
        assertEquals(Marker.O, player.getMarker());
        assertFalse(player.isWithdrawn());
    }

    /**
     * Setting the user should set the expected user.
     */
    @Test
    public void setUser() {
        Player player = Player.X;
        User user = new User("Totò", 2, 3 ,4);
        player.setUser(user);
        assertEquals(user.getPseudonym(), player.getUser().getPseudonym());
        assertTrue(player.hasUser());
    }

    @Test
    public void withdraw() {
        Player player = Player.O;
        player.setWithDrawn(true);
        assertTrue(player.isWithdrawn());
    }

}
