package com.example.music_band_oop.Controller.mainuser;

public class event {
    private String eventName;
    private String venue;
    private String bandMembers;

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getBandMembers() {
        return bandMembers;
    }

    public void setBandMembers(String bandMembers) {
        this.bandMembers = bandMembers;
    }

    @Override
    public String toString() {
        return "event{" +
                "eventName='" + eventName + '\'' +
                ", venue='" + venue + '\'' +
                ", bandMembers='" + bandMembers + '\'' +
                '}';
    }

    public event(String eventName, String venue, String bandMembers) {
        this.eventName = eventName;
        this.venue = venue;
        this.bandMembers = bandMembers;


    }
}


