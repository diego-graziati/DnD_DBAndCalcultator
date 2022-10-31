package com.sersapessi;

import com.sersapessi.views.DictionaryView;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class Main {
    private static JPanel rootP;

    public static void run() throws FileNotFoundException {
        rootP = new JPanel();
        rootP.setLayout(new GridBagLayout());

        DictionaryView dictView = new DictionaryView(rootP);
        dictView.run();

        JFrame mainFrame = new JFrame();
        mainFrame.add(rootP);
        mainFrame.pack();
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }

    public static void main(String[] args) throws FileNotFoundException {
        //TODO:needs to reload specific data if it's found that they've been changed
        MainSingleton.getInstance();
        MainSingleton.getInstance().db.getCloseWeapons();
        MainSingleton.getInstance().db.getLongWeapons();
        MainSingleton.getInstance().db.getEnchantments();
        run();
    }
}