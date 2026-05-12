package com.example.music_band_oop.Controller.mainuser;

import javafx.beans.property.*;
import java.io.Serializable;

public class MonitorChannel implements Serializable {
    private final StringProperty timestamp;
    private final StringProperty channelName;
    private final StringProperty performerName;
    private final StringProperty role;
    private final StringProperty inputSource;
    private final DoubleProperty currentLevel;
    private final DoubleProperty targetLevel;
    private final IntegerProperty pan;
    private final BooleanProperty muted;
    private final BooleanProperty solo;
    private final StringProperty status;
    private final StringProperty notes;

    public MonitorChannel(String timestamp, String channelName, String performerName, String role,
                          String inputSource, double currentLevel, double targetLevel, int pan,
                          boolean muted, boolean solo, String status, String notes) {
        this.timestamp = new SimpleStringProperty(timestamp);
        this.channelName = new SimpleStringProperty(channelName);
        this.performerName = new SimpleStringProperty(performerName);
        this.role = new SimpleStringProperty(role);
        this.inputSource = new SimpleStringProperty(inputSource);
        this.currentLevel = new SimpleDoubleProperty(currentLevel);
        this.targetLevel = new SimpleDoubleProperty(targetLevel);
        this.pan = new SimpleIntegerProperty(pan);
        this.muted = new SimpleBooleanProperty(muted);
        this.solo = new SimpleBooleanProperty(solo);
        this.status = new SimpleStringProperty(status);
        this.notes = new SimpleStringProperty(notes);
    }

    public String getTimestamp() { return timestamp.get(); }
    public void setTimestamp(String value) { timestamp.set(value); }
    public StringProperty timestampProperty() { return timestamp; }

    public String getChannelName() { return channelName.get(); }
    public void setChannelName(String value) { channelName.set(value); }
    public StringProperty channelNameProperty() { return channelName; }

    public String getPerformerName() { return performerName.get(); }
    public void setPerformerName(String value) { performerName.set(value); }
    public StringProperty performerNameProperty() { return performerName; }

    public String getRole() { return role.get(); }
    public void setRole(String value) { role.set(value); }
    public StringProperty roleProperty() { return role; }

    public String getInputSource() { return inputSource.get(); }
    public void setInputSource(String value) { inputSource.set(value); }
    public StringProperty inputSourceProperty() { return inputSource; }

    public double getCurrentLevel() { return currentLevel.get(); }
    public void setCurrentLevel(double value) { currentLevel.set(value); }
    public DoubleProperty currentLevelProperty() { return currentLevel; }

    public double getTargetLevel() { return targetLevel.get(); }
    public void setTargetLevel(double value) { targetLevel.set(value); }
    public DoubleProperty targetLevelProperty() { return targetLevel; }

    public int getPan() { return pan.get(); }
    public void setPan(int value) { pan.set(value); }
    public IntegerProperty panProperty() { return pan; }

    public boolean isMuted() { return muted.get(); }
    public void setMuted(boolean value) { muted.set(value); }
    public BooleanProperty mutedProperty() { return muted; }

    public boolean isSolo() { return solo.get(); }
    public void setSolo(boolean value) { solo.set(value); }
    public BooleanProperty soloProperty() { return solo; }

    public String getStatus() { return status.get(); }
    public void setStatus(String value) { status.set(value); }
    public StringProperty statusProperty() { return status; }

    public String getNotes() { return notes.get(); }
    public void setNotes(String value) { notes.set(value); }
    public StringProperty notesProperty() { return notes; }
}