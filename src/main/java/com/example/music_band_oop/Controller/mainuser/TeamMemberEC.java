package com.example.music_band_oop.Controller.mainuser;

import java.io.Serializable;

public class TeamMemberEC implements Serializable {

    private String event;
    private String role;
    private boolean arrived;
    private boolean ready;

    public TeamMemberEC(String event, String role, boolean arrived, boolean ready) {
        this.event = event;
        this.role = role;
        this.arrived = arrived;
        this.ready = ready;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isArrived() {
        return arrived;
    }

    public void setArrived(boolean arrived) {
        this.arrived = arrived;
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    @Override
    public String toString() {
        return "TeamMemberEC{" +
                "event='" + event + '\'' +
                ", role='" + role + '\'' +
                ", arrived=" + arrived +
                ", ready=" + ready +
                '}';
    }
}