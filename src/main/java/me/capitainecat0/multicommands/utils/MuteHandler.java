package me.capitainecat0.multicommands.utils;

import org.bukkit.entity.Player;

import java.util.ArrayList;

public class MuteHandler {
    private static final ArrayList<Player> muted = new ArrayList<>();
    private static final ArrayList<String> reason = new ArrayList<>();
    private static MuteHandler instance;

    public void toggleMute(Player player){
        if(muted.contains(player)){
            mutePlayer(player);
        }
            unmutePlayer(player);
    }
    public void toggleMute(Player player, String reason){
        if(muted.contains(player)){
            mutePlayer(player, reason);
        }
        unmutePlayer(player, reason);
    }

    public static ArrayList<Player> getMuted(){
        return muted;
    }
    public static ArrayList<String> getReason(){
        return reason;
    }

    public boolean isMuted(Player player){
        return muted.contains(player);
    }

    private void mutePlayer(Player player){
        if(muted.contains(player)) return;
        muted.add(player);
    }
    private void unmutePlayer(Player player){
        muted.remove(player);
    }
    private void mutePlayer(Player player, String muteReason){
        if(muted.contains(player)) return;
        muted.add(player);
        reason.add(muteReason);
    }
    private void unmutePlayer(Player player, String muteReason){
        muted.remove(player);
        reason.remove(muteReason);
    }

    public static MuteHandler getInstance(){
        if(instance == null)
            instance = new MuteHandler();
        return instance;
    }
}
