package de.senseless.betachan.handler;

import de.senseless.betachan.commands.HelloCommand;
import de.senseless.betachan.commands.types.ServerCommand;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.concurrent.ConcurrentHashMap;

public class CommandManager {

    public ConcurrentHashMap<String, ServerCommand> commands;

    public CommandManager() {
        this.commands = new ConcurrentHashMap<>();
        this.commands.put("hi",new HelloCommand());
    }

    public boolean perform(String command, Member member, TextChannel channel, Message message) {

        ServerCommand cmd;

        if ((cmd = this.commands.get(command.toLowerCase())) != null) {
            cmd.performCommand(member, channel, message);
            return true;
        }

        return false;
    }
}
//13