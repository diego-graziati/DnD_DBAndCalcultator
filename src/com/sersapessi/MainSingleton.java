package com.sersapessi;

import com.sersapessi.db.Database;
import com.sersapessi.models.*;
import com.sersapessi.utilities.LoadingScreensFacility;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class MainSingleton {

    private static MainSingleton instance;

    private MainSingleton() throws IOException {
        sections = new ArrayList<>();

        sections.add("Arma da vicino");
        sections.add("Arma da distanza");
        sections.add("Incantesimo");
        sections.add("Armatura");
        sections.add("Bombe");
        sections.add("Essenze");
        sections.add("Stati");
        sections.add("Nemici umani");
        sections.add("Nemici bestie");
        sections.add("Gli NPC");

        db = new Database();
    }

    synchronized public static MainSingleton getInstance() throws IOException {
        if(instance==null){
            instance=new MainSingleton();
        }
        return instance;
    }

    public ArrayList<String> sections;
    public Database db;
    public ArrayList<CloseWeaponsModel> closeWeapons;
    public ArrayList<LongWeaponsModel> longWeapons;
    public ArrayList<EnchantmentModel> enchantments;
    public ArrayList<ArmorModel> armors;
    public ArrayList<BombModel> bombs;
    public ArrayList<EssenceModel> essences;
    public ArrayList<StatusModel> status;
    public ArrayList<HumanEnemyModel> humanEnemies;
    public ArrayList<BeastEnemyModel> beastEnemies;
    public ArrayList<NPCsModel> npcs;

    public LoadingScreensFacility loadingScreensFacility;

    //Frames
    public JFrame applicationFrame;                         //The application frame. Mainly for dispose operations
}
