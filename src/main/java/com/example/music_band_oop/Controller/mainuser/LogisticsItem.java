package com.example.music_band_oop.Controller.mainuser;

public class LogisticsItem {
    private String aspect;
    private String value;
    private String status;


    public LogisticsItem(String aspect, String value, String status) {
        this.aspect = aspect;
        this.value = value;
        this.status = status;
    }

    public String getAspect() {
        return aspect;
    }

    public void setAspect(String aspect) {
        this.aspect = aspect;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "LogisticsItem{" +
                "aspect='" + aspect + '\'' +
                ", value='" + value + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
