package com.example.music_band_oop.Controller.mainuser;

import java.io.Serializable;
import java.time.LocalDate;

public class EquipmentTrack implements Serializable {

    private int id;
    private String name;
    private String type;
    private LocalDate maintenanceDueDate;
    private String repairStatus;
    private String notes;

    public EquipmentTrack(int id, String name, String type,
                          LocalDate maintenanceDueDate,
                          String repairStatus,
                          String notes) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.maintenanceDueDate = maintenanceDueDate;
        this.repairStatus = repairStatus;
        this.notes = notes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getMaintenanceDueDate() {
        return maintenanceDueDate;
    }

    public void setMaintenanceDueDate(LocalDate maintenanceDueDate) {
        this.maintenanceDueDate = maintenanceDueDate;
    }

    public String getRepairStatus() {
        return repairStatus;
    }

    public void setRepairStatus(String repairStatus) {
        this.repairStatus = repairStatus;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "EquipmentTrack{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", maintenanceDueDate=" + maintenanceDueDate +
                ", repairStatus='" + repairStatus + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }
}