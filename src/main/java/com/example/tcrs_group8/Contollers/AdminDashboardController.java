package com.example.tcrs_group8.Contollers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import java.io.IOException;

public class AdminDashboardController {
    SceneController sceneController=new SceneController();
    @FXML
    private TableView<?> tableView;

    @FXML
    private void onAddClicked() {
        System.out.println("Add clicked");
    }

    @FXML
    private void onUpdateClicked() {
        System.out.println("Update clicked");
    }

    @FXML
    private void onDeleteClicked() {
        System.out.println("Delete clicked");
    }

    @FXML
    private void onReportClicked() {
        System.out.println("Generate Report clicked");
    }

    @FXML
    private void onHelpClicked() {
        System.out.println("Help clicked");
    }

    @FXML
    private void onHelpClicked(ActionEvent actionEvent) throws IOException {
        sceneController.switchToFaqPage(actionEvent);
        System.out.println("Help clicked");
    }

    @FXML
    private void onLogutClick(ActionEvent actionEvent) throws IOException {
        sceneController.switchToSignInPage(actionEvent);
        System.out.println("Logout clicked");
    }
}
