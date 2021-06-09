package de.senseless.betachan.commands.misc;

import de.senseless.betachan.BetaChan;
import de.senseless.betachan.commands.types.ServerCommand;
import de.senseless.betachan.handler.ItemHandler;
import de.senseless.betachan.item.Item;
import de.senseless.betachan.user.User;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import java.time.Instant;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public class ShopCommand implements ServerCommand {
    @Override
    public void performCommand(String[] args, Member member, TextChannel channel, Message message) {
        HashMap<Item,Integer> availableShopItems = new LinkedHashMap<>();
        availableShopItems.put(ItemHandler.WOOD,20);
        availableShopItems.put(ItemHandler.POTION,100);
        availableShopItems.put(ItemHandler.BASKET,200);
        User u = User.loadUser(member.getId(), member.getEffectiveName());
        if(u.getStarted() == 1){
            if (args.length == 0) {
                EmbedBuilder eb = new EmbedBuilder();
                eb.addField("💳Shop💳","`Wood`: 20💶\n`Potion`: 100💶\n`Basket`: 200💶",false);
                eb.addField("Example","`beta shop Wood` - Pay 20💶, receive 1 Wood\n`beta shop Potion 4` - Pay 400💶, receive 4 Potion",false);
                eb.setColor(0x006600);
                eb.setTimestamp(Instant.now());
                channel.sendMessage(eb.build()).queue();
            } else if (args.length == 1) {
                String item = args[0];
                Item i = Item.getByName(item);
                if(i != null) {
                    if (availableShopItems.containsKey(i)) {
                        int price = availableShopItems.get(i);
                        if(u.getMoney()-price >= 0){
                            u.removeMoney(price);
                            u.addToInventory(i,1);
                            u.save();
                            channel.sendMessage("You successfully bought 1x " + i.getName()).queue();
                        } else {
                            message.reply("I'm sorry but you dont have enough Money to buy that!").queue();
                        }
                    } else {
                        message.reply("I'm sorry but this Item isn't available to buy!").queue();
                    }
                } else {
                    message.reply("I'm Sorry but this Item doesn't exist!").queue();
                }
            } else if (args.length == 2) {
                String item = args[0];
                int amount = Integer.parseInt(args[1]);
                Item i = Item.getByName(item);
                if(i != null) {
                    if (availableShopItems.containsKey(i)) {
                        int price = availableShopItems.get(i) * amount;
                        if(u.getMoney()-price >= 0){
                            u.removeMoney(price);
                            u.addToInventory(i,amount);
                            u.save();
                            channel.sendMessage("You successfully bought "+amount+"x " + i.getName()).queue();
                        } else {
                            message.reply("I'm sorry but you dont have enough Money to buy that!").queue();
                        }
                    } else {
                        message.reply("I'm sorry but this Item isn't available to buy!").queue();
                    }
                } else {
                    message.reply("I'm Sorry but this Item doesn't exist!").queue();
                }
            } else {
                message.reply("Usage: ``" + BetaChan.INSTANCE.prop.getProperty("prefix") + " shop [item] {amount}`").queue();
            }
        } else {
            message.reply("I'm sorry but to use this command you need to start your adventure first with `" + BetaChan.INSTANCE.prop.getProperty("prefix") + " start`").queue();
        }
    }
}
