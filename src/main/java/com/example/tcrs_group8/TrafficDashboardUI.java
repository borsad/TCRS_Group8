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
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TrafficDashboardUI extends Application {

    @Override
    public void start(Stage primaryStage) {
        // top menu layout
        HBox topMenu = new HBox();
        topMenu.setAlignment(Pos.TOP_RIGHT);
        topMenu.setPadding(new Insets(10));
        topMenu.setSpacing(10);

        // menu buttons help and logout
        Button helpButton = new Button("Help");
        Button logoutButton = new Button("Logout");

        topMenu.getChildren().addAll(helpButton, logoutButton);

        // left side search and action buttons
        VBox leftSide = new VBox(10);
        leftSide.setPadding(new Insets(15));
        leftSide.setStyle("-fx-background-color: #EFEFEF;");

        TextField searchField = new TextField();
        searchField.setPromptText("Search by License #");

        Button enrollButton = new Button("Enroll Driver");
        enrollButton.setStyle("-fx-background-color: #0000FF; -fx-text-fill: white;");


        leftSide.getChildren().addAll(searchField, enrollButton);

        // righ side case details
        VBox rightSide = new VBox(10);
        rightSide.setAlignment(Pos.TOP_CENTER);
        rightSide.setPadding(new Insets(15));
        rightSide.setStyle("-fx-background-color: #b0b0b0;");

        Label driverDetailsTitle = new Label("Driver Details");
        driverDetailsTitle.setFont(new Font("Arial", 20)); // Set the title font size
        driverDetailsTitle.setMaxWidth(Double.MAX_VALUE);
        driverDetailsTitle.setAlignment(Pos.CENTER); // Center the title

        // form to display case details
        GridPane caseForm = new GridPane();
        caseForm.setVgap(5);
        caseForm.setHgap(10);
        caseForm.setAlignment(Pos.CENTER);

        // case details section
        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField("ABC"); // placeholder for actual data

        Label licenseLabel = new Label("License Number:");
        TextField licenseField = new TextField("4214fs6353317676137"); // placeholder

        Label addressLabel = new Label("Address:");
        TextField offensesField = new TextField("430, 421"); // placeholder

        // add case details to case form
        caseForm.addRow(0, nameLabel, nameField);
        caseForm.addRow(1, licenseLabel, licenseField);
        caseForm.addRow(2, addressLabel, offensesField);
        caseForm.setAlignment(Pos.CENTER);

        // separator
        Separator separator = new Separator();
        separator.setMaxWidth(Double.MAX_VALUE);

        // form for displaying driver details
        GridPane driverForm = new GridPane();
        driverForm.setAlignment(Pos.CENTER); // Center the GridPane contents
        driverForm.setVgap(5);
        driverForm.setHgap(10);
        driverForm.setMaxWidth(Double.MAX_VALUE);

        // driver details section
        Label trainingTitle = new Label("Assigned Trainings");
        trainingTitle.setFont(new Font("Arial", 20)); // Set the title font size
        trainingTitle.setMaxWidth(Double.MAX_VALUE);
        trainingTitle.setAlignment(Pos.CENTER); // Center the title


        Label trainingLabel = new Label("Training Assigned:");
        TextField trainingField = new TextField("421"); // Placeholder for actual data

        Label dateToCompleteLabel = new Label("Deadline To Complete:");
        TextField dateToCompleteField = new TextField("Today's date"); // placeholder for actual data


        // add driver details to driver form
        driverForm.addRow(0, driverDetailsTitle);
        driverForm.addRow(1, trainingLabel, trainingField);
        driverForm.addRow(2, dateToCompleteLabel, dateToCompleteField);


        // add title, case form, separator, and driver form to right side layout
        rightSide.getChildren().addAll(driverDetailsTitle, caseForm, separator, trainingTitle, driverForm);

        // main layout
        BorderPane mainLayout = new BorderPane();
        mainLayout.setTop(topMenu);
        mainLayout.setLeft(leftSide);
        mainLayout.setCenter(rightSide);

        // event handlers for the buttons
        //TODO:: ADD functions here
        enrollButton.setOnAction(event -> System.out.println("Enroll button clicked"));
        helpButton.setOnAction(event -> System.out.println("Help button clicked"));
        logoutButton.setOnAction(event -> System.out.println("logout button clicked"));

        // create scene and set the stage
        Scene scene = new Scene(mainLayout, 800, 600); // Window size can be adjusted
        primaryStage.setTitle("Traffic School Dashboard");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
