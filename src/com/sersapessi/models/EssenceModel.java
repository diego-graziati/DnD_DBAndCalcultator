package com.sersapessi.models;

import java.util.ArrayList;

public class EssenceModel {
    private String name;
    private int cost;
    private String description;
    private ArrayList<EssenceEffectsModel> effects;
    private ArrayList<RecipeModel> recipe;

    public EssenceModel(String name, int cost, String description, ArrayList<EssenceEffectsModel> effects, ArrayList<RecipeModel> recipe) {
        this.name = name;
        this.cost = cost;
        this.description = description;
        this.effects = effects;
        this.recipe = recipe;
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

    public ArrayList<EssenceEffectsModel> getEffects() {
        return effects;
    }

    public ArrayList<RecipeModel> getRecipe() {
        return recipe;
    }
}
