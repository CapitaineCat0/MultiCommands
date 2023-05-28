package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.*;
import org.apache.commons.lang.time.DateUtils;
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

import static me.capitainecat0.multicommands.utils.Messenger.CMD_NO_PERM;
import static me.capitainecat0.multicommands.utils.Messenger.PLUGIN_PREFIX;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;
import static me.capitainecat0.multicommands.utils.Perms.ALL_PERMS;
import static me.capitainecat0.multicommands.utils.Perms.SERVERINFO_PERM;

public class ServerInfo implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        hideActiveBossBar();
        if(!sender.hasPermission(SERVERINFO_PERM.getPermission()) || !sender.hasPermission(ALL_PERMS.getPermission())){
            if(soundEnabled()){
                playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
            }
            getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage().replace("{prefix}", PLUGIN_PREFIX.getMessage()));
            return true;
        }
        else{
            if(sender instanceof Player){
                if(soundEnabled()){
                    playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                }
            }
            sendMessage(sender, "&a&m-+---------------+- &7 - &e&l{ &cServer Informations &e&l} &7- &a&m-+---------------+-");
            sendMessage(sender, "&6IP &c/&6 Port: &c" + Bukkit.getServer().getIp() + " &e:&c " + Bukkit.getServer().getPort());
            sendMessage(sender, "&6Jar: &c" + Bukkit.getServer().getVersion());
            sendMessage(sender, "&6Version: &c" + Bukkit.getServer().getBukkitVersion());
            sendMessage(sender, "&6Uptime: &c" + ManagementFactory.getRuntimeMXBean().getStartTime() / 1024 / 1024);
            sendMessage(sender, "&6TPS: &a"+ Arrays.toString(Bukkit.getTPS()));
            sendMessage(sender, "&6MaxRam: &c" + Runtime.getRuntime().maxMemory() / 1024 / 1024 +" &6Go");
            sendMessage(sender, "&6FreeRam: &c" + Runtime.getRuntime().freeMemory() / 1024 / 1024+" &6Go &e/ &c" + Runtime.getRuntime().totalMemory() / 1024 / 1024+" &6Go");
            sendMessage(sender, "&6Processor: &c" + Runtime.getRuntime().availableProcessors() + " &6cores");
            sendMessage(sender, "&6Players: &c" + Bukkit.getServer().getOnlinePlayers().size() + "&e / &c" + Bukkit.getServer().getMaxPlayers());
            sendMessage(sender, "&6Operators: &7(&c" + Bukkit.getServer().getOperators().size() + "&7)");
            for (OfflinePlayer op : Bukkit.getOperators()) {
                sendMessage(sender, "&c- &7" + op.getName());
            }
            sendMessage(sender, "&6Whitelisted: &7(&c" + Bukkit.getServer().getWhitelistedPlayers().size() + "&7)");
            for(OfflinePlayer wl : Bukkit.getWhitelistedPlayers()){
                sendMessage(sender, "&c- &7" + wl.getName());
            }
            sendMessage(sender, "&6Banned: &7(&c" + Bukkit.getServer().getBannedPlayers().size() + "&7) ");
            for(OfflinePlayer ban : Bukkit.getBannedPlayers()){
                sendMessage(sender, "&c- &7" + ban.getName());
            }
            sendMessage(sender, "&6Vanished: &7(&c" + VanishHandler.getVanished().size() + "&7)");
            for(OfflinePlayer vanished : VanishHandler.getVanished()){
                sendMessage(sender, "&c- &7" + vanished.getName());
            }
            sendMessage(sender, "&6AFK: &7(&c" + AFKHandler.getAFK().size() + "&7)");
            for(OfflinePlayer afk : AFKHandler.getAFK()){
                sendMessage(sender, "&c- &7" + afk.getName());
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
            sendMessage(sender, "&a&m-+----------------------------------------------------------------+-");
        }
        return false;
    }
}
