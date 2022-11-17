package com.sersapessi.views.dictionary;

import com.sersapessi.Main;
import com.sersapessi.MainSingleton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

public class EnchantmentsDictView {
    private JPanel mainP;
    private JPanel topP;
    private JLabel originL;
    private JLabel scalabilityL;
    private JTextArea descriptionArea;
    private JScrollPane descriptionPane;
    private JList<String> damageList;
    private JTextArea levelingArea;
    private JScrollPane levelingPane;

    public EnchantmentsDictView(JPanel mainP){
        this.mainP=mainP;
        this.topP=topP;
    }

    public void run(int index) throws IOException {
        GridBagConstraints gbc = new GridBagConstraints();

        if(MainSingleton.getInstance().enchantments.size()>0){

            if(originL==null){
                originL = new JLabel("Origine: "+MainSingleton.getInstance().enchantments.get(index).getOrigin());
            }else{
                originL.setText("Origine: "+MainSingleton.getInstance().enchantments.get(index).getOrigin());
            }
            if(scalabilityL==null){
                scalabilityL = new JLabel("Scala rispetto a "+MainSingleton.getInstance().enchantments.get(index).getScalabilityParam());
            }else{
                scalabilityL.setText("Scala rispetto a "+MainSingleton.getInstance().enchantments.get(index).getScalabilityParam());
            }

            if(descriptionArea==null){
                descriptionArea = new JTextArea(MainSingleton.getInstance().enchantments.get(index).getDescription());
                descriptionArea.setLineWrap(true);
                descriptionArea.setEditable(false);
                descriptionPane = new JScrollPane(descriptionArea);
            }else{
                descriptionArea.setText(MainSingleton.getInstance().enchantments.get(index).getDescription());
            }

            damageList = new JList<>();
            for(int i=0;i<MainSingleton.getInstance().enchantments.get(index).getDamages().size();i++){
                StringBuilder str = new StringBuilder("d"+MainSingleton.getInstance().enchantments.get(index).getDamages().get(i).getDice()+" ");
                if(MainSingleton.getInstance().enchantments.get(index).getDamages().get(i).getInitDice() == MainSingleton.getInstance().enchantments.get(index).getDamages().get(i).getFinalDice()){
                    if(MainSingleton.getInstance().enchantments.get(index).getDamages().get(i).isNat()){
                        str.append(MainSingleton.getInstance().enchantments.get(index).getDamages().get(i).getDice()).append(" crit");
                    }else{
                        str.append(MainSingleton.getInstance().enchantments.get(index).getDamages().get(i).getFinalDice());
                    }
                }else{
                    str.append(MainSingleton.getInstance().enchantments.get(index).getDamages().get(i).getInitDice())
                            .append("/").append(MainSingleton.getInstance().enchantments.get(index).getDamages().get(i).getFinalDice());
                }
                str.append(" ").append(MainSingleton.getInstance().enchantments.get(index).getDamages().get(i).getDescription());

                damageList.add(new JLabel(str.toString()));
            }

            if(levelingArea==null){
                levelingArea = new JTextArea(MainSingleton.getInstance().enchantments.get(index).getLevelingDesc());
                levelingArea.setLineWrap(true);
                levelingArea.setEditable(false);
                levelingPane = new JScrollPane(levelingArea);
            }else{
                levelingArea.setText(MainSingleton.getInstance().enchantments.get(index).getLevelingDesc());
            }

            gbc.gridx=0;
            gbc.gridy=0;
            gbc.weightx=0.5;
            gbc.weighty=0.10;
            gbc.fill=GridBagConstraints.BOTH;
            mainP.add(originL,gbc);
            gbc.gridx=1;
            mainP.add(scalabilityL,gbc);
            gbc.gridx=0;
            gbc.gridy=1;
            gbc.gridwidth=2;
            mainP.add(descriptionPane,gbc);
            gbc.gridy=2;
            mainP.add(damageList,gbc);
            gbc.gridy=3;
            mainP.add(levelingPane,gbc);
        }
    }
}
