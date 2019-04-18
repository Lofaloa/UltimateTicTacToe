package atlg4.ultimate.g47923.persistence.business;

import atlg4.ultimate.g47923.dto.UserDTO;
import atlg4.ultimate.g47923.exception.UltimateTicTacToeDbException;
import atlg4.ultimate.g47923.persistence.db.UserDB;
import atlg4.ultimate.g47923.persistence.seldto.UserSel;
import java.util.Collection;

/**
 * Class containing methods that manages Users table.
 *
 * @author Logan Farci (47923)
 */
class UserBl {

    static void add(UserDTO user) throws UltimateTicTacToeDbException {
        UserDB.insertDb(user);
    }

    static void setNbOfVictories(String pseudonym, int nvictories)
            throws UltimateTicTacToeDbException {
        UserDB.setNbOfVictories(pseudonym, nvictories);
    }

    static void setNbOfExaequos(String pseudonym, int nexaequos)
            throws UltimateTicTacToeDbException {
        UserDB.setNbOfExaequos(pseudonym, nexaequos);
    }

    static void setNbOfDefeats(String pseudonym, int nvictories)
            throws UltimateTicTacToeDbException {
        UserDB.setNbOfDefeats(pseudonym, nvictories);
    }

    static void delete(String pseudonym) throws UltimateTicTacToeDbException {
        UserDB.deleteDb(pseudonym);
    }

    static UserDTO findByPseudonym(String pseudonym)
            throws UltimateTicTacToeDbException {
        UserDTO user;
        UserSel sel = new UserSel(pseudonym);
        Collection<UserDTO> users = UserDB.getCollection(sel);
        if (users.size() == 1) {
            user = users.iterator().next();
        } else {
            user = null;
        }
        return user;
    }

    static Collection<UserDTO> findAll() throws UltimateTicTacToeDbException {
        UserSel sel = new UserSel(null);
        Collection<UserDTO> users = UserDB.getCollection(sel);
        return users;
    }

}
