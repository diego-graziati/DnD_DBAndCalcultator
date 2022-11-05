package com.sersapessi.models;

import java.util.ArrayList;

public class BombModel {
    private String name;
    private int cost;
    private String description;
    private ArrayList<RecipeModel> recipe;
    private ArrayList<DiceModel> damage;

    public BombModel(String name, int cost, String description, ArrayList<RecipeModel> recipe, ArrayList<DiceModel> damage){
        this.name=name;
        this.cost=cost;
        this.description=description;
        this.recipe=recipe;
        this.damage=damage;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<RecipeModel> getRecipe() {
        return recipe;
    }

    public ArrayList<DiceModel> getDamage() {
        return damage;
    }
}
