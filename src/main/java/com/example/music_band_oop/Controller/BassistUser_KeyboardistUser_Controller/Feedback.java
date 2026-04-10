package com.example.music_band_oop.Controller.BassistUser_KeyboardistUser_Controller;

public class Feedback {
    private String eventName;
    private String date;
    private String comments;
    private String status;
    private boolean read;

    public Feedback(String eventName, String date, String comments, String status, boolean read) {
        this.eventName = eventName;
        this.date = date;
        this.comments = comments;
        this.status = status;
        this.read = read;
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

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }
}
