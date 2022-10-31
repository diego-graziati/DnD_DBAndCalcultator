package com.sersapessi.models;

import java.util.ArrayList;

public class CloseWeaponsModel {
    private String name;
    private ArrayList<DiceModel> dice;
    private boolean canBeThrown;
    private int cost;
    private String description;

    public CloseWeaponsModel(String name, ArrayList<DiceModel> dice, boolean canBeThrown, int cost, String description){
        this.canBeThrown=canBeThrown;
        this.cost=cost;
        this.description=description;
        this.dice=dice;
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<DiceModel> getDice() {
        return dice;
    }

    public boolean canBeThrown() {
        return canBeThrown;
    }

    public int getCost() {
        return cost;
    }

    public String getDescription() {
        return description;
    }
}
