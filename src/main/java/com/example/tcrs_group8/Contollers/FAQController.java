package com.example.tcrs_group8.Contollers;

import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

public class FAQController {

    @FXML
    private Accordion faqAccordion;

    @FXML
    private Button helpButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Hyperlink userManualLink;

    @FXML
    private Text supportEmail;

    @FXML
    private Text supportPhone;

    @FXML
    private Text supportAddress;

    // Initialize method is called after all @FXML annotated members have been injected
    public void initialize() {
        // You can initialize anything here if needed
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

    // Method for handling the 'User Manual' hyperlink action
    @FXML
    private void handleUserManualAction() {
        // Your logic for the 'User Manual' hyperlink action
        System.out.println("User Manual link clicked");
    }
}
