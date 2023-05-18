package me.capitainecat0.multicommands.data;

import me.capitainecat0.multicommands.MultiCommands;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

import static me.capitainecat0.multicommands.data.PlayerData.existsPlayerData;

public class BalanceData {

    private static @NotNull OfflinePlayer offlinePlayer;
    private static Player player;
    private static double balance;

    /**
     *
     * @param offlinePlayer Player name
     * Create BalanceData for that player
     */
    public BalanceData(@NotNull OfflinePlayer offlinePlayer) {
        if(!existsPlayerData("player-data", offlinePlayer)){
            File file = new File(MultiCommands.getInstance().getDataFolder()+"/player-data/", player.getUniqueId()+".yml");
            YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
            configuration.set("pseudo", offlinePlayer.getName());
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
    /**
     *
     * @param player Player name
     * Create BalanceData for that player
     */
    public BalanceData(@NotNull Player player) {
        if(!existsPlayerData("player-data", player)){
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

    /**
     *
     * @param offlinePlayer Player name
     * @return player balance data
     */
    public static double getBalance(OfflinePlayer offlinePlayer){
        File file = PlayerData.getPlayerDataFile(offlinePlayer, "player-data");
        assert file != null;
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        return Double.parseDouble(String.valueOf(configuration.getDouble("balance")));
        //return Integer.parseInt(Objects.requireNonNull(configuration.get("balance")).toString());
    }
    /**
     * @param player Player name
     * @return player balance data
     */
    public static double getBalance(Player player){
        File file = PlayerData.getPlayerDataFile(player, "player-data");
        assert file != null;
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        return Double.parseDouble(String.valueOf(configuration.getDouble("balance")));
        //return Integer.parseInt(Objects.requireNonNull(configuration.get("balance")).toString());
    }

    /**
     *
     * @param offlinePlayer Player name
     * @param newBal New value
     * Set the balance value for that player
     */
    public static void setBalance(OfflinePlayer offlinePlayer, double newBal){
        File file = PlayerData.getPlayerDataFile(offlinePlayer, "player-data");
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
    /**
     *
     * @param player Player name
     * @param newBal New value
     * Set the balance value for that player
     */
    public static void setBalance(Player player, double newBal){
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

    /**
     *
     * @param offlinePlayer Player name
     * Reset the player balance
     */
    public static void resetBalance(OfflinePlayer offlinePlayer){
        File file = PlayerData.getPlayerDataFile(offlinePlayer, "player-data");
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
    /**
     *
     * @param player Player name
     * Reset the player balance
     */
    public static void resetBalance(Player player){
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
    public @NotNull OfflinePlayer getOfflinePlayer() {
        return offlinePlayer;
    }
    public Player getPlayer(){
        return player;
    }
}
