package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.Messenger;
import me.capitainecat0.multicommands.utils.Perms;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class ClearInventory implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!sender.hasPermission(Perms.CLEARINV_PERM.getPermission()) || !sender.hasPermission(Perms.ALL_PERMS.getPermission())){
            MultiCommands.getInstance().getMsgSendConfig(sender, command.getName(), Messenger.CMD_NO_PERM.getMessage());
            return true;
        }
        else{
            if(sender instanceof Player) {

                if(args.length == 0){
                    Player player = (Player) sender;
                    player.getInventory().clear();
                    player.sendMessage(Messenger.CLEARINV_SELF_DONE.getMessage());
                }else if(args.length == 1){
                    if(sender.hasPermission(Perms.ALL_PERMS.getPermission())){
                        Player target = Bukkit.getPlayerExact(args[0]);
                        if (target != null) {
                            MultiCommands.getInstance().getMsgSendConfig(target, command.getName(), Messenger.CLEARINV_ADMIN.getMessage());
                            MultiCommands.getInstance().getMsgSendConfig(sender, command.getName(), Messenger.CLEARINV_SENDER.getMessage().replace("%p", target.getName()));
                            target.getInventory().clear();
                        }else{
                            MultiCommands.getInstance().getMsgSendConfig(sender, command.getName(), Messenger.NOT_A_PLAYER.getMessage().replace("%p", args[0]));
                        }
                    }else{
                        MultiCommands.getInstance().getMsgSendConfig(sender, command.getName(), Messenger.CMD_NO_PERM_TO_OTHER.getMessage());
                    }
                }
            }else if(sender instanceof ConsoleCommandSender){
                if(args.length == 0){
                    sender.sendMessage(Messenger.NO_CONSOLE_COMMAND_WITHOUT_ARGS.getMessage().replace("%cmd%", command.getName()));
                }else if(args.length == 1) {
                    Player target = Bukkit.getPlayerExact(args[0]);
                    if (target != null) {
                        target.sendMessage(Messenger.CLEARINV_ADMIN.getMessage());
                        sender.sendMessage(Messenger.CLEARINV_SENDER.getMessage().replace("%p", target.getName()));
                        target.getInventory().clear();
                    } else {
                        sender.sendMessage(Messenger.NOT_A_PLAYER.getMessage().replace("%p", args[0]));
                    }
                }
            }
        }
        return false;
    }
}
