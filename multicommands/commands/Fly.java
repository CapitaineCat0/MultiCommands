package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.utils.Messenger;
import me.capitainecat0.multicommands.utils.Perms;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class Fly implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!sender.hasPermission(Perms.FLY_PERM.getPermission()) || !sender.hasPermission(Perms.ALL_PERMS.getPermission())){
            sender.sendMessage(Messenger.CMD_NO_PERM.getMessage().replace("%cmd%", command.getName()));
            return true;
        }
        else{
            if(sender instanceof Player) {
                if(args.length == 0){
                    Player player = (Player) sender;
                    if(!player.getAllowFlight()){
                        player.setAllowFlight(true);
                        player.sendMessage(Messenger.FLY_TOGGLE_ON.getMessage());
                    }else if(player.getAllowFlight()){
                        player.setAllowFlight(false);
                        player.sendMessage(Messenger.FLY_TOGGLE_OFF.getMessage());
                    }

                }else if(args.length == 1){
                    if(sender.hasPermission(Perms.ALL_PERMS.getPermission())){
                        Player player = (Player)sender;
                        Player target = Bukkit.getPlayerExact(args[0]);
                        if (target != null) {
                            if(!target.getAllowFlight()){
                                target.setAllowFlight(true);
                                target.sendMessage(Messenger.FLY_TOGGLE_ON_BY_ADMIN.getMessage());
                                sender.sendMessage(Messenger.FLY_TOGGLE_ON_SENDER.getMessage().replace("%p", target.getName()));
                            }else if(target.getAllowFlight()){
                                target.setAllowFlight(false);
                                target.sendMessage(Messenger.FLY_TOGGLE_OFF_BY_ADMIN.getMessage());
                                sender.sendMessage(Messenger.FLY_TOGGLE_OFF_SENDER.getMessage().replace("%p", target.getName()));
                            }
                        }else{
                            player.sendMessage(Messenger.NOT_A_PLAYER.getMessage().replace("%p", args[0]));
                        }
                    }else{
                        sender.sendMessage(Messenger.CMD_NO_PERM_TO_OTHER.getMessage().replace("%cmd%", command.getName()));
                    }
                }
            }else if(sender instanceof ConsoleCommandSender) {
                if (args.length == 0) {
                    sender.sendMessage(Messenger.NO_CONSOLE_COMMAND_WITHOUT_ARGS.getMessage().replace("%cmd%", command.getName()));
                }
                if (args.length == 1) {
                    Player target = Bukkit.getPlayerExact(args[0]);
                    if (target != null) {
                        if (!target.getAllowFlight()) {
                            target.setAllowFlight(true);
                            target.sendMessage(Messenger.FLY_TOGGLE_ON_BY_ADMIN.getMessage());
                            sender.sendMessage(Messenger.FLY_TOGGLE_ON_SENDER.getMessage().replace("%p", target.getName()));
                        } else if (target.getAllowFlight()) {
                            target.setAllowFlight(false);
                            target.sendMessage(Messenger.FLY_TOGGLE_OFF_BY_ADMIN.getMessage());
                            sender.sendMessage(Messenger.FLY_TOGGLE_OFF_SENDER.getMessage().replace("%p", target.getName()));
                        }
                    } else {
                        sender.sendMessage(Messenger.NOT_A_PLAYER.getMessage().replace("%p", args[0]));
                    }
                }
            }
        }
        return false;
    }
}
