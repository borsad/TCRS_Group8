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
    public TextField noOfSessions;
    public TextField trainingType;
    public TextField nameFeild;
    public TextField Lnumber;
    public TextField notes;
    SceneController sceneController = new SceneController();
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

        if (!(noOfSessions.getText().isEmpty() && (trainingType.getText().isEmpty()))) {
            try {

                String sql = "UPDATE TrafficSchoolEnrollment SET EnrollmentStatus = ?, NumberOfSessions = ? ,TrainingType=? WHERE caseID = ?";
                PreparedStatement preparedStatement = DBConnector.getConnection().prepareStatement(sql);
                preparedStatement.setString(1, "Enrolled");
                preparedStatement.setString(2, noOfSessions.getText());
                preparedStatement.setString(3, trainingType.getText());
                preparedStatement.setString(4, searchField.getText());
                int rowsAffected = preparedStatement.executeUpdate();
                Alert helpAlert;
                if (rowsAffected > 0) {
                    helpAlert = new Alert(Alert.AlertType.CONFIRMATION);
                    helpAlert.setTitle("Confirmation");
                    helpAlert.setHeaderText("Successfully updated the Record");
                    helpAlert.setContentText("User Enrolled Successfully !");
                    enrollButton.setDisable(true);
                    nameFeild.setText("");
                    Lnumber.setText("");
                    notes.setText("");
                    noOfSessions.setText("");
                    trainingType.setText("");
                    searchField.setText("");
                } else {
                    helpAlert = new Alert(Alert.AlertType.ERROR);
                    helpAlert.setHeaderText("Error");
                    helpAlert.setContentText("Error in updating queries ! Please check if the user details to see if the user has a order for Traffic school!");
                }
                helpAlert.showAndWait();
            } catch (SQLException e) {
                System.out.println(e);
                Alert helpAlert = new Alert(Alert.AlertType.ERROR);
                helpAlert.setHeaderText("Error");
                helpAlert.setContentText("Error in updating queries !");
                helpAlert.showAndWait();
            }
        } else {
            Alert helpAlert = new Alert(Alert.AlertType.ERROR);
            helpAlert.setHeaderText("Error");
            helpAlert.setContentText("Please fill all the fields! ");
            helpAlert.showAndWait();
        }
    }

    // Event handler for the search functionality
    @FXML
    void onSearchFieldAction(ActionEvent event) {
        String searchQuery = searchField.getText();
        System.out.println("Searching for: " + searchQuery);
        // Implement your search logic here
    }

    @FXML
    void search() {
        noOfSessions.setText("");
        trainingType.setText("");
        String sql = "SELECT * FROM CaseUserResultDetails Where caseID=?";
        try {
            PreparedStatement preparedStatement = DBConnector.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, searchField.getText());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                nameFeild.setText(resultSet.getString("UserName"));
                Lnumber.setText(resultSet.getString("DriverLicenseNumber"));
                notes.setText(resultSet.getString("Notes"));
                enrollButton.setDisable(false);
                sql = "SELECT * FROM TrafficSchoolEnrollment Where caseID=?";
                preparedStatement = DBConnector.getConnection().prepareStatement(sql);
                preparedStatement.setString(1, searchField.getText());
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    if(!(resultSet.getString("NumberOfSessions") == null)){
                        noOfSessions.setText(resultSet.getString("NumberOfSessions"));
                        trainingType.setText(resultSet.getString("TrainingType"));
                        enrollButton.setText("Update Enrolment");
                    }else{
                        enrollButton.setText("Enroll Driver");
                    }
                }
            } else {
                Alert helpAlert = new Alert(Alert.AlertType.ERROR);
                helpAlert.setHeaderText("Error");
                helpAlert.setContentText("No results found for this case number!");
                helpAlert.showAndWait();
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    // Initialize method (optional), called after the FXML fields are populated
    @FXML
    public void initialize() {
        // Initialization code here, such as configuring components
        enrollButton.setDisable(true);
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
