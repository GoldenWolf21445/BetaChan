package de.senseless.betachan.monster;

import de.senseless.betachan.item.Item;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Monster {

    private static List<Monster> monster = new LinkedList<>();

    private final int HP,ATK,DEF;
    private final String NAME;
    private Item drop;

    public Monster(int HP, int ATK, int DEF, String name, Item drop) {
        this.HP = HP;
        this.ATK = ATK;
        this.DEF = DEF;
        NAME = name;
        this.drop = drop;
        monster.add(this);
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

    public Item getDrop() {
        return drop;
    }

    public static List<Monster> getMonster() {
        return monster;
    }
}
