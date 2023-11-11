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

public class God implements CommandExecutor {

    /**
     *
     * The God command can toggle god-mode to player
     * <br>If args isn't null, it will toggle god-mode for targeted player
     * <br>If args is null, it will toggle god-mode for command sender
     */
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        hideActiveBossBar();
            if(sender instanceof Player){
                try{
                    if(sender.hasPermission(GOD_PERM_SELF.getPermission()) || sender.hasPermission(GOD_PERM_ALL.getPermission()) || sender.hasPermission(ALL_PERMS.getPermission())){
                        Player player = (Player) sender;
                        if(args.length == 0){
                            boolean isInvulnerable = player.isInvulnerable();
                            player.setInvulnerable(!isInvulnerable);
                            player.setGlowing(!isInvulnerable);
                            playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            getMsgSendConfig(sender, command.getName(), isInvulnerable ? GOD_SELF_OFF.getMessage() : GOD_SELF_ON.getMessage());
                        }
                    } else{
                        playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                        getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage().replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                        return true;
                    }if(args.length == 1){
                        if(sender.hasPermission(GOD_PERM_SELF.getPermission()) || sender.hasPermission(GOD_PERM_ALL.getPermission()) || sender.hasPermission(ALL_PERMS.getPermission())){
                            Player target = Bukkit.getPlayerExact(args[0]);
                            if(target != null){
                                boolean isInvulnerable = target.isInvulnerable();
                                target.setInvulnerable(!isInvulnerable);
                                target.setGlowing(!isInvulnerable);
                                playSoundIfEnabled(target, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                                playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                                getMsgSendConfig(target, command.getName(), isInvulnerable ? GOD_OTHER_OFF.getMessage() : GOD_OTHER_ON.getMessage());
                                getMsgSendConfig(sender, command.getName(), isInvulnerable ? GOD_OTHER_ADMIN_OFF.getMessage().replace("{0}", target.getName()) : GOD_OTHER_ADMIN_ON.getMessage().replace("{0}", target.getName()));
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
