package atlg4.ultimate.g47923.persistence.db;

import atlg4.ultimate.g47923.dto.UserDTO;
import atlg4.ultimate.g47923.exception.DataBaseException;
import atlg4.ultimate.g47923.persistence.seldto.UserSel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Manages operations made in the USERS table of the ultimate database.
 *
 * @author Logan Farci (47923)
 */
public class UserDB {

    /**
     * Gets all the users matching the given selection.
     *
     * @param sel is the given selection.
     * @return all the users matching the given selection.
     * @throws DataBaseException if a database access error occurs or if the
     * selection cannot be executed.
     */
    public static Collection<UserDTO> getCollection(UserSel sel) throws DataBaseException {
        List<UserDTO> collection = new ArrayList<>();
        try {
            String query = "SELECT pseudonym, nvictories, nexaequos, ndefeats "
                    + "FROM Users ";
            Connection connection = DBManager.getConnection();
            PreparedStatement stmt;
            String where = "";
            if (sel.getPseudonym() != null) {
                if (!where.equals("")) {
                    where = where + " AND ";
                }
                where = where + " pseudonym = ? ";
            }
            if (where.length() != 0) {
                where = " WHERE " + where + " ORDER BY pseudonym";
                query = query + where;
                stmt = connection.prepareStatement(query);
                int i = 1;
                if (sel.getPseudonym() != null) {
                    stmt.setString(i, sel.getPseudonym());
                    i++;
                }
                if (sel.getNbOfVictories() >= 0) {
                    stmt.setInt(i, sel.getNbOfVictories());
                    i++;
                }
                if (sel.getNbOfExaequos() >= 0) {
                    stmt.setInt(i, sel.getNbOfExaequos());
                    i++;
                }
                if (sel.getNbOfDefeats() >= 0) {
                    stmt.setInt(i, sel.getNbOfDefeats());
                    i++;
                }
            } else {
                query = query + " ORDER BY pseudonym";
                stmt = connection.prepareStatement(query);
            }
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                collection.add(new UserDTO(
                        rs.getString("pseudonym"),
                        rs.getInt("nvictories"),
                        rs.getInt("nexaequos"),
                        rs.getInt("ndefeats")
                ));
            }
        } catch (DataBaseException e) {
            throw new DataBaseException("Cannot access the table because of "
                    + "DataBaseException: " + e.getMessage());
        } catch (SQLException eSQL) {
            throw new DataBaseException("Cannot get the collection of "
                    + "users: " + eSQL.getMessage());
        }
        return collection;
    }

    /**
     * Gets all the users registered in the USERS table.
     *
     * @return all the users of the USERS table.
     * @throws DataBaseException if a database access error occurs or if the
     * selection cannot be executed.
     */
    public static Collection<UserDTO> getAllUsers() throws DataBaseException {
        Collection<UserDTO> users = getCollection(new UserSel(null));
        return users;
    }

    /**
     * Inserts the given user in the USERS table.
     *
     * @param user is the given user data transfer object.
     * @throws DataBaseException if a database access error occurs or if the
     * insertion cannot be executed.
     */
    public static void insertDb(UserDTO user) throws DataBaseException {
        try {
            Connection connexion = DBManager.getConnection();
            PreparedStatement insert;
            insert = connexion.prepareStatement(
                    "INSERT INTO Users(pseudonym, nvictories, nexaequos, ndefeats) "
                    + "VALUES(?, ?, ?, ?)"
            );
            insert.setString(1, user.getPseudonym());
            insert.setInt(2, user.getNbOfVictories());
            insert.setInt(3, user.getNbOfTies());
            insert.setInt(4, user.getNbOfDefeats());
            insert.executeUpdate();
        } catch (DataBaseException e) {
            throw new DataBaseException("Cannot access the table because of "
                    + "DataBaseException: " + e.getMessage());
        } catch (SQLException eSQL) {
            throw new DataBaseException("Cannot execute the insertion of user "
                    + user.getPseudonym() + " in USERS table.");
        }
    }

    /**
     * Sets the number of victories for the user corresponding to the given
     * pseudonym.
     *
     * @param pseudonym the pseudonym of the user to update.
     * @param nvictories the number of victories of the user to update.
     * @throws DataBaseException if a database access error occurs or if the
     * update cannot be executed.
     */
    public static void setNbOfVictories(String pseudonym, int nvictories)
            throws DataBaseException {
        try {
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement update;
            update = connexion.prepareStatement(
                    "UPDATE Users SET nvictories=? WHERE pseudonym=?"
            );
            update.setInt(1, nvictories);
            update.setString(2, pseudonym);
            update.executeUpdate();
        } catch (DataBaseException e) {
            throw new DataBaseException("Cannot access the table because of "
                    + "DataBaseException: " + e.getMessage());
        } catch (SQLException eSQL) {
            throw new DataBaseException("Cannot execute the update of user "
                    + pseudonym + " in USERS table.");
        }
    }

    /**
     * Sets the number of ex aequos for the user corresponding to the given
     * pseudonym.
     *
     * @param pseudonym the pseudonym of the user to update.
     * @param nexaequos the number of ex aequos of the user to update.
     * @throws DataBaseException if a database access error occurs or if the
     * update cannot be executed.
     */
    public static void setNbOfExaequos(String pseudonym, int nexaequos)
            throws DataBaseException {
        try {
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement update;
            update = connexion.prepareStatement(
                    "UPDATE Users SET nexaequos=? WHERE pseudonym=?"
            );
            update.setInt(1, nexaequos);
            update.setString(2, pseudonym);
            update.executeUpdate();
        } catch (DataBaseException e) {
            throw new DataBaseException("Cannot access the table because of "
                    + "DataBaseException: " + e.getMessage());
        } catch (SQLException eSQL) {
            throw new DataBaseException("Cannot execute the update of user "
                    + pseudonym + " in USERS table.");
        }
    }

    /**
     * Sets the number of ndefeats for the user corresponding to the given
     * pseudonym.
     *
     * @param pseudonym the pseudonym of the user to update.
     * @param ndefeats the number of defeats of the user to update.
     * @throws DataBaseException if a database access error occurs or if the
     * update cannot be executed.
     */
    public static void setNbOfDefeats(String pseudonym, int ndefeats)
            throws DataBaseException {
        try {
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement update;
            update = connexion.prepareStatement(
                    "UPDATE Users SET ndefeats=? WHERE pseudonym=?"
            );
            update.setInt(1, ndefeats);
            update.setString(2, pseudonym);
            update.executeUpdate();
        } catch (DataBaseException e) {
            throw new DataBaseException("Cannot access the table because of "
                    + "DataBaseException: " + e.getMessage());
        } catch (SQLException eSQL) {
            throw new DataBaseException("Cannot execute the update of user "
                    + pseudonym + " in USERS table.");
        }
    }

    /**
     * Deletes the the user matching the given pseudonym.
     *
     * @param pseudonym is the given pseudonym.
     * @throws DataBaseException
     */
    public static void deleteDb(String pseudonym) throws DataBaseException {
        try {
            java.sql.Statement stmt = DBManager.getConnection().createStatement();
            stmt.execute("DELETE FROM Users WHERE pseudonym=" + pseudonym);
        }  catch (DataBaseException e) {
            throw new DataBaseException("Cannot access the table because of "
                    + "DataBaseException: " + e.getMessage());
        } catch (SQLException eSQL) {
            throw new DataBaseException("Cannot execute the deletion of user "
                    + pseudonym + " in USERS table.");
        }
    }

}
