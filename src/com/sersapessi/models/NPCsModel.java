package com.sersapessi.models;

public class NPCsModel {
    private String name;
    private String description;
    private boolean isEnemy;
    private String currentStatus;
    private String evolution;

    public NPCsModel(String name, String description, boolean isEnemy, String currentStatus, String evolution) {
        this.name = name;
        this.description = description;
        this.isEnemy = isEnemy;
        this.currentStatus = currentStatus;
        this.evolution = evolution;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isEnemy() {
        return isEnemy;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public String getEvolution() {
        return evolution;
    }
}
