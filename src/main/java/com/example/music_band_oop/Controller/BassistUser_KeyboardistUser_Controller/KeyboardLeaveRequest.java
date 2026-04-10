package com.example.music_band_oop.Controller.BassistUser_KeyboardistUser_Controller;

public class KeyboardLeaveRequest {
    private String startDate;
    private String endDate;
    private String reason;
    private String status;
    private long totalDays;

    public KeyboardLeaveRequest(String startDate, String endDate, String reason, String status, long totalDays) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.reason = reason;
        this.status = status;
        this.totalDays = totalDays;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(long totalDays) {
        this.totalDays = totalDays;
    }
}
