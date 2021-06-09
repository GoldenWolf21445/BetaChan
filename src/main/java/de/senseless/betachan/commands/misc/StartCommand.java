package de.senseless.betachan.commands.misc;

import de.senseless.betachan.BetaChan;
import de.senseless.betachan.commands.types.ServerCommand;
import de.senseless.betachan.user.User;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class StartCommand implements ServerCommand {
    @Override
    public void performCommand(String[] args, Member member, TextChannel channel, Message message) {
        if (args.length == 0) {
            User u = User.loadUser(member.getId(), member.getEffectiveName());
            if(u.getStarted() != 1) {
                channel.sendMessage("You are now registered! Your journey starts now!").queue();
                u.setStarted(1);
                BetaChan.INSTANCE.database.onUpdate("UPDATE users SET started=1 WHERE id="+member.getId());
            }
        }
    }
}
