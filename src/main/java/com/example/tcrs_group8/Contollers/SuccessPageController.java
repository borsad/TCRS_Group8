package com.example.tcrs_group8.Contollers;

import com.example.tcrs_group8.Models.DataModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class SuccessPageController {
    SceneController sceneController = new SceneController();

    // ... other methods ...
    private static DataModel sharedData;
    public Label paidLabel;
    public Label payId;

    public static void setSharedData(DataModel data) {
        sharedData = data;
    }
    @FXML
    private void handleBackAction(ActionEvent actionEvent) throws IOException {
        sceneController.switchToPayFine(actionEvent);
    }
    public void initialize() {
        // Update UI with shared data
        if (sharedData != null) {
            payId.setText(sharedData.getid());
            paidLabel.setText(sharedData.getAmount());
        }
    }

}
