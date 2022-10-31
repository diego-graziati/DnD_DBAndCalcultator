package com.sersapessi.views.dictionary;

import com.sersapessi.MainSingleton;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class ArmorDictView {
    private JPanel mainP;
    private JLabel baseTotalCAL;
    private JLabel baseCostL;
    private JTextArea descriptionArea;
    private JScrollPane descriptionPane;

    public ArmorDictView(JPanel mainP){
        this.mainP = mainP;
    }

    public void run(int index) throws FileNotFoundException {
        GridBagConstraints gbc = new GridBagConstraints();

        if(MainSingleton.getInstance().armors.size()>0){
            if(baseTotalCAL==null){
                baseTotalCAL = new JLabel("CA totale base dell'armatura: "+MainSingleton.getInstance().armors.get(index).getBaseTotalCA());
            }else{
                baseTotalCAL.setText("CA totale base dell'armatura: "+MainSingleton.getInstance().armors.get(index).getBaseTotalCA());
            }

            if(baseCostL==null){
                baseCostL = new JLabel("Costo totale base dell'armatura: "+MainSingleton.getInstance().armors.get(index).getBaseCost());
            }else{
                baseCostL.setText("Costo totale base dell'armatura: "+MainSingleton.getInstance().armors.get(index).getBaseCost());
            }

            if(descriptionArea==null){
                descriptionArea = new JTextArea(MainSingleton.getInstance().armors.get(index).getDescription());
                descriptionPane = new JScrollPane(descriptionArea);
            }else{
                descriptionArea.setText(MainSingleton.getInstance().armors.get(index).getDescription());
            }

            gbc.gridy=0;
            gbc.gridx=0;
            gbc.weightx=0.5;
            gbc.weighty=0.4;
            gbc.fill=GridBagConstraints.BOTH;
            gbc.insets = new Insets(10,10,10,10);
            mainP.add(baseTotalCAL,gbc);
            gbc.gridx=1;
            gbc.fill=GridBagConstraints.BOTH;
            mainP.add(baseCostL,gbc);
            gbc.gridy=1;
            gbc.gridx=0;
            gbc.gridwidth=2;
            gbc.weighty=0.6;
            gbc.fill=GridBagConstraints.BOTH;
            mainP.add(descriptionPane,gbc);
        }
    }
}
