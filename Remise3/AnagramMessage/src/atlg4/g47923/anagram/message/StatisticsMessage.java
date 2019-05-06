package atlg4.g47923.anagram.message;

import atlg4.g47923.anagram.players.GameStatistics;
import atlg4.g47923.anagram.players.User;

/**
 * Represents a message sent from the server to a specified client containing
 * all the statistics of the game he/ she is playing.
 *
 * @author Logan Farci (47923)
 */
public class StatisticsMessage implements Message {

    private final GameStatistics statistics;
    private final User recipient;

    /**
     * Constructs an instance of <code> StatisticsMessage </code> with the
     * specified statistics and the given recipient.
     *
     * @param statistics are the specified statistics.
     * @param recipient is the specified recipient.
     */
    public StatisticsMessage(GameStatistics statistics, User recipient) {
        this.statistics = statistics;
        this.recipient = recipient;
    }

    /**
     * Gets the type of the message, in this case Type.STATISTICS.
     *
     * @return the type of the message, in this case Type.STATISTICS.
     */
    @Override
    public Type getType() {
        return Type.STATISTICS;
    }

    /**
     * Gets this message author. A statistics message is always sent by the
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
     * Gets this message content. In this case the message contains the
     * statistics of the game currently played by the recipient.
     *
     * @return this message content.
     */
    @Override
    public Object getContent() {
        return statistics;
    }

}
