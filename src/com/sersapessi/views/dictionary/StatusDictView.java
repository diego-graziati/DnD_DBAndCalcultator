package com.sersapessi.views.dictionary;

import com.sersapessi.MainSingleton;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class StatusDictView {
    private JPanel mainP;
    private JTextArea descriptionArea;
    private JScrollPane descriptionPane;

    public StatusDictView(JPanel mainP){
        this.mainP=mainP;
    }

    public void run(int index) throws IOException {
        GridBagConstraints gbc = new GridBagConstraints();

        if(MainSingleton.getInstance().status.size()>0){
            //TODO: da tenere d'occhio anche in tutte le altre JTextArea. Non si puliscono: se il testo inserito è più corto di quello precedente, la parte precedente si glitcherà e rimarrà visibile, ma non selezionabile.
            if(descriptionArea==null){
                descriptionArea = new JTextArea(MainSingleton.getInstance().status.get(index).getDescription());
                descriptionArea.setLineWrap(true);
                descriptionArea.setEditable(false);
                descriptionPane = new JScrollPane(descriptionArea);
            }else{
                descriptionArea.selectAll();
                descriptionArea.replaceSelection(MainSingleton.getInstance().status.get(index).getDescription());
            }

            gbc.insets=new Insets(10,10,10,10);
            gbc.gridy=0;
            gbc.gridx=0;
            gbc.weighty=1.0;
            gbc.weightx=1.0;
            gbc.fill=GridBagConstraints.BOTH;
            mainP.add(descriptionPane,gbc);
        }
    }
}
