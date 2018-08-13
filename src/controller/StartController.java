package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class StartController{


    @FXML
    private void goToResources(ActionEvent event) {
        System.out.println("goToResources");
        PaneNavigator.loadPane(PaneNavigator.RESOURCES_PANE);
    }

}
