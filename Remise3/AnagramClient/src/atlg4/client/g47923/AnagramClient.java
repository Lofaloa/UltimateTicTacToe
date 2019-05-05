package atlg4.client.g47923;

import atlg4.g47923.anagram.message.Message;
import atlg4.g47923.anagram.message.ProfileMessage;
import atlg4.g47923.anagram.message.Type;
import atlg4.g47923.anagram.players.Players;
import atlg4.g47923.anagram.players.User;
import java.io.IOException;

/**
 * The <code> ChatClient </code> contains all the methods necessary to set up a
 * Instant messaging client.
 */
public class AnagramClient extends AbstractClient {

    private final Players players;
    private User mySelf;

    /**
     * Constructs the client. Opens the connection with the server. Sends the
     * user name inside a <code> MessageProfile </code> to the server. Builds an
     * empty list of users.
     *
     * @param host the server's host name.
     * @param port the port number.
     * @param name the name of the user.
     * @param password the password needed to connect.
     * @throws IOException if an I/O error occurs when opening.
     */
    public AnagramClient(String host, int port, String name, String password) throws IOException {
        super(host, port);
        openConnection();
        updateName(name);
        players = new Players();
    }

    @Override
    protected void handleMessageFromServer(Object msg) {
        Message message = (Message) msg;
        Type type = message.getType();
        switch (type) {
            case PROFILE:
                // on doit recevoir son profile je pense, on se connecte, 
                // le serveur nous renvoie notre profile
                break;
            case PROPOSAL:
                // je pense que l'on doit rien faire: le serveur n'envoit pas
                // de proposal
                break;
            case PLAYERS:
                // ici on doit stocker la liste des joueurs
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
     * Return the user with the given id.
     *
     * @param id of the user.
     * @return the user with the given id.
     */
    public User getUsers(int id) {
        return players.getUser(id);
    }

    /**
     * Return the user.
     *
     * @return the user.
     */
    public User getMySelf() {
        return mySelf;
    }

    void setMySelf(User user) {
        this.mySelf = user;
    }

    void updatePlayers(Players players) {
        this.players.clear();
        for (User member : players) {
            this.players.add(member);
        }
        notifyChange();
    }

    void showMessage(Message message) {
        notifyView(message);
    }

    private void updateName(String name) throws IOException {
        sendToServer(new ProfileMessage(0, name));
    }

    private void notifyChange() {
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
