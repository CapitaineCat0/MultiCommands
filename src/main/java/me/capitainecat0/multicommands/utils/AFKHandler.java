package me.capitainecat0.multicommands.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;

import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.getMsgSendConfig;
import static me.capitainecat0.multicommands.utils.MessengerUtils.hideActiveBossBar;

public class AFKHandler {
    private static final ArrayList<Player> afk = new ArrayList<>();
    private static AFKHandler instance;

    public void toggleAFK(Player player){
        hideActiveBossBar();
        if(afk.contains(player))
            disableAFK(player);
        else enableAFK(player);
    }

    public static ArrayList<Player> getAFK(){
        return afk;
    }

    public boolean isAFK(Player player){
        return afk.contains(player);
    }

    private void enableAFK(Player player){
        if(afk.contains(player))return;
        for (Player people : Bukkit.getOnlinePlayers()) {
            people.sendMessage(AFK_BROADCAST_ENABLED.getMessage().replace("%player%", player.getName()));
        }
        hideActiveBossBar();
        getMsgSendConfig(player, "afk", AFK_ENABLED.getMessage());
        player.setInvulnerable(true);
        afk.add(player);
    }

    private void disableAFK(Player player){
        for (Player people : Bukkit.getOnlinePlayers()) {
            people.sendMessage(AFK_BROADCAST_DISABLED.getMessage().replace("%player%", player.getName()));
        }
        hideActiveBossBar();
        getMsgSendConfig(player, "afk",AFK_DISABLED.getMessage());
        player.setInvulnerable(false);
        afk.remove(player);
    }

    public static AFKHandler getInstance(){
        if(instance == null)
            instance = new AFKHandler();
        return instance;
    }
}
