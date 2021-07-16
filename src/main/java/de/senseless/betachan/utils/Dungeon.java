package de.senseless.betachan.utils;

import de.senseless.betachan.BetaChan;
import de.senseless.betachan.enums.MonsterType;
import net.dv8tion.jda.api.entities.TextChannel;

public class Dungeon {

    private MonsterType mt;
    private int hp, atk, def;
    private double xp;
    private long guildID, channelID;

    public Dungeon(MonsterType mt, int hp, int atk, int def, double xp, long guildID,long channelID) {
        this.mt = mt;
        this.hp = hp;
        this.atk = atk;
        this.def = def;
        this.xp = xp;
        this.guildID =guildID;
        this.channelID = channelID;
    }

    public MonsterType getMt() {
        return mt;
    }

    public void setMt(MonsterType mt) {
        this.mt = mt;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public double getXp() {
        return xp;
    }

    public void setXp(double xp) {
        this.xp = xp;
    }

    public long getGuildID() {
        return guildID;
    }

    public void setGuildID(long guildID) {
        this.guildID = guildID;
    }

    public long getChannelID() {
        return channelID;
    }

    public void setChannelID(long channelID) {
        this.channelID = channelID;
    }
}
