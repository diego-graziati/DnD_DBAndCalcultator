package com.sersapessi.models;

public class MoveSetModel {
    private String name;
    private String description;

    public MoveSetModel(String name, String description) {
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
