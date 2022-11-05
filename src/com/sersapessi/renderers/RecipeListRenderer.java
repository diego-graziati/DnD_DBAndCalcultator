package com.sersapessi.renderers;

import com.sersapessi.MainSingleton;
import com.sersapessi.models.EssenceModel;
import com.sersapessi.models.RecipeModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class RecipeListRenderer extends JPanel implements ListCellRenderer, MouseListener {

    private ArrayList<RecipeModel> recipe;
    private JLabel itemLabel;

    public RecipeListRenderer(ArrayList<RecipeModel> recipe){
        this.recipe=recipe;
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

        itemLabel = new JLabel(recipe.get(index).getItem().getName());
        itemLabel.addMouseListener(this);
        itemLabel.setForeground(new Color(0,0,238));

        JLabel itemDescriptionL = new JLabel(": "+recipe.get(index).getItem().getDescription());

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setLayout(new BoxLayout(scrollPane,BoxLayout.X_AXIS));
        scrollPane.add(itemLabel);
        scrollPane.add(itemDescriptionL);

        return this;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getSource() == itemLabel){
            Color previousColor = itemLabel.getForeground();
            itemLabel.setForeground(new Color(previousColor.getRed(),previousColor.getGreen(), previousColor.getBlue()).brighter());
            System.out.println("Label dell'item premuta");
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getSource() == itemLabel){
            itemLabel.setForeground(new Color(85,26,139));
            System.out.println("Label dell'item rilasciata");
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
