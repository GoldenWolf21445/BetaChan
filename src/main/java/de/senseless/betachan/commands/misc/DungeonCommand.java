package de.senseless.betachan.commands.misc;

import de.senseless.betachan.BetaChan;
import de.senseless.betachan.area.Area;
import de.senseless.betachan.commands.types.ServerCommand;
import de.senseless.betachan.enums.MonsterType;
import de.senseless.betachan.user.User;
import de.senseless.betachan.utils.Dungeon;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.interactions.components.Button;

public class DungeonCommand implements ServerCommand {
    public static void fight(Member m) {
        User u = User.loadUser(m.getId(), m.getEffectiveName());
        Dungeon d = u.getCurrentDungeon();
        if (d != null) {
            if ((d.getHp() + 0.5 * d.getDef()) - u.getAtk() > 0) {
                if (d.getHp() - ((d.getHp() + 0.5 * d.getDef()) - u.getAtk()) >= 0) {
                    d.setHp((int) ((d.getHp() + 0.5 * d.getDef()) - u.getAtk()));

                }
                u.setLife((int) ((u.getLife() + 0.5 * u.getDef()) - d.getAtk()));
                if (u.getLife() <= 0) {
                    u.setLife(0);
                    u.setCurrentDungeon(null);
                    u.setCooldown("dungeon");
                    u.save();
                    ((TextChannel) BetaChan.INSTANCE.shardManager.getGuildById(d.getGuildID()).getGuildChannelById(d.getChannelID())).sendMessage("You died in the Process of fighting the Boss!").queue();

                } else {
                    u.setCurrentDungeon(d);
                    u.setCooldown("dungeon");
                    u.save();

                    Guild g = BetaChan.INSTANCE.shardManager.getGuildById(d.getGuildID());
                    if (g != null) {
                        fightDungeon(u, d.getHp(), d.getAtk(), d.getDef(), d.getXp(), ((TextChannel) g.getGuildChannelById(d.getChannelID())));
                    }
                }
            } else {
                u.setCurrentDungeon(null);
                u.addXp(d.getXp());
                u.addMoney(1000 * u.getArea().getNumber());
                u.setCooldown("dungeon");
                ((TextChannel) BetaChan.INSTANCE.shardManager.getGuildById(d.getGuildID()).getGuildChannelById(d.getChannelID())).sendMessage("You slayed the boss of Area " + u.getArea().getNumber() + "!\nYou obtained: " + 1000 * u.getArea().getNumber() + " 💶 and " + d.getXp() + "xp").queue();
                u.setArea(Area.getByNumber(u.getArea().getNumber() + 1));
                u.save();
            }
        }
    }

    public static void defend(Member m) {
        User u = User.loadUser(m.getId(), m.getEffectiveName());
        Dungeon d = u.getCurrentDungeon();
        if (d != null) {
            if ((u.getLife() + 0.75 * u.getDef()) - d.getAtk() > 0) {
                u.setLife((int) ((u.getLife() + 0.75 * u.getDef()) - d.getAtk()));
                d.setHp((int) ((d.getHp() + 0.5 * d.getDef()) - u.getAtk()));
                u.save();
                u.setCurrentDungeon(d);
                fightDungeon(u, d.getHp(), d.getAtk(), d.getDef(), d.getXp(), ((TextChannel) BetaChan.INSTANCE.shardManager.getGuildById(d.getGuildID()).getGuildChannelById(d.getChannelID())));
            } else {
                u.setLife(0);
                u.setCurrentDungeon(null);
                u.save();
                ((TextChannel) BetaChan.INSTANCE.shardManager.getGuildById(d.getGuildID()).getGuildChannelById(d.getChannelID())).sendMessage("You died in the Process of fighting the Boss!").queue();
                u.setCooldown("dungeon");
            }
        }
    }

    public static void surrender(Member m,TextChannel channel) {
        User u = User.loadUser(m.getId(), m.getEffectiveName());
        Dungeon d = u.getCurrentDungeon();
        u.setCurrentDungeon(null);
        u.setLife(0);
        u.save();
        channel.sendMessage("You surrendered the Dungeon but you lost all of your HP!").queue();
        u.setCooldown("dungeon");

    }

    private static void fightDungeon(User u, int bossHP, int bossATK, int bossDEF, double bossXP, TextChannel channel) {
        if (bossHP > 0) {
            channel.sendMessage(channel.getGuild().getMemberById(u.getId()).getAsMention() + "\nThe Boss has " + bossHP + "❤ left!\nYou have "+u.getLife() + "❤ left!\n What do you want to do ?").setActionRow(Button.success("attack", "Attack"), Button.primary("defend", "Defend"),Button.danger("exit","Surrender")).queue();
        } else {
            u.setCooldown("dungeon");
        }
    }

    @Override
    public void performCommand(String[] args, Member member, TextChannel channel, Message message) {
        User u = User.loadUser(member.getId(), member.getEffectiveName());
        if (u.getStarted() == 1) {
            if (args.length == 0) {
                long cooldown = System.currentTimeMillis() - u.getCooldown("dungeon");
                System.out.println(cooldown);
                if (cooldown >= 24*60*60*1000) {
                    MonsterType monsterType = MonsterType.values()[(int) Math.round(Math.random() * MonsterType.values().length)];
                    if (monsterType != null) {
                        int hp = monsterType.getHP() * 5 * u.getArea().getNumber();
                        int atk = monsterType.getATK() * 5 * u.getArea().getNumber();
                        int def = monsterType.getDEF() * 5 * u.getArea().getNumber();
                        double xp = monsterType.getXP() * 5 * u.getArea().getNumber() * ((0.75) * u.getArea().getNumber() + Math.round(Math.random() * 10));
                        u.setCurrentDungeon(new Dungeon(monsterType, hp, atk, def, xp, channel.getGuild().getIdLong(), channel.getIdLong()));
                        u.save();
                        fightDungeon(u, hp, atk, def, xp, channel);
                    } else {
                        message.reply("I'm sorry but something went wrong please try again!").queue();
                    }
                } else {
                    long sec = (24*60*60*1000 - cooldown)/1000;
                    message.reply("I'm sorry but you need to wait " + (sec/60*60) % 24+" hours "+(sec/60) % (60)+" min "+sec % 60+" seconds").queue();
                }
            } else {
                message.reply("Usage: " + BetaChan.INSTANCE.prop.getProperty("prefix") + " dungeon").queue();
            }
        } else {
            message.reply("I'm sorry but to use this command you need to start your adventure first with '" + BetaChan.INSTANCE.prop.getProperty("prefix") + " start`").queue();
        }
    }
}
