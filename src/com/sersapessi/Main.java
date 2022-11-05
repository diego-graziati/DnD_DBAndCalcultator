package com.sersapessi;

import com.sersapessi.views.DictionaryView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class Main {

    private static MainFrame mainFrame;

    public static void main(String[] args) throws FileNotFoundException {
        //TODO:needs to reload specific data if it's found that they've been changed
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch (Exception ignored){}
        MainSingleton.getInstance();
        MainSingleton.getInstance().db.getCloseWeapons();
        MainSingleton.getInstance().db.getLongWeapons();
        MainSingleton.getInstance().db.getEnchantments();
        MainSingleton.getInstance().db.getArmors();
        MainSingleton.getInstance().db.getBombs();
        MainSingleton.getInstance().db.getEssence();

        mainFrame = new MainFrame();
        mainFrame.run();
    }
}