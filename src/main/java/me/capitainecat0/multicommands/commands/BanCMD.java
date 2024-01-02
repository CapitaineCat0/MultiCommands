package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.commands.implementers.Ban;
import me.capitainecat0.multicommands.utils.permissions.PermissionManager;
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

import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;
import static me.capitainecat0.multicommands.utils.permissions.Perms.ALL_PERMS;
import static me.capitainecat0.multicommands.utils.permissions.Perms.BAN_PERM;

public class BanCMD implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        hideActiveBossBar();
        PermissionManager perms = MultiCommands.getPermissionManager();
        try {
            if (sender instanceof Player) {
                if (!perms.hasPermission((OfflinePlayer) sender, BAN_PERM.getPermission())
                        || !perms.hasPermission((OfflinePlayer) sender, ALL_PERMS.getPermission())) {
                    playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                    getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage().replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                    return true;
                } else {
                    if (args.length <= 1) {
                        playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                        getMsgSendConfig(sender, command.getName(), CMD_NO_ARGS.getMessage().replace("<command>", command.getName()).replace("{0}", "<player> <reason>"));
                    } else if (args.length == 2) {
                        UUID uuid = Objects.requireNonNull(Bukkit.getPlayerExact(args[0])).getUniqueId();
                        OfflinePlayer target = Bukkit.getOfflinePlayer(uuid);
                        if (!target.isBanned()) {
                            new Ban(target, sender, args).execute();
                            sendBroadcastMessage(BAN_ALERT.getMessage());
                        } else {
                            playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                            sendMessage(sender, BAN_ERROR.getMessage().replace("{0}", Objects.requireNonNull(target.getName())));
                        }
                    }
                }
            } else if (sender instanceof ConsoleCommandSender) {
                if(args.length <= 1){
                    sendConsoleMessage(NO_CONSOLE_COMMAND_WITHOUT_ARGS.getMessage().replace("<command>", command.getName()).replace("{0}", "<player> <reason>"));
                }
                else if(args.length == 2) {
                    UUID uuid = Objects.requireNonNull(Bukkit.getPlayerExact(args[0])).getUniqueId();
                    OfflinePlayer target = Bukkit.getOfflinePlayer(uuid);
                    if (!target.isBanned()) {
                        new Ban(target, sender, new String[]{getBanReason(args)}).execute();
                        sendConsoleMessage(BAN_ALERT.getMessage());
                    } else {
                        sendConsoleMessage(BAN_ERROR.getMessage().replace("{0}", Objects.requireNonNull(target.getName())));
                    }
                }
            }
        } catch (Exception e) {
            sendCommandExceptionMessage(e, command.getName());
            sendMessage(sender, CMD_ERROR.getMessage().replace("<command>", command.getName()).replace("{e}", e.getMessage()));
        }
        return false;
    }

    private @NotNull String getBanReason(String @NotNull [] args) {
        StringBuilder bc = new StringBuilder();
        for (String part : args) {
            bc.append(part).append(" ");
        }
        return bc.toString().replace(args[0] + " ", "");
    }
}
