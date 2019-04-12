package atlg4.ultimate.g47923.model;

import atlg4.ultimate.g47923.dto.PositionDTO;
import atlg4.ultimate.g47923.dto.UserDTO;
import atlg4.ultimate.g47923.exception.IllegalMoveException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests the UltimateTicTacToeGame class.
 *
 * @author Logan Farci (47923)
 */
public class UltimateTicTacToeGameTest {

    /**
     * A game should be constructed as expected.
     */
    @Test
    public void construction() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        assertEquals(Marker.X, game.getCurrentPlayer().getMarker());
        assertFalse(game.isOver());
        assertFalse(game.hasAPlayerWithdrawn());
    }

    /**
     * When starting a game, the board should be empty.
     */
    @Test
    public void start_theBoardIsEmpty() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        game.start();
        assertTrue(game.getBoard().isEmpty());
    }

    /**
     * When starting a game, the players should not be withdrawn.
     */
    @Test
    public void start_playersAreNotWithDrawn() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        game.start();
        assertFalse(game.hasAPlayerWithdrawn());
    }

    /**
     * When starting a game, X should be the current player.
     */
    @Test
    public void start_xIsTheCurrentPlayer() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        game.start();
        assertEquals(Marker.X, game.getCurrentPlayer().getMarker());
    }

    /**
     * When starting a game, no moves should have been executed.
     */
    @Test
    public void start_noMovesAreExecuted() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        game.start();
        assertTrue(game.getExecutedMoves().isEmpty());
    }

    /**
     * When starting a game after the game was full, the game should not be over
     * anymore.
     */
    @Test
    public void start_afterGameIsOver() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        game.getBoard().fillWith(Player.O);
        game.start();
        assertFalse(game.isOver());
    }

    /**
     * When starting a game after the game had an owner, the game should not be
     * over anymore.
     */
    @Test
    public void start_afterGameHadOwner() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        game.getBoard().setOwner(Player.O);
        game.start();
        assertFalse(game.isOver());
    }

    /**
     * When starting a game after the game had an withdrawer, the game should
     * not be over anymore.
     */
    @Test
    public void start_afterGameHadWithdrawer() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        game.withdraw();
        game.start();
        assertFalse(game.isOver());
    }

    /**
     * Setting the user of the X player should set the expected user of the X
     * player.
     */
    @Test
    public void setUserOf_xPlayerHasAUser() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        game.setUserOf(Marker.X, new UserDTO("Pinguino", 2, 3, 4));
        assertEquals("Pinguino", game.getX().getUser().getPseudonym());
    }

    /**
     * Setting the user of the O player should set the expected user of the O
     * player.
     */
    @Test
    public void setUserOf_oPlayerHasAUser() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        game.setUserOf(Marker.O, new UserDTO("Anatra", 2, 3, 4));
        assertEquals("Anatra", game.getO().getUser().getPseudonym());
    }

    /**
     * Setting a user during a game should cause an exception.
     */
    @Test(expected = IllegalStateException.class)
    public void setUserOf() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        Move move = new Move(Player.X, new Position(), new Position(), game.getBoard());
        for (int m = 0; m < 5; m++) game.getExecutedMoves().add(move);
        game.setUserOf(Marker.O, new UserDTO("Cuaddu", 2, 3, 4));
    }

    /**
     * Right after construction, the game should be at first turn.
     */
    @Test
    public void isFirstTurn_rightAfterConstruction() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        assertTrue(game.isFirstTurn());
    }

    /**
     * When the first turn has been played, the game should not be at first turn
     * anymore.
     */
    @Test
    public void isFirstTurn_afterFirstTurnWasPlayed() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        game.select(new PositionDTO(2, 2), new PositionDTO(0, 0));
        game.play();
        assertFalse(game.isFirstTurn());
    }

    /**
     * Getting the winner when the game is over should return the owner of the
     * board.
     */
    @Test
    public void getWinner_oOwnsTheBoard() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        game.getX().setWithDrawn(false);
        game.getO().setWithDrawn(false);
        game.getBoard().setOwner(Player.O);
        assertEquals(Marker.O, game.getWinner().getMarker());
    }

    /**
     * Getting the winner when the game is over should return the owner of the
     * board.
     */
    @Test
    public void getWinner_xOwnsTheBoard() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        game.getX().setWithDrawn(false);
        game.getO().setWithDrawn(false);
        game.getBoard().setOwner(Player.X);
        assertEquals(Marker.X, game.getWinner().getMarker());
    }

    /**
     * Getting the winner when O has withdrawn should return X.
     */
    @Test
    public void getWinner_OHasWithdrawn() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        game.getX().setWithDrawn(false);
        game.getO().setWithDrawn(true);
        assertEquals(Marker.X, game.getWinner().getMarker());
    }

    /**
     * Getting the winner when X has withdrawn should return O.
     */
    @Test
    public void getWinner_XHasWithdrawn() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        game.getX().setWithDrawn(true);
        game.getO().setWithDrawn(false);
        assertEquals(Marker.O, game.getWinner().getMarker());
    }

    /**
     * Trying to get the winner before the end of the game causes an exception.
     */
    @Test(expected = IllegalStateException.class)
    public void getWinner_duringAGame() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        game.getWinner();
    }

    /**
     * Trying to get the winner when the game is over but the board is full.
     */
    @Test(expected = IllegalStateException.class)
    public void getWinner_gameIsEven() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        game.getX().setWithDrawn(false);
        game.getO().setWithDrawn(false);
        game.getBoard().fillWith(Player.O);
        game.getWinner();
    }

    /**
     * A game should be over when the board is owned.
     */
    @Test
    public void isOver_boardHasAnOwner() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        game.getBoard().setOwner(Player.O);
        assertTrue(game.isOver());
    }

    /**
     * A game should be over when the board is full.
     */
    @Test
    public void isOver_boardIsFull() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        game.getBoard().fillWith(Player.O);
        assertTrue(game.isOver());
    }

    /**
     * A game should be over when one of the players withdraws.
     */
    @Test
    public void isOver_aPlayerWithDraws() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        game.getX().setWithDrawn(true);
        assertTrue(game.isOver());
        game.getX().setWithDrawn(false);
    }

    /**
     * A move that has a MiniTicTacToe position equals to the last move Cell
     * position should be in the in the expected MiniTicTacToe.
     */
    @Test
    public void isInExpectedMiniTicTacToe() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        Move lastMove = new Move(
                Player.X,
                new Position(0, 0),
                new Position(1, 2),
                game.getBoard()
        );
        Move currentMove = new Move(
                Player.O,
                new Position(1, 2),
                new Position(1, 1),
                game.getBoard()
        );
        game.getExecutedMoves().add(lastMove);
        assertTrue(game.isInExpectedMiniTicTacToe(currentMove));
    }

    /**
     * A move that has a MiniTicTacToe position different from the last move
     * Cell position should not be in the in the expected MiniTicTacToe.
     */
    @Test
    public void isNotInExpectedMiniTicTacToe() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        Move lastMove = new Move(
                Player.X,
                new Position(0, 0),
                new Position(1, 2),
                game.getBoard()
        );
        Move currentMove = new Move(
                Player.O,
                new Position(2, 2),
                new Position(1, 1),
                game.getBoard()
        );
        game.getExecutedMoves().add(lastMove);
        assertFalse(game.isInExpectedMiniTicTacToe(currentMove));
    }

    /**
     * Calling isInExpectedMiniTicTacToe during the first turn should cause an
     * exception.
     */
    @Test(expected = IllegalStateException.class)
    public void isInExpectedMiniTicTacToe_calledDuringFirstTurn() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        Move currentMove = new Move(
                Player.O,
                new Position(2, 2),
                new Position(1, 1),
                game.getBoard()
        );
        game.isInExpectedMiniTicTacToe(currentMove);
    }

    /**
     * Selection should constructed the expected current move.
     */
    @Test
    public void select_expectedMoveConstruction() {
        UltimateTicTacToeGame g = new UltimateTicTacToeGame();
        Position m = new Position(1, 1);
        Position c = new Position(0, 0);
        g.select(m.toDTO(), c.toDTO());
        assertEquals(m, g.getCurrentMove().getMiniTicTacToePosition());
        assertEquals(c, g.getCurrentMove().getCellPosition());
    }

    /**
     * When a player selects a MiniTicTacToe that has not the same position as
     * the Cell selected by the previous player, an exception is thrown.
     */
    @Test(expected = IllegalMoveException.class)
    public void select_unexpectedMiniTicTacToe() {
        UltimateTicTacToeGame g = new UltimateTicTacToeGame();
        Position miniSelectedByX = new Position(0, 0);
        Position cellSelectedByX = new Position(0, 0);
        Position miniSelectedByO = new Position(1, 1);
        Position cellSelectedByO = new Position(0, 0);
        g.select(miniSelectedByX.toDTO(), cellSelectedByX.toDTO());
        g.play();
        g.nextPlayer();
        g.select(miniSelectedByO.toDTO(), cellSelectedByO.toDTO());
    }

    /**
     * When the expected MiniTicTacToe is full, the player can choose another
     * MiniTicTacToe.
     */
    @Test
    public void select_expectedMiniTicTacToeIsFull() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        Position miniPosSelectedByX = new Position(1, 1);
        Position cellPosSelectedByX = new Position(1, 2);
        Position miniPosSelectedByO = new Position(2, 2);
        Position cellPosSelectedByO = new Position(1, 1);

        game.select(miniPosSelectedByX.toDTO(), cellPosSelectedByX.toDTO());
        game.play();
        game.nextPlayer();

        game.getBoard().getCellAt(cellPosSelectedByX).fillWith(Player.X);
        game.select(miniPosSelectedByO.toDTO(), cellPosSelectedByO.toDTO());
        game.play();

        Grid miniSelectedByO = game.getBoard().getCellAt(miniPosSelectedByO);
        Grid cellSelectedByO = miniSelectedByO.getCellAt(cellPosSelectedByO);

        assertEquals(Player.O, cellSelectedByO.getOwner());
    }

    /**
     * When the expected MiniTicTacToe has an owner, the player can choose
     * another MiniTicTacToe.
     */
    @Test
    public void select_expectedMiniTicTacToeHasAnOwner() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        Position miniPosSelectedByX = new Position(1, 1);
        Position cellPosSelectedByX = new Position(1, 2);
        Position miniPosSelectedByO = new Position(2, 2);
        Position cellPosSelectedByO = new Position(1, 1);

        game.select(miniPosSelectedByX.toDTO(), cellPosSelectedByX.toDTO());
        game.play();
        game.getBoard().getCellAt(cellPosSelectedByX).setOwner(Player.X);
        game.nextPlayer();

        game.select(miniPosSelectedByO.toDTO(), cellPosSelectedByO.toDTO());
        game.play();

        Grid miniSelectedByO = game.getBoard().getCellAt(miniPosSelectedByO);
        Grid cellSelectedByO = miniSelectedByO.getCellAt(cellPosSelectedByO);

        assertEquals(Player.O, cellSelectedByO.getOwner());
    }

    /**
     * When the selected MiniTicTacToe is full an exception is thrown
     */
    @Test(expected = IllegalMoveException.class)
    public void select_selectedMiniTicTacToeIsFull() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        Position miniPosSelectedByX = new Position(1, 1);
        Position cellPosSelectedByX = new Position(1, 2);
        game.getBoard().getCellAt(miniPosSelectedByX).setOwner(Player.X);
        game.select(miniPosSelectedByX.toDTO(), cellPosSelectedByX.toDTO());
    }

    /**
     * When the selected MiniTicTacToe has an owner an exception is thrown
     */
    @Test(expected = IllegalMoveException.class)
    public void select_selectedMiniTicTacToeHasAnOwner() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        Position miniPosSelectedByX = new Position(1, 1);
        Position cellPosSelectedByX = new Position(1, 2);
        game.getBoard().getCellAt(miniPosSelectedByX).setOwner(Player.X);
        game.select(miniPosSelectedByX.toDTO(), cellPosSelectedByX.toDTO());
    }

    /**
     * Play method should set the owner at the selected position.
     */
    @Test
    public void play_setOwnerAtSelectedPosition() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        Position miniPosSelectedByX = new Position(1, 1);
        Position cellPosSelectedByX = new Position(1, 2);
        game.select(miniPosSelectedByX.toDTO(), cellPosSelectedByX.toDTO());
        game.play();
        Grid mini = game.getBoard().getCellAt(miniPosSelectedByX);
        Grid cell = mini.getCellAt(cellPosSelectedByX);
        assertEquals(Player.X, cell.getOwner());
    }

    /**
     * At first turn, play method should throw an exception when the current
     * player has not selected a position.
     */
    @Test(expected = IllegalStateException.class)
    public void play_firstTurn_currentPlayerHasNotSelected() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        game.play();
    }

    /**
     * Play method should throw an exception when the current player has not
     * selected a position.
     */
    @Test(expected = IllegalStateException.class)
    public void play_currentPlayerHasNotSelected() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        Move last = new Move(
                Player.O,
                new Position(2, 2),
                new Position(1, 1),
                game.getBoard()
        );
        game.getExecutedMoves().add(last);
        game.play();
    }

    /**
     * A call to withdraw should withdraw the expected player.
     */
    @Test
    public void withdraw() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        game.getX().setWithDrawn(false);
        game.getO().setWithDrawn(false);
        game.withdraw();
        assertTrue(game.getCurrentPlayer().isWithDrawn());
    }

    /**
     * The game should be over after calling withdraw and the second player
     * should be the winner.
     */
    @Test
    public void withdraw_gameIsOver() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        game.getX().setWithDrawn(false);
        game.getO().setWithDrawn(false);
        game.withdraw();
        assertTrue(game.isOver());
    }

    /**
     * The game should pass to the next player as expected.
     */
    @Test
    public void nextPlayer() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        assertEquals(Marker.X, game.getCurrentPlayer().getMarker());
        game.select(new PositionDTO(1, 1), new PositionDTO(0, 0));
        game.play();
        game.nextPlayer();
        assertEquals(Marker.O, game.getCurrentPlayer().getMarker());
    }

    /**
     * Calling nextPlayer methods when the first player has not selected nor
     * played causes an exception.
     */
    @Test(expected = IllegalStateException.class)
    public void nextPlayer_firstPlayerNotDone() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        game.nextPlayer();
    }

    /**
     * Calling nextPlayer methods when the current player is done causes an
     * exception.
     */
    @Test(expected = IllegalStateException.class)
    public void nextPlayer_currentPlayerNotDone() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        game.select(new PositionDTO(1, 1), new PositionDTO(0, 0));
        game.play();
        game.nextPlayer();
        game.select(new PositionDTO(0, 0), new PositionDTO(2, 1));
        game.nextPlayer();
    }

}
