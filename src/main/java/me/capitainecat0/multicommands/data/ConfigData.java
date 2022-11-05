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

    public static FileConfiguration getDataConfig(String fileName, String path){
        if(existsConfigData(fileName)){
            YamlConfiguration configuration = YamlConfiguration.loadConfiguration(new File(MultiCommands.getInstance().getDataFolder(), fileName));
            configuration.get(path);
        }
        return null;
    }

    public static FileConfiguration getDataConfig(String folderName, String fileName, String path){
        if(existsDataFolder(folderName) && existsConfigData(fileName)){
            YamlConfiguration configuration = YamlConfiguration.loadConfiguration(new File(MultiCommands.getInstance().getDataFolder()+"/"+folderName+"/", fileName));
            configuration.get(path);
        }
        return null;
    }

    public static File getDataFile(String fileName){
        if(existsConfigData(fileName)){
            new File(MultiCommands.getInstance().getDataFolder(), fileName);
        }
        return null;
    }

    public static File getDataFolder(String folder){
        if(existsDataFolder(folder)){
            new File(MultiCommands.getInstance().getDataFolder()+"/"+folder+"/", "");
        }
        return null;
    }

    public static FileConfiguration getDataFolderFile(String folder, String fileName){
        if(existsDataFolder(folder) && existsConfigData(fileName)){
            new File(MultiCommands.getInstance().getDataFolder()+"/"+folder+"/", fileName);
        }
        return null;
    }

    public static void createConfigData(String fileName, String path, Object config){
        if(!existsConfigData(fileName)){
            File file = new File(MultiCommands.getInstance().getDataFolder(), fileName);
            YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
            configuration.set(path, config);
            try {
                configuration.save(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
