package com.example.tcrs_group8.Contollers;

import com.example.tcrs_group8.HelloApplication;
import com.example.tcrs_group8.Services.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SignUpController {
    public Stage stage;
    public Scene scene;
    private Parent root;
    SceneController sceneController = new SceneController();
    @FXML
    public void switchToSignIn(ActionEvent event) throws IOException {
    event.consume();
    sceneController.switchToSignInPage(event);
    }
}
