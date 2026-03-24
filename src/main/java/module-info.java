module com.example.music_band_oop {
    requires javafx.controls;
    requires javafx.fxml;

    // Export the main package
    opens com.example.music_band_oop to javafx.fxml;
    exports com.example.music_band_oop;

    opens com.example.music_band_oop.Controller to javafx.fxml;
    exports com.example.music_band_oop.Controller;
}