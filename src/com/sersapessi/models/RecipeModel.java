package com.sersapessi.models;

import java.util.ArrayList;

public class RecipeModel {
    private ItemModel item;
    private int num;

    public RecipeModel(ItemModel item, int num){
        this.item=item;
        this.num=num;
    }

    public ItemModel getItem() {
        return item;
    }

    public int getNum() {
        return num;
    }
}
