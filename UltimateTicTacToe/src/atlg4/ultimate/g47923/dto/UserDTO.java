package atlg4.ultimate.g47923.dto;

/**
 *
 * @author Logan Farci (47923)
 */
public class UserDTO {

    private final String pseudonym;
    private final int nbOfVictories;
    private final int nbOfExaequos;
    private final int nbOfDefeats;

    public UserDTO(String pseudonym, int nbOfVictories, int nbOfExaequos, int nbOfDefeats) {
        this.pseudonym = pseudonym;
        this.nbOfVictories = nbOfVictories;
        this.nbOfExaequos = nbOfExaequos;
        this.nbOfDefeats = nbOfDefeats;
    }
    
    public UserDTO(String pseudonym) {
        this(pseudonym, 0, 0, 0);
    }

    public String getPseudonym() {
        return pseudonym;
    }

    public int getNbOfVictories() {
        return nbOfVictories;
    }

    public int getNbOfExaequos() {
        return nbOfExaequos;
    }

    public int getNbOfDefeats() {
        return nbOfDefeats;
    }

}
