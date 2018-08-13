package controller;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Room;
import model.StudentsGroup;

public class ResourcesController implements Initializable{

    @FXML private CheckBox saturdayCheckBox;
    @FXML private CheckBox sundayCheckBox;
    @FXML private CheckBox mondayCheckBox;
    @FXML private CheckBox tuesdayCheckBox;
    @FXML private CheckBox wednesdayCheckBox;
    @FXML private CheckBox thursdayCheckBox;

    @FXML
    private TableView<Room> roomTable = new TableView<>();
    @FXML
    private TableColumn<Room, Integer> roomIDCol;
    @FXML
    private TableColumn<Room, String> roomNameCol;

    @FXML
    private TableView<StudentsGroup> groupTable = new TableView<>();
    @FXML
    private TableColumn<StudentsGroup, Integer> groupIDCol;
    @FXML
    private TableColumn<StudentsGroup, String> groupNameCol;

    @FXML
    private TextField periodsCount;

    @FXML
    private TextField newRoomName;

    @FXML
    private TextField newGroupName;

    public ResourcesController(){

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("initilaize");
        roomIDCol.setCellValueFactory(cellData -> cellData.getValue().roomID().asObject());
        roomNameCol.setCellValueFactory(cellData -> cellData.getValue().roomName());

        roomTable.setItems(PaneNavigator.getMainApp().getRoomData());

        groupIDCol.setCellValueFactory(cellData -> cellData.getValue().groupID().asObject());
        groupNameCol.setCellValueFactory(cellData -> cellData.getValue().groupName());

        groupTable.setItems(PaneNavigator.getMainApp().getGroupData());

        HashMap<String, Boolean> workingDaysMap = PaneNavigator.getMainApp().getWorkingDays();
        saturdayCheckBox.setSelected(workingDaysMap.get("saturday"));
        sundayCheckBox.setSelected(workingDaysMap.get("sunday"));
        mondayCheckBox.setSelected(workingDaysMap.get("monday"));
        tuesdayCheckBox.setSelected(workingDaysMap.get("tuesday"));
        wednesdayCheckBox.setSelected(workingDaysMap.get("wednesday"));
        thursdayCheckBox.setSelected(workingDaysMap.get("thursday"));
        periodsCount.setText(String.valueOf(PaneNavigator.getMainApp().getPeriodsCount()));

    }

    @FXML
    private void handleDeleteRoom() {
        int selectedIndex = roomTable.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0) {
            roomTable.getItems().remove(selectedIndex);
            PaneNavigator.getMainApp().getClassData().clear();
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(PaneNavigator.getMainApp().getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Room Selected");
            alert.setContentText("Please select a room in the table.");

            alert.showAndWait();
        }
    }

    @FXML
    private void handleDeleteGroup() {
        int selectedIndex = groupTable.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0) {
            groupTable.getItems().remove(selectedIndex);
            PaneNavigator.getMainApp().getClassData().clear();
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(PaneNavigator.getMainApp().getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Group Selected");
            alert.setContentText("Please select a group in the table.");

            alert.showAndWait();
        }
    }

    private boolean roomNameIsValid(String roomName){
        return true;
    }
    private boolean groupNameIsValid(String groupName){
        return true;
    }

    @FXML
    private void handleNewRoom() {
        String roomName = newRoomName.getText();
        if(roomNameIsValid(roomName)){
            PaneNavigator.getMainApp().getRoomData().add(new Room(roomName));
        }
    }

    @FXML
    private void handleNewGroup() {
        String groupName = newGroupName.getText();
        if(groupNameIsValid(groupName)){
            PaneNavigator.getMainApp().getGroupData().add(new StudentsGroup(groupName));
        }
    }

    @FXML
    private void goToCourses(ActionEvent event) {
        System.out.println("goToCourses");
        HashMap<String, Boolean> workingDaysMap = PaneNavigator.getMainApp().getWorkingDays();
        workingDaysMap.put("saturday",saturdayCheckBox.isSelected());
        workingDaysMap.put("sunday",sundayCheckBox.isSelected());
        workingDaysMap.put("monday",mondayCheckBox.isSelected());
        workingDaysMap.put("tuesday",tuesdayCheckBox.isSelected());
        workingDaysMap.put("wednesday",wednesdayCheckBox.isSelected());
        workingDaysMap.put("thursday",thursdayCheckBox.isSelected());
        PaneNavigator.getMainApp().setPeriodsCount(Integer.valueOf(periodsCount.getText()));
        PaneNavigator.loadPane(PaneNavigator.COURSES_PANE);
    }

    @FXML
    private void handleClearAll() {
        roomTable.getItems().clear();
        groupTable.getItems().clear();
        PaneNavigator.getMainApp().getClassData().clear();
    }

}
