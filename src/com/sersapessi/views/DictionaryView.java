package com.sersapessi.views;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.IOException;
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
    private BombsDictView bombsDictView;
    private EssenceDictView essenceDictView;
    private StatusDictView statusDictView;
    private HumanEnemyDictView humanEnemyDictView;
    private BeastEnemyDictView beastEnemyDictView;
    private NPCsDictView npCsDictView;

    public DictionaryView(JPanel rootP){
        this.rootP=rootP;
    }

    public void run() throws IOException {
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
        bombsDictView = new BombsDictView(centerPanel);
        essenceDictView = new EssenceDictView(centerPanel);
        statusDictView = new StatusDictView(centerPanel);
        humanEnemyDictView = new HumanEnemyDictView(centerPanel);
        beastEnemyDictView = new BeastEnemyDictView(centerPanel);
        npCsDictView = new NPCsDictView(centerPanel);
    }

    private void clearView(){
        centerPanel.removeAll();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO: ricordarsi di creare una schermata di caricamento per le operazioni logiche troppo lunghe
        //TODO: controllare strana eccezione che avviene quando, selezionata una sezione, si passa ad un'altra con dati. Se si riclicca la stessa sezione, questa eccezione sembra non verificarsi di nuovo. Possibile lentezza rispetto alla grafica?
        if(e.getSource() == sectionSelection){
            clearView();                //Libero il panel da qualsiasi elemento

            if(specificList.getItemCount() != 0){
                specificList.removeAllItems();
            }

            int sectionIndex = sectionSelection.getSelectedIndex();

            switch(sectionIndex){
                case 1:
                    try {
                        if(MainSingleton.getInstance().closeWeapons.size()>0){
                            System.out.println("(DictView - 1)Si è sull'Event Dispatch Thread? "+SwingUtilities.isEventDispatchThread());
                            for(int i=0; i<MainSingleton.getInstance().closeWeapons.size(); i++){
                                specificList.addItem(MainSingleton.getInstance().closeWeapons.get(i).getName());
                            }
                            specificList.setSelectedIndex(0);
                            closeWeaponsDictView.run(specificList.getSelectedIndex());
                        }else{
                            specificList.addItem("Nessuna arma da vicino trovata");
                        }
                    } catch (IOException ex) {
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
                            specificList.addItem("Nessuna arma a distanza trovata");
                        }
                    } catch (IOException ex) {
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
                    } catch (IOException ex) {
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
                            specificList.addItem("Nessuna armatura trovata");
                        }
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
                case 5:
                    try {
                        if(MainSingleton.getInstance().bombs.size()>0){
                            for(int i=0;i<MainSingleton.getInstance().bombs.size();i++){
                                specificList.addItem(MainSingleton.getInstance().bombs.get(i).getName());
                            }
                            specificList.setSelectedIndex(0);
                            bombsDictView.run(specificList.getSelectedIndex());
                        }else{
                            specificList.addItem("Nessuna bomba trovato");
                        }
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
                case 6:
                    try {
                        if(MainSingleton.getInstance().essences.size()>0){
                            for(int i=0;i<MainSingleton.getInstance().essences.size();i++){
                                specificList.addItem(MainSingleton.getInstance().essences.get(i).getName());
                            }
                            specificList.setSelectedIndex(0);
                            essenceDictView.run(specificList.getSelectedIndex());
                        }else{
                            specificList.addItem("Nessuna essenza trovato");
                        }
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
                case 7:
                    try {
                        if(MainSingleton.getInstance().status.size()>0){
                            for(int i=0;i<MainSingleton.getInstance().status.size();i++){
                                specificList.addItem(MainSingleton.getInstance().status.get(i).getName());
                            }
                            specificList.setSelectedIndex(0);
                            statusDictView.run(specificList.getSelectedIndex());
                        }else{
                            specificList.addItem("Nessuno stato trovato");
                        }
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
                case 8:
                    try {
                        if(MainSingleton.getInstance().humanEnemies.size()>0){
                            for(int i=0;i<MainSingleton.getInstance().humanEnemies.size();i++){
                                specificList.addItem(MainSingleton.getInstance().humanEnemies.get(i).getName());
                            }
                            specificList.setSelectedIndex(0);
                            humanEnemyDictView.run(specificList.getSelectedIndex());
                        }else{
                            specificList.addItem("Nessun nemico umano trovato");
                        }
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
                case 9:
                    try {
                        if(MainSingleton.getInstance().beastEnemies.size()>0){
                            for(int i=0;i<MainSingleton.getInstance().beastEnemies.size();i++){
                                specificList.addItem(MainSingleton.getInstance().beastEnemies.get(i).getName());
                            }
                            specificList.setSelectedIndex(0);
                            beastEnemyDictView.run(specificList.getSelectedIndex());
                        }else{
                            specificList.addItem("Nessuna bestia trovata");
                        }
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
                case 10:
                    try {
                        if(MainSingleton.getInstance().npcs.size()>0){
                            for(int i=0;i<MainSingleton.getInstance().npcs.size();i++){
                                specificList.addItem(MainSingleton.getInstance().npcs.get(i).getName());
                            }
                            specificList.setSelectedIndex(0);
                            npCsDictView.run(specificList.getSelectedIndex());
                        }else{
                            specificList.addItem("Nessun NPC trovato");
                        }
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
            }
            specificList.setVisible(true);
        }else if(e.getSource() == specificList){
            clearView();                            //Libero il panel da qualsiasi elemento
            int sectionIndex = sectionSelection.getSelectedIndex();

            switch(sectionIndex){
                case 1:
                    try {
                        System.out.println("(DictView - 2)Si è sull'Event Dispatch Thread? "+SwingUtilities.isEventDispatchThread());
                        closeWeaponsDictView.run(specificList.getSelectedIndex());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
                case 2:
                    try {
                        longWeaponsDictView.run(specificList.getSelectedIndex());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
                case 3:
                    try {
                        enchantmentsDictView.run(specificList.getSelectedIndex());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
                case 4:
                    try {
                        armorDictView.run(specificList.getSelectedIndex());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
                case 5:
                    try {
                        bombsDictView.run(specificList.getSelectedIndex());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
                case 6:
                    try {
                        essenceDictView.run(specificList.getSelectedIndex());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
                case 7:
                    try {
                        statusDictView.run(specificList.getSelectedIndex());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
                case 8:
                    try {
                        humanEnemyDictView.run(specificList.getSelectedIndex());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
                case 9:
                    try {
                        beastEnemyDictView.run(specificList.getSelectedIndex());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
                case 10:
                    try {
                        npCsDictView.run(specificList.getSelectedIndex());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
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
