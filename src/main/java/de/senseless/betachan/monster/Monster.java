package de.senseless.betachan.monster;

import de.senseless.betachan.item.Item;

import java.util.HashMap;

public class Monster {

    private final int HP,ATK,DEF;
    private final String NAME;
    private HashMap<Item,Integer> DROPS;

    public Monster(int HP, int ATK, int DEF, String name, HashMap<Item, Integer> drops) {
        this.HP = HP;
        this.ATK = ATK;
        this.DEF = DEF;
        NAME = name;
        DROPS = drops;
    }

    public int getHP() {
        return HP;
    }

    public int getATK() {
        return ATK;
    }

    public int getDEF() {
        return DEF;
    }

    public String getNAME() {
        return NAME;
    }

    public HashMap<Item, Integer> getDROPS() {
        return DROPS;
    }


}
