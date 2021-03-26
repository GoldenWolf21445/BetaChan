package de.senseless.betachan.item;

import java.util.LinkedList;
import java.util.List;

public class Item {
    private String name;
    private static List<Item> items = new LinkedList<>();

    public Item(String name) {
        this.name = name;
        items.add(this);
    }

    public String getName() {
        return name;
    }

    public static Item getByName(String name){
        for (Item i: items){
            if(i.getName().equalsIgnoreCase(name)){
                return i;
            }
        }
        return null;
    }
}
