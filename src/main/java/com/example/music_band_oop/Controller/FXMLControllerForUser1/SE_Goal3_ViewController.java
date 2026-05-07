package com.example.music_band_oop.Controller.FXMLControllerForUser1;

import com.example.music_band_oop.Controller.mainuser.SoundCheckRecord;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SE_Goal3_ViewController {

    private static final Logger LOGGER = Logger.getLogger(SE_Goal3_ViewController.class.getName());
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // ==================== FXML Injected Fields ====================

    // Form Fields
    @FXML private TextField EventTextField;
    @FXML private DatePicker eventDatePicker;
    @FXML private ComboBox<String> venueComboBox;
    @FXML private TextField engineerTextField;
    @FXML private Slider soundLevelSlider;
    @FXML private Label sliderValueLabel;
    @FXML private CheckBox performerCheck;
    @FXML private CheckBox equipmentCheck;
    @FXML private CheckBox backupCheck;
    @FXML private TextArea notesTextArea;
    @FXML private ComboBox<String> statusComboBox;
    @FXML private Label confirmLabel;
    @FXML private Button confirmButton;

    // Table
    @FXML private TableView<SoundCheckRecord> deviceTable;
    @FXML private TableColumn<SoundCheckRecord, String> EventNameCol;
    @FXML private TableColumn<SoundCheckRecord, LocalDate> eventDateCol;
    @FXML private TableColumn<SoundCheckRecord, String> venueCol;
    @FXML private TableColumn<SoundCheckRecord, String> TeststatusCol;
    @FXML private TableColumn<SoundCheckRecord, Number> SoundLevelCol;
    @FXML private TableColumn<SoundCheckRecord, String> engineerCol;

    // Search & Chart
    @FXML private TextField searchField;
    @FXML private BarChart<String, Number> soundLevelChart;

    // Status
    @FXML private Label statusBarLabel;

    // ==================== Data & State ====================

    private final ObservableList<SoundCheckRecord> masterData = FXCollections.observableArrayList();
    private FilteredList<SoundCheckRecord> filteredData;
    private SoundCheckRecord selectedRecord = null;

    // ==================== Initialization ====================

    @FXML
    public void initialize() {
        LOGGER.info("Initializing Sound Check Dashboard Controller...");

        initializeComboBoxes();
        initializeSlider();
        initializeTable();
        initializeSearch();
        initializeChart();

        updateStatus("System initialized. Ready for input.");
    }

    private void initializeComboBoxes() {
        venueComboBox.setItems(FXCollections.observableArrayList(
                "Main Hall", "Outdoor Stage", "Studio A", "Studio B", "Rehearsal Room", "Basement"
        ));
        statusComboBox.setItems(FXCollections.observableArrayList(
                "Confirmed", "Pending", "Failed", "Cancelled", "In Progress"
        ));
        statusComboBox.getSelectionModel().selectFirst();
    }

    private void initializeSlider() {
        soundLevelSlider.valueProperty().addListener((obs, oldVal, newVal) ->
                sliderValueLabel.setText(String.format("Current: %d dB", newVal.intValue()))
        );
    }

    private void initializeTable() {
        // Bind columns to model properties
        EventNameCol.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        eventDateCol.setCellValueFactory(new PropertyValueFactory<>("eventDate"));
        venueCol.setCellValueFactory(new PropertyValueFactory<>("venue"));
        TeststatusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        SoundLevelCol.setCellValueFactory(new PropertyValueFactory<>("soundLevel"));
        engineerCol.setCellValueFactory(new PropertyValueFactory<>("engineerName"));

        // Format date column
        eventDateCol.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setText(empty || date == null ? null : date.format(DATE_FORMATTER));
            }
        });

        // Color-code status column
        TeststatusCol.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(String status, boolean empty) {
                super.updateItem(status, empty);
                if (empty || status == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(status);
                    setStyle(switch (status) {
                        case "Confirmed" -> "-fx-text-fill: #2e7d32; -fx-font-weight: bold;";
                        case "Pending" -> "-fx-text-fill: #ef6c00; -fx-font-weight: bold;";
                        case "Failed" -> "-fx-text-fill: #c62828; -fx-font-weight: bold;";
                        case "In Progress" -> "-fx-text-fill: #1565c0; -fx-font-weight: bold;";
                        default -> "-fx-text-fill: #555555;";
                    });
                }
            }
        });

        // Handle row selection for editing
        deviceTable.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                selectedRecord = newVal;
                populateForm(newVal);
                confirmButton.setText("Add New"); // Prevent accidental overwrite
                confirmLabel.setTextFill(Color.web("#1565c0"));
                confirmLabel.setText("Selected: " + newVal.getEventName() + ". Click 'Update' to modify.");
            }
        });

        filteredData = new FilteredList<>(masterData, p -> true);
        deviceTable.setItems(filteredData);
    }

    private void initializeSearch() {
        searchField.textProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal == null || newVal.isBlank()) {
                filteredData.setPredicate(p -> true);
            }
        });
    }

    private void initializeChart() {
        refreshChart();
    }

    // ==================== Core CRUD Operations ====================

    @FXML
    public void ConfirmSoundCheckButtonOnAction() {
        if (!validateForm()) return;

        SoundCheckRecord newRecord = buildRecordFromForm();

        // Check for duplicates by event name
        boolean exists = masterData.stream()
                .anyMatch(r -> r.getEventName().equalsIgnoreCase(newRecord.getEventName()));

        if (exists) {
            showAlert(Alert.AlertType.WARNING, "Duplicate Entry",
                    "An event with this name already exists. Please use a different name or update the existing entry.");
            confirmLabel.setTextFill(Color.RED);
            confirmLabel.setText("Event already exists.");
            return;
        }

        masterData.add(newRecord);
        refreshChart();
        clearForm();

        confirmLabel.setTextFill(Color.web("#2e7d32"));
        confirmLabel.setText(String.format("✓ Sound check confirmed: %s at %s (%d dB)",
                newRecord.getEventName(), newRecord.getVenue(), newRecord.getSoundLevel()));
        updateStatus(String.format("Added record: %s", newRecord.getEventName()));
        LOGGER.info("Added new sound check record: " + newRecord.getEventName());
    }

    @FXML
    public void handleUpdate() {
        if (selectedRecord == null) {
            showAlert(Alert.AlertType.WARNING, "No Selection",
                    "Please select a record from the table first, then modify the form and click Update.");
            return;
        }

        if (!validateForm()) return;

        // Build updated record keeping the same identity conceptually
        SoundCheckRecord updated = buildRecordFromForm();
        int index = masterData.indexOf(selectedRecord);

        if (index >= 0) {
            masterData.set(index, updated);
            refreshChart();
            clearForm();
            selectedRecord = null;
            deviceTable.getSelectionModel().clearSelection();

            confirmLabel.setTextFill(Color.web("#ef6c00"));
            confirmLabel.setText("✓ Record updated successfully.");
            updateStatus("Updated record: " + updated.getEventName());
            LOGGER.info("Updated sound check record: " + updated.getEventName());
        }
    }

    @FXML
    public void handleDeleteSelected() {
        SoundCheckRecord selected = deviceTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "No Selection", "Please select a record to delete.");
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirm Deletion");
        confirm.setHeaderText("Delete Sound Check Record");
        confirm.setContentText("Are you sure you want to permanently delete '" + selected.getEventName() + "'?");

        Optional<ButtonType> result = confirm.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            masterData.remove(selected);
            if (selectedRecord == selected) {
                clearForm();
                selectedRecord = null;
            }
            refreshChart();
            confirmLabel.setTextFill(Color.RED);
            confirmLabel.setText("Deleted: " + selected.getEventName());
            updateStatus("Deleted record: " + selected.getEventName());
            LOGGER.info("Deleted sound check record: " + selected.getEventName());
        }
    }

    @FXML
    public void handleClearForm() {
        clearForm();
        selectedRecord = null;
        deviceTable.getSelectionModel().clearSelection();
        confirmLabel.setTextFill(Color.web("#555555"));
        confirmLabel.setText("Form cleared. Ready for new entry.");
    }

    // ==================== Search & Filter ====================

    @FXML
    public void handleSearch() {
        String query = searchField.getText().toLowerCase().trim();
        if (query.isEmpty()) {
            filteredData.setPredicate(p -> true);
            updateStatus("Showing all records.");
            return;
        }

        filteredData.setPredicate(record ->
                record.getEventName().toLowerCase().contains(query) ||
                        (record.getVenue() != null && record.getVenue().toLowerCase().contains(query)) ||
                        (record.getEngineerName() != null && record.getEngineerName().toLowerCase().contains(query))
        );

        updateStatus(String.format("Search found %d matching record(s).", filteredData.size()));
    }

    @FXML
    public void handleResetSearch() {
        searchField.clear();
        filteredData.setPredicate(p -> true);
        updateStatus("Search reset. Showing all " + masterData.size() + " records.");
    }

    // ==================== Data Export ====================

    @FXML
    public void handleExportCSV() {
        if (masterData.isEmpty()) {
            showAlert(Alert.AlertType.INFORMATION, "Export", "No data available to export.");
            return;
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Export Sound Check Data");
        fileChooser.setInitialFileName("sound_checks_" + LocalDate.now() + ".csv");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("CSV Files (*.csv)", "*.csv")
        );

        File file = fileChooser.showSaveDialog(deviceTable.getScene().getWindow());
        if (file != null) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
                // Header
                writer.println("Event Name,Date,Venue,Status,Sound Level,Engineer,Performer Ready,Equipment Ready,Backup Ready,Notes");

                // Data rows
                for (SoundCheckRecord r : masterData) {
                    writer.printf("\"%s\",\"%s\",\"%s\",\"%s\",%d,\"%s\",%b,%b,%b,\"%s\"%n",
                            escapeCSV(r.getEventName()),
                            r.getEventDate() != null ? r.getEventDate().format(DATE_FORMATTER) : "",
                            escapeCSV(r.getVenue()),
                            r.getStatus(),
                            r.getSoundLevel(),
                            escapeCSV(r.getEngineerName()),
                            r.isPerformerReady(),
                            r.isEquipmentReady(),
                            r.isBackupReady(),
                            escapeCSV(r.getNotes())
                    );
                }

                confirmLabel.setTextFill(Color.web("#2e7d32"));
                confirmLabel.setText("✓ Exported " + masterData.size() + " records to CSV.");
                updateStatus("Data exported to: " + file.getName());
                LOGGER.info("Exported data to: " + file.getAbsolutePath());

            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "Failed to export CSV", e);
                showAlert(Alert.AlertType.ERROR, "Export Failed", "Could not write file: " + e.getMessage());
            }
        }
    }

    // ==================== Utilities & Helpers ====================

    @FXML
    public void handleLoadSample() {
        masterData.addAll(
                new SoundCheckRecord("Summer Rock Fest", LocalDate.now(), "Outdoor Stage",
                        "Confirmed", 88, "Alex Rivera", "Main PA tested. Subs positioned.", true, true, true),
                new SoundCheckRecord("Jazz Ensemble", LocalDate.now().plusDays(2), "Studio A",
                        "Pending", 65, "Sarah Chen", "Awaiting piano mic setup.", true, false, false),
                new SoundCheckRecord("EDM Night", LocalDate.now().plusDays(5), "Main Hall",
                        "In Progress", 95, "Mike Johnson", "High SPL required. Monitor check pending.", true, true, false)
        );
        refreshChart();
        updateStatus("Loaded 3 sample records for demonstration.");
        confirmLabel.setTextFill(Color.web("#1565c0"));
        confirmLabel.setText("Sample data loaded. Try searching, editing, or exporting!");
    }

    private boolean validateForm() {
        StringBuilder errors = new StringBuilder();

        if (isNullOrEmpty(EventTextField.getText())) {
            errors.append("• Event Name is required.\n");
        }
        if (eventDatePicker.getValue() == null) {
            errors.append("• Event Date is required.\n");
        } else if (eventDatePicker.getValue().isBefore(LocalDate.now().minusDays(1))) {
            errors.append("• Event Date cannot be in the distant past.\n");
        }
        if (isNullOrEmpty(engineerTextField.getText())) {
            errors.append("• Sound Engineer name is required.\n");
        }
        if (!performerCheck.isSelected() || !equipmentCheck.isSelected()) {
            errors.append("• Both Performer and Equipment checks must be confirmed.\n");
        }
        if (statusComboBox.getValue() == null) {
            errors.append("• Overall Status must be selected.\n");
        }

        if (errors.length() > 0) {
            confirmLabel.setTextFill(Color.RED);
            confirmLabel.setText("Please fix the following:\n" + errors.toString());
            return false;
        }
        return true;
    }

    private SoundCheckRecord buildRecordFromForm() {
        return new SoundCheckRecord(
                EventTextField.getText().trim(),
                eventDatePicker.getValue(),
                venueComboBox.getValue() != null ? venueComboBox.getValue().trim() : "Not Specified",
                statusComboBox.getValue(),
                (int) soundLevelSlider.getValue(),
                engineerTextField.getText().trim(),
                notesTextArea.getText().trim(),
                performerCheck.isSelected(),
                equipmentCheck.isSelected(),
                backupCheck.isSelected()
        );
    }

    private void populateForm(SoundCheckRecord record) {
        EventTextField.setText(record.getEventName());
        eventDatePicker.setValue(record.getEventDate());
        venueComboBox.setValue(record.getVenue());
        engineerTextField.setText(record.getEngineerName());
        soundLevelSlider.setValue(record.getSoundLevel());
        statusComboBox.setValue(record.getStatus());
        notesTextArea.setText(record.getNotes());
        performerCheck.setSelected(record.isPerformerReady());
        equipmentCheck.setSelected(record.isEquipmentReady());
        backupCheck.setSelected(record.isBackupReady());
    }

    private void clearForm() {
        EventTextField.clear();
        eventDatePicker.setValue(null);
        venueComboBox.setValue(null);
        engineerTextField.clear();
        soundLevelSlider.setValue(50.0);
        statusComboBox.getSelectionModel().selectFirst();
        notesTextArea.clear();
        performerCheck.setSelected(false);
        equipmentCheck.setSelected(false);
        backupCheck.setSelected(false);
        confirmButton.setText("Confirm / Add");
    }

    private void refreshChart() {
        soundLevelChart.getData().clear();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Sound Levels");

        for (SoundCheckRecord record : masterData) {
            series.getData().add(new XYChart.Data<>(record.getEventName(), record.getSoundLevel()));
        }

        soundLevelChart.getData().add(series);
    }

    private void updateStatus(String message) {
        statusBarLabel.setText("Status: " + message);
    }

    private boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    private String escapeCSV(String input) {
        if (input == null) return "";
        return input.replace("\"", "\"\"");
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    // ==================== Navigation ====================

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
            LOGGER.info("Navigated back to Dashboard");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to navigate to Dashboard", e);
            showAlert(Alert.AlertType.ERROR, "Navigation Error", "Could not load dashboard: " + e.getMessage());
        }
    }
}