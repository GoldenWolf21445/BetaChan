package de.senseless.betachan.commands.gathering;

import de.senseless.betachan.BetaChan;
import de.senseless.betachan.area.Area;
import de.senseless.betachan.commands.types.ServerCommand;
import de.senseless.betachan.enums.ToolType;
import de.senseless.betachan.handler.ItemHandler;
import de.senseless.betachan.item.Item;
import de.senseless.betachan.user.User;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import java.time.Instant;
import java.util.LinkedList;
import java.util.List;

public class MineCommand implements ServerCommand {
    @Override
    public void performCommand(String[] args, Member member, TextChannel channel, Message message) {
        User u = User.loadUser(member.getId(), member.getEffectiveName());

        List<Item> mineableItems = new LinkedList<>();

        if(u.getStarted() == 1){
            if(args.length == 0) {
                Item tool = u.getEquippedTool();
                if (tool != null) {
                    if (tool.getToolType() == ToolType.PICKAXE) {
                        long cooldown = System.currentTimeMillis() - u.getCooldown("mine");
                        if (cooldown >= 10*1000) {
                            int amount = (int) (Math.round(Math.random() * 2) + 1);

                            Area a = u.getArea();

                            if (a.getNumber() == 1) {
                                mineableItems.add(ItemHandler.STONE);
                            } else {
                                mineableItems.add(ItemHandler.STONE);
                                mineableItems.add(ItemHandler.COAL);
                                mineableItems.add(ItemHandler.RAWIRON);
                            }
                            int item = (int) Math.ceil(Math.random() * mineableItems.size()) - 1;

                            u.addToInventory(mineableItems.get(item), amount);
                            u.save();
                            EmbedBuilder eb = new EmbedBuilder();
                            eb.setTimestamp(Instant.now());
                            eb.setTitle("⛏ Mine ⛏");
                            eb.setDescription("Mined " + amount + " " + mineableItems.get(item).getName() + "!");
                            eb.setColor(0x006900);
                            channel.sendMessage(eb.build()).queue();
                            u.setCooldown("mine");
                            u.save();
                        } else {
                            message.reply("I'm sorry but you need to wait " + (10-(cooldown/1000)) + " sec!").queue();
                        }
                    } else {
                        message.reply("I'm sorry but without an Pickaxe you can't mine Ores").queue();
                    }
                } else {
                    message.reply("You have no Tool equipped").queue();
                }
            } else {
                message.reply("Usage: " + BetaChan.INSTANCE.prop.getProperty("prefix")+ " chop").queue();
            }
        } else {
            message.reply("I'm sorry but to use this command you need to start your adventure first with '" + BetaChan.INSTANCE.prop.getProperty("prefix") + " start`").queue();
        }
    }
}
