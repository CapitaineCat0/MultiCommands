package me.capitainecat0.multicommands.data;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class BalanceData {

    public static int getBalance(Player player){
        File file = PlayerData.getPlayerDataFile(player);
        assert file != null;
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        return Integer.parseInt(Objects.requireNonNull(configuration.get("balance")).toString());
    }

    public static void setBalance(Player player, int newBal){
        File file = PlayerData.getPlayerDataFile(player);
        assert file != null;
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        configuration.set("balance", newBal);
        try {
            configuration.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void resetBalance(Player player){
        File file = PlayerData.getPlayerDataFile(player);
        assert file != null;
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        configuration.set("balance", 0);
        try {
            configuration.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
