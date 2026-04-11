package com.example.music_band_oop.Controller.BassistUser_KeyboardistUser_Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class SetlistController
{
    @javafx.fxml.FXML
    private TableColumn<SetlistItem, String> eventNameColumn;
    @javafx.fxml.FXML
    private TableColumn<SetlistItem, String> notesColumn;
    @javafx.fxml.FXML
    private TableColumn<SetlistItem, String> keyColumn;
    @javafx.fxml.FXML
    private Label titleLabel;
    @javafx.fxml.FXML
    private TextField filterTextField;
    @javafx.fxml.FXML
    private TableView<SetlistItem> setlistTableView;
    @javafx.fxml.FXML
    private TableColumn<SetlistItem, String> tempoColumn;
    @javafx.fxml.FXML
    private Label filterLabel;
    @javafx.fxml.FXML
    private TableColumn<SetlistItem, String> songTitleColumn;
    @javafx.fxml.FXML
    private Label countLabel;
    @javafx.fxml.FXML
    private Label messageLabel;

    private ArrayList<SetlistItem> setlistItem = new ArrayList<>();

    @javafx.fxml.FXML
    public void initialize() {
        eventNameColumn.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        songTitleColumn.setCellValueFactory(new PropertyValueFactory<>("songTitle"));
        tempoColumn.setCellValueFactory(new PropertyValueFactory<>("tempo"));
        keyColumn.setCellValueFactory(new PropertyValueFactory<>("key"));
        notesColumn.setCellValueFactory(new PropertyValueFactory<>("notesForBassist"));

        loadSampleData();

        setlistTableView.getItems().addAll(setlistItem);
    }

    private void loadSampleData() {
        setlistItem.add(new SetlistItem("Summer Fest", "Another One Bites the Dust", 92, "E minor", "Simple octave pattern"));
        setlistItem.add(new SetlistItem("Summer Fest", "Billie Jean", 117, "F# minor", "Syncopated groove"));
        setlistItem.add(new SetlistItem("Jazz Night", "Take Five", 170, "E-flat minor", "Walking bass line"));
        setlistItem.add(new SetlistItem("Rock Arena", "Seven Nation Army", 124, "E minor", "Fuzz effect, simple riff"));

    }

    @javafx.fxml.FXML
    public void countSongsButton(ActionEvent actionEvent) {
        int count = setlistTableView.getItems().size();
        countLabel.setText("Total Songs: " + count);
    }

    @javafx.fxml.FXML
    public void filterButton(ActionEvent actionEvent) {
        String keyword = filterTextField.getText().toLowerCase();

        if (keyword.isEmpty()) {
            messageLabel.setText("Enter something to filter.");
            return;
        }

        setlistTableView.getItems().clear();

        for (SetlistItem item : setlistItem) {
            if (item.getEventName().toLowerCase().contains(keyword) ||
                    item.getSongTitle().toLowerCase().contains(keyword)) {

                setlistTableView.getItems().add(item);
            }
        }

        messageLabel.setText("Filtered result shown.");
    }

    @javafx.fxml.FXML
    public void resetFilterButton(ActionEvent actionEvent) {
        setlistTableView.getItems().clear();
        setlistTableView.getItems().addAll(setlistItem);

        filterTextField.clear();
        messageLabel.setText("Filter reset.");
    }
}