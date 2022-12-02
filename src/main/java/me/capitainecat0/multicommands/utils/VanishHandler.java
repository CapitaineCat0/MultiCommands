package me.capitainecat0.multicommands.utils;

import me.capitainecat0.multicommands.MultiCommands;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class VanishHandler {
    private static final ArrayList<Player> vanished = new ArrayList<>();
    private static VanishHandler instance;

    public void toggleVanish(Player player){
        if(vanished.contains(player)){
            showPlayer(player);
        }
            hidePlayer(player);
    }

    public static ArrayList<Player> getVanished(){
        return vanished;
    }

    public boolean isVanished(Player player){
        return vanished.contains(player);
    }

    private void hidePlayer(Player player){
        if(vanished.contains(player)) return;
        for (Player people : Bukkit.getOnlinePlayers()) {
            people.hidePlayer(MultiCommands.getInstance(),player);
        }
        vanished.add(player);
    }

    private void showPlayer(Player player){
        for (Player people : Bukkit.getOnlinePlayers()) {
            people.showPlayer(MultiCommands.getInstance(), player);
        }
        vanished.remove(player);
    }

    public static VanishHandler getInstance(){
        if(instance == null)
            instance = new VanishHandler();
        return instance;
    }
}
