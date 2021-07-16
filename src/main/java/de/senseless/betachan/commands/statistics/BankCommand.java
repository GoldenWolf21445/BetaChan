package de.senseless.betachan.commands.statistics;

import de.senseless.betachan.BetaChan;
import de.senseless.betachan.commands.types.ServerCommand;
import de.senseless.betachan.user.User;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class BankCommand implements ServerCommand {
    @Override
    public void performCommand(String[] args, Member member, TextChannel channel, Message message) {
        User u = User.loadUser(member.getId(), member.getEffectiveName());
        if(u.getStarted() == 1) {
            if (args.length == 0) {
                EmbedBuilder eb = new EmbedBuilder();
                eb.setColor(0x660000);
                eb.setTitle("💶 Bank 💶");
                eb.setDescription("Your Bank balance: " + u.getBank() + " 💶");
                channel.sendMessage(eb.build()).queue();
            } else if (args.length == 2) {
                //beta bank deposit [zahl]
                if (args[0].equalsIgnoreCase("deposit")) {
                    if(args[1].equalsIgnoreCase("all")){
                        u.addToBank(u.getMoney());
                        u.setMoney(0);

                        EmbedBuilder eb = new EmbedBuilder();
                        eb.setColor(0x660000);
                        eb.setTitle("💶 Bank 💶");
                        eb.setDescription("Your Bank balance: " + u.getBank() + " 💶\nYour pocket balance: 0💶");
                        channel.sendMessage(eb.build()).queue();
                    } else {
                        int amount = Integer.parseInt(args[1]);
                        if (u.getMoney() - amount < 0) {
                            u.addToBank(u.getMoney());
                            u.setMoney(0);

                            EmbedBuilder eb = new EmbedBuilder();
                            eb.setColor(0x660000);
                            eb.setTitle("💶 Bank 💶");
                            eb.setDescription("Your Bank balance: " + u.getBank() + " 💶\nYour pocket balance: 0💶");
                            channel.sendMessage(eb.build()).queue();
                        } else {
                            u.addToBank(amount);
                            u.removeMoney(amount);

                            EmbedBuilder eb = new EmbedBuilder();
                            eb.setColor(0x660000);
                            eb.setTitle("💶 Bank 💶");
                            eb.setDescription("Your Bank balance: " + u.getBank() + " 💶\nYour pocket balance: "+(u.getMoney())+"💶");
                            channel.sendMessage(eb.build()).queue();
                        }
                    }
                    u.save();
                } else if (args[0].equalsIgnoreCase("withdraw")) {
                    if(args[1].equalsIgnoreCase("all")) {
                        u.addMoney(u.getBank());
                        u.removeFromBank(u.getBank());
                        EmbedBuilder eb = new EmbedBuilder();
                        eb.setColor(0x660000);
                        eb.setTitle("💶 Bank 💶");
                        eb.setDescription("Your Bank balance: " + u.getBank() + " 💶\nYour pocket balance: "+(u.getMoney())+"💶");
                        channel.sendMessage(eb.build()).queue();
                    } else {
                        int amount = Integer.parseInt(args[1]);
                        if(u.getBank() - amount < 0) {
                            u.addMoney(u.getBank());
                            u.removeFromBank(u.getBank());
                            EmbedBuilder eb = new EmbedBuilder();
                            eb.setColor(0x660000);
                            eb.setTitle("💶 Bank 💶");
                            eb.setDescription("Your Bank balance: " + u.getBank() + " 💶\nYour pocket balance: "+(u.getMoney())+"💶");
                            channel.sendMessage(eb.build()).queue();
                        } else {
                            u.addMoney(amount);
                            u.removeFromBank(amount);
                            EmbedBuilder eb = new EmbedBuilder();
                            eb.setColor(0x660000);
                            eb.setTitle("💶 Bank 💶");
                            eb.setDescription("Your Bank balance: " + u.getBank() + " 💶\nYour pocket balance: "+(u.getMoney())+"💶");
                            channel.sendMessage(eb.build()).queue();
                        }
                    }
                    u.save();

                }
            } else {
                message.reply("Usage: " + BetaChan.INSTANCE.prop.getProperty("prefix") + " bank {deposit/withdraw} {amount}").queue();
            }
        } else {
            message.reply("I'm sorry but to use this command you need to start your adventure first with '" + BetaChan.INSTANCE.prop.getProperty("prefix") + " start`").queue();
        }
    }

    //beta bank deposit 1234
}
