package com.example.music_band_oop.Controller.mainuser;

public class AudienceEntry {
    private String name;
    private String ticketId;
    private boolean verified;
    private boolean allowedEntry;


    public AudienceEntry(String name, String ticketId, boolean verified, boolean allowedEntry) {
        this.name = name;
        this.ticketId = ticketId;
        this.verified = verified;
        this.allowedEntry = allowedEntry;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public boolean isAllowedEntry() {
        return allowedEntry;
    }

    public void setAllowedEntry(boolean allowedEntry) {
        this.allowedEntry = allowedEntry;
    }

    @Override
    public String toString() {
        return "AudienceEntry{" +
                "name='" + name + '\'' +
                ", ticketId='" + ticketId + '\'' +
                ", verified=" + verified +
                ", allowedEntry=" + allowedEntry +
                '}';
    }
}
