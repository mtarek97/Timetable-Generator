package view;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import controller.MainController;
import controller.PaneNavigator;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Class;
import model.Course;
import model.Instructor;
import model.Room;
import model.StudentsGroup;

public class App extends Application {

    private Stage primaryStage;
    private ObservableList<Room> RoomData = FXCollections.observableArrayList();
    private ObservableList<StudentsGroup> GroupData = FXCollections.observableArrayList();
    private ObservableList<Course> CourseData = FXCollections.observableArrayList();
    private ObservableList<Instructor> InstructorData = FXCollections.observableArrayList();
    private ObservableList<Class> ClassData = FXCollections.observableArrayList();
    private HashMap<String,Boolean> workingDays = new HashMap<>();
    private ObservableList<Class> generatedTableData = FXCollections.observableArrayList();
    private int periodsCount = 5;

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        initialize();
        primaryStage.setTitle("Time Table Generator");
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("file:resources/images/logo.png"));
        primaryStage.setScene(createScene(loadMainPane()));
        primaryStage.show();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void initialize(){
        System.out.println("init");
        Room room104 = new Room("104");
        Room room201 = new Room("201");
        Room room202 = new Room("202");
        Room room203 = new Room("203");
        RoomData.addAll(room104,room201,room202,room203);

        StudentsGroup firstYear = new StudentsGroup("First Year");
        StudentsGroup secondYear = new StudentsGroup("Second Year");
        StudentsGroup thirdYear = new StudentsGroup("Third Year");
        StudentsGroup fourthYear = new StudentsGroup("Fourth Year");
        GroupData.addAll(firstYear,secondYear,thirdYear,fourthYear);

        Course firstMPx14 = new Course("Mathematics 4","MPx14");
        Course firstCS111 = new Course("Probability","CS111");
        Course firstCS122 = new Course("Data Structures 1","CS122");
        Course firstCS131 = new Course("Computer Fundamentals","CS131");
        Course firstEEx11 = new Course("Electric Circuits","EEx11");
        Course firstHS172 = new Course("Computer and Society","HS172");
        CourseData.addAll(firstMPx14,firstCS111,firstCS122,firstCS131,firstEEx11,firstHS172);

        Instructor DrMervat = new Instructor("Dr Mervat");
        Instructor DrE3tdal = new Instructor("Dr E3tdal");
        Instructor DrSoheir = new Instructor("Dr Soheir");
        Instructor DrKhaled = new Instructor("Dr Khaled");
        Instructor DrNazeih = new Instructor("Dr Nazeih");
        Instructor DrHassan = new Instructor("Dr Hassan");
        Instructor DrAmal = new Instructor("Dr Amal");
        InstructorData.addAll(DrMervat,DrE3tdal,DrSoheir,DrKhaled,DrNazeih,DrHassan,DrAmal);

        Class digitalLec1 = new Class(firstCS131,"Lec",DrNazeih,firstYear,room104);
        Class societyLec = new Class(firstHS172,"Lec",DrAmal,firstYear,room104);
        Class DSLec1 = new Class(firstCS122,"Lec",DrKhaled,firstYear,room104);
        Class probLec1 = new Class(firstCS111,"Lec",DrSoheir,firstYear,room104);
        Class mathLec1 = new Class(firstMPx14,"Lec",DrMervat,firstYear,room104);
        Class EELec1 = new Class(firstEEx11,"Lec",DrHassan,firstYear,room104);
        Class digitalLec2 = new Class(firstCS131,"Lec",DrNazeih,firstYear,room104);
        Class probLec2 = new Class(firstCS111,"Lec",DrSoheir,firstYear,room104);
        Class mathLec2 = new Class(firstMPx14,"Lec",DrE3tdal,firstYear,room104);
        Class DSLec2 = new Class(firstCS122,"Lec",DrKhaled,firstYear,room104);
        Class EELec2 = new Class(firstEEx11,"Lec",DrHassan,firstYear,room104);

        Class digitalLec11 = new Class(firstCS131,"Lec",DrNazeih,secondYear,room201);
        Class societyLec1 = new Class(firstHS172,"Lec",DrAmal,secondYear,room201);
        Class DSLec11 = new Class(firstCS122,"Lec",DrKhaled,secondYear,room201);
        Class probLec11 = new Class(firstCS111,"Lec",DrSoheir,secondYear,room201);
        Class mathLec11 = new Class(firstMPx14,"Lec",DrMervat,secondYear,room201);
        Class EELec11 = new Class(firstEEx11,"Lec",DrHassan,secondYear,room201);
        Class digitalLec21 = new Class(firstCS131,"Lec",DrNazeih,secondYear,room201);
        Class probLec21 = new Class(firstCS111,"Lec",DrSoheir,secondYear,room201);
        Class mathLec21 = new Class(firstMPx14,"Lec",DrE3tdal,secondYear,room201);
        Class DSLec21 = new Class(firstCS122,"Lec",DrKhaled,secondYear,room201);
        Class EELec21 = new Class(firstEEx11,"Lec",DrHassan,secondYear,room201);

        ClassData.addAll(digitalLec1,societyLec,DSLec1,probLec1,mathLec1,EELec1,digitalLec2,probLec2,
                mathLec2,DSLec2,EELec2,digitalLec11,societyLec1,DSLec11,probLec11,mathLec11,EELec11,
                digitalLec21,probLec21,mathLec21,DSLec21,EELec21);

        workingDays.put("saturday",true);
        workingDays.put("sunday",true);
        workingDays.put("monday",true);
        workingDays.put("tuesday",true);
        workingDays.put("wednesday",true);
        workingDays.put("thursday",false);
    }

    private Pane loadMainPane() throws IOException {
        FXMLLoader loader = new FXMLLoader(new URL("file:resources/fxml/" + PaneNavigator.MAIN_PANE));
        //loader.setLocation(new URL("file:resources/fxml/" + PaneNavigator.MAIN_PANE));
        //Pane mainPane = loader.load();
        Pane mainPane = loader.load();
        MainController mainController = loader.getController();
        PaneNavigator.setMainApp(this);
        PaneNavigator.setMainController(mainController);
        PaneNavigator.loadPane(PaneNavigator.START_PANE);

        return mainPane;
    }

    private Scene createScene(Pane mainPane) {
        Scene scene = new Scene(mainPane);
        //new URL("file:resources/style/tab.css)
        File f = new File("resources/style/tab.css");
        scene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
        //scene.getStylesheets().add(this.getClass().getResource("/resources/style/tab.css").toExternalForm());
        f = new File("resources/style/style.css");
        scene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
        return scene;
    }


    public static void main(String[] args) {
        launch();
    }

    public ObservableList<Room> getRoomData() {
        return RoomData;
    }

    public ObservableList<StudentsGroup> getGroupData() {
        return GroupData;
    }

    public ObservableList<Course> getCourseData() {
        return CourseData;
    }

    public ObservableList<Instructor> getInstrutorData() {
        return InstructorData;
    }

    public ObservableList<Class> getClassData() {
        return ClassData;
    }

    public HashMap<String,Boolean> getWorkingDays() {
        return workingDays;
    }

    public int getPeriodsCount() {
        return periodsCount;
    }

    public void setPeriodsCount(int periodsCount) {
        this.periodsCount = periodsCount;
    }

    public ObservableList<Class> getGeneratedTableData() {
        return generatedTableData;
    }

    public void setGeneratedTableData(ArrayList<Class> generatedtable){
        generatedTableData.clear();
        generatedTableData.addAll(generatedtable);
    }
}
