package com.sersapessi.views.dictionary;

import com.sersapessi.MainSingleton;
import com.sersapessi.models.RecipeModel;
import com.sersapessi.renderers.RecipeListRenderer;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class EssenceDictView {
    private JPanel mainP;
    private JLabel costL;
    private JList<String> effectsList;
    private JList<RecipeModel> recipe;
    private JTextArea descriptionArea;
    private JScrollPane descriptionPane;

    public EssenceDictView(JPanel mainP){
        this.mainP=mainP;
    }

    public void run(int index) throws FileNotFoundException {
        GridBagConstraints gbc = new GridBagConstraints();

        if(MainSingleton.getInstance().essences.size()>0){
            if(costL==null){
                costL = new JLabel("Costo singola essenza: "+MainSingleton.getInstance().essences.get(index).getCost());
            }else{
                costL.setText("Costo singola essenza: "+MainSingleton.getInstance().essences.get(index).getCost());
            }

            effectsList = new JList<>();
            for(int i=0; i<MainSingleton.getInstance().essences.get(index).getEffects().size(); i++){
                effectsList.add(new JLabel("- "+MainSingleton.getInstance().essences.get(index).getEffects().get(i).getDescription()));
            }

            recipe = new JList<>((RecipeModel[]) MainSingleton.getInstance().essences.get(index).getRecipe().toArray());
            recipe.setCellRenderer(new RecipeListRenderer(MainSingleton.getInstance().essences.get(index).getRecipe()));

            if(descriptionArea==null){
                descriptionArea = new JTextArea(MainSingleton.getInstance().essences.get(index).getDescription());
                descriptionPane = new JScrollPane(descriptionArea);
            }else{
                descriptionArea.setText(MainSingleton.getInstance().essences.get(index).getDescription());
            }

            gbc.insets = new Insets(10,10,10,10);
            gbc.gridy=0;
            gbc.gridx=0;
            gbc.weighty=0.10;
            gbc.weightx=1.0;
            gbc.fill=GridBagConstraints.BOTH;
            mainP.add(costL,gbc);
            gbc.gridy=1;
            gbc.weighty=0.30;
            gbc.fill=GridBagConstraints.BOTH;
            mainP.add(effectsList,gbc);
            gbc.gridy=2;
            gbc.fill=GridBagConstraints.BOTH;
            mainP.add(recipe,gbc);
            gbc.gridy=3;
            gbc.fill=GridBagConstraints.BOTH;
            mainP.add(descriptionPane,gbc);
        }
    }
}
