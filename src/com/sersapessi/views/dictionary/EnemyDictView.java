package com.sersapessi.views.dictionary;

import javax.swing.*;

public class EnemyDictView {
    private JPanel mainP;
    private JPanel topP;
    private boolean isHuman;

    public EnemyDictView(JPanel mainP, JPanel topP, boolean isHuman){
        this.mainP=mainP;
        this.topP=topP;
        this.isHuman=isHuman;
    }
}
