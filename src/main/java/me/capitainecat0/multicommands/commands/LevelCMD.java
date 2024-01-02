package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.commands.implementers.Level;
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

public class LevelCMD implements CommandExecutor {

    /**
     *
     * The Level command can manage player levels
     * @params:
     * <br>add - Add levels to player
     * <br>set - Set the player level
     * <br>remove - Remove levels from player
     */
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        hideActiveBossBar();
        PermissionManager perms = MultiCommands.getPermissionManager();
        if(sender instanceof Player){
            try{
                if(args.length <= 1){
                    playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                    getMsgSendConfig(sender, command.getName(), CMD_NO_ARGS.getMessage().replace("<command>", command.getName()).replace("{0}", "<add | set | remove> <value> [player]"));
                }else{
                    String action = args[0].toLowerCase();
                    if(action.equals("add") || action.equals("set") || action.equals("remove")){
                        if(!perms.hasPermission((Player) sender, LEVEL_ADD_PERM.getPermission())
                                || !perms.hasPermission((Player) sender, LEVEL_ALL_PERM.getPermission())
                                || !perms.hasPermission((Player) sender, ALL_PERMS.getPermission())){
                            playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                            getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage());
                        }else{
                            if(args.length == 2){
                                new Level((Player) sender, null, action, Integer.parseInt(args[1])).execute();

                            }else if(args.length == 3){
                                Player target = Bukkit.getPlayerExact(args[2]);
                                new Level((Player) sender, target, action, Integer.parseInt(args[1])).execute();
                            }
                        }
                    }
                }
            }catch(Exception e){
                sendCommandExceptionMessage(e, command.getName());
                sendMessage(sender, CMD_ERROR.getMessage().replace("<command>", command.getName()).replace("{e}", e.getMessage()));
                sendSuggestCommandMessage(sender, CMD_ERROR_SUGGEST.getMessage(), "helpop" + CMD_ERROR_ASSISTANCE.getMessage().replace("<command>", command.getName()).replace("{e}", e.getMessage()));
            }
        }else if(sender instanceof ConsoleCommandSender){
            try{
                if(args.length >= 2){
                    sendConsoleMessage(NO_CONSOLE_COMMAND_WITHOUT_ARGS.getMessage().replace("{0}", command.getName()).replace("{1}", "<add | set | remove> <value> <player>"));
                }else{
                    String action = args[0].toLowerCase();
                    if(action.equals("add") || action.equals("set") || action.equals("remove")){
                        if(args.length == 2){
                                new Level(null, Bukkit.getPlayerExact(args[1]), action, Integer.parseInt(args[0])).execute();
                            }else if(args.length == 3){
                                Player target = Bukkit.getPlayerExact(args[2]);
                                new Level(null, target, action, Integer.parseInt(args[1])).execute();
                        }
                    }
            }
        }catch(Exception e){
                sendCommandExceptionMessage(e, command.getName());
                sendMessage(sender, CMD_ERROR.getMessage().replace("<command>", command.getName()).replace("{e}", e.getMessage()));
            }
    }
        return false;
    }
}
