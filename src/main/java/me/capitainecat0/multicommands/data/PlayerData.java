package me.capitainecat0.multicommands.data;

import me.capitainecat0.multicommands.MultiCommands;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

public class PlayerData {


    public static boolean existsPlayerData(String folderName, Player player){
        File file = new File(MultiCommands.getInstance().getDataFolder()+"/"+folderName+"/", player.getUniqueId()+".yml");
        return file.exists();
    }

    public static boolean existsPlayerData(String folderName, OfflinePlayer player){
        File file = new File(MultiCommands.getInstance().getDataFolder()+"/"+folderName+"/", player.getUniqueId()+".yml");
        return file.exists();
    }

    public static File getPlayerDataFile(OfflinePlayer player, String folderName){
        if(existsPlayerData(folderName, player)){
            return new File(MultiCommands.getInstance().getDataFolder()+"/"+folderName+"/", player.getUniqueId() +".yml");
        }else{
            return null;
        }
    }
    public static File getPlayerDataFile(String folderName, Player player){
        if(existsPlayerData(folderName, player)){
            return new File(MultiCommands.getInstance().getDataFolder()+"/"+folderName+"/", player.getUniqueId() +".yml");
        }else{
            return null;
        }
    }
}
