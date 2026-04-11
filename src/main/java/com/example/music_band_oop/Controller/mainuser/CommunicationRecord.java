package com.example.music_band_oop.Controller.mainuser;

public class CommunicationRecord {

    private String event;
    private String message;
    private String recipients;

    public CommunicationRecord(String event, String message, String recipients) {
        this.event = event;
        this.message = message;
        this.recipients = recipients;
    }

    public String getEvent() {
        return event;
    }

    public String getMessage() {
        return message;
    }

    public String getRecipients() {
        return recipients;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setRecipients(String recipients) {
        this.recipients = recipients;
    }

    public void setTimestamp(String timestamp) {
    }

    @Override
    public String toString() {
        return "CommunicationRecord{" +
                "event='" + event + '\'' +
                ", message='" + message + '\'' +
                ", recipients='" + recipients + '\'' + +
                '}';
    }
}
