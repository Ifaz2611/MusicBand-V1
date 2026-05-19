package com.example.music_band_oop.Controller.mainuser;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.io.Serializable;

public class ShowRecording implements Serializable {
    private final StringProperty timestamp;
    private final StringProperty showTitle;
    private final StringProperty engineerName;
    private final StringProperty recordingDate;
    private final StringProperty duration;
    private final StringProperty quality;
    private final StringProperty fileLocation;
    private final StringProperty feedback;

    public ShowRecording(String timestamp, String showTitle, String engineerName, String recordingDate,
                         String duration, String quality, String fileLocation, String feedback) {
        this.timestamp = new SimpleStringProperty(timestamp);
        this.showTitle = new SimpleStringProperty(showTitle);
        this.engineerName = new SimpleStringProperty(engineerName);
        this.recordingDate = new SimpleStringProperty(recordingDate);
        this.duration = new SimpleStringProperty(duration);
        this.quality = new SimpleStringProperty(quality);
        this.fileLocation = new SimpleStringProperty(fileLocation);
        this.feedback = new SimpleStringProperty(feedback);
    }

    public String getTimestamp() { return timestamp.get(); }
    public void setTimestamp(String value) { timestamp.set(value); }
    public StringProperty timestampProperty() { return timestamp; }

    public String getShowTitle() { return showTitle.get(); }
    public void setShowTitle(String value) { showTitle.set(value); }
    public StringProperty showTitleProperty() { return showTitle; }

    public String getEngineerName() { return engineerName.get(); }
    public void setEngineerName(String value) { engineerName.set(value); }
    public StringProperty engineerNameProperty() { return engineerName; }

    public String getRecordingDate() { return recordingDate.get(); }
    public void setRecordingDate(String value) { recordingDate.set(value); }
    public StringProperty recordingDateProperty() { return recordingDate; }

    public String getDuration() { return duration.get(); }
    public void setDuration(String value) { duration.set(value); }
    public StringProperty durationProperty() { return duration; }

    public String getQuality() { return quality.get(); }
    public void setQuality(String value) { quality.set(value); }
    public StringProperty qualityProperty() { return quality; }

    public String getFileLocation() { return fileLocation.get(); }
    public void setFileLocation(String value) { fileLocation.set(value); }
    public StringProperty fileLocationProperty() { return fileLocation; }

    public String getFeedback() { return feedback.get(); }
    public void setFeedback(String value) { feedback.set(value); }
    public StringProperty feedbackProperty() { return feedback; }
}