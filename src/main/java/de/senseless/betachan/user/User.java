package de.senseless.betachan.user;


import de.senseless.betachan.area.Area;
import de.senseless.betachan.item.Item;
import net.dv8tion.jda.api.entities.Member;

import java.util.LinkedList;
import java.util.List;

public class User {

    private static List<User> users = new LinkedList<>();

    private String id;

    private final int money, xp, level, atk, def,life,maxlife;
    private final Area area;
    private List<Item> inventory;

    public User(Member member, int money, int xp, int level, int atk, int def, int life, int maxlife, Area area) {

        this.id = member.getId();
        this.money = money;
        this.xp = xp;
        this.level = level;
        this.atk = atk;
        this.def = def;
        this.life = life;
        this.maxlife = maxlife;
        this.area = area;
        users.add(this);
        inventory = new LinkedList<>();
    }

    public static User getByID(String id) {
        for (User u : users) {
            if (u.getId().equals(id)) {
                return u;
            }
        }
        return null;
    }

    public List<User> getUsers() {
        return users;
    }

    public String getId() {
        return id;
    }

    public int getMoney() {
        return money;
    }

    public int getXp() {
        return xp;
    }

    public int getLevel() {
        return level;
    }

    public int getAtk() {
        return atk;
    }

    public int getDef() {
        return def;
    }

    public int getLife() {
        return life;
    }

    public int getMaxlife() {
        return maxlife;
    }

    public Area getArea() {
        return area;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public void addToInventory(Item item) {
        inventory.add(item);
    }


}
