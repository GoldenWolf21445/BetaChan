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

public class RecipesCommand implements ServerCommand {
    @Override
    public void performCommand(String[] args, Member member, TextChannel channel, Message message) {
        User u = User.loadUser(member.getId(), member.getEffectiveName());
        if(u.getStarted() == 1) {
            if(args.length == 0) {
                EmbedBuilder embedBuilder = new EmbedBuilder();
                for(Item it: Item.getItems()){
                    if(it.isCraftable() && !it.getRecipe().equals("")) {
                        StringBuilder recipe = new StringBuilder();
                        String[] components = it.getRecipe().split("---");
                        for (String component : components) {
                            String[] splittedComps = component.split("\\|");
                            Item a = Item.getByName(splittedComps[0]);
                            recipe.append(", ").append(splittedComps[1]).append("x ").append(a.getName());
                        }
                        embedBuilder.addField(it.getName(), recipe.substring(2), false);
                    }
                }

                embedBuilder.setColor(0xff6600);
                embedBuilder.setTimestamp(Instant.now());
                embedBuilder.setTitle("Recipes");

                channel.sendMessage(embedBuilder.build()).queue();
            } else {
                StringBuilder itemBuilder = new StringBuilder();
                for (String arg:args){
                    itemBuilder.append(" ").append(arg);
                }

                Item item = Item.getByName(itemBuilder.substring(1));

                if(item != null){
                    if(item.isCraftable()){
                        if(!item.getRecipe().equals("")){
                            StringBuilder recipe = new StringBuilder();
                            String[] components = item.getRecipe().split("---");
                            for (String component : components) {
                                String[] splittedComps = component.split("\\|");
                                Item a = Item.getByName(splittedComps[0]);
                                recipe.append(", ").append(splittedComps[1]).append("x ").append(a.getName());
                            }

                            EmbedBuilder eb = new EmbedBuilder();
                            eb.setTitle("Recipe - " + item.getName());
                            eb.setDescription(recipe.substring(2));
                            eb.setTimestamp(Instant.now());
                            eb.setColor(0xff6600);
                            channel.sendMessage(eb.build()).queue();
                        } else {
                            message.reply("This item has no recipe").queue();
                        }
                    } else {
                        message.reply("This item isn't craftable").queue();
                    }
                } else {
                    message.reply("This item doesn't exist").queue();
                }
            }
        } else {
            message.reply("I'm sorry but to use this command you need to start your adventure first with '" + BetaChan.INSTANCE.prop.getProperty("prefix") + " start`").queue();
        }
    }
}
