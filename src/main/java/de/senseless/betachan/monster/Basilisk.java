package de.senseless.betachan.monster;

import de.senseless.betachan.item.BasiliskScale;
import de.senseless.betachan.item.Item;

public class Basilisk extends Monster{

    public Basilisk() {
        super(1, 1, 1, 1, "Basilisk", new BasiliskScale());
    }
}
