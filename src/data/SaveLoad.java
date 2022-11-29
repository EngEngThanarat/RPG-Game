package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.text.html.CSS;

import Entity.Entity;
import Objects.Obj_Axe;
import Objects.Obj_Chest;
import Objects.Obj_Door;
import Objects.Obj_Halberd;
import Objects.Obj_Key;
import Objects.Obj_Potion_Red;
import Objects.Obj_Shield_Wood;
import Objects.Obj_Sword_Normal;
import Objects.Obj_boots;
import Objects.Obj_rock_shield;
import main.GamePanel;

public class SaveLoad {
    
    GamePanel gp;
    
    public SaveLoad(GamePanel gp){
        this.gp = gp;
    }

    public Entity getObject(String itemName){
        Entity obj = null;

        switch(itemName){
            case "Axe": obj = new Obj_Axe(gp); break;
            case "boot": obj = new Obj_boots(gp); break;
            case "Chest": obj = new Obj_Chest(gp); break;
            case "Door": obj = new Obj_Door(gp); break;
            case "Halberd": obj = new Obj_Halberd(gp); break;
            case "Key": obj = new Obj_Key(gp); break;
            case "Red Potion": obj = new Obj_Potion_Red(gp); break;
            case "Rock Shield": obj = new Obj_rock_shield(gp); break;
            case "Wood Shield": obj = new Obj_Shield_Wood(gp); break;
            case "Normal Sword": obj = new Obj_Sword_Normal(gp); break;
        }
        return obj;
    }

    public void Save(){

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.dat")))) {

            DataStorage ds = new DataStorage();

            // PLAYER STATUS
            ds.level = gp.player.level;
            ds.maxLife = gp.player.maxLife;
            ds.life = gp.player.life;
            ds.strength = gp.player.strength;
            ds.dexterity = gp.player.dexterity;
            ds.exp = gp.player.exp;
            ds.nextLevelExp = gp.player.NextLevel;
            ds.coin = gp.player.coin;

            // PLAYER INVENTORY
            for(int i = 0; i < gp.player.inventory.size(); i++){
                ds.itemNames.add(gp.player.inventory.get(i).name);
                ds.itemAmounts.add(gp.player.inventory.get(i).amout);
            }

            // PLAYER EQUIPMENT
            ds.currentWeaponSlot = gp.player.getCurrentWeaponSlot();
            ds.currentShildSlot = gp.player.getCurrentShieldSlot();

            // OBJECT ON MAP
            ds.mapObjectNames = new String[gp.maxMap][gp.obj[1].length];
            ds.mapObjectWorldX = new int[gp.maxMap][gp.obj[1].length];
            ds.mapObjectWorldY = new int[gp.maxMap][gp.obj[1].length];
            ds.mapObjectLootNames = new String[gp.maxMap][gp.obj[1].length];
            ds.mapObjectOpened = new boolean[gp.maxMap][gp.obj[1].length];

            for(int mapNum = 0; mapNum < gp.maxMap; mapNum++){
                for(int i = 0; i < gp.obj[1].length; i++){
                    if(gp.obj[mapNum][i] == null){
                        ds.mapObjectNames[mapNum][i] = "NA";
                    }
                    else{
                        ds.mapObjectNames[mapNum][i] = gp.obj[mapNum][i].name;
                        ds.mapObjectWorldX[mapNum][i] = gp.obj[mapNum][i].worldX;
                        ds.mapObjectWorldY[mapNum][i] = gp.obj[mapNum][i].worldY;
                        if(gp.obj[mapNum][i].chest != null){
                            ds.mapObjectLootNames[mapNum][i] = gp.obj[mapNum][i].chest.name;
                        }
                        ds.mapObjectOpened[mapNum][i] = gp.obj[mapNum][i].opened;
                    }
                }
            }

            // write the DataStorage Object
            oos.writeObject(ds);
        } catch (IOException e) {
            System.out.println("Save Exception");
        }
    }

    public void Load(){
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("save.dat")));

            // Read the DataStorage object
            DataStorage ds = (DataStorage)ois.readObject();


            // PLAYER STATUS
            gp.player.level = ds.level;
            gp.player.maxLife = ds.maxLife;
            gp.player.life = ds.life;
            gp.player.strength = ds.strength;
            gp.player.dexterity = ds.dexterity;
            gp.player.exp = ds.exp;
            gp.player.NextLevel = ds.nextLevelExp;
            gp.player.coin = ds.coin;

            // PLAYER INVENTORY
            gp.player.inventory.clear();
            for(int i = 0; i < ds.itemNames.size(); i++){
                gp.player.inventory.add(getObject(ds.itemNames.get(i)));
                gp.player.inventory.get(i).amout = ds.itemAmounts.get(i);
            }

            // PLAYER EQUIPMENT
            gp.player.currentWeapon = gp.player.inventory.get(ds.currentWeaponSlot);
            gp.player.currentShield = gp.player.inventory.get(ds.currentShildSlot);
            gp.player.getDefense();
            gp.player.getAttack();
            gp.player.getPlayerAttackImage();

            // OBJECT ON MAP
            for(int mapNum = 0; mapNum < gp.maxMap; mapNum++){
                for(int i = 0; i < gp.obj[1].length ; i++){

                    if(ds.mapObjectNames[mapNum][i].equals("NA")){
                        gp.obj[mapNum][i] = null;
                    }
                    else{
                        gp.obj[mapNum][i] = getObject(ds.mapObjectNames[mapNum][i]);
                        gp.obj[mapNum][i].worldX = ds.mapObjectWorldX[mapNum][i];
                        gp.obj[mapNum][i].worldY = ds.mapObjectWorldY[mapNum][i];
                        if(ds.mapObjectLootNames[mapNum][i] != null){
                            gp.obj[mapNum][i].chest = getObject(ds.mapObjectLootNames[mapNum][i]);
                        }
                        gp.obj[mapNum][i].opened = ds.mapObjectOpened[mapNum][i];
                        if(gp.obj[mapNum][i].opened == true){
                            gp.obj[mapNum][i].down1 = gp.obj[mapNum][i].image2;
                        }
                    }
                }
            }
            
        } catch (Exception e) {
            System.out.println("Load Exception");
        }
    }
}
