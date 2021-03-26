package de.senseless.betachan.commands;

import de.senseless.betachan.commands.types.ServerCommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;

public class HelpCommand implements ServerCommand {

    @Override
    public void performCommand(String[] args, Member member, TextChannel channel, Message message) {
        EmbedBuilder builder = new EmbedBuilder();
        if(args[0].equalsIgnoreCase("arena")){
            builder.addField("Command","Arena",false);
        } else if(args[0].equalsIgnoreCase("area")){
            builder.addField("Command","Area",false);
        } else {
            builder.setDescription("Command help for more Help");
        }
        builder.setColor(Color.decode("#ff6600"));
        builder.setFooter("Requested by " + member.getEffectiveName(),member.getUser().getAvatarUrl());
        channel.sendMessage(builder.build()).queue();
    }
}
