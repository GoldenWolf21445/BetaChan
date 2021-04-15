package de.senseless.betachan.commands.misc;

import de.senseless.betachan.BetaChan;
import de.senseless.betachan.commands.types.ServerCommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;

public class HelpCommand implements ServerCommand {
    //TODO: Finish it
    @Override
    public void performCommand(String[] args, Member member, TextChannel channel, Message message) {
        EmbedBuilder builder = new EmbedBuilder();
        if (args.length == 0) {
            builder.setTitle("Commands");
            String prefix = BetaChan.INSTANCE.prop.getProperty("prefix");
            builder.setDescription("More help use " + prefix + " help [command]\nAdd `" + prefix + "` before any command!\nTo Start your Adventure use `" + prefix + " start`");
            builder.addField("Statistics", "`profile`,`area [#]`,`inventory {@user}`", false);
            builder.addField("Gathering", "`hunt`,`mine`,`chop`,`pickup`", false);
            builder.addField("Crafting", "`craft [item]`,`cook [item]`,`recipes [#]`,`smelt [amount]`", false);
            builder.addField("Miscellaneous", "`eat [item]`,`help {cmd}`,`start`,`dungeon [@user,...]`,`enchant [#]`,\n`equip [item]`", false);
        } else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("dungeon")) {
                builder.setTitle("Dungeon");
                builder.addField("Command", "Dungeon", false);
                builder.addField("Usage:", "dungeon [@user,...]", false);
                builder.addField("Information:", "Used to unlock the next Area", false);
            } else if (args[0].equalsIgnoreCase("area")) {
                builder.setTitle("Area");
                builder.addField("Command", "Area", false);
                builder.addField("Usage:", "area [#]", false);
                builder.addField("Available areas:", "1,2,3,4,5", false);
                builder.addField("Information:", "Used to get information about an area", false);
            } else if (args[0].equalsIgnoreCase("hunt")) {
                builder.setTitle("Hunt");
                builder.addField("Command", "Hunt", false);
                builder.addField("Usage:", "hunt", false);
                builder.addField("Information:", "Used to slay mobs to get special resources", false);
            } else if (args[0].equalsIgnoreCase("craft")) {
                builder.setTitle("Craft");
                builder.addField("Command", "Craft", false);
                builder.addField("Usage:", "craft [item]", false);
                builder.addField("Information:", "Used to Craft your tools to gather better resources and Slay harder mobs", false);
            } else if (args[0].equalsIgnoreCase("profile")) {
                builder.setTitle("Profile");
                builder.addField("Command", "Profile", false);
                builder.addField("Usage:", "profile {@user}", false);
                builder.addField("Information:", "Used to Check your/@user's Stats", false);
            } else if (args[0].equalsIgnoreCase("mine")) {
                builder.setTitle("Mine");
                builder.addField("Command", "Mine", false);
                builder.addField("Usage:", "mine", false);
                builder.addField("Information:", "Used to mine Stone and Ore", false);
            } else if (args[0].equalsIgnoreCase("pickup")) {
                builder.setTitle("Pickup");
                builder.addField("Command", "Pickup", false);
                builder.addField("Usage:", "pickup", false);
                builder.addField("Information:", "Used to Check your/@user's Stats", false);
            } else if (args[0].equalsIgnoreCase("smelt")) {
                builder.setTitle("Profile");
                builder.addField("Command", "Profile", false);
                builder.addField("Usage:", "smelt [amount]", false);
                builder.addField("Information:", "Used to Check your/@user's Stats", false);
            } else if (args[0].equalsIgnoreCase("cook")) {
                builder.setTitle("Profile");
                builder.addField("Command", "Profile", false);
                builder.addField("Usage:", "profile {@user}", false);
                builder.addField("Information:", "Used to Check your/@user's Stats", false);
            } else if (args[0].equalsIgnoreCase("eat")) {
                builder.setTitle("Profile");
                builder.addField("Command", "Profile", false);
                builder.addField("Usage:", "profile {@user}", false);
                builder.addField("Information:", "Used to Check your/@user's Stats", false);
            } else {
                builder.setDescription("Command help for more Help");
            }
        }
        builder.setColor(Color.decode("#ff6600"));
        builder.setFooter("Requested by " + member.getUser().getName() + " | [] = Required, {} = Optional", member.getUser().getAvatarUrl());
        channel.sendMessage(builder.build()).queue();
    }
}
