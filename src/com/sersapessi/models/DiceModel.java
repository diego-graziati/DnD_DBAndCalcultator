package com.sersapessi.models;

import java.util.ArrayList;

public class DiceModel {
    private int dice;
    private int numOfDice;
    private ArrayList<TypeOfDamageModel> typeOfDamage;      //Normally one: can be more. Normally the last typeOfDamage gets a description
    private String description;

    public DiceModel(int dice, int numOfDice, String description, ArrayList<TypeOfDamageModel> typeOfDamage){
        this.dice=dice;
        this.numOfDice=numOfDice;
        this.typeOfDamage=typeOfDamage;
        this.description=description;
    }

    public int getDice() {
        return dice;
    }

    public int getNumOfDice() {
        return numOfDice;
    }

    public ArrayList<TypeOfDamageModel> getTypeOfDamage() {
        return typeOfDamage;
    }

    public String getDescription() {
        return description;
    }
}
