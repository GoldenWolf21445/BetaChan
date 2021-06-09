package de.senseless.betachan.commands.statistics;

import de.senseless.betachan.BetaChan;
import de.senseless.betachan.area.Area;
import de.senseless.betachan.commands.types.ServerCommand;
import de.senseless.betachan.enums.MonsterType;
import de.senseless.betachan.item.Item;
import de.senseless.betachan.user.User;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class AreaCommand implements ServerCommand {
    @Override
    public void performCommand(String[] args, Member member, TextChannel channel, Message message) {
        EmbedBuilder embed = new EmbedBuilder();
        User u = User.loadUser(member.getId(), member.getEffectiveName());
        if(u.getStarted() == 1){
            if (args.length == 0) {
                embed.addField("Current Area:", "" + u.getArea().getNumber(), false);
                StringBuilder b = new StringBuilder();
                for (MonsterType m : u.getArea().getMonster()) {
                    b.append("\n").append(m.getNAME());
                }
                embed.addField("Monsters: ", b.toString(), false);
                channel.sendMessage(embed.build()).queue();
            } else if (args.length == 1) {
                StringBuilder monster = new StringBuilder();
                StringBuilder drops = new StringBuilder();
                Area a = Area.getByNumber(Integer.parseInt(args[0]));
                if(a != null) {
                    for (MonsterType m : a.getMonster()) {
                        monster.append("\n").append(m.getNAME());
                        if(m.getDrop() != null) {
                            for (Item drop : m.getDrop()) {
                                drops.append("\n").append(drop.getName());
                            }
                        }
                    }
                    embed.addField("Area:", args[0], false);
                    embed.addField("Monster:", monster.toString(), false);
                    embed.addField("Drops:", drops.toString(), false);
                    channel.sendMessage(embed.build()).queue();
                } else {
                    message.reply("I'm sorry but I couldn't find this Area").queue();
                }
            }
        } else {
            String prefix = BetaChan.INSTANCE.prop.getProperty("prefix");
            message.reply("You need to register yourself with **" + prefix + " start**!").queue();
        }
    }
}
