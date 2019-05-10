package atlg4.client.g47923;

import atlg4.g47923.anagram.message.Message;
import atlg4.g47923.anagram.message.PassCurrentWordMessage;
import atlg4.g47923.anagram.message.ProfileMessage;
import atlg4.g47923.anagram.message.ProposalMessage;
import atlg4.g47923.anagram.message.Type;
import atlg4.g47923.anagram.players.Players;
import atlg4.g47923.anagram.players.User;
import java.io.IOException;

/**
 * The <code> AnagramClient </code> contains all the methods necessary to set up
 * an Anagram game client.
 */
public class AnagramClient extends AbstractClient {

    static private final String DEFAULT_HOST = "localhost";
    static private final int DEFAULT_PORT = 12345;

    private final Players players;
    private User mySelf;

    /**
     * Constructs the client.Builds an empty list of users.
     *
     * @throws IOException if an I/O error occurs when opening.
     */
    public AnagramClient() throws IOException {
        super(DEFAULT_HOST, DEFAULT_PORT);
        players = new Players();
    }

    @Override
    protected void handleMessageFromServer(Object msg) {
        Message message = (Message) msg;
        Type type = message.getType();
        switch (type) {
            case PROFILE:
                setMySelf(message.getAuthor());
                break;
            case PROPOSAL:
                break;
            case WORD:
            case ANSWER:
            case STATISTICS:
            case FAILURE:
            case END_OF_GAME:
                showMessage(message);
                break;
            case PASS_CURRENT_WORD:
                break;
            case PLAYERS:
                Players players = (Players) message.getContent();
                updatePlayers(players);
                break;
            default:
                throw new IllegalArgumentException("Message type unknown " + type);
        }
    }

    /**
     * Quits the client and closes all aspects of the connection to the server.
     *
     * @throws IOException if an I/O error occurs when closing.
     */
    public void quit() throws IOException {
        closeConnection();
    }

    /**
     * Return all the connected users.
     *
     * @return all the connected users.
     */
    public Players getPlayers() {
        return players;
    }

    /**
     * Return the user.
     *
     * @return the user.
     */
    public User getMySelf() {
        return mySelf;
    }

    /**
     * Connects this client to the given host and sends the given login to the
     * server.
     *
     * @param host is the host of the server.
     * @param port is the port of the server.
     * @param login is the login of the user.
     */
    public void connect(String host, int port, String login) throws IOException {
        setHost(host);
        setPort(port);
        openConnection();
        updateName(host);
    }

    /**
     * Sends the given proposal to the server.
     *
     * @param proposal is the given proposal.
     */
    public void sendProposal(String proposal) throws IOException {
        if (proposal == null || proposal.isEmpty()) {
            throw new IllegalArgumentException("Pas de proposition donnée!");
        }
        Message message = new ProposalMessage(
                proposal,
                mySelf.getId(),
                mySelf.getName()
        );
        sendToServer(message);
    }

    /**
     * Sends a message asking the server to pass to the next word.
     */
    public void sendPassCurrentWord() throws IOException {
        Message message = new PassCurrentWordMessage(
                mySelf.getId(),
                mySelf.getName()
        );
        sendToServer(message);
    }

    void setMySelf(User user) {
        this.mySelf = user;
    }

    void updatePlayers(Players players) {
        this.players.clear();
        for (User member : players) {
            this.players.add(member);
        }
        notifyView();
    }

    void showMessage(Message message) {
        notifyView(message);
    }

    private void updateName(String name) throws IOException {
        sendToServer(new ProfileMessage(0, name));
    }

    private void notifyView() {
        setChanged();
        notifyObservers();
    }

    private void notifyView(Message message) {
        setChanged();
        notifyObservers(message);
    }

    /**
     * Return the numbers of connected users.
     *
     * @return the numbers of connected users.
     */
    public int getNbConnected() {
        return players.size();
    }

}
