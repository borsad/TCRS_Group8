package com.example.tcrs_group8.Contollers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import java.io.IOException;

public class PayFineClientController {
    SceneController sceneController = new SceneController();
    @FXML
    private TextField nameField;

    @FXML
    private TextField violationField;

    @FXML
    private TextField licenseNumberField;

    @FXML
    private TextField amountField;

    @FXML
    private TextField nameField11; // You might want to rename this ID to something more descriptive.

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
    @FXML
    public void clickBackButton(ActionEvent actionEvent) throws IOException {
        sceneController.switchToUserDashboard(actionEvent);
        System.out.println("clicked on back button");
    }
    @FXML
    public void onHelpClicked(ActionEvent actionEvent) throws IOException {
        sceneController.switchToFaqPage(actionEvent);
        System.out.println("Help clicked");
    }
    @FXML
    public void onLogutClick(ActionEvent actionEvent) throws IOException {
        sceneController.switchToSignInPage(actionEvent);
        System.out.println("Help clicked");
    }

}
