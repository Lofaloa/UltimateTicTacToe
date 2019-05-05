package atlg4.g47923.anagram.message;

import atlg4.g47923.anagram.players.User;

/**
 * The <code> Message </code> represents a message with an anagram proposal
 * made by a specific player.
 */
public class ProposalMessage implements Message {

    private final String proposal;
    private final User author;

    /**
     * Constructs message with a proposal made by a specific user.
     *
     * @param id userID of the author.
     * @param name user name of the author.
     * @param proposal is the author proposal.
     */
    public ProposalMessage(String proposal, int id, String name) {
        this.proposal = proposal;
        this.author = new User(id, name);
    }

    /**
     * Gets the author of the message. The author send his profile.
     *
     * @return the author of the message.
     */
    @Override
    public User getAuthor() {
        return author;
    }

    /**
     * Gets the recipient of this message. A proposal message is always send
     * to the administrator.
     *
     * @return the recipient of this message.
     */
    @Override
    public User getRecipient() {
        return User.ADMIN;
    }

    /**
     * Gets the type of the message, in this case Type.PROPOSAL.
     *
     * @return the type of the message, in this case Type.PROPOSAL.
     */
    @Override
    public Type getType() {
        return Type.PROPOSAL;
    }

    /**
     * Gets the content of the message : the author anagram proposal.
     *
     * @return the content of the message : the author anagram proposal.
     */
    @Override
    public Object getContent() {
        return proposal;
    }

}
