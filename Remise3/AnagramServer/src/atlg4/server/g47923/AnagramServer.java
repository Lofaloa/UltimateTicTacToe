package atlg4.server.g47923;

import atlg4.g47923.anagram.message.Message;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * AnagramServer is used to handle the connections of the clients during a game
 * of Anagram.
 *
 * @author Logan Farci (47923)
 */
public class AnagramServer extends AbstractServer {

    private static final int PORT = 12_345;

    private static InetAddress getLocalAddress() {
        try {
            Enumeration<NetworkInterface> b = NetworkInterface.getNetworkInterfaces();
            while (b.hasMoreElements()) {
                for (InterfaceAddress f : b.nextElement().getInterfaceAddresses()) {
                    if (f.getAddress().isSiteLocalAddress()) {
                        return f.getAddress();
                    }
                }
            }
        } catch (SocketException e) {
            Logger.getLogger(AnagramServer.class.getName()).log(Level.SEVERE,
                    "NetworkInterface error", e);
        }
        return null;
    }

    private int currentClientId;

    /**
     * Constructs the server. Build a thread to listen connection request.
     *
     * @throws IOException if an I/O error occurs when creating the server
     * socket.
     */
    public AnagramServer() throws IOException {
        super(PORT);
        this.currentClientId = 0;
        this.listen();
    }

    /**
     * Gets the server IP address.
     *
     * @return the server IP address.
     */
    public String getIP() {
        if (getLocalAddress() == null) {
            return "Unknown";
        }
        return getLocalAddress().getHostAddress();
    }

    /**
     * Return the number of connected users.
     *
     * @return the number of connected users.
     */
    public int getNbConnected() {
        return getNumberOfClients();
    }

    /**
     * Quits the server and closes all aspects of the connection to clients.
     *
     * @throws IOException
     */
    public void quit() throws IOException {
        this.stopListening();
        this.close();
    }

    /**
     * Return the next client id.
     *
     * @return the next client id.
     */
    final synchronized int getNextId() {
        currentClientId++;
        return currentClientId;
    }

    @Override
    protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
        Message message = (Message) msg;
        switch (message.getType()) {
            case PROFILE:
                // new client connection
                break;
            case PROPOSAL:
                // proposal validation
                break;
            case PLAYERS:
                break;
            default:
                throw new IllegalArgumentException("Message type unkown: "
                        + message.getType());
        }
    }

    @Override
    protected void clientConnected(ConnectionToClient client) {
        // Exécuté à la connexion d'un client
        super.clientConnected(client);

    }

    @Override
    protected synchronized void clientException(ConnectionToClient client, Throwable exception) {
        super.clientException(client, exception);
        // envoie d'exception au client
    }

}
