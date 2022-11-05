package me.capitainecat0.multicommands.data;

import me.capitainecat0.multicommands.MultiCommands;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class PlayerData {

    public static boolean existsPlayerData(Player player){
        File file = new File(MultiCommands.getInstance().getDataFolder()+"/player-data/", player.getUniqueId()+".yml");
        return file.exists();
    }

    public static void createPlayerData(Player player){
        if(!existsPlayerData(player)){
            File file = new File(MultiCommands.getInstance().getDataFolder()+"/player-data/", player.getUniqueId() +".yml");
            File folder = new File(MultiCommands.getInstance().getDataFolder()+"/player-data/", "");
            YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
            configuration.set("pseudo", player.getName());
            configuration.set("balance", 10);
            try {
                configuration.save(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static File getPlayerDataFile(Player player){
        if(existsPlayerData(player)){
            return new File(MultiCommands.getInstance().getDataFolder()+"/player-data/", player.getUniqueId() +".yml");
        }else{
            return null;
        }
    }
}
