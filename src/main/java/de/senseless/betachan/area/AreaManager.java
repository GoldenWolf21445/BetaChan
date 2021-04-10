package de.senseless.betachan.area;

import de.senseless.betachan.monster.*;

import java.util.LinkedList;
import java.util.List;

public class AreaManager {

    public static void init() {
        Area one = new Area(1);
        one.addMonster(new Goblin());
        one.addMonster(new Wolf());
        Area two = new Area(2);
        two.addMonster(new Wizzard());
        two.addMonster(new Witch());
        Area three = new Area(3);
        three.addMonster(new Griffin());
        Area four = new Area(4);
        four.addMonster(new Giant());
        four.addMonster(new Golem());
        Area five = new Area(5);
        five.addMonster(new Dragon());
    }

}
