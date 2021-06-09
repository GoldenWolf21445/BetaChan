package de.senseless.betachan.commands.statistics;

import de.senseless.betachan.BetaChan;
import de.senseless.betachan.commands.types.ServerCommand;
import de.senseless.betachan.user.User;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;

public class ProfileCommand implements ServerCommand {
    @Override
    public void performCommand(String[] args, Member member, TextChannel channel, Message message) {
        if(args.length == 0) {
            EmbedBuilder builder = new EmbedBuilder();
            builder.setThumbnail(member.getUser().getAvatarUrl());
            User u = User.loadUser(member.getId(), member.getEffectiveName());
            if(u.getStarted() == 1){
                builder.setTitle(member.getUser().getName() + "'s profile");
                builder.addField(new MessageEmbed.Field("⚔Stats🛡","\n⚔**ATK**: " + u.getAtk() + "\n🛡**DEF**: " + u.getDef() + "\n❤**Life**: " + u.getLife() + "/" + u.getMaxlife(),false));
                builder.addField(new MessageEmbed.Field("⭐Progress⭐","\n**Level**: " + u.getLevel() + "\n**XP**: " + u.getXp() + "\n**Area:** " + u.getArea().getNumber(),false));
                builder.addField(new MessageEmbed.Field("🪓Equipment⛏","\n**Sword**: " + (u.getEquippedSword() != null ?  u.getEquippedSword().getName() : "") + "\n**Armor**: " + (u.getEquippedArmor() != null ? u.getEquippedArmor().getName() : "") + "\n**Tool:** " + (u.getEquippedTool() != null ? u.getEquippedTool().getName() : ""),true));
                builder.addField(new MessageEmbed.Field("💶Money💶","\n**Money**: " + u.getMoney() + "\n**Bank**: " + u.getBank(),true));
                channel.sendMessage(builder.build()).queue();
            } else {

                String prefix = BetaChan.INSTANCE.prop.getProperty("prefix");
                message.reply("You need to register yourself with **" + prefix + " start**!").queue();
            }
        } else {
            message.reply("Usage: " + BetaChan.INSTANCE.prop.getProperty("prefix")+ " profile {@user}").queue();
        }
    }
}
