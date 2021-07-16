package de.senseless.betachan.commands.crafting;

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

public class CookCommand implements ServerCommand {
    @Override
    public void performCommand(String[] args, Member member, TextChannel channel, Message message) {
        User user = User.loadUser(member.getId(), member.getEffectiveName());
        if(user.getStarted() == 1){
            if(args.length == 2){
                if(args[0].equalsIgnoreCase("coal")){
                    String whatToCook = args[1];
                    Item itemWhatToCook = Item.getByName("Cooked " +whatToCook);
                    if(itemWhatToCook != null){
                        if (itemWhatToCook.isCraftable()) {
                            Item rawItemToCook = Item.getByName(whatToCook);
                            if(rawItemToCook != null){
                                if(user.getInventory().containsKey(rawItemToCook)){
                                    if(user.getInventory().get(rawItemToCook) > 0) {
                                        EmbedBuilder eb = new EmbedBuilder();
                                        eb.setTitle("🍳 Cooking 🍳");
                                        eb.setTimestamp(Instant.now());
                                        eb.setColor(0xff6600);
                                        int amountAvailable = user.getInventory().get(rawItemToCook);
                                        if(amountAvailable - 8 < 0){
                                            user.getInventory().remove(rawItemToCook);
                                            user.addToInventory(itemWhatToCook,8-(amountAvailable-8));
                                            eb.setDescription("Successfully cooked " + (8-(amountAvailable-8)) + " " + rawItemToCook.getName());
                                        } else {
                                            user.getInventory().put(rawItemToCook,amountAvailable - 8);
                                            user.addToInventory(itemWhatToCook,8);
                                            eb.setDescription("Successfully cooked 8 " + rawItemToCook.getName());
                                        }
                                        user.getInventory().put(ItemHandler.COAL,user.getInventory().get(ItemHandler.COAL)-1);
                                        channel.sendMessage(eb.build()).queue();
                                        user.save();
                                    } else {
                                        message.reply("You don't have enough Raw Items to cook this").queue();
                                    }
                                } else {
                                    message.reply("You don't have enough Raw Items to cook this").queue();
                                }
                            }
                        } else {
                            message.reply("You can't cook this Item").queue();
                        }
                    } else {
                        message.reply("I'm sorry but the Item you want to cook doesn't exists").queue();
                    }
                } else if(args[0].equalsIgnoreCase("wood")){
                    String whatToCook = args[1];
                    Item itemWhatToCook = Item.getByName("Cooked " +whatToCook);
                    StringBuilder a = new StringBuilder();
                    for (String s : args) {
                        a.append(s).append(", ");
                    }
                    System.out.println(a);
                    if(itemWhatToCook != null){
                        if (itemWhatToCook.isCraftable()) {
                            Item rawItemToCook = Item.getByName(whatToCook);
                            if(rawItemToCook != null){
                                if(user.getInventory().containsKey(rawItemToCook)){
                                    if(user.getInventory().get(rawItemToCook) > 0) {
                                        EmbedBuilder eb = new EmbedBuilder();
                                        eb.setTitle("🍳 Cooking 🍳");
                                        eb.setTimestamp(Instant.now());
                                        eb.setColor(0xff6600);
                                        int amountAvailable = user.getInventory().get(rawItemToCook);
                                        if(amountAvailable - 4 < 0){
                                            user.getInventory().remove(rawItemToCook);
                                            user.addToInventory(itemWhatToCook,4-(amountAvailable-4));
                                            eb.setDescription("Successfully cooked " + (4-(amountAvailable-4)) + " " + rawItemToCook.getName());
                                        } else {
                                            user.getInventory().put(rawItemToCook,amountAvailable - 4);
                                            user.addToInventory(itemWhatToCook,4);
                                            eb.setDescription("Successfully cooked 4 " + rawItemToCook.getName());
                                        }
                                        channel.sendMessage(eb.build()).queue();
                                        user.getInventory().put(ItemHandler.WOOD,user.getInventory().get(ItemHandler.WOOD)-1);
                                        user.save();
                                    } else {
                                        message.reply("You don't have enough Raw Items to cook this").queue();
                                    }
                                } else {
                                    message.reply("You don't have enough Raw Items to cook this").queue();
                                }
                            }
                        } else {
                            message.reply("You can't cook this Item").queue();
                        }
                    } else {
                        message.reply("I'm sorry but the Item you want to cook doesn't exists").queue();
                    }
                } else {
                    message.reply("Please select a Fuel you want to use to Cook your items").queue();
                }
            } else {
                message.reply("Usage: **" + BetaChan.INSTANCE.prop.getProperty("prefix") + " cook [coal/wood] [item]**").queue();
            }
        } else {
            message.reply("I'm sorry but to use this command you need to start your adventure first with '" + BetaChan.INSTANCE.prop.getProperty("prefix") + " start`").queue();
        }
    }
}
