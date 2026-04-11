package com.example.music_band_oop.Controller.FXMLControllerForUser1;

import com.example.music_band_oop.Controller.mainuser.SoundSetupRecord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.util.ArrayList;


public class SE_Goal1_ViewController {
    @FXML private TextField EventNameTextField;
    @FXML private TextField VenueNameTextField;
    @FXML private TextField BandMembersTextField;
    @FXML private TextField MicLevelTextField;
    @FXML private TextField SpeakerBalanceTextField;
    @FXML private TextField MonitorLevelTextField;
    @FXML private TextField EffectsLevelTextField;

    @FXML private TableView<SoundSetupRecord> equipmentTable;
    @FXML private TableColumn<SoundSetupRecord, String> EventNameTableColumn;
    @FXML private TableColumn<SoundSetupRecord, String> VenueTableColumn;
    @FXML private TableColumn<SoundSetupRecord, Integer> MicTableColumn;
    @FXML private TableColumn<SoundSetupRecord, Integer> SpeakerBalanceTableColumn;
    @FXML private TableColumn<SoundSetupRecord, String> EffectTableColumn;
    @FXML private Label AlertSoundSetupLabel;


    private ArrayList<SoundSetupRecord> recordList = new ArrayList<>();

    @FXML
    public void initialize() {
        EventNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        VenueTableColumn.setCellValueFactory(new PropertyValueFactory<>("venue"));
        MicTableColumn.setCellValueFactory(new PropertyValueFactory<>("micLevel"));
        SpeakerBalanceTableColumn.setCellValueFactory(new PropertyValueFactory<>("speakerBalance"));
        EffectTableColumn.setCellValueFactory(new PropertyValueFactory<>("effect"));
    }

    @FXML
    public void SoundSetupButtonOnAction(ActionEvent actionEvent) {

        String eventName = EventNameTextField.getText();
        String venue = VenueNameTextField.getText();
        String bandMembers = BandMembersTextField.getText();
        String micText = MicLevelTextField.getText();
        String speakerText = SpeakerBalanceTextField.getText();
        String monitorText = MonitorLevelTextField.getText();
        String effects = EffectsLevelTextField.getText();
        if (eventName.isEmpty() || venue.isEmpty() || bandMembers.isEmpty() ||
                micText.isEmpty() || speakerText.isEmpty() ||
                monitorText.isEmpty() || effects.isEmpty()) {

            AlertSoundSetupLabel.setText("FAILED — All fields are required.");
            return;
        }

        int micLevel, speakerBalance;
        try {
            micLevel = Integer.parseInt(micText);
            speakerBalance = Integer.parseInt(speakerText);
        } catch (NumberFormatException e) {
            AlertSoundSetupLabel.setText("FAILED — Number required.");
            return;
        }
        SoundSetupRecord record = new SoundSetupRecord(eventName, venue, micLevel, speakerBalance, effects
        );

        recordList.add(record);
        equipmentTable.getItems().setAll(recordList);
        AlertSoundSetupLabel.setText("PASSED");
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
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/music_band_oop/DashboardOfUsers/SoundEngineerDashbroad.fxml"));
            Scene dashboardScene = new Scene(fxmlLoader.load());
            Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            currentStage.setScene(dashboardScene);
            currentStage.setTitle("Sound Engineer Dashboard");
            currentStage.show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}