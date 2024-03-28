package com.example.tcrs_group8.Contollers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class TrafficDashboardController {
    SceneController sceneController=new SceneController();
    public VBox rightSide;
    public VBox leftSide;
    @FXML
    private Button helpButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Button enrollButton;

    @FXML
    private TextField searchField;

    // Event handler for the Help button click
    @FXML
    void onHelpButtonClick(ActionEvent event) {
        System.out.println("Help button clicked");
        // Add your logic here
    }

    // Event handler for the Logout button click
    @FXML
    void onLogoutButtonClick(ActionEvent event) {
        System.out.println("Logout button clicked");
        // Add your logic here, such as navigating back to the login screen
    }

    // Event handler for the Enroll Driver button click
    @FXML
    void onEnrollButtonClick(ActionEvent event) {
        System.out.println("Enroll Driver button clicked");
        // Add your logic here, such as opening an enrollment form dialog
    }

    // Event handler for the search functionality
    @FXML
    void onSearchFieldAction(ActionEvent event) {
        String searchQuery = searchField.getText();
        System.out.println("Searching for: " + searchQuery);
        // Implement your search logic here
    }

    // Initialize method (optional), called after the FXML fields are populated
    @FXML
    public void initialize() {
        // Initialization code here, such as configuring components
        System.out.println("Traffic Dashboard UI initialized");
    }
@FXML
    public void onHelpClicked(ActionEvent actionEvent) throws IOException {
        sceneController.switchToFaqPage(actionEvent);
        System.out.println("Help clicked");
    }
@FXML
    public void onLogutClick(ActionEvent actionEvent) throws IOException {
        sceneController.switchToSignInPage(actionEvent);
        System.out.println("Logut clicked");
    }
}
