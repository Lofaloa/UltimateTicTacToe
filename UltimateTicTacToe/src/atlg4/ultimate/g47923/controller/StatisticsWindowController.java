package atlg4.ultimate.g47923.controller;

import atlg4.ultimate.g47923.dto.UserDTO;
import atlg4.ultimate.g47923.exception.DataBaseException;
import atlg4.ultimate.g47923.model.Game;
import atlg4.ultimate.g47923.persistence.business.UsersFacade;
import atlg4.ultimate.g47923.view.View;
import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.event.ActionEvent;

/**
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

    public StatisticsWindowController(Game game, View view) {
        this.game = game;
        this.view = view;
    }

    @FXML
    private void showMenu(ActionEvent event) {
        view.showGameMenuWindow();
    }

    @FXML
    private void quit(ActionEvent event) {
        System.exit(0);
    }

    private void setCellFactories() {
        pseudonymColumn.setCellValueFactory(new PropertyValueFactory<>("pseudonym"));
        victoriesColumn.setCellValueFactory(new PropertyValueFactory<>("nbOfVictories"));
        exaequosColumn.setCellValueFactory(new PropertyValueFactory<>("nbOfExaequos"));
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
            view.showWarning("Error while accessing the database!", e.getMessage());
        }
    }

}