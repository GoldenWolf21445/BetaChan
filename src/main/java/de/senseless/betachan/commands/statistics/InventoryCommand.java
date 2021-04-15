package de.senseless.betachan.commands.statistics;

import de.senseless.betachan.BetaChan;
import de.senseless.betachan.commands.types.ServerCommand;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class InventoryCommand implements ServerCommand {
    @Override
    public void performCommand(String[] args, Member member, TextChannel channel, Message message) {
        if(args.length == 0 ){

        } else if(args.length == 1){

        } else {
            message.reply("Usage: " + BetaChan.INSTANCE.prop.getProperty("prefix")+ " inventory {@user}").queue();
        }
    }
}
