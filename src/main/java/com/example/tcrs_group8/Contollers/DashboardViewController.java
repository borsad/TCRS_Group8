package com.example.tcrs_group8.Contollers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class DashboardViewController {

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
        // Add your logout logic here
    }
}
