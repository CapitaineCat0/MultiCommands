package me.capitainecat0.multicommands.commands.implementers;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.AFKHandler;
import me.capitainecat0.multicommands.utils.CommandsImpl;
import me.capitainecat0.multicommands.utils.VanishHandler;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.Arrays;

import static me.capitainecat0.multicommands.utils.MessengerUtils.playSoundIfEnabled;
import static me.capitainecat0.multicommands.utils.MessengerUtils.sendMessage;

public class ServerInfo implements CommandsImpl {

    private final Player sender;

    public ServerInfo(Player sender) {
        this.sender = sender;
    }
    @Override
    public void execute() {
        playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
        sendMessage(sender, "<green><strikethrough>-+---------------+- </strikethrough><gray> - <yellow><bold>{ <red>Server Informations <yellow><bold>} <gray>- <green><strikethrough>-+---------------+-</strikethrough>");
        sendMessage(sender, "<gold>IP <red>/<gold> Port: <red>" + Bukkit.getServer().getIp() + " <yellow>:<red> " + Bukkit.getServer().getPort());
        sendMessage(sender, "<gold>Jar: <red>" + Bukkit.getServer().getVersion());
        sendMessage(sender, "<gold>Version: <red>" + Bukkit.getServer().getBukkitVersion());
        //sendMessage(sender, "&6Uptime: &c" + ManagementFactory.getRuntimeMXBean().getStartTime() / 1024 / 1024 + " &6milliseconds &8(&e"+ManagementFactory.getRuntimeMXBean().getStartTime() / 1024 / 1024 / 1000 +"h&8)");
        sendMessage(sender, "<gold>TPS: <green>"+ Arrays.toString(Bukkit.getTPS()));
        sendMessage(sender, "<gold>MaxRam: <red>" + Runtime.getRuntime().maxMemory() / 1024 / 1024 +" <gold>Mo");
        sendMessage(sender, "<gold>FreeRam: <red>" + Runtime.getRuntime().freeMemory() / 1024 / 1024+" <gold>Mo <yellow>/ <red>" + Runtime.getRuntime().totalMemory() / 1024 / 1024+" <gold>Mo");
        sendMessage(sender, "<gold>Processor: <red>" + Runtime.getRuntime().availableProcessors() + " <gold>cores");
        sendMessage(sender, "<gold>Players: <red>" + Bukkit.getServer().getOnlinePlayers().size() + "<yellow> / <red>" + Bukkit.getServer().getMaxPlayers());
        sendMessage(sender, "<gold>Operators: <gray>(<red>" + Bukkit.getServer().getOperators().size() + "<gray>)");
        for (OfflinePlayer op : Bukkit.getOperators()) {
            sendMessage(sender, "<red>- <gray>" + op.getName());
        }
        sendMessage(sender, "<gold>Whitelisted: <gray>(<red>" + Bukkit.getServer().getWhitelistedPlayers().size() + "<gray>)");
        for(OfflinePlayer wl : Bukkit.getWhitelistedPlayers()){
            sendMessage(sender, "<red>- <gray>" + wl.getName());
        }
        sendMessage(sender, "<gold>Banned: <gray>(<red>" + Bukkit.getServer().getBannedPlayers().size() + "<gray>) ");
        for(OfflinePlayer ban : Bukkit.getBannedPlayers()){
            sendMessage(sender, "<red>- <gray>" + ban.getName());
        }
        sendMessage(sender, "<gold>Vanished: <gray>(<red>" + VanishHandler.getVanished().size() + "<gray>)");
        for(OfflinePlayer vanished : VanishHandler.getVanished()){
            sendMessage(sender, "<red>- <gray>" + vanished.getName());
        }
        sendMessage(sender, "<gold>AFK: <gray>(<red>" + AFKHandler.getAFK().size() + "<gray>)");
        for(OfflinePlayer afk : AFKHandler.getAFK()){
            sendMessage(sender, "<red>- <gray>" + afk.getName());
        }
        sendMessage(sender, "<green><strikethrough>-+----------------------------------------------------------------+-");
    }
}
