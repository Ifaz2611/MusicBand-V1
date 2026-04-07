package com.example.music_band_oop.Controller.FXMLControllerForUser2;

import com.example.music_band_oop.Controller.mainuser.NewEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class EC_Goal1_ViewController implements Initializable {

    @FXML private DatePicker DateDtatePicker;
    @FXML private TableView<NewEvent> EventTableView;
    @FXML private TextField EventNameTextField, BandNameTextField, AudienceSizeTextField, VenueFieldTextField;
    @FXML private TableColumn<NewEvent, String> EventNameCol, BandCol, VenueCol;
    @FXML private TableColumn<NewEvent, Integer> AudienceCol;
    @FXML private TableColumn<NewEvent, LocalDate> DateCol;
    @FXML private Label messageLabel;

    private final ObservableList<NewEvent> eventList = FXCollections.observableArrayList();
    private boolean availabilityVerified = false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        EventNameCol.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        DateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        VenueCol.setCellValueFactory(new PropertyValueFactory<>("venue"));
        BandCol.setCellValueFactory(new PropertyValueFactory<>("bandName"));
        AudienceCol.setCellValueFactory(new PropertyValueFactory<>("audienceSize"));
    }

    @FXML
    public void VerifyButtonOnAction(ActionEvent actionEvent) {
        if (!areFieldsFilled()) {
            setMessage("Please fill all fields.", "red");
            availabilityVerified = false;
            return;
        }

        String venue = VenueFieldTextField.getText().trim();
        String band = BandNameTextField.getText().trim();
        LocalDate date = DateDtatePicker.getValue();

        boolean allAvailable = checkVenueAvailability(venue, date)
                && checkBandAvailability(band, date)
                && checkEquipmentAvailability(venue, date);

        if (allAvailable) {
            setMessage("All available! You can now save.", "green");
            availabilityVerified = true;
        } else {
            setMessage("Conflict: Venue/Band/Equipment not available.", "red");
            availabilityVerified = false;
        }
    }

    @FXML
    public void HandleSaveButtonOnAction(ActionEvent actionEvent) {
        if (!availabilityVerified) {
            setMessage("Please verify availability first.", "red");
            return;
        }
        try {
            NewEvent newEvent = new NewEvent(
                    EventNameTextField.getText().trim(),
                    DateDtatePicker.getValue(),
                    VenueFieldTextField.getText().trim(),
                    BandNameTextField.getText().trim(),
                    Integer.parseInt(AudienceSizeTextField.getText().trim())
            );
            EventTableView.getItems().add(newEvent);
            clearForm();
            setMessage("Event saved successfully!", "green");
            assignRolesAndNotify(newEvent);
            EventTableView.refresh();

        } catch (NumberFormatException e) {
            setMessage("Audience size must be a number.", "red");
        }
    }

    @FXML
    public void DashboardButtonOnAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/music_band_oop/DashboardOfUsers/EventCoordinatorDashbroad.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Event Coordinator Dashboard");
        } catch (Exception e) {
            showAlert("Error", "Failed to load dashboard.");
        }
    }

    private boolean areFieldsFilled() {
        return !EventNameTextField.getText().trim().isEmpty()
                && !VenueFieldTextField.getText().trim().isEmpty()
                && !BandNameTextField.getText().trim().isEmpty()
                && !AudienceSizeTextField.getText().trim().isEmpty()
                && DateDtatePicker.getValue() != null;
    }

    private void clearForm() {
        EventNameTextField.clear();
        VenueFieldTextField.clear();
        BandNameTextField.clear();
        AudienceSizeTextField.clear();
        DateDtatePicker.setValue(null);
        availabilityVerified = false;
    }

    private void setMessage(String msg, String color) {
        messageLabel.setText(msg);
        messageLabel.setStyle("-fx-text-fill: " + color + ";");
    }

    private boolean checkVenueAvailability(String venue, LocalDate date)     { return true; }
    private boolean checkBandAvailability(String band, LocalDate date)       { return true; }
    private boolean checkEquipmentAvailability(String venue, LocalDate date) { return true; }

    private void assignRolesAndNotify(NewEvent event) {
        System.out.println("Roles assigned and notification sent for: " + event.getEventName());
    }

    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(msg);
        alert.showAndWait();
    }


    //---------------------------ReUseable Code -----------------------------
//    @FXML
//    public void DashboardButtonOnAction(ActionEvent event) {
//        try {
//            Parent root = FXMLLoader.load(getClass().getResource("/com/example/music_band_oop/DashboardOfUsers/EventCoordinatorDashbroad.fxml"));
//            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            stage.setScene(new Scene(root));
//            stage.setTitle("Event Coordinator Dashboard");
//        } catch (Exception e) {
//            showAlert("Error", "Failed to load dashboard.");
//        }
//    }
//    private void showAlert(String title, String msg) {
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle(title);
//        alert.setContentText(msg);
//        alert.showAndWait();
//    }

}