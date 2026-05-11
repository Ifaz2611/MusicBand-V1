package com.example.music_band_oop.Controller.mainuser;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.io.Serializable;

public class IssueLog implements Serializable {
    private final StringProperty timestamp;
    private final StringProperty channel;
    private final StringProperty problem;
    private final StringProperty severity;
    private final StringProperty action;
    private final StringProperty status;
    private final StringProperty notes;

    public IssueLog(String timestamp, String channel, String problem, String severity,
                    String action, String status, String notes) {
        this.timestamp = new SimpleStringProperty(timestamp);
        this.channel = new SimpleStringProperty(channel);
        this.problem = new SimpleStringProperty(problem);
        this.severity = new SimpleStringProperty(severity);
        this.action = new SimpleStringProperty(action);
        this.status = new SimpleStringProperty(status);
        this.notes = new SimpleStringProperty(notes);
    }

    public String getTimestamp() { return timestamp.get(); }
    public void setTimestamp(String value) { timestamp.set(value); }
    public StringProperty timestampProperty() { return timestamp; }

    public String getChannel() { return channel.get(); }
    public void setChannel(String value) { channel.set(value); }
    public StringProperty channelProperty() { return channel; }

    public String getProblem() { return problem.get(); }
    public void setProblem(String value) { problem.set(value); }
    public StringProperty problemProperty() { return problem; }

    public String getSeverity() { return severity.get(); }
    public void setSeverity(String value) { severity.set(value); }
    public StringProperty severityProperty() { return severity; }

    public String getAction() { return action.get(); }
    public void setAction(String value) { action.set(value); }
    public StringProperty actionProperty() { return action; }

    public String getStatus() { return status.get(); }
    public void setStatus(String value) { status.set(value); }
    public StringProperty statusProperty() { return status; }

    public String getNotes() { return notes.get(); }
    public void setNotes(String value) { notes.set(value); }
    public StringProperty notesProperty() { return notes; }
}