package com.example.tcrs_group8;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

public class SignInUI extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Blue panel on the left side
        VBox bluePanel = new VBox();
        bluePanel.setPrefWidth(200);
        bluePanel.setPrefHeight(600);
        bluePanel.setStyle("-fx-background-color: #0000FF;");

        // Form for Sign In on the right side
        GridPane form = new GridPane();
        form.setAlignment(Pos.CENTER);
        form.setVgap(10);
        form.setHgap(10);
        form.setPadding(new Insets(25, 25, 25, 25)); // Padding to match the image

        // Sign In title
        Label signInTitle = new Label("Sign In");
        signInTitle.setStyle("-fx-font-size: 20px;"); // Font size similar to the image

        // Email label and field
        Label emailLabel = new Label("Email");
        TextField emailTextField = new TextField();
        emailTextField.setPromptText("Enter your email");

        // Password label and field
        Label passwordLabel = new Label("Password");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter your password");

        // Sign In button
        Button signInButton = new Button("Sign In");
        signInButton.setStyle("-fx-background-color: #0000FF; -fx-text-fill: white;"); // Styling to match the image

        // Forgot Password link
        Hyperlink forgotPasswordLink = new Hyperlink("Forgot Password?");
        forgotPasswordLink.setTextFill(Color.BLUE); // Blue text for the hyperlink

        // Sign Up link at the bottom
        Hyperlink signUpLink = new Hyperlink("Don't have an account? Sign Up");
        signUpLink.setTextFill(Color.BLUE); // Blue text for the hyperlink

        // Adding components to the form
        form.add(signInTitle, 0, 0, 2, 1);
        form.add(emailLabel, 0, 1);
        form.add(emailTextField, 1, 1);
        form.add(passwordLabel, 0, 2);
        form.add(passwordField, 1, 2);
        form.add(forgotPasswordLink, 1, 3);
        form.add(signInButton, 1, 4);

        // Aligning the Sign In button to the right (similar to the image)
        GridPane.setHalignment(signInButton, HPos.RIGHT);

        // Layout for the Sign Up link
        HBox signUpBox = new HBox(signUpLink);
        signUpBox.setAlignment(Pos.CENTER); // Center alignment for the sign-up hyperlink
        form.add(signUpBox, 0, 5, 2, 1); // Spanning across two columns

        // Final layout composition
        HBox content = new HBox(bluePanel, form);
        VBox mainLayout = new VBox(content);
        mainLayout.setAlignment(Pos.CENTER); // Center the content within the main layout

        // Create scene and set the stage
        Scene scene = new Scene(mainLayout, 800, 600); // Set the window size to match the image
        primaryStage.setTitle("Sign In");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
