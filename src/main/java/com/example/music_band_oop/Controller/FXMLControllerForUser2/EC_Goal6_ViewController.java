package com.example.music_band_oop.Controller.FXMLControllerForUser2;

import com.example.music_band_oop.Controller.mainuser.AudienceEntry;
import com.example.music_band_oop.Controller.mainuser.Feedback;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.*;

public class EC_Goal6_ViewController implements Initializable {

    @FXML private ComboBox<String> EventComboBox;
    @FXML private TableView<AudienceEntry> AudienceTableView;
    @FXML private TableColumn<AudienceEntry, String> NameCol, TicketIdCol, StatusCol, ActionCol;
    @FXML private TextField ticketIdField;
    @FXML private TableView<Feedback> FeedbackTableView;
    @FXML private TableColumn<Feedback, String> FeedbackNameCol, ReviewCol;
    @FXML private TableColumn<Feedback, Integer> RatingCol;
    @FXML private TextField FeedbackNameField, CommentTextField;
    @FXML private ComboBox<Integer> RatingComboBox;
    @FXML private Label statusLabel;
    private final Map<String, ObservableList<AudienceEntry>> eventAudienceMap = new HashMap<>();
    private final Map<String, ObservableList<Feedback>> eventFeedbackMap = new HashMap<>();
    private String currentEvent;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        NameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        TicketIdCol.setCellValueFactory(new PropertyValueFactory<>("ticketId"));
        StatusCol.setCellValueFactory(c -> {
            AudienceEntry a = c.getValue();
            return new SimpleStringProperty(
                    (a.isVerified() ? "Verified" : "Unverified") + " | " +
                            (a.isAllowedEntry() ? "Entered" : "Waiting")
            );
        });
        ActionCol.setCellValueFactory(c -> new SimpleStringProperty(""));
        FeedbackNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        RatingCol.setCellValueFactory(new PropertyValueFactory<>("rating"));
        ReviewCol.setCellValueFactory(new PropertyValueFactory<>("comment"));
        RatingComboBox.setItems(FXCollections.observableArrayList(1, 2, 3, 4, 5));
        loadDummyData();
        EventComboBox.setItems(FXCollections.observableArrayList(eventAudienceMap.keySet()));
        EventComboBox.setOnAction(e -> onEventSelected());
    }

    private void loadDummyData() {

        eventAudienceMap.put("Summer Fest", FXCollections.observableArrayList(
                new AudienceEntry("Ifaz Md Zahin", "T123", false, false),
                new AudienceEntry("Rashad Hossen", "T124", false, false)
        ));

        eventFeedbackMap.put("Summer Fest", FXCollections.observableArrayList(
                new Feedback("Ifaz Md Zahin", 5, "Amazing concert!")
        ));

        eventAudienceMap.put("Jazz Night", FXCollections.observableArrayList(
                new AudienceEntry("Alice Brown", "T567", false, false)
        ));

        eventFeedbackMap.put("Jazz Night", FXCollections.observableArrayList());
    }

    private void onEventSelected() {
        currentEvent = EventComboBox.getValue();

        if (currentEvent != null) {
            AudienceTableView.setItems(eventAudienceMap.get(currentEvent));
            FeedbackTableView.setItems(eventFeedbackMap.get(currentEvent));
            statusLabel.setText("Loaded: " + currentEvent);
        }
    }

    private boolean validateEvent() {
        if (currentEvent == null) {
            showAlert("No Event", "Please select an event first.");
            return false;
        }
        return true;
    }

    @FXML
    private void VerifyTicketButtonOnAction(ActionEvent event) {
        if (!validateEvent()) return;

        String ticketId = ticketIdField.getText().trim();

        if (ticketId.isEmpty()) {
            showAlert("Input Required", "Enter a Ticket ID.");
            return;
        }

        AudienceEntry found = AudienceTableView.getItems().stream()
                .filter(a -> a.getTicketId().equals(ticketId))
                .findFirst()
                .orElse(null);

        if (found == null) {
            showAlert("Invalid Ticket", "Ticket ID not found.");
        } else {
            found.setVerified(true);
            AudienceTableView.refresh();
            statusLabel.setText("Ticket " + ticketId + " verified.");
        }

        ticketIdField.clear();
    }

    @FXML
    private void AllowEntryButtonOnAction(ActionEvent event) {
        if (!validateEvent()) return;

        AudienceEntry selected = AudienceTableView.getSelectionModel().getSelectedItem();

        if (selected == null) {
            showAlert("No Selection", "Select a person first.");
            return;
        }

        if (!selected.isVerified()) {
            showAlert("Not Verified", "Verify ticket first.");
            return;
        }

        selected.setAllowedEntry(true);
        AudienceTableView.refresh();
        statusLabel.setText("Entry allowed for " + selected.getName());
    }

    @FXML
    private void CollectFeedbackButtonOnAction(ActionEvent event) {
        if (!validateEvent()) return;

        String name = FeedbackNameField.getText().trim();
        Integer rating = RatingComboBox.getValue();

        if (name.isEmpty() || rating == null) {
            showAlert("Missing Info", "Name & rating required.");
            return;
        }

        eventFeedbackMap.get(currentEvent)
                .add(new Feedback(name, rating, CommentTextField.getText().trim()));

        FeedbackTableView.refresh();

        statusLabel.setText("Feedback added.");

        FeedbackNameField.clear();
        RatingComboBox.setValue(null);
        CommentTextField.clear();
    }

    @FXML
    private void SaveFeedbackButtonOnAction(ActionEvent event) {
        if (!validateEvent()) return;

        showAlert("Saved", "Feedback saved for " + currentEvent);
        statusLabel.setText("Saved successfully.");
    }

    @FXML
    private void GenerateReportButtonOnAction(ActionEvent event) {
//        if (!validateEvent()) return;
//
//        ObservableList<Feedback> feedbacks = eventFeedbackMap.get(currentEvent);
//
//        if (feedbacks.isEmpty()) {
//            showAlert("No Data", "No feedback yet.");
//            return;
//        }
//
//        double avg = feedbacks.stream()
//                .mapToInt(Feedback::getRating)
//                .average()
//                .orElse(0);
//
//        StringBuilder report = new StringBuilder();
//        report.append("Event: ").append(currentEvent).append("\n")
//                .append("Total: ").append(feedbacks.size()).append("\n")
//                .append("Average: ").append(String.format("%.2f", avg)).append("\n\n");
//
//        feedbacks.forEach(f ->
//                report.append(f.getName())
//                        .append(" (").append(f.getRating()).append("): ")
//                        .append(f.getComment()).append("\n")
//        );
//
//        showAlert("Report", report.toString());
    }

    @FXML
    public void DashboardButtonOnAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/music_band_oop/DashboardOfUsers/EventCoordinatorDashbroad.fxml"));

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));

        } catch (Exception e) {
            showAlert("Error", "Failed to load dashboard.");
        }
    }

    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}