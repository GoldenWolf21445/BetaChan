package de.senseless.betachan.monster;

import de.senseless.betachan.item.GriffinClaw;
import de.senseless.betachan.item.GriffinFeather;
import de.senseless.betachan.item.Item;

public class Griffin extends Monster{

    public Griffin() {
        super(1, 1, 1, 1, "Griffin", new GriffinFeather(),new GriffinClaw());
    }
}
