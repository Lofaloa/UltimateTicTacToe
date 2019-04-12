package atlg4.ultimate.g47923.model;

import atlg4.ultimate.g47923.dto.UserDTO;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Tests the User class.
 *
 * @author Logan Farci (47923)
 */
public class UserTest {

    /**
     * A user should be constructed by values as expected.
     */
    @Test
    public void construction_byValues() {
        User user = new User("Bob", 1, 2, 3);
        assertEquals("Bob", user.getPseudonym());
        assertEquals(1, user.getNbOfVictories());
        assertEquals(2, user.getNbOfExaequos());
        assertEquals(3, user.getNbOfDefeats());
    }

    /**
     * A user should be constructed by a data transfer object.
     */
    @Test
    public void construction_byDataTransferObject() {
        UserDTO dto = new UserDTO("Bob", 1, 2, 3);
        User user = new User(dto);
        assertEquals("Bob", user.getPseudonym());
        assertEquals(1, user.getNbOfVictories());
        assertEquals(2, user.getNbOfExaequos());
        assertEquals(3, user.getNbOfDefeats());
    }

    /**
     * The number of victories should actually be incremented after adding a
     * victory.
     */
    @Test
    public void addAVictory() {
        int nvictories = 2;
        User user = new User("Bob", nvictories, 2, 3);
        user.addAVictory();
        assertEquals(nvictories + 1, user.getNbOfVictories());
    }

    /**
     * The number of victories should actually be incremented after adding an ex
     * aequo.
     */
    @Test
    public void addAnExaequo() {
        int nexaequos = 2;
        User user = new User("Bob", 2, nexaequos, 3);
        user.addAnExaequo();
        assertEquals(nexaequos + 1, user.getNbOfExaequos());
    }

    /**
     * The number of victories should actually be incremented after adding a
     * defeat.
     */
    @Test
    public void addADefeat() {
        int ndefeats = 2;
        User user = new User("Bob", 2, 2, ndefeats);
        user.addADefeat();
        assertEquals(ndefeats + 1, user.getNbOfDefeats());
    }

}
