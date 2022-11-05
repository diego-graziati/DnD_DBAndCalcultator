package com.sersapessi.models;

import java.util.ArrayList;

public class BeastEnemyModel extends EnemyModel{

    public BeastEnemyModel(String name, int hitPoints, int CA, String description,
                           ArrayList<MoveSetModel> moveSet, ArrayList<EnemiesAttackModel> attacksSet,
                           ArrayList<EnemiesCharacteristicsModel> characteristics) {
        super(name, hitPoints, CA, description, moveSet, attacksSet, characteristics);
    }
}
