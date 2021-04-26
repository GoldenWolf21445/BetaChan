package de.senseless.betachan.area;

import de.senseless.betachan.enums.MonsterType;

public class AreaManager {

    public static final Area AREA_ONE = new Area(1);
    public static final Area AREA_TWO = new Area(2);
    public static final Area AREA_THREE = new Area(3);
    public static final Area AREA_FOUR = new Area(4);
    public static final Area AREA_FIVE = new Area(5);


    public static void init() {
        for (MonsterType mt : MonsterType.values()){
            AREA_ONE.addMonster(mt);
        }
    }

}
