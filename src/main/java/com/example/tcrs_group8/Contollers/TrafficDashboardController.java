package com.example.tcrs_group8.Contollers;

import com.example.tcrs_group8.Services.DBConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TrafficDashboardController {
    SceneController sceneController=new SceneController();
    public VBox rightSide;
    public VBox leftSide;
    @FXML
    private Button helpButton;

    public Button searchButton;
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

    @FXML
    void search(){
        String sql = "SELECT * FROM Cases Where caseID=1234567890";
        try {
            PreparedStatement preparedStatement = DBConnector.getConnection().prepareStatement(sql);
//            preparedStatement.setString(1,searchField.getText());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
//                caseDate.setText(resultSet.getString("OffenceDate"));
//                offenceNumber.setText(resultSet.getString("OffenceNumber"));
//                officerName.setText(resultSet.getString("OfficerName"));
//                officerNotes.setText(resultSet.getString("OfficerNotes"));
//                pastOffence.setText(resultSet.getString("PastOffences"));
//                sql = "SELECT * FROM UserDetails Where UserId = " + resultSet.getString("UserID");
//                preparedStatement = DBConnector.getConnection().prepareStatement(sql);
//                resultSet = preparedStatement.executeQuery();
//                if(resultSet.next()){
//                    licenseNumber.setText(resultSet.getString(4));
//                    name.setText(resultSet.getString("Name"));
//                }else{
//                    System.out.println("Error");
//                }

            }else{
                Alert helpAlert = new Alert(Alert.AlertType.ERROR);
                helpAlert.setHeaderText("Error");
                helpAlert.setContentText("No results found for this case number!");
                helpAlert.showAndWait();
            }
        }catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
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
