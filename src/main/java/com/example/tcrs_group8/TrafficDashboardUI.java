/**COMMENT !
 Author: Group 8
 Name: Asem AL Dwaikat ID: 239386640
 Name:
 Name:
 Name:
 COSC 3506 Section A
 Any and all work in this file is our own.
 !**/

package com.example.tcrs_group8;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import javax.swing.text.html.ImageView;

public class TrafficDashboardUI extends Application {

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

        // Form fields
        TextField caseIDField = new TextField();
        caseIDField.setPromptText("Case ID");

        TextField userIDField = new TextField();
        userIDField.setPromptText("User ID");

        TextField officerNameField = new TextField();
        officerNameField.setPromptText("Officer Name");

        TextArea officerNotesArea = new TextArea();
        officerNotesArea.setPromptText("Officer Notes");

        DatePicker offenceDatePicker = new DatePicker();
        offenceDatePicker.setPromptText("Offence Date");

        TextField offenceNumberField = new TextField();
        offenceNumberField.setPromptText("Offence Number");

        TextField pastOffencesField = new TextField();
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
        // Handle form submission logic here
        System.out.println("Form submitted!");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
