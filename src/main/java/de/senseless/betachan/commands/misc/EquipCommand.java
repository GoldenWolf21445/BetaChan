package de.senseless.betachan.commands.misc;

import de.senseless.betachan.BetaChan;
import de.senseless.betachan.commands.types.ServerCommand;
import de.senseless.betachan.enums.ToolType;
import de.senseless.betachan.item.Item;
import de.senseless.betachan.user.User;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import java.time.Instant;

public class EquipCommand implements ServerCommand {
    @Override
    public void performCommand(String[] args, Member member, TextChannel channel, Message message) {
        User u = User.loadUser(member.getId(), member.getEffectiveName());
        if(u.getStarted() == 1){
            if(args.length != 0){
                StringBuilder item = new StringBuilder();
                for (String arg : args) {
                    item.append(" ").append(arg);
                }
                Item i = Item.getByName(item.substring(1));
                if(i != null){
                    if(u.getInventory().containsKey(i)){
                        if(u.getInventory().get(i) > 0){
                            if(i.getToolType() != ToolType.SWORD) {
                                Item currentlyEquiped = u.getEquippedTool();
                                if (currentlyEquiped != null) {
                                    u.addToInventory(currentlyEquiped, 1);
                                }
                                if (u.getInventory().get(i) - 1 == 0) {
                                    u.getInventory().remove(i);
                                } else {
                                    u.getInventory().put(i, u.getInventory().get(i) - 1);
                                }
                                u.setEquippedTool(i);
                                u.save();
                                EmbedBuilder eb = new EmbedBuilder();
                                eb.setTitle("Item Equiped");
                                eb.setTimestamp(Instant.now());
                                eb.addField("Item Equipped", i.getName(), false);
                                channel.sendMessage(eb.build()).queue();
                            } else {
                                Item currentlyEquiped = u.getEquippedSword();
                                if (currentlyEquiped != null) {
                                    u.addToInventory(currentlyEquiped, 1);
                                }
                                if (u.getInventory().get(i) - 1 == 0) {
                                    u.getInventory().remove(i);
                                } else {
                                    u.getInventory().put(i, u.getInventory().get(i) - 1);
                                }
                                u.setEquippedSword(i);
                                u.save();
                                EmbedBuilder eb = new EmbedBuilder();
                                eb.setTitle("Sword Equiped");
                                eb.setTimestamp(Instant.now());
                                eb.addField("Sword Equipped", i.getName(), false);
                                channel.sendMessage(eb.build()).queue();
                            }
                        } else {
                            message.reply("I'm sorry but you don't own this Item!").queue();
                        }
                    } else {
                        message.reply("I'm sorry but you don't own this Item!").queue();
                    }
                } else {
                    message.reply("I'm sorry but i can't find that Item!").queue();
                }
            } else {
                message.reply("Usage: " + BetaChan.INSTANCE.prop.getProperty("prefix")+ " equip [item]").queue();
            }
        } else {
            message.reply("I'm sorry but to use this command you need to start your adventure first with `" + BetaChan.INSTANCE.prop.getProperty("prefix") + " start`").queue();
        }
    }
}
