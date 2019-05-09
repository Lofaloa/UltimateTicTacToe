package atlg4.g47923.anagram.message;

import atlg4.g47923.anagram.players.User;

/**
 * Message used to tell the client that given login
 *
 * @author Logan Farci (47923)
 */
public class LoginValidationMessage implements Message {

    private final boolean isValidLogin;
    private final User recipient;

    /**
     * Constructs message that tells the client if the given login is valid or
     * not.
     *
     * @param id userID of the recipient.
     * @param name user name of the recipient.
     * @param isValid tells if the given login .
     */
    public LoginValidationMessage(int id, String name, boolean isValid) {
        this.isValidLogin = isValid;
        this.recipient = new User(id, name);
    }

    /**
     * Gets the type of the message, in this case Type.LOGIN_VALIDATION.
     *
     * @return the type of the message, in this case Type.LOGIN_VALIDATION.
     */
    @Override
    public Type getType() {
        return Type.LOGIN_VALIDATION;
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
     * Gets the content of the message : a boolean telling if the given login is
     * valid.
     *
     * @return the content of the message.
     */
    @Override
    public Object getContent() {
        return isValidLogin;
    }

}
