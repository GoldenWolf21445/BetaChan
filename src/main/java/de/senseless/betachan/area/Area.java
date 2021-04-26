package de.senseless.betachan.area;

import de.senseless.betachan.enums.MonsterType;

import java.util.LinkedList;
import java.util.List;

public class Area {

    private List<MonsterType> monster;
    private static List<Area> areas = new LinkedList<>();

    private int number;

    Area(int number) {
        this.monster = new LinkedList<>();
        this.number = number;
        areas.add(this);
    }

    public List<MonsterType> getMonster() {
        return monster;
    }

    public void addMonster(MonsterType m){
        this.monster.add(m);
    }

    public int getNumber() {
        return number;
    }

    public static Area getByNumber(int number){
        for(Area a: areas){
            if(a.getNumber() == number){
                return a;
            }
        }
        return null;
    }

    public static List<Area> getAreas() {
        return areas;
    }
}
