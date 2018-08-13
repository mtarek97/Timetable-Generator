package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Chromosome;
import model.Class;
import model.Course;
import model.Data;
import model.Driver;
import model.Instructor;
import model.Room;
import model.StudentsGroup;

public class ClassesController implements Initializable {
    @FXML
    private TableView<Class> classTable = new TableView<>();
    @FXML
    private TableColumn<model.Class, Integer> classIDCol;
    @FXML
    private TableColumn<model.Class, String> classGroupCol;
    @FXML
    private TableColumn<model.Class, String> classTypeCol;
    @FXML
    private TableColumn<model.Class, String> classCourseCol;
    @FXML
    private TableColumn<model.Class, String> classInstructorCol;
    @FXML
    private TableColumn<model.Class, String> classRoomCol;


    @FXML
    private ChoiceBox<StudentsGroup> newClassGroup;
    @FXML
    private ChoiceBox<String> newClassType;
    @FXML
    private ChoiceBox<Course> newClassCourse;
    @FXML
    private ChoiceBox<Instructor> newClassInstructor;
    @FXML
    private ChoiceBox<Room> newClassRoom;

    public ClassesController(){

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("initilaize");
        classIDCol.setCellValueFactory(cellData -> cellData.getValue().classID().asObject());
        classGroupCol.setCellValueFactory(cellData -> cellData.getValue().classGroup());
        classTypeCol.setCellValueFactory(cellData -> cellData.getValue().classType());
        classCourseCol.setCellValueFactory(cellData -> cellData.getValue().classCourse());
        classInstructorCol.setCellValueFactory(cellData -> cellData.getValue().classInstructor());
        classRoomCol.setCellValueFactory(cellData -> cellData.getValue().classRoom());

        classTable.setItems(PaneNavigator.getMainApp().getClassData());

        newClassGroup.setItems(PaneNavigator.getMainApp().getGroupData());
        newClassType.getItems().add("Lecture");
        newClassType.getItems().add("Section");
        newClassType.getItems().add("Lab");
        newClassCourse.setItems(PaneNavigator.getMainApp().getCourseData());
        newClassInstructor.setItems(PaneNavigator.getMainApp().getInstrutorData());
        newClassRoom.setItems(PaneNavigator.getMainApp().getRoomData());
    }

    @FXML
    private void handleDeleteClass() {
        int selectedIndex = classTable.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0) {
            classTable.getItems().remove(selectedIndex);
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(PaneNavigator.getMainApp().getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Class Selected");
            alert.setContentText("Please select a class in the table.");

            alert.showAndWait();
        }
    }

    private boolean classDataIsValid(){
        return true;
    }

    @FXML
    private void handleNewClass() {
        StudentsGroup group = newClassGroup.getValue();
        String type = newClassType.getValue();
        Course course = newClassCourse.getValue();
        Instructor instructor = newClassInstructor.getValue();
        Room room = newClassRoom.getValue();

        if(classDataIsValid()){
            PaneNavigator.getMainApp().getClassData().add(new Class(course,type,instructor,group,room));
        }
    }

    @FXML
    private void generateTable(ActionEvent event) {
        System.out.println("generateTable");

        //Data.setCourses((Course[]) PaneNavigator.getMainApp().getCourseData().stream().collect(Collectors.toList()).toArray());
        Data.setCourses(PaneNavigator.getMainApp().getCourseData().toArray(new Course[0]));
        Data.setWorkingDays(PaneNavigator.getMainApp().getWorkingDays());
        Data.setDaysPerWeek(Data.workingDays.size());
        Data.setPeriodsPerDay(PaneNavigator.getMainApp().getPeriodsCount());
        Data.setInstructors(PaneNavigator.getMainApp().getInstrutorData().toArray(new Instructor[0]));
        Data.setRooms(PaneNavigator.getMainApp().getRoomData().toArray(new Room[0]));
        Data.setStudentsGroups(PaneNavigator.getMainApp().getGroupData().toArray(new StudentsGroup[0]));
        Data.setClasses(PaneNavigator.getMainApp().getClassData().toArray(new Class[0]));
        Chromosome ans = Driver.generateTimeTable();
        if(ans==null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(PaneNavigator.getMainApp().getPrimaryStage());
            alert.setTitle("Warning!");
            alert.setHeaderText("No Generated Table");
            alert.setContentText("Can't generate a valid time table.");
            alert.showAndWait();
        }else{
            ArrayList<Class> generatedTable = new ArrayList<>();
            for(int i=0;i<ans.chromosomeLength;i++){
                int gene = ans.getGene(i);
                Class cls = Data.getClassByID(gene);
                // set day & preriod
                String day = Data.workingDays.get((i*Data.daysPerWeek/ans.chromosomeLength)%Data.daysPerWeek);
                int roomID = Data.rooms[(i*Data.rooms.length*Data.daysPerWeek/ans.chromosomeLength)%Data.rooms.length].getID();
                int period = (i%Data.periodsPerDay) + 1;
                cls.setDay(day);
                cls.setPeriod(period);
                cls.setAllowedRoom(Data.getRoomByID(roomID));
                generatedTable.add(cls);
            }
            PaneNavigator.getMainApp().setGeneratedTableData(generatedTable);
            PaneNavigator.loadPane(PaneNavigator.TABLE_PANE);
        }

    }

    @FXML
    private void handleClearAll() {
        classTable.getItems().clear();
    }
}
