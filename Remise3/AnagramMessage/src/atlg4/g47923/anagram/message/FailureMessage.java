package atlg4.g47923.anagram.message;

import atlg4.g47923.anagram.players.User;

/**
 * The <code> Message </code> represents a message informing a client that the
 * given proposal is wrong. The message contains the wrong proposal made by the
 * player.
 */
public class FailureMessage implements Message {

    private final String wrongProposal;
    private final User recipient;

    /**
     * Constructs a message used to inform a client that the given proposal is
     * wrong.
     *
     * @param wrongAnswer is the wrong proposal made by the player.
     * @param id is the id this message recipient.
     * @param name is the name of this message recipient.
     */
    public FailureMessage(String wrongAnswer, int id, String name) {
        this.wrongProposal = wrongAnswer;
        this.recipient = new User(id, name);
    }

    /**
     * Gets this message author. A FailureMessage is always sent by the
     * administrator.
     *
     * @return the administrator.
     */
    @Override
    public User getAuthor() {
        return User.ADMIN;
    }

    /**
     * Return the recipient of the message.
     *
     * @return the recipient of the message.
     */
    @Override
    public User getRecipient() {
        return recipient;
    }

    /**
     * Return the type of the message, in this case Type.FAILURE.
     *
     * @return the type of the message, in this case Type.FAILURE.
     */
    @Override
    public Type getType() {
        return Type.FAILURE;
    }

    /**
     * Gets this message wrong proposal.
     *
     * @return this message wrong proposal.
     */
    @Override
    public Object getContent() {
        return wrongProposal;
    }

}
