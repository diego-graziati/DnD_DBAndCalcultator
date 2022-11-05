package com.sersapessi.views.dictionary;

import com.sersapessi.MainSingleton;
import com.sersapessi.models.HumanEnemyModel;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class HumanEnemyDictView {
    private JPanel mainP;

    public HumanEnemyDictView(JPanel mainP){
        this.mainP=mainP;
    }

    //TODO: to be implemented
    public void run(int index) throws FileNotFoundException {
        GridBagConstraints gbc = new GridBagConstraints();

        if(MainSingleton.getInstance().humanEnemies.size()>0){

        }
    }
}
