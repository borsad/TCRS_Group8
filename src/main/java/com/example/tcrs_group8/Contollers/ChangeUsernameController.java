package com.example.tcrs_group8.Contollers;

import com.example.tcrs_group8.Services.DBConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChangeUsernameController {
    SceneController sceneController = new SceneController();
    @FXML
    private TextField searchField;
    @FXML
    private Label lblErrors;
    @FXML
    private Label nameField;
    @FXML
    private Label currentUName;
    @FXML
    private Label newUNameLabel;
    @FXML
    private TextField newUNameLabelField;
    String userId;
    String name;

    @FXML

    public void clickSearchDriverLicense(ActionEvent actionEvent) {
        String dlNumber = searchField.getText();
        if (dlNumber.isEmpty()) {
            setLblError(Color.TOMATO, "Empty License Field");
        } else if (dlNumber.length() != 16) {
            setLblError(Color.TOMATO, "Please Enter 16 Digits");
        } else {
            String sql = "SELECT * FROM UserDetails Where DriverLicenseNumber = ?";
            try {
                PreparedStatement statement = DBConnector.getConnection().prepareStatement(sql);
                statement.setString(1, dlNumber);
                ResultSet rSet = statement.executeQuery();
                if (!rSet.next()) {
                    setLblError(Color.TOMATO, "Can't find the record");
                } else {
                    name = rSet.getString(3);
                    nameField.setText(name);
                    userId = rSet.getString(1);
                    System.out.println(userId + "UserIDddddddd");
                    getCurrentUserName();
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }

    public void getCurrentUserName() {
        String sql = "SELECT * FROM LoginCredentials Where UserId = ?";
        try {
            PreparedStatement statement = DBConnector.getConnection().prepareStatement(sql);
            statement.setString(1, userId);
            ResultSet rSet = statement.executeQuery();
            if (!rSet.next()) {
                Alert helpAlert = new Alert(Alert.AlertType.ERROR);
                helpAlert.setHeaderText("Error");
                helpAlert.setContentText("User with the license number is not registered");
                helpAlert.showAndWait();
            } else {
                currentUName.setText(rSet.getString(2));
                System.out.println("CurrentUname     " + currentUName);
                newUNameLabel.setVisible(true);
                newUNameLabelField.setVisible(true);
            }
        } catch (Exception ex) {
        }
    }

    @FXML
    private void setLblError(Color color, String text) {
        lblErrors.setTextFill(color);
        lblErrors.setText(text);
        System.out.println(text);
    }

    public void onLogutClick(ActionEvent actionEvent) throws IOException {
        sceneController.switchToSignInPage(actionEvent);
    }

    public void clickBackButton(ActionEvent actionEvent) throws IOException {
        sceneController.switchToUserManagement(actionEvent);
    }

    public void clickSubmit(ActionEvent actionEvent) {
        String sql = "UPDATE LoginCredentials SET Username = ? WHERE Username = ?";
        if (newUNameLabelField.getText().isEmpty()) {
            Alert helpAlert = new Alert(Alert.AlertType.ERROR);
            helpAlert.setHeaderText("Error");
            helpAlert.setContentText("Please Enter the new Username First");
            helpAlert.showAndWait();
        } else if (newUNameLabelField.getText().length() < 8) {
            Alert helpAlert = new Alert(Alert.AlertType.ERROR);
            helpAlert.setHeaderText("Error");
            helpAlert.setContentText("Username should have at least 8 characters");
            helpAlert.showAndWait();
        } else {
            try {
                PreparedStatement statement = DBConnector.getConnection().prepareStatement(sql);
                statement.setString(2, currentUName.getText());
                statement.setString(1, newUNameLabelField.getText());
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Record inserted successfully into LoginCredentials.");
                    sceneController.switchToAdminGenericSuccesspage(actionEvent);
                }
                else {
                    Alert helpAlert = new Alert(Alert.AlertType.ERROR);
                    helpAlert.setHeaderText("Error");
                    helpAlert.setContentText("Error");
                    helpAlert.showAndWait();                    }

            } catch (SQLException | IOException e) {
                throw new RuntimeException(e);
            }


        }
    }
}
