package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.commands.implementers.Report;
import me.capitainecat0.multicommands.utils.permissions.PermissionManager;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static me.capitainecat0.multicommands.utils.Messenger.CMD_NO_PERM;
import static me.capitainecat0.multicommands.utils.MessengerUtils.getMsgSendConfig;
import static me.capitainecat0.multicommands.utils.MessengerUtils.playSoundIfEnabled;
import static me.capitainecat0.multicommands.utils.permissions.Perms.ALL_PERMS;
import static me.capitainecat0.multicommands.utils.permissions.Perms.REPORT_PERM;


public class ReportCMD implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        PermissionManager perms = MultiCommands.getPermissionManager();
        if(!perms.hasPermission((OfflinePlayer) sender, REPORT_PERM.getPermission()) || !perms.hasPermission((OfflinePlayer) sender, ALL_PERMS.getPermission())){
            playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1, 1);
            getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage().replace("<command>", command.getName()));
        }else{
            if(args.length < 2){
                getMsgSendConfig(sender, command.getName(), "<red>Usage: <yellow>/report <player> <message>");
                playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1, 1);
            }else{
                OfflinePlayer reported = MultiCommands.getInstance().getServer().getOfflinePlayer(args[0]);
                new Report((Player) reported, (Player) sender, args);
            }
        }
        return false;
    }
}
