package atlg4.ultimate.g47923.persistence.db;

import atlg4.ultimate.g47923.exception.DataBaseException;
import java.sql.*;

/**
 * Manages the data base connection and transactions.
 */
public class DBManager {

    private static Connection CONNECTION;
    private static final String DATABASE_URL = "jdbc:derby://localhost:1527/ultimate";

    /**
     * Gets the connection to the data base.
     *
     * @return the connection to the data base.
     * @throws DataBaseException if a database access error occurs.
     */
    public static Connection getConnection() throws DataBaseException {
        if (CONNECTION == null) {
            try {
                CONNECTION = DriverManager.getConnection(DATABASE_URL, "app", "app");
            } catch (SQLException ex) {
                throw new DataBaseException(50, "Cannot acces the database: "
                        + ex.getMessage());
            }
        }
        return CONNECTION;
    }

    /**
     * Starts a transaction.
     *
     * @throws DataBaseException if a database access error occurs, if it is
     * called while participating in a distributed transaction, or if this
     * manager connection has been closed.
     */
    public static void startTransaction() throws DataBaseException {
        try {
            getConnection().setAutoCommit(false);
        } catch (SQLException ex) {
            throw new DataBaseException(51, "Cannot start the transaction: "
                    + ex.getMessage());
        }
    }

    /**
     * Validates the transaction.
     *
     * @throws DataBaseException if a database access error occurs, this method
     * is called while participating in a distributed transaction, if this
     * method is called on a closed connection or this Manager Connection object
     * is in auto-commit mode
     */
    public static void validateTransaction() throws DataBaseException {
        try {
            getConnection().commit();
            getConnection().setAutoCommit(true);
        } catch (SQLException ex) {
            throw new DataBaseException(52, "Cannot validate the transaction: "
                    + ex.getMessage());
        }
    }

    /**
     * Cancels the transaction.
     *
     * @throws DataBaseException if a database access error occurs, this method
     * is called while participating in a distributed transaction, this method
     * is called when this manager Connection is closed or the Connection object
     * is in auto-commit mode
     */
    public static void cancelTransaction() throws DataBaseException {
        try {
            getConnection().rollback();
            getConnection().setAutoCommit(true);
        } catch (SQLException ex) {
            throw new DataBaseException(53, "Cannot cancel the transaction: "
                    + ex.getMessage());
        }
    }
}
