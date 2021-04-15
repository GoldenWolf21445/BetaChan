package de.senseless.betachan.commands.misc;

import de.senseless.betachan.commands.types.ServerCommand;
import de.senseless.betachan.handler.UserHandler;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class StartCommand implements ServerCommand {
    @Override
    public void performCommand(String[] args, Member member, TextChannel channel, Message message) {
        if(args.length == 0){
            UserHandler.createNewUser(member);
            channel.sendMessage("You are now registered! Your journey starts now!").queue();
        }
    }
}
