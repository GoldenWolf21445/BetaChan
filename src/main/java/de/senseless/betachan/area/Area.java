package de.senseless.betachan.area;

import de.senseless.betachan.monster.Monster;

import java.util.LinkedList;
import java.util.List;

public class Area {

    private List<Monster> monster;
    private static List<Area> areas = new LinkedList<>();

    private int number;

    Area(int number) {
        this.monster = new LinkedList<>();
        this.number = number;
        areas.add(this);
    }

    public List<Monster> getMonster() {
        return monster;
    }

    public void addMonster(Monster m){
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



}
