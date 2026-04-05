package com.example.music_band_oop.Controller.mainuser;

import java.time.LocalDate;

public class NewEvent {
    private String eventName;
    private LocalDate date;
    private String venue;
    private String bandName;
    private int audienceSize;


    public NewEvent(String eventName, LocalDate date, String venue, String bandName, int audienceSize) {
        this.eventName = eventName;
        this.date = date;
        this.venue = venue;
        this.bandName = bandName;
        this.audienceSize = audienceSize;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    public int getAudienceSize() {
        return audienceSize;
    }

    public void setAudienceSize(int audienceSize) {
        this.audienceSize = audienceSize;
    }

    @Override
    public String toString() {
        return "NewEvent{" +
                "eventName='" + eventName + '\'' +
                ", date=" + date +
                ", venue='" + venue + '\'' +
                ", bandName='" + bandName + '\'' +
                ", audienceSize=" + audienceSize +
                '}';
    }
}
