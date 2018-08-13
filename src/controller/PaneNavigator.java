package controller;

import view.App;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.net.URL;

public class PaneNavigator {

    public static final String MAIN_PANE   = "main.fxml";
    public static final String START_PANE   = "start.fxml";
    public static final String RESOURCES_PANE = "resources.fxml";
    public static final String COURSES_PANE = "courses.fxml";
    public static final String INSTRUCTORS_PANE = "instructors.fxml";
    public static final String CLASSES_PANE = "classes.fxml";
    public static final String TABLE_PANE = "table.fxml";

    private static MainController mainController;
    private static App mainApp;

    public static void setMainController(MainController mainController) {
        PaneNavigator.mainController = mainController;
    }

    public static void setMainApp(App mainApp){
        PaneNavigator.mainApp = mainApp;
    }

    public static App getMainApp(){
        return mainApp;
    }

    public static void loadPane(String fxml) {
        try {
            String[] dir = fxml.split("\\.");
            String tabID = dir[dir.length - 2];
            mainController.setCurrentTab(tabID);
            //getClass().getResourceAsStream("/resources/fxml/" + fxml);
            mainController.setPane(FXMLLoader.load( new URL("file:resources/fxml/" + fxml)));
            //mainController.setPane(FXMLLoader.load(PaneNavigator.class.getResource("/resources/fxml/" + fxml)));
            //mainController.setPane(FXMLLoader.load(this.getClass().getResource("/resources/fxml/" + fxml)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
