package com.example.tcrs_group8.Contollers;

import com.example.tcrs_group8.Models.DataModel;
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
import java.sql.Statement;
import java.util.Objects;

public class PayFineClientController {

    public TextField paymentStatus;
    public Button payFineButton;
    String CaseID, OfficerNotes, Fine, ResultID, Name, UserID, License, OffenseNumber,Paid;
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
    private void handlePayFine(ActionEvent actionEvent) throws IOException {
        String insertSql = "INSERT INTO payments (resultID, caseId, userId,Paid) VALUES (?, ?, ?,True)";
        try {
            PreparedStatement insertPay = DBConnector.getConnection().prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
            insertPay.setString(1, ResultID);
            insertPay.setString(2, CaseID);
            insertPay.setString(3, UserID);
            int rowsAffected = insertPay.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Record inserted successfully into LoginCredentials.");
                try (ResultSet generatedKeys = insertPay.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        DataModel dataModel = new DataModel();
                        dataModel.setData(generatedKeys.getString(1),amountField.getText());

                        SuccessPageController.setSharedData(dataModel);
                        sceneController.switchToFineSuccess(actionEvent);
                    }
                    else {
                        Alert helpAlert = new Alert(Alert.AlertType.ERROR);
                        helpAlert.setHeaderText("Error");
                        helpAlert.setContentText("Error in paying fine! contact sysadmin");
                        helpAlert.showAndWait();
                    }
                }
            } else {
                Alert helpAlert = new Alert(Alert.AlertType.ERROR);
                helpAlert.setHeaderText("Error");
                helpAlert.setContentText("Error in paying fine! contact sysadmin");
                helpAlert.showAndWait();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    // This method will be called when the "Search" button is clicked
    @FXML
    private void handleSearch() {
        String sql = "SELECT * FROM FineDetails2 Where CaseID=?";
        try {
            PreparedStatement preparedStatement = DBConnector.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, nameField11.getText());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Name = resultSet.getString("Name");
                OfficerNotes = resultSet.getString("OfficerNotes");
                License = resultSet.getString("DriverLicenseNumber");
                Fine = resultSet.getString("Fine");
                UserID = resultSet.getString("UserID");
                CaseID = resultSet.getString("CaseID");
                OffenseNumber = resultSet.getString("OffenseNumber");
                ResultID = resultSet.getString("ResultID");

                nameField.setText(resultSet.getString("Name"));
                violationField.setText(resultSet.getString("OfficerNotes"));
                licenseNumberField.setText(resultSet.getString("DriverLicenseNumber"));
                amountField.setText(resultSet.getString("Fine"));
                if(Objects.equals(resultSet.getString("Paid"), "1")){
                    paymentStatus.setText("You have already paid the fine.");
                    payFineButton.setDisable(false);
                }else {
                    paymentStatus.setText("You have not paid the fine yet.");
                    payFineButton.setDisable(false);
                    payFineButton.setVisible(true);

                }

            } else {
                Alert helpAlert = new Alert(Alert.AlertType.ERROR);
                helpAlert.setHeaderText("Error");
                helpAlert.setContentText("No results found for this violation number!");
                helpAlert.showAndWait();
            }
        } catch (SQLException ex) {
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
    }
    @FXML
    public void initialize() {
        payFineButton.setDisable(true);
        payFineButton.setVisible(false);
    }
}
