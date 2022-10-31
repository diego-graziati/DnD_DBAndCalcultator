package com.sersapessi.db;

import com.sersapessi.MainSingleton;
import com.sersapessi.models.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Database {
    private final String pathToDb = "resource/dbs/MainDB.db";
    private Connection conn;

    public Database() throws FileNotFoundException {
        File dbFile = new File(pathToDb);

        if(dbFile.exists()){
            System.out.println("Database found");
        }else{
            throw new FileNotFoundException("Database non presente! Per procedere è obbligatorio scaricarlo!");
        }
    }

    public void getCloseWeapons() throws FileNotFoundException {
        MainSingleton.getInstance().closeWeapons = new ArrayList<>();
        try{
            conn = DriverManager.getConnection("jdbc:sqlite:"+pathToDb);

            String query = "SELECT * FROM closeWeapons";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while(rs.next()){
                //Ottengo tutte le Armi da vicino
                System.out.println("Id: "+rs.getInt("Id")+"\tName: "+
                                    rs.getString("Name")+"\tCanBeThrown: "+
                                    rs.getBoolean("CanBeThrown")+"\tCost: "+
                                    rs.getInt("Cost")+"\tDescription: "+rs.getString("Description"));

                String diceQuery = "SELECT Id,Dice,NumberOfDice,Description FROM dice WHERE CloseWeaponsID="+rs.getInt("Id");
                String partialTodQuery = "SELECT Name FROM typeOfDamage WHERE DiceId=";

                MainSingleton.getInstance().closeWeapons.add(new CloseWeaponsModel(rs.getString("Name"),getDiceList(diceQuery,partialTodQuery),rs.getBoolean("CanBeThrown"),rs.getInt("Cost"),rs.getString("Description")));
            }
        }catch(java.sql.SQLException ex){
            ex.printStackTrace();
        }finally {
            try{
                conn.close();
                System.out.println("Database Connection closed");
            }catch(java.sql.SQLException ex){
                System.err.println("Unable to close the Database Connection");
            }
        }
    }
    public void getLongWeapons() throws FileNotFoundException {
        MainSingleton.getInstance().longWeapons = new ArrayList<>();
        try{
            conn = DriverManager.getConnection("jdbc:sqlite:"+pathToDb);

            String query = "SELECT * FROM longWeapons";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while(rs.next()){
                //Ottengo tutte le Armi da distanza
                System.out.println("Id: "+rs.getInt("Id")+"\tName: "+rs.getString("Name")+"\tCost: "+
                        rs.getInt("Cost")+"\tDescription: "+rs.getString("Description"));

                String diceQuery = "SELECT Id,Dice,NumberOfDice,Description FROM dice WHERE CloseWeaponsID="+rs.getInt("Id");
                String partialTodQuery = "SELECT Name FROM typeOfDamage WHERE DiceId=";

                MainSingleton.getInstance().longWeapons.add(new LongWeaponsModel(rs.getString("Name"),getDiceList(diceQuery,partialTodQuery),rs.getInt("Cost"),rs.getString("Description")));
            }
        }catch(java.sql.SQLException ex){
            ex.printStackTrace();
        }finally {
            try{
                conn.close();
                System.out.println("Database Connection closed");
            }catch(java.sql.SQLException ex){
                System.err.println("Unable to close the Database Connection");
            }
        }
    }
    public void getEnchantments() throws FileNotFoundException {
        MainSingleton.getInstance().enchantments = new ArrayList<>();
        try{
            conn = DriverManager.getConnection("jdbc:sqlite:"+pathToDb);

            String query = "SELECT * FROM enchantments";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while(rs.next()){
                //Ottengo tutti gli incantesimi
                System.out.println("Id: "+rs.getInt("Id")+"\tName: "+rs.getString("Name")+"\tOrigin: "+
                        rs.getInt("Origin")+"\tScalability Parameter: "+rs.getString("ScalabilityParam")+
                        "\tLeveling Description: "+rs.getString("LevelingDesc")+"\tDescription: "+
                        rs.getString("Description"));

                String damageEnchanmentsQuery = "SELECT * FROM damageEnchantments WHERE Id="+rs.getInt("Id");

                Statement dEStmt = conn.createStatement();
                ResultSet dERs = dEStmt.executeQuery(damageEnchanmentsQuery);
                ArrayList<DamageEnchantmentModel> tempDEList = new ArrayList<>();
                while(dERs.next()){
                    tempDEList.add(new DamageEnchantmentModel(dERs.getInt("Dice"),dERs.getInt("InitDice"),dERs.getInt("FinalDice"),dERs.getBoolean("Nat"),dERs.getString("Description")));
                }
                MainSingleton.getInstance().enchantments.add(new EnchantmentModel(rs.getString("Name"),rs.getString("Origin"),rs.getString("ScalabilityParam"),rs.getString("LevelingDesc"),rs.getString("Description"),tempDEList));
            }
        }catch(java.sql.SQLException ex){
            ex.printStackTrace();
        }finally {
            try{
                conn.close();
                System.out.println("Database Connection closed");
            }catch(java.sql.SQLException ex){
                System.err.println("Unable to close the Database Connection");
            }
        }
    }
    public void getArmors() throws FileNotFoundException {
        MainSingleton.getInstance().armors = new ArrayList<>();
        try{
            conn = DriverManager.getConnection("jdbc:sqlite:"+pathToDb);
            String armorQuery = "SELECT * FROM armor";

            Statement armorStmt = conn.createStatement();
            ResultSet armorRs = armorStmt.executeQuery(armorQuery);

            while(armorRs.next()){
                MainSingleton.getInstance().armors.add(new ArmorModel(armorRs.getString("Name"),armorRs.getInt("BaseTotalCA"),armorRs.getInt("BaseCost"),armorRs.getString("Description")));
            }
        }catch(java.sql.SQLException ex) {
            ex.printStackTrace();
        }finally {
            try{
                conn.close();
                System.out.println("Database Connection closed");
            }catch(java.sql.SQLException ex){
                System.err.println("Unable to close the Database Connection");
            }
        }
    }

    //Gets the complete DiceList: it lets you specify the diceQuery and a partialTodQuery. The last one must always end referencing to the "DiceId=".
    private ArrayList<DiceModel> getDiceList(String diceQuery, String partialTodQuery){
        ArrayList<DiceModel> tempDiceList = new ArrayList<>();
        try{
            Statement diceStmt = conn.createStatement();
            ResultSet diceRs = diceStmt.executeQuery(diceQuery);

            while (diceRs.next()){

                Statement todStmt = conn.createStatement();
                ResultSet todRs = todStmt.executeQuery(partialTodQuery);

                ArrayList<TypeOfDamageModel> tempTypeOfDamageList = new ArrayList<>();
                while (todRs.next()){
                    tempTypeOfDamageList.add(new TypeOfDamageModel(todRs.getString("Name")));
                }

                tempDiceList.add(new DiceModel(diceRs.getInt("Dice"),diceRs.getInt("NumberOfDice"),diceRs.getString("Description"),tempTypeOfDamageList));
            }
        }catch(java.sql.SQLException ex){
            System.err.println("Unable to read data!");
            ex.printStackTrace();
        }
        return tempDiceList;
    }
}
