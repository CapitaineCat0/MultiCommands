package me.capitainecat0.multicommands.utils;

import org.bukkit.entity.Player;

import java.util.ArrayList;

public class FreezeHandler {
    private static final ArrayList<Player> freeze = new ArrayList<>();
    private static FreezeHandler instance;

    public void toggleFreeze(Player player){
        if(freeze.contains(player)){
            frozePlayer(player);
        }
            unfrozePlayer(player);
    }
    public static ArrayList<Player> getFreeze(){
        return freeze;
    }

    public boolean isFreeze(Player player){
        return freeze.contains(player);
    }

    private void frozePlayer(Player player){
        if(freeze.contains(player)) return;
        freeze.add(player);
    }
    private void unfrozePlayer(Player player){
        freeze.remove(player);
    }
    public static FreezeHandler getInstance(){
        if(instance == null)
            instance = new FreezeHandler();
        return instance;
    }
}
