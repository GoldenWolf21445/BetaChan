package de.senseless.betachan.handler;

import de.senseless.betachan.area.Area;
import de.senseless.betachan.user.User;
import net.dv8tion.jda.api.entities.Member;

public class UserHandler {

    public static void createNewUser(Member member){
        Area area = Area.getByNumber(1);
        User u = new User(member,100,0,1,4,4,100, 100, area);
    }
}
