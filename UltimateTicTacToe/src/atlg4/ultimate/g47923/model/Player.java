package atlg4.ultimate.g47923.model;

/**
 * Represents a tic tac toe player.
 *
 * @author Logan Farci (47923)
 */
enum Player {

    /**
     * Represents the player using the X marker.
     */
    X(Marker.X),

    /**
     * Represents the player using the O marker;
     */
    O(Marker.O);

    private final Marker marker;
    private boolean isWithdrawn;

    /**
     * Constructs a player with the given marker. Initially a player is not
     * withdrawn.
     *
     * @param marker the marker of this player.
     */
    Player(Marker marker) {
        this.marker = marker;
        this.isWithdrawn = false;
    }

    /**
     * Gets this player marker.
     *
     * @return this player marker.
     */
    public Marker getMarker() {
        return marker;
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
