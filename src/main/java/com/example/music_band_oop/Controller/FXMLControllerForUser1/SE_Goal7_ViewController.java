package com.example.music_band_oop.Controller.FXMLControllerForUser1;

import com.example.music_band_oop.Controller.mainuser.MonitorChannel;
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

public class SE_Goal7_ViewController {

    @FXML private TextField MonitorChannelTextField;
    @FXML private TextField PerformerTextField;
    @FXML private ComboBox<String> RoleComboBox;
    @FXML private ComboBox<String> InputSourceComboBox;

    @FXML private Slider CurrentLevelSlider;
    @FXML private Label CurrentLevelValueLabel;
    @FXML private Slider TargetLevelSlider;
    @FXML private Label TargetLevelValueLabel;
    @FXML private Slider PanSlider;
    @FXML private Label PanValueLabel;
    @FXML private CheckBox MuteCheckBox;
    @FXML private CheckBox SoloCheckBox;
    @FXML private TextArea notesTextArea;

    @FXML private TableView<MonitorChannel> MonitorTableView;
    @FXML private TableColumn<MonitorChannel, String> TimeCol;
    @FXML private TableColumn<MonitorChannel, String> MonitorChannelCol;
    @FXML private TableColumn<MonitorChannel, String> PerformerCol;
    @FXML private TableColumn<MonitorChannel, String> RoleCol;
    @FXML private TableColumn<MonitorChannel, Double> CurrentLevelCol;
    @FXML private TableColumn<MonitorChannel, Double> TargetLevelCol;
    @FXML private TableColumn<MonitorChannel, Integer> PanCol;
    @FXML private TableColumn<MonitorChannel, Boolean> MuteCol;
    @FXML private TableColumn<MonitorChannel, Boolean> SoloCol;
    @FXML private TableColumn<MonitorChannel, String> StatusCol;
    @FXML private TableColumn<MonitorChannel, String> NotesCol;

    @FXML private TextField searchField;
    @FXML private Label feedbackLabel;
    @FXML private Label recordCountLabel;
    @FXML private Label activeCountLabel;
    @FXML private Label mutedCountLabel;
    @FXML private Label soloCountLabel;

    private final ObservableList<MonitorChannel> masterData = FXCollections.observableArrayList();
    private FilteredList<MonitorChannel> filteredData;
    private MonitorChannel selectedChannel = null;
    private static final DateTimeFormatter TIMESTAMP_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static final String FILE_NAME = "MonitorControlLog.bin";

    @FXML
    public void initialize() {
        initializeComboBoxes();
        initializeSliders();
        initializeTable();
        initializeSearch();
        updateStats();
    }

    private void initializeComboBoxes() {
        RoleComboBox.setItems(FXCollections.observableArrayList(
                "Lead Vocal", "Backup Vocal", "Drums", "Bass", "Guitar", "Keys",
                "Horn Section", "Strings", "MC / Host", "DJ", "Other"
        ));
        InputSourceComboBox.setItems(FXCollections.observableArrayList(
                "Aux 1", "Aux 2", "Aux 3", "Aux 4", "Group 1", "Group 2",
                "Matrix A", "Matrix B", "Direct Out", "Post Fader"
        ));
    }

