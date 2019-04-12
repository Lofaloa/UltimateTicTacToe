package atlg4.ultimate.g47923.model;

import atlg4.ultimate.g47923.dto.UserDTO;

/**
 * Represents a person that has played the game once.
 *
 * @author Logan Farci (47923)
 */
class User {

    private final String pseudonym;
    private int nbOfVictories;
    private int nbOfExaequos;
    private int nbOfDefeats;

    User(String pseudonym, int nbOfVictories, int nbOfExaequos, int nbOfDefeats) {
        this.pseudonym = pseudonym;
        this.nbOfVictories = nbOfVictories;
        this.nbOfExaequos = nbOfExaequos;
        this.nbOfDefeats = nbOfDefeats;
    }

    /**
     * Constructs an instance of player with the specified user data transfer
     * object.
     *
     * @param user is the specified user.
     */
    User(UserDTO user) {
        this(
                user.getPseudonym(),
                user.getNbOfVictories(),
                user.getNbOfExaequos(),
                user.getNbOfDefeats()
        );
    }

    String getPseudonym() {
        return pseudonym;
    }

    int getNbOfVictories() {
        return nbOfVictories;
    }

    int getNbOfExaequos() {
        return nbOfExaequos;
    }

    int getNbOfDefeats() {
        return nbOfDefeats;
    }

    void addAVictory() {
        nbOfVictories++;
    }

    void addAnExaequo() {
        nbOfExaequos++;
    }

    void addADefeat() {
        nbOfDefeats++;
    }

}
