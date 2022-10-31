package com.sersapessi.models;

public class DamageEnchantmentModel {
    private int dice;
    private int initDice;
    private int finalDice;
    private boolean isNat;
    private String description;

    public DamageEnchantmentModel(int dice, int initDice, int finalDice, boolean isNat, String description){
        this.description=description;
        this.dice=dice;
        this.finalDice=finalDice;
        this.isNat=isNat;
        this.initDice=initDice;
    }

    public int getDice() {
        return dice;
    }

    public int getInitDice() {
        return initDice;
    }

    public int getFinalDice() {
        return finalDice;
    }

    public boolean isNat() {
        return isNat;
    }

    public String getDescription() {
        return description;
    }
}
