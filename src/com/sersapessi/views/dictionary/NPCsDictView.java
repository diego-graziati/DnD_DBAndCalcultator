package com.sersapessi.views.dictionary;

import com.sersapessi.MainSingleton;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class NPCsDictView {
    private JPanel mainP;
    private JLabel isEnemyL;
    private JLabel currentStatusL;
    private JTextArea descriptionArea;
    private JScrollPane descriptionPane;
    private JTextArea evolutionArea;
    private JScrollPane evolutionPane;

    public NPCsDictView(JPanel mainP){
        this.mainP=mainP;
    }

    public void run(int index) throws IOException {
        GridBagConstraints gbc = new GridBagConstraints();
        if(MainSingleton.getInstance().npcs.size()>0){
            if(isEnemyL==null){
                if(MainSingleton.getInstance().npcs.get(index).isEnemy()){
                    isEnemyL = new JLabel("L'NPC è un nemico");
                }else{
                    isEnemyL = new JLabel("L'NPC non è un nemico");
                }
            }else{
                if(MainSingleton.getInstance().npcs.get(index).isEnemy()){
                    isEnemyL.setText("L'NPC è un nemico");
                }else{
                    isEnemyL.setText("L'NPC non è un nemico");
                }
            }

            if(currentStatusL==null){
                currentStatusL = new JLabel("Stato: "+MainSingleton.getInstance().npcs.get(index).getCurrentStatus());
            }else{
                currentStatusL.setText("Stato: "+MainSingleton.getInstance().npcs.get(index).getCurrentStatus());
            }

            if(descriptionArea==null){
                descriptionArea = new JTextArea(MainSingleton.getInstance().npcs.get(index).getDescription());
                descriptionArea.setLineWrap(true);
                descriptionArea.setEditable(false);
                descriptionPane = new JScrollPane(descriptionArea);
            }else{
                descriptionArea.setText(MainSingleton.getInstance().npcs.get(index).getDescription());
            }

            if(evolutionArea==null){
                evolutionArea = new JTextArea(MainSingleton.getInstance().npcs.get(index).getEvolution());
                evolutionArea.setLineWrap(true);
                evolutionArea.setEditable(false);
                evolutionPane = new JScrollPane(evolutionArea);
            }else{
                evolutionArea.setText(MainSingleton.getInstance().npcs.get(index).getEvolution());
            }

            gbc.insets=new Insets(10,10,10,10);
            gbc.gridy=0;
            gbc.gridx=0;
            gbc.weighty=0.20;
            gbc.weightx=0.50;
            gbc.fill=GridBagConstraints.BOTH;
            mainP.add(isEnemyL,gbc);
            gbc.gridx=1;
            gbc.fill=GridBagConstraints.BOTH;
            mainP.add(currentStatusL,gbc);
            gbc.gridy=1;
            gbc.gridx=0;
            gbc.weighty=0.40;
            gbc.weightx=1.0;
            gbc.gridwidth=2;
            gbc.fill=GridBagConstraints.BOTH;
            mainP.add(descriptionPane,gbc);
            gbc.gridy=2;
            gbc.fill=GridBagConstraints.BOTH;
            mainP.add(evolutionPane,gbc);
        }
    }
}
