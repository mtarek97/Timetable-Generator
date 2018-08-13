package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class MainController implements Initializable{

    @FXML private StackPane paneHolder;
    @FXML private ToggleButton startTab;
    @FXML private ToggleButton resourcesTab;
    @FXML private ToggleButton coursesTab;
    @FXML private ToggleButton instructorsTab;
    @FXML private ToggleButton classesTab;
    @FXML private ToggleButton tableTab;

    private HashMap<String,ToggleButton> tabs =  new HashMap<>();

    private ToggleButton currentTab;

    public void setPane(Node node) {
        paneHolder.getChildren().setAll(node);
    }

    public void setCurrentTab(String tabID){
        currentTab = tabs.get(tabID);
        currentTab.setSelected(true);
    }


    public void navigateTab(ActionEvent event) {
        ToggleButton btn = (ToggleButton) event.getSource();
        btn.setSelected(true);
        currentTab = btn;
        String tab = btn.getText();
        System.out.println(tab);
        if(tab.equals("Start")){
            PaneNavigator.loadPane(PaneNavigator.START_PANE);
        }else if(tab.equals("Resources")){
            PaneNavigator.loadPane(PaneNavigator.RESOURCES_PANE);
        }else if(tab.equals("Courses")){
            PaneNavigator.loadPane(PaneNavigator.COURSES_PANE);
        }else if(tab.equals("Instructors")){
            PaneNavigator.loadPane(PaneNavigator.INSTRUCTORS_PANE);
        }else if(tab.equals("Classes")){
            PaneNavigator.loadPane(PaneNavigator.CLASSES_PANE);
        }else if(tab.equals("Table")){
            PaneNavigator.loadPane(PaneNavigator.TABLE_PANE);
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tabs.put("start",startTab);
        tabs.put("resources",resourcesTab);
        tabs.put("courses",coursesTab);
        tabs.put("instructors",instructorsTab);
        tabs.put("classes",classesTab);
        tabs.put("table",tableTab);
    }

}
