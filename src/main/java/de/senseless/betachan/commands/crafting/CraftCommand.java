package de.senseless.betachan.commands.crafting;

import de.senseless.betachan.BetaChan;
import de.senseless.betachan.commands.types.ServerCommand;
import de.senseless.betachan.item.Item;
import de.senseless.betachan.user.User;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import java.time.Instant;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class CraftCommand implements ServerCommand {
    @Override
    public void performCommand(String[] args, Member member, TextChannel channel, Message message) {
        EmbedBuilder eb = new EmbedBuilder();
        User u = User.loadUser(member.getId(), member.getEffectiveName());
        if (u.getStarted() == 1) {
            if (args.length != 0) {
                StringBuilder item = new StringBuilder();
                for (String arg : args) {
                    item.append(" ").append(arg);
                }
                Item i = Item.getByName(item.substring(1));
                if (i != null) {
                    if (i.isCraftable()) {

                        HashMap<Item, Integer> recipeReq = new LinkedHashMap<>();
                        String recipe = i.getRecipe();
                        String[] items = recipe.split("---");
                        for (String itemAmount : items) {
                            if (itemAmount != null) {
                                String[] splitItemAmount = itemAmount.split("\\|");
                                Item s = Item.getByName(splitItemAmount[0]);
                                if (s != null) {
                                    recipeReq.put(s, Integer.parseInt(splitItemAmount[1]));
                                }
                            }
                        }

                        HashMap<Item, Boolean> reqmet = new LinkedHashMap<>();
                        if (!recipeReq.isEmpty()) {
                            for (Item s : recipeReq.keySet()) {
                                if (u.getInventory().containsKey(s)) {
                                    if (u.getInventory().get(s) >= recipeReq.get(s)) {
                                        reqmet.put(s, true);
                                    } else {
                                        reqmet.put(s, false);
                                    }
                                } else {
                                    reqmet.put(s, false);
                                }
                            }
                            for (Item s : reqmet.keySet()) {
                                if (!reqmet.get(s)) {
                                    int amount = 0;
                                    if (u.getInventory().containsKey(s)) {
                                        amount = u.getInventory().get(s) - recipeReq.get(s);
                                    } else {
                                        amount = recipeReq.get(s);
                                    }

                                    eb.addField(s.getName() + ":", "Not enough Material (Missing: " + amount + ")", false);
                                    eb.setColor(0xff0000);
                                } else {
                                    eb.addField(s.getName() + ":", "Enough Materials", false);
                                }
                            }

                            if (!reqmet.containsValue(false)) {
                                for (Item s : recipeReq.keySet()) {
                                    int newAmount = u.getInventory().get(s) - recipeReq.get(s);
                                    if (newAmount != 0) {
                                        u.getInventory().put(s, newAmount);
                                    } else {
                                        u.getInventory().remove(s);
                                    }
                                }
                                u.addToInventory(i, 1);
                                u.save();
                                BetaChan.INSTANCE.database.onUpdate("UPDATE users SET inventory=" + u.formatInventory() + " WHERE id=" + member.getId());
                                channel.sendMessage("Successfully crafted 1x " + i.getName()).queue();
                            }

                            eb.setTitle("🛠Crafting🛠");
                            eb.setTimestamp(Instant.now());
                            channel.sendMessage(eb.build()).queue();

                        } else {
                            message.reply("It seams to be that this item has no Recipe!").queue();
                        }

                    } else {
                        message.reply("Sorry but this item isn't craftable").queue();
                    }
                } else {
                    message.reply("Sorry but this Item what you want to Craft doesn't exists! \nTry `" + BetaChan.INSTANCE.prop.getProperty("prefix") + " recipes` for more Information").queue();
                }
            } else {
                message.reply("Usage: ``" + BetaChan.INSTANCE.prop.getProperty("prefix") + " craft [item]`").queue();
            }
        } else {
            message.reply("I'm sorry but to use this command you need to start your adventure first with '" + BetaChan.INSTANCE.prop.getProperty("prefix") + " start`").queue();
        }
    }
}
