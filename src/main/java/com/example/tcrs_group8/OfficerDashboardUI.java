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
import com.example.tcrs_group8.Services.Utils;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class OfficerDashboardUI extends Application {
    @Override
    public void start(Stage primaryStage) {
        // top menu layout
        HBox topMenu = new HBox();
        topMenu.setAlignment(Pos.TOP_RIGHT);
        topMenu.setPadding(new Insets(10));
        topMenu.setSpacing(5);

        // menu buttons
        Button helpButton = new Button("Help");
        Hyperlink logoutLink = new Hyperlink("Logout");

        topMenu.getChildren().addAll(helpButton, logoutLink);

        // Create a container for the top part of the layout
        BorderPane topLayout = new BorderPane();

        // search bar on top of the page
        TextField searchField = new TextField();
        searchField.setPromptText("Search");
        HBox searchBox = new HBox(searchField);
        searchBox.setAlignment(Pos.TOP_RIGHT);
        searchBox.setPadding(new Insets(10, 10, 10, 0)); // Adjust padding as needed

        // add  help and logout buttons to  left side of toplayout
        topLayout.setLeft(topMenu);
        topLayout.setRight(searchBox);


        // table for data display
        TableView<ObservableList<String>> tableView = new TableView<>();
        String[] columns = {"Report ID", "Violations", "Driving License Number", "Officer ID"};
        double[] columnWidths = {100, 100, 100, 100};
        for (int i = 0; i < columns.length; i++) {
            TableColumn<ObservableList<String>, String> column = new TableColumn<>(columns[i]);
            column.setPrefWidth(columnWidths[i]);
            tableView.getColumns().add(column);
        }
        //  flexible resizing of columns
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.setEditable(true);

        // input fields on left side for data
        VBox inputFields = new VBox(10);
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
        inputFields.getChildren().addAll(caseIDField, userIDField, officerNameField, officerNotesArea, offenceDatePicker, offenceNumberField, pastOffencesField);

        // gridpane for button layout
        GridPane buttonsGrid = new GridPane();
        buttonsGrid.setVgap(10); // Vertical spacing between rows
        buttonsGrid.setHgap(10); // Horizontal spacing between columns
        buttonsGrid.setAlignment(Pos.CENTER_LEFT);

        // buttons below the input fields
        Button retrieveButton = new Button("Retrieve Data");
        Button updateButton = new Button("Update");
        Button deleteButton = new Button("Delete");
        Button reportButton = new Button("Generate Report");
        retrieveButton.setStyle("-fx-background-color: #0000FF; -fx-text-fill: white;");
        updateButton.setStyle("-fx-background-color: #0000FF; -fx-text-fill: white;");
        deleteButton.setStyle("-fx-background-color: #0000FF; -fx-text-fill: white;");
        reportButton.setStyle("-fx-background-color: #0000FF; -fx-text-fill: white;");

        // add buttons to the gridpane
        buttonsGrid.add(retrieveButton, 0, 0); // Column 0, Row 0
        buttonsGrid.add(updateButton, 1, 0); // Column 1, Row 0
        buttonsGrid.add(deleteButton, 2, 0); // Column 2, Row 0
        buttonsGrid.add(reportButton, 3, 0); // Column 3, Row 0
        buttonsGrid.setAlignment(Pos.CENTER_LEFT);

        // layout for  side panel containing input fields and buttons
        VBox sidePanel = new VBox(10, inputFields, buttonsGrid);
        sidePanel.setPadding(new Insets(10));
        sidePanel.setStyle("-fx-background-color: #EFEFEF;"); // Styling for the side panel

        // main layout
        BorderPane mainLayout = new BorderPane();
        mainLayout.setTop(topLayout); // Use the new topLayout as the top component
        mainLayout.setLeft(sidePanel);
        mainLayout.setCenter(tableView);

        // TODO: add functions here

        // Retrieve button
        retrieveButton.setOnAction(e -> {
            String caseID = caseIDField.getText();
            if (!caseID.isEmpty()) {
                // Call method to retrieve data from database for all cases
                Utils utils = new Utils();
                ResultSet resultSet = utils.getData("Cases");
                try {
                    boolean dataFound = false;
                    while (resultSet.next()) {
                        if (resultSet.getString("CaseID").equals(caseID)) {
                            // If Case ID matches, set values in respective fields
                            userIDField.setText(resultSet.getString("UserID"));
                            officerNameField.setText(resultSet.getString("OfficerName"));
                            officerNotesArea.setText(resultSet.getString("OfficerNotes"));
                            String offenceDate = resultSet.getString("OffenceDate");
                            if (offenceDate != null) {
                                LocalDate date = LocalDate.parse(offenceDate);
                                offenceDatePicker.setValue(date);
                            } else {
                                offenceDatePicker.setValue(null); // Set DatePicker value to null if date is null
                            }
                            offenceNumberField.setText(resultSet.getString("OfficerNotes"));
                            pastOffencesField.setText(resultSet.getString("OfficerNotes"));
                            // Similarly set values for other fields
                            dataFound = true;
                            break; // Exit loop once data is found
                        }
                    }
                    if (!dataFound) {
                        // Handle case when no data is found for the given Case ID
                        System.out.println("No data found for Case ID: " + caseID);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            } else {
                // Handle case when Case ID field is empty
                System.out.println("Please enter Case ID for retrieval.");
            }
        });

        // Update button action
        updateButton.setOnAction(e -> {
            String caseID = caseIDField.getText();
            String userID = userIDField.getText();
            String officerName = officerNameField.getText();
            String officerNotes = officerNotesArea.getText();
            String offenceDate = offenceDatePicker.getValue().toString(); // Assuming the date format is yyyy-MM-dd
            String offenceNumber = offenceNumberField.getText();
            String pastOffences = pastOffencesField.getText();

            // Call the update method from Utils class to update the data
            Utils utils = new Utils();
            String values = "'" + caseID + "', '" + userID + "', '" + officerName + "', '" + officerNotes + "', '" +
                    offenceDate + "', '" + offenceNumber + "', '" + pastOffences + "'";
            int result = utils.update("Cases", "UserID='" + userID + "',OfficerName='" + officerName + "',OfficerNotes='" + officerNotes + "',OffenceDate='" + offenceDate + "',OffenceNumber='" + offenceNumber + "',PastOffences='" + pastOffences + "'", "CaseID='" + caseID + "'");
            if (result > 0) {
                System.out.println("Data updated successfully.");
                // Refresh the table view after updating data
                tableView.getItems().clear();
            } else {
                System.out.println("Error updating data.");
            }
        });

        // Delete button action
        deleteButton.setOnAction(e -> {
            String caseID = caseIDField.getText();

            // Call the delete method from Utils class to delete the data
            Utils utils = new Utils();
            int result = utils.delete("Cases", "CaseID='" + caseID + "'");
            if (result > 0) {
                System.out.println("Data deleted successfully.");
                // Refresh the table view after deleting data
                tableView.getItems().clear();
            } else {
                System.out.println("Error deleting data.");
            }
        });


        reportButton.setOnAction(e -> {
            String caseID = caseIDField.getText();
            if (!caseID.isEmpty()) {
                // Call method to retrieve data from database for the specified case
                Utils utils = new Utils();
                ResultSet resultSet = utils.getData("Cases");
                try {
                    boolean dataFound = false;
                    while (resultSet.next()) {
                        if (resultSet.getString("CaseID").equals(caseID)) {
                            // If Case ID matches, generate the report
                            System.out.println("Generating report for Case ID: " + caseID);
                            System.out.println("User ID: " + resultSet.getString("UserID"));
                            System.out.println("Officer Name: " + resultSet.getString("OfficerName"));
                            System.out.println("Officer Notes: " + resultSet.getString("OfficerNotes"));
                            System.out.println("Offence Date: " + resultSet.getString("OffenceDate"));
                            System.out.println("Offence Number: " + resultSet.getString("OffenceNumber"));
                            System.out.println("Past Offences: " + resultSet.getString("PastOffences"));
                            // You can customize the report generation logic based on your requirements
                            dataFound = true;
                            break; // Exit loop once data is found
                        }
                    }
                    if (!dataFound) {
                        // Handle case when no data is found for the given Case ID
                        System.out.println("No data found for Case ID: " + caseID);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            } else {
                // Handle case when Case ID field is empty
                System.out.println("Please enter Case ID to generate the report.");
            }
        });

        helpButton.setOnAction(e -> System.out.println("Help clicked"));
        logoutLink.setOnAction(e -> System.out.println("Logout clicked"));

        // set scene and stage
        Scene scene = new Scene(mainLayout, 1000, 600); // Adjusted size for a dashboard view
        primaryStage.setTitle("Officer Dashboard");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}