package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.commands.implementers.Kill;
import me.capitainecat0.multicommands.utils.permissions.PermissionManager;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;
import static me.capitainecat0.multicommands.utils.permissions.Perms.*;

public class KillCMD implements CommandExecutor {

    /**
     *
     * The Kill command can kill player
     * <br>If args isn't null, it will kill targeted player
     * <br>If args is null, it will kill command sender
     */
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        hideActiveBossBar();
        PermissionManager perms = MultiCommands.getPermissionManager();
            if(sender instanceof Player){
                try{
                    if(!perms.hasPermission((Player) sender, KILL_PERM.getPermission())
                            || !perms.hasPermission((Player) sender, ALL_PERMS.getPermission())){
                        playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                        getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage());
                        return true;
                }else{
                    if(args.length <= 1){
                        new Kill((Player) sender).execute();
                        playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                        getMsgSendConfig(sender, command.getName(), KILL_DONE.getMessage().replace("{0}", sender.getName()));
                    }
                    else if(args.length == 2){
                        Player target = Objects.requireNonNull(Bukkit.getPlayerExact(args[0]));
                        new Kill(target).execute();
                        playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                        getMsgSendConfig(sender, command.getName(), KILL_DONE.getMessage().replace("{0}", target.getName()));
                    }
                }
            } catch(Exception e){
                    sendCommandExceptionMessage(e, command.getName());
                    sendMessage(sender, CMD_ERROR.getMessage().replace("<command>", command.getName()).replace("{e}", e.getMessage()));
                    sendSuggestCommandMessage(sender, CMD_ERROR_SUGGEST.getMessage(), "helpop" + CMD_ERROR_ASSISTANCE.getMessage().replace("<command>", command.getName()).replace("{e}", e.getMessage()));
                }
        }else if(sender instanceof ConsoleCommandSender) {
                try{
                    if(args.length <= 1){
                        sendConsoleMessage(NO_CONSOLE_COMMAND_WITHOUT_ARGS.getMessage().replace("<command>", command.getName()).replace("{0}", "<player>"));
                    }
                    else if(args.length == 2){
                        Player target = Objects.requireNonNull(Bukkit.getPlayerExact(args[0]));
                        new Kill(target).execute();
                        sendConsoleMessage(KILL_DONE.getMessage().replace("{0}", target.getName()));
                    }
                }catch(Exception e){
                    sendCommandExceptionMessage(e, command.getName());
                    sendMessage(sender, CMD_ERROR.getMessage().replace("<command>", command.getName()).replace("{e}", e.getMessage()));
                }
            }
        return false;
    }
}
