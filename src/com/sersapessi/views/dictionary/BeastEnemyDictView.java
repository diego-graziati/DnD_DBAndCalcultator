package com.sersapessi.views.dictionary;

import com.sersapessi.MainSingleton;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class BeastEnemyDictView {
    private JPanel mainP;
    private JLabel hitPointsL;
    private JLabel CAL;
    private JList<String> charactList;
    private JList<String> damageList;
    private JList<String> movesList;
    private JTextArea descriptionArea;
    private JScrollPane descriptionPane;

    public BeastEnemyDictView(JPanel mainP) {
        this.mainP = mainP;
    }

    public void run(int index) throws IOException {
        GridBagConstraints gbc = new GridBagConstraints();

        if (MainSingleton.getInstance().beastEnemies.size() > 0) {
            if (hitPointsL == null) {
                hitPointsL = new JLabel("Punti ferita: " + MainSingleton.getInstance().beastEnemies.get(index).getHitPoints());
            } else {
                hitPointsL.setText("Punti ferita: " + MainSingleton.getInstance().beastEnemies.get(index).getHitPoints());
            }

            if (CAL == null) {
                CAL = new JLabel("CA: " + MainSingleton.getInstance().beastEnemies.get(index).getCA());
            } else {
                CAL.setText("CA: " + MainSingleton.getInstance().beastEnemies.get(index).getCA());
            }

            charactList = new JList<>();
            for (int i = 0; i < MainSingleton.getInstance().beastEnemies.get(index).getCharacteristics().size(); i++) {
                charactList.add(new JLabel(MainSingleton.getInstance().beastEnemies.get(index).getCharacteristics().get(i).getName() + ": " +
                        MainSingleton.getInstance().beastEnemies.get(index).getCharacteristics().get(i).getDescription()));
            }

            damageList = new JList<>();
            for (int i = 0; i < MainSingleton.getInstance().beastEnemies.get(index).getAttacksSet().size(); i++) {
                StringBuilder str = new StringBuilder(MainSingleton.getInstance().beastEnemies.get(index).getAttacksSet().get(i).getName() + ", ");
                str.append(MainSingleton.getInstance().beastEnemies.get(index).getAttacksSet().get(i).getDice().getNumOfDice()).append("d")
                        .append(MainSingleton.getInstance().beastEnemies.get(index).getAttacksSet().get(i).getDice().getDice());
                for (int j = 0; j < MainSingleton.getInstance().beastEnemies.get(index).getAttacksSet().get(i).getDice().getTypeOfDamage().size(); j++) {
                    str.append(" ").append(MainSingleton.getInstance().beastEnemies.get(index).getAttacksSet().get(i).getDice().getTypeOfDamage().get(j).getName());
                }
                str.append(" ").append(MainSingleton.getInstance().beastEnemies.get(index).getAttacksSet().get(i).getAddDescription());

                damageList.add(new JLabel(str.toString()));
            }

            movesList = new JList<>();
            for (int i = 0; i < MainSingleton.getInstance().beastEnemies.get(index).getMoveSet().size(); i++) {
                movesList.add(new JLabel(MainSingleton.getInstance().beastEnemies.get(index).getMoveSet().get(i).getName() + ": " +
                        MainSingleton.getInstance().beastEnemies.get(index).getMoveSet().get(i).getDescription()));
            }

            if (descriptionArea == null) {
                descriptionArea = new JTextArea(MainSingleton.getInstance().beastEnemies.get(index).getDescription());
                descriptionArea.setLineWrap(true);
                descriptionArea.setEditable(false);
                descriptionPane = new JScrollPane(descriptionArea);
            } else {
                descriptionArea.setText(MainSingleton.getInstance().beastEnemies.get(index).getDescription());
            }

            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.gridy = 0;
            gbc.gridx = 0;
            gbc.weightx = 0.50;
            gbc.weighty = 0.10;
            gbc.fill = GridBagConstraints.BOTH;
            mainP.add(hitPointsL, gbc);
            gbc.gridx = 1;
            gbc.fill = GridBagConstraints.BOTH;
            mainP.add(CAL, gbc);
            gbc.gridy = 1;
            gbc.gridx = 0;
            gbc.weighty = 0.20;
            gbc.weightx=1.0;
            gbc.gridwidth = 2;
            gbc.fill = GridBagConstraints.BOTH;
            mainP.add(charactList, gbc);
            gbc.gridy = 2;
            gbc.fill = GridBagConstraints.BOTH;
            mainP.add(damageList, gbc);
            gbc.gridy = 3;
            gbc.fill = GridBagConstraints.BOTH;
            mainP.add(movesList, gbc);
            gbc.gridy = 4;
            gbc.weighty = 0.30;
            gbc.fill = GridBagConstraints.BOTH;
            mainP.add(descriptionPane, gbc);
        }
    }
}
