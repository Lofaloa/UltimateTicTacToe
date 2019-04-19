package atlg4.ultimate.g47923.persistence.seldto;

/**
 * Represents a selection of user.
 *
 * @author Logan Farci (47923)
 */
public class UserSel {

    private final String pseudonym;
    private final int nbOfVictories;
    private final int nbOfTies;
    private final int nbOfDefeats;

    /**
     * Constructs an instance of UserSel with the specified pseudonym, number of
     * victories, number of ties and number of defeats.
     *
     * The pseudonym should be <code>null</code> if this selection is not based
     * on it.
     *
     * The number of victories, number of ties and number of defeats should be
     * negative if one these argument should not be considered in this user
     * selection.
     *
     * @param pseudonym is this user selection pseudonym.
     * @param nbOfVictories is this user selection number of victories.
     * @param nbOfTies is this user selection number of ties.
     * @param nbOfDefeats is this user selection number of defeats.
     */
    public UserSel(String pseudonym, int nbOfVictories, int nbOfTies, int nbOfDefeats) {
        this.pseudonym = pseudonym;
        this.nbOfVictories = nbOfVictories;
        this.nbOfTies = nbOfTies;
        this.nbOfDefeats = nbOfDefeats;
    }

    /**
     * Constructs an instance of UserSel with the specified pseudonym. The
     * number of victories, number of ties and number of defeats are all set to
     * negative values as they are not part of this selection.
     *
     * @param pseudonym is this user selection pseudonym.
     */
    public UserSel(String pseudonym) {
        this(pseudonym, -1, -1, -1);
    }

    /**
     * Gets this user selection pseudonym.
     *
     * @return this user selection pseudonym.
     */
    public String getPseudonym() {
        return pseudonym;
    }

    /**
     * Gets this user selection number of victories.
     *
     * @return this user selection number of victories.
     */
    public int getNbOfVictories() {
        return nbOfVictories;
    }

    /**
     * Gets this user selection number of ties.
     *
     * @return this user selection number of ties.
     */
    public int getNbOfTies() {
        return nbOfTies;
    }

    /**
     * Gets this user selection number of defeats.
     *
     * @return this user selection number of defeats.
     */
    public int getNbOfDefeats() {
        return nbOfDefeats;
    }

}
