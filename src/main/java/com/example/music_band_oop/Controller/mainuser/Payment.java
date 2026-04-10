package com.example.music_band_oop.Controller.mainuser;

public class Payment {

    private String vendorName;
    private String expenseType;
    private double amount;
    private String paymentStatus;
    private boolean verified;

    public Payment(String vendorName, String expenseType, double amount, String paymentStatus, boolean verified) {
        this.vendorName = vendorName;
        this.expenseType = expenseType;
        this.amount = amount;
        this.paymentStatus = paymentStatus;
        this.verified = verified;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(String expenseType) {
        this.expenseType = expenseType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "vendorName='" + vendorName + '\'' +
                ", expenseType='" + expenseType + '\'' +
                ", amount=" + amount +
                ", paymentStatus='" + paymentStatus + '\'' +
                ", verified=" + verified +
                '}';
    }
}
