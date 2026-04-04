package com.example.music_band_oop.Controller.mainuser;

public class ShowRecording {
    private String showTitle;
    private String date;
    private String filePath;
    private String feedback;

    public ShowRecording(String showTitle, String date, String filePath, String feedback) {
        this.showTitle = showTitle;
        this.date = date;
        this.filePath = filePath;
        this.feedback = feedback;
    }

    public String getShowTitle() {
        return showTitle;
    }

    public void setShowTitle(String showTitle) {
        this.showTitle = showTitle;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
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
                ", date='" + date + '\'' +
                ", filePath='" + filePath + '\'' +
                ", feedback='" + feedback + '\'' +
                '}';
    }
}