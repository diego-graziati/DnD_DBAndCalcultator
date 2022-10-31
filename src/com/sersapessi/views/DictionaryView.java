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

                topMainPanel.add(sectionSelection);

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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO: ricordarsi di creare una schermata di caricamento per le operazioni logiche troppo lunghe
        if(e.getSource() == sectionSelection){
            if(sectionSelection.getSelectedIndex()==1){
                specificList = new JComboBox<>();
                specificList.setVisible(false);

                topMainPanel.add(specificList);
                System.out.println("Numero di items: "+specificList.getItemCount());
                try {
                    CloseWeaponsDictView closeWeaponsDictView = new CloseWeaponsDictView(centerPanel, specificList);
                    closeWeaponsDictView.run();
                } catch (FileNotFoundException ex) {
                    System.out.println("File not found");
                    throw new RuntimeException(ex);
                }
                SwingUtilities.updateComponentTreeUI(topMainPanel);
                System.out.println("Arma da vicino");
            }else if(sectionSelection.getSelectedIndex()==2){
                specificList = new JComboBox<>();
                specificList.setVisible(false);

                topMainPanel.add(specificList);
                System.out.println("Numero di items: "+specificList.getItemCount());
                LongWeaponsDictView longWeaponsDictView = new LongWeaponsDictView(centerPanel, specificList);
                try {
                    longWeaponsDictView.run();
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                SwingUtilities.updateComponentTreeUI(topMainPanel);
                System.out.println("Arma da distanza");
            }else if(sectionSelection.getSelectedIndex()==3){
                specificList = new JComboBox<>();
                specificList.setVisible(false);

                topMainPanel.add(specificList);
                System.out.println("Numero di items: "+specificList.getItemCount());
                EnchantmentsDictView enchantmentsDictView = new EnchantmentsDictView(centerPanel, topMainPanel, specificList);
                try {
                    enchantmentsDictView.run();
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                System.out.println("Incantesimo");
                //Incantesimo
            }else if(sectionSelection.getSelectedIndex()==4){
                specificList.removeAllItems();
                ArmorDictView armorDictView = new ArmorDictView(centerPanel, topMainPanel);
                //Armatura
            }else if(sectionSelection.getSelectedIndex()==5){
                specificList.removeAllItems();
                BombsDictView bombsDictView = new BombsDictView(centerPanel, topMainPanel);
                //Bombe
            }else if(sectionSelection.getSelectedIndex()==6){
                specificList.removeAllItems();
                EssenceDictView essenceDictView = new EssenceDictView(centerPanel, topMainPanel);
                //Essenze
            }else if(sectionSelection.getSelectedIndex()==7){
                specificList.removeAllItems();
                StatusDictView statusDictView = new StatusDictView(centerPanel, topMainPanel);
                //Stati
            }else if(sectionSelection.getSelectedIndex()==8){
                specificList.removeAllItems();
                EnemyDictView enemyDictView = new EnemyDictView(centerPanel, topMainPanel, true);
                //Nemici umani
            }else if(sectionSelection.getSelectedIndex()==9){
                specificList.removeAllItems();
                EnemyDictView enemyDictView = new EnemyDictView(centerPanel, topMainPanel, false);
                //Nemici bestie
            }else if(sectionSelection.getSelectedIndex()==10){
                specificList.removeAllItems();
                NPCsDictView npCsDictView = new NPCsDictView(centerPanel, topMainPanel);
                //Gli NPC
            }
        }
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
