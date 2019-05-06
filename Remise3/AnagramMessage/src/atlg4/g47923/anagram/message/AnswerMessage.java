package atlg4.g47923.anagram.message;

import atlg4.g47923.anagram.players.User;

/**
 * The <code> Message </code> represents a message with the answer to the last
 * anagram to be found.
 */
public class AnswerMessage implements Message {

    private final String answer;
    private final User recipient;

    /**
     * Constructs message with the answer to the last anagram to be found.
     *
     * @param id userID of the recipient.
     * @param name user name of the recipient.
     * @param answer is the author answer.
     */
    public AnswerMessage(int id, String name, String answer) {
        this.answer = answer;
        this.recipient = new User(id, name);
    }

    /**
     * Gets the author of the message. An answer message is always sent by the
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
     * Gets the type of the message, in this case Type.ANSWER.
     *
     * @return the type of the message, in this case Type.ANSWER.
     */
    @Override
    public Type getType() {
        return Type.ANSWER;
    }

    /**
     * Gets the content of the message : the answer to the last passed word.
     *
     * @return the content of the message : the answer to the last passed word.
     */
    @Override
    public Object getContent() {
        return answer;
    }

}
