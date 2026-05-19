package com.example.music_band_oop.Controller.FXMLControllerForUser1;

import com.example.music_band_oop.Controller.mainuser.ShowRecording;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class SE_Goal8_ViewController {

    @FXML private TextField TitleTextField;
    @FXML private TextField EngineerTextField;
    @FXML private DatePicker RecordingDatePicker;
    @FXML private TextField DurationTextField;
    @FXML private ComboBox<String> QualityComboBox;
    @FXML private TextField FileLocationTextField;
    @FXML private TextArea FeedbackandNotesTextField;

    @FXML private TableView<ShowRecording> RecordingTableView;

    @FXML private TableColumn<ShowRecording, String> TimeCol;
    @FXML private TableColumn<ShowRecording, String> TitleCol;
    @FXML private TableColumn<ShowRecording, String> EngineerCol;
    @FXML private TableColumn<ShowRecording, String> DateCol;
    @FXML private TableColumn<ShowRecording, String> DurationCol;
    @FXML private TableColumn<ShowRecording, String> QualityCol;
    @FXML private TableColumn<ShowRecording, String> LocationCol;
    @FXML private TableColumn<ShowRecording, String> FeedbackNotesCol;

    @FXML private TextField searchField;

    @FXML private Label StatusLabel;
    @FXML private Label recordCountLabel;
    @FXML private Label excellentCountLabel;
    @FXML private Label poorCountLabel;

    private final ObservableList<ShowRecording> masterData =
            FXCollections.observableArrayList();

    private FilteredList<ShowRecording> filteredData;

    private ShowRecording selectedRecording = null;

    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static final DateTimeFormatter TIMESTAMP_FORMATTER =
            DateTimeFormatter.ofPattern("HH:mm:ss");

    @FXML
    public void initialize() {

        initializeComboBox();
        initializeTable();
        initializeSearch();
        updateStats();

        clearStatus();
    }

    private void initializeComboBox() {

        QualityComboBox.setItems(FXCollections.observableArrayList(
                "Excellent",
                "Good",
                "Fair",
                "Poor",
                "Unusable"
        ));
    }

    private void initializeTable() {

        TimeCol.setCellValueFactory(
                new PropertyValueFactory<>("timestamp"));

        TitleCol.setCellValueFactory(
                new PropertyValueFactory<>("showTitle"));

        EngineerCol.setCellValueFactory(
                new PropertyValueFactory<>("engineerName"));

        DateCol.setCellValueFactory(
                new PropertyValueFactory<>("recordingDate"));

        DurationCol.setCellValueFactory(
                new PropertyValueFactory<>("duration"));

        QualityCol.setCellValueFactory(
                new PropertyValueFactory<>("quality"));

        LocationCol.setCellValueFactory(
                new PropertyValueFactory<>("fileLocation"));

        FeedbackNotesCol.setCellValueFactory(
                new PropertyValueFactory<>("feedback"));

        // Color Styling for Quality Column
        QualityCol.setCellFactory(col -> new TableCell<>() {

            @Override
            protected void updateItem(String quality, boolean empty) {

                super.updateItem(quality, empty);

                if (empty || quality == null) {

                    setText(null);
                    setStyle("");

                } else {

                    setText(quality);

                    switch (quality) {

                        case "Excellent":
                            setStyle("-fx-text-fill: #2e7d32; -fx-font-weight: bold;");
                            break;

                        case "Good":
                            setStyle("-fx-text-fill: #1565c0; -fx-font-weight: bold;");
                            break;

                        case "Fair":
                            setStyle("-fx-text-fill: #f9a825; -fx-font-weight: bold;");
                            break;

                        case "Poor":
                            setStyle("-fx-text-fill: #ef6c00; -fx-font-weight: bold;");
                            break;

                        case "Unusable":
                            setStyle("-fx-text-fill: #c62828; -fx-font-weight: bold;");
                            break;

                        default:
                            setStyle("-fx-text-fill: #555555;");
                    }
                }
            }
        });

        filteredData = new FilteredList<>(masterData, p -> true);

        RecordingTableView.setItems(filteredData);

        RecordingTableView.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, oldVal, newVal) -> {

                    if (newVal != null) {

                        selectedRecording = newVal;

                        populateForm(newVal);

                        StatusLabel.setTextFill(Color.web("#1565c0"));

                        StatusLabel.setStyle(
                                "-fx-background-color: #dbeafe;" +
                                        "-fx-padding: 12;" +
                                        "-fx-background-radius: 6;" +
                                        "-fx-font-weight: bold;"
                        );

                        StatusLabel.setText(
                                "Selected: "
                                        + newVal.getShowTitle()
                                        + " ("
                                        + newVal.getQuality()
                                        + ")"
                        );

                        StatusLabel.setVisible(true);
                        StatusLabel.setManaged(true);
                    }
                });
    }

    private void initializeSearch() {

        searchField.textProperty().addListener((obs, oldVal, newVal) -> {

            String query = newVal.toLowerCase().trim();

            if (query.isEmpty()) {

                filteredData.setPredicate(p -> true);

            } else {

                filteredData.setPredicate(r ->

                        r.getShowTitle().toLowerCase().contains(query)

                                || r.getEngineerName()
                                .toLowerCase()
                                .contains(query)

                                || r.getQuality()
                                .toLowerCase()
                                .contains(query)

                                || r.getRecordingDate()
                                .toLowerCase()
                                .contains(query)
                );
            }
        });
    }

    @FXML
    public void SaveToTableButtonOnAction(ActionEvent event) {

        clearStatus();

        String title = TitleTextField.getText();
        String engineer = EngineerTextField.getText();
        LocalDate date = RecordingDatePicker.getValue();
        String duration = DurationTextField.getText();
        String quality = QualityComboBox.getValue();
        String location = FileLocationTextField.getText();
        String feedback = FeedbackandNotesTextField.getText();

        StringBuilder errors = new StringBuilder();

        if (isNullOrEmpty(title))
            errors.append("• Show Title is required.\n");

        if (isNullOrEmpty(engineer))
            errors.append("• Engineer Name is required.\n");

        if (date == null)
            errors.append("• Recording Date is required.\n");

        if (quality == null)
            errors.append("• Quality Rating is required.\n");

        if (isNullOrEmpty(feedback))
            errors.append("• Feedback / Notes are required.\n");

        if (errors.length() > 0) {

            showError("Please fix the following:\n" + errors);
            return;
        }

        boolean exists = masterData.stream().anyMatch(r ->

                r.getShowTitle().equalsIgnoreCase(title.trim())

                        && r.getRecordingDate()
                        .equals(date.format(DATE_FORMATTER))
        );

        if (exists) {

            showError("A recording for this show on this date already exists.");
            return;
        }

        String timestamp =
                LocalDateTime.now().format(TIMESTAMP_FORMATTER);

        ShowRecording record = new ShowRecording(

                timestamp,
                title.trim(),
                engineer.trim(),
                date.format(DATE_FORMATTER),

                isNullOrEmpty(duration)
                        ? "N/A"
                        : duration.trim(),

                quality,

                isNullOrEmpty(location)
                        ? "Not specified"
                        : location.trim(),

                feedback.trim()
        );

        masterData.add(record);

        updateStats();

        clearForm();

        showSuccess("✓ Recording logged: " + title);
    }

    @FXML
    public void handleUpdate() {

        if (selectedRecording == null) {

            showError("Please select a recording from the table to update.");
            return;
        }

        String title = TitleTextField.getText();
        String engineer = EngineerTextField.getText();
        LocalDate date = RecordingDatePicker.getValue();
        String quality = QualityComboBox.getValue();
        String feedback = FeedbackandNotesTextField.getText();

        if (isNullOrEmpty(title)
                || isNullOrEmpty(engineer)
                || date == null
                || quality == null
                || isNullOrEmpty(feedback)) {

            showError("All required fields must be filled.");
            return;
        }

        int index = masterData.indexOf(selectedRecording);

        if (index >= 0) {

            ShowRecording updated = new ShowRecording(

                    selectedRecording.getTimestamp(),

                    title.trim(),

                    engineer.trim(),

                    date.format(DATE_FORMATTER),

                    isNullOrEmpty(DurationTextField.getText())
                            ? "N/A"
                            : DurationTextField.getText().trim(),

                    quality,

                    isNullOrEmpty(FileLocationTextField.getText())
                            ? "Not specified"
                            : FileLocationTextField.getText().trim(),

                    feedback.trim()
            );

            masterData.set(index, updated);

            selectedRecording = null;

            RecordingTableView.getSelectionModel().clearSelection();

            updateStats();

            clearForm();

            showSuccess("✓ Recording updated successfully.");
        }
    }

    @FXML
    public void handleDelete() {

        ShowRecording selected =
                RecordingTableView.getSelectionModel().getSelectedItem();

        if (selected == null) {

            showError("Please select a recording to delete.");
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);

        confirm.setTitle("Confirm Deletion");

        confirm.setHeaderText("Delete Recording Log");

        confirm.setContentText(
                "Delete '"
                        + selected.getShowTitle()
                        + "' ?"
        );

        Optional<ButtonType> result = confirm.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {

            masterData.remove(selected);

            clearForm();

            selectedRecording = null;

            updateStats();

            showSuccess("Deleted Successfully.");
        }
    }

    @FXML
    public void handleClear() {

        clearForm();

        selectedRecording = null;

        RecordingTableView.getSelectionModel().clearSelection();

        clearStatus();
    }

    @FXML
    public void handleBrowseFile() {

        FileChooser chooser = new FileChooser();

        chooser.setTitle("Select Recording File");

        chooser.getExtensionFilters().addAll(

                new FileChooser.ExtensionFilter(
                        "Audio Files",
                        "*.wav",
                        "*.mp3",
                        "*.flac",
                        "*.aiff"
                ),

                new FileChooser.ExtensionFilter(
                        "All Files",
                        "*.*"
                )
        );

        File file = chooser.showOpenDialog(
                TitleTextField.getScene().getWindow()
        );

        if (file != null) {

            FileLocationTextField.setText(
                    file.getAbsolutePath()
            );
        }
    }

    @FXML
    public void DashboardButtonOnAction(ActionEvent actionEvent) {

        try {

            FXMLLoader fxmlLoader = new FXMLLoader(
                    getClass().getResource(
                            "/com/example/music_band_oop/DashboardOfUsers/SoundEngineerDashbroad.fxml"
                    )
            );

            Scene dashboardScene =
                    new Scene(fxmlLoader.load());

            Stage currentStage =
                    (Stage) ((Node) actionEvent.getSource())
                            .getScene()
                            .getWindow();

            currentStage.setScene(dashboardScene);

            currentStage.setTitle("Sound Engineer Dashboard");

            currentStage.show();

        } catch (Exception e) {

            showError("Failed to navigate: " + e.getMessage());
        }
    }

    private void populateForm(ShowRecording r) {

        TitleTextField.setText(r.getShowTitle());

        EngineerTextField.setText(r.getEngineerName());

        RecordingDatePicker.setValue(
                LocalDate.parse(
                        r.getRecordingDate(),
                        DATE_FORMATTER
                )
        );

        DurationTextField.setText(r.getDuration());

        QualityComboBox.setValue(r.getQuality());

        FileLocationTextField.setText(r.getFileLocation());

        FeedbackandNotesTextField.setText(r.getFeedback());
    }

    private void clearForm() {

        TitleTextField.clear();

        EngineerTextField.clear();

        RecordingDatePicker.setValue(null);

        DurationTextField.clear();

        QualityComboBox.setValue(null);

        FileLocationTextField.clear();

        FeedbackandNotesTextField.clear();
    }

    private void updateStats() {

        recordCountLabel.setText(
                String.valueOf(masterData.size())
        );

        long excellent =
                masterData.stream()
                        .filter(r ->
                                "Excellent".equals(r.getQuality()))
                        .count();

        long poor =
                masterData.stream()
                        .filter(r ->
                                "Poor".equals(r.getQuality())
                                        || "Unusable".equals(r.getQuality()))
                        .count();

        excellentCountLabel.setText(
                String.valueOf(excellent)
        );

        poorCountLabel.setText(
                String.valueOf(poor)
        );
    }

    private void showError(String msg) {

        StatusLabel.setText(msg);

        StatusLabel.setTextFill(Color.web("#7f1d1d"));

        StatusLabel.setStyle(
                "-fx-background-color: #fecaca;" +
                        "-fx-padding: 12;" +
                        "-fx-background-radius: 6;" +
                        "-fx-font-weight: bold;"
        );

        StatusLabel.setVisible(true);

        StatusLabel.setManaged(true);
    }

    private void showSuccess(String msg) {

        StatusLabel.setText(msg);

        StatusLabel.setTextFill(Color.web("#14532d"));

        StatusLabel.setStyle(
                "-fx-background-color: #bbf7d0;" +
                        "-fx-padding: 12;" +
                        "-fx-background-radius: 6;" +
                        "-fx-font-weight: bold;"
        );

        StatusLabel.setVisible(true);

        StatusLabel.setManaged(true);
    }

    private void clearStatus() {

        StatusLabel.setVisible(false);

        StatusLabel.setManaged(false);
    }

    private boolean isNullOrEmpty(String str) {

        return str == null || str.trim().isEmpty();
    }

    @FXML
    public void handleSearch(ActionEvent actionEvent) {
    }

    @FXML
    public void handleResetSearch(ActionEvent actionEvent) {
    }
}

