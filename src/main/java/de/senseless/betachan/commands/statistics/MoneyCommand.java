package de.senseless.betachan.commands.statistics;

import de.senseless.betachan.BetaChan;
import de.senseless.betachan.commands.types.ServerCommand;
import de.senseless.betachan.user.User;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import java.time.Instant;
import java.util.Arrays;

public class MoneyCommand implements ServerCommand {
    @Override
    public void performCommand(String[] args, Member member, TextChannel channel, Message message) {
        User u = User.loadUser(member.getId(), member.getEffectiveName());
        if(u.getStarted() == 1) {
            if(args.length == 0){
                EmbedBuilder eb = new EmbedBuilder();
                eb.setTitle("💶Money💶");
                eb.setColor(0x079100);
                eb.setDescription("Money: " + u.getMoney() + " 💶");
                eb.setTimestamp(Instant.now());
                channel.sendMessage(eb.build()).queue();
            } else if(args.length == 3){
                if(args[0].equalsIgnoreCase("pay")){
                    int amount = Integer.parseInt(args[2]);
                    Member m = message.getMentionedMembers().get(0);
                    if(m != member){
                        User userToPay = User.loadUser(m.getId(),m.getEffectiveName());
                        if(userToPay.getStarted() == 1){
                            if(u.getMoney()-amount >= 0) {
                                userToPay.addMoney(amount);
                                u.removeMoney(amount);
                                EmbedBuilder eb = new EmbedBuilder();
                                eb.setDescription(member.getAsMention() + " has payed " + m.getAsMention() + " " + amount + " 💶\n" + member.getAsMention() + " you have now " + u.getMoney() + " 💶\n" + m.getAsMention() + " you have now " + userToPay.getMoney() + " 💶");
                                eb.setColor(0x079100);
                                eb.setTimestamp(Instant.now());
                                eb.setTitle("💶 Payment 💶");
                                u.save();
                                userToPay.save();
                                channel.sendMessage(eb.build()).queue();
                            } else {
                                message.reply("I'm sorry but you dont have a enough money to pay that much!").queue();
                            }
                        } else {
                            message.reply("I'm sorry but the mentioned Member hasn't started his adventure yet").queue();
                        }
                    } else {
                        message.reply("I'm sorry but you need to mention a Member!").queue();
                    }
                } else {
                    message.reply("Usage: " + BetaChan.INSTANCE.prop.getProperty("prefix") + " money {pay} {@user} {amount}").queue();
                }
            } else {
                message.reply("Usage: " + BetaChan.INSTANCE.prop.getProperty("prefix") + " money {pay} {@user} {amount}").queue();
            }
        } else {
            message.reply("I'm sorry but to use this command you need to start your adventure first with '" + BetaChan.INSTANCE.prop.getProperty("prefix") + " start`").queue();
        }
    }
}
