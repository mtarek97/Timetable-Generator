package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Class;

public class TableController implements Initializable {

    @FXML
    private TableView<Class> generatedTable = new TableView<>();
    @FXML
    private TableColumn<Class, String> tableDayCol;
    @FXML
    private TableColumn<Class, String> tableRoomCol;
    @FXML
    private TableColumn<Class, Integer> tablePeriodCol;
    @FXML
    private TableColumn<Class, String> tableClassCol;


    public TableController(){

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("initilaize");
        tableDayCol.setCellValueFactory(cellData -> cellData.getValue().classDay());
        tableRoomCol.setCellValueFactory(cellData -> cellData.getValue().classRoom());
        tablePeriodCol.setCellValueFactory(cellData -> cellData.getValue().classPeriod().asObject());
        tableClassCol.setCellValueFactory(cellData -> cellData.getValue().className());
        generatedTable.setItems(PaneNavigator.getMainApp().getGeneratedTableData());
    }

}
