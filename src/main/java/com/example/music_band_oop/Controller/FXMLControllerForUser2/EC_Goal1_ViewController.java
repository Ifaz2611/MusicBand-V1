package com.example.music_band_oop.Controller.FXMLControllerForUser2;

import com.example.music_band_oop.Controller.mainuser.NewEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EC_Goal1_ViewController {

    @FXML private DatePicker DateDtatePicker;
    @FXML private TableView<NewEvent> EventTableView;
    @FXML private TextField EventNameTextField, BandNameTextField, AudienceSizeTextField, VenueFieldTextField;
    @FXML private TableColumn<NewEvent, String> EventNameCol, BandCol, VenueCol;
    @FXML private TableColumn<NewEvent, Integer> AudienceCol;
    @FXML private TableColumn<NewEvent, LocalDate> DateCol;
    @FXML private Label messageLabel;

    private final List<NewEvent> eventList = new ArrayList<>();
    private boolean availabilityVerified = false;

    @FXML
    public void initialize() {
        EventNameCol.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        DateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        VenueCol.setCellValueFactory(new PropertyValueFactory<>("venue"));
        BandCol.setCellValueFactory(new PropertyValueFactory<>("bandName"));
        AudienceCol.setCellValueFactory(new PropertyValueFactory<>("audienceSize"));
        refreshTable();
    }

    private void refreshTable() {
        EventTableView.getItems().setAll(eventList);
    }

    @FXML
    public void VerifyButtonOnAction(ActionEvent actionEvent) {
        availabilityVerified = true;
        setMessage("All available! You can now save.");
    }
    @FXML
    public void HandleSaveButtonOnAction(ActionEvent actionEvent) {
        if (!availabilityVerified) {
            setMessage("Please verify availability first.");
            return;
        }
        try {
            int audienceSize = 0;
            if (!AudienceSizeTextField.getText().isEmpty()) {
                audienceSize = Integer.parseInt(AudienceSizeTextField.getText());
            }
            NewEvent newEvent = new NewEvent(
                    EventNameTextField.getText(),
                    DateDtatePicker.getValue(),
                    VenueFieldTextField.getText(),
                    BandNameTextField.getText(),
                    audienceSize
            );
            eventList.add(newEvent);
            refreshTable();
            clearForm();
            setMessage("Event saved successfully!");
        } catch (NumberFormatException e) {
            setMessage("Audience size must be a number.");
        }
    }

    private void clearForm() {
        EventNameTextField.clear();
        VenueFieldTextField.clear();
        BandNameTextField.clear();
        AudienceSizeTextField.clear();
        DateDtatePicker.setValue(null);
        availabilityVerified = false;
    }

    private void setMessage(String msg) {
        messageLabel.setText(msg);
    }

    @FXML
    public void DashboardButtonOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/music_band_oop/DashboardOfUsers/EventCoordinatorDashbroad.fxml"));
            Scene dashboardScene = new Scene(fxmlLoader.load());
            Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            currentStage.setScene(dashboardScene);
            currentStage.setTitle("Event Coordinator Dashboard");
            currentStage.show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}