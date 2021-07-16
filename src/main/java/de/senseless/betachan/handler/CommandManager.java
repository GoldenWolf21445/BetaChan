package de.senseless.betachan.handler;

import de.senseless.betachan.commands.administrative.AdminCommand;
import de.senseless.betachan.commands.crafting.*;
import de.senseless.betachan.commands.gathering.*;
import de.senseless.betachan.commands.misc.*;
import de.senseless.betachan.commands.statistics.*;
import de.senseless.betachan.commands.types.ServerCommand;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

public class CommandManager {

    public ConcurrentHashMap<String, ServerCommand> commands;

    public CommandManager() {
        this.commands = new ConcurrentHashMap<>();
        this.commands.put("help", new HelpCommand());
        this.commands.put("start", new StartCommand());
        this.commands.put("profile", new ProfileCommand());
        this.commands.put("p", new ProfileCommand());
        this.commands.put("area", new AreaCommand());
        this.commands.put("craft", new CraftCommand());
        this.commands.put("recipes", new RecipesCommand());
        this.commands.put("chop", new ChopCommand());
        this.commands.put("hunt", new HuntCommand());
        this.commands.put("mine", new MineCommand());
        this.commands.put("pickup", new PickupCommand());
        this.commands.put("cook", new CookCommand());
        this.commands.put("eat", new EatCommand());
        this.commands.put("inventory", new InventoryCommand());
        this.commands.put("inv", new InventoryCommand());
        this.commands.put("enchant", new EnchantCommand());
        this.commands.put("dungeon", new DungeonCommand());
        this.commands.put("equip", new EquipCommand());
        this.commands.put("smelt", new SmeltCommand());
        this.commands.put("shop", new ShopCommand());
        this.commands.put("buy", new ShopCommand());
        this.commands.put("admin", new AdminCommand());
        this.commands.put("bank", new BankCommand());
        this.commands.put("money", new MoneyCommand());
    }

    public boolean perform(String command, String[] args, Member member, TextChannel channel, Message message) {

        ServerCommand cmd;

        if ((cmd = this.commands.get(command.toLowerCase())) != null) {
            cmd.performCommand(args, member, channel, message);

            System.out.println();
            return true;
        }

        return false;
    }


    private String getTime(){
        Date d = Date.from(Instant.now());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:ss:mm");
        return sdf.format(d);
    }
}