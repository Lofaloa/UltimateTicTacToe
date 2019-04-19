package atlg4.ultimate.g47923.dto;

import atlg4.ultimate.g47923.model.*;

/**
 * Data transfer object for a Player.
 *
 * @author Logan Farci (47923)
 */
public class PlayerDTO {

    private final Marker marker;
    private final UserDTO user;
    private final boolean isWithdrawn;

    /**
     * Constructs a player with the given marker. Initially a player is not
     * withdrawn.
     *
     * @param marker the marker of this player.
     * @param user is this player user.
     * @param isWithdrawn tells if this player is withdrawn.
     */
    public PlayerDTO(Marker marker, UserDTO user, boolean isWithdrawn) {
        this.marker = marker;
        this.user = user;
        this.isWithdrawn = isWithdrawn;
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
     * Gets this player user.
     *
     * @return this player user.
     */
    public UserDTO getUser() {
        return user;
    }

    /**
     * Tells if this player is withdrawn.
     *
     * @return true if this player is withdrawn.
     */
    public boolean isWithdrawn() {
        return isWithdrawn;
    }

}
