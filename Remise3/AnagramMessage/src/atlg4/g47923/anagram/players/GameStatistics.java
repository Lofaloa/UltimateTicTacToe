package atlg4.g47923.anagram.players;

/**
 * Represents the statistics of a specified anagram game.
 *
 * @author Logan Farci (47923)
 */
public class GameStatistics {

    private final int nbWords;
    private final int nbRemaingingWords;
    private final int nbSolvedWords;
    private final int nbUnsolvedWords;
    private final int nbProposal;

    /**
     * Constructs an instance of <code>GameStatistics</code> whith the specified
     * values.
     * 
     * @param nbWords is the total number of words of the game.
     * @param nbRemaingingWords is the number of words remaining to play.
     * @param nbSolvedWords is the number of solved words.
     * @param nbUnsolvedWords is the number of unsolved words.
     * @param nbProposal is the number of proposal for the current word.
     */
    public GameStatistics(int nbWords, int nbRemaingingWords, int nbSolvedWords,
            int nbUnsolvedWords, int nbProposal) {
        this.nbWords = nbWords;
        this.nbRemaingingWords = nbRemaingingWords;
        this.nbSolvedWords = nbSolvedWords;
        this.nbUnsolvedWords = nbUnsolvedWords;
        this.nbProposal = nbProposal;
    }

    public int getNbWords() {
        return nbWords;
    }

    public int getNbRemaingingWords() {
        return nbRemaingingWords;
    }

    public int getNbSolvedWords() {
        return nbSolvedWords;
    }

    public int getNbUnsolvedWords() {
        return nbUnsolvedWords;
    }

    public int getNbProposal() {
        return nbProposal;
    }
    
}
