package de.senseless.betachan.enums;

import de.senseless.betachan.handler.ItemHandler;
import de.senseless.betachan.item.Item;

public enum MonsterType {

    COW(3,1,0,1,"Cow",ItemHandler.MEAT),
    SHEEP(3,1,0,1,"Sheep",ItemHandler.MEAT),
    CHICKEN(3,1,0,1,"Chicken",ItemHandler.MEAT),
    RABBIT(3,1,0,1,"Rabbit",ItemHandler.MEAT),
    PIG(3,1,0,1,"Pig",ItemHandler.MEAT),
    HORSE(3,1,0,1,"Horse",ItemHandler.MEAT),
    LAMA(3,1,0,1,"Lama",ItemHandler.MEAT),
    ELCH(3,1,0,1,"Elch",ItemHandler.MEAT),
    DEER(3,1,0,1,"Deer",ItemHandler.MEAT),
    BEAR(3,1,0,1,"Bear",ItemHandler.MEAT),
    WOLF(1,1,1,1,"Wolf", ItemHandler.WOLFFUR,ItemHandler.MEAT),
    SKELETON(1,1,1,1,"Skeleton",ItemHandler.BONE),
    ELITE_SKELETON(1,1,1,1,"Elite-Skeleton",ItemHandler.BONE),
    KING_SKELETON(1,1,1,1,"King-Skeleton",ItemHandler.BONE),
    DEATHRIDER(1,1,1,1,"Death Rider",ItemHandler.BONE),
    CENTAUR(1,1,1,1,"Centaur"),
    GOBLIN(1,1,1,1,"Goblin"),
    GOBLINGENERAL(1,1,1,1,"Goblin General"),
    GOBLINKING(1,1,1,1,"Goblin King"),
    GOLEM(1,1,1,1,"Golem",ItemHandler.STONE),
    GIANT(1,1,1,1,"Giant"),
    OGRE(1,1,1,1,"Ogre"),
    DRAGON(1,1,1,1,"Dragon",ItemHandler.DRAGONHEART,ItemHandler.DRAGONSCALE,ItemHandler.DRAGONTOOTH),
    GRIFFIN(1,1,1,1,"Griffin",ItemHandler.GRIFFINCLAW,ItemHandler.GRIFFINFEATHER),
    PIRATE(1,1,1,1,"Pirate"),
    WIKING(1,1,1,1,"Wiking"),
    RUINGUARD(1,1,1,1,"Ruin Guard"),
    RUINHUNTER(1,1,1,1,"Ruin Hunter"),
    RUINWATCHTOWER(1,1,1,1,"Ruin Watchtower"),
    BANDIT(1,1,1,1,"Bandit"),
    ELF(1,1,1,1,"Elf"),
    TROLL(1,1,1,1,"Troll"),
    BASILISK(1,1,1,1,"Basilisk",ItemHandler.BASILISKSCALE),
    ONI(1,1,1,1,"Oni"),
    KITSUNE(1,1,1,1,"Kitsune",ItemHandler.KITSUNETAIL),
    DEMON(1,1,1,1,"Demon"),
    DEMONGENERAL(1,1,1,1,"Demon General"),
    DEMONPRINCE(1,1,1,1,"Demon Prince"),
    DEMONKING(1,1,1,1,"Demon King"),
    BEAST(1,1,1,1,"Beast"),
    SLIME(1,1,1,1,"Slime"),
    DEVIL(1,1,1,1,"Devil"),
    LIZARD(1,1,1,1,"Lizard",ItemHandler.LIZARDSCALE),
    SPIDER(1,1,1,1,"Spider",ItemHandler.SPIDERSTRING),
    ANGEL(1,1,1,1,"Angel",ItemHandler.ANGELWING),
    HUMAN(1,1,1,1,"Human"),
    DRYAD(1,1,1,1,"Dryad",ItemHandler.MAGICLEAF),
    UNICORN(1,1,1,1,"Unicorn",ItemHandler.UNICORNHORN),
    FAIRY(1,1,1,1,"Fairy",ItemHandler.FAIRYWING),
    GHOST(1,1,1,1,"Ghost"),
    DEATHWORM(1,1,1,1,"Death Worm"),
    ANCIENTRUINGUARD(1,1,1,1,"Ancient Ruin Guard"),
    ANCIENTRUINHUNTER(1,1,1,1,"Ancient Ruin Hunter"),
    WITCH(1,1,1,1,"Witch"),
    SUCCUBUS(1,1,1,1,"Succubus"),
    INCUBUS(1,1,1,1,"Incubus"),
    SHADOW(1,1,1,1,"Shadow"),
    GHOUL(1,1,1,1,"Ghoul"),
    DULLAHAN(1,1,1,1,"Dullahan"),
    MUMMY(1,1,1,1,"Mummy"),
    SYLPH(1,1,1,1,"Sylph"),
    DEATHANGEL(1,1,1,1,"Death Angel"),
    VAMPIRE(1,1,1,1,"Vampire"),
    WILL_O_WISP(1,1,1,1,"Will-o'-wisp"),
    MINOTAUR(1,1,1,1,"Minotaur"),
    SPHINX(1,1,1,1,"Sphinx"),
    MANTICORE(1,1,1,1,"Manticore"),
    HIPPOGRYPH(1,1,1,1,"Hippogryph"),
    GORGON(1,1,1,1,"Gorgon"),
    BEHEMOTH(1,1,1,1,"Behemoth"),
    HYDRA(1,1,1,1,"Hydra"),
    TITAN(1,1,1,1,"Titan"),
    BASILISK_BOSS(1,1,1,1,"Basilisk"),
    YAMATANOOROCHI(1,1,1,1,"Yamata no Orochi"),
    ABADDON(1,1,1,1,"Abaddon"),
    ASMODEUS(1,1,1,1,"Asmodeus"),
    IFRIT(1,1,1,1,"Ifrit"),
    LILITH(1,1,1,1,"Lilith");


    private int HP,ATK,DEF,XP;
    private String NAME;
    private Item[] drop;

    MonsterType(int HP, int ATK, int DEF, int XP, String NAME, Item... drop) {
        this.HP = HP;
        this.ATK = ATK;
        this.DEF = DEF;
        this.XP = XP;
        this.NAME = NAME;
        this.drop = drop;
    }

    public static MonsterType getByName(String name){
        for (MonsterType mt : values()){
            if(mt.getNAME().toLowerCase().equals(name.toLowerCase())){
                return mt;
            }
        }
        return null;
    }

    public int getHP() {
        return HP;
    }

    public int getATK() {
        return ATK;
    }

    public int getDEF() {
        return DEF;
    }

    public int getXP() {
        return XP;
    }

    public String getNAME() {
        return NAME;
    }

    public Item[] getDrop() {
        return drop;
    }
}
