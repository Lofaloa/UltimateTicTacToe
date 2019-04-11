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

    @Test
    public void withdraw() {
        Player player = Player.O;
        player.setWithDrawn(true);
        assertTrue(player.isWithdrawn());
    }

}
