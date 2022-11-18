package me.capitainecat0.multicommands.data;

import me.capitainecat0.multicommands.MultiCommands;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

import static me.capitainecat0.multicommands.data.PlayerData.existsPlayerData;

public class BannedData {

    private static @NotNull OfflinePlayer player;
    private static boolean isBanned;
    private static String raison;

    public BannedData(@NotNull OfflinePlayer player) {
        if(!existsPlayerData(player, "banned-players")){
            File file = new File(MultiCommands.getInstance().getDataFolder()+"/banned-data/", player.getUniqueId()+".yml");
            File folder = new File(MultiCommands.getInstance().getDataFolder()+"/banned-data/");
            YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
            configuration.set("pseudo", player.getName());
            configuration.set("banned", false);
            try {
                configuration.save(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static boolean isBanned() {
        return isBanned;
    }

    public static void save() {
            File file = new File(MultiCommands.getInstance().getDataFolder()+"/banned-data/", player.getUniqueId()+".yml");
            YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
            configuration.set("pseudo", player.getName());
            configuration.set("reason", raison);
            configuration.set("banned", isBanned);
            try {
                configuration.save(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }

    public void setBanned(boolean banned) {
        isBanned = banned;
        save();
    }

    public void setReason(String reason){
        raison = reason;
        save();
    }

    public String getReason(){
        return raison;
    }

    public @NotNull OfflinePlayer getPlayer() {
        return player;
    }
}