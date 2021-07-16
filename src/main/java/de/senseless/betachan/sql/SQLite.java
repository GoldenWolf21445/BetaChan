package de.senseless.betachan.sql;

import de.senseless.betachan.user.User;

import java.sql.*;

public class SQLite {
    private Connection connection = null;
    private Statement statement;

    public SQLite() {
        connect();
        init();
    }

    public void init(){
        onUpdate("CREATE TABLE IF NOT EXISTS users(id varchar(255) NOT NULL,name varchar(255) NOT NULL, hp int(255), maxhp int(255), atk int(255), def int(255), money int(255), bank int(255),level int(255),xp double(255,2), area int(255), inventory varchar(65535), equipped_tool varchar(65535), equipped_armor varchar(65535), equipped_sword varchar(65535), pets varchar(65535), started bit);");
        onUpdate("CREATE TABLE IF NOT EXISTS 'pets' ( 'id' INTEGER, 'owner_id' INTEGER NOT NULL, 'name' TEXT, 'level' INTEGER, 'xp' double(255,2), PRIMARY KEY('id' AUTOINCREMENT))");
        onUpdate("CREATE TABLE IF NOT EXISTS 'cooldowns' ( 'id' INTEGER, 'user_id' INTEGER NOT NULL, 'name' TEXT, 'time' INTEGER, PRIMARY KEY('id' AUTOINCREMENT))");
        onUpdate("CREATE TABLE IF NOT EXISTS 'dungeons' ( 'id' INTEGER, 'user_id' INTEGER NOT NULL, 'monster' TEXT, 'hp' INTEGER, 'atk' INTEGER, 'def' INTEGER, 'xp' double(255,2),'guild_id' TEXT,'channel_id' TEXT, PRIMARY KEY('id' AUTOINCREMENT))");
    }

    public void connect(){
        String url = "jdbc:sqlite:user.db";
        if(connection == null) {
            try {
                connection = DriverManager.getConnection(url);
                statement = connection.createStatement();
                statement.setQueryTimeout(3);
                System.out.println("Database Connected!");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }


    public ResultSet onQuery(String query){
        try {
            connect();
            return statement.executeQuery(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
    public void onUpdate(String query){
        try {
            connect();
            statement.executeUpdate(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void disconnect(){
        if(connection != null){
            try {
                for (User u : User.getUsers()){
                    u.save();
                }
                connection.close();
                System.out.println("Database Connection closed!");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
