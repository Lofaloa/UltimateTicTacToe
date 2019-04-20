package atlg4.ultimate.g47923.controller;

import atlg4.ultimate.g47923.dto.UserDTO;
import atlg4.ultimate.g47923.exception.DataBaseException;
import atlg4.ultimate.g47923.model.Game;
import atlg4.ultimate.g47923.persistence.business.UsersFacade;
import atlg4.ultimate.g47923.view.ErrorAlert;
import atlg4.ultimate.g47923.view.View;
import java.net.URL;
import java.util.Collection;
import static java.util.Objects.requireNonNull;
import java.util.ResourceBundle;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

/**
 * Controls the logic of the statistics window.
 *
 * @author Logan Farci (47923)
 */
public class StatisticsWindowController implements Initializable {

    private final Game game;
    private final View view;

    @FXML
    private TableView<UserDTO> statistics;

    @FXML
    private TableColumn<UserDTO, String> pseudonymColumn;

    @FXML
    private TableColumn<UserDTO, Integer> victoriesColumn;

    @FXML
    private TableColumn<UserDTO, Integer> exaequosColumn;

    @FXML
    private TableColumn<UserDTO, Integer> defeatsColumn;

    /**
     * Constructs an instance of StatisticsWindowController with the given game
     * and view.
     *
     * @param game is the given game.
     * @param view is the given view.
     */
    public StatisticsWindowController(Game game, View view) {
        this.game = requireNonNull(game, "Trying to construct a "
                + "StatisticsWindowController with a null game");
        this.view = requireNonNull(view, "Trying to construct a "
                + "StatisticsWindowController with a null view");
    }

    @FXML
    private void showMenu(ActionEvent event) {
        view.showGameMenuWindow();
    }

    @FXML
    private void quit(ActionEvent event) {
        String message = "Do you really want to quit?";
        if (!game.isOver() && view.askConfirmation(message)) {
            System.exit(0);
        }
    }

    private void setCellFactories() {
        pseudonymColumn.setCellValueFactory(new PropertyValueFactory<>("pseudonym"));
        victoriesColumn.setCellValueFactory(new PropertyValueFactory<>("nbOfVictories"));
        exaequosColumn.setCellValueFactory(new PropertyValueFactory<>("nbOfTies"));
        defeatsColumn.setCellValueFactory(new PropertyValueFactory<>("nbOfDefeats"));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            setCellFactories();
            Collection<UserDTO> usersCollection = UsersFacade.getUsers();
            ObservableList<UserDTO> users = observableArrayList(usersCollection);
            statistics.setItems(users);
        } catch (DataBaseException e) {
            Alert error = new ErrorAlert(e);
            error.show();
        }
    }

}
