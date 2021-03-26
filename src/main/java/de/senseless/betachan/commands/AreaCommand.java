package de.senseless.betachan.commands;

import de.senseless.betachan.area.Area;
import de.senseless.betachan.commands.types.ServerCommand;
import de.senseless.betachan.item.Item;
import de.senseless.betachan.monster.Monster;
import de.senseless.betachan.user.User;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class AreaCommand implements ServerCommand {
    @Override
    public void performCommand(String[] args, Member member, TextChannel channel, Message message) {
        User u = User.getByID(member.getId());
        EmbedBuilder embed = new EmbedBuilder();
        if (args.length == 0) {
            embed.addField("Current Area:","" + u.getArea().getNumber(),false);
            StringBuilder b = new StringBuilder();
            for(Monster m : u.getArea().getMonster()){
                b.append("\n").append(m.getNAME());
            }
            embed.addField("Monsters: ",b.toString(),false);
            channel.sendMessage(embed.build()).queue();
        } else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("1")) {
                StringBuilder monster = new StringBuilder();
                StringBuilder drops = new StringBuilder();
                for(Monster m : Area.getByNumber(1).getMonster()){
                    monster.append("\n").append(m.getNAME());
                    for (Item i : m.getDROPS().keySet()){
                        drops.append("\n").append(i.getName());
                    }
                }
                embed.addField("Area:","1",false);
                embed.addField("Monster:",monster.toString(),false);
                embed.addField("Drops:",drops.toString(),false);
                channel.sendMessage(embed.build()).queue();
            } else if (args[0].equalsIgnoreCase("2")) {
                StringBuilder monster = new StringBuilder();
                StringBuilder drops = new StringBuilder();
                for(Monster m : Area.getByNumber(2).getMonster()){
                    monster.append("\n").append(m.getNAME());
                    for (Item i : m.getDROPS().keySet()){
                        drops.append("\n").append(i.getName());
                    }
                }
                embed.addField("Area:","2",false);
                embed.addField("Monster:",monster.toString(),false);
                embed.addField("Drops:",drops.toString(),false);
                channel.sendMessage(embed.build()).queue();
            } else if (args[0].equalsIgnoreCase("3")) {
                StringBuilder monster = new StringBuilder();
                StringBuilder drops = new StringBuilder();
                for(Monster m : Area.getByNumber(3).getMonster()){
                    monster.append("\n").append(m.getNAME());
                    for (Item i : m.getDROPS().keySet()){
                        drops.append("\n").append(i.getName());
                    }
                }
                embed.addField("Area:","3",false);
                embed.addField("Monster:",monster.toString(),false);
                embed.addField("Drops:",drops.toString(),false);
                channel.sendMessage(embed.build()).queue();
            } else if (args[0].equalsIgnoreCase("4")) {
                StringBuilder monster = new StringBuilder();
                StringBuilder drops = new StringBuilder();
                for(Monster m : Area.getByNumber(4).getMonster()){
                    monster.append("\n").append(m.getNAME());
                    for (Item i : m.getDROPS().keySet()){
                        drops.append("\n").append(i.getName());
                    }
                }
                embed.addField("Area:","4",false);
                embed.addField("Monster:",monster.toString(),false);
                embed.addField("Drops:",drops.toString(),false);
                channel.sendMessage(embed.build()).queue();
            } else if (args[0].equalsIgnoreCase("5")) {
                StringBuilder monster = new StringBuilder();
                StringBuilder drops = new StringBuilder();
                for(Monster m : Area.getByNumber(5).getMonster()){
                    monster.append("\n").append(m.getNAME());
                    for (Item i : m.getDROPS().keySet()){
                        drops.append("\n").append(i.getName());
                    }
                }
                embed.addField("Area:","5",false);
                embed.addField("Monster:",monster.toString(),false);
                embed.addField("Drops:",drops.toString(),false);
                channel.sendMessage(embed.build()).queue();
            } else {
                message.reply("You need to specify a known Area!").queue();
            }
        }
    }
}
