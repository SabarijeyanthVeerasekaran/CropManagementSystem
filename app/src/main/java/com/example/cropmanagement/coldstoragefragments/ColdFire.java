package com.example.cropmanagement.coldstoragefragments;

public class ColdFire {
    private String name;
    private long capacity;
    private  String purpose;

    public ColdFire() {
    }

    public ColdFire(String name, long capacity, String purpose) {
        this.name = name;
        this.capacity = capacity;
        this.purpose = purpose;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCapacity() {
        return capacity;
    }

    public void setCapacity(long capacity) {
        this.capacity = capacity;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
}
