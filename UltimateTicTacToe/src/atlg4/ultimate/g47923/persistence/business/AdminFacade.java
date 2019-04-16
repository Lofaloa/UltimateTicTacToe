package atlg4.ultimate.g47923.persistence.business;

import atlg4.ultimate.g47923.dto.UserDTO;
import atlg4.ultimate.g47923.exception.UltimateTicTacToeDbException;
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
     * @throws UltimateTicTacToeDbException
     */
    public static void addUser(UserDTO user) throws UltimateTicTacToeDbException {
        try {
            DBManager.startTransaction();
            UserBl.add(user);
            DBManager.validateTransaction();
        } catch (UltimateTicTacToeDbException eDB) {
            String msg = eDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (UltimateTicTacToeDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new UltimateTicTacToeDbException(57, "User cannot be added\n" + msg);
            }
        }
    }

    /**
     * Finds the user matching the given pseudonym.
     *
     * @param pseudonym is the given pseudonym.
     * @return the user matching the given pseudonym.
     * @throws UltimateTicTacToeDbException
     */
    public static UserDTO findUserByPseudonym(String pseudonym)
            throws UltimateTicTacToeDbException {
        try {
            DBManager.startTransaction();
            UserDTO user = UserBl.findByPseudonym(pseudonym);
            DBManager.validateTransaction();
            return user;
        } catch (UltimateTicTacToeDbException eDB) {
            String msg = eDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (UltimateTicTacToeDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new UltimateTicTacToeDbException(58, "User not found \n" + msg);
            }
        }
    }

    public static Collection<UserDTO> getUsers() throws UltimateTicTacToeDbException {
        try {
            DBManager.startTransaction();
            Collection<UserDTO> users = UserBl.findAll();
            DBManager.validateTransaction();
            return users;
        } catch (UltimateTicTacToeDbException eDB) {
            String msg = eDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (UltimateTicTacToeDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new UltimateTicTacToeDbException(58, "User not found \n" + msg);
            }
        }
    }

}
