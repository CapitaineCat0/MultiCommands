package me.capitainecat0.multicommands.utils;

import org.bukkit.entity.Player;

import java.util.ArrayList;

public class PrivateMessengerHandler {
    private static final ArrayList<Player> messager = new ArrayList<>();
    private static PrivateMessengerHandler instance;

    /**
     * The setMessager function adds a player to the messager list.
     *
     *
     * @param player Set the player to be messager
     *
     * @return A boolean, true if the player was added to the list and false if it wasn't
     */
    public void setMessager(Player player){
        if(messager.contains(player)){
            messager.remove(player);
        }
        messager.add(player);
    }

    /**
     * The isMessager function checks if a player is in the messager list.
     *
     *
     * @param player Check if the player is in the messager list
     *
     * @return True if the player is in the messager list

     */
    public boolean isMessager(Player player){
        return messager.contains(player);
    }

    /**
     * The getInstance function is a singleton function that returns the instance of the PrivateMessengerHandler class.
     * If there is no instance, it creates one and then returns it.
     *
     *
     *
     * @return An instance of the privatemessengerhandler class
     */
    public static PrivateMessengerHandler getInstance(){
        if(instance == null){
            instance = new PrivateMessengerHandler();
        }
        return instance;
    }
}
