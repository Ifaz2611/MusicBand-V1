package com.example.music_band_oop.Controller.mainuser;

public class DeviceModel {
    private String name;
    private String status;
    private int level;

    public DeviceModel(String name, String status, int level) {
        this.name = name;
        this.status = status;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "DeviceModel{" +
                "name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", level=" + level +
                '}';
    }
}
