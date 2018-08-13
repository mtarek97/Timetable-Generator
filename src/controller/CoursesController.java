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
import model.Course;

public class CoursesController implements Initializable {
    @FXML
    private TableView<Course> courseTable = new TableView<>();
    @FXML
    private TableColumn<Course, String> courseCodeCol;
    @FXML
    private TableColumn<Course, String> courseNameCol;
    @FXML
    private TextField newCourseCode;
    @FXML
    private TextField newCourseName;

    public CoursesController(){

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("initilaize");
        courseCodeCol.setCellValueFactory(cellData -> cellData.getValue().courseCode());
        courseNameCol.setCellValueFactory(cellData -> cellData.getValue().courseName());
        courseTable.setItems(PaneNavigator.getMainApp().getCourseData());
    }

    @FXML
    private void handleDeleteCourse() {
        int selectedIndex = courseTable.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0) {
            courseTable.getItems().remove(selectedIndex);
            PaneNavigator.getMainApp().getClassData().clear();

        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(PaneNavigator.getMainApp().getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Course Selected");
            alert.setContentText("Please select a course in the table.");

            alert.showAndWait();
        }
    }

    private boolean courseDataIsValid(String courseCode, String courseName){
        return true;
    }

    @FXML
    private void handleNewCourse() {
        String courseCode = newCourseCode.getText();
        String courseName = newCourseName.getText();

        if(courseDataIsValid(courseCode,courseName)){
            PaneNavigator.getMainApp().getCourseData().add(new Course(courseName,courseCode));
        }
    }

    @FXML
    private void goToInstructors(ActionEvent event) {
        System.out.println("goToInstructors");
        PaneNavigator.loadPane(PaneNavigator.INSTRUCTORS_PANE);
    }

    @FXML
    private void handleClearAll() {
        courseTable.getItems().clear();
        PaneNavigator.getMainApp().getClassData().clear();
    }
}
