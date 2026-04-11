package com.example.music_band_oop.Controller.BassistUser_KeyboardistUser_Controller;

public class Issues {
    private String issueId;
    private String eventName;
    private String equipmentName;
    private String status;
    private String date;
    private String description;

    public Issues(String issueId, String eventName, String equipmentName, String status, String date, String description) {
        this.issueId = issueId;
        this.eventName = eventName;
        this.equipmentName = equipmentName;
        this.status = status;
        this.date = date;
        this.description = description;
    }

    public String getIssueId() {
        return issueId;
    }

    public void setIssueId(String issueId) {
        this.issueId = issueId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
