package com.example.music_band_oop.Controller.BassistUser_KeyboardistUser_Controller;

public class KeyboardRehearsal {
    private String date;
    private String time;
    private String duration;
    private String venue;
    private String status;
    private String keyboardNotes;
    private String bandManagerNotes;
    private String requiredEquipment;

    public KeyboardRehearsal(String date, String time, String duration, String venue, String status, String keyboardNotes, String bandManagerNotes, String requiredEquipment) {
        this.date = date;
        this.time = time;
        this.duration = duration;
        this.venue = venue;
        this.status = status;
        this.keyboardNotes = keyboardNotes;
        this.bandManagerNotes = bandManagerNotes;
        this.requiredEquipment = requiredEquipment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getKeyboardNotes() {
        return keyboardNotes;
    }

    public void setKeyboardNotes(String keyboardNotes) {
        this.keyboardNotes = keyboardNotes;
    }

    public String getBandManagerNotes() {
        return bandManagerNotes;
    }

    public void setBandManagerNotes(String bandManagerNotes) {
        this.bandManagerNotes = bandManagerNotes;
    }

    public String getRequiredEquipment() {
        return requiredEquipment;
    }

    public void setRequiredEquipment(String requiredEquipment) {
        this.requiredEquipment = requiredEquipment;
    }
}
