package atlg4.ultimate.g47923.model;

import atlg4.ultimate.g47923.dto.PlayerDTO;

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
    private User user;
    private boolean isWithdrawn;

    /**
     * Constructs a player with the given marker. Initially a player is not
     * withdrawn.
     *
     * @param marker the marker of this player.
     */
    Player(Marker marker) {
        this.marker = marker;
        this.user = null;
        this.isWithdrawn = false;
    }

    /**
     * Gets this player marker.
     *
     * @return this player marker.
     */
    Marker getMarker() {
        return marker;
    }

    User getUser() {
        return user;
    }

    boolean hasUser() {
        return user != null;
    }

    /**
     * Tells if this player is withdrawn.
     *
     * @return true if this player is withdrawn.
     */
    boolean isWithdrawn() {
        return isWithdrawn;
    }

    void setUser(User user) {
        this.user = user;
    }

    /**
     * Withdraws this player.
     */
    void setWithDrawn(boolean value) {
        this.isWithdrawn = value;
    }

    /**
     * Converts this player to a data transfer object.
     */
    PlayerDTO toDTO() {
        return new PlayerDTO(this.marker, this.isWithdrawn);
    }

}
