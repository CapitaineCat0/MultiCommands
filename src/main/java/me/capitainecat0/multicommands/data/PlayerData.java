package me.capitainecat0.multicommands.data;

import me.capitainecat0.multicommands.MultiCommands;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class PlayerData {

    public static boolean existsPlayerData(Player player, String folderName){
        File file = new File(MultiCommands.getInstance().getDataFolder()+"/"+folderName+"/", player.getUniqueId()+".yml");
        return file.exists();
    }

    public static boolean existsPlayerData(OfflinePlayer player, String folderName){
        File file = new File(MultiCommands.getInstance().getDataFolder()+"/"+folderName+"/", player.getUniqueId()+".yml");
        return file.exists();
    }

    public static File getPlayerDataFile(Player player, String folderName){
        if(existsPlayerData(player, folderName)){
            return new File(MultiCommands.getInstance().getDataFolder()+"/"+folderName+"/", player.getUniqueId() +".yml");
        }else{
            return null;
        }
    }

    public static File getPlayerDataFile(OfflinePlayer player, String folderName){
        if(existsPlayerData(player, folderName)){
            return new File(MultiCommands.getInstance().getDataFolder()+"/"+folderName+"/", player.getUniqueId() +".yml");
        }else{
            return null;
        }
    }
}
