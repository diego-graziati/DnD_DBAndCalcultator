package com.sersapessi.views.dictionary;

import com.sersapessi.Main;
import com.sersapessi.MainSingleton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.sql.SQLException;

public class EnchantmentsDictView implements ActionListener {
    private JPanel mainP;
    private JPanel topP;
    private JComboBox<String> enchantmentsList;
    private JLabel originL;
    private JLabel scalabilityL;
    private JTextArea descriptionArea;
    private JScrollPane descriptionPane;
    private JList<String> damageList;
    private JTextArea levelingArea;
    private JScrollPane levelingPane;

    public EnchantmentsDictView(JPanel mainP, JPanel topP, JComboBox<String> enchantmentsList){
        this.mainP=mainP;
        this.topP=topP;
        this.enchantmentsList=enchantmentsList;
    }

    public void run() throws FileNotFoundException {
        GridBagConstraints gbc = new GridBagConstraints();

            for(int i=0; i< MainSingleton.getInstance().enchantments.size();i++){
                enchantmentsList.addItem(MainSingleton.getInstance().enchantments.get(i).getName());
            }
            enchantmentsList.addActionListener(this);
            if(MainSingleton.getInstance().enchantments.size() == 0){
                enchantmentsList.addItem("Nessun incantesimo trovato");
            }
            enchantmentsList.setVisible(true);

            if(MainSingleton.getInstance().enchantments.size()>0){
                int index = enchantmentsList.getSelectedIndex();

                originL = new JLabel("Origine: "+MainSingleton.getInstance().enchantments.get(index).getOrigin());
                scalabilityL = new JLabel("Scala rispetto a "+MainSingleton.getInstance().enchantments.get(index).getScalabilityParam());

                descriptionArea = new JTextArea(MainSingleton.getInstance().enchantments.get(index).getDescription());
                descriptionPane = new JScrollPane(descriptionArea);

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

                levelingArea = new JTextArea(MainSingleton.getInstance().enchantments.get(index).getLevelingDesc());
                levelingPane = new JScrollPane(levelingArea);

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

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == enchantmentsList){
            try{
                if(MainSingleton.getInstance().enchantments.size()>0){
                    int index = enchantmentsList.getSelectedIndex();

                    originL.setText("Origine: "+MainSingleton.getInstance().enchantments.get(index).getOrigin());
                    scalabilityL.setText("Scala rispetto a "+MainSingleton.getInstance().enchantments.get(index).getScalabilityParam());

                    descriptionArea.setText(MainSingleton.getInstance().enchantments.get(index).getDescription());

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

                    levelingArea.setText(MainSingleton.getInstance().enchantments.get(index).getLevelingDesc());
                }else{
                    enchantmentsList.addItem("Nessun incantesimo trovato");
                }
            }catch(FileNotFoundException ex){
                ex.printStackTrace();
            }

        }
    }
}
