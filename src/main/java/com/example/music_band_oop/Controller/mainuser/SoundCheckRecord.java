package com.example.music_band_oop.Controller.mainuser;

import javafx.beans.property.*;

import java.time.LocalDate;

public class SoundCheckRecord {

    private final StringProperty eventName;
    private final ObjectProperty<LocalDate> eventDate;
    private final StringProperty venue;
    private final StringProperty status;
    private final IntegerProperty soundLevel;
    private final StringProperty engineerName;
    private final StringProperty notes;
    private final BooleanProperty performerReady;
    private final BooleanProperty equipmentReady;
    private final BooleanProperty backupReady;

    public SoundCheckRecord(String eventName, LocalDate eventDate, String venue,
                            String status, int soundLevel, String engineerName,
                            String notes, boolean performerReady,
                            boolean equipmentReady, boolean backupReady) {
        this.eventName = new SimpleStringProperty(eventName);
        this.eventDate = new SimpleObjectProperty<>(eventDate);
        this.venue = new SimpleStringProperty(venue);
        this.status = new SimpleStringProperty(status);
        this.soundLevel = new SimpleIntegerProperty(soundLevel);
        this.engineerName = new SimpleStringProperty(engineerName);
        this.notes = new SimpleStringProperty(notes);
        this.performerReady = new SimpleBooleanProperty(performerReady);
        this.equipmentReady = new SimpleBooleanProperty(equipmentReady);
        this.backupReady = new SimpleBooleanProperty(backupReady);
    }

    // --- Getters ---
    public String getEventName() { return eventName.get(); }
    public LocalDate getEventDate() { return eventDate.get(); }
    public String getVenue() { return venue.get(); }
    public String getStatus() { return status.get(); }
    public int getSoundLevel() { return soundLevel.get(); }
    public String getEngineerName() { return engineerName.get(); }
    public String getNotes() { return notes.get(); }
    public boolean isPerformerReady() { return performerReady.get(); }
    public boolean isEquipmentReady() { return equipmentReady.get(); }
    public boolean isBackupReady() { return backupReady.get(); }

    // --- Setters ---
    public void setEventName(String value) { eventName.set(value); }
    public void setEventDate(LocalDate value) { eventDate.set(value); }
    public void setVenue(String value) { venue.set(value); }
    public void setStatus(String value) { status.set(value); }
    public void setSoundLevel(int value) { soundLevel.set(value); }
    public void setEngineerName(String value) { engineerName.set(value); }
    public void setNotes(String value) { notes.set(value); }
    public void setPerformerReady(boolean value) { performerReady.set(value); }
    public void setEquipmentReady(boolean value) { equipmentReady.set(value); }
    public void setBackupReady(boolean value) { backupReady.set(value); }

    // --- Property Accessors (needed for TableView binding) ---
    public StringProperty eventNameProperty() { return eventName; }
    public ObjectProperty<LocalDate> eventDateProperty() { return eventDate; }
    public StringProperty venueProperty() { return venue; }
    public StringProperty statusProperty() { return status; }
    public IntegerProperty soundLevelProperty() { return soundLevel; }
    public StringProperty engineerNameProperty() { return engineerName; }
    public StringProperty notesProperty() { return notes; }


}
