package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.commands.implementers.God;
import me.capitainecat0.multicommands.utils.permissions.PermissionManager;
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
import static me.capitainecat0.multicommands.utils.permissions.Perms.*;

public class GodCMD implements CommandExecutor {

    /**
     *
     * The God command can toggle god-mode to player
     * <br>If args isn't null, it will toggle god-mode for targeted player
     * <br>If args is null, it will toggle god-mode for command sender
     */
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        hideActiveBossBar();
        PermissionManager perms = MultiCommands.getPermissionManager();
            if(sender instanceof Player){
                try{
                    if(perms.hasPermission((Player) sender, GOD_PERM_SELF.getPermission())
                            || perms.hasPermission((Player) sender, GOD_PERM_ALL.getPermission())
                            || perms.hasPermission((Player) sender, ALL_PERMS.getPermission())){
                        Player player = (Player) sender;
                        if(args.length == 0){
                            new God(player, null).execute();
                        }
                    } else{
                        playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                        getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage().replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                        return true;
                    }if(args.length == 1){
                        if(perms.hasPermission((Player) sender, GOD_PERM_OTHER.getPermission())
                                || perms.hasPermission((Player) sender, GOD_PERM_ALL.getPermission())
                                || perms.hasPermission((Player) sender, ALL_PERMS.getPermission())){
                            Player target = Bukkit.getPlayerExact(args[0]);
                            if(target != null){
                                new God((Player) sender, target).execute();
                            }else{
                                playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                                getMsgSendConfig(sender, command.getName(), NOT_A_PLAYER.getMessage().replace("{0}", args[0]));
                            }
                        }else{
                            playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                            getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage());
                            return true;
                        }
                    }
                }catch (Exception e){
                    sendCommandExceptionMessage(e, command.getName());
                    sendMessage(sender, CMD_ERROR.getMessage().replace("<command>", command.getName()).replace("{e}", e.getMessage()));
                }
            }else if(sender instanceof ConsoleCommandSender){
                try{
                    if(args.length == 0){
                        sendConsoleMessage(NO_CONSOLE_COMMAND_WITHOUT_ARGS.getMessage().replace("<command>", command.getName()));
                    }
                    if(args.length == 1) {
                        Player target = Bukkit.getPlayerExact(args[0]);
                        if (target != null) {
                            boolean isInvulnerable = target.isInvulnerable();
                            if (isInvulnerable) {
                                target.setInvulnerable(false);
                                target.setGlowing(false);
                                playSoundIfEnabled(target, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                                getMsgSendConfig(target, command.getName(), GOD_OTHER_OFF.getMessage());
                                sendConsoleMessage(GOD_OTHER_ADMIN_OFF.getMessage().replace("{0}", target.getName()));
                            } else {
                                target.setInvulnerable(true);
                                target.setGlowing(true);
                                playSoundIfEnabled(target, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                                getMsgSendConfig(target, command.getName(), GOD_OTHER_ON.getMessage());
                                sendConsoleMessage(GOD_OTHER_ADMIN_ON.getMessage().replace("{0}", target.getName()));
                            }
                        } else {
                            sendConsoleMessage(NOT_A_PLAYER.getMessage().replace("{0}", args[0]));
                        }
                    }
                }catch (Exception e){
                    sendCommandExceptionMessage(e, command.getName());
                    sendMessage(sender, CMD_ERROR.getMessage().replace("<command>", command.getName()).replace("{e}", e.getMessage()));
                }
            }
        return false;
    }
}
