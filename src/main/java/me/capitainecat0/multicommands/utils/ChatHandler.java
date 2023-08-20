package me.capitainecat0.multicommands.utils;

import me.capitainecat0.multicommands.MultiCommands;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

import static me.capitainecat0.multicommands.utils.MessengerUtils.getMsgSendConfig;

public class ChatHandler {
    private static ChatHandler instance;
    boolean chatActive = true;
    public boolean isEnabled(){
        return chatActive;
    }

    public void toggleChat(CommandSender sender){
        if(isEnabled()){
            chatActive = false;
            getMsgSendConfig(sender, "ChatHandler", "&6Chat toggle &coff");
        }else{
            chatActive = true;
            getMsgSendConfig(sender, "ChatHandler", "&6Chat toggle &aon");
        }
    }
    public static ChatHandler getInstance(){
        if(instance == null)
            instance = new ChatHandler();
        return instance;
    }
}
