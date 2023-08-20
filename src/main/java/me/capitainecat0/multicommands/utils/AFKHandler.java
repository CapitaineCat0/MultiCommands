package me.capitainecat0.multicommands.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;

import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;

public class AFKHandler {
    private static final ArrayList<Player> afk = new ArrayList<>();
    private static AFKHandler instance;

    /**
     * The toggleAFK function is used to toggle the AFK status of a player.
     * If the player is already AFK, it will disable their AFK status and remove them from the afk list.
     * If they are not currently AFK, it will enable their AFK status and add them to the afk list.

     *
     * @param player Get the player who is toggling afk
     */
    public void toggleAFK(Player player){
        hideActiveBossBar();
        if(afk.contains(player))
            disableAFK(player);
        else enableAFK(player);
    }

    /**
     * The getAFK function returns an ArrayList of all players who are currently AFK.
     *
     *
     *
     * @return An arraylist of player objects
     */
    public static ArrayList<Player> getAFK(){
        return afk;
    }

    /**
     * The isAFK function checks if a player is AFK.
     *
     *
     * @param player Check if the player is in the afk list
     *
     * @return A boolean value
     */
    public boolean isAFK(Player player){
        return afk.contains(player);
    }

    /**
     * The enableAFK function is used to enable the AFK mode for a player.
     *
     *
     * @param player Get the player that is being enabled
     *
     */
    private void enableAFK(Player player){
        if(afk.contains(player))return;
        for (Player people : Bukkit.getOnlinePlayers()) {
            sendMessage(people, AFK_BROADCAST_ENABLED.getMessage().replace("{0}", player.getName()).replace("{prefix}", PLUGIN_PREFIX.getMessage()));
        }
        hideActiveBossBar();
        getMsgSendConfig(player, "afk", AFK_ENABLED.getMessage().replace("{prefix}", PLUGIN_PREFIX.getMessage()));
        player.setInvulnerable(true);
        afk.add(player);
    }

    /**
     * The disableAFK function is used to disable the AFK status of a player.
     * It does this by removing them from the afk HashSet, and then sending a message to all online players that they are no longer AFK.
     *
     *
     * @param player Get the player's name and send them a message
     *
     * @return Nothing
     *
     * @docauthor Trelent
     */
    private void disableAFK(Player player){
        for (Player people : Bukkit.getOnlinePlayers()) {
            sendMessage(people, AFK_BROADCAST_DISABLED.getMessage().replace("{0}", player.getName()).replace("{prefix}", PLUGIN_PREFIX.getMessage()));
        }
        hideActiveBossBar();
        getMsgSendConfig(player, "afk",AFK_DISABLED.getMessage().replace("{prefix}", PLUGIN_PREFIX.getMessage()));
        player.setInvulnerable(false);
        afk.remove(player);
    }

    /**
     * The getInstance function is a singleton function that returns the instance of the AFKHandler class.
     * If there is no instance, it creates one and then returns it.

     *
     *
     * @return An instance of the afkhandler class
     */
    public static AFKHandler getInstance(){
        if(instance == null)
            instance = new AFKHandler();
        return instance;
    }
}
