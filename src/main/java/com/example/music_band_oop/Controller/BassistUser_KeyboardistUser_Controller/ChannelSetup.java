package com.example.music_band_oop.Controller.BassistUser_KeyboardistUser_Controller;

public class ChannelSetup {
    private String channel;
    private String outputLevel;
    private String effects;
    private String pan;
    private String mute;

    public ChannelSetup(String channel, String outputLevel, String effects, String pan, String mute) {
        this.channel = channel;
        this.outputLevel = outputLevel;
        this.effects = effects;
        this.pan = pan;
        this.mute = mute;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getOutputLevel() {
        return outputLevel;
    }

    public void setOutputLevel(String outputLevel) {
        this.outputLevel = outputLevel;
    }

    public String getEffects() {
        return effects;
    }

    public void setEffects(String effects) {
        this.effects = effects;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getMute() {
        return mute;
    }

    public void setMute(String mute) {
        this.mute = mute;
    }
}
