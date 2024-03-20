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
import javafx.stage.Stage;
import javafx.collections.ObservableList;

public class UserDashboardUI extends Application {
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
        String[] columns = {"Driver Name", "Vehicle Number", "Driving License Number", "Fine Amount"};
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
        inputFields.setPadding(new Insets(10));
        TextField driverNameInput = new TextField();
        driverNameInput.setPromptText("Driver Name");
        TextField vehicleNumberInput = new TextField();
        vehicleNumberInput.setPromptText("Vehicle Number");
        TextField licenseNumberInput = new TextField();
        licenseNumberInput.setPromptText("Driving License Number");
        TextField fineAmountInput = new TextField();
        fineAmountInput.setPromptText("Fine Amount");
        inputFields.getChildren().addAll(driverNameInput, vehicleNumberInput, licenseNumberInput, fineAmountInput);

        // gridpane for button layout
        GridPane buttonsGrid = new GridPane();
        buttonsGrid.setVgap(10); // Vertical spacing between rows
        buttonsGrid.setHgap(10); // Horizontal spacing between columns
        buttonsGrid.setAlignment(Pos.CENTER_LEFT);

        // buttons below the input fields
        Button addButton = new Button("Add");
        Button updateButton = new Button("Update");
        Button deleteButton = new Button("Delete");
        Button reportButton = new Button("Generate Report");
        addButton.setStyle("-fx-background-color: #0000FF; -fx-text-fill: white;");
        updateButton.setStyle("-fx-background-color: #0000FF; -fx-text-fill: white;");
        deleteButton.setStyle("-fx-background-color: #0000FF; -fx-text-fill: white;");
        reportButton.setStyle("-fx-background-color: #0000FF; -fx-text-fill: white;");

        // add buttons to the gridpane
        buttonsGrid.add(addButton, 0, 0); // Column 0, Row 0
        buttonsGrid.add(updateButton, 1, 0); // Column 1, Row 0
        buttonsGrid.add(deleteButton, 0, 1); // Column 0, Row 1
        buttonsGrid.add(reportButton, 1, 1); // Column 1, Row 1
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
        addButton.setOnAction(e -> System.out.println("Add clicked"));
        updateButton.setOnAction(e -> System.out.println("Update clicked"));
        deleteButton.setOnAction(e -> System.out.println("Delete clicked"));
        reportButton.setOnAction(e -> System.out.println("Generate Report clicked"));
        helpButton.setOnAction(e -> System.out.println("Help clicked"));
        logoutLink.setOnAction(e -> System.out.println("Logout clicked"));

        // set scene and stage
        Scene scene = new Scene(mainLayout, 1000, 600); // Adjusted size for a dashboard view
        primaryStage.setTitle("User Dashboard");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}