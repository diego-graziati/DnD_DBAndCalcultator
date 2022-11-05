package com.sersapessi.db;

import com.sersapessi.MainSingleton;
import com.sersapessi.models.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;

public class Database {
    private final String pathToDb = "resource/dbs/MainDB.db";
    private Connection conn;

    public Database() throws FileNotFoundException {
        File dbFile = new File(pathToDb);

        if(dbFile.exists()){
            System.out.println("Database found");
            try {
                conn = DriverManager.getConnection("jdbc:sqlite:"+pathToDb);
                System.out.println("Database connection established");
            } catch (SQLException e) {
                System.err.println("Unable to create the Database Connection");
            }

        }else{
            throw new FileNotFoundException("The Database couldn't be found! To proceed you must download it!");
        }
    }

    public void getCloseWeapons() throws FileNotFoundException {
        MainSingleton.getInstance().closeWeapons = new ArrayList<>();
        try{

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

                MainSingleton.getInstance().closeWeapons.add(new CloseWeaponsModel(rs.getString("Name"),getDiceList(diceQuery),rs.getBoolean("CanBeThrown"),rs.getInt("Cost"),rs.getString("Description")));
            }
        }catch(java.sql.SQLException ex){
            ex.printStackTrace();
        }
    }
    public void getLongWeapons() throws FileNotFoundException {
        MainSingleton.getInstance().longWeapons = new ArrayList<>();
        try{

            String query = "SELECT * FROM longWeapons";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while(rs.next()){
                //Ottengo tutte le Armi da distanza
                System.out.println("Id: "+rs.getInt("Id")+"\tName: "+rs.getString("Name")+"\tCost: "+
                        rs.getInt("Cost")+"\tDescription: "+rs.getString("Description"));

                String diceQuery = "SELECT Id,Dice,NumberOfDice,Description FROM dice WHERE CloseWeaponsID="+rs.getInt("Id");

                MainSingleton.getInstance().longWeapons.add(new LongWeaponsModel(rs.getString("Name"),getDiceList(diceQuery),rs.getInt("Cost"),rs.getString("Description")));
            }
        }catch(java.sql.SQLException ex){
            ex.printStackTrace();
        }
    }
    public void getEnchantments() throws FileNotFoundException {
        MainSingleton.getInstance().enchantments = new ArrayList<>();
        try{

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
        }
    }
    public void getArmors() throws FileNotFoundException {
        MainSingleton.getInstance().armors = new ArrayList<>();
        try{
            String armorQuery = "SELECT * FROM armor";

            Statement armorStmt = conn.createStatement();
            ResultSet armorRs = armorStmt.executeQuery(armorQuery);

            while(armorRs.next()){
                MainSingleton.getInstance().armors.add(new ArmorModel(armorRs.getString("Name"),armorRs.getInt("BaseTotalCA"),armorRs.getInt("BaseCost"),armorRs.getString("Description")));
            }
        }catch(java.sql.SQLException ex) {
            ex.printStackTrace();
        }
    }
    public void getBombs() throws FileNotFoundException {
        MainSingleton.getInstance().bombs = new ArrayList<>();
        try{
            String bombQuery = "SELECT * FROM bombs";

            Statement bombStmt = conn.createStatement();
            ResultSet bombRs = bombStmt.executeQuery(bombQuery);

            while(bombRs.next()){
                ArrayList<RecipeModel> recipe = new ArrayList<>();
                String recipeQuery = "SELECT Num,ItemsID FROM recipe WHERE BombsID="+bombRs.getInt("Id");

                Statement recipeStmt = conn.createStatement();
                ResultSet recipeRs = recipeStmt.executeQuery(recipeQuery);
                while(recipeRs.next()){
                    String itemQuery = "SELECT * FROM items WHERE Id="+recipeRs.getInt("ItemsID");

                    Statement itemStmt = conn.createStatement();
                    ResultSet itemRs = itemStmt.executeQuery(itemQuery);

                    ItemModel item = new ItemModel(itemRs.getString("Name"),itemRs.getInt("Cost"),itemRs.getString("Description"));

                    recipe.add(new RecipeModel(item, recipeRs.getInt("Num")));
                }
                String diceQuery = "SELECT Dice,NumberOfDice,Description FROM dice WHERE BombsID="+bombRs.getInt("Id");

                MainSingleton.getInstance().bombs.add(new BombModel(bombRs.getString("Name"),bombRs.getInt("Cost"),bombRs.getString("Description"),recipe,getDiceList(diceQuery)));
            }
        }catch(java.sql.SQLException ex) {
            ex.printStackTrace();
        }
    }
    public void getEssence() throws FileNotFoundException {
        MainSingleton.getInstance().essences = new ArrayList<>();
        try{
            String essenceQuery = "SELECT * FROM essences";

            Statement essenceStmt = conn.createStatement();
            ResultSet essenceRs = essenceStmt.executeQuery(essenceQuery);

            while(essenceRs.next()){
                String effectsQuery = "SELECT * FROM essencesEffects WHERE EssencesID="+essenceRs.getString("Id");

                Statement effectsStmt = conn.createStatement();
                ResultSet effectsRs = effectsStmt.executeQuery(effectsQuery);
                ArrayList<EssenceEffectsModel> tempEffects = new ArrayList<>();
                while(effectsRs.next()){
                    tempEffects.add(new EssenceEffectsModel(effectsRs.getString("Description")));
                }
                String recipeQuery = "SELECT Num,ItemsID FROM recipe WHERE EssencesID="+essenceRs.getInt("Id");

                Statement recipeStmt = conn.createStatement();
                ResultSet recipeRs = recipeStmt.executeQuery(recipeQuery);
                ArrayList<RecipeModel> tempRecipe = new ArrayList<>();
                while(recipeRs.next()){
                    String itemQuery = "SELECT * FROM items WHERE Id="+recipeRs.getInt("ItemsID");

                    Statement itemStmt = conn.createStatement();
                    ResultSet itemRs = itemStmt.executeQuery(itemQuery);

                    ItemModel item = new ItemModel(itemRs.getString("Name"),itemRs.getInt("Cost"),itemRs.getString("Description"));

                    tempRecipe.add(new RecipeModel(item,recipeRs.getInt("Num")));
                }

                MainSingleton.getInstance().essences.add(new EssenceModel(essenceRs.getString("Name"),
                                                            essenceRs.getInt("Cost"),essenceRs.getString("Description"),
                                                            tempEffects,tempRecipe));
            }
        }catch(java.sql.SQLException ex) {
            ex.printStackTrace();
        }
    }
    public void getStatus() throws FileNotFoundException {
        MainSingleton.getInstance().status = new ArrayList<>();
        try{
            String statusQuery = "SELECT * FROM status";

            Statement statusStmt = conn.createStatement();
            ResultSet statusRs = statusStmt.executeQuery(statusQuery);

            while(statusRs.next()){
                MainSingleton.getInstance().status.add(new StatusModel(statusRs.getString("Name"),statusRs.getString("Description")));
            }
        }catch(java.sql.SQLException ex) {
            ex.printStackTrace();
        }
    }
    public void getHumanEnemies() throws FileNotFoundException {
        MainSingleton.getInstance().humanEnemies = new ArrayList<>();
        try{
            String humanEnQuery = "SELECT * FROM humanEnemies";

            Statement humanEnStmt = conn.createStatement();
            ResultSet humanEnRs = humanEnStmt.executeQuery(humanEnQuery);
            while(humanEnRs.next()){
                String moveSetQuery = "SELECT Name,Description FROM moveSets WHERE HumanEnemiesID="+humanEnRs.getInt("Id");

                Statement moveSetStmt = conn.createStatement();
                ResultSet moveSetRs = moveSetStmt.executeQuery(moveSetQuery);
                ArrayList<MoveSetModel> tempMoveSet = new ArrayList<>();
                while(moveSetRs.next()){
                    tempMoveSet.add(new MoveSetModel(moveSetRs.getString("Name"),moveSetRs.getString("Description")));
                }
                String attacksSetQuery = "SELECT * FROM enemiesAttack WHERE HumanEnemiesID="+humanEnRs.getInt("Id");

                Statement attackSetStmt = conn.createStatement();
                ResultSet attackSetRs = attackSetStmt.executeQuery(attacksSetQuery);
                ArrayList<EnemiesAttackModel> tempEnAttacks = new ArrayList<>();
                while(attackSetRs.next()){
                    String diceQuery = "SELECT * FROM dice WHERE EnemiesAttackID="+attackSetRs.getInt("Id");
                    tempEnAttacks.add(new EnemiesAttackModel(attackSetRs.getString("Name"),attackSetRs.getString("AdditionalDesc"),getDice(diceQuery)));
                }
                String enCharactQuery = "SELECT * FROM enemiesCharacteristics WHERE HumanEnemiesID="+humanEnRs.getInt("Id");

                Statement enCharactStmt = conn.createStatement();
                ResultSet enCharactRs = enCharactStmt.executeQuery(enCharactQuery);
                ArrayList<EnemiesCharacteristicsModel> tempEnCharacts = new ArrayList<>();
                while(enCharactRs.next()){
                    tempEnCharacts.add(new EnemiesCharacteristicsModel(enCharactRs.getString("Name"),enCharactRs.getString("Description")));
                }
                MainSingleton.getInstance().humanEnemies.add(new HumanEnemyModel(humanEnRs.getString("Name"),humanEnRs.getInt("HitPoints"),
                                                                                    humanEnRs.getInt("CA"),humanEnRs.getString("Description"),
                                                                                    tempMoveSet,tempEnAttacks,tempEnCharacts));
            }
        }catch(java.sql.SQLException ex) {
            ex.printStackTrace();
        }
    }
    public void getBeastEnemies() throws FileNotFoundException {
        MainSingleton.getInstance().beastEnemies = new ArrayList<>();
        try{
            String humanEnQuery = "SELECT * FROM beastsEnemies";

            Statement humanEnStmt = conn.createStatement();
            ResultSet humanEnRs = humanEnStmt.executeQuery(humanEnQuery);
            while(humanEnRs.next()){
                String moveSetQuery = "SELECT Name,Description FROM moveSets WHERE BeastsEnemiesID="+humanEnRs.getInt("Id");

                Statement moveSetStmt = conn.createStatement();
                ResultSet moveSetRs = moveSetStmt.executeQuery(moveSetQuery);
                ArrayList<MoveSetModel> tempMoveSet = new ArrayList<>();
                while(moveSetRs.next()){
                    tempMoveSet.add(new MoveSetModel(moveSetRs.getString("Name"),moveSetRs.getString("Description")));
                }
                String attacksSetQuery = "SELECT * FROM enemiesAttack WHERE BeastsEnemiesID="+humanEnRs.getInt("Id");

                Statement attackSetStmt = conn.createStatement();
                ResultSet attackSetRs = attackSetStmt.executeQuery(attacksSetQuery);
                ArrayList<EnemiesAttackModel> tempEnAttacks = new ArrayList<>();
                while(attackSetRs.next()){
                    String diceQuery = "SELECT * FROM dice WHERE EnemiesAttackID="+attackSetRs.getInt("Id");
                    tempEnAttacks.add(new EnemiesAttackModel(attackSetRs.getString("Name"),attackSetRs.getString("AdditionalDesc"),getDice(diceQuery)));
                }
                String enCharactQuery = "SELECT * FROM enemiesCharacteristics WHERE BeastsEnemiesID="+humanEnRs.getInt("Id");

                Statement enCharactStmt = conn.createStatement();
                ResultSet enCharactRs = enCharactStmt.executeQuery(enCharactQuery);
                ArrayList<EnemiesCharacteristicsModel> tempEnCharacts = new ArrayList<>();
                while(enCharactRs.next()){
                    tempEnCharacts.add(new EnemiesCharacteristicsModel(enCharactRs.getString("Name"),enCharactRs.getString("Description")));
                }
                MainSingleton.getInstance().humanEnemies.add(new HumanEnemyModel(humanEnRs.getString("Name"),humanEnRs.getInt("HitPoints"),
                        humanEnRs.getInt("CA"),humanEnRs.getString("Description"),
                        tempMoveSet,tempEnAttacks,tempEnCharacts));
            }
        }catch(java.sql.SQLException ex) {
            ex.printStackTrace();
        }
    }

    //Gets the complete DiceList: it lets you specify the diceQuery and a partialTodQuery. The last one must always end referencing to the "DiceId=".
    private ArrayList<DiceModel> getDiceList(String diceQuery){
        ArrayList<DiceModel> tempDiceList = new ArrayList<>();
        String partialTodQuery = "SELECT Name FROM typeOfDamage WHERE DiceId=";
        try{
            Statement diceStmt = conn.createStatement();
            ResultSet diceRs = diceStmt.executeQuery(diceQuery);

            while (diceRs.next()){

                Statement todStmt = conn.createStatement();
                ResultSet todRs = todStmt.executeQuery(partialTodQuery+diceRs.getInt("Id"));

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
    private DiceModel getDice(String diceQuery) throws SQLException {
        String partialTodQuery = "SELECT Name FROM typeOfDamage WHERE DiceId=";
        Statement diceStmt = conn.createStatement();;
        ResultSet diceRs = diceStmt.executeQuery(diceQuery);
        ArrayList<TypeOfDamageModel> tempTypeOfDamageList = new ArrayList<>();
        try{
            Statement todStmt = conn.createStatement();
            ResultSet todRs = todStmt.executeQuery(partialTodQuery+diceRs.getInt("Id"));

            while (todRs.next()){
                tempTypeOfDamageList.add(new TypeOfDamageModel(todRs.getString("Name")));
            }
        }catch(java.sql.SQLException ex){
            System.err.println("Unable to read data!");
            ex.printStackTrace();
        }
        return new DiceModel(diceRs.getInt("Dice"),diceRs.getInt("NumberOfDice"),diceRs.getString("Description"),tempTypeOfDamageList);
    }

    public void close(){
        try{
            conn.close();
            System.out.println("Database Connection closed");
        }catch (SQLException e) {
            System.err.println("Unable to close the Database Connection");
        }
    }
}
