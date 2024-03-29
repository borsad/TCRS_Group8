package com.example.tcrs_group8.Contollers;

import com.example.tcrs_group8.Services.DBConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SignInController {
    public Stage stage =new Stage();
    SceneController sceneController =new SceneController();
    @FXML
    PasswordField passwordInput;
    @FXML
    TextField usernameField;
    @FXML
    private Label lblErrors;
    String name;
    OfficerDashboardController officerDashboardController=new OfficerDashboardController();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @FXML
    public void initialize(){
    }
    @FXML
    public void switchToSignUp(ActionEvent actionEvent) throws IOException {
        actionEvent.consume();
        sceneController.switchToSignUpPage(actionEvent);
    }
@FXML
    public void signIn(ActionEvent actionEvent) throws IOException {

   List<String> logInResult= logIn();
   if(logInResult.get(0).equalsIgnoreCase("success")){
       switch (logInResult.get(1)){
           case "1":
               sceneController.switchToUserDashboard(actionEvent);
               break;
           case "2":
               System.out.println("+++++++"+name);
               officerDashboardController.setOfficerName(name);
               sceneController.switchToOfficerDashboard(actionEvent);
               break;
           case "3":
               sceneController.switchToJudicialDashboard(actionEvent);
               break;
           case "4":
               sceneController.switchToTrafficSchoolDashboard(actionEvent);
               break;
           case "5":
               sceneController.switchToAdminDashboard(actionEvent);
       }
       //add redirection logic based on roles here
   }else {
       setLblError(Color.TOMATO, "Failed to sign in");
   }

    }
    @FXML
    private void setLblError(Color color, String text) {
        lblErrors.setTextFill(color);
        lblErrors.setText(text);
        System.out.println(text);
    }
    private List<String> logIn() {
        String roleId=null;
        String status = "Success";
        List<String> returnList=new ArrayList<>();
        String username = usernameField.getText();
        String password = passwordInput.getText();
        if(username.isEmpty() || password.isEmpty()) {
            setLblError(Color.TOMATO, "Empty credentials");
            status = "Error";
            returnList.add(status);
            returnList.add(roleId);
        } else {
            String sql = "SELECT * FROM LoginCredentials Where username = ? and password = ?";
            try {
               PreparedStatement preparedStatement = DBConnector.getConnection().prepareStatement(sql);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
               ResultSet resultSet = preparedStatement.executeQuery();
                if (!resultSet.next()) {
                    setLblError(Color.TOMATO, "Enter Correct Email/Password");
                    status = "Error";
                    returnList.add(status);
                    returnList.add(roleId);
                } else {
                    String userId= resultSet.getString(4);
                    String query="SELECT * FROM UserDetails Where UserId = ?";
                   PreparedStatement statement=DBConnector.getConnection().prepareStatement(query);
                   statement.setString(1,userId);
                    ResultSet rSet=statement.executeQuery();
                    rSet.next();
                    roleId =rSet.getString(5);
                    setLblError(Color.GREEN, "Login Successful..Redirecting..");
                    returnList.add(status);
                    returnList.add(roleId);
                    setName(rSet.getString(3));
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                status = "Exception";
                returnList.add(status);
                returnList.add(roleId);
            }
        }
        return returnList;}

    public void forgotPassword(ActionEvent actionEvent) {
        actionEvent.consume();
        Alert helpAlert = new Alert(Alert.AlertType.INFORMATION);
                        helpAlert.setTitle("Forgot Password Information");
                        helpAlert.setHeaderText("Forgot Password");
                        helpAlert.setContentText("Please call Ministry Customer Service on 1-800-444-2156 for help with resetting the password");
                        helpAlert.showAndWait();

    }
}
