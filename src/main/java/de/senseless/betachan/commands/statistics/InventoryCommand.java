package de.senseless.betachan.commands.statistics;

import de.senseless.betachan.BetaChan;
import de.senseless.betachan.commands.types.ServerCommand;
import de.senseless.betachan.item.Item;
import de.senseless.betachan.user.User;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;

public class InventoryCommand implements ServerCommand {
    @Override
    public void performCommand(String[] args, Member member, TextChannel channel, Message message) {
        if(args.length == 0 ){
            User user = User.getByID(member.getId());
            if (user != null) {
                StringBuilder inv = new StringBuilder();
                for (Item i : user.getInventory().keySet()) {
                    inv.append("\n").append(user.getInventory().get(i)).append("x ").append(i.getName());
                }
                EmbedBuilder eb = new EmbedBuilder();
                eb.setColor(Color.decode("#ff6600"));
                eb.setTitle(member.getUser().getName() + "'s Inventory");
                if(!user.getInventory().isEmpty()) {
                    eb.addField("Inventory", inv.toString(), false);
                } else {
                    eb.addField("Inventory", "Empty", false);
                }
                channel.sendMessage(eb.build()).queue();
            } else {
                message.reply("I'm sorry but to use this command you need to start your adventure first with '" + BetaChan.INSTANCE.prop.getProperty("prefix") + " start`").queue();
            }
        } else if(args.length == 1){
            Member m = message.getMentionedMembers().get(0);
            if(m != null) {
                User user = User.getByID(m.getId());
                if (user != null) {
                    StringBuilder inv = new StringBuilder();
                    for (Item i : user.getInventory().keySet()) {
                        inv.append("\n").append(user.getInventory().get(i)).append("x ").append(i.getName());
                    }
                    EmbedBuilder eb = new EmbedBuilder();
                    eb.setColor(Color.decode("#ff6600"));
                    eb.setTitle(m.getUser().getName() + "'s Inventory");
                    if(!user.getInventory().isEmpty()) {
                        eb.addField("Inventory", inv.toString(), false);
                    } else {
                        eb.addField("Inventory", "Empty", false);
                    }
                    channel.sendMessage(eb.build()).queue();
                } else {
                    message.reply("I'm sorry but this Player hasn't started the RPG yet!").queue();
                }
            } else {
                message.reply("I'm sorry but I need to know which Inventory you want to see!").queue();
            }

        } else {
            message.reply("Usage: " + BetaChan.INSTANCE.prop.getProperty("prefix")+ " inventory {@user}").queue();
        }
    }
}
