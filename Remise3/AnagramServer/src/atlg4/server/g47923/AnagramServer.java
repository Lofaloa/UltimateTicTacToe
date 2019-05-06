package atlg4.server.g47923;

import anagram.exception.ModelException;
import anagram.model.Facade;
import anagram.model.Model;
import atlg4.g47923.anagram.message.Message;
import atlg4.g47923.anagram.message.PlayersMessage;
import atlg4.g47923.anagram.message.ProfileMessage;
import atlg4.g47923.anagram.message.ProposalMessage;
import atlg4.g47923.anagram.message.WordMessage;
import atlg4.g47923.anagram.players.Players;
import atlg4.g47923.anagram.players.User;
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
    private final Players players;
    private final Facade anagram;

    /**
     * Constructs the server. Build a thread to listen connection request.
     *
     * @throws IOException if an I/O error occurs when creating the server
     * socket.
     */
    public AnagramServer() throws IOException, ModelException {
        super(PORT);
        this.currentClientId = 0;
        this.players = new Players();
        this.anagram = new Model();
        anagram.initialize();
        anagram.start();
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
     * Gets the players connected to this server.
     *
     * @return the players connected to this server.
     */
    public Players getPlayers() {
        return players;
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
                handle((ProfileMessage) message, client);
                break;
            case PROPOSAL:
                handle((ProposalMessage) message, client);
                break;
            case WORD:
                break;
            case PLAYERS:
                break;
            default:
                throw new IllegalArgumentException("Message type unkown: "
                        + message.getType());
        }
        setChanged();
        notifyObservers(message);
    }

    @Override
    protected void clientConnected(ConnectionToClient client) {
        super.clientConnected(client);
        int playerID = players.add(getNextId(), client.getName(), client.getInetAddress());
        client.setInfo("ID", playerID);
        sendToAllClients(new PlayersMessage(players));
        setChanged();
        notifyObservers();
    }

    @Override
    protected synchronized void clientException(ConnectionToClient client, Throwable exception) {
        super.clientException(client, exception);
        // envoie d'exception au client
    }

    void sendToClient(Message message, User recipient) {
        sendToClient(message, recipient.getId());
    }

    void sendToClient(Message message, int clientId) {
        Thread connection = null;
        for (Thread clientConnection : getClientConnections()) {
            ConnectionToClient client = (ConnectionToClient) clientConnection;
            if (((int) client.getInfo("ID")) == clientId) {
                connection = clientConnection;
            }
        }
        if (connection == null) {
            throw new IllegalArgumentException("The client with id " + clientId
                    + " does not exist.");
        } else {
            try {
                ((ConnectionToClient) connection).sendToClient(message);
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }

    private void handle(ProfileMessage message, ConnectionToClient client) {
        int playerId = (int) client.getInfo("ID");
        User author = message.getAuthor();
        players.changeName(author.getName(), playerId);
        Message messageName = new ProfileMessage(playerId, author.getName());

        Message word = null;
        try {
            word = new WordMessage(playerId, author.getName(), anagram.nextWord());
        } catch (ModelException ex) {
            Logger.getLogger(AnagramServer.class.getName()).log(Level.SEVERE, null, ex);
        }

        sendToClient(messageName, playerId);
        sendToClient(word, playerId);
        sendToAllClients(new PlayersMessage(players));
    }

    private void handle(ProposalMessage proposal, ConnectionToClient client) {
        try {
            if (anagram.propose((String) proposal.getContent())) {
                WordMessage nextWord = new WordMessage(
                        proposal.getAuthor().getId(), 
                        proposal.getAuthor().getName(),
                        anagram.nextWord());
                sendToClient(nextWord, proposal.getAuthor().getId());
            } else {
                System.out.println("FAILURE");
            }
        } catch (ModelException ex) {
            clientException(client, ex);
        }
    }

}
