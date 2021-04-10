package de.senseless.betachan.commands;

import de.senseless.betachan.BetaChan;
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
        if(args.length == 0) {
            builder.setTitle("Commands");
            String prefix = BetaChan.INSTANCE.prop.getProperty("prefix");
            builder.setDescription("More help use " + prefix + " help [command]\nAdd `"+prefix+"` before any command!");
            builder.addField("Statistics","`profile`,`area [#]`",false);
            builder.addField("Fighting","`hunt[WIP]`",false);
        } else if(args.length == 1) {
            if (args[0].equalsIgnoreCase("arena")) {
                builder.setTitle("Arena");
                builder.addField("Command", "Arena", false);
            } else if (args[0].equalsIgnoreCase("area")) {
                builder.setTitle("Area");
                builder.addField("Areas:", "\n1\n2\n3\n4\n5", false);
            } else {
                builder.setDescription("Command help for more Help");
            }
        }
        builder.setColor(Color.decode("#ff6600"));
        builder.setFooter("Requested by " + member.getUser().getName(),member.getUser().getAvatarUrl());
        channel.sendMessage(builder.build()).queue();
    }
}
