package de.senseless.betachan.area;

import de.senseless.betachan.monster.*;

import java.util.LinkedList;
import java.util.List;

public class AreaManager {

    public static void init() {
        Area one = new Area(1);
        one.addMonster(new Wolf());
        Area two = new Area(2);
        Area three = new Area(3);
        Area four = new Area(4);
        Area five = new Area(5);
    }

}
