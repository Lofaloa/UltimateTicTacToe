package atlg4.ultimate.g47923.model;

import atlg4.ultimate.g47923.dto.MoveDTO;
import static java.util.Objects.requireNonNull;

/**
 * Represents a move made by a player.
 *
 * @author Logan Farci (47923)
 */
class Move {

    private final Player author;
    private final Position miniTicTacToePosition;
    private final Position cellPosition;
    private final UltimateTicTacToe board;
    private boolean isWinning;

    Move(Player author, Position mini, Position cell,
            UltimateTicTacToe board) {
        this.author = requireNonNull(author, "This move author is null.");
        this.miniTicTacToePosition = requireNonNull(mini, "This move "
                + "MiniTicTacToe position is null.");
        this.cellPosition = requireNonNull(cell, "This move Cell position is "
                + "null.");
        this.board = requireNonNull(board, "This move "
                + "target is null.");
        this.isWinning = false;
    }

    Player getAuthor() {
        return author;
    }

    Position getMiniTicTacToePosition() {
        return miniTicTacToePosition;
    }

    Position getCellPosition() {
        return cellPosition;
    }

    UltimateTicTacToe getBoard() {
        return board;
    }

    private boolean hasAuthorWon() {
        Grid miniTicTacToe = board.getCellAt(miniTicTacToePosition);
        return miniTicTacToe.hasOwner() && author.getMarker()
                == miniTicTacToe.getOwner().getMarker();
    }

    void execute() {
        board.setOwnerAt(author, miniTicTacToePosition, cellPosition);
        if (hasAuthorWon()) {
            isWinning = true;
        }
    }

    MoveDTO toDTO() {
        return new MoveDTO(author.toDTO(), miniTicTacToePosition.toDTO(),
                cellPosition.toDTO(), isWinning);
    }

}
