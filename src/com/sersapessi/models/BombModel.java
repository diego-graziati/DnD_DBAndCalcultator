package com.sersapessi.models;

import java.util.ArrayList;

public class BombModel {
    private String name;
    private int cost;
    private String description;
    private ArrayList<RecipeModel> recipe;

    public BombModel(String name, int cost, String description, ArrayList<RecipeModel> recipe){
        this.name=name;
        this.cost=cost;
        this.description=description;
        this.recipe=recipe;
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
}
