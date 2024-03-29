package com.example.tcrs_group8.Contollers;

import javafx.event.ActionEvent;

import java.io.IOException;

public class AdminGenericSuccessController {
SceneController sceneController=new SceneController();
    public void onBackButtonClick(ActionEvent actionEvent) throws IOException {
        sceneController.switchToAdminDashboard(actionEvent);
    }

    public void onLogutClick(ActionEvent actionEvent) throws IOException {
        sceneController.switchToSignInPage(actionEvent);
    }
}
