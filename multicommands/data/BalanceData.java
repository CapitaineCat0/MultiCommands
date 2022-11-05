package me.capitainecat0.multicommands.data;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class BalanceData {

    public static int getBalance(Player player){
        File file = PlayerData.getPlayerDataFile(player);
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        return Integer.parseInt(configuration.get("balance").toString());
    }

    public static void setBalance(Player player, int newBal){
        File file = PlayerData.getPlayerDataFile(player);
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
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        configuration.set("balance", 0);
        try {
            configuration.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
