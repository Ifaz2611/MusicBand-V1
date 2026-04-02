package com.example.music_band_oop.Controller.mainuser;

public class SoundConfiguration {
    private int micLevel;
    private int speakerBalance;
    private int monitorLevel;
    private String effects;

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

    public int getMonitorLevel() {
        return monitorLevel;
    }

    public void setMonitorLevel(int monitorLevel) {
        this.monitorLevel = monitorLevel;
    }

    public String getEffects() {
        return effects;
    }

    public void setEffects(String effects) {
        this.effects = effects;
    }

    public SoundConfiguration(int micLevel, int speakerBalance, int monitorLevel, String effects) {
        this.micLevel = micLevel;
        this.speakerBalance = speakerBalance;
        this.monitorLevel = monitorLevel;
        this.effects = effects;


    }

    @Override
    public String toString() {
        return "SoundConfiguration{" +
                "micLevel=" + micLevel +
                ", speakerBalance=" + speakerBalance +
                ", monitorLevel=" + monitorLevel +
                ", effects='" + effects + '\'' +
                '}';
    }
}
