package de.senseless.betachan.handler;

import de.senseless.betachan.commands.crafting.CookCommand;
import de.senseless.betachan.commands.crafting.CraftCommand;
import de.senseless.betachan.commands.crafting.RecipesCommand;
import de.senseless.betachan.commands.gathering.ChopCommand;
import de.senseless.betachan.commands.gathering.HuntCommand;
import de.senseless.betachan.commands.gathering.MineCommand;
import de.senseless.betachan.commands.gathering.PickupCommand;
import de.senseless.betachan.commands.misc.*;
import de.senseless.betachan.commands.statistics.AreaCommand;
import de.senseless.betachan.commands.statistics.InventoryCommand;
import de.senseless.betachan.commands.statistics.ProfileCommand;
import de.senseless.betachan.commands.types.ServerCommand;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.concurrent.ConcurrentHashMap;

public class CommandManager {

    public ConcurrentHashMap<String, ServerCommand> commands;

    public CommandManager() {
        this.commands = new ConcurrentHashMap<>();
        this.commands.put("help", new HelpCommand());
        this.commands.put("start", new StartCommand());
        this.commands.put("profile", new ProfileCommand());
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
        this.commands.put("enchant", new EnchantCommand());
        this.commands.put("dungeon", new DungeonCommand());
        this.commands.put("equip", new EquipCommand());
    }

    public boolean perform(String command, String[] args, Member member, TextChannel channel, Message message) {

        ServerCommand cmd;

        if ((cmd = this.commands.get(command.toLowerCase())) != null) {
            cmd.performCommand(args, member, channel, message);
            return true;
        }

        return false;
    }
}