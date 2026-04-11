package com.example.music_band_oop.Controller.BassistUser_KeyboardistUser_Controller;

public class KeyboardIssue {
    private String id;
    private String date;
    private String type;
    private String name;
    private String priority;
    private String status;
    private String description;

    public KeyboardIssue(String id, String date, String type, String name, String priority, String status, String description) {
        this.id = id;
        this.date = date;
        this.type = type;
        this.name = name;
        this.priority = priority;
        this.status = status;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
