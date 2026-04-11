package com.example.music_band_oop.nasim;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class LoginController
{
    @javafx.fxml.FXML
    private TextField txtUserId;
    @javafx.fxml.FXML
    private ComboBox cmbRole;
    @javafx.fxml.FXML
    private TextField txtPassword;

    @javafx.fxml.FXML
    public void initialize() {
        cmbRole.getItems().addAll(
                "Lead Guitarist",
                "Drummer"
        );
    }

    @javafx.fxml.FXML
    public void onActionLogin(ActionEvent actionEvent) {
    }
}