package me.capitainecat0.multicommands.data;

import me.capitainecat0.multicommands.MultiCommands;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class ConfigData {

    public static boolean existsConfigData(String fileName){
        File file = new File(MultiCommands.getInstance().getDataFolder(), fileName);
        return file.exists();
    }

    public static boolean existsDataFolder(String folder){
        File file = new File(MultiCommands.getInstance().getDataFolder()+"/"+folder+"/", "");
        return file.exists();
    }

    public static FileConfiguration getConfig(String folderName, String fileName){
        if(existsDataFolder(folderName) && existsConfigData(fileName)){
            YamlConfiguration.loadConfiguration(new File(MultiCommands.getInstance().getDataFolder()+"/"+folderName+"/", fileName));

        }
        return null;
    }

    public static FileConfiguration getConfig(String fileName){
        if(existsConfigData(fileName)){
            YamlConfiguration.loadConfiguration(new File(MultiCommands.getInstance().getDataFolder(), fileName));

        }
        return null;
    }
}
