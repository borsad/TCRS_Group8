package com.example.tcrs_group8.Contollers;

import com.example.tcrs_group8.Services.DBConnector;
import com.example.tcrs_group8.Services.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.paint.Color;

import java.awt.desktop.SystemEventListener;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JudicialDashboardController {
    SceneController sceneController= new SceneController();
    public Button searchButton;
    @FXML
    private Button helpButton;

    @FXML
    private Button logoutButton;

    @FXML
    private TextField searchField;

    @FXML
    private Button fineButton;

    @FXML
    private Button assignButton;

    @FXML
    private Button dismissButton;

    @FXML private TextField caseDate;
    @FXML private TextField offenceNumber;
    @FXML private TextField officerName;
    @FXML private TextField officerNotes;
    @FXML private TextField pastOffence;
    @FXML private TextField licenseNumber;
    @FXML private TextField name;

    @FXML private TextField fineField;
    Utils u = new Utils();
    String values;

    @FXML
    void handleFineButtonAction(ActionEvent event) {
        System.out.println("Fine the Driver button clicked");
        // Implement your logic here
    }

    @FXML
    void handleAssignButtonAction(ActionEvent event) {
        System.out.println("Assign Training button clicked");
        // Implement your logic here
    }

    @FXML
    void handleDismissButtonAction(ActionEvent event) {
        System.out.println("Dismiss Charge button clicked");
        // Implement your logic here
    }

    @FXML
    void handleHelpButtonAction(ActionEvent event) {
        System.out.println("Help button clicked");
        // Implement your logic here
    }

    @FXML
    void handleLogoutButtonAction(ActionEvent event) {
        System.out.println("Logout button clicked");
        // Implement your logic here
    }
    @FXML
    void handleSearchButtonAction(ActionEvent event) {
        String sql = "SELECT * FROM Cases Where caseID=?";
        try {
            PreparedStatement preparedStatement = DBConnector.getConnection().prepareStatement(sql);
            preparedStatement.setString(1,searchField.getText());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                caseDate.setText(resultSet.getString("OffenceDate"));
                offenceNumber.setText(resultSet.getString("OffenseNumber"));
                officerName.setText(resultSet.getString("OfficerName"));
                officerNotes.setText(resultSet.getString("OfficerNotes"));
                pastOffence.setText(resultSet.getString("PastOffenses"));
                sql = "SELECT * FROM UserDetails Where UserId = " + resultSet.getString("UserID");
                preparedStatement = DBConnector.getConnection().prepareStatement(sql);
                resultSet = preparedStatement.executeQuery();
                fineField.setVisible(true);
                fineButton.setVisible(true);
                assignButton.setVisible(true);
                dismissButton.setVisible(true);
                if(resultSet.next()){
                    licenseNumber.setText(resultSet.getString(4));
                    name.setText(resultSet.getString("Name"));
                }else{
                    System.out.println("Error");
                }

            }else{
                Alert helpAlert = new Alert(Alert.AlertType.ERROR);
                helpAlert.setHeaderText("Error");
                helpAlert.setContentText("No results found for this case number!");
                helpAlert.showAndWait();
            }
        }catch (SQLException ex) {
                    System.err.println(ex.getMessage());
            Alert helpAlert = new Alert(Alert.AlertType.ERROR);
            helpAlert.setHeaderText("Error");
            helpAlert.setContentText("Database Error Occurred! Please contact sysadmin");
            helpAlert.showAndWait();
                }
        catch (RuntimeException e){
            Alert helpAlert = new Alert(Alert.AlertType.ERROR);
            helpAlert.setHeaderText("Error");
            helpAlert.setContentText("Database Error Occurred! Please contact sysadmin");
            helpAlert.showAndWait();
        }
    }

    @FXML
    void addFine() {
        values = "null,"+searchField.getText()+",'Fine',"+fineField.getText();
        int k=u.insert("CaseResults",values);
        if(k>0){
            System.out.println("done");
        }else{
            System.out.println("F");
        }

    }

    @FXML
    public void assignTrafficSchool() {
        values = "null,"+searchField.getText()+",'Traffic School',null";
        String sql= "Insert into CaseResults(CaseID,ResultType) values(?,'Traffic School')";
        try{
        PreparedStatement preparedStatement = DBConnector.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1,searchField.getText());

        int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    System.out.println(generatedKeys.getString(1));
                    sql= "Insert into TrafficSchoolEnrollment(resultID,caseId) values(?,?)";
                    preparedStatement = DBConnector.getConnection().prepareStatement(sql);
                    preparedStatement.setString(1,generatedKeys.getString(1));
                    preparedStatement.setString(2,searchField.getText());
                    affectedRows = preparedStatement.executeUpdate();
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        }
        catch (SQLException e){
            System.out.println(e);
        }
    }
    @FXML
    public void dismissCase() {
        String sql= "Insert into CaseResults(CaseID,ResultType) values(?,'Case Dismissed')";
        try{PreparedStatement preparedStatement = DBConnector.getConnection().prepareStatement(sql);
            preparedStatement.setString(1,searchField.getText());
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                Alert helpAlert = new Alert(Alert.AlertType.ERROR);
                helpAlert.setHeaderText("Can't Update");
                helpAlert.setContentText("Can't Update");
                helpAlert.showAndWait();
                throw new SQLException("Creating user failed, no rows affected.");
            }else{
                Alert helpAlert = new Alert(Alert.AlertType.CONFIRMATION);
                helpAlert.setHeaderText("Records Updated");
                helpAlert.setContentText("Records Updated");
                helpAlert.showAndWait();
                fineField.setVisible(false);
                dismissButton.setVisible(false);
                assignButton.setVisible(false);
                fineButton.setVisible(false);
                searchField.setText("");
                name.setText("");
                licenseNumber.setText("");
                pastOffence.setText("");
                officerNotes.setText("");
                offenceNumber.setText("");
                caseDate.setText("");
                officerName.setText("");

            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    @FXML
    public void initialize() {
        // Initialization code can go here
        System.out.println("Judicial Dashboard UI initialized");
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
