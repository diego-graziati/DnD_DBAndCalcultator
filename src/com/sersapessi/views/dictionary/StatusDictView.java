package com.sersapessi.views.dictionary;

import com.sersapessi.MainSingleton;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class StatusDictView {
    private JPanel mainP;
    private JTextArea descriptionArea;
    private JScrollPane descriptionPane;

    public StatusDictView(JPanel mainP){
        this.mainP=mainP;
    }

    public void run(int index) throws FileNotFoundException {
        GridBagConstraints gbc = new GridBagConstraints();

        if(MainSingleton.getInstance().status.size()>0){
            if(descriptionArea==null){
                descriptionArea = new JTextArea(MainSingleton.getInstance().status.get(index).getDescription());
                descriptionPane = new JScrollPane(descriptionArea);
            }else{
                descriptionArea.setText(MainSingleton.getInstance().status.get(index).getDescription());
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
