package com.sersapessi;

import com.sersapessi.utilities.LoadingScreensFacility;

import javax.swing.*;
import java.io.IOException;

public class Main {

    private static MainFrame mainFrame;

    public static void main(String[] args) throws IOException {
        //TODO:needs to reload specific data if it's found that they've been changed
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch (Exception ignored){}
        //The loading screen frame. I want only one graphical screen loader, not thousands. It would be a mess.
        JFrame loadingScreenFrame = new JFrame();
        LoadingScreensFacility loadingScreensFacility = new LoadingScreensFacility(loadingScreenFrame);
            new SwingWorker<>(){
                @Override
                protected Object doInBackground() throws Exception {

                    loadingScreensFacility.startIndefinite(false);
                    MainSingleton.getInstance().db.getCloseWeapons();
                    MainSingleton.getInstance().db.getLongWeapons();
                    MainSingleton.getInstance().db.getEnchantments();
                    MainSingleton.getInstance().db.getArmors();
                    MainSingleton.getInstance().db.getBombs();
                    MainSingleton.getInstance().db.getEssence();
                    MainSingleton.getInstance().db.getStatus();
                    MainSingleton.getInstance().db.getHumanEnemies();
                    MainSingleton.getInstance().db.getBeastEnemies();
                    MainSingleton.getInstance().db.getNPCs();

                    loadingScreensFacility.controlledStopIndefinite(false);
                    return null;
                }
            }.execute();
        MainSingleton.getInstance().loadingScreensFacility = loadingScreensFacility;
        mainFrame = new MainFrame();
        mainFrame.run();
    }
}