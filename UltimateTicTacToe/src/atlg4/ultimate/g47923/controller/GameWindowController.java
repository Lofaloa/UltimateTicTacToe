/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atlg4.ultimate.g47923.controller;

import atlg4.composant.g47923.MyTicTacToe;
import atlg4.ultimate.g47923.dto.PositionDTO;
import atlg4.ultimate.g47923.exception.UltimateTicTacToeException;
import atlg4.ultimate.g47923.model.Game;
import atlg4.ultimate.g47923.view.IllegalMoveAlert;
import atlg4.ultimate.g47923.view.View;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import static javafx.scene.layout.GridPane.getColumnIndex;
import static javafx.scene.layout.GridPane.getRowIndex;

/**
 *
 * @author Logan Farci (47923)
 */
public class GameWindowController implements Initializable {

    private final Game game;
    private final View view;

    @FXML
    private GridPane board;

    @FXML
    private Label playerOPseudonym;

    @FXML
    private Label playerXPseudonym;

    public GameWindowController(Game game, View view) {
        this.game = game;
        this.view = view;
    }

    private MyTicTacToe getTicTacToeAt(int row, int column) {
        MyTicTacToe target = null;
        for (Node node : board.getChildren()) {
            MyTicTacToe t = (MyTicTacToe) node;
            if (getRowIndex(node) == row && getColumnIndex(node) == column) {
                target = t;
            }
        }
        return target;
    }

    void clearBoard() {
        for (Node node : board.getChildren()) {
            MyTicTacToe tictactoe = (MyTicTacToe) node;
            tictactoe.initialize(null);
            tictactoe.displayWinner(null);
            tictactoe.getStyleClass().clear();
        }
    }

    @FXML
    private void withdraw(ActionEvent event) {
        String message = "You are about to withdraw and grant "
                + "the victory to your opponent!";
        if (view.askConfirmation(message)) {
            game.withdraw();
            if (view.askReplay()) {
                game.start();
            } else {
                System.exit(0);
            }
        }
    }

    @FXML
    private void startNewGame(ActionEvent event) {
        String message = "You are about to restart the game, all the "
                + "advancement will be lost!";
        if (game.isOver() || view.askConfirmation(message)) {
            game.start();
        }
    }

    @FXML
    private void quit(ActionEvent event) {
        String message = "You are about to quit the game, all the "
                + "advancement will be lost!";
        if (view.askConfirmation(message)) {
            System.exit(0);
        }
    }

    private void showIllegalMoveAlert(String msg) {
        Alert warning = new IllegalMoveAlert(msg);
        warning.show();
    }

    private void addHandlerAt(MyTicTacToe t, final int row, final int column) {
        t.addEventHandlerAt(row, column, event -> {
            try {
                PositionDTO mini = new PositionDTO(getRowIndex(t), getColumnIndex(t));
                PositionDTO cell = new PositionDTO(row, column);
                game.select(mini, cell);
                game.play();
                game.nextPlayer();
                if (game.isOver()) {
                    if (view.askReplay()) {
                        game.start();
                    }
                }
            } catch (UltimateTicTacToeException e) {
                showIllegalMoveAlert(e.getMessage());
            }
        });
    }

    private void addHandlersTo(MyTicTacToe t) {
        for (int row = 0; row < MyTicTacToe.SIZE; row++) {
            for (int column = 0; column < MyTicTacToe.SIZE; column++) {
                addHandlerAt(t, row, column);
            }
        }
    }

    private void addBoardHandlers() {
        for (Node child : board.getChildren()) {
            MyTicTacToe tictactoe = (MyTicTacToe) child;
            addHandlersTo(tictactoe);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addBoardHandlers();
    }

}
