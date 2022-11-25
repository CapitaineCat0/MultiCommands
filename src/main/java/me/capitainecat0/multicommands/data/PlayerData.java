package me.capitainecat0.multicommands.data;

import me.capitainecat0.multicommands.MultiCommands;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

public class PlayerData {

    public PlayerData(@NotNull OfflinePlayer player) {
        if(!existsPlayerData(player, "player-data")){
            File file = new File(MultiCommands.getInstance().getDataFolder()+"/player-data/", player.getUniqueId()+".yml");
            YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
            configuration.set("pseudo", player.getName());
            configuration.set("balance", BalanceData.getBalance(player));
            configuration.set("banned", BannedData.isBanned());
            configuration.set("freeze", FreezeData.isFrozen());
            try {
                configuration.save(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static boolean existsPlayerData(Player player, String folderName){
        File file = new File(MultiCommands.getInstance().getDataFolder()+"/"+folderName+"/", player.getUniqueId()+".yml");
        return file.exists();
    }

    public static boolean existsPlayerData(OfflinePlayer player, String folderName){
        File file = new File(MultiCommands.getInstance().getDataFolder()+"/"+folderName+"/", player.getUniqueId()+".yml");
        return file.exists();
    }

    public static File getPlayerDataFile(OfflinePlayer player, String folderName){
        if(existsPlayerData(player, folderName)){
            return new File(MultiCommands.getInstance().getDataFolder()+"/"+folderName+"/", player.getUniqueId() +".yml");
        }else{
            return null;
        }
    }
}
