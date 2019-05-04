package atlg4.g47923.anagram.message;

import atlg4.g47923.anagram.players.User;

/**
 * The <code> Message </code> represents a message with the profile of a specific
 * user.
 */
public class ProfileMessage implements Message {

    private final User author;

    /**
     * Constructs message with the profile of a specific user. The author of the
     * message give his profile to be send.
     *
     * @param id userID of the author.
     * @param name user name of the author.
     */
    public ProfileMessage(int id, String name) {
        author = new User(id, name);
    }

    /**
     * Return the author of the message. The author send his profile.
     *
     * @return the author of the message.
     */
    @Override
    public User getAuthor() {
        return author;
    }

    /**
     * Return the recipient of this message. A profile message is always send to
     * the administrator.
     *
     * @return the recipient of this message.
     */
    @Override
    public User getRecipient() {
        return User.ADMIN;
    }

    /**
     * Return the type of the message, in this case Type.PROFILE.
     *
     * @return the type of the message, in this case Type.PROFILE.
     */
    @Override
    public Type getType() {
        return Type.PROFILE;
    }

    /**
     * Return the content of the message : the author profile.
     *
     * @return the content of the message : the author profile.
     */
    @Override
    public Object getContent() {
        return author;
    }

}
