package com.example.music_band_oop.Controller.mainuser;

import java.io.Serializable;

public class ChannelData implements Serializable {
    private String name;
    private double level;
    private String status;


    public ChannelData(String name, double level, String status) {
        this.name = name;
        this.level = level;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLevel() {
        return level;
    }

    public void setLevel(double level) {
        this.level = level;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ChannelData{" +
                "name='" + name + '\'' +
                ", level=" + level +
                ", status='" + status + '\'' +
                '}';
    }
}