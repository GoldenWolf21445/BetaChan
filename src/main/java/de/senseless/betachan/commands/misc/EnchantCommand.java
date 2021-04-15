package de.senseless.betachan.commands.misc;

import de.senseless.betachan.commands.types.ServerCommand;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class EnchantCommand implements ServerCommand {
    @Override
    public void performCommand(String[] args, Member member, TextChannel channel, Message message) {
        message.reply("Command Work in Progress").queue();
    }
}
