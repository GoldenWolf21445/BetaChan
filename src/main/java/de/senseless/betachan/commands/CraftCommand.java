package de.senseless.betachan.commands;

import de.senseless.betachan.BetaChan;
import de.senseless.betachan.commands.types.ServerCommand;
import de.senseless.betachan.item.Item;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class CraftCommand implements ServerCommand {
    @Override
    public void performCommand(String[] args, Member member, TextChannel channel, Message message) {
        EmbedBuilder eb = new EmbedBuilder();
        if(args.length != 0) {
            Item i = Item.getByName(args[0]);
            if (i != null) {
                if(i.isCraftable()){

                } else {
                    message.reply("Sorry but this item isn't craftable").queue();
                }
            } else {
                message.reply("Sorry but this Item what you want to Craft doesn't exists! \nTry `" + BetaChan.INSTANCE.prop.getProperty("prefix") + " recipes` for more Information").queue();
            }
        } else {
            message.reply("Usage: ``" + BetaChan.INSTANCE.prop.getProperty("prefix") + " craft [item]`").queue();
        }
    }
}
