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
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class JudicialDashboardUI extends Application {

    @Override
    public void start(Stage stage) throws IOException {
//        // top menu layout
//        HBox topMenu = new HBox();
//        topMenu.setAlignment(Pos.TOP_RIGHT);
//        topMenu.setPadding(new Insets(10));
//        topMenu.setSpacing(10);
//
//        // menu buttons help and logout
//        Button helpButton = new Button("Help");
//        Button logoutButton = new Button("Logout");
//
//        topMenu.getChildren().addAll(helpButton, logoutButton);
//
//        // left side search and action buttons
//        VBox leftSide = new VBox(10);
//        leftSide.setPadding(new Insets(15));
//        leftSide.setStyle("-fx-background-color: #EFEFEF;");
//
//        TextField searchField = new TextField();
//        searchField.setPromptText("Search by Case #");
//
//        Button fineButton = new Button("Fine the Driver");
//        Button assignButton = new Button("Assign Training");
//        Button dismissButton = new Button("Dismiss Charge");
//        fineButton.setStyle("-fx-background-color: #0000FF; -fx-text-fill: white;");
//        assignButton.setStyle("-fx-background-color: #0000FF; -fx-text-fill: white;");
//        dismissButton.setStyle("-fx-background-color: #0000FF; -fx-text-fill: white;");
//
//
//        leftSide.getChildren().addAll(searchField, fineButton, assignButton, dismissButton);
//
//        // righ side case details
//        VBox rightSide = new VBox(10);
//        rightSide.setAlignment(Pos.TOP_CENTER);
//        rightSide.setPadding(new Insets(15));
//        rightSide.setStyle("-fx-background-color: #b0b0b0;");
//
//        Label caseDetailsTitle = new Label("Case Details");
//        caseDetailsTitle.setFont(new Font("Arial", 20));
//        caseDetailsTitle.setMaxWidth(Double.MAX_VALUE);
//        caseDetailsTitle.setAlignment(Pos.CENTER);
//
//        // form to display case details
//        GridPane caseForm = new GridPane();
//        caseForm.setVgap(5);
//        caseForm.setHgap(10);
//        caseForm.setAlignment(Pos.CENTER);
//
//        // case details section
//        Label nameLabel = new Label("Name:");
//        TextField nameField = new TextField("ABC"); // placeholder for actual data
//
//        Label licenseLabel = new Label("License Number:");
//        TextField licenseField = new TextField("4214fs6353317676137"); // placeholder
//
//        Label offensesLabel = new Label("Past Offenses:");
//        TextField offensesField = new TextField("430, 421"); // placeholder
//
//        // add case details to case form
//        caseForm.addRow(0, nameLabel, nameField);
//        caseForm.addRow(1, licenseLabel, licenseField);
//        caseForm.addRow(2, offensesLabel, offensesField);
//        caseForm.setAlignment(Pos.CENTER);
//
//        // separator
//        Separator separator = new Separator();
//        separator.setMaxWidth(Double.MAX_VALUE);
//
//        // form for displaying driver details
//        GridPane driverForm = new GridPane();
//        driverForm.setAlignment(Pos.CENTER);
//        driverForm.setVgap(5);
//        driverForm.setHgap(10);
//        driverForm.setMaxWidth(Double.MAX_VALUE);
//
//        // driver details section
//        Label driverDetailsTitle = new Label("Driver Details");
//        driverDetailsTitle.setFont(new Font("Arial", 20));
//        driverDetailsTitle.setMaxWidth(Double.MAX_VALUE);
//        driverDetailsTitle.setAlignment(Pos.CENTER);
//
//        Label dateAndTimeLabel = new Label("Date And Time:");
//        TextField dateAndTimeField = new TextField("Today's date"); // placeholder for actual data
//
//        Label offenseNumberLabel = new Label("Offense Number:");
//        TextField offenseNumberField = new TextField("421"); // placeholder for actual data
//
//        Label officerLabel = new Label("Officer:");
//        TextField officerField = new TextField("Pappu"); // placeholder for actual data
//
//        Label officerNotesLabel = new Label("Officer Notes, Evidences:");
//        TextArea officerNotesTextArea = new TextArea("Was driving very fast and didn't stop for me"); // placeholder for actual data
//        officerNotesTextArea.setPrefHeight(50); // Set the preferred height for TextArea
//
//        // add driver details to driver form
//        driverForm.addRow(0, driverDetailsTitle);
//        driverForm.addRow(1, dateAndTimeLabel, dateAndTimeField);
//        driverForm.addRow(2, offenseNumberLabel, offenseNumberField);
//        driverForm.addRow(3, officerLabel, officerField);
//        driverForm.addRow(4, officerNotesLabel, officerNotesTextArea);
//
//        // add title, case form, separator, and driver form to right side layout
//        rightSide.getChildren().addAll(caseDetailsTitle, caseForm, separator, driverDetailsTitle, driverForm);
//
//        // main layout
//        BorderPane mainLayout = new BorderPane();
//        mainLayout.setTop(topMenu);
//        mainLayout.setLeft(leftSide);
//        mainLayout.setCenter(rightSide);
//
//        // event handlers for the buttons
//        //TODO:: ADD functions here
//        fineButton.setOnAction(event -> System.out.println("Fine the Driver button clicked"));
//        assignButton.setOnAction(event -> System.out.println("Assign Training button clicked"));
//        dismissButton.setOnAction(event -> System.out.println("Dismiss Charge button clicked"));
//        helpButton.setOnAction(event -> System.out.println("Help button clicked"));
//        logoutButton.setOnAction(event -> System.out.println("logout button clicked"));

        // create scene and set the stage
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("judicialDashboard-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Judicial Dashboard");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
