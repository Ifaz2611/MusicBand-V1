module com.example.music_band_oop {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.music_band_oop to javafx.fxml;
    exports com.example.music_band_oop;
}