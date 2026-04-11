package com.example.music_band_oop.Controller.mainuser;

public class ShowRecording {

    private String showTitle;
    private String feedback;

    public ShowRecording(String showTitle, String feedback) {
        this.showTitle = showTitle;
        this.feedback = feedback;
    }

    public String getShowTitle() {
        return showTitle;
    }

    public void setShowTitle(String showTitle) {
        this.showTitle = showTitle;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    @Override
    public String toString() {
        return "ShowRecording{" +
                "showTitle='" + showTitle + '\'' +
                ", feedback='" + feedback + '\'' +
                '}';
    }
}