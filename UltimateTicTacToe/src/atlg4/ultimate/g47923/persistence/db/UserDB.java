package atlg4.ultimate.g47923.persistence.db;

import atlg4.ultimate.g47923.dto.UserDTO;
import atlg4.ultimate.g47923.exception.UltimateTicTacToeDbException;
import atlg4.ultimate.g47923.persistence.seldto.UserSel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * @author Logan Farci (47923)
 */
public class UserDB {

    /**
     * Gets all the users of the Users table.
     *
     * @return all the users of the Users table.
     * @throws UltimateTicTacToeDbException if it is not possible to get the
     * collection of users.
     */
    public static List<UserDTO> getAllUsers() throws UltimateTicTacToeDbException {
        List<UserDTO> users = getCollection(new UserSel(null));
        return users;
    }

    /**
     * Gets all the users matching the given selection.
     *
     * @param sel is the given selection.
     * @return all the users matching the given selection.
     * @throws UltimateTicTacToeDbException
     */
    public static List<UserDTO> getCollection(UserSel sel) throws UltimateTicTacToeDbException {
        List<UserDTO> collection = new ArrayList<>();
        try {
            String query = "SELECT pseudonym, nvictories, nexaequos, ndefeats FROM Users ";
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement stmt;
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
                stmt = connexion.prepareStatement(query);
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
                stmt = connexion.prepareStatement(query);
            }
            java.sql.ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                collection.add(new UserDTO(
                        rs.getString("pseudonym"),
                        rs.getInt("nvictories"),
                        rs.getInt("nexaequos"),
                        rs.getInt("ndefeats")
                ));
            }
        } catch (UltimateTicTacToeDbException ex) {
            throw new UltimateTicTacToeDbException(54, "Instanciation de User impossible:\nDTOException: " + ex.getMessage());
        } catch (java.sql.SQLException eSQL) {
            throw new UltimateTicTacToeDbException(55, "Instanciation de User impossible:\nSQLException: " + eSQL.getMessage());
        }
        return collection;
    }

    /**
     * Inserts the given user in the Users table.
     *
     * @param user is the given user.
     * @throws UltimateTicTacToeDbException
     */
    public static void insertDb(UserDTO user) throws UltimateTicTacToeDbException {
        try {
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement insert;
            insert = connexion.prepareStatement(
                    "INSERT INTO Users(pseudonym, nvictories, nexaequos, ndefeats) "
                    + "VALUES(?, ?, ?, ?)"
            );
            insert.setString(1, user.getPseudonym());
            insert.setInt(2, user.getNbOfVictories());
            insert.setInt(3, user.getNbOfExaequos());
            insert.setInt(4, user.getNbOfDefeats());
            insert.executeUpdate();
        } catch (Exception ex) {
            throw new UltimateTicTacToeDbException(56, "Users: cannot insert\n" + ex.getMessage());
        }
    }

    public static void setNbOfVictories(String pseudonym, int nvictories)
            throws UltimateTicTacToeDbException {
        try {
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement update;
            update = connexion.prepareStatement(
                    "UPDATE Users SET nvictories=? WHERE pseudonym=?"
            );
            update.setInt(1, nvictories);
            update.setString(2, pseudonym);
            update.executeUpdate();
        } catch (Exception ex) {
            throw new UltimateTicTacToeDbException(57, "Cannot set the number of victories in Users:\n" + ex.getMessage());
        }
    }

    public static void setNbOfExaequos(String pseudonym, int nexaequos)
            throws UltimateTicTacToeDbException {
        try {
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement update;
            update = connexion.prepareStatement(
                    "UPDATE Users SET nexaequos=? WHERE pseudonym=?"
            );
            update.setInt(1, nexaequos);
            update.setString(2, pseudonym);
            update.executeUpdate();
        } catch (Exception ex) {
            throw new UltimateTicTacToeDbException(57, "Cannot set the number of victories in Users:\n" + ex.getMessage());
        }
    }

    public static void setNbOfDefeats(String pseudonym, int ndefeats)
            throws UltimateTicTacToeDbException {
        try {
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement update;
            update = connexion.prepareStatement(
                    "UPDATE Users SET ndefeats=? WHERE pseudonym=?"
            );
            update.setInt(1, ndefeats);
            update.setString(2, pseudonym);
            update.executeUpdate();
        } catch (Exception ex) {
            throw new UltimateTicTacToeDbException(57, "Cannot set the number of victories in Users:\n" + ex.getMessage());
        }
    }

    /**
     * Deletes the the user matching the given pseudonym.
     *
     * @param pseudonym is the given pseudonym.
     * @throws UltimateTicTacToeDbException
     */
    public static void deleteDb(String pseudonym) throws UltimateTicTacToeDbException {
        try {
            java.sql.Statement stmt = DBManager.getConnection().createStatement();
            stmt.execute("DELETE FROM Users WHERE pseudonym=" + pseudonym);
        } catch (Exception ex) {
            throw new UltimateTicTacToeDbException(56, "Client: suppression impossible\n" + ex.getMessage());
        }
    }

}
