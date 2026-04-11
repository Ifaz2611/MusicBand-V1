package com.example.music_band_oop.Controller.mainuser;

import java.io.Serializable;

public class MonitorChannel implements Serializable {
    private String performerName;
    private double currentLevel;

    public MonitorChannel(String performerName, double currentLevel) {
        this.performerName = performerName;
        this.currentLevel = currentLevel;
    }

    public String getPerformerName() {
        return performerName;
    }

    public void setPerformerName(String performerName) {
        this.performerName = performerName;
    }

    public double getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(double currentLevel) {
        this.currentLevel = currentLevel;
    }

    @Override
    public String toString() {
        return "MonitorChannel{" +
                "performerName='" + performerName + '\'' +
                ", currentLevel=" + currentLevel +
                '}';
    }
}