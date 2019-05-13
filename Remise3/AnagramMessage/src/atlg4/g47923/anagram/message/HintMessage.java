package atlg4.g47923.anagram.message;

import atlg4.g47923.anagram.players.User;

public class HintMessage implements Message {

    private final Character hint;
    private final User recipient;

    public HintMessage(int id, String name, Character hint) {
        this.hint = hint;
        this.recipient = new User(id, name);
    }

    @Override
    public User getAuthor() {
        return User.ADMIN;
    }

    @Override
    public User getRecipient() {
        return recipient;
    }

    @Override
    public Type getType() {
        return Type.HINT;
    }

    @Override
    public Object getContent() {
        return hint;
    }

}
