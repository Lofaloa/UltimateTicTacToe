/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atlg4.ultimate.g47923.model;

import atlg4.ultimate.g47923.model.Move;
import atlg4.ultimate.g47923.dto.PositionDTO;
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
        game.getBoard().setOwner(Player.X);
        assertEquals(Marker.X, game.getWinner().getMarker());
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
     * A game should be over when the board is owned.
     */
    @Test
    public void isOver_boardOwnedByO() {
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
     * The game should pass to the next player as expected.
     */
    @Test
    public void nextPlayer() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        assertEquals(Marker.X, game.getCurrentPlayer().getMarker());
        game.nextPlayer();
        assertEquals(Marker.O, game.getCurrentPlayer().getMarker());
    }

}
