package com.example.tcrs_group8.Contollers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class UserManagementController {
    SceneController sceneController=new SceneController();

    public UserManagementController() {
    }

    // Method for handling the 'Help' button action
    @FXML
    private void handleHelpAction() {
        // Your logic for the 'Help' action
        System.out.println("Help button clicked");
    }

    // Method for handling the 'Logout' button action
    @FXML
    private void handleLogoutAction() {
        // Your logic for the 'Logout' action
        System.out.println("Logout button clicked");
    }

    public void clickChangeUserName(ActionEvent actionEvent) throws IOException {
        sceneController.switchToUserNameChangeDashBoard(actionEvent);
    }

    public void clickChangePassword(ActionEvent actionEvent) throws IOException {
        sceneController.switchToChangePassword(actionEvent);
    }
}
