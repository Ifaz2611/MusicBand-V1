package com.example.music_band_oop.Controller.BassistUser_KeyboardistUser_Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class KeyGoal3ArrangementsController
{
    @javafx.fxml.FXML
    private TableColumn<KeyboardArrangement, String> colPatch;
    @javafx.fxml.FXML
    private TableColumn<KeyboardArrangement, String> colKey;
    @javafx.fxml.FXML
    private TextArea sheetMusicTextArea;
    @javafx.fxml.FXML
    private TextArea patchNotesTextArea;
    @javafx.fxml.FXML
    private TableView<KeyboardArrangement> arrangementsTableView;
    @javafx.fxml.FXML
    private TableColumn<KeyboardArrangement, String> colReviewed;
    @javafx.fxml.FXML
    private TableColumn<KeyboardArrangement, String> colSong;
    @javafx.fxml.FXML
    private TableColumn<KeyboardArrangement, String> colTempo;
    @javafx.fxml.FXML
    private TextArea midiTextArea;
    @javafx.fxml.FXML
    private TableColumn<KeyboardArrangement, String> colEvent;
    @javafx.fxml.FXML
    private Label messageLabel;

    private ArrayList<KeyboardArrangement> arrangementList = new ArrayList<>();

    private void loadData() {
        arrangementList.add(new KeyboardArrangement("Summer Fest 2025", "Final Countdown", 120, "E minor", "Lead Synth", false, "Use bright lead", "midi1.mid", "sheet1.pdf"));
        arrangementList.add(new KeyboardArrangement("Jazz Night", "Autumn Leaves", 90, "G minor", "Soft Pad", true, "Smooth transition", "midi2.mid", "sheet2.pdf"));
    }

    @javafx.fxml.FXML
    public void initialize() {
        colEvent.setCellValueFactory(new PropertyValueFactory<>("event"));
        colSong.setCellValueFactory(new PropertyValueFactory<>("song"));
        colTempo.setCellValueFactory(new PropertyValueFactory<>("tempo"));
        colKey.setCellValueFactory(new PropertyValueFactory<>("key"));
        colPatch.setCellValueFactory(new PropertyValueFactory<>("synthPatch"));
        colReviewed.setCellValueFactory(new PropertyValueFactory<>("reviewed"));

        loadData();
        arrangementsTableView.getItems().addAll(arrangementList);

        // show details on click
        arrangementsTableView.setOnMouseClicked(e -> {
            KeyboardArrangement k = arrangementsTableView.getSelectionModel().getSelectedItem();
            if (k != null) {
                patchNotesTextArea.setText(k.getPatchNotes());
                midiTextArea.setText(k.getMidiReference());
                sheetMusicTextArea.setText(k.getSheetMusic());
            }
        });
    }

    @javafx.fxml.FXML
    public void markReviewedBtn(ActionEvent actionEvent) {
        KeyboardArrangement k = arrangementsTableView.getSelectionModel().getSelectedItem();

        if (k == null) {
            messageLabel.setText("Select an arrangement first.");
            return;
        }

        if (k.isReviewed()) {
            messageLabel.setText("Already reviewed.");
            return;
        }

        k.setReviewed(true);

        arrangementsTableView.refresh();

        messageLabel.setText("Marked as reviewed.");

    }
}