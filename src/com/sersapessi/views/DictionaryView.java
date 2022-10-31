package com.sersapessi.views;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.Serial;

import com.sersapessi.MainSingleton;
import com.sersapessi.views.dictionary.*;

//I componenti in questa classe sono responsive
public class DictionaryView implements ActionListener, MouseListener
{
    private JPanel rootP;
    private JPanel modesColumnP;
    private JPanel currentView;
    private JPanel otherView;
    private JPanel mainPanel;
    private JPanel centerPanel;
    private JPanel topMainPanel;
    private JComboBox<String> sectionSelection;
    private JComboBox<String> specificList;

    //General Views
    private CloseWeaponsDictView closeWeaponsDictView;
    private LongWeaponsDictView longWeaponsDictView;
    private EnchantmentsDictView enchantmentsDictView;
    private ArmorDictView armorDictView;

    public DictionaryView(JPanel rootP){
        this.rootP=rootP;
    }

    public void run() throws FileNotFoundException {
        GridBagConstraints gbc = new GridBagConstraints();

            modesColumnP = new JPanel();
            modesColumnP.setLayout(new GridBagLayout());
            modesColumnP.setBorder(new LineBorder(Color.RED, 2, false));

                currentView = new JPanel();
                    JLabel label = new JLabel("Dictionary");
                currentView.add(label);
                currentView.setBorder(new LineBorder(Color.GREEN, 2, false));
                otherView = new JPanel();
                    JLabel label2 = new JLabel("Add");
                otherView.add(label2);
                otherView.addMouseListener(this);
                otherView.setBorder(new LineBorder(Color.BLUE, 2, false));

                JPanel space = new JPanel();
                space.setBorder(new LineBorder(Color.BLACK, 2, false));

            gbc.gridx=0;
            gbc.gridy=0;
            gbc.weightx=1.0;
            gbc.weighty=0.05;
            gbc.fill=GridBagConstraints.BOTH;
            modesColumnP.add(currentView,gbc);
            gbc.gridx=0;
            gbc.gridy=1;
            gbc.fill=GridBagConstraints.BOTH;
            modesColumnP.add(otherView,gbc);
            gbc.gridx=0;
            gbc.gridy=2;
            gbc.weighty=0.90;
            gbc.fill=GridBagConstraints.BOTH;
            modesColumnP.add(space,gbc);

            mainPanel = new JPanel();
            mainPanel.setLayout(new BorderLayout());
                topMainPanel = new JPanel();
                topMainPanel.setLayout(new GridLayout(1,2));

                    sectionSelection = new JComboBox<>();
                    //Setto un valore di default non più ricliccabile una volta scelta un'altra sezione
                    sectionSelection.setModel(new DefaultComboBoxModel<String>() {
                        @Serial
                        private static final long serialVersionUID = 1L;
                        boolean selectionAllowed = true;

                        @Override
                        public void setSelectedItem(Object anObject) {
                            if (!"Seleziona una sezione".equals(anObject)) {
                                super.setSelectedItem(anObject);
                            } else if (selectionAllowed) {
                                // Allow this just once
                                selectionAllowed = false;
                                super.setSelectedItem(anObject);
                            }
                        }
                    });

                    sectionSelection.addItem("Seleziona una sezione");
                    for(int i=0; i<MainSingleton.getInstance().sections.size(); i++){
                        sectionSelection.addItem(MainSingleton.getInstance().sections.get(i));
                    }
                    sectionSelection.addActionListener(this);

                    specificList = new JComboBox<>();
                    specificList.setVisible(false);
                    specificList.addActionListener(this);
                topMainPanel.add(sectionSelection);
                topMainPanel.add(specificList);

                centerPanel = new JPanel();
                    centerPanel.setLayout(new GridBagLayout());
            mainPanel.add(topMainPanel, BorderLayout.NORTH);
            mainPanel.add(centerPanel,BorderLayout.CENTER);

        gbc.gridx=0;
        gbc.gridy=0;
        gbc.gridheight=5;
        gbc.weightx=0.10;
        gbc.weighty=1.0;
        gbc.fill=GridBagConstraints.BOTH;
        rootP.add(modesColumnP, gbc);
        gbc.gridx=1;
        gbc.gridy=0;
        gbc.weightx=0.90;
        gbc.fill=GridBagConstraints.BOTH;
        mainPanel.setBorder(new LineBorder(Color.CYAN, 2, false));
        rootP.setBorder(new LineBorder(Color.ORANGE, 2, false));
        rootP.add(mainPanel, gbc);

        //Views Initialization
        closeWeaponsDictView = new CloseWeaponsDictView(centerPanel);
        longWeaponsDictView = new LongWeaponsDictView(centerPanel);
        enchantmentsDictView = new EnchantmentsDictView(centerPanel);
        armorDictView = new ArmorDictView(centerPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO: ricordarsi di creare una schermata di caricamento per le operazioni logiche troppo lunghe
        if(e.getSource() == sectionSelection){

            if(specificList.getItemCount() != 0){
                specificList.removeAllItems();
            }

            int sectionIndex = sectionSelection.getSelectedIndex();

            switch(sectionIndex){
                case 1:
                    try {
                        if(MainSingleton.getInstance().closeWeapons.size()>0){
                            for(int i=0; i<MainSingleton.getInstance().closeWeapons.size(); i++){
                                specificList.addItem(MainSingleton.getInstance().closeWeapons.get(i).getName());
                            }
                            specificList.setSelectedIndex(0);
                            closeWeaponsDictView.run(specificList.getSelectedIndex());
                        }else{
                            specificList.addItem("Nessun'arma da vicino trovata");
                        }
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
                case 2:
                    try {
                        if(MainSingleton.getInstance().longWeapons.size()>0){
                            for(int i=0; i<MainSingleton.getInstance().longWeapons.size(); i++){
                                specificList.addItem(MainSingleton.getInstance().longWeapons.get(i).getName());
                            }
                            specificList.setSelectedIndex(0);
                            longWeaponsDictView.run(specificList.getSelectedIndex());
                        }else{
                            specificList.addItem("Nessun'arma a distanza trovata");
                        }
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
                case 3:
                    try {
                        if(MainSingleton.getInstance().enchantments.size()>0){
                            for(int i=0;i<MainSingleton.getInstance().enchantments.size();i++){
                                specificList.addItem(MainSingleton.getInstance().enchantments.get(i).getName());
                            }
                            specificList.setSelectedIndex(0);
                            enchantmentsDictView.run(specificList.getSelectedIndex());
                        }else{
                            specificList.addItem("Nessun incantesimo trovato");
                        }
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
                case 4:
                    try {
                        if(MainSingleton.getInstance().armors.size()>0){
                            for(int i=0;i<MainSingleton.getInstance().armors.size();i++){
                                specificList.addItem(MainSingleton.getInstance().armors.get(i).getName());
                            }
                            specificList.setSelectedIndex(0);
                            armorDictView.run(specificList.getSelectedIndex());
                        }else{
                            specificList.addItem("Nessun incantesimo trovato");
                        }
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    break;
                case 10:
                    break;
            }
            specificList.setVisible(true);
        }else if(e.getSource() == specificList){
            int sectionIndex = sectionSelection.getSelectedIndex();

            switch(sectionIndex){
                case 1:
                    try {
                        closeWeaponsDictView.run(specificList.getSelectedIndex());
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
                case 2:
                    try {
                        longWeaponsDictView.run(specificList.getSelectedIndex());
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
                case 3:
                    try {
                        enchantmentsDictView.run(specificList.getSelectedIndex());
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
                case 4:
                    try {
                        armorDictView.run(specificList.getSelectedIndex());
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    break;
                case 10:
                    break;
            }
        }
        SwingUtilities.updateComponentTreeUI(topMainPanel);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getSource() == otherView){
            System.out.println("Other View");
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getSource() == otherView){
            System.out.println("Cambio a Other View");
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
