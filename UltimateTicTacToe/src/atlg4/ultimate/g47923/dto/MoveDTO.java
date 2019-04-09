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

    public MoveDTO(PlayerDTO author, PositionDTO mini, PositionDTO cell,
            boolean isWinning) {
        this.author = requireNonNull(author, "This move author is null.");
        this.miniTicTacToePosition = requireNonNull(mini, "This move "
                + "MiniTicTacToe position is null.");
        this.cellPosition = requireNonNull(cell, "This move Cell position is "
                + "null.");
        this.isWinning = isWinning;
    }

    public PlayerDTO getAuthor() {
        return author;
    }

    public PositionDTO getMiniTicTacToePosition() {
        return miniTicTacToePosition;
    }

    public PositionDTO getCellPosition() {
        return cellPosition;
    }

    public boolean isWinning() {
        return isWinning;
    }

}
