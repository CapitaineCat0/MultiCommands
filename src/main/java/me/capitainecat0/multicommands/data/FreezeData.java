package me.capitainecat0.multicommands.data;

import me.capitainecat0.multicommands.MultiCommands;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;
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
            YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
            configuration.set("pseudo", player.getName());
            configuration.set("freeze", false);
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


    public void setFrozen(OfflinePlayer player, boolean frozen) {
        File file = PlayerData.getPlayerDataFile(player, "freeze-data");
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