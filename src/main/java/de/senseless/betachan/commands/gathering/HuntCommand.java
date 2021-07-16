package de.senseless.betachan.commands.gathering;

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

import java.time.Instant;

public class HuntCommand implements ServerCommand {
    @Override
    public void performCommand(String[] args, Member member, TextChannel channel, Message message) {
        User user = User.loadUser(member.getId(), member.getEffectiveName());
        if(user.getStarted() == 1){
            if (args.length == 1) {
                Area area = user.getArea();
                MonsterType monsterType = MonsterType.getByName(args[0]);
                long cooldown = System.currentTimeMillis() - user.getCooldown("hunt");
                if (cooldown >= 10*1000) {
                    if (monsterType != null) {
                        if (area.getMonster().contains(monsterType)) {
                            int userHp = user.getLife();
                            int userAtk = user.getAtk() + (user.getEquippedSword() != null ? user.getEquippedSword().getAtk() : 0 );
                            int userDef = user.getDef();
                            int monsterHp = monsterType.getHP();
                            int monsterAtk = monsterType.getATK();
                            int monsterDef = monsterType.getDEF();

                            if (userHp > (monsterAtk - userDef)) {
                                if (monsterHp <= (userAtk - monsterDef)) {
                                    if (monsterType.getXP() != 0) {
                                        double xpDrop = monsterType.getXP() * ((0.75) * area.getNumber() + Math.round(Math.random() * 10));
                                        int money = (int) Math.round(10 * ((0.75) * area.getNumber() + (Math.random() * 6)));
                                        user.addXp(xpDrop);
                                        user.addMoney(money);
                                        Item[] drops = monsterType.getDrop();
                                        EmbedBuilder eb = new EmbedBuilder();

                                        if (user.getLife() - Math.abs(monsterAtk - userDef) > 0) {
                                            eb.setColor(0x006600);
                                            eb.setTitle("⚔Hunt⚔");
                                            eb.setTimestamp(Instant.now());
                                            eb.addField("Monster:", monsterType.getNAME(), false);
                                            eb.addField("XP:", "" + xpDrop, false);
                                            eb.addField("Money:", "" + money, false);
                                            if (userDef - monsterAtk < 0) {
                                                user.removeLife(Math.abs(monsterAtk - userDef));
                                                eb.addField("Health Change", "" + (monsterAtk - userDef), false);
                                            }
                                            long item = Math.round(Math.random() * drops.length) - 1;//TODO FIX
                                            if (drops.length != 0 && (item >= 0)) {
                                                Item drop = drops[(int) item];
                                                if (drop != null) {
                                                    eb.addField("Loot:", drop.getName(), false);
                                                    user.addToInventory(drop, 1);
                                                }
                                            }

                                            channel.sendMessage(eb.build()).queue();
                                        } else {
                                            user.setLife(user.getMaxlife());
                                            double xp = user.getXp();
                                            double tenPercentXp = xp / 10;
                                            if (user.getXp() - tenPercentXp >= 0) {
                                                user.removeXP(tenPercentXp);
                                            } else {
                                                user.setXp(0);
                                            }
                                            message.reply("As you tried to fight one " + monsterType.getNAME() + " you died! You lost some Experience as you cured! Better Heal before next time!").queue();

                                        }
                                        user.setCooldown("hunt");
                                        user.save();
                                    }
                                }
                            }
                        } else {
                            message.reply("The targeted Target isn't available! You can find available Targets with **" + BetaChan.INSTANCE.prop.getProperty("prefix") + " area**!").queue();
                        }
                    } else {
                        message.reply("The targeted Target isn't available! You can find available Targets with **" + BetaChan.INSTANCE.prop.getProperty("prefix") + " area**!").queue();
                    }
                } else {
                    message.reply("I'm sorry but you need to wait "+ (10-(cooldown/1000)) + " sec!").queue();
                }
            } else {
                message.reply("You need to specify a Hunt target! You can find available Targets with **" + BetaChan.INSTANCE.prop.getProperty("prefix") + " area**!").queue();
            }
        } else {

            message.reply("I'm sorry but to use this command you need to start your adventure first with '" + BetaChan.INSTANCE.prop.getProperty("prefix") + " start`").queue();
        }
    }
}
