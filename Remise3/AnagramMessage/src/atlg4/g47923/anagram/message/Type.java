package atlg4.g47923.anagram.message;

/**
 * The <code> Type </code> represents the type of a message send between a user
 * and the server.
 */
public enum Type {
    
    /**
     * Message with the profile of a specific user.
     */
    PROFILE,
    
    /**
     * Anagram proposal sent from a player to the server.
     */
    PROPOSAL,
    
    /**
     * Word sent from the server to a specific player.
     */
    WORD,
    
    /**
     * Message that informs the server that the author has passed the current
     * word.
     */
    PASS_CURRENT_WORD,
    
    /**
     * Message with the list of all connected users.
     */
    PLAYERS
    
}
