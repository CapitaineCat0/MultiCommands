package me.capitainecat0.multicommands.utils;

import org.bukkit.entity.Player;

import java.util.ArrayList;

public class PrivateMessengerHandler {
    private static final ArrayList<Player> messager = new ArrayList<>();
    private static PrivateMessengerHandler instance;

    public void setMessager(Player player){
        if(messager.contains(player)){
            messager.remove(player);
        }
        messager.add(player);
    }

    public boolean isMessager(Player player){
        return messager.contains(player);
    }

    public static PrivateMessengerHandler getInstance(){
        if(instance == null){
            instance = new PrivateMessengerHandler();
        }
        return instance;
    }
}
