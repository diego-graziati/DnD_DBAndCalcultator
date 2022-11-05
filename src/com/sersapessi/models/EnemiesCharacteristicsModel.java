package com.sersapessi.models;

public class EnemiesCharacteristicsModel {
    private String name;
    private String description;

    public EnemiesCharacteristicsModel(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
