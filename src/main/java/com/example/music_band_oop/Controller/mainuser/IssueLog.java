package com.example.music_band_oop.Controller.mainuser;

public class IssueLog {
    private String timestamp;
    private String channel;
    private String problem;
    private String action;
    private String status;

    public IssueLog(String timestamp, String channel, String problem, String action, String status) {
        this.timestamp = timestamp;
        this.channel = channel;
        this.problem = problem;
        this.action = action;
        this.status = status;
    }


    public String getTimestamp() {
        return timestamp;
    }

    public String getChannel() {
        return channel;
    }

    public String getProblem() {
        return problem;
    }

    public String getAction() {
        return action;
    }

    public String getStatus() {
        return status;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "IssueLog{" +
                "timestamp='" + timestamp + '\'' +
                ", channel='" + channel + '\'' +
                ", problem='" + problem + '\'' +
                ", action='" + action + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}