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

import com.example.tcrs_group8.Services.DBConnector;
import com.example.tcrs_group8.Services.Utils;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SignInUI extends Application {

    @Override
    public void start(Stage primaryStage) {
        //create new pane on left side same as sign up page
        VBox bluePanel = new VBox();
        bluePanel.setPrefWidth(200);
        bluePanel.setPrefHeight(600);
        bluePanel.setStyle("-fx-background-color: #0000FF;");

        // create the sign in form
        GridPane form = new GridPane();
        form.setAlignment(Pos.CENTER);
        form.setVgap(10);
        form.setHgap(10);
        form.setPadding(new Insets(25, 25, 25, 25)); // Padding to match the image

        // sign in page title
        Label signInTitle = new Label("Sign In");
        signInTitle.setStyle("-fx-font-size: 20px;"); // Font size similar to the image

        // add components to the form
        //email
        Label emailLabel = new Label("Email");
        TextField emailTextField = new TextField();
        emailTextField.setPromptText("Enter your email");

        // password field
        Label passwordLabel = new Label("Password");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter your password");

        // sign in button
        Button signInButton = new Button("Sign In");
        signInButton.setStyle("-fx-background-color: #0000FF; -fx-text-fill: white;"); // Styling to match the image
        signInButton.setOnAction(actionEvent -> {
            try {
                Utils res = new Utils();
                ResultSet rs=res.getData("LoginCredentials");
                while (rs.next()) {
                    if(emailTextField.getText().equals(rs.getString(2))&&(passwordField.getText().equals(rs.getString(3)))){
                        System.out.println("Login Success");
                        break;
                    }else{
                        System.out.println("Failed");
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        });

        // forgot password link
        Hyperlink forgotPasswordLink = new Hyperlink("Forgot Password?");
        forgotPasswordLink.setTextFill(Color.BLUE); // Blue text for the hyperlink

        // sign up link if someone does not have account
        Hyperlink signUpLink = new Hyperlink("Don't have an account? Sign Up");
        signUpLink.setTextFill(Color.BLUE); // Blue text for the hyperlink

        // adding components to the form
        form.add(signInTitle, 0, 0, 2, 1);
        form.add(emailLabel, 0, 1);
        form.add(emailTextField, 1, 1);
        form.add(passwordLabel, 0, 2);
        form.add(passwordField, 1, 2);
        form.add(forgotPasswordLink, 1, 3);
        form.add(signInButton, 1, 4);

        // aligning the sign in  button to the right
        GridPane.setHalignment(signInButton, HPos.RIGHT);

        // layout for the sign up link
        HBox signUpBox = new HBox(signUpLink);
        signUpBox.setAlignment(Pos.CENTER); // Center alignment for the sign-up hyperlink
        form.add(signUpBox, 0, 5, 2, 1); // Spanning across two columns

        // final layout composition
        HBox content = new HBox(bluePanel, form);
        VBox mainLayout = new VBox(content);
        mainLayout.setAlignment(Pos.CENTER); // Center the content within the main layout

        // create scene and set the stage
        Scene scene = new Scene(mainLayout, 800, 600); // Set the window size to match the image
        primaryStage.setTitle("Sign In");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
