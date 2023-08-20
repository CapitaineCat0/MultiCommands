package me.capitainecat0.multicommands.utils;

import org.bukkit.entity.Player;

import java.util.ArrayList;

public class MuteHandler {
    private static final ArrayList<Player> muted = new ArrayList<>();
    private static final ArrayList<String> reason = new ArrayList<>();
    private static MuteHandler instance;

    /**
     * The toggleMute function takes a player as an argument and checks if the player is muted.
     * If the player is muted, it will unmute them. If they are not muted, it will mute them.

     *
     * @param player Determine which player is being muted or unmuted
     */
    public void toggleMute(Player player){
        if(muted.contains(player)){
            mutePlayer(player);
        }
            unmutePlayer(player);
    }

    /**
     * The toggleMute function takes a player and a reason as arguments.
     * If the player is muted, it will unmute them.
     * If the player is not muted, it will mute them.

     *
     * @param player Mute the player, and the string reason parameter is used to tell why they were muted
     * @param reason Specify the reason for muting a player
     */
    public void toggleMute(Player player, String reason){
        if(muted.contains(player)){
            mutePlayer(player, reason);
        }
        unmutePlayer(player, reason);
    }

    /**
     * The getMuted function returns an ArrayList of all the players who are muted.
     *
     *
     *
     * @return The muted arraylist
     */
    public static ArrayList<Player> getMuted(){
        return muted;
    }

    /**
     * The getReason function returns the reason ArrayList.
     *
     *
     *
     * @return The reason arraylist
     */
    public static ArrayList<String> getReason(){
        return reason;
    }

    /**
     * The isMuted function checks if a player is muted.
     *
     *
     * @param player Check if the player is muted
     *
     * @return A boolean value
     */
    public boolean isMuted(Player player){
        return muted.contains(player);
    }

    /**
     * The mutePlayer function takes a Player object as an argument and adds it to the muted ArrayList.
     *
     *
     * @param player Mute the player
     */
    private void mutePlayer(Player player){
        if(muted.contains(player)) return;
        muted.add(player);
    }

    /**
     * The unmutePlayer function removes a player from the muted list.
     *
     *
     * @param player Specify which player to unmute
     */
    private void unmutePlayer(Player player){
        muted.remove(player);
    }

    /**
     * The mutePlayer function takes in a player and a mute reason, and adds the player to the muted list.
     *
     *
     * @param player Mute the player
     * @param muteReason Store the reason for muting a player
     */
    private void mutePlayer(Player player, String muteReason){
        if(muted.contains(player)) return;
        muted.add(player);
        reason.add(muteReason);
    }

    /**
     * The unmutePlayer function removes a player from the muted list and removes their mute reason.
     *
     *
     * @param player Get the player that is muted
     * @param muteReason Get the reason for the mute
     *
     * @return Nothing
     *
     * @docauthor Trelent
     */
    private void unmutePlayer(Player player, String muteReason){
        muted.remove(player);
        reason.remove(muteReason);
    }

    /**
     * The getInstance function is a singleton function that returns the instance of the MuteHandler class.
     *
     *
     *
     * @return An instance of the mutehandler class
     */
    public static MuteHandler getInstance(){
        if(instance == null)
            instance = new MuteHandler();
        return instance;
    }
}
