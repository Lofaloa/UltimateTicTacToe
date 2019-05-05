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
     * A signal has no significant content but is used to inform that an event
     * took place.
     */
    SIGNAL,
    
    /**
     * Message with the list of all connected users.
     */
    PLAYERS
    
}
