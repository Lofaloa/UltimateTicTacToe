package atlg4.g47923.anagram.message;

import atlg4.g47923.anagram.players.User;

/**
 * Message informing the client that the current game is over.
 *
 * @author Logan Farci (47923)
 */
public class EndGameMessage implements Message {

    private final boolean isOver;
    private final boolean canAskNextWord;
    private final User recipient;

    /**
     * Constructs an instance of <code> Message </code> that informs the client
     * that the current game is over.
     *
     * @param id is the recipient id.
     * @param name is the name of the recipient.
     * @param isOver tells if the game is over.
     * @param canAskNextWord tells if the a next word can be asked.
     */
    public EndGameMessage(int id, String name, boolean isOver, boolean canAskNextWord) {
        this.isOver = isOver;
        this.canAskNextWord = canAskNextWord;
        this.recipient = new User(id, name);
    }

    /**
     * Returns this message type. This message is of type Type.END_OF_GAME.
     *
     * @return this message type. This message is of type Type.END_OF_GAME.
     */
    @Override
    public Type getType() {
        return Type.END_OF_GAME;
    }

    /**
     * Gets this author message. This message is always sent by the
     * administrator.
     *
     * @return the administrator.
     */
    @Override
    public User getAuthor() {
        return User.ADMIN;
    }

    /**
     * Gets this message recipient.
     *
     * @return this message recipient.
     */
    @Override
    public User getRecipient() {
        return recipient;
    }

    /**
     * Tells if the game is over.
     *
     * @return true if the game is over.
     */
    @Override
    public Object getContent() {
        return isOver;
    }

}
