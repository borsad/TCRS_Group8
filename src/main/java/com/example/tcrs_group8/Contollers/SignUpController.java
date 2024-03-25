package com.example.tcrs_group8.Contollers;

import com.example.tcrs_group8.HelloApplication;
import com.example.tcrs_group8.Services.DBConnector;
import com.example.tcrs_group8.Services.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class SignUpController {
    public Stage stage;
    public Scene scene;
    private Parent root;
    SceneController sceneController = new SceneController();
    @FXML
    PasswordField passwordInput;
    @FXML
    TextField usernameField;
    @FXML
    private Label lblErrors;
    @FXML
    TextField uniqueCodeField;

    @FXML
    public void switchToSignIn(ActionEvent event) throws IOException {
        event.consume();
        sceneController.switchToSignInPage(event);
    }

    public void signUpClick(ActionEvent actionEvent) throws IOException {
String signUpResult= signUp();
if(signUpResult.equalsIgnoreCase("success")){
sceneController.switchToSignInPage(actionEvent);
}else{
    System.out.println("SignUp Failed");
}

    }

    public String signUp() {
        String userId = null;
        String status = "success";
        String username = usernameField.getText();
        String password = passwordInput.getText();
        String uniqueCode = uniqueCodeField.getText();
        if (username.isEmpty() || password.isEmpty() || uniqueCode.isEmpty()) {
            //checks if any of the creds are empty
            setLblError(Color.TOMATO, "Empty credentials");
            status = "Error";
        } else if (uniqueCode.length()!=12) {
            //checks if uniquecode is not 12 digits long
            setLblError(Color.TOMATO, "Invalid UniqueCode");
            status = "Error";
        } else {

            String sql = "SELECT * FROM UserDetails Where UniqueCode = ?";
            try {//trys looking for entry corresponding to the unique code
                PreparedStatement preparedStatement = DBConnector.getConnection().prepareStatement(sql);
                preparedStatement.setString(1, uniqueCode);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (!resultSet.next()) {
                    //shows inline error if the unique doesn't exist
                    setLblError(Color.TOMATO, "Enter Correct UniqueCode");
                    status = "Error";
                } else {//gets password, uname, userId ready
                    String insertSql = "INSERT INTO LoginCredentials (Username, Password, UserID) VALUES (?, ?, ?)";
                    userId = resultSet.getString(1);
                    String checkIfUserExistsQuery="SELECT * FROM LoginCredentials Where UserID = ?";
                    PreparedStatement checkUserExistsStatement=DBConnector.getConnection().prepareStatement(checkIfUserExistsQuery);
                       checkUserExistsStatement.setString(1,userId);
                       ResultSet checkUserExists=checkUserExistsStatement.executeQuery();
                       if(!checkUserExists.next()){
                           //passes the query if the user doesn't already exist
                    PreparedStatement statement = DBConnector.getConnection().prepareStatement(insertSql);
                    statement.setString(1,username);
                    statement.setString(2, password);
                    statement.setString(3,userId);
                    int rowsAffected = statement.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.println("Record inserted successfully into LoginCredentials.");
                        status="success";
                    } else {
                        System.out.println("Failed to insert record into LoginCredentials.");
                        status="Error";
                    }}else {//inline error if user already exists
                           setLblError(Color.TOMATO, "User already Exists in the system please use sign in page");
                           status = "Error";

                       }
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                status = "Exception";
            }

        }
        return status;

    }

    @FXML
    private void setLblError(Color color, String text) {
        lblErrors.setTextFill(color);
        lblErrors.setText(text);
        System.out.println(text);
    }
}