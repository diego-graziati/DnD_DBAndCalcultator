package com.sersapessi.models;

import java.util.ArrayList;

public class EnemiesAttackModel {
    private String name;
    private String addDescription;
    private DiceModel dice;

    public EnemiesAttackModel(String name, String addDescription, DiceModel dice) {
        this.name = name;
        this.addDescription = addDescription;
        this.dice = dice;
    }

    public String getName() {
        return name;
    }

    public String getAddDescription() {
        return addDescription;
    }

    public DiceModel getDice() {
        return dice;
    }
}
