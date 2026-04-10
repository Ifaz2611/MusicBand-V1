package com.example.music_band_oop.Controller.BassistUser_KeyboardistUser_Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class KeyGoal4SoundSetupController
{
    @javafx.fxml.FXML
    private Label vol_label;
    @javafx.fxml.FXML
    private TableColumn<ChannelSetup, String> colPan;
    @javafx.fxml.FXML
    private ComboBox<String> eventComboBox;
    @javafx.fxml.FXML
    private TableView<ChannelSetup> channelTable;
    @javafx.fxml.FXML
    private Label messageLabel;
    @javafx.fxml.FXML
    private Label soundEngineerLabel;
    @javafx.fxml.FXML
    private TableColumn<ChannelSetup, String> colMute;
    @javafx.fxml.FXML
    private Slider volumeSlider;
    @javafx.fxml.FXML
    private TableColumn<ChannelSetup, String> colChannel;
    @javafx.fxml.FXML
    private Label soundEngineerValueLabel;
    @javafx.fxml.FXML
    private TextField filterTextField;
    @javafx.fxml.FXML
    private TableColumn<ChannelSetup, String> colEffects;
    @javafx.fxml.FXML
    private TableColumn<ChannelSetup, String> colOutputLevel;
    @javafx.fxml.FXML
    private Label filterLabel;

    private ArrayList<ChannelSetup> channelList = new ArrayList<>();
    @javafx.fxml.FXML
    private Label statusLabel;

    @javafx.fxml.FXML
    public void initialize() {
        colChannel.setCellValueFactory(new PropertyValueFactory<>("channel"));
        colOutputLevel.setCellValueFactory(new PropertyValueFactory<>("outputLevel"));
        colEffects.setCellValueFactory(new PropertyValueFactory<>("effects"));
        colPan.setCellValueFactory(new PropertyValueFactory<>("pan"));
        colMute.setCellValueFactory(new PropertyValueFactory<>("mute"));

        eventComboBox.getItems().addAll("Summer Fest 2025", "Jazz Night", "Rock Arena", "Blues Festival");

        loadData();
        channelTable.getItems().addAll(channelList);

        // show volume slider value
        volumeSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            vol_label.setText("Volume: " + newVal.intValue());
        });
    }

    private void loadData() {
        channelList.add(new ChannelSetup("Ch-1", "High", "Reverb", "Left", "No"));
        channelList.add(new ChannelSetup("Ch-2", "Medium", "Delay", "Center", "No"));
        channelList.add(new ChannelSetup("Ch-3", "Low", "Chorus", "Right", "Yes"));
    }

    @javafx.fxml.FXML
    public void saveConfigBtn(ActionEvent actionEvent) {
        if (eventComboBox.getValue() == null) {
            messageLabel.setText("Select event first.");
            return;
        }

        messageLabel.setText("Configuration saved for " + eventComboBox.getValue());
    }

    @javafx.fxml.FXML
    public void verifyBalanceBtn(ActionEvent actionEvent) {
        if (eventComboBox.getValue() == null) {
            messageLabel.setText("Please select an event first.");
            return;
        }
        messageLabel.setText("Sound balance verified with Sound Engineer. Levels are good.");
        statusLabel.setText("Status: Balance verified");
    }

    @javafx.fxml.FXML
    public void resetFilterButton(ActionEvent actionEvent) {
        channelTable.getItems().clear();
        channelTable.getItems().addAll(channelList);

        filterTextField.clear();

        messageLabel.setText("Filter reset.");
    }

    @javafx.fxml.FXML
    public void filterButton(ActionEvent actionEvent) {
        String text = filterTextField.getText().toLowerCase();

        channelTable.getItems().clear();

        for (ChannelSetup c : channelList) {
            if (c.getChannel().toLowerCase().contains(text)) {
                channelTable.getItems().add(c);
            }
        }

        messageLabel.setText("Filtered result.");
    }

    @javafx.fxml.FXML
    public void testEffectsBtn(ActionEvent actionEvent) {
        ChannelSetup c = channelTable.getSelectionModel().getSelectedItem();

        if (c == null) {
            messageLabel.setText("Select a channel first.");
            return;
        }

        messageLabel.setText("Testing effects: " + c.getEffects());
    }

    @javafx.fxml.FXML
    public void testVolumeBtn(ActionEvent actionEvent) {
        int volume = (int) volumeSlider.getValue();

        messageLabel.setText("Volume set to: " + volume);
    }

    @javafx.fxml.FXML
    public void OnSelected_eventComboBox(ActionEvent actionEvent) {
        if (eventComboBox.getValue() == null) return;

        // dummy data
        soundEngineerValueLabel.setText("Fahmida Islam");
        statusLabel.setText("Status: Event selected. Sound engineer assigned.");
        messageLabel.setText("");
    }
}