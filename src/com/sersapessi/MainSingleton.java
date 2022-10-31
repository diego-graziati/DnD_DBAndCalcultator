package com.sersapessi;

import com.sersapessi.db.Database;
import com.sersapessi.models.CloseWeaponsModel;
import com.sersapessi.models.EnchantmentModel;
import com.sersapessi.models.LongWeaponsModel;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class MainSingleton {

    private static MainSingleton instance;

    private MainSingleton() throws FileNotFoundException {
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

    synchronized public static MainSingleton getInstance() throws FileNotFoundException {
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
}
