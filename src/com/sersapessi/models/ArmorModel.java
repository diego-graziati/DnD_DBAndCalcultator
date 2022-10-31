package com.sersapessi.models;

public class ArmorModel {
    private String name;
    private int baseTotalCA;
    private int baseCost;
    private String description;

    public ArmorModel(String name, int baseTotalCA, int baseCost, String description){
        this.baseCost=baseCost;
        this.name=name;
        this.baseTotalCA=baseTotalCA;
        this.description=description;
    }

    public String getName() {
        return name;
    }

    public int getBaseTotalCA() {
        return baseTotalCA;
    }

    public int getBaseCost() {
        return baseCost;
    }

    public String getDescription() {
        return description;
    }
}
