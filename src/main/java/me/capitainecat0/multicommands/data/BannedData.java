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
        if(!existsPlayerData(player, "player-data")){
            File file = new File(MultiCommands.getInstance().getDataFolder()+"/player-data/", player.getUniqueId()+".yml");
            YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
            configuration.set("pseudo", player.getName());
            configuration.set("balance", BalanceData.getBalance(player));
            configuration.set("banned", false);
            configuration.set("freeze", FreezeData.isFrozen());
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


    public void setBanned(OfflinePlayer player, boolean banned) {
        File file = PlayerData.getPlayerDataFile(player, "player-data");
        assert file != null;
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        configuration.set("banned", banned);
        isBanned = banned;
        try {
            configuration.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void setReason(OfflinePlayer player, String reason){
        File file = PlayerData.getPlayerDataFile(player, "player-data");
        assert file != null;
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        configuration.set("reason", reason);
        raison = reason;
        try {
            configuration.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getReason(){
        return raison;
    }

    public @NotNull OfflinePlayer getPlayer() {
        return player;
    }
}