package com.example.music_band_oop.Controller.BassistUser_KeyboardistUser_Controller;

public class KeyboardFeedback {
    private String eventName;
    private String date;
    private String status;
    private String timestamp;
    private String timingComments;
    private String soundComments;
    private String stageComments;

    public KeyboardFeedback(String eventName, String date, String status, String timestamp, String timingComments, String soundComments, String stageComments) {
        this.eventName = eventName;
        this.date = date;
        this.status = status;
        this.timestamp = timestamp;
        this.timingComments = timingComments;
        this.soundComments = soundComments;
        this.stageComments = stageComments;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTimingComments() {
        return timingComments;
    }

    public void setTimingComments(String timingComments) {
        this.timingComments = timingComments;
    }

    public String getSoundComments() {
        return soundComments;
    }

    public void setSoundComments(String soundComments) {
        this.soundComments = soundComments;
    }

    public String getStageComments() {
        return stageComments;
    }

    public void setStageComments(String stageComments) {
        this.stageComments = stageComments;
    }
}
