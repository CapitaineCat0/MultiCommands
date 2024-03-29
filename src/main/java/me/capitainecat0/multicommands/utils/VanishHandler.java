package me.capitainecat0.multicommands.utils;

import me.capitainecat0.multicommands.MultiCommands;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;

import static me.capitainecat0.multicommands.utils.permissions.Perms.ALL_PERMS;

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
        }for(Player operators : Bukkit.getOnlinePlayers()){
            if(operators.hasPermission(ALL_PERMS.getPermission())){
                operators.showPlayer(MultiCommands.getInstance(), player);
            }
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
