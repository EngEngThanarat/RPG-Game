package data;

import java.io.Serializable;
import java.util.ArrayList;



public class DataStorage implements Serializable{
    
    // PLAYER STATUS
    int level;
    int maxLife;
    int life;
    int strength;
    int dexterity;
    int exp;
    int nextLevelExp;
    int coin;

    // Player Inventory
    ArrayList<String> itemNames = new ArrayList<>();
    ArrayList<Integer> itemAmounts = new ArrayList<>();
    int currentWeaponSlot;
    int currentShildSlot;

    // OBJECT ON MAP
    String mapObjectNames[][];
    int mapObjectWorldX[][];
    int mapObjectWorldY[][];
    String mapObjectLootNames[][];
    boolean mapObjectOpened[][];
}