    private void initializeSliders() {
        CurrentLevelSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            int val = newVal.intValue();
            CurrentLevelValueLabel.setText(val + " dB");
            CurrentLevelValueLabel.setTextFill(val > 0 ? Color.web("#c62828") : Color.web("#415a77"));
        });

        TargetLevelSlider.valueProperty().addListener((obs, oldVal, newVal) ->
                TargetLevelValueLabel.setText(newVal.intValue() + " dB"));

        PanSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            int val = newVal.intValue();
            String text = val == 0 ? "C" : (val < 0 ? "L" + Math.abs(val) : "R" + val);
            PanValueLabel.setText(text);
        });
    }

    private void initializeTable() {
        TimeCol.setCellValueFactory(new PropertyValueFactory<>("timestamp"));
        MonitorChannelCol.setCellValueFactory(new PropertyValueFactory<>("channelName"));
        PerformerCol.setCellValueFactory(new PropertyValueFactory<>("performerName"));
        RoleCol.setCellValueFactory(new PropertyValueFactory<>("role"));
        CurrentLevelCol.setCellValueFactory(new PropertyValueFactory<>("currentLevel"));
        TargetLevelCol.setCellValueFactory(new PropertyValueFactory<>("targetLevel"));
        PanCol.setCellValueFactory(new PropertyValueFactory<>("pan"));
        MuteCol.setCellValueFactory(new PropertyValueFactory<>("muted"));
        SoloCol.setCellValueFactory(new PropertyValueFactory<>("solo"));
        StatusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        NotesCol.setCellValueFactory(new PropertyValueFactory<>("notes"));

        CurrentLevelCol.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(Double level, boolean empty) {
                super.updateItem(level, empty);
                if (empty || level == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(String.format("%.1f", level));
                    if (level > 0) setStyle("-fx-text-fill: #c62828; -fx-font-weight: bold;");
                    else if (level < -40) setStyle("-fx-text-fill: #757575;");
                    else setStyle("-fx-text-fill: #0d1b2a;");
                }
            }
        });

        StatusCol.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(String status, boolean empty) {
                super.updateItem(status, empty);
                if (empty || status == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(status);
                    setStyle(switch (status) {
                        case "Clipping" -> "-fx-text-fill: #c62828; -fx-font-weight: bold;";
                        case "Good" -> "-fx-text-fill: #2e7d32; -fx-font-weight: bold;";
                        case "Low" -> "-fx-text-fill: #f9a825; -fx-font-weight: bold;";
                        case "Muted" -> "-fx-text-fill: #757575; -fx-font-weight: bold;";
                        default -> "-fx-text-fill: #555555;";
                    });
                }
            }
        });

        MuteCol.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(Boolean muted, boolean empty) {
                super.updateItem(muted, empty);
                setText(empty || muted == null || !muted ? "" : "MUTE");
                setStyle(empty || muted == null || !muted ? "" : "-fx-text-fill: #c62828; -fx-font-weight: bold;");
            }
        });

        SoloCol.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(Boolean solo, boolean empty) {
                super.updateItem(solo, empty);
                setText(empty || solo == null || !solo ? "" : "SOLO");
                setStyle(empty || solo == null || !solo ? "" : "-fx-text-fill: #1565c0; -fx-font-weight: bold;");
            }
        });

        MonitorTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                selectedChannel = newVal;
                populateForm(newVal);
                feedbackLabel.setTextFill(Color.web("#1565c0"));
                feedbackLabel.setStyle("-fx-background-color: #dbeafe; -fx-padding: 12; -fx-background-radius: 6; -fx-font-weight: bold;");
                feedbackLabel.setText("Selected: " + newVal.getChannelName() + " (" + newVal.getPerformerName() + ")");
                feedbackLabel.setVisible(true);
                feedbackLabel.setManaged(true);
            }
        });

        filteredData = new FilteredList<>(masterData, p -> true);
        MonitorTableView.setItems(filteredData);
    }

    private void initializeSearch() {
        searchField.textProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal == null || newVal.isBlank()) {
                filteredData.setPredicate(p -> true);
            }
        });
    }

    @FXML
    public void AddToTheTableButtonOnAction(ActionEvent event) {
        clearFeedback();

        String channelName = MonitorChannelTextField.getText();
        String performer = PerformerTextField.getText();
        String role = RoleComboBox.getValue();
        String input = InputSourceComboBox.getValue();
        double currentLevel = CurrentLevelSlider.getValue();
        double targetLevel = TargetLevelSlider.getValue();
        int pan = (int) PanSlider.getValue();
        boolean muted = MuteCheckBox.isSelected();
        boolean solo = SoloCheckBox.isSelected();
        String notes = notesTextArea.getText();

        StringBuilder errors = new StringBuilder();
        if (isNullOrEmpty(channelName)) errors.append("• Channel Name is required.\n");
        if (isNullOrEmpty(performer)) errors.append("• Performer Name is required.\n");
        if (role == null) errors.append("• Role/Instrument is required.\n");

        if (errors.length() > 0) {
            showError("Please fix the following:\n" + errors.toString());
            return;
        }

        boolean exists = masterData.stream()
                .anyMatch(c -> c.getChannelName().equalsIgnoreCase(channelName.trim()));
        if (exists) {
            showError("A channel with this name already exists.");
            return;
        }

        String status = muted ? "Muted" : (currentLevel > 0 ? "Clipping" : (currentLevel < -40 ? "Low" : "Good"));
        String timestamp = LocalDateTime.now().format(TIMESTAMP_FORMATTER);

        MonitorChannel channel = new MonitorChannel(
                timestamp, channelName.trim(), performer.trim(), role,
                input != null ? input : "Not Assigned",
                currentLevel, targetLevel, pan, muted, solo,
                status, notes != null ? notes.trim() : ""
        );

        masterData.add(channel);
        updateStats();
        clearForm();
        showSuccess("✓ Channel added: " + channelName + " (" + performer + ")");
    }

    @FXML
    public void handleUpdate() {
        if (selectedChannel == null) {
            showError("Please select a channel from the table to update.");
            return;
        }

        String channelName = MonitorChannelTextField.getText();
        String performer = PerformerTextField.getText();
        String role = RoleComboBox.getValue();

        if (isNullOrEmpty(channelName) || isNullOrEmpty(performer) || role == null) {
            showError("Channel Name, Performer, and Role are required.");
            return;
        }

        int index = masterData.indexOf(selectedChannel);
        if (index >= 0) {
            String status = MuteCheckBox.isSelected() ? "Muted" :
                    (CurrentLevelSlider.getValue() > 0 ? "Clipping" :
                            (CurrentLevelSlider.getValue() < -40 ? "Low" : "Good"));

            MonitorChannel updated = new MonitorChannel(
                    selectedChannel.getTimestamp(),
                    channelName.trim(), performer.trim(), role,
                    InputSourceComboBox.getValue() != null ? InputSourceComboBox.getValue() : "Not Assigned",
                    CurrentLevelSlider.getValue(), TargetLevelSlider.getValue(),
                    (int) PanSlider.getValue(), MuteCheckBox.isSelected(), SoloCheckBox.isSelected(),
                    status, notesTextArea.getText() != null ? notesTextArea.getText().trim() : ""
            );

            masterData.set(index, updated);
            selectedChannel = null;
            MonitorTableView.getSelectionModel().clearSelection();
            updateStats();
            clearForm();
            showSuccess("✓ Channel updated successfully.");
        }
    }

    @FXML
    public void handleDelete() {
        MonitorChannel selected = MonitorTableView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showError("Please select a channel to delete.");
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirm Deletion");
        confirm.setHeaderText("Delete Monitor Channel");
        confirm.setContentText("Delete '" + selected.getChannelName() + "' (" + selected.getPerformerName() + ")?");

        Optional<ButtonType> result = confirm.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            masterData.remove(selected);
            if (selectedChannel == selected) {
                clearForm();
                selectedChannel = null;
            }
            updateStats();
            showSuccess("Deleted: " + selected.getChannelName());
        }
    }

    @FXML
    public void handleClear() {
        clearForm();
        selectedChannel = null;
        MonitorTableView.getSelectionModel().clearSelection();
        clearFeedback();
    }

    @FXML
    public void handleSearch() {
        String query = searchField.getText().toLowerCase().trim();
        if (query.isEmpty()) {
            filteredData.setPredicate(p -> true);
            return;
        }
        filteredData.setPredicate(ch ->
                ch.getChannelName().toLowerCase().contains(query) ||
                        ch.getPerformerName().toLowerCase().contains(query) ||
                        ch.getRole().toLowerCase().contains(query)
        );
    }

    @FXML
    public void handleResetSearch() {
        searchField.clear();
        filteredData.setPredicate(p -> true);
    }

    @FXML
    public void SaveLogButtonOnAction(ActionEvent event) {
        if (masterData.isEmpty()) {
            showError("No data to save.");
            return;
        }

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

            for (MonitorChannel ch : masterData) {
                oos.writeObject(ch);
            }
            oos.close();
            showSuccess("✓ Saved " + masterData.size() + " channels to log file.");
        } catch (Exception e) {
            showError("Failed to save: " + e.getMessage());
            e.printStackTrace();
        }
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

    private void populateForm(MonitorChannel ch) {
        MonitorChannelTextField.setText(ch.getChannelName());
        PerformerTextField.setText(ch.getPerformerName());
        RoleComboBox.setValue(ch.getRole());
        InputSourceComboBox.setValue(ch.getInputSource());
        CurrentLevelSlider.setValue(ch.getCurrentLevel());
        TargetLevelSlider.setValue(ch.getTargetLevel());
        PanSlider.setValue(ch.getPan());
        MuteCheckBox.setSelected(ch.isMuted());
        SoloCheckBox.setSelected(ch.isSolo());
        notesTextArea.setText(ch.getNotes());
    }

    private void clearForm() {
        MonitorChannelTextField.clear();
        PerformerTextField.clear();
        RoleComboBox.setValue(null);
        InputSourceComboBox.setValue(null);
        CurrentLevelSlider.setValue(-20.0);
        TargetLevelSlider.setValue(-20.0);
        PanSlider.setValue(0.0);
        MuteCheckBox.setSelected(false);
        SoloCheckBox.setSelected(false);
        notesTextArea.clear();
    }

    private void updateStats() {
        recordCountLabel.setText(String.valueOf(masterData.size()));
        long active = masterData.stream().filter(c -> !c.isMuted()).count();
        long muted = masterData.stream().filter(MonitorChannel::isMuted).count();
        long solo = masterData.stream().filter(MonitorChannel::isSolo).count();
        activeCountLabel.setText(String.valueOf(active));
        mutedCountLabel.setText(String.valueOf(muted));
        soloCountLabel.setText(String.valueOf(solo));
    }

    private void showError(String msg) {
        feedbackLabel.setText(msg);
        feedbackLabel.setTextFill(Color.web("#7f1d1d"));
        feedbackLabel.setStyle("-fx-background-color: #fecaca; -fx-padding: 12; -fx-background-radius: 6; -fx-font-weight: bold;");
        feedbackLabel.setVisible(true);
        feedbackLabel.setManaged(true);
    }

    private void showSuccess(String msg) {
        feedbackLabel.setText(msg);
        feedbackLabel.setTextFill(Color.web("#14532d"));
        feedbackLabel.setStyle("-fx-background-color: #bbf7d0; -fx-padding: 12; -fx-background-radius: 6; -fx-font-weight: bold;");
        feedbackLabel.setVisible(true);
        feedbackLabel.setManaged(true);
    }

    private void clearFeedback() {
        feedbackLabel.setVisible(false);
        feedbackLabel.setManaged(false);
    }

    private boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
}