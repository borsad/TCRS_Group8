package com.example.tcrs_group8.Contollers;

import com.example.tcrs_group8.Services.DBConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        String sql = "SELECT * FROM FineDetails Where OffenseNumber=?";
        try {
            PreparedStatement preparedStatement = DBConnector.getConnection().prepareStatement(sql);
            preparedStatement.setString(1,nameField11.getText());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                nameField.setText(resultSet.getString("Name"));
                violationField.setText(resultSet.getString("OfficerNotes"));
                licenseNumberField.setText(resultSet.getString("DriverLicenseNumber"));
                amountField.setText(resultSet.getString("Fine"));
            }else{
                Alert helpAlert = new Alert(Alert.AlertType.ERROR);
                helpAlert.setHeaderText("Error");
                helpAlert.setContentText("No results found for this violation number!");
                helpAlert.showAndWait();
            }
        }catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
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
