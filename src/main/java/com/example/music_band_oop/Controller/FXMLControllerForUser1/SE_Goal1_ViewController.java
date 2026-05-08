package com.example.music_band_oop.Controller.FXMLControllerForUser1;

import com.example.music_band_oop.Controller.mainuser.SoundSetupRecord;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class SE_Goal1_ViewController {

    @FXML private TextField EventNameTextField;
    @FXML private ComboBox<String> VenueNameComboBox;
    @FXML private TextField BandMembersTextField;
    @FXML private DatePicker setupDatePicker;

    @FXML private Slider MicLevelSlider;
    @FXML private Label MicLevelValueLabel;
    @FXML private Slider SpeakerBalanceSlider;
    @FXML private Label SpeakerBalanceValueLabel;
    @FXML private Slider MonitorLevelSlider;
    @FXML private Label MonitorLevelValueLabel;
    @FXML private ComboBox<String> EffectsLevelComboBox;

    @FXML private TableView<SoundSetupRecord> equipmentTable;
    @FXML private TableColumn<SoundSetupRecord, String> EventNameTableColumn;
    @FXML private TableColumn<SoundSetupRecord, String> VenueTableColumn;
    @FXML private TableColumn<SoundSetupRecord, String> DateTableColumn;
    @FXML private TableColumn<SoundSetupRecord, String> BandTableColumn;
    @FXML private TableColumn<SoundSetupRecord, Integer> MicTableColumn;
    @FXML private TableColumn<SoundSetupRecord, Integer> SpeakerBalanceTableColumn;
    @FXML private TableColumn<SoundSetupRecord, Integer> MonitorTableColumn;
    @FXML private TableColumn<SoundSetupRecord, String> EffectTableColumn;
    @FXML private TableColumn<SoundSetupRecord, String> StatusTableColumn;

    @FXML private Label AlertSoundSetupLabel;
    @FXML private Label recordCountLabel;

    private final ObservableList<SoundSetupRecord> recordList = FXCollections.observableArrayList();
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @FXML
    public void initialize() {
        initializeComboBoxes();
        initializeSliders();
        initializeTable();
        updateRecordCount();
    }

    private void initializeComboBoxes() {
        VenueNameComboBox.setItems(FXCollections.observableArrayList(
                "Main Hall", "Outdoor Stage", "Studio A", "Studio B", "Rehearsal Room", "Basement"
        ));
        EffectsLevelComboBox.setItems(FXCollections.observableArrayList(
                "None", "Reverb Light", "Reverb Heavy", "Delay", "Chorus", "Distortion", "Compression", "EQ Boost"
        ));
    }

    private void initializeSliders() {
        MicLevelSlider.valueProperty().addListener((obs, oldVal, newVal) ->
                MicLevelValueLabel.setText(String.valueOf(newVal.intValue())));
        SpeakerBalanceSlider.valueProperty().addListener((obs, oldVal, newVal) ->
                SpeakerBalanceValueLabel.setText(String.valueOf(newVal.intValue())));
        MonitorLevelSlider.valueProperty().addListener((obs, oldVal, newVal) ->
                MonitorLevelValueLabel.setText(String.valueOf(newVal.intValue())));
    }

    private void initializeTable() {
        EventNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        VenueTableColumn.setCellValueFactory(new PropertyValueFactory<>("venue"));
        DateTableColumn.setCellValueFactory(new PropertyValueFactory<>("setupDate"));
        BandTableColumn.setCellValueFactory(new PropertyValueFactory<>("bandMembers"));
        MicTableColumn.setCellValueFactory(new PropertyValueFactory<>("micLevel"));
        SpeakerBalanceTableColumn.setCellValueFactory(new PropertyValueFactory<>("speakerBalance"));
        MonitorTableColumn.setCellValueFactory(new PropertyValueFactory<>("monitorLevel"));
        EffectTableColumn.setCellValueFactory(new PropertyValueFactory<>("effect"));
        StatusTableColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        StatusTableColumn.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(String status, boolean empty) {
                super.updateItem(status, empty);
                if (empty || status == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(status);
                    setStyle(switch (status) {
                        case "Configured" -> "-fx-text-fill: #2e7d32; -fx-font-weight: bold;";
                        case "Pending" -> "-fx-text-fill: #ef6c00; -fx-font-weight: bold;";
                        case "Failed" -> "-fx-text-fill: #c62828; -fx-font-weight: bold;";
                        default -> "-fx-text-fill: #555555;";
                    });
                }
            }
        });

        equipmentTable.setItems(recordList);
    }

    @FXML
    public void SoundSetupButtonOnAction(ActionEvent actionEvent) {
        clearStatus();

        String eventName = EventNameTextField.getText();
        String venue = VenueNameComboBox.getValue();
        String bandMembers = BandMembersTextField.getText();
        LocalDate date = setupDatePicker.getValue();
        int micLevel = (int) MicLevelSlider.getValue();
        int speakerBalance = (int) SpeakerBalanceSlider.getValue();
        int monitorLevel = (int) MonitorLevelSlider.getValue();
        String effect = EffectsLevelComboBox.getValue();

        StringBuilder errors = new StringBuilder();
        if (isNullOrEmpty(eventName)) errors.append("• Event Name is required.\n");
        if (isNullOrEmpty(venue)) errors.append("• Venue is required.\n");
        if (isNullOrEmpty(bandMembers)) errors.append("• Band Members is required.\n");
        if (date == null) errors.append("• Setup Date is required.\n");
        if (effect == null) errors.append("• Effect preset is required.\n");

        if (errors.length() > 0) {
            showError("Please fix the following:\n" + errors.toString());
            return;
        }

        boolean exists = recordList.stream()
                .anyMatch(r -> r.getEventName().equalsIgnoreCase(eventName.trim()));
        if (exists) {
            showError("An event with this name already exists.");
            return;
        }

        SoundSetupRecord record = new SoundSetupRecord(
                eventName.trim(),
                venue,
                date.format(DATE_FORMATTER),
                bandMembers.trim(),
                micLevel,
                speakerBalance,
                monitorLevel,
                effect,
                "Configured"
        );

        recordList.add(record);
        updateRecordCount();
        showSuccess("✓ Sound setup configured for: " + eventName);
        clearFields();
    }

    @FXML
    public void handleDeleteSelected() {
        SoundSetupRecord selected = equipmentTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showError("Please select a record to delete.");
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirm Deletion");
        confirm.setHeaderText("Delete Sound Setup Record");
        confirm.setContentText("Delete '" + selected.getEventName() + "'?");

        Optional<ButtonType> result = confirm.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            recordList.remove(selected);
            updateRecordCount();
            showSuccess("Deleted: " + selected.getEventName());
        }
    }

    @FXML
    public void handleClearForm() {
        clearFields();
        clearStatus();
        equipmentTable.getSelectionModel().clearSelection();
    }

    @FXML
    public void DashboardButtonOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/music_band_oop/DashboardOfUsers/SoundEngineerDashbroad.fxml"));
            Scene dashboardScene = new Scene(fxmlLoader.load());
            Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            currentStage.setScene(dashboardScene);
            currentStage.setTitle("Sound Engineer Dashboard");
            currentStage.show();
        } catch (Exception e) {
            showError("Failed to navigate: " + e.getMessage());
        }
    }

    private void clearFields() {
        EventNameTextField.clear();
        VenueNameComboBox.setValue(null);
        BandMembersTextField.clear();
        setupDatePicker.setValue(null);
        MicLevelSlider.setValue(50.0);
        SpeakerBalanceSlider.setValue(0.0);
        MonitorLevelSlider.setValue(50.0);
        EffectsLevelComboBox.setValue(null);
    }

    private void updateRecordCount() {
        recordCountLabel.setText(String.valueOf(recordList.size()));
    }

    private void showError(String msg) {
        AlertSoundSetupLabel.setText(msg);
        AlertSoundSetupLabel.setTextFill(Color.web("#7f1d1d"));
        AlertSoundSetupLabel.setStyle("-fx-background-color: #fecaca; -fx-padding: 12; -fx-background-radius: 6;");
        AlertSoundSetupLabel.setVisible(true);
        AlertSoundSetupLabel.setManaged(true);
    }

    private void showSuccess(String msg) {
        AlertSoundSetupLabel.setText(msg);
        AlertSoundSetupLabel.setTextFill(Color.web("#14532d"));
        AlertSoundSetupLabel.setStyle("-fx-background-color: #bbf7d0; -fx-padding: 12; -fx-background-radius: 6;");
        AlertSoundSetupLabel.setVisible(true);
        AlertSoundSetupLabel.setManaged(true);
    }

    private void clearStatus() {
        AlertSoundSetupLabel.setVisible(false);
        AlertSoundSetupLabel.setManaged(false);
    }

    private boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
}