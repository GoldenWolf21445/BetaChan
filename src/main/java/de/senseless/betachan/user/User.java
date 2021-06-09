package de.senseless.betachan.user;


import de.senseless.betachan.BetaChan;
import de.senseless.betachan.area.Area;
import de.senseless.betachan.area.AreaManager;
import de.senseless.betachan.handler.ItemHandler;
import de.senseless.betachan.item.Item;
import de.senseless.betachan.utils.Pet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public class User {

    private static List<User> users = new LinkedList<>();
    private String id,name;
    private int money, level, atk, def, life, maxlife, bank;
    private double xp;
    private Area area;
    private HashMap<Item, Integer> inventory;
    private LinkedList<Pet> pets;
    private Item equippedTool, equippedSword, equippedArmor;
    private int started;

    public User(String id,String name, int money, int bank, double xp, int level, int atk, int def, int life, int maxlife, Area area, int started,Item equippedTool,Item equippedArmor, Item equippedSword) {

        this.id = id;
        this.name = name;
        this.money = money;
        this.bank = bank;
        this.xp = xp;
        this.level = level;
        this.atk = atk;
        this.def = def;
        this.life = life;
        this.maxlife = maxlife;
        this.area = area;
        this.started = started;
        this.equippedTool = equippedTool;
        this.equippedSword = equippedSword;
        this.equippedArmor = equippedArmor;
        users.add(this);
        inventory = new LinkedHashMap<>();
        pets = new LinkedList<>();
        save();
    }

    public static User loadUser(String id,String name) {
        ResultSet rs = BetaChan.INSTANCE.database.onQuery("SELECT * FROM users WHERE id =" + id);
        try {
            if (rs.next()) {

                int money = rs.getInt("money");
                int bank = rs.getInt("bank");

                double xp = rs.getDouble("xp");

                int level = rs.getInt("level");

                int atk = rs.getInt("atk");

                int def = rs.getInt("def");

                int hp = rs.getInt("hp");

                int maxhp = rs.getInt("maxhp");

                int started = rs.getInt("started");

                String inv = rs.getString("inventory");

                Item equippedTool = Item.getByName(rs.getString("equipped_tool"));
                Item equippedSword = Item.getByName(rs.getString("equipped_sword"));
                Item equippedArmor = Item.getByName(rs.getString("equipped_armor"));

                Area area = Area.getByNumber(rs.getInt("area"));
                User u = new User(id,name, money, bank, xp, level, atk, def, hp, maxhp, area, started,equippedTool,equippedSword,equippedArmor);
                String[] items = inv.split("---");
                for (String s : items) {
                    if (!s.isEmpty()) {
                        String[] subsplit = s.split("\\|");
                        u.addToInventory(Item.getByName(subsplit[0]), Integer.parseInt(subsplit[1]));

                    }
                }
                String[] pets = inv.split("---");
                for (String pet : pets) {
                    if (!pet.isEmpty()) {
                        String[] subsplit = pet.split("\\|");
                        u.addPet(subsplit[0], Integer.parseInt(subsplit[1]));

                    }
                }

                u.save();
                return u;
            } else {
                User u = new User(id, name, 100, 0, 0, 1, 4, 4, 100, 100, AreaManager.AREA_ONE, 0,null,null,null);
                u.addToInventory(ItemHandler.POTION, 5);
                u.save();
                return u;

            }
        } catch (SQLException exception) {
            exception.printStackTrace();
            User u = new User(id,name, 100, 0, 0, 1, 4, 4, 100, 100, AreaManager.AREA_ONE, 0,null,null,null);
            u.addToInventory(ItemHandler.POTION, 5);
            u.save();
            return u;

        }
    }


    public static User getByID(String id) {
        for (User u : users) {
            if (u.getId().equals(id)) {
                return u;
            }
        }
        return null;
    }

    public static List<User> getUsers() {
        return users;
    }

    public String getId() {
        return id;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public double getXp() {
        return xp;
    }

    public void setXp(double xp) {
        this.xp = xp;
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

    public void setLife(int life) {
        this.life = life;
    }

    public int getMaxlife() {
        return maxlife;
    }

    public Area getArea() {
        return area;
    }

    public int getStarted() {
        return started;
    }

    public void setStarted(int started) {
        this.started = started;
    }

    public Item getEquippedTool() {
        return equippedTool;
    }

    public void setEquippedTool(Item equippedTool) {
        this.equippedTool = equippedTool;
    }

    public Item getEquippedSword() {
        return equippedSword;
    }

    public void setEquippedSword(Item equippedSword) {
        this.equippedSword = equippedSword;
    }

    public Item getEquippedArmor() {
        return equippedArmor;
    }

    public void setEquippedArmor(Item equippedArmor) {
        this.equippedArmor = equippedArmor;
    }

    public HashMap<Item, Integer> getInventory() {
        return inventory;
    }

    public void addToInventory(Item item, int amount) {
        if (inventory.containsKey(item)) {
            inventory.put(item, inventory.get(item) + amount);
        } else {
            inventory.put(item, amount);
        }
    }

    public void addPet(String petName, int rank){
        pets.add(new Pet(petName,rank));
    }

    public void addXp(double xp) {
        this.xp += xp;
    }

    public void removeLife(int life) {
        this.life -= life;
    }

    public void addLife(int life) {
        this.life += life;
    }

    public void removeXP(double xp) {
        this.xp -= xp;
    }

    public void removeMoney(int money) {
        this.money -= money;
    }

    public void addMoney(int money) {
        this.money += money;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public LinkedList<Pet> getPets() {
        return pets;
    }

    public void save() {
        ResultSet rs = BetaChan.INSTANCE.database.onQuery("SELECT id FROM users WHERE id=" + getId());
        try {
            if (!rs.next()) {

                StringBuilder inv = new StringBuilder("'");
                for (Item i : getInventory().keySet()) {
                    if (i != null) {
                        inv.append(i.getName()).append("|").append(getInventory().get(i)).append("---");
                    }
                }
                inv.append("'");

                BetaChan.INSTANCE.database.onUpdate("INSERT INTO users (id,name,hp,maxhp,atk,def,money,level,xp,area,inventory,started) VALUES (" + getId() + ",'"+getName()+"'," + getLife() + "," + getMaxlife() + "," + getAtk() + "," + getDef() + "," + getMoney() + "," + getLevel() + "," + getXp() + "," + getArea().getNumber() + "," + inv + "," + getStarted() + ")");
            } else {
                StringBuilder inv = new StringBuilder("'");
                for (Item i : getInventory().keySet()) {
                    if (i != null) {
                        inv.append(i.getName()).append("|").append(getInventory().get(i)).append("---");
                    }
                }
                inv.append("'");
                BetaChan.INSTANCE.database.onUpdate("UPDATE users SET hp=" + getLife() + ",name='" + getName() + "',maxhp=" + getMaxlife() + ",atk=" + getAtk() + ",def=" + getDef() + ",money=" + getMoney() + ",bank= " + getBank() + ",level=" + getLevel() + ",xp=" + getXp() + ",area=" + getArea().getNumber() + ",inventory=" + inv + ",started=" + getStarted() + ",equipped_tool=" + (getEquippedTool() != null ? "'" + getEquippedTool().getName() + "'" : "null") + ",equipped_armor=" + (getEquippedArmor() != null ? "'" + getEquippedArmor().getName() + "'" : "null") + ",equipped_sword=" + (getEquippedSword() != null ? "'" +getEquippedSword().getName() + "'" : "null") + " WHERE id=" + getId());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public String formatInventory() {
        StringBuilder inv = new StringBuilder("'");
        for (Item i : getInventory().keySet()) {
            if (i != null) {
                inv.append(i.getName()).append("|").append(getInventory().get(i)).append("---");
            }
        }
        inv.append("'");
        return inv.toString();
    }


    public int getBank() {
        return this.bank;
    }

    public void addToBank(int money) {
        bank += money;
    }

    public void removeFromBank(int money){
        bank -= money;
    }
}
