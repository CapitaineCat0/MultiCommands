package me.capitainecat0.multicommands.data;

import me.capitainecat0.multicommands.MultiCommands;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static me.capitainecat0.multicommands.data.PlayerData.existsPlayerData;

public class BalanceData {

    private static @NotNull OfflinePlayer player;
    private static int balance;

    public BalanceData(@NotNull OfflinePlayer player) {
        if(!existsPlayerData(player, "economy-data")){
            File file = new File(MultiCommands.getInstance().getDataFolder()+"/economy-data/", player.getUniqueId()+".yml");
            File folder = new File(MultiCommands.getInstance().getDataFolder()+"/economy-data/");
            YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
            configuration.set("pseudo", player.getName());
            configuration.set("balance", balance);
            try {
                configuration.save(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public @NotNull OfflinePlayer getPlayer() {
        return player;
    }

    public static int getBalance(Player player){
        File file = PlayerData.getPlayerDataFile(player, "economy-data");
        assert file != null;
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        return Integer.parseInt(Objects.requireNonNull(configuration.get("balance")).toString());
    }

    public static void setBalance(Player player, int newBal){
        File file = PlayerData.getPlayerDataFile(player, "economy-data");
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

    public static void resetBalance(Player player){
        File file = PlayerData.getPlayerDataFile(player, "economy-data");
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
}
