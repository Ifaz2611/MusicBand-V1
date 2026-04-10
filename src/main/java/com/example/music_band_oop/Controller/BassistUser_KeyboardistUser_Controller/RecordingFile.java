package com.example.music_band_oop.Controller.BassistUser_KeyboardistUser_Controller;

public class RecordingFile {
    private String title;
    private String description;
    private String type;
    private String event;
    private String fileName;

    public RecordingFile(String title, String description, String type, String event, String fileName) {
        this.title = title;
        this.description = description;
        this.type = type;
        this.event = event;
        this.fileName = fileName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
