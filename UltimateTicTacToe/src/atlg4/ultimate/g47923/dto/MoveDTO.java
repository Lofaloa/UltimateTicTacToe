package atlg4.ultimate.g47923.dto;

import static java.util.Objects.requireNonNull;

/**
 * Data transfer object for a Move.
 *
 * @author Logan Farci (47923)
 */
public class MoveDTO {

    private final PlayerDTO author;
    private final PositionDTO miniTicTacToePosition;
    private final PositionDTO cellPosition;
    private final boolean isWinning;

    /**
     * Constructs this move data transfer object with the given player, the
     * specified <code>MiniTicTacToe</code> position and <code>Cell</code>
     * position.
     *
     * @param author is the player that makes this move.
     * @param mini is the position of the <code>MiniTicTacToe</code> where this
     * move is made.
     * @param cell is the position of the <code>Cell</code> in the given
     * <code>MiniTicTacToe</code> where this move is made.
     * @param isWinning tells if this move has lead to a
     * <code>MiniTicTacToe</code> win.
     */
    public MoveDTO(PlayerDTO author, PositionDTO mini, PositionDTO cell,
            boolean isWinning) {
        this.author = requireNonNull(author, "This move author is null.");
        this.miniTicTacToePosition = requireNonNull(mini, "This move "
                + "MiniTicTacToe position is null.");
        this.cellPosition = requireNonNull(cell, "This move Cell position is "
                + "null.");
        this.isWinning = isWinning;
    }

    /**
     * Gets this move author data transfer object.
     *
     * @return this move author data transfer object.
     */
    public PlayerDTO getAuthor() {
        return author;
    }

    /**
     * Gets this move <code>MiniTicTacToe</code> position.
     *
     * @return this move <code>MiniTicTacToe</code> position.
     */
    public PositionDTO getMiniTicTacToePosition() {
        return miniTicTacToePosition;
    }

    /**
     * Gets this move <code>Cell</code> position.
     *
     * @return this move <code>Cell</code> position.
     */
    public PositionDTO getCellPosition() {
        return cellPosition;
    }

    /**
     * Tells if this move has lead to a <code>MiniTicTacToe</code> win.
     *
     * @return true id this move has lead to a <code>MiniTicTacToe</code> win.
     */
    public boolean isWinning() {
        return isWinning;
    }

}
