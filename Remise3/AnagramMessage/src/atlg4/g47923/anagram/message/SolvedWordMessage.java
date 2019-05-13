package atlg4.g47923.anagram.message;

import atlg4.g47923.anagram.players.User;

public class SolvedWordMessage implements Message {

    private final String word;
    private final User author;
    private final boolean usedHint;

    public SolvedWordMessage(int id, String name, String word, boolean usedHint) {
        this.word = word;
        this.author = new User(id, name);
        this.usedHint = usedHint;
    }

    @Override
    public User getAuthor() {
        return author;
    }

    @Override
    public User getRecipient() {
        return User.EVERYBODY;
    }

    @Override
    public Type getType() {
        return Type.SOLVED_WORD;
    }

    @Override
    public Object getContent() {
        return word;
    }

    public boolean hasUsedhint() {
        return usedHint;
    }
    
}
