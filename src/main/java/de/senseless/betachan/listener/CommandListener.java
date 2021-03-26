package de.senseless.betachan.listener;

import de.senseless.betachan.BetaChan;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

public class CommandListener extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();
        Properties prop = new Properties();
        String prefix = "betachan";
        try {
            prop.load(new FileInputStream("src/main/resources/config.properties"));
            prefix = prop.getProperty("prefix");
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (message.startsWith(prefix)) {
            String[] args = message.substring(prefix.length()+1).split(" ");
            if (args.length > 0) {
                if (!BetaChan.INSTANCE.getCommandManager().perform(args[0].toLowerCase(), event.getMember(), event.getChannel(), event.getMessage())) {
                    event.getChannel().sendMessage("Unbekannter Befehl!").queue();
                    System.out.println("Fehler!");
                }
            }
        }

    }
}
