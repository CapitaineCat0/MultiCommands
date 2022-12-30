package me.capitainecat0.multicommands.data;

import me.capitainecat0.multicommands.MultiCommands;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static me.capitainecat0.multicommands.data.PlayerData.existsPlayerData;

public class BalanceData {

    private static @NotNull OfflinePlayer player;
    private static double balance;

    public BalanceData(@NotNull OfflinePlayer player) {
        if(!existsPlayerData(player, "player-data")){
            File file = new File(MultiCommands.getInstance().getDataFolder()+"/player-data/", player.getUniqueId()+".yml");
            YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
            configuration.set("pseudo", player.getName());
            configuration.set("balance", balance);
            configuration.set("banned", false);
            configuration.set("freeze", false);
            try {
                configuration.save(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static int getBalance(OfflinePlayer player){
        File file = PlayerData.getPlayerDataFile(player, "player-data");
        assert file != null;
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        return Integer.parseInt(Objects.requireNonNull(configuration.get("balance")).toString());
    }

    public static void setBalance(OfflinePlayer player, double newBal){
        File file = PlayerData.getPlayerDataFile(player, "player-data");
        assert file != null;
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        configuration.set("balance", newBal);
        balance = newBal;
        try {
            configuration.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void resetBalance(OfflinePlayer player){
        File file = PlayerData.getPlayerDataFile(player, "player-data");
        assert file != null;
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        configuration.set("balance", 0);
        balance = 0;
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
