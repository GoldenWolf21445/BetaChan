package de.senseless.betachan.armor;

import de.senseless.betachan.item.Item;

public class Armor extends Item {

    private int def;

    public Armor(int def, String name) {
        super(name,true);
        this.def = def;
    }

    public int getDef() {
        return def;
    }
}
