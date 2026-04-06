package com.example.music_band_oop.Controller.mainuser;

public class ScheduleItem {

    private String time;
    private String activity;
    private String status;

    public ScheduleItem(String time, String activity, String status) {
        this.time = time;
        this.activity = activity;
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ScheduleItem{" +
                "time='" + time + '\'' +
                ", activity='" + activity + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
