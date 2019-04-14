package atlg4.ultimate.g47923.persistence.db;

import atlg4.ultimate.g47923.exception.UltimateTicTacToeDbException;
import java.sql.*;

/**
 * Manages the data base connection and the transactions.
 */
public class DBManager {

    private static Connection connection;
    private static final String DATA_BASE_URL = "jdbc:derby://localhost:1527/ultimate";

    /**
     * Gets the connection to the data base.
     *
     * @return the connection.
     */
    public static Connection getConnection() throws UltimateTicTacToeDbException {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(DATA_BASE_URL, "app", "app");
            } catch (SQLException ex) {
                throw new UltimateTicTacToeDbException(50, "Cannot connect: " + ex.getMessage());
            }
        }
        return connection;
    }

    /**
     * Starts a transaction.
     */
    public static void startTransaction() throws UltimateTicTacToeDbException {
        try {
            getConnection().setAutoCommit(false);
        } catch (SQLException ex) {
            throw new UltimateTicTacToeDbException(51, "Cannot start the transaction: " + ex.getMessage());
        }
    }

    /**
     * Validates the transaction.
     */
    public static void validateTransaction() throws UltimateTicTacToeDbException {
        try {
            getConnection().commit();
            getConnection().setAutoCommit(true);
        } catch (SQLException ex) {
            throw new UltimateTicTacToeDbException(52, "Cannot validate the transaction: " + ex.getMessage());
        }
    }

    /**
     * Cancels the transaction.
     */
    public static void cancelTransaction() throws UltimateTicTacToeDbException {
        try {
            getConnection().rollback();
            getConnection().setAutoCommit(true);
        } catch (SQLException ex) {
            throw new UltimateTicTacToeDbException(53, "Cannot cancel the transaction: " + ex.getMessage());
        }
    }
}
