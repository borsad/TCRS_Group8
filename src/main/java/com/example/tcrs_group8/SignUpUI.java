/**
 * COMMENT !
 * Author: Group 8
 * Name: Asem AL Dwaikat ID: 239386640
 * Name:
 * Name:
 * Name:
 * COSC 3506 Section A
 * Any and all work in this file is our own.
 * !
 **/


package com.example.tcrs_group8;

import com.example.tcrs_group8.Services.DBConnector;
import com.example.tcrs_group8.Services.Utils;
import javafx.scene.control.Label;


import javafx.scene.control.PasswordField;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import javafx.stage.Stage;

import java.sql.SQLException;
import java.sql.Statement;


public class SignUpUI extends Application {

    @Override
    public void start(Stage primStage) {
        //set up main layout
        VBox mainLayout = new VBox(10);
        mainLayout.setAlignment(Pos.CENTER);


        //create new blue panel on left side
        VBox bluePanel = new VBox();
        bluePanel.setPrefWidth(200);
        bluePanel.setPrefHeight(600);
        bluePanel.setStyle("-fx-background-color: #0000FF;");

        //create the sign up form
        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);
        form.setAlignment(Pos.CENTER);
        form.setPadding(new Insets(25, 25, 25, 25)); // Padding to match the image


        //add components to the form
        Label emailLabel = new Label("Email");
        TextField emailTextField = new TextField();
        emailTextField.setPromptText("Enter your Email");

        Label passwordLabel = new Label("Password");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter your Password");


        Label confirmPasswordLabel = new Label("Confirm Password");
        PasswordField confirmPasswordField = new PasswordField();
        confirmPasswordField.setPromptText("Confrim your Password");

        Label uniqeCodeLabel = new Label("Uniqe Authintication Code");
        TextField uniqeCodeField = new TextField();
        uniqeCodeField.setPromptText("Enter the Uniqe Authintication Code");

        //help button
        Button helpButton = new Button("Help");
        helpButton.setStyle("-fx-background-color: #0000FF; -fx-text-fill: white;");

        // Setting an action for the help button
        helpButton.setOnAction(e -> {
            // Show help dialog or message. This is just a placeholder for whatever help functionality you need.
            Alert helpAlert = new Alert(Alert.AlertType.INFORMATION);
            helpAlert.setHeaderText("Authentication Code Help");
            helpAlert.setContentText("This unique code is provided by your administrator.");
            helpAlert.showAndWait();
        });

        Button signUpButton = new Button("Sign Up");
//        signUpButton.setDefaultButton(true);
        signUpButton.setStyle("-fx-background-color: #0000FF; -fx-text-fill: white;");


        Button Test = new Button("Test");
        signUpButton.setStyle("-fx-background-color: #0000FF; -fx-text-fill: white;");
        Test.setOnAction(actionEvent -> {
//            try{
//                System.out.println("ss");
//                DBConnector con = new DBConnector();
//                Statement stmt = con.getConnection().createStatement();
//                String query = "insert into UserDetails values (null,'test','test',5,5)";
//                int result= stmt.executeUpdate(query);
//                if (result>0){
//                    System.out.println("success");
//                }else{
//                    System.out.println("ddd");
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
            Utils n = new Utils();
//            int result = n.insert("UserDetails", "null,'test1','test',999,5");
            int result=n.delete("UserDetails","UniqueCode='test'");
            if (result > 0) {
                System.out.println("success");
            } else {
                System.out.println("Error!");
            }

        });


        Hyperlink signInPromptButton = new Hyperlink("Already have an account? Sign In");
        signInPromptButton.setTextFill(Color.BLUE);

        // Setting an action for the sign-in button
        signInPromptButton.setOnAction(e -> {
            // Here, you can navigate to the sign-in page or show the sign-in form.
            // For demonstration, I'll just print to the console.
            System.out.println("Navigating to the sign-in page...");
            // Actual navigation or showing a sign-in form should go here.
        });


        // Add components to the form layout
        form.add(emailLabel, 0, 1);
        form.add(emailTextField, 1, 1);
        form.add(passwordLabel, 0, 2);
        form.add(passwordField, 1, 2);
        form.add(confirmPasswordLabel, 0, 3);
        form.add(confirmPasswordField, 1, 3);
        form.add(uniqeCodeLabel, 0, 4);
        form.add(uniqeCodeField, 1, 4);
        form.add(helpButton, 2, 4); // Add help button beside the unique code field at Row 4, Column 2

        form.add(signUpButton, 1, 5);
        form.add(Test, 1, 7);
        HBox signInBox = new HBox();
        signInBox.setAlignment(Pos.CENTER);
        signInBox.getChildren().add(signInPromptButton);
        form.add(signInBox, 1, 6);


        // Add the blue panel and form to the main layout
        HBox content = new HBox(10, bluePanel, form);
        mainLayout.getChildren().addAll(content);

        // Event handler for the sign-up button
        signUpButton.setOnAction(event -> {
            // TODO: Add validation logic here
            // TODO: Add database interaction logic here
        });

        // Set the scene and stage
        Scene scene = new Scene(mainLayout, 800, 600);
        primStage.setTitle("Sign Up");
        primStage.setScene(scene);
        primStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
