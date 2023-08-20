package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;
import static me.capitainecat0.multicommands.utils.Perms.*;

public class ClearInventory implements CommandExecutor {

    /**
     *
     * The ClearInventory command can clear player inventory
     */
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
    hideActiveBossBar();
        try {
            if(sender instanceof Player) {
                if(args.length == 0){
                    if(sender.hasPermission(CLEARINVENTORY_PERM_SELF.getPermission()) || sender.hasPermission(CLEARINVENTORY_PERM_ALL.getPermission()) || sender.hasPermission(ALL_PERMS.getPermission())){
                        ((Player)sender).getInventory().clear();
                        playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                        getMsgSendConfig(sender, command.getName(), CLEARINV_SELF_DONE.getMessage());
                    }else{
                        playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                        getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage());
                    }
                }else if(args.length == 1){
                    Player target = Bukkit.getPlayerExact(args[0]);
                    if (target != null && (sender.hasPermission(CLEARINVENTORY_PERM_OTHER.getPermission()) || sender.hasPermission(CLEARINVENTORY_PERM_ALL.getPermission()) || sender.hasPermission(ALL_PERMS.getPermission()))){
                        target.getInventory().clear();
                        getMsgSendConfig(sender, command.getName(), CLEARINV_SENDER.getMessage().replace("{0}", target.getName()));
                        getMsgSendConfig(target, command.getName(), CLEARINV_ADMIN.getMessage());
                        playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                        playSoundIfEnabled(target, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                    }else{
                        playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                        getMsgSendConfig(sender, command.getName(), CMD_NO_PERM_TO_OTHER.getMessage());
                    }
                }
            }else if(sender instanceof ConsoleCommandSender){
                if(args.length == 0){
                    sendConsoleMessage(NO_CONSOLE_COMMAND.getMessage());
                }else if(args.length == 1) {
                    Player target = Bukkit.getPlayerExact(args[0]);
                    if (target != null) {
                        target.getInventory().clear();
                        target.sendMessage(CLEARINV_ADMIN.getMessage());
                        sendConsoleMessage(CLEARINV_SENDER.getMessage().replace("{0}", target.getName()));
                        playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                    } else {
                        sendConsoleMessage(NOT_A_PLAYER.getMessage().replace("{0}", args[0]));
                    }
                }
            }
        }catch (Exception e){
            sendCommandExceptionMessage(e, command.getName());
        }
    return false;
    }
}
