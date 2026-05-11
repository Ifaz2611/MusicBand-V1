package com.example.music_band_oop.Controller.FXMLControllerForUser1;

import com.example.music_band_oop.Controller.mainuser.IssueLog;
import com.example.music_band_oop.Controller.nonuser.AppendableObjectOutputStream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class SE_Goal4_ViewController {

    @FXML private TextField AffectedChannelTextField;
    @FXML private ComboBox<String> ProblemTypeComboBox;
    @FXML private ComboBox<String> SeverityComboBox;
    @FXML private ComboBox<String> StatusComboBox;
    @FXML private ComboBox<String> ActionComboBox;
    @FXML private TextArea notesTextArea;

    @FXML private TableView<IssueLog> logTableView;
    @FXML private TableColumn<IssueLog, String> timeCol;
    @FXML private TableColumn<IssueLog, String> channelCol;
    @FXML private TableColumn<IssueLog, String> problemCol;
    @FXML private TableColumn<IssueLog, String> severityCol;
    @FXML private TableColumn<IssueLog, String> actionCol;
    @FXML private TableColumn<IssueLog, String> statusCol;
    @FXML private TableColumn<IssueLog, String> notesCol;

    @FXML private TextField searchField;
    @FXML private Label statusLabel;
    @FXML private Label recordCountLabel;

    private final ObservableList<IssueLog> masterData = FXCollections.observableArrayList();
    private FilteredList<IssueLog> filteredData;
    private IssueLog selectedLog = null;
    private static final DateTimeFormatter TIMESTAMP_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final String FILE_NAME = "AudioIssuesLog.bin";

    @FXML
    public void initialize() {
        initializeComboBoxes();
        initializeTable();
        initializeSearch();
        updateRecordCount();
    }

    private void initializeComboBoxes() {
        ProblemTypeComboBox.setItems(FXCollections.observableArrayList(
                "Feedback", "Noise", "Dead mic", "Distortion", "Dropout",
                "Ground Loop", "Clipping", "Intermittent"
        ));
        SeverityComboBox.setItems(FXCollections.observableArrayList(
                "Critical", "High", "Medium", "Low"
        ));
        StatusComboBox.setItems(FXCollections.observableArrayList(
                "Resolved", "Pending", "Escalated", "Monitoring"
        ));
        ActionComboBox.setItems(FXCollections.observableArrayList(
                "Adjust gain/EQ", "Replace cable", "Swap mic", "Mute frequency",
                "Reposition speaker", "Add DI box", "Reset processor", "No action yet"
        ));
        StatusComboBox.getSelectionModel().selectFirst();
    }

    private void initializeTable() {
        timeCol.setCellValueFactory(new PropertyValueFactory<>("timestamp"));
        channelCol.setCellValueFactory(new PropertyValueFactory<>("channel"));
        problemCol.setCellValueFactory(new PropertyValueFactory<>("problem"));
        severityCol.setCellValueFactory(new PropertyValueFactory<>("severity"));
        actionCol.setCellValueFactory(new PropertyValueFactory<>("action"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        notesCol.setCellValueFactory(new PropertyValueFactory<>("notes"));

        severityCol.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(String severity, boolean empty) {
                super.updateItem(severity, empty);
                if (empty || severity == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(severity);
                    setStyle(switch (severity) {
                        case "Critical" -> "-fx-text-fill: #c62828; -fx-font-weight: bold;";
                        case "High" -> "-fx-text-fill: #ef6c00; -fx-font-weight: bold;";
                        case "Medium" -> "-fx-text-fill: #f9a825; -fx-font-weight: bold;";
                        case "Low" -> "-fx-text-fill: #2e7d32; -fx-font-weight: bold;";
                        default -> "";
                    });
                }
            }
        });

        statusCol.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(String status, boolean empty) {
                super.updateItem(status, empty);
                if (empty || status == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(status);
                    setStyle(switch (status) {
                        case "Resolved" -> "-fx-text-fill: #2e7d32; -fx-font-weight: bold;";
                        case "Pending" -> "-fx-text-fill: #ef6c00; -fx-font-weight: bold;";
                        case "Escalated" -> "-fx-text-fill: #c62828; -fx-font-weight: bold;";
                        case "Monitoring" -> "-fx-text-fill: #1565c0; -fx-font-weight: bold;";
                        default -> "";
                    });
                }
            }
        });

        logTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                selectedLog = newVal;
                populateForm(newVal);
                statusLabel.setTextFill(Color.web("#1565c0"));
                statusLabel.setStyle("-fx-background-color: #dbeafe; -fx-padding: 12; -fx-background-radius: 6; -fx-font-weight: bold;");
                statusLabel.setText("Selected: " + newVal.getChannel() + " | " + newVal.getProblem());
                statusLabel.setVisible(true);
                statusLabel.setManaged(true);
            }
        });

        filteredData = new FilteredList<>(masterData, p -> true);
        logTableView.setItems(filteredData);
    }

    private void initializeSearch() {
        searchField.textProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal == null || newVal.isBlank()) {
                filteredData.setPredicate(p -> true);
            }
        });
    }

    @FXML
    public void saveLogbuttonOnAction(ActionEvent event) {
        clearStatus();

        String channel = AffectedChannelTextField.getText();
        String problem = ProblemTypeComboBox.getValue();
        String severity = SeverityComboBox.getValue();
        String status = StatusComboBox.getValue();
        String action = ActionComboBox.getValue();
        String notes = notesTextArea.getText();

        StringBuilder errors = new StringBuilder();
        if (isNullOrEmpty(channel)) errors.append("• Affected Channel is required.\n");
        if (problem == null) errors.append("• Problem Type is required.\n");
        if (severity == null) errors.append("• Severity is required.\n");
        if (status == null) errors.append("• Status is required.\n");
        if (action == null) errors.append("• Action Taken is required.\n");

        if (errors.length() > 0) {
            showError("Please fix the following:\n" + errors.toString());
            return;
        }

        String timestamp = LocalDateTime.now().format(TIMESTAMP_FORMATTER);
        IssueLog log = new IssueLog(timestamp, channel.trim(), problem, severity, action, status,
                notes != null ? notes.trim() : "");

        masterData.add(log);
        saveToBinaryFile();
        refreshTable();
        clearForm();
        updateRecordCount();
        showSuccess("✓ Issue logged: " + problem + " on " + channel);
    }

    @FXML
    public void handleUpdate() {
        if (selectedLog == null) {
            showError("Please select a record from the table to update.");
            return;
        }

        String channel = AffectedChannelTextField.getText();
        String problem = ProblemTypeComboBox.getValue();
        String severity = SeverityComboBox.getValue();
        String status = StatusComboBox.getValue();
        String action = ActionComboBox.getValue();
        String notes = notesTextArea.getText();

        if (isNullOrEmpty(channel) || problem == null || severity == null || status == null || action == null) {
            showError("All required fields must be filled to update.");
            return;
        }

        int index = masterData.indexOf(selectedLog);
        if (index >= 0) {
            IssueLog updated = new IssueLog(
                    selectedLog.getTimestamp(),
                    channel.trim(), problem, severity, action, status,
                    notes != null ? notes.trim() : ""
            );
            masterData.set(index, updated);
            saveToBinaryFile();
            refreshTable();
            clearForm();
            selectedLog = null;
            logTableView.getSelectionModel().clearSelection();
            updateRecordCount();
            showSuccess("✓ Record updated successfully.");
        }
    }

    @FXML
    public void handleDelete() {
        IssueLog selected = logTableView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showError("Please select a record to delete.");
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirm Deletion");
        confirm.setHeaderText("Delete Issue Log");
        confirm.setContentText("Delete log for '" + selected.getChannel() + "' (" + selected.getProblem() + ")?");

        Optional<ButtonType> result = confirm.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            masterData.remove(selected);
            if (selectedLog == selected) {
                clearForm();
                selectedLog = null;
            }
            saveToBinaryFile();
            refreshTable();
            updateRecordCount();
            showSuccess("Deleted: " + selected.getChannel());
        }
    }

    @FXML
    public void handleClear() {
        clearForm();
        selectedLog = null;
        logTableView.getSelectionModel().clearSelection();
        clearStatus();
    }

    @FXML
    public void handleSearch() {
        String query = searchField.getText().toLowerCase().trim();
        if (query.isEmpty()) {
            filteredData.setPredicate(p -> true);
            return;
        }
        filteredData.setPredicate(log ->
                log.getChannel().toLowerCase().contains(query) ||
                        log.getProblem().toLowerCase().contains(query) ||
                        log.getStatus().toLowerCase().contains(query)
        );
    }

    @FXML
    public void handleResetSearch() {
        searchField.clear();
        filteredData.setPredicate(p -> true);
    }

    private void saveToBinaryFile() {
        try {
            File file = new File(FILE_NAME);
            FileOutputStream fos;
            ObjectOutputStream oos;

            if (file.exists()) {
                fos = new FileOutputStream(file, true);
                oos = new AppendableObjectOutputStream(fos);
            } else {
                fos = new FileOutputStream(file);
                oos = new ObjectOutputStream(fos);
            }

            for (IssueLog log : masterData) {
                oos.writeObject(log);
            }
            oos.close();
        } catch (Exception e) {
            showError("Failed to save to file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void populateForm(IssueLog log) {
        AffectedChannelTextField.setText(log.getChannel());
        ProblemTypeComboBox.setValue(log.getProblem());
        SeverityComboBox.setValue(log.getSeverity());
        StatusComboBox.setValue(log.getStatus());
        ActionComboBox.setValue(log.getAction());
        notesTextArea.setText(log.getNotes());
    }

    private void clearForm() {
        AffectedChannelTextField.clear();
        ProblemTypeComboBox.setValue(null);
        SeverityComboBox.setValue(null);
        StatusComboBox.getSelectionModel().selectFirst();
        ActionComboBox.setValue(null);
        notesTextArea.clear();
    }

    private void refreshTable() {
        logTableView.refresh();
    }

    private void updateRecordCount() {
        recordCountLabel.setText(String.valueOf(masterData.size()));
    }

    private void showError(String msg) {
        statusLabel.setText(msg);
        statusLabel.setTextFill(Color.web("#7f1d1d"));
        statusLabel.setStyle("-fx-background-color: #fecaca; -fx-padding: 12; -fx-background-radius: 6;");
        statusLabel.setVisible(true);
        statusLabel.setManaged(true);
    }

    private void showSuccess(String msg) {
        statusLabel.setText(msg);
        statusLabel.setTextFill(Color.web("#14532d"));
        statusLabel.setStyle("-fx-background-color: #bbf7d0; -fx-padding: 12; -fx-background-radius: 6;");
        statusLabel.setVisible(true);
        statusLabel.setManaged(true);
    }

    private void clearStatus() {
        statusLabel.setVisible(false);
        statusLabel.setManaged(false);
    }

    private boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    @FXML
    public void DashboardButtonOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                    "/com/example/music_band_oop/DashboardOfUsers/SoundEngineerDashbroad.fxml"));
            Scene dashboardScene = new Scene(fxmlLoader.load());
            Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            currentStage.setScene(dashboardScene);
            currentStage.setTitle("Sound Engineer Dashboard");
            currentStage.show();
        } catch (Exception e) {
            showError("Failed to navigate: " + e.getMessage());
        }
    }
}