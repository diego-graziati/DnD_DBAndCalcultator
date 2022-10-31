package com.sersapessi.models;

import java.util.ArrayList;

public class EnchantmentModel {
    String name;
    String origin;
    String scalabilityParam;
    String levelingDesc;
    String description;
    ArrayList<DamageEnchantmentModel> damages;

    public EnchantmentModel(String name, String origin, String scalabilityParam, String levelingDesc, String description, ArrayList<DamageEnchantmentModel> damages){
        this.damages=damages;
        this.name=name;
        this.description=description;
        this.origin=origin;
        this.scalabilityParam=scalabilityParam;
        this.levelingDesc=levelingDesc;
    }

    public String getName() {
        return name;
    }

    public String getOrigin() {
        return origin;
    }

    public String getScalabilityParam() {
        return scalabilityParam;
    }

    public String getLevelingDesc() {
        return levelingDesc;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<DamageEnchantmentModel> getDamages() {
        return damages;
    }
}
