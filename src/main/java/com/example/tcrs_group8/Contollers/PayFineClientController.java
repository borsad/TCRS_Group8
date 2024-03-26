package com.example.tcrs_group8.Contollers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

public class PayFineClientController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField violationField;

    @FXML
    private TextField licenseNumberField;

    @FXML
    private TextField amountField;

    @FXML
    private Button payFineButton;

    @FXML
    private TextField nameField11; // You might want to rename this ID to something more descriptive.

    @FXML
    private Button searchButton;

    // This method will be called when the "Pay The Fine" button is clicked
    @FXML
    private void handlePayFine() {
        // Implement your logic here
        System.out.println("Pay The Fine button clicked");
        System.out.println("Name: " + nameField.getText());
        System.out.println("Violation: " + violationField.getText());
        System.out.println("Driving License Number: " + licenseNumberField.getText());
        System.out.println("Amount: " + amountField.getText());
    }

    // This method will be called when the "Search" button is clicked
    @FXML
    private void handleSearch() {
        // Implement your logic here
        System.out.println("Search button clicked");
        System.out.println("Violation Number: " + nameField11.getText());
    }
}
