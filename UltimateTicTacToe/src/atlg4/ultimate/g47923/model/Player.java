package atlg4.ultimate.g47923.model;

/**
 *
 * @author Logan Farci (47923)
 */
public class Player {

    private Marker marker;
    private boolean isWithdrawn;

    /**
     * Constructs a player with the given marker. Initially a player is not
     * withdrawn.
     *
     * @param marker the marker of this player.
     */
    public Player(Marker marker) {
        this.marker = marker;
        this.isWithdrawn = false;
    }

    /**
     * Tells if this player is withdrawn.
     * 
     * @return true if this player is withdrawn. 
     */
    public boolean isIsWithdrawn() {
        return isWithdrawn;
    }
    
    /**
     * Withdraws this player.
     */
    void withdraw() {
        this.isWithdrawn = true;
    }

}
