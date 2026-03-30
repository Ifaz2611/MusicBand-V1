module com.example.music_band_oop {
    requires javafx.controls;
    requires javafx.fxml;

    // Main package
    opens com.example.music_band_oop to javafx.fxml;
    exports com.example.music_band_oop;


    opens com.example.music_band_oop.Controller to javafx.fxml;
    exports com.example.music_band_oop.Controller;

    opens com.example.music_band_oop.Controller.FXMLControllerForUser1 to javafx.fxml;
    exports com.example.music_band_oop.Controller.FXMLControllerForUser1;

    opens com.example.music_band_oop.Controller.FXMLControllerForUser2 to javafx.fxml;
    exports com.example.music_band_oop.Controller.FXMLControllerForUser2;
}