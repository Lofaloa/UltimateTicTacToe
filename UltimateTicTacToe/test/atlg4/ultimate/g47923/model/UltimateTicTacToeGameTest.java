/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atlg4.ultimate.g47923.model;

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
        assertTrue(game.isFirstRound());
        assertEquals(Marker.X, game.getCurrentPlayer().getMarker());
        assertFalse(game.isOver());
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
        for (int mrow = 0; mrow < UltimateTicTacToe.SIZE; mrow++) {
            for (int mcol = 0; mcol < UltimateTicTacToe.SIZE; mcol++) {
                for (int crow = 0; crow < UltimateTicTacToe.SIZE; crow++) {
                    for (int ccol = 0; ccol < UltimateTicTacToe.SIZE; ccol++) {
                        Position currentMini = new Position(mrow, mcol);
                        Position currentCell = new Position(crow, ccol);
                        game.getBoard().setOwnerAt(Player.O, currentMini, currentCell);
                    }
                }
            }
        }
        assertTrue(game.isOver());
    }

    /**
     * Selection should constructed the expected current move.
     */
    @Test
    public void select_firstRound() {
        UltimateTicTacToeGame g = new UltimateTicTacToeGame();
        Position m = new Position(1, 1);
        Position c = new Position(0, 0);
        g.select(m.toDTO(), c.toDTO());
        assertEquals(m, g.getCurrentMove().getMiniTicTacToePosition());
        assertEquals(c, g.getCurrentMove().getCellPosition());
    }

    /**
     * When the player selects the wrong MiniTicTacToe, an exception is thrown.
     */
    @Test(expected = IllegalMoveException.class)
    public void select_OPlayer() {
        UltimateTicTacToeGame g = new UltimateTicTacToeGame();
        Position mX = new Position(0, 0);
        Position cX = new Position(0, 0);
        Position mO = new Position(1, 1);
        Position cO = new Position(0, 0);
        g.select(mX.toDTO(), cX.toDTO());
        g.play();
        g.nextPlayer();
        g.select(mO.toDTO(), cO.toDTO());
    }

    /**
     * When the expected MiniTicTacToe is full, the player can choose another
     * MiniTicTacToe.
     */
    @Test(expected = IllegalMoveException.class)
    public void select_expectedMiniTicTacToeIsFull() {

    }

    /**
     * When the expected MiniTicTacToe has an owner, the player can choose
     * another MiniTicTacToe.
     */
    @Test(expected = IllegalMoveException.class)
    public void select_expectedMiniTicTacToeHasAnOwner() {

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
