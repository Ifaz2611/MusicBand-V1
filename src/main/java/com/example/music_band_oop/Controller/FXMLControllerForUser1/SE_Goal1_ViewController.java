package com.example.music_band_oop.Controller.FXMLControllerForUser1;

import com.example.music_band_oop.Controller.mainuser.SoundConfiguration;
import com.example.music_band_oop.Controller.mainuser.SoundSetupRecord;
import com.example.music_band_oop.Controller.mainuser.event;
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

    @FXML private TableView<SoundSetupRecord> equipmentTable;
    @FXML private TableColumn<SoundSetupRecord, String>  EventNameTableColumn;
    @FXML private TableColumn<SoundSetupRecord, String>  VenueTableColumn;
    @FXML private TableColumn<SoundSetupRecord, Integer> MicTableColumn;          // ← Integer
    @FXML private TableColumn<SoundSetupRecord, Integer> SpeakerBalanceTableColumn; // ← Integer
    @FXML private TableColumn<SoundSetupRecord, String>  EffectTableColumn;

    @FXML private Label AlertSoundSetupLabel;
    @FXML private Label equipmentStatusLabel;

    private ObservableList<SoundSetupRecord> recordList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        EventNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        VenueTableColumn.setCellValueFactory(new PropertyValueFactory<>("venue"));
        MicTableColumn.setCellValueFactory(new PropertyValueFactory<>("micLevel"));
        SpeakerBalanceTableColumn.setCellValueFactory(new PropertyValueFactory<>("speakerBalance"));
        EffectTableColumn.setCellValueFactory(new PropertyValueFactory<>("effect"));

        equipmentTable.setItems(recordList);
    }

    @FXML
    public void SoundSetupButtonOnAction(ActionEvent actionEvent) {

        String eventName   = EventNameTextField.getText().trim();
        String venue       = VenueNameTextField.getText().trim();
        String bandMembers = BandMembersTextField.getText().trim();
        String micText     = MicLevelTextField.getText().trim();
        String speakerText = SpeakerBalanceTextField.getText().trim();
        String monitorText = MonitorLevelTextField.getText().trim();
        String effects     = EffectsLevelTextField.getText().trim();

        // Validate empty fields
        if (eventName.isEmpty() || venue.isEmpty() || bandMembers.isEmpty() ||
                micText.isEmpty() || speakerText.isEmpty() ||
                monitorText.isEmpty() || effects.isEmpty()) {

            AlertSoundSetupLabel.setText("FAILED — All fields are required.");
            AlertSoundSetupLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        // Validate numeric fields
        int micLevel, speakerBalance, monitorLevel;
        try {
            micLevel       = Integer.parseInt(micText);
            speakerBalance = Integer.parseInt(speakerText);
            monitorLevel   = Integer.parseInt(monitorText);
        } catch (NumberFormatException e) {
            AlertSoundSetupLabel.setText("FAILED — Mic, Speaker, Monitor must be numbers.");
            AlertSoundSetupLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        // Build model objects
        event eventObj            = new event(eventName, venue, bandMembers);
        SoundConfiguration config = new SoundConfiguration(micLevel, speakerBalance, monitorLevel, effects);

        // Build table record
        SoundSetupRecord record = new SoundSetupRecord(
                eventObj.getEventName(),
                eventObj.getVenue(),
                config.getMicLevel(),
                config.getSpeakerBalance(),
                config.getEffects()
        );

        recordList.add(record);
        equipmentTable.refresh();

        AlertSoundSetupLabel.setText("PASSED");
        AlertSoundSetupLabel.setStyle("-fx-text-fill: green;");

        System.out.println(eventObj);
        System.out.println(config);
        System.out.println(record);

        clearFields();
    }

    private void clearFields() {
        EventNameTextField.clear();
        VenueNameTextField.clear();
        BandMembersTextField.clear();
        MicLevelTextField.clear();
        SpeakerBalanceTextField.clear();
        MonitorLevelTextField.clear();
        EffectsLevelTextField.clear();
    }

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
}