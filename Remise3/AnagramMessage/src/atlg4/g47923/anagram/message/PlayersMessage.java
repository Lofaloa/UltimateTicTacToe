package atlg4.g47923.anagram.message;

import atlg4.g47923.anagram.players.Players;
import atlg4.g47923.anagram.players.User;

/**
 * The <code> Message </code> represents a message with the list of all
 * connected players.
 */
public class PlayersMessage implements Message {

    private final Players players;

    /**
     * Constructs message with the list of all connected users.
     *
     * @param members list of all connected users.
     */
    public PlayersMessage(Players members) {
        this.players = members;
    }

    /**
     * Gets the administrator. The author of a message with all the connected
     * users is the administrator.
     *
     * @return the administrator.
     */
    @Override
    public User getAuthor() {
        return User.ADMIN;
    }

    /**
     * Return the recipient of the message, in this case User.EVERYBODY.
     *
     * @return the recipient of the message, in this case User.EVERYBODY.
     */
    @Override
    public User getRecipient() {
        return User.EVERYBODY;
    }

    /**
     * Return the type of the message, in this case Type.PLAYERS.
     *
     * @return the type of the message, in this case Type.PLAYERS.
     */
    @Override
    public Type getType() {
        return Type.PLAYERS;
    }

    /**
     * Return the content of the message : the list of all connected users.
     *
     * @return the content of the message : the list of all connected users.
     */
    @Override
    public Object getContent() {
        return players;
    }

}
