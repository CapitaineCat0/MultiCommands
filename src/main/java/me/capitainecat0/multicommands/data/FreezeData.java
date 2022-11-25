package me.capitainecat0.multicommands.data;

import me.capitainecat0.multicommands.MultiCommands;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

import static me.capitainecat0.multicommands.data.PlayerData.*;

public class FreezeData {

    private static @NotNull OfflinePlayer player = null;
    private static boolean isFrozen;

    public FreezeData(@NotNull OfflinePlayer player) {
        if(!existsPlayerData(player, "player-data")){
            File file = new File(MultiCommands.getInstance().getDataFolder()+"/player-data/", player.getUniqueId()+".yml");
            YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
            configuration.set("pseudo", player.getName());
            configuration.set("balance", BalanceData.getBalance(player));
            configuration.set("banned", BannedData.isBanned());
            configuration.set("freeze", false);
            try {
                configuration.save(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static boolean isFrozen() {
        return isFrozen;
    }


    public void setFrozen(OfflinePlayer player, boolean frozen) {
        File file = PlayerData.getPlayerDataFile(player, "player-data");
        assert file != null;
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        configuration.set("freeze", frozen);
        isFrozen = frozen;
        try {
            configuration.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public @NotNull OfflinePlayer getPlayer() {
        return player;
    }
}