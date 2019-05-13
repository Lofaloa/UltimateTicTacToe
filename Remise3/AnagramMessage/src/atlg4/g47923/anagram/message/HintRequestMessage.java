package atlg4.g47923.anagram.message;

import atlg4.g47923.anagram.players.User;

public class HintRequestMessage implements Message {

    private final User author;

    public HintRequestMessage(int id, String name) {
        this.author = new User(id, name);
    }

    @Override
    public User getAuthor() {
        return author;
    }

    @Override
    public User getRecipient() {
        return User.ADMIN;
    }

    @Override
    public Type getType() {
        return Type.HINT_REQUEST;
    }

    @Override
    public Object getContent() {
        throw new UnsupportedOperationException("This message has no content.");
    }

}
