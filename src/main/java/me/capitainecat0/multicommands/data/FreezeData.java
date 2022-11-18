package me.capitainecat0.multicommands.data;

import me.capitainecat0.multicommands.MultiCommands;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

import static me.capitainecat0.multicommands.data.PlayerData.*;

public class FreezeData {

    private static @NotNull OfflinePlayer player;
    private static boolean isFrozen;

    public FreezeData(@NotNull OfflinePlayer player) {
        if(!existsPlayerData(player, "freeze-data")){
            File file = new File(MultiCommands.getInstance().getDataFolder()+"/freeze-data/", player.getUniqueId()+".yml");
            File folder = new File(MultiCommands.getInstance().getDataFolder()+"/freeze-data/");
            YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
            configuration.set("pseudo", player.getName());
            configuration.set("isFrozen", false);
            try {
                configuration.save(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public boolean isFrozen() {
        return isFrozen;
    }

    public static void save() {
            File file = new File(MultiCommands.getInstance().getDataFolder()+"/freeze-data/", player.getUniqueId()+".yml");
            YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
            configuration.set("pseudo", player.getName());
            configuration.set("isFrozen", isFrozen);
            try {
                configuration.save(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }

    public void setFrozen(boolean frozen) {
        isFrozen = frozen;
        save();
    }

    public @NotNull OfflinePlayer getPlayer() {
        return player;
    }
}