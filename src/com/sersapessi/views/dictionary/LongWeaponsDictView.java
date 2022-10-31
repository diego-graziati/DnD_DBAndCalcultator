package com.sersapessi.views.dictionary;

import com.sersapessi.Main;
import com.sersapessi.MainSingleton;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class LongWeaponsDictView implements ActionListener {
    private JPanel mainP;
    private boolean longViewMode;
    private JComboBox<String> weaponsList;
    private JLabel throwableL;
    private JLabel costL;
    private JScrollPane descriptionPane;
    private JTextArea descriptionArea;
    private JList<String> damageList;

    public LongWeaponsDictView(JPanel mainP, JComboBox<String> weaponsList){
        this.mainP = mainP;
        this.weaponsList = weaponsList;
    }

    //TODO: controllare la moltiplicazione delle combobox quando ci si sposta da una modalità all'altra. Potrebbe essere un problema correlato alla checkbox di stato
    //Da usare se si usa direttamente la classe
    public void run() throws FileNotFoundException {
        longViewMode=true;
        GridBagConstraints gbc = new GridBagConstraints();

        //TODO: schermata di caricamento se dovesse impiegarci troppo
        if(MainSingleton.getInstance().longWeapons.size()>0){
            for(int i=0; i<MainSingleton.getInstance().longWeapons.size(); i++){
                weaponsList.addItem(MainSingleton.getInstance().longWeapons.get(i).getName());
            }
        }else{
            weaponsList.addItem("Nessuna arma trovata");
        }
        weaponsList.addActionListener(this);
        weaponsList.setVisible(true);

        if(MainSingleton.getInstance().longWeapons.size()>0){
            int index = weaponsList.getSelectedIndex();

            costL = new JLabel("Costo arma: "+ MainSingleton.getInstance().longWeapons.get(index).getCost());
            descriptionArea = new JTextArea(MainSingleton.getInstance().longWeapons.get(index).getDescription());
            descriptionArea.setEditable(false);
            descriptionPane = new JScrollPane(descriptionArea);

            damageList = new JList<>();
            //TODO: schermata di caricamento se dovesse impiegarci troppo
            for (int i=0; i<MainSingleton.getInstance().longWeapons.get(index).getDice().size(); i++){
                StringBuilder str = new StringBuilder(MainSingleton.getInstance().longWeapons.get(index).getDice().get(i).getNumOfDice()+
                        MainSingleton.getInstance().longWeapons.get(index).getDice().get(i).getDice()+" ");
                for(int j=0; i<MainSingleton.getInstance().longWeapons.get(index).getDice().get(i).getTypeOfDamage().size(); j++){
                    str.append(MainSingleton.getInstance().longWeapons.get(index).getDice().get(i).getTypeOfDamage().get(j).getName());
                    str.append(" ");
                }
                if(MainSingleton.getInstance().longWeapons.get(index).getDice().get(i).getDescription()!=null){
                    str.append(MainSingleton.getInstance().longWeapons.get(index).getDice().get(i).getDescription());
                }
                damageList.add(new JLabel(str.toString()));
            }

            gbc.gridy=0;
            gbc.gridx=0;
            gbc.weightx=1;
            gbc.weighty=0.20;
            gbc.insets = new Insets(10,10,10,10);
            gbc.fill=GridBagConstraints.BOTH;
            costL.setBorder(new LineBorder(Color.CYAN, 2, false));
            mainP.add(costL,gbc);
            gbc.gridx=0;
            gbc.gridy=1;
            gbc.weighty=0.40;
            gbc.gridwidth=2;
            gbc.fill=GridBagConstraints.BOTH;
            //descriptionPane.setBorder(new LineBorder(Color.GREEN, 2, false));
            mainP.add(descriptionPane,gbc);
            gbc.gridy=2;
            gbc.fill=GridBagConstraints.BOTH;
            //damageList.setBorder(new LineBorder(Color.BLACK,2,false));
            mainP.add(damageList,gbc);
        }

        //Mostra le modifiche avvenute nel Component specificato (in questo caso topP e mainP).
        //Obbligatorio se una classe lavora sul componente grafico di un'altra classe, come in questo caso.
        SwingUtilities.updateComponentTreeUI(mainP);
    }
    //Da usare se si usa la classe CloseWeaponsDictView per accedere al metodo
    public void runClose() throws FileNotFoundException {
        longViewMode=false;
        GridBagConstraints gbc = new GridBagConstraints();

        //TODO: schermata di caricamento se dovesse impiegarci troppo
        if(MainSingleton.getInstance().closeWeapons.size()>0){
            for(int i=0; i<MainSingleton.getInstance().closeWeapons.size(); i++){
                weaponsList.addItem(MainSingleton.getInstance().closeWeapons.get(i).getName());
            }
        }else{
            weaponsList.addItem("Nessuna arma trovata");
        }
        weaponsList.addActionListener(this);
        weaponsList.setVisible(true);

        if(MainSingleton.getInstance().closeWeapons.size()>0){
            int index = weaponsList.getSelectedIndex();

            if(MainSingleton.getInstance().closeWeapons.get(index).canBeThrown()){
                throwableL = new JLabel("Può essere lanciata? Sì");
            }else{
                throwableL = new JLabel("Può essere lanciata? No");
            }

            costL = new JLabel("Costo arma: "+ MainSingleton.getInstance().closeWeapons.get(index).getCost());
            descriptionArea = new JTextArea(MainSingleton.getInstance().closeWeapons.get(index).getDescription());
            descriptionArea.setEditable(false);
            descriptionPane = new JScrollPane(descriptionArea);

            damageList = new JList<>();
            //TODO: schermata di caricamento se dovesse impiegarci troppo
            for (int i=0; i<MainSingleton.getInstance().closeWeapons.get(index).getDice().size(); i++){
                StringBuilder str = new StringBuilder(MainSingleton.getInstance().closeWeapons.get(index).getDice().get(i).getNumOfDice()+
                        MainSingleton.getInstance().closeWeapons.get(index).getDice().get(i).getDice()+" ");
                for(int j=0; i<MainSingleton.getInstance().closeWeapons.get(index).getDice().get(i).getTypeOfDamage().size(); j++){
                    str.append(MainSingleton.getInstance().closeWeapons.get(index).getDice().get(i).getTypeOfDamage().get(j).getName());
                    str.append(" ");
                }
                if(MainSingleton.getInstance().closeWeapons.get(index).getDice().get(i).getDescription()!=null){
                    str.append(MainSingleton.getInstance().closeWeapons.get(index).getDice().get(i).getDescription());
                }
                damageList.add(new JLabel(str.toString()));
            }

            gbc.gridy=0;
            gbc.gridx=0;
            gbc.weightx=0.5;
            gbc.weighty=0.20;
            gbc.insets = new Insets(10,10,10,10);
            gbc.fill=GridBagConstraints.BOTH;
            mainP.add(throwableL,gbc);
            gbc.gridx=1;
            gbc.fill=GridBagConstraints.BOTH;
            costL.setBorder(new LineBorder(Color.CYAN, 2, false));
            mainP.add(costL,gbc);
            gbc.gridx=0;
            gbc.gridy=1;
            gbc.weighty=0.40;
            gbc.gridwidth=2;
            gbc.fill=GridBagConstraints.BOTH;
            //descriptionPane.setBorder(new LineBorder(Color.GREEN, 2, false));
            mainP.add(descriptionPane,gbc);
            gbc.gridy=2;
            gbc.fill=GridBagConstraints.BOTH;
            //damageList.setBorder(new LineBorder(Color.BLACK,2,false));
            mainP.add(damageList,gbc);
        }

        //Mostra le modifiche avvenute nel Component specificato (in questo caso topP e mainP).
        //Obbligatorio se una classe lavora sul componente grafico di un'altra classe, come in questo caso.
        SwingUtilities.updateComponentTreeUI(mainP);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == weaponsList){
            //Aggiorno tutto con i dati dell'arma selezionata
            int index = weaponsList.getSelectedIndex();

            if(!longViewMode){
                try {
                    if(MainSingleton.getInstance().closeWeapons.size()>0){
                        if(MainSingleton.getInstance().closeWeapons.get(index).canBeThrown()){
                            throwableL.setText("Può essere lanciata? Sì");
                        }else{
                            throwableL.setText("Può essere lanciata? No");
                        }

                        costL.setText("Costo arma: "+ MainSingleton.getInstance().closeWeapons.get(index).getCost());
                        descriptionArea.setText(MainSingleton.getInstance().closeWeapons.get(index).getDescription());

                        damageList = new JList<>();
                        //TODO: schermata di caricamento se dovesse impiegarci troppo
                        for (int i=0; i<MainSingleton.getInstance().closeWeapons.get(index).getDice().size(); i++){
                            StringBuilder str = new StringBuilder(MainSingleton.getInstance().closeWeapons.get(index).getDice().get(i).getNumOfDice()+
                                    MainSingleton.getInstance().closeWeapons.get(index).getDice().get(i).getDice()+" ");
                            for(int j=0; i<MainSingleton.getInstance().closeWeapons.get(index).getDice().get(i).getTypeOfDamage().size(); j++){
                                str.append(MainSingleton.getInstance().closeWeapons.get(index).getDice().get(i).getTypeOfDamage().get(j).getName());
                                str.append(" ");
                            }
                            if(MainSingleton.getInstance().closeWeapons.get(index).getDice().get(i).getDescription()!=null){
                                str.append(MainSingleton.getInstance().closeWeapons.get(index).getDice().get(i).getDescription());
                            }
                            damageList.add(new JLabel(str.toString()));
                        }
                    }else{
                        weaponsList.addItem("Nessuna arma trovata");
                    }
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }else{
                try {
                    if(MainSingleton.getInstance().longWeapons.size()>0){
                        costL.setText("Costo arma: "+ MainSingleton.getInstance().longWeapons.get(index).getCost());
                        descriptionArea.setText(MainSingleton.getInstance().longWeapons.get(index).getDescription());

                        damageList = new JList<>();
                        //TODO: schermata di caricamento se dovesse impiegarci troppo
                        for (int i=0; i<MainSingleton.getInstance().longWeapons.get(index).getDice().size(); i++){
                            StringBuilder str = new StringBuilder(MainSingleton.getInstance().longWeapons.get(index).getDice().get(i).getNumOfDice()+
                                    MainSingleton.getInstance().longWeapons.get(index).getDice().get(i).getDice()+" ");
                            for(int j=0; i<MainSingleton.getInstance().longWeapons.get(index).getDice().get(i).getTypeOfDamage().size(); j++){
                                str.append(MainSingleton.getInstance().longWeapons.get(index).getDice().get(i).getTypeOfDamage().get(j).getName());
                                str.append(" ");
                            }
                            if(MainSingleton.getInstance().longWeapons.get(index).getDice().get(i).getDescription()!=null){
                                str.append(MainSingleton.getInstance().longWeapons.get(index).getDice().get(i).getDescription());
                            }
                            damageList.add(new JLabel(str.toString()));
                        }
                    }else{
                        weaponsList.addItem("Nessuna arma trovata");
                    }
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }
}
