package atlg4.ultimate.g47923.model;

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

    /**
     * Constructs this move with the given player, the specified
     * <code>MiniTicTacToe</code> position and <code>Cell</code> position.
     *
     * @param author is the player that makes this move.
     * @param mini is the position of the <code>MiniTicTacToe</code> where this
     * move is made.
     * @param cell is the position of the <code>Cell</code> in the given
     * <code>MiniTicTacToe</code> where this move is made.
     * @param board is the board where to execute this move.
     * @throws NullPointerException if one of the arguments is null.
     */
    Move(Player author, Position mini, Position cell,
            UltimateTicTacToe board) {
        this.author = requireNonNull(author, "This move author is null.");
        this.miniTicTacToePosition = requireNonNull(mini, "This move "
                + "MiniTicTacToe position is null.");
        this.cellPosition = requireNonNull(cell, "This move Cell position is "
                + "null.");
        this.board = requireNonNull(board, "This move "
                + "target is null.");
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

    /**
     * Executes this move.
     */
    void execute() {
        board.setOwnerAt(author, miniTicTacToePosition, cellPosition);
    }

}
