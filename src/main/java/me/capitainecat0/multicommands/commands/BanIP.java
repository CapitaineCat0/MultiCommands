package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.data.BannedData;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.UUID;

import static me.capitainecat0.multicommands.MultiCommands.getInstance;
import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;
import static me.capitainecat0.multicommands.utils.Perms.*;

public class BanIP implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        hideActiveBossBar();
        if(sender instanceof Player){
            if(!sender.hasPermission(BANIP_PERM.getPermission()) || !sender.hasPermission(ALL_PERMS.getPermission())){
                if(soundEnabled()){
                    playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                }
                getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage().replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                return true;
            }
            else{
                if(args.length <= 1){
                    if(soundEnabled()){
                        playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                    }
                    getMsgSendConfig(sender, command.getName(), CMD_NO_ARGS.getMessage().replace("<command>", command.getName()).replace("{0}", "<player> <reason>").replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                }
                else if(args.length == 2){
                    UUID uuid = Objects.requireNonNull(Bukkit.getPlayerExact(args[0]).getUniqueId());
                    Player target = (Player) Bukkit.getOfflinePlayer(uuid);
                    StringBuilder bc = new StringBuilder();
                    for(String part : args) {
                        bc.append(part).append(" ");
                    }
                    String banReason = bc.toString().replace(args[0], "");
                    new BannedData(target);
                    if(!target.isBanned()){
                        new BannedData(target).setBanned(target, true);
                        new BannedData(target).setReason(target, banReason);
                        getInstance().getServer().getBanList(BanList.Type.IP).addBan(target.getAddress().toString(), BAN_PREFIX.getMessage().replace("{0}", banReason).replace("{prefix}", PLUGIN_PREFIX.getMessage()), null, sender.getName());
                        if(soundEnabled()){
                            playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                        }
                        sendMessage(sender, BAN_DONE.getMessage().replace("{0}", target.getName()).replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                        for (Player operators : Bukkit.getOnlinePlayers()) {
                            if(Bukkit.getOnlinePlayers().contains(operators)){
                                if(Objects.requireNonNull(operators.getPlayer()).hasPermission(ALL_PERMS.getPermission()) || operators.getPlayer().hasPermission(BAN_PERM.getPermission())){
                                    if(soundEnabled()){
                                        playSound(operators.getPlayer(), Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                                    }
                                    sendMessage(operators, BAN_ADMIN.getMessage().replace("{prefix}", PLUGIN_PREFIX.getMessage()).replace("{0}", target.getName()).replace("{1}", sender.getName()).replace("{2}", banReason));
                                }
                            }
                        }
                        sendMessage(BAN_ALERT.getMessage().replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                    }else{
                        if(soundEnabled()){
                            playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                        }
                        sendMessage(sender, BAN_ERROR.getMessage().replace("{0}", Objects.requireNonNull(target.getName()).replace("{prefix}", PLUGIN_PREFIX.getMessage())));
                    }
                }
            }
        }else if(sender instanceof ConsoleCommandSender){
            if(args.length <= 1){
                sendConsoleMessage(NO_CONSOLE_COMMAND_WITHOUT_ARGS.getMessage().replace("<command>", command.getName()).replace("{0}", "<player> <reason>").replace("{prefix}", PLUGIN_PREFIX.getMessage()));
            }
            else if(args.length == 2){
                UUID uuid = Objects.requireNonNull(Bukkit.getPlayerExact(args[0]).getUniqueId());
                Player target = (Player) Bukkit.getOfflinePlayer(uuid);
                StringBuilder bc = new StringBuilder();
                for(String part : args) {
                    bc.append(part).append(" ");
                }
                String banReason = bc.toString().replace(args[0], "");
                new BannedData(target);
                if(!target.isBanned()){
                    new BannedData(target).setBanned(target, true);
                    new BannedData(target).setReason(target, banReason);
                    getInstance().getServer().getBanList(BanList.Type.IP).addBan(target.getAddress().toString(), BAN_PREFIX.getMessage().replace("{0}", banReason).replace("{prefix}", PLUGIN_PREFIX.getMessage()), null, sender.getName());
                    sendConsoleMessage(BAN_DONE.getMessage().replace("{0}", target.getName()).replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                    for (Player operators : Bukkit.getOnlinePlayers()) {
                        if(Bukkit.getOnlinePlayers().contains(operators)){
                            if(Objects.requireNonNull(operators.getPlayer()).hasPermission(ALL_PERMS.getPermission()) || operators.getPlayer().hasPermission(BAN_PERM.getPermission())){
                                if(soundEnabled()){
                                    playSound(operators.getPlayer(), Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                                }
                                sendMessage(operators, BAN_ADMIN.getMessage().replace("{prefix}", PLUGIN_PREFIX.getMessage()).replace("{0}", target.getName()).replace("{1}", sender.getName()).replace("{2}", banReason));
                            }
                        }
                    }
                    sendConsoleMessage(BAN_ALERT.getMessage().replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                }else{
                    sendConsoleMessage(BAN_ERROR.getMessage().replace("{0}", Objects.requireNonNull(target.getName()).replace("{prefix}", PLUGIN_PREFIX.getMessage())));
                }
            }
        }

        return false;
    }
}
