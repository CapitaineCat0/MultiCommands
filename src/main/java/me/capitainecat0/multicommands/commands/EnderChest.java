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

public class EnderChest implements CommandExecutor {

    /**
     *
     * The EnderChest command can open ender-chest
     * <br>If args isn't null, it will open the target's ender-chest
     * <br>If args is null, it will open sender's ender-chest
     */
    @Override
public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
    hideActiveBossBar();
        try {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (args.length == 0) {
                    if(player.hasPermission(ENDERCHEST_PERM_SELF.getPermission()) || player.hasPermission(ENDERCHEST_PERM_ALL.getPermission()) || player.hasPermission(ALL_PERMS.getPermission())){
                        playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                        player.openInventory(player.getEnderChest());
                    }else{
                        playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                        getMsgSendConfig(player, command.getName(), CMD_NO_PERM.getMessage());
                        return true;
                    }
                } else if (args.length == 1) {
                    if(player.hasPermission(ENDERCHEST_PERM_OTHER.getPermission()) || player.hasPermission(ENDERCHEST_PERM_ALL.getPermission()) || player.hasPermission(ALL_PERMS.getPermission())){
                        Player target = Bukkit.getPlayerExact(args[0]);
                        if (target != null) {
                            playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            sendMessage(target, command.getName(), ENDERCHEST_ADMIN_OPEN.getMessage());
                            player.openInventory(target.getEnderChest());
                        }else{
                            playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                            getMsgSendConfig(player, command.getName(), NOT_A_PLAYER.getMessage().replace("{0}", args[0]));
                        }
                    }else{
                        playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                        getMsgSendConfig(player, command.getName(), CMD_NO_PERM_TO_OTHER.getMessage());
                    }
                }
            } else if (sender instanceof ConsoleCommandSender) {
                sendConsoleMessage(NO_CONSOLE_COMMAND.getMessage().replace("<command>", command.getName()));
            }
        }catch (Exception e){
            sendCommandExceptionMessage(e, command.getName());
            sendMessage(sender, CMD_ERROR.getMessage().replace("<command>", command.getName()).replace("{e}", e.getMessage()));
        }
    return false;
    }
}
