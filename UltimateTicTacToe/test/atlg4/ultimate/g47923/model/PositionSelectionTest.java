package atlg4.ultimate.g47923.model;

import atlg4.ultimate.g47923.exception.IllegalMoveException;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Tests the selection of a position during a game.
 *
 * @author Logan Farci (47923)
 */
public class PositionSelectionTest {

    private void setPlayersUsers(UltimateTicTacToeGame game) {
        game.getX().setUser(new User("Willie", 1, 2, 3));
        game.getO().setUser(new User("Nazari", 1, 2, 3));
    }

    /**
     * Selection should constructed the expected current move.
     */
    @Test
    public void select_expectedMoveConstruction() {
        UltimateTicTacToeGame g = new UltimateTicTacToeGame();
        Position m = new Position(1, 1);
        Position c = new Position(0, 0);

        setPlayersUsers(g);
        g.select(m.toDTO(), c.toDTO());
        g.clearUsers();

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

        setPlayersUsers(g);

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

        setPlayersUsers(game);

        game.select(miniPosSelectedByX.toDTO(), cellPosSelectedByX.toDTO());
        game.play();
        game.nextPlayer();

        game.getBoard().getCellAt(cellPosSelectedByX).fillWith(Player.X);
        game.select(miniPosSelectedByO.toDTO(), cellPosSelectedByO.toDTO());
        game.play();

        Grid miniSelectedByO = game.getBoard().getCellAt(miniPosSelectedByO);
        Grid cellSelectedByO = miniSelectedByO.getCellAt(cellPosSelectedByO);

        game.clearUsers();

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

        setPlayersUsers(game);

        game.select(miniPosSelectedByX.toDTO(), cellPosSelectedByX.toDTO());
        game.play();
        game.getBoard().getCellAt(cellPosSelectedByX).setOwner(Player.X);
        game.nextPlayer();

        game.select(miniPosSelectedByO.toDTO(), cellPosSelectedByO.toDTO());
        game.play();

        game.clearUsers();

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

        setPlayersUsers(game);

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
        setPlayersUsers(game);
        game.getBoard().getCellAt(miniPosSelectedByX).setOwner(Player.X);
        game.select(miniPosSelectedByX.toDTO(), cellPosSelectedByX.toDTO());
    }

    /**
     * Trying to select a position while no users have been set should cause an
     * exception.
     */
    @Test(expected = IllegalStateException.class)
    public void select_noUserHaveBeenSet() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        game.select(new Position().toDTO(), new Position().toDTO());
    }

    /**
     * Trying to select a position while only one user has been set should cause
     * an exception.
     */
    @Test(expected = IllegalStateException.class)
    public void select_onlyOneUserHasBeenSet() {
        UltimateTicTacToeGame game = new UltimateTicTacToeGame();
        game.clearUsers();
        game.getX().setUser(new User("BOBO", 1, 2, 3));
        game.select(new Position().toDTO(), new Position().toDTO());
    }

}
