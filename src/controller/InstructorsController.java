package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Instructor;

public class InstructorsController implements Initializable {
    @FXML
    private TableView<Instructor> instructorTable = new TableView<>();
    @FXML
    private TableColumn<Instructor, Integer> instructorIDCol;
    @FXML
    private TableColumn<Instructor, String> instructorNameCol;
    @FXML
    private TextField newInstructorName;

    public InstructorsController(){

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("initilaize");
        instructorIDCol.setCellValueFactory(cellData -> cellData.getValue().instructorID().asObject());
        instructorNameCol.setCellValueFactory(cellData -> cellData.getValue().instructorName());
        instructorTable.setItems(PaneNavigator.getMainApp().getInstrutorData());
    }

    @FXML
    private void handleDeleteInstructor() {
        int selectedIndex = instructorTable.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0) {
            instructorTable.getItems().remove(selectedIndex);
            PaneNavigator.getMainApp().getClassData().clear();

        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(PaneNavigator.getMainApp().getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Instructor Selected");
            alert.setContentText("Please select an instructor in the table.");

            alert.showAndWait();
        }
    }

    private boolean instructorNameIsValid(String instructorName){
        return true;
    }

    @FXML
    private void handleNewInstructor() {
        String instructorName = newInstructorName.getText();

        if(instructorNameIsValid(instructorName)){
            PaneNavigator.getMainApp().getInstrutorData().add(new Instructor(instructorName));
        }
    }

    @FXML
    private void goToClasses(ActionEvent event) {
        System.out.println("goToClasses");
        PaneNavigator.loadPane(PaneNavigator.CLASSES_PANE);
    }

    @FXML
    private void handleClearAll() {
        instructorTable.getItems().clear();
        PaneNavigator.getMainApp().getClassData().clear();
    }
}
