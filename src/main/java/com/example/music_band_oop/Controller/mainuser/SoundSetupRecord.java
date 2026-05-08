package com.example.music_band_oop.Controller.mainuser;

import javafx.beans.property.*;

import java.io.Serializable;

public class SoundSetupRecord {
    private final StringProperty eventName;
    private final StringProperty venue;
    private final StringProperty setupDate;
    private final StringProperty bandMembers;
    private final IntegerProperty micLevel;
    private final IntegerProperty speakerBalance;
    private final IntegerProperty monitorLevel;
    private final StringProperty effect;
    private final StringProperty status;

    public SoundSetupRecord(String eventName, String venue, String setupDate, String bandMembers,
                            int micLevel, int speakerBalance, int monitorLevel,
                            String effect, String status) {
        this.eventName = new SimpleStringProperty(eventName);
        this.venue = new SimpleStringProperty(venue);
        this.setupDate = new SimpleStringProperty(setupDate);
        this.bandMembers = new SimpleStringProperty(bandMembers);
        this.micLevel = new SimpleIntegerProperty(micLevel);
        this.speakerBalance = new SimpleIntegerProperty(speakerBalance);
        this.monitorLevel = new SimpleIntegerProperty(monitorLevel);
        this.effect = new SimpleStringProperty(effect);
        this.status = new SimpleStringProperty(status);
    }

    public String getEventName() { return eventName.get(); }
    public void setEventName(String value) { eventName.set(value); }
    public StringProperty eventNameProperty() { return eventName; }

    public String getVenue() { return venue.get(); }
    public void setVenue(String value) { venue.set(value); }
    public StringProperty venueProperty() { return venue; }

    public String getSetupDate() { return setupDate.get(); }
    public void setSetupDate(String value) { setupDate.set(value); }
    public StringProperty setupDateProperty() { return setupDate; }

    public String getBandMembers() { return bandMembers.get(); }
    public void setBandMembers(String value) { bandMembers.set(value); }
    public StringProperty bandMembersProperty() { return bandMembers; }

    public int getMicLevel() { return micLevel.get(); }
    public void setMicLevel(int value) { micLevel.set(value); }
    public IntegerProperty micLevelProperty() { return micLevel; }

    public int getSpeakerBalance() { return speakerBalance.get(); }
    public void setSpeakerBalance(int value) { speakerBalance.set(value); }
    public IntegerProperty speakerBalanceProperty() { return speakerBalance; }

    public int getMonitorLevel() { return monitorLevel.get(); }
    public void setMonitorLevel(int value) { monitorLevel.set(value); }
    public IntegerProperty monitorLevelProperty() { return monitorLevel; }

    public String getEffect() { return effect.get(); }
    public void setEffect(String value) { effect.set(value); }
    public StringProperty effectProperty() { return effect; }

    public String getStatus() { return status.get(); }
    public void setStatus(String value) { status.set(value); }
    public StringProperty statusProperty() { return status; }
}