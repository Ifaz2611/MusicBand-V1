package com.example.music_band_oop.Controller.mainuser;

import java.io.Serializable;

public class BudgetItem implements Serializable {
    private String costType;
    private double amount;
    private boolean paid;

    public BudgetItem(String costType, double amount, boolean paid) {
        this.costType = costType;
        this.amount = amount;
        this.paid = paid;
    }

    public String getCostType() {
        return costType;
    }

    public void setCostType(String costType) {
        this.costType = costType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    @Override
    public String toString() {
        return "BudgetItem{" +
                "costType='" + costType + '\'' +
                ", amount=" + amount +
                ", paid=" + paid +
                '}';
    }
}
