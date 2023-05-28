package me.capitainecat0.multicommands.data;

import me.capitainecat0.multicommands.MultiCommands;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

import static me.capitainecat0.multicommands.data.PlayerData.existsData;
import static me.capitainecat0.multicommands.data.PlayerData.existsPlayerData;

public class BannedDataAdmin {

    public BannedDataAdmin(@NotNull String fileName, Player player) {
        if(!existsData("", fileName)){
            File file = new File(MultiCommands.getInstance().getDataFolder()+"", fileName+".yml");
            YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
            configuration.addDefault("pseudo=", player.getName());
            try {
                configuration.save(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void addBanned(Player player){
        if(!existsData("", "banned")){
            File file = new File(MultiCommands.getInstance().getDataFolder()+"", "banned.yml");
            YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
            YamlConfiguration path = (YamlConfiguration) configuration.createSection(String.valueOf(player.getUniqueId()));
            configuration.addDefault(path+".pseudo=", player.getName());
            configuration.addDefault(path+".ip-address=", player.getAddress());
            try {
                configuration.save(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void removeBanned(Player player){
        if(!existsData("", "banned")){
            File file = new File(MultiCommands.getInstance().getDataFolder()+"", "banned.yml");
            YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
            YamlConfiguration path = (YamlConfiguration) configuration.createSection(String.valueOf(player.getUniqueId()));
            configuration.set(String.valueOf(path), "");
            //configuration.set(path+".pseudo="+player.getName(), "");
            //configuration.set(path+".ip-address="+player.getAddress(), "");
            try {
                configuration.save(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}