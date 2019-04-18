package atlg4.ultimate.g47923.persistence.business;

import atlg4.ultimate.g47923.dto.UserDTO;
import atlg4.ultimate.g47923.exception.DataBaseException;
import atlg4.ultimate.g47923.persistence.db.DBManager;
import java.util.Collection;

/**
 *
 * @author Logan Farci (47923)
 */
public class AdminFacade {

    /**
     * Adds the given user to the Users table.
     *
     * @param user the given user.
     * @throws DataBaseException
     */
    public static void addUser(UserDTO user) throws DataBaseException {
        try {
            DBManager.startTransaction();
            UserBl.add(user);
            DBManager.validateTransaction();
        } catch (DataBaseException eDB) {
            String msg = eDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DataBaseException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DataBaseException(57, "User cannot be added\n" + msg);
            }
        }
    }

    /**
     * Gets an user for the given pseudonym. If no user matches the given
     * pseudonym, a new user is added to the data base.
     *
     * @param pseudonym the given pseudonym.
     * @return a player matching the given pseudonym.
     */
    public static UserDTO getUser(String pseudonym) {
        UserDTO user = null;
        try {
            DBManager.startTransaction();
            user = AdminFacade.findUserByPseudonym(pseudonym);
            if (user == null) {
                user = new UserDTO(pseudonym);
                addUser(user);
            }
            DBManager.validateTransaction();
        } catch (DataBaseException e) {
            String msg = e.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DataBaseException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DataBaseException(58, "User not found \n" + msg);
            }
        }
        return user;
    }

    /**
     * Finds the user matching the given pseudonym.
     *
     * @param pseudonym is the given pseudonym.
     * @return the user matching the given pseudonym.
     * @throws DataBaseException
     */
    public static UserDTO findUserByPseudonym(String pseudonym)
            throws DataBaseException {
        try {
            DBManager.startTransaction();
            UserDTO user = UserBl.findByPseudonym(pseudonym);
            DBManager.validateTransaction();
            return user;
        } catch (DataBaseException eDB) {
            String msg = eDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DataBaseException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DataBaseException(58, "User not found \n" + msg);
            }
        }
    }

    public static Collection<UserDTO> getUsers() throws DataBaseException {
        try {
            DBManager.startTransaction();
            Collection<UserDTO> users = UserBl.findAll();
            DBManager.validateTransaction();
            return users;
        } catch (DataBaseException eDB) {
            String msg = eDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DataBaseException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DataBaseException(58, "User not found \n" + msg);
            }
        }
    }

    public static void updateUser(UserDTO user) {
        try {
            DBManager.startTransaction();
            UserBl.setNbOfVictories(user.getPseudonym(), user.getNbOfVictories());
            UserBl.setNbOfExaequos(user.getPseudonym(), user.getNbOfExaequos());
            UserBl.setNbOfDefeats(user.getPseudonym(), user.getNbOfDefeats());
            DBManager.validateTransaction();
        } catch (DataBaseException e) {
            String msg = e.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DataBaseException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DataBaseException(58, "User not found \n" + msg);
            }
        }
    }

}
