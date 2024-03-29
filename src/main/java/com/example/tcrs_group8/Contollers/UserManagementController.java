package com.example.tcrs_group8.Contollers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class UserManagementController {
    SceneController sceneController=new SceneController();

    public UserManagementController() {
    }


    public void logout(ActionEvent actionEvent) throws IOException {
        sceneController.switchToSignInPage(actionEvent);

    }

    public void clickChangeUserName(ActionEvent actionEvent) throws IOException {
        sceneController.switchToUserNameChangeDashBoard(actionEvent);
    }

    public void clickChangePassword(ActionEvent actionEvent) throws IOException {
        sceneController.switchToChangePassword(actionEvent);
    }
}
