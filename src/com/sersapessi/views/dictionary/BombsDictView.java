package com.sersapessi.views.dictionary;

import com.sersapessi.Main;
import com.sersapessi.MainSingleton;
import com.sersapessi.models.RecipeModel;
import com.sersapessi.renderers.RecipeListRenderer;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class BombsDictView {
    private JPanel mainP;

    private JLabel costL;

    private JScrollPane descriptionPane;
    private JTextArea descriptionArea;
    private JList<String> damageList;
    private JList<RecipeModel> recipeList;

    public BombsDictView(JPanel mainP){
        this.mainP=mainP;
    }

    public void run(int index) throws IOException {
        GridBagConstraints gbc = new GridBagConstraints();

        if(MainSingleton.getInstance().bombs.size()>0){
            if(costL==null){
                costL=new JLabel("Costo singola bomba: "+MainSingleton.getInstance().bombs.get(index).getCost());
            }else{
                costL.setText("Costo singola bomba: "+MainSingleton.getInstance().bombs.get(index).getCost());
            }

            if(damageList == null){
                damageList = new JList<>();
            }else{
                damageList.removeAll();
            }
            for(int i=0; i<MainSingleton.getInstance().bombs.get(index).getDamage().size(); i++){
                StringBuilder str = new StringBuilder(MainSingleton.getInstance().bombs.get(index).getDamage().get(i).getNumOfDice()+"d"+
                        MainSingleton.getInstance().bombs.get(index).getDamage().get(i).getDice()+" ");
                for(int j=0; j<MainSingleton.getInstance().bombs.get(index).getDamage().get(i).getTypeOfDamage().size(); j++){
                    str.append(MainSingleton.getInstance().bombs.get(index).getDamage().get(i).getTypeOfDamage().get(j).getName()).append(" ");
                }
                if(MainSingleton.getInstance().bombs.get(index).getDamage().get(i).getDescription()!=null){
                    str.append(MainSingleton.getInstance().bombs.get(index).getDamage().get(i).getDescription());
                }

                damageList.add(new JLabel(str.toString()));
            }

            if(descriptionArea == null){
                descriptionArea = new JTextArea(MainSingleton.getInstance().bombs.get(index).getDescription());
                descriptionArea.setLineWrap(true);
                descriptionArea.setEditable(false);
                descriptionPane = new JScrollPane(descriptionArea);
            }else{
                descriptionArea.setText(MainSingleton.getInstance().bombs.get(index).getDescription());
            }

            //TODO: creare tutto l'ambaradam per la recipeList. Ricorda: l'item deve essere selezionabile. Guarda: https://docs.oracle.com/javase/7/docs/api/javax/swing/ListCellRenderer.html (ListCellRenderer)
            recipeList = new JList<>((RecipeModel[]) MainSingleton.getInstance().bombs.get(index).getRecipe().toArray());
            recipeList.setCellRenderer(new RecipeListRenderer(MainSingleton.getInstance().bombs.get(index).getRecipe()));

            gbc.insets = new Insets(10,10,10,10);
            gbc.gridy = 0;
            gbc.gridx = 0;
            gbc.weighty = 0.10;
            gbc.weightx= 1.0;
            gbc.fill = GridBagConstraints.BOTH;
            mainP.add(costL,gbc);
            gbc.gridy = 1;
            gbc.weighty = 0.3;
            gbc.fill = GridBagConstraints.BOTH;
            mainP.add(damageList,gbc);
            gbc.gridy = 2;
            gbc.fill = GridBagConstraints.BOTH;
            mainP.add(recipeList,gbc);
            gbc.gridy = 3;
            gbc.fill = GridBagConstraints.BOTH;
            mainP.add(descriptionPane,gbc);
        }
    }
}
