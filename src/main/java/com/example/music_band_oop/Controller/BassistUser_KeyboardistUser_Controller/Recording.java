package com.example.music_band_oop.Controller.BassistUser_KeyboardistUser_Controller;

public class Recording {
    private String title;
    private String eventName;
    private String date;
    private String notes;

    public Recording(String title, String eventName, String date, String notes) {
        this.title = title;
        this.eventName = eventName;
        this.date = date;
        this.notes = notes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
