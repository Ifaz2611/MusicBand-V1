package com.example.music_band_oop.Controller.BassistUser_KeyboardistUser_Controller;

public class Rehearsal {
    private String date;
    private String time;
    private String venue;
    private String status;

    public Rehearsal(String date, String time, String venue, String status) {
        this.date = date;
        this.time = time;
        this.venue = venue;
        this.status = status;
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

    @Override
    public String toString() {
        return "Rehearsal{" +
                "date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", venue='" + venue + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
