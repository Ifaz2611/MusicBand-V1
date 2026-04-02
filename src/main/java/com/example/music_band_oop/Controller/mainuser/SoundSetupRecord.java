package com.example.music_band_oop.Controller.mainuser;

public class SoundSetupRecord {
    private String eventName;
    private String venue;
    private int micLevel;
    private int speakerBalance;
    private String effect;

    public SoundSetupRecord(String eventName, String venue, int micLevel, int speakerBalance, String effect) {
        this.eventName = eventName;
        this.venue = venue;
        this.micLevel = micLevel;
        this.speakerBalance = speakerBalance;
        this.effect = effect;
    }

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

    public int getMicLevel() {
        return micLevel;
    }

    public void setMicLevel(int micLevel) {
        this.micLevel = micLevel;
    }

    public int getSpeakerBalance() {
        return speakerBalance;
    }

    public void setSpeakerBalance(int speakerBalance) {
        this.speakerBalance = speakerBalance;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    @Override
    public String toString() {
        return "SoundSetupRecord{" +
                "eventName='" + eventName + '\'' +
                ", venue='" + venue + '\'' +
                ", micLevel=" + micLevel +
                ", speakerBalance=" + speakerBalance +
                ", effect='" + effect + '\'' +
                '}';
    }
}
