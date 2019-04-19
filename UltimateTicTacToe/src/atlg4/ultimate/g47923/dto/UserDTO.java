package atlg4.ultimate.g47923.dto;

/**
 * Data transfer object for a User.
 *
 * @author Logan Farci (47923)
 */
public class UserDTO {

    private final String pseudonym;
    private final int nbOfVictories;
    private final int nbOfTies;
    private final int nbOfDefeats;

    /**
     * Constructs an instance of UserDTO with the specified pseudonym, number of
     * victories, number of ties and number of defeats.
     *
     * @param pseudonym is this user pseudonym.
     * @param nbOfVictories is this user number of victories.
     * @param nbOfTies is this user number of ties.
     * @param nbOfDefeats is this user number of defeats.
     */
    public UserDTO(String pseudonym, int nbOfVictories, int nbOfTies, int nbOfDefeats) {
        this.pseudonym = pseudonym;
        this.nbOfVictories = nbOfVictories;
        this.nbOfTies = nbOfTies;
        this.nbOfDefeats = nbOfDefeats;
    }

    /**
     * Constructs a instance of UserDTO with the specified pseudonym and number
     * of victories, number of ties and number of defeats set to 0.
     *
     * @param pseudonym is this user pseudonym.
     */
    public UserDTO(String pseudonym) {
        this(pseudonym, 0, 0, 0);
    }

    /**
     * Gets this user pseudonym.
     *
     * @return this player pseudonym.
     */
    public String getPseudonym() {
        return pseudonym;
    }

    /**
     * Gets this user number of victories.
     *
     * @return this user number of victories.
     */
    public int getNbOfVictories() {
        return nbOfVictories;
    }

    /**
     * Gets this user number of ties.
     *
     * @return this user number of ties.
     */
    public int getNbOfTies() {
        return nbOfTies;
    }

    /**
     * Gets this user number of defeats.
     *
     * @return this user number of defeats.
     */
    public int getNbOfDefeats() {
        return nbOfDefeats;
    }

}
