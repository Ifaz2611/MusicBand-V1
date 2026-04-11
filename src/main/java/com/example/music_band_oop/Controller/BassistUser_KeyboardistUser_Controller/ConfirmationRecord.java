package com.example.music_band_oop.Controller.BassistUser_KeyboardistUser_Controller;

public class ConfirmationRecord {
    private String eventName;
    private String date;
    private String confirmation;
    private String notes;

    public ConfirmationRecord(String eventName, String date, String confirmation, String notes) {
        this.eventName = eventName;
        this.date = date;
        this.confirmation = confirmation;
        this.notes = notes;
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

    public String getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(String confirmation) {
        this.confirmation = confirmation;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
