package atlg4.ultimate.g47923.persistence.business;

import atlg4.ultimate.g47923.dto.UserDTO;
import atlg4.ultimate.g47923.exception.DataBaseException;
import atlg4.ultimate.g47923.persistence.db.UserDB;
import atlg4.ultimate.g47923.persistence.seldto.UserSel;
import java.util.Collection;

/**
 * Class containing methods that manages Users table.
 *
 * @author Logan Farci (47923)
 */
class UserBl {

    static void add(UserDTO user) throws DataBaseException {
        UserDB.insertDb(user);
    }

    static void setNbOfVictories(String pseudonym, int nvictories)
            throws DataBaseException {
        UserDB.setNbOfVictories(pseudonym, nvictories);
    }

    static void setNbOfExaequos(String pseudonym, int nexaequos)
            throws DataBaseException {
        UserDB.setNbOfExaequos(pseudonym, nexaequos);
    }

    static void setNbOfDefeats(String pseudonym, int nvictories)
            throws DataBaseException {
        UserDB.setNbOfDefeats(pseudonym, nvictories);
    }

    static void delete(String pseudonym) throws DataBaseException {
        UserDB.deleteDb(pseudonym);
    }

    static UserDTO findByPseudonym(String pseudonym)
            throws DataBaseException {
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

    static Collection<UserDTO> findAll() throws DataBaseException {
        UserSel sel = new UserSel(null);
        Collection<UserDTO> users = UserDB.getCollection(sel);
        return users;
    }

}
