package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.lang.management.ManagementFactory;
import java.util.Arrays;

import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;
import static me.capitainecat0.multicommands.utils.Perms.ALL_PERMS;
import static me.capitainecat0.multicommands.utils.Perms.SERVERINFO_PERM;

public class ServerInfo implements CommandExecutor {

    /**
     *
     * La commande &quot;/serverinfo&quot; requiert la permission &quot;multicommands.serverinfo&quot; pour fonctionner.
     * <br>Cette commande permet d'afficher les informations sur le serveur.
     */
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        hideActiveBossBar();
        try{
            if(!MultiCommands.getPermissions().has(sender, SERVERINFO_PERM.getPermission()) || !MultiCommands.getPermissions().has(sender, ALL_PERMS.getPermission())){
                playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage());
                return true;
            }
            else{
                if(sender instanceof Player){
                    playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                }
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
           /* try{
                Class.forName("me.capitainecat0.multimaintenance.MultiMaintenance");
                OfflinePlayer player;

                sendMessage(sender, "&6Whitelisted players &8(multimaintenance) &7(&c" + MultiMaintenance.getAUTHORIZED().size() + "&7)");
                for (UUID uuid : MultiMaintenance.getAUTHORIZED()) {
                    player = Bukkit.getOfflinePlayer(uuid);
                    sendMessage(sender, "&e-&a " + player.getName());
                }
                if (MultiMaintenance.ENABLED) {
                    sendMessage(sender, me.capitainecat0.multimaintenance.utils.Messenger.MAINTENANCE_STATUS.getMessage().replace("%status", "&aactive"));
                }else {
                    sendMessage(sender, me.capitainecat0.multimaintenance.utils.Messenger.MAINTENANCE_STATUS.getMessage().replace("%status", "&cinactive"));
                }
            }catch(final Exception ex) {
                sendMessage(sender, "&7Install &cMulti§&Maintenance &7to show more informations!");
            }*/
                sendMessage(sender, "<green><strikethrough>-+----------------------------------------------------------------+-");
            }
        }catch(Exception e){
            sendCommandExceptionMessage(e, command.getName());
            sendMessage(sender, CMD_ERROR.getMessage().replace("<command>", command.getName()).replace("{e}", e.getMessage()));
            sendSuggestCommandMessage(sender, CMD_ERROR_SUGGEST.getMessage(), "helpop" + CMD_ERROR_ASSISTANCE.getMessage().replace("<command>", command.getName()).replace("{e}", e.getMessage()));
        }
        return false;
    }
}
