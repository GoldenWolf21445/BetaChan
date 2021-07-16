package de.senseless.betachan.handler;

import de.senseless.betachan.commands.misc.DungeonCommand;
import de.senseless.betachan.user.User;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class DungeonInteractionHandler extends ListenerAdapter {

    @Override
    public void onButtonClick(ButtonClickEvent event) {
        Member m = event.getInteraction().getMember();
        User u = User.loadUser(m.getId(), m.getEffectiveName());
        if (event.getComponentId().equals("attack")) {
            if (u.getCurrentDungeon() != null) {
                DungeonCommand.fight(m);
                event.getMessage().delete().queue();
            }
            event.deferEdit().queue();
        } else if (event.getComponentId().equals("defend")) {
            if (u.getCurrentDungeon() != null) {
                DungeonCommand.defend(m);
                event.getMessage().delete().queue();
            }
            event.deferEdit().queue();
        } else if (event.getComponentId().equals("exit")) {
            if (u.getCurrentDungeon() != null) {
                DungeonCommand.surrender(m, event.getTextChannel());
                event.getMessage().delete().queue();
            }
        }
    }

}
