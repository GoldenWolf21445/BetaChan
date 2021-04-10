package de.senseless.betachan.item;

import java.util.LinkedList;
import java.util.List;

public class Item {
    private String name;
    private boolean craftable;
    private static List<Item> items = new LinkedList<>();

    public Item(String name,boolean craftable) {
        this.name = name;
        this.craftable = craftable;
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

    public boolean isCraftable() {
        return craftable;
    }

    public static List<Item> getItems() {
        return items;
    }
}
