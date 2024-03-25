package com.example.tcrs_group8;

import com.example.tcrs_group8.Contollers.SignInController;
import com.example.tcrs_group8.Models.User;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class HelloController {
    @FXML
    private Label welcomeText;
    SignInController userDashboardUI=new SignInController();

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}