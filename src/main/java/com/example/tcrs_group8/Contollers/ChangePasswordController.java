package com.example.tcrs_group8.Contollers;

import com.example.tcrs_group8.Services.DBConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import org.w3c.dom.Text;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChangePasswordController {
    @FXML
    private TextField searchField;
    @FXML
    private Label lblErrors;
    @FXML
    private Label currentPassword;
    @FXML
    private TextField newPasswordField;
    @FXML
    private Label newPasswordLabel;
    @FXML
    private Label userName;
    @FXML
    private Button submitButton;
    SceneController sceneController=new SceneController();
    public void onLogutClick(ActionEvent actionEvent) throws IOException {
        sceneController.switchToSignInPage(actionEvent);
    }

    public void clickBackButton(ActionEvent actionEvent) throws IOException {
        sceneController.switchToUserManagement(actionEvent);
    }

    public void clickSearchUsername(ActionEvent actionEvent) {
        String uName = searchField.getText();
        if (uName.isEmpty()) {
            setLblError(Color.TOMATO, "Empty License Field");
        } else if (uName.length()<8) {
            setLblError(Color.TOMATO, "invalid Username");
        }else{
            String sql = "SELECT * FROM LoginCredentials Where Username = ?";
            try {
                PreparedStatement statement = DBConnector.getConnection().prepareStatement(sql);
                statement.setString(1, uName);
                ResultSet rSet = statement.executeQuery();
                if (!rSet.next()) {
                    Alert helpAlert = new Alert(Alert.AlertType.ERROR);
                    helpAlert.setHeaderText("Error");
                    helpAlert.setContentText("Can't find Username");
                    helpAlert.showAndWait();
                } else {
                    userName.setText(rSet.getString(2));
                    currentPassword.setText(rSet.getString(3));
                    System.out.println("CurrentPassword     " + currentPassword);
                    newPasswordField.setVisible(true);
                    newPasswordLabel.setVisible(true);
                    submitButton.setVisible(true);

                }
            } catch (Exception ex) {
            }
        }

    }
    @FXML
    private void setLblError(Color color, String text) {
        lblErrors.setTextFill(color);
        lblErrors.setText(text);
        System.out.println(text);
    }

    public void clickSubmit(ActionEvent actionEvent) {
        String sql = "UPDATE LoginCredentials SET Password = ? WHERE Username = ?";
        if (newPasswordField.getText().isEmpty()) {
            Alert helpAlert = new Alert(Alert.AlertType.ERROR);
            helpAlert.setHeaderText("Error");
            helpAlert.setContentText("Please Enter the new Username First");
            helpAlert.showAndWait();
        } else if (newPasswordField.getText().length() < 8) {
            Alert helpAlert = new Alert(Alert.AlertType.ERROR);
            helpAlert.setHeaderText("Error");
            helpAlert.setContentText("Password should have at least 8 characters");
            helpAlert.showAndWait();
        }else {
            try {
                PreparedStatement statement = DBConnector.getConnection().prepareStatement(sql);
                statement.setString(2, userName.getText());
                statement.setString(1, newPasswordField.getText());
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
