/**COMMENT !
 Author: Group 8
 Name: Asem AL Dwaikat ID: 239386640
 Name:Sonal Koggala Liyanage ID:249601680
 Name:
 Name:
 COSC 3506 Section A
 Any and all work in this file is our own.
 !**/

package com.example.tcrs_group8;

import com.example.tcrs_group8.Services.Utils;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import javax.swing.text.html.ImageView;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TrafficDashboardUI extends Application {

    // Declare class-level fields for form fields
    private TextField caseIDField;
    private TextField userIDField;
    private TextField officerNameField;
    private TextArea officerNotesArea;
    private DatePicker offenceDatePicker;
    private TextField offenceNumberField;
    private TextField pastOffencesField;

    @Override
    public void start(Stage stage) {
        // Help button and Logout hyperlink
        Button helpButton = new Button("Help");
        Hyperlink logoutLink = new Hyperlink("Logout");

        // Menu container
        HBox menuContainer = new HBox(10);
        menuContainer.setAlignment(Pos.CENTER_LEFT);
        menuContainer.setPadding(new Insets(10));
        menuContainer.getChildren().addAll(helpButton, logoutLink);

        // Initialize form fields
        caseIDField = new TextField();
        caseIDField.setPromptText("Case ID");

        userIDField = new TextField();
        userIDField.setPromptText("User ID");

        officerNameField = new TextField();
        officerNameField.setPromptText("Officer Name");

        officerNotesArea = new TextArea();
        officerNotesArea.setPromptText("Officer Notes");

        offenceDatePicker = new DatePicker();
        offenceDatePicker.setPromptText("Offence Date");

        offenceNumberField = new TextField();
        offenceNumberField.setPromptText("Offence Number");

        pastOffencesField = new TextField();
        pastOffencesField.setPromptText("Past Offences");

        // Submit button
        Button submitButton = new Button("Submit");
        submitButton.setStyle("-fx-background-color: #0000FF; -fx-text-fill: white;"); // Set button background color
        submitButton.setOnAction(event -> submitForm());

        // Form layout
        VBox formLayout = new VBox(10);
        formLayout.setPadding(new Insets(10, 20, 20, 20)); // Adjusted padding
        formLayout.setAlignment(Pos.CENTER_LEFT);
        formLayout.getChildren().addAll(
                caseIDField,
                userIDField,
                officerNameField,
                officerNotesArea,
                offenceDatePicker,
                offenceNumberField,
                pastOffencesField
        );

        // Center the Submit button
        HBox submitContainer = new HBox(submitButton);
        submitContainer.setAlignment(Pos.CENTER);

        // Main layout
        BorderPane mainLayout = new BorderPane();
        mainLayout.setTop(menuContainer);
        mainLayout.setCenter(formLayout);
        mainLayout.setBottom(submitContainer);

        // Create scene and set the stage
        Scene scene = new Scene(mainLayout, 600, 400);
        stage.setTitle("Traffic Offence Report Form");
        stage.setScene(scene);
        stage.show();
    }

    // Method to handle form submission
    private void submitForm() {
        // Get the values from the form fields
        String caseID = caseIDField.getText();
        String uniqueCode = userIDField.getText(); // Assuming user enters UniqueCode in the userIDField
        String officerName = officerNameField.getText();
        String officerNotes = officerNotesArea.getText();
        String offenceDate = offenceDatePicker.getValue().toString();
        String offenceNumber = offenceNumberField.getText();
        String pastOffences = pastOffencesField.getText();

        // Retrieve UserID from UserDetails table based on UniqueCode
        int userID = getUserIDFromUserDetails(uniqueCode);

        if (userID != -1) {
            // Insert the values into the Cases table
            Utils utils = new Utils();
            String values = "'" + caseID + "', '" + userID + "', '" + officerName + "', '" + officerNotes + "', '" +
                    offenceDate + "', '" + offenceNumber + "', '" + pastOffences + "'";
            int rowsInserted = utils.insert("Cases", values);

            if (rowsInserted > 0) {
                System.out.println("User details inserted successfully!");
            } else {
                System.out.println("Failed to insert user details!");
            }
        } else {
            System.out.println("User not found in UserDetails table.");
        }
    }

    // Method to retrieve UserID from UserDetails table based on UniqueCode
    private int getUserIDFromUserDetails(String uniqueCode) {
        try {
            Utils utils = new Utils();
            ResultSet resultSet = utils.getData("UserDetails");

            while (resultSet.next()) {
                if (resultSet.getString("UniqueCode").equals(uniqueCode)) {
                    return resultSet.getInt("UserId");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Return -1 if UserID is not found
    }

    public static void main(String[] args) {
        launch(args);
    }
}
