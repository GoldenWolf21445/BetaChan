package de.senseless.betachan.handler;

import de.senseless.betachan.commands.*;
import de.senseless.betachan.commands.types.ServerCommand;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.concurrent.ConcurrentHashMap;

public class CommandManager {

    public ConcurrentHashMap<String, ServerCommand> commands;

    public CommandManager() {
        this.commands = new ConcurrentHashMap<>();
        this.commands.put("help",new HelpCommand());
        this.commands.put("start",new StartCommand());
        this.commands.put("profile",new ProfileCommand());
        this.commands.put("area",new AreaCommand());
    }

    public boolean perform(String command,String[] args, Member member, TextChannel channel, Message message) {

        ServerCommand cmd;

        if ((cmd = this.commands.get(command.toLowerCase())) != null) {
            cmd.performCommand(args,member, channel, message);
            return true;
        }

        return false;
    }
}
//13