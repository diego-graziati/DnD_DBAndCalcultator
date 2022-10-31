package com.sersapessi.models;

import java.util.ArrayList;

public class LongWeaponsModel {
    private String name;
    private ArrayList<DiceModel> dice;
    private int cost;
    private String description;

    public LongWeaponsModel(String name, ArrayList<DiceModel> dice, int cost, String description){
        this.name=name;
        this.dice=dice;
        this.cost=cost;
        this.description=description;
    }

    public String getName() {
        return name;
    }

    public ArrayList<DiceModel> getDice() {
        return dice;
    }

    public int getCost() {
        return cost;
    }

    public String getDescription() {
        return description;
    }
}
