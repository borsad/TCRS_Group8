package com.example.tcrs_group8.Contollers;

import com.example.tcrs_group8.Services.DBConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class VehicleRegDashboardController {
    String dlNumber=null;
    String name=null;
    String userId;
    String vinNumber=null;
    String finalVin=null;
    String registrationId=null;
    SceneController sceneController=new SceneController();
    @FXML
    private Button helpButton;
    @FXML
    private Label makeResult;
    @FXML
    private Button logoutButton;

    @FXML
    private TextField searchField;
    @FXML
    private Label lblErrors;
    @FXML
    private Label makeNumber;
    @FXML
    private Label model;
    @FXML
    private Button searchButton;
    @FXML
    private Label modelResult;
    @FXML
    private Label licenseField;
    @FXML
    private Label nameField;
    @FXML
    private Label lblErrorsVin;



    @FXML
    private TextField addressField;

    // Assuming these fields are for a vehicle registration form based on the provided FXML structure
    @FXML
    private TextField makeField;

    @FXML
    private TextField modelTrimField;

    @FXML
    private TextField vinNumberField;

    @FXML
    private Button caseDetailsSearchButton; // Assuming this is the button within the "Case Details" GridPane

    @FXML
    private Button submitButton; // Assuming this is the "Submit" button for the form



    // Method called to initialize the controller after its root element has been completely processed
    @FXML
    void initialize() {
        // Initialization logic here
        System.out.println("Traffic Dashboard Controller Initialized");
    }

    @FXML
    public void onHelpClicked(ActionEvent actionEvent) throws IOException {
        sceneController.switchToFaqPage(actionEvent);
        System.out.println("Help clicked");
    }
    @FXML
    public void onLogutClick(ActionEvent actionEvent) throws IOException {
        sceneController.switchToSignInPage(actionEvent);
        System.out.println("Logout clicked");
    }
    @FXML
    public void clickBackButton(ActionEvent actionEvent) throws IOException {
        sceneController.switchToUserDashboard(actionEvent);
        System.out.println("clicked on back button");
    }

    public void clickSearchDriverLicense(ActionEvent actionEvent) throws SQLException {
        String dlNumber = searchField.getText();
        if (dlNumber.isEmpty()){
            setLblError(Color.TOMATO, "Empty License Field");
        } else if (dlNumber.length()!=16) {
            setLblError(Color.TOMATO, "Please Enter 16 Digits");
        } else{
            String sql = "SELECT * FROM UserDetails Where DriverLicenseNumber = ?";
            try {
            PreparedStatement statement= DBConnector.getConnection().prepareStatement(sql);
            statement.setString(1,dlNumber);
            ResultSet rSet=statement.executeQuery();
        if (!rSet.next()) {
            setLblError(Color.TOMATO, "Can't find the record");
        }else{ name= rSet.getString(3);
            nameField.setText(name);
            licenseField.setText(dlNumber);
            userId=rSet.getString(1);
            System.out.println(userId+"UserIDddddddd");
        }}catch (SQLException ex){
                System.err.println(ex.getMessage());
            }
        }

    }
    @FXML
    private void setLblError(Color color, String text) {
        lblErrors.setTextFill(color);
        lblErrors.setText(text);
        System.out.println(text);
    }
    @FXML
    private void setLblErrorVIN(Color color, String text) {
        lblErrors.setTextFill(color);
        lblErrors.setText(text);
        System.out.println(text);
    }
    public void vinSearchClick(ActionEvent actionEvent) {
        vinNumber = vinNumberField.getText();
        if (vinNumber.isEmpty()){
            setLblErrorVIN(Color.TOMATO, "Empty Vin Field");
        } else if (vinNumber.length()!=17) {
            setLblErrorVIN(Color.TOMATO, "Please Enter 17 Digits");
        } else{
            String sql = "SELECT * FROM Vehicle Where VIN = ?";
            try {
                PreparedStatement statement= DBConnector.getConnection().prepareStatement(sql);
                statement.setString(1,vinNumber);
                ResultSet rSet=statement.executeQuery();
                if (!rSet.next()) {
                    setLblErrorVIN(Color.TOMATO, "Can't find the record");
                }else{
                    makeResult.setText(rSet.getString(3));
                    modelResult.setText(rSet.getString(4));
                    finalVin=rSet.getString(2);
                    System.out.println(finalVin+"regIDDDDD");
                }}catch (SQLException ex){
                System.err.println(ex.getMessage());
            }
        }

    }

    public void clickSubmit(ActionEvent actionEvent) throws SQLException {
        if(makeResult.getText().isEmpty()){
            Alert helpAlert = new Alert(Alert.AlertType.ERROR);
            helpAlert.setHeaderText("Error");
            helpAlert.setContentText("Please Enter VIN First");
            helpAlert.showAndWait();

        } else if (nameField.getText().isEmpty()){
            Alert helpAlert = new Alert(Alert.AlertType.ERROR);
            helpAlert.setHeaderText("Error");
            helpAlert.setContentText("Please Enter Driver License Details First");
            helpAlert.showAndWait();
        } else if(userId == null||finalVin==null){
            Alert helpAlert = new Alert(Alert.AlertType.ERROR);
            helpAlert.setHeaderText("Error");
            helpAlert.setContentText("Please Refill information and try again");
            helpAlert.showAndWait();
          //  setLblErrorVIN(Color.TOMATO, "Please Refill information and try again");
        }else{
            try {
                String registrationCheckerQuery = "SELECT * FROM Registration Where VIN = ? AND UserID= ?";
                String insertSql = "INSERT INTO Registration (VIN, UserID) VALUES (?, ?)";
                PreparedStatement checkRegistrationExistsStatement = DBConnector.getConnection().prepareStatement(registrationCheckerQuery);
                checkRegistrationExistsStatement.setString(1, finalVin);
                checkRegistrationExistsStatement.setString(2, userId);
                ResultSet checkRegistrationExists = checkRegistrationExistsStatement.executeQuery();
                if (!checkRegistrationExists.next()) {
                    PreparedStatement statement = DBConnector.getConnection().prepareStatement(insertSql);
                    statement.setString(1, finalVin);
                    statement.setString(2, userId);
                    int rowsAffected = statement.executeUpdate();
                    if (rowsAffected > 0) {
                        Alert  helpAlert = new Alert(Alert.AlertType.CONFIRMATION);
                        helpAlert.setTitle("Confirmation");
                        helpAlert.setHeaderText("Successfully updated the Record");
                        helpAlert.setContentText("Vehicle Registered");
                        helpAlert.showAndWait();
                        System.out.println("Record inserted successfully into LoginCredentials.");
                        vinNumberField.setText("");
                        licenseField.setText("");
                        nameField.setText("");
                        makeResult.setText("");
                        modelResult.setText("");
                        searchField.setText("");
                    }
                    else {
                        Alert helpAlert = new Alert(Alert.AlertType.ERROR);
                        helpAlert.setHeaderText("Error");
                        helpAlert.setContentText("The Vehicle is already registered to the same user");
                        helpAlert.showAndWait();                    }

                }else{
                    Alert helpAlert = new Alert(Alert.AlertType.ERROR);
                    helpAlert.setHeaderText("Error");
                    helpAlert.setContentText("The Vehicle is already registered to the same user");
                    helpAlert.showAndWait();
                }

            }
            catch (SQLException ex) {
           System.err.println(ex.getMessage());}

        }

    }
}

