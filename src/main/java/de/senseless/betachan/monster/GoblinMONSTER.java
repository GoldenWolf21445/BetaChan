package de.senseless.betachan.monster;

import de.senseless.betachan.item.Item;

import java.util.HashMap;

public class GoblinMONSTER extends Monster{


    private static final HashMap<Item, Integer> drops = new HashMap<>();

    public GoblinMONSTER() {
        super(2, 1, 2, "Goblin", drops);
    }
}
