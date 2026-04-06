package com.example.music_band_oop.Controller.mainuser;

public class Vendor {
    private String name;
    private String type;
    private String contractStatus;
    private String deliveryStatus;

    public Vendor(String name, String type, String contractStatus, String deliveryStatus) {
        this.name = name;
        this.type = type;
        this.contractStatus = contractStatus;
        this.deliveryStatus = deliveryStatus;
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

    public String getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(String contractStatus) {
        this.contractStatus = contractStatus;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    @Override
    public String toString() {
        return "Vendor{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", contractStatus='" + contractStatus + '\'' +
                ", deliveryStatus='" + deliveryStatus + '\'' +
                '}';
    }
}
