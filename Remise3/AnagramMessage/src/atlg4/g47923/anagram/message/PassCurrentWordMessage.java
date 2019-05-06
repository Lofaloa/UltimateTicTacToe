package atlg4.g47923.anagram.message;

import atlg4.g47923.anagram.players.User;

/**
 * The <code> Message </code> represents a message informing the server that the
 * author client has passed the current word.
 */
public class PassWordMessage implements Message {

    private final User author;

    /**
     * Constructs a message used to inform the server that the author has passed
     * the current word.
     *
     * @param id is the id this message author.
     * @param name is the name of this message author.
     */
    public PassWordMessage(int id, String name) {
        this.author = new User(id, name);
    }

    /**
     * Gets this message author.
     *
     * @return this message author.
     */
    @Override
    public User getAuthor() {
        return author;
    }

    /**
     * Return the recipient of the message, in this case User.ADMIN.
     *
     * @return the recipient of the message, in this case User.ADMIN.
     */
    @Override
    public User getRecipient() {
        return User.ADMIN;
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
     * This message has no content then a call to this method cause an <code> 
     * UnsupportedOperationException</code>.
     *
     * @return nothing.
     * @throws UnsupportedOperationException when this method is called.
     */
    @Override
    public Object getContent() {
        throw new UnsupportedOperationException("This message has no content.");
    }

}
