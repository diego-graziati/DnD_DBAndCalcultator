package com.sersapessi.models;

import java.util.ArrayList;

public abstract class EnemyModel {
    private String name;
    private int hitPoints;
    private int CA;
    private String description;
    private ArrayList<MoveSetModel> moveSet;
    private ArrayList<EnemiesAttackModel> attacksSet;
    private ArrayList<EnemiesCharacteristicsModel> characteristics;

    public EnemyModel(String name, int hitPoints, int CA, String description,
                           ArrayList<MoveSetModel> moveSet, ArrayList<EnemiesAttackModel> attacksSet,
                           ArrayList<EnemiesCharacteristicsModel> characteristics) {
        this.name = name;
        this.hitPoints = hitPoints;
        this.CA = CA;
        this.description = description;
        this.moveSet = moveSet;
        this.attacksSet = attacksSet;
        this.characteristics = characteristics;
    }

    public String getName() {
        return name;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public int getCA() {
        return CA;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<MoveSetModel> getMoveSet() {
        return moveSet;
    }

    public ArrayList<EnemiesAttackModel> getAttacksSet() {
        return attacksSet;
    }

    public ArrayList<EnemiesCharacteristicsModel> getCharacteristics() {
        return characteristics;
    }
}
