package com.example.music_band_oop.Controller.FXMLControllerForUser1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class SE_Goal1_ViewController {

    // === FXML Injected Fields ===
    @FXML private TextField EventNameTextField;
    @FXML private TextField VenueNameTextField;
    @FXML private TextField BandMembersTextField;
    @FXML private TextField MicLevelTextField;
    @FXML private TextField SpeakerBalanceTextField;
    @FXML private TextField MonitorLevelTextField;
    @FXML private TextField EffectsLevelTextField;
    @FXML private TableView<Equipment> equipmentTable;
    @FXML private TableColumn<Equipment, String> EventNameTableColumn;
    @FXML private TableColumn<Equipment, String> VenueTableColumn;
    @FXML private TableColumn<Equipment, String> MicTableColumn;
    @FXML private TableColumn<Equipment, String> SpeakerBalanceTableColumn;
    @FXML private TableColumn<Equipment, String> EffectTableColumn;
    @FXML private Label AlertSoundSetupLabel;

    // Store table data
    private ObservableList<Equipment> equipmentList = FXCollections.observableArrayList();
    @FXML
    private Label equipmentStatusLabel;

    // === Initialize - Setup Table Columns Only ===
    @FXML
    public void initialize() {
        // Setup table columns
        EventNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        VenueTableColumn.setCellValueFactory(new PropertyValueFactory<>("venue"));
        MicTableColumn.setCellValueFactory(new PropertyValueFactory<>("mic"));
        SpeakerBalanceTableColumn.setCellValueFactory(new PropertyValueFactory<>("speakerBalance"));
        EffectTableColumn.setCellValueFactory(new PropertyValueFactory<>("effect"));

        // Set empty list to table
        equipmentTable.setItems(equipmentList);
    }

    // === Sound Check Button - Add Input to Table ===
    @FXML
    public void SoundSetupButtonOnAction(ActionEvent actionEvent) {
        // Get user input
        String eventName = EventNameTextField.getText().trim();
        String venue = VenueNameTextField.getText().trim();
        String mic = MicLevelTextField.getText().trim();
        String speakerBalance = SpeakerBalanceTextField.getText().trim();
        String effects = EffectsLevelTextField.getText().trim();

        // Validate - check if fields are empty
        if (eventName.isEmpty() || venue.isEmpty() || mic.isEmpty() ||
                speakerBalance.isEmpty() || effects.isEmpty()) {

            AlertSoundSetupLabel.setText("FAILED");
            AlertSoundSetupLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        // Add new row to table with user input
        equipmentList.add(new Equipment(eventName, venue, mic, speakerBalance, effects));
        equipmentTable.refresh();

        // Success message
        AlertSoundSetupLabel.setText("PASSED");
        AlertSoundSetupLabel.setStyle("-fx-text-fill: green;");
    }

    // === Dashboard Button ===
    @FXML
    public void DashboardButtonOnAction(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(
                    "/com/example/music_band_oop/DashboardOfUsers/SoundEngineerDashbroad.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Sound Engineer Dashboard");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // === Equipment Data Class ===
    public static class Equipment {
        private final String eventName;
        private final String venue;
        private final String mic;
        private final String speakerBalance;
        private final String effect;

        public Equipment(String eventName, String venue, String mic,
                         String speakerBalance, String effect) {
            this.eventName = eventName;
            this.venue = venue;
            this.mic = mic;
            this.speakerBalance = speakerBalance;
            this.effect = effect;
        }

        public String getEventName() { return eventName; }
        public String getVenue() { return venue; }
        public String getMic() { return mic; }
        public String getSpeakerBalance() { return speakerBalance; }
        public String getEffect() { return effect; }
    }
}