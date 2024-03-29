package com.example.tcrs_group8.Contollers;

import com.example.tcrs_group8.Services.DBConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class OfficerDashboardController {
    public String getOfficerName() {
        return officerName;
    }

    public void setOfficerName(String officerName) {
        this.officerName = officerName;
    }
    public void initData(String data) {
        // Process the received data
    }
    static String officerName="Jane Smith";

    public TextField officerNotesText;
    public TextField pastOffenseText;
    public TextField offenseText;
    SceneController sceneController=new SceneController();
    String name = null;
    String userId;
    @FXML
    private Label lblErrors;
    @FXML
    private TextField searchField;
    @FXML
    private Label nameField;
    @FXML
    private Label licenseField;
    @FXML
    private Button submitButton;

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
                    licenseField.setText(rSet.getString(4));
                    userId = rSet.getString(1);
                    submitButton.setVisible(true);
                    System.out.println(userId + "UserIDddddddd");
                }
            } catch (SQLException ex) {
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
    public void clickSubmit(ActionEvent actionEvent) {
        if(offenseText.getText().isEmpty()||officerNotesText.getText().isEmpty()){
            Alert helpAlert = new Alert(Alert.AlertType.ERROR);
            helpAlert.setHeaderText("Blank Fields");
            helpAlert.setContentText("Can't have notes and text fields blank");
            helpAlert.showAndWait();
        }
        if(!offenseText.getText().matches("\\d+")){
            Alert helpAlert = new Alert(Alert.AlertType.ERROR);
            helpAlert.setHeaderText("Invalid Offense Number");
            helpAlert.setContentText("Offense Number can only be a number");
            helpAlert.showAndWait();
        }else{
            String sql="INSERT INTO `Cases`(`UserID`, `OfficerName`, `OfficerNotes`, `OffenceDate`, `OffenseNumber`, `PastOffenses`) \n" +
                    "VALUES (?, ?, ?, ?, ?, ?);";
            try {
                PreparedStatement preparedStatement = DBConnector.getConnection().prepareStatement(sql);
                preparedStatement.setString(1,userId);
                preparedStatement.setString(2, officerName);
                preparedStatement.setString(3,officerNotesText.getText());
                preparedStatement.setString(4,getFormattedSystemDate());
                preparedStatement.setString(5,offenseText.getText());
                preparedStatement.setString(6,pastOffenseText.getText());
                int modifiedRows=preparedStatement.executeUpdate();
                if (modifiedRows > 0) {
                    System.out.println("Record inserted successfully into Cases.");
                    Alert helpAlert = new Alert(Alert.AlertType.CONFIRMATION);
                    helpAlert.setTitle("Confirmation");
                    helpAlert.setHeaderText("Sucessfully updated the Record");
                    helpAlert.setContentText("The Case record has been updated");
                    helpAlert.showAndWait();
                    submitButton.setVisible(false);
                    nameField.setText("");
                    licenseField.setText("");
                    searchField.setText("");
                    offenseText.setText("");
                    pastOffenseText.setText("");
                    officerNotesText.setText("");
                }
                else {
                    Alert helpAlert = new Alert(Alert.AlertType.ERROR);
                    helpAlert.setHeaderText("Error");
                    helpAlert.setContentText("Error");
                    helpAlert.showAndWait();                    }

            }
             catch (SQLException e) {
                System.out.println(e);}
        }

    }
   public String getFormattedSystemDate(){
       // Get current system date
       LocalDate currentDate = LocalDate.now();

       // Format the date as a string in 'yyyy-MM-dd' format
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
       String formattedDate = currentDate.format(formatter);
       System.out.println("Current system date in yyyy-MM-dd format: " + formattedDate);
       return formattedDate;
       // Print the formatted date
   }
}
