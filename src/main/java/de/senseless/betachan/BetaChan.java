package de.senseless.betachan;

import de.senseless.betachan.handler.CommandManager;
import de.senseless.betachan.listener.CommandListener;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

import javax.security.auth.login.LoginException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BetaChan {

    public static BetaChan INSTANCE;

    public ShardManager shardManager;
    private CommandManager commandManager;

    public BetaChan() throws LoginException {
        INSTANCE = this;
        Dotenv dotenv = Dotenv.configure()
                .ignoreIfMalformed()
                .ignoreIfMissing()
                .load();

        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(dotenv.get("TOKEN"));
        builder.setActivity(Activity.listening("Music"));

        builder.setStatus(OnlineStatus.ONLINE);
        this.commandManager = new CommandManager();
        builder.addEventListeners(new CommandListener());

        shardManager = builder.build();
        System.out.println("Bot online.");

        shutdown();

    }

    public static void main(String[] args) throws LoginException {
        new BetaChan();
    }

    public void shutdown() {
        new Thread(() -> {
            String line;
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                while ((line = reader.readLine()) != null) {
                    if (line.equalsIgnoreCase("exit")) {
                        if (shardManager != null) {
                            shardManager.setStatus(OnlineStatus.OFFLINE);
                            shardManager.shutdown();
                            System.out.println("Bot offline.");
                        }

                        break;
                    }
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }


    public CommandManager getCommandManager() {
        return commandManager;
    }
}

