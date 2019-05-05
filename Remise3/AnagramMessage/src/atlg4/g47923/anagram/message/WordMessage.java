package atlg4.g47923.anagram.message;

import atlg4.g47923.anagram.players.User;

/**
 * The <code> Message </code> represents a message with one of the words the
 * player has to find an anagram for from the server to the client.
 */
public class WordMessage implements Message {

    private final String word;
    private final User recipient;

    /**
     * Constructs message with a word and the player to send it to.
     *
     * @param id userID of the recipient.
     * @param name user name of the recipient.
     * @param word is the author proposal.
     */
    public WordMessage(int id, String name, String word) {
        this.word = word;
        this.recipient = new User(id, name);
    }

    /**
     * Gets the author of the message. A word message is always sent by the
     * administrator.
     *
     * @return the author of the message.
     */
    @Override
    public User getAuthor() {
        return User.ADMIN;
    }

    /**
     * Gets the recipient of this message.
     *
     * @return the recipient of this message.
     */
    @Override
    public User getRecipient() {
        return recipient;
    }

    /**
     * Gets the type of the message, in this case Type.WORD.
     *
     * @return the type of the message, in this case Type.WORD.
     */
    @Override
    public Type getType() {
        return Type.WORD;
    }

    /**
     * Gets the content of the message : a word to guess an anagram for.
     *
     * @return the content of the message : a word to guess an anagram for.
     */
    @Override
    public Object getContent() {
        return word;
    }

}
