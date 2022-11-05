package com.sersapessi.models;

public class StatusModel {
    private String name;
    private String description;

    public StatusModel(String name, String description){
        this.name=name;
        this.description=description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
