package de.senseless.betachan.area;

import de.senseless.betachan.monster.GoblinMONSTER;
import de.senseless.betachan.monster.Monster;

import java.util.LinkedList;
import java.util.List;

public class AreaManager {

    public static void init() {

        List<Monster> area1 = new LinkedList<>();
        area1.add(new GoblinMONSTER());

        Area one = new Area(area1, 1);
    }

}
