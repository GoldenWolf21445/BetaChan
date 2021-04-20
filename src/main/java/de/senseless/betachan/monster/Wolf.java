package de.senseless.betachan.monster;

import de.senseless.betachan.item.Item;
import de.senseless.betachan.item.WolfFur;

public class Wolf extends Monster{

    public Wolf() {
        super(5, 1, 2, 10, "Wolf", new WolfFur());
    }
}
