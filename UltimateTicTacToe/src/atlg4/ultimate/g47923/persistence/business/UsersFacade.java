package atlg4.ultimate.g47923.persistence.business;

import atlg4.ultimate.g47923.dto.UserDTO;
import atlg4.ultimate.g47923.exception.DataBaseException;
import atlg4.ultimate.g47923.persistence.db.DBManager;
import java.util.Collection;

/**
 * Methods of insertion, update and deletion in the USERS table in the ultimate
 * database.
 *
 * @author Logan Farci (47923)
 */
public class UsersFacade {

    /**
     * Adds the given user to the USERS table.
     *
     * @param user the given user.
     * @throws DataBaseException if an error occurs during the insertion.
     */
    public static void addUser(UserDTO user) throws DataBaseException {
        try {
            DBManager.startTransaction();
            UserBl.add(user);
            DBManager.validateTransaction();
        } catch (DataBaseException eDB) {
            try {
                DBManager.cancelTransaction();
            } catch (DataBaseException eCancel) {
                throw new DataBaseException("addUser(user), error while "
                        + "canceling the transaction because of: "
                        + eCancel.getMessage());
            }
            throw new DataBaseException("User with pseudonym " + user.getPseudonym()
                    + " cannot be found.");
        }
    }

    /**
     * Gets an user matching the given pseudonym in the USERS table. If no user
     * matches the given pseudonym, a new user is added to the data base.
     *
     * @param pseudonym the given pseudonym.
     * @return a player matching the given pseudonym.
     * @throws DataBaseException is a database access error occurs or if the
     * given user cannot be found.
     */
    public static UserDTO getUser(String pseudonym) {
        UserDTO user = null;
        try {
            DBManager.startTransaction();
            user = UsersFacade.findUserByPseudonym(pseudonym);
            if (user == null) {
                user = new UserDTO(pseudonym);
                addUser(user);
            }
            DBManager.validateTransaction();
        } catch (DataBaseException e) {
            try {
                DBManager.cancelTransaction();
            } catch (DataBaseException eCancel) {
                throw new DataBaseException("getUser(pseudonym), error while "
                        + "canceling the transaction because of: "
                        + eCancel.getMessage());
            }
            throw new DataBaseException("User with pseudonym " + pseudonym
                    + " cannot be found.");
        }
        return user;
    }

    /**
     * Finds a user matching the given pseudonym.
     *
     * @param pseudonym is the given pseudonym.
     * @return the user matching the given pseudonym.
     * @throws DataBaseException if a database access error occurs or if the
     * given user cannot be found.
     */
    private static UserDTO findUserByPseudonym(String pseudonym)
            throws DataBaseException {
        try {
            DBManager.startTransaction();
            UserDTO user = UserBl.findByPseudonym(pseudonym);
            DBManager.validateTransaction();
            return user;
        } catch (DataBaseException e) {
            try {
                DBManager.cancelTransaction();
            } catch (DataBaseException eCancel) {
                throw new DataBaseException("findUserByPseudonym(pseudonym), "
                        + "error while canceling the transaction because of: "
                        + eCancel.getMessage());
            }
            throw new DataBaseException("User with pseudonym " + pseudonym
                    + " cannot be found because of " + e.getMessage());
        }
    }

    /**
     * Gets the collection of users registered in the USERS table.
     *
     * @return the collection of users.
     * @throws DataBaseException if a database access error occurs or if the
     * given user cannot be found.
     */
    public static Collection<UserDTO> getUsers() throws DataBaseException {
        try {
            DBManager.startTransaction();
            Collection<UserDTO> users = UserBl.findAll();
            DBManager.validateTransaction();
            return users;
        } catch (DataBaseException eDB) {
            try {
                DBManager.cancelTransaction();
            } catch (DataBaseException eCancel) {
                throw new DataBaseException("getUsers(), error while "
                        + "canceling the transaction because of: "
                        + eCancel.getMessage());
            }
            throw new DataBaseException("Cannot get the collection of users"
                    + " because of: " + eDB.getMessage());
        }
    }

    /**
     * Updates the given user in the USERS table.
     *
     * @param user is the user to update.
     * @throws DataBaseException if a database access error occurs or if the
     * user cannot be updated.
     */
    public static void updateUser(UserDTO user) throws DataBaseException {
        try {
            DBManager.startTransaction();
            UserBl.setNbOfVictories(user.getPseudonym(), user.getNbOfVictories());
            UserBl.setNbOfExaequos(user.getPseudonym(), user.getNbOfTies());
            UserBl.setNbOfDefeats(user.getPseudonym(), user.getNbOfDefeats());
            DBManager.validateTransaction();
        } catch (DataBaseException e) {
            try {
                DBManager.cancelTransaction();
            } catch (DataBaseException eCancel) {
                throw new DataBaseException("updateUser(user), error while "
                        + "canceling the transaction because of: "
                        + eCancel.getMessage());
            }
            throw new DataBaseException("Cannot get the collection of users"
                    + " because of: " + e.getMessage());
        }
    }

}
