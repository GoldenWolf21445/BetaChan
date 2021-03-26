package de.senseless.betachan.commands;

import de.senseless.betachan.commands.types.ServerCommand;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class HelloCommand implements ServerCommand {
    @Override
    public void performCommand(Member member, TextChannel channel, Message message) {
        message.reply("Hello").queue();
    }
}
