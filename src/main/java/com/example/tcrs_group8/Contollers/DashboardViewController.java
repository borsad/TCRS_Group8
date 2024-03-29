package com.example.tcrs_group8.Contollers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class DashboardViewController {
    @FXML
    SceneController sceneController=new SceneController();
    @FXML
    private Button reportManagementButton;

    @FXML
    private Button citationManagementButton;

    @FXML
    private Button userManagementButton;

    @FXML
    private Button finePaymentManagementButton;

    @FXML
    private Button logoutButton;

    @FXML
    public void initialize() {
        // Initialize your controller here if needed
    }

    @FXML
    private void handleReportManagementAction() {
        System.out.println("Report Management button clicked");
        // Add your handling code here
    }

    @FXML
    private void handleCitationManagementAction() {
        System.out.println("Citation Management button clicked");
        // Add your handling code here
    }

    @FXML
    private void handleUserManagementAction() {
        System.out.println("User Management button clicked");
        // Add your handling code here
    }

    @FXML
    private void handleFinePaymentManagementAction() {
        System.out.println("Fine Payment Management button clicked");
        // Add your handling code here
    }

    @FXML
    private void handleLogoutAction() {
        System.out.println("Logout button clicked");

    }
    @FXML
    public void clickUserManagement(ActionEvent actionEvent) throws IOException {
        sceneController.switchToUserManagement(actionEvent);

    }

    @FXML
    public void onLogutClick(ActionEvent actionEvent) throws IOException {
        sceneController.switchToSignInPage(actionEvent);
    }
}
