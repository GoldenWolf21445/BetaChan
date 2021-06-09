package de.senseless.betachan.item;

import de.senseless.betachan.enums.ToolType;

import java.util.LinkedList;
import java.util.List;

public class Item {
    private final String name,recipe;
    private final boolean craftable;
    private int durability, maxDurability;
    private ToolType toolType;
    private static List<Item> items = new LinkedList<>();

    public Item(String name,boolean craftable,String recipe,int maxDurability,ToolType toolType) {
        this.name = name;
        this.craftable = craftable;
        this.recipe = recipe;
        this.durability = maxDurability;
        this.maxDurability = maxDurability;
        this.toolType=toolType;
        items.add(this);
    }


    public Item(String name,boolean craftable,String recipe) {
        this.name = name;
        this.craftable = craftable;
        this.recipe = recipe;
        items.add(this);
    }

    public Item(String name,boolean craftable) {
        this.name = name;
        this.craftable = craftable;
        this.recipe = "";
        items.add(this);
    }
    public Item(String name) {
        this.name = name;
        this.craftable = false;
        this.recipe = "";
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

    public String getRecipe() {
        return recipe;
    }

    public ToolType getToolType() {
        return toolType;
    }

    public int getDurability() {
        return durability;
    }

    public int getMaxDurability() {
        return maxDurability;
    }
}
