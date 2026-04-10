module com.example.music_band_oop {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens com.example.music_band_oop to javafx.fxml;
    exports com.example.music_band_oop;

    opens com.example.music_band_oop.Controller to javafx.fxml;
    exports com.example.music_band_oop.Controller;

    opens com.example.music_band_oop.Controller.FXMLControllerForUser1 to javafx.fxml;
    exports com.example.music_band_oop.Controller.FXMLControllerForUser1;

    opens com.example.music_band_oop.Controller.FXMLControllerForUser2 to javafx.fxml;
    exports com.example.music_band_oop.Controller.FXMLControllerForUser2;

    opens com.example.music_band_oop.Controller.mainuser to javafx.base, javafx.fxml;
    exports com.example.music_band_oop.Controller.mainuser;

    opens com.example.music_band_oop.Controller.BassistUser_KeyboardistUser_Controller to javafx.fxml, javafx.base;

}