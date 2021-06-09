package de.senseless.betachan.commands.misc;

import de.senseless.betachan.BetaChan;
import de.senseless.betachan.commands.types.ServerCommand;
import de.senseless.betachan.item.Item;
import de.senseless.betachan.user.User;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import java.time.Instant;

public class EatCommand implements ServerCommand {
    @Override
    public void performCommand(String[] args, Member member, TextChannel channel, Message message) {
        User u = User.loadUser(member.getId(), member.getEffectiveName());
        if(u.getStarted() == 1){
            if(args.length != 0){
                StringBuilder itemBuilder = new StringBuilder();
                for (String arg : args){
                    itemBuilder.append(" ").append(arg);
                }
                Item i = Item.getByName(itemBuilder.substring(1));
                if(i != null){
                    if(i.getName().startsWith("Cooked")){
                        if(u.getLife() != u.getMaxlife()) {
                            u.getInventory().put(i, (u.getInventory().get(i) - 1));
                            u.setLife(Math.min(u.getLife() + 25, u.getMaxlife()));
                            u.save();
                            EmbedBuilder eb = new EmbedBuilder();
                            eb.setColor(0xff6600);
                            eb.setTimestamp(Instant.now());
                            eb.setDescription("Successfully eaten 1 " + i.getName() + "!\n Healed 25HP or got to Max health!");
                            channel.sendMessage(eb.build()).queue();
                        } else {
                            message.reply("You really want to eat this your health is at max!").queue();
                        }
                    }
                } else {
                    message.reply("I'm sorry but this item doesn't exists!").queue();
                }
            } else {
                message.reply("Usage: " + BetaChan.INSTANCE.prop.getProperty("prefix") + " eat [item]").queue();
            }
        } else {
            message.reply("I'm sorry but to use this command you need to start your adventure first with `" + BetaChan.INSTANCE.prop.getProperty("prefix") + " start`").queue();
        }
    }
}
