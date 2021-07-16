package de.senseless.betachan.commands.administrative;

import de.senseless.betachan.BetaChan;
import de.senseless.betachan.area.Area;
import de.senseless.betachan.area.AreaManager;
import de.senseless.betachan.commands.types.ServerCommand;
import de.senseless.betachan.sql.SQLite;
import de.senseless.betachan.user.User;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class AdminCommand implements ServerCommand {
    @Override
    public void performCommand(String[] args, Member member, TextChannel channel, Message message) {
        if(member.getId().equals("137701911993253888")){
            if(args.length == 4) {
                if(args[0].equalsIgnoreCase("money")){
                    if(args[1].equalsIgnoreCase("add")){
                        Member m = message.getGuild().getMemberById(args[2]);
                        if(m != null) {
                            User u = User.getByID(m.getId());
                            if (u != null) {
                                u.addMoney(Integer.parseInt(args[3]));
                                u.save();
                            }
                        }
                    } else if(args[1].equalsIgnoreCase("remove")){
                        Member m = message.getGuild().getMemberById(args[2]);
                        if(m != null) {
                            User u = User.getByID(m.getId());
                            if (u != null) {
                                u.removeMoney(Integer.parseInt(args[3]));
                                u.save();
                            }
                        }
                    } else if(args[1].equalsIgnoreCase("set")){
                        Member m = message.getGuild().getMemberById(args[2]);
                        if(m != null) {
                            User u = User.loadUser(m.getId(),m.getEffectiveName());
                            u.setMoney(Integer.parseInt(args[3]));
                            u.save();
                        }
                    }
                } else if(args[0].equalsIgnoreCase("area")) {
                    if(args[1].equalsIgnoreCase("set")){
                        Member m = message.getGuild().getMemberById(args[2]);
                        if(m != null) {
                            User u = User.getByID(m.getId());
                            if (u != null) {
                                u.setArea(Area.getByNumber(Integer.parseInt(args[3])));
                                u.save();
                            }
                        }
                    }
                } else if(args[0].equalsIgnoreCase("pet")){
                    if(args[1].equalsIgnoreCase("create")){
                        BetaChan.INSTANCE.database.onUpdate("INSERT INTO pets (owner_id, name) VALUES (" + args[2] +", '" + args[3] + "');");
                    }
                }
            }
        }
        //beta admin money add @ 89
    }
}
