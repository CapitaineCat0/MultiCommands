package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.Messenger;
import me.capitainecat0.multicommands.utils.Perms;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


public class TP implements CommandExecutor {
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if(!sender.hasPermission(Perms.TELEPORT_PERM.getPermission()) || !sender.hasPermission(Perms.ALL_PERMS.getPermission())){
            sender.sendMessage(Messenger.CMD_NO_PERM.getMessage().replace("%cmd%", command.getName()));
            if(MultiCommands.getInstance().DEBUG_ENABLED) {
                for (OfflinePlayer operators : Bukkit.getOperators()) {
                    if (operators.getPlayer().hasPermission(Perms.ALL_PERMS.getPermission()) || operators.getPlayer().hasPermission(Perms.DEBUG_PERM.getPermission())) {
                        operators.getPlayer().sendMessage(Messenger.CMD_DEBUG_NO_PERM.getMessage()
                                .replace("%p", sender.getName())
                                .replace("%cmd%", command.getName()));
                    }
                }
            }
            return true;
        }
        else{
            if(MultiCommands.getInstance().DEBUG_ENABLED) {
                for (OfflinePlayer operators : Bukkit.getOperators()) {
                    if (operators.getPlayer().hasPermission(Perms.ALL_PERMS.getPermission()) || operators.getPlayer().hasPermission(Perms.DEBUG_PERM.getPermission())) {
                        operators.getPlayer().sendMessage(Messenger.CMD_DEBUG_SUCCES.getMessage()
                                .replace("%p", sender.getName())
                                .replace("%cmd%", command.getName()));
                    }
                }
            }
            if(sender instanceof Player){
                if(args.length == 0){
                    sender.sendMessage(Messenger.TELEPORT_ERROR.getMessage());
                }
                if(args.length <= 4){
                    if(args.length == 1){
                        Player target = Bukkit.getPlayerExact(args[0]);
                        if(target != null){
                            ((Player) sender).teleport(target.getLocation());
                            sender.sendMessage(Messenger.TELEPORT_SELF_TO_PLAYER.getMessage().replace("%p", target.getName()));
                        }else{
                            sender.sendMessage(Messenger.NOT_A_PLAYER.getMessage().replace("%p", args[0]));
                        }
                    }
                    if(args.length == 2){
                        Player target = Bukkit.getPlayerExact(args[0]);
                        Player target2 = Bukkit.getPlayerExact(args[1]);
                        if(args[0].equalsIgnoreCase(target.getName()) && args[1].equalsIgnoreCase(target2.getName())){
                            if(target2 != null){
                                if(target != null){
                                    target.teleport(target2.getLocation());
                                    target.sendMessage(Messenger.TELEPORT_OTHER_TO_OTHER.getMessage().replace("%p", target2.getName()));
                                    sender.sendMessage(Messenger.TELEPORT_OTHER_TO_OTHER_SENDER.getMessage().replace("%p1", target.getName()).replace("%p2", target2.getName()));
                                }else{
                                    sender.sendMessage(Messenger.NOT_A_PLAYER.getMessage().replace("%p", args[0]));
                                }
                            }else{
                                sender.sendMessage(Messenger.NOT_A_PLAYER.getMessage().replace("%p", args[1]));
                            }
                        }else{
                            sender.sendMessage(Messenger.TELEPORT_ERROR.getMessage());
                        }
                    }
                    if(args.length == 3){
                        try{
                            int x = Integer.parseInt(args[0]);
                            int y = Integer.parseInt(args[1]);
                            int z = Integer.parseInt(args[2]);
                            ((Player) sender).teleport(new Location(((Player) sender).getWorld(), x, y, z));
                            sender.sendMessage(Messenger.TELEPORT_SELF_TO_COORDINATES.getMessage().replace("%loc%", "X "+x+" Y "+y+" Z"+z));
                        }catch(NumberFormatException ex){
                            sender.sendMessage(Messenger.TELEPORT_INVALID_COORDINATES.getMessage());
                        }
                    }
                    if(args.length == 4){
                        Player target = Bukkit.getPlayerExact(args[0]);
                        if(target != null){
                            try{
                                int x = Integer.parseInt(args[1]);
                                int y = Integer.parseInt(args[2]);
                                int z = Integer.parseInt(args[3]);
                                target.teleport(new Location(target.getWorld(), x, y, z));
                                target.sendMessage(Messenger.TELEPORT_OTHER_TO_COORDINATES.getMessage().replace("%loc%", "X "+x+" Y "+y+" Z"+z));
                                sender.sendMessage(Messenger.TELEPORT_OTHER_TO_COORDINATES_SENDER.getMessage().replace("%p", target.getName()).replace("%loc%", "X "+x+" Y "+y+" Z"+z));
                            }catch(NumberFormatException ex){
                                sender.sendMessage(Messenger.TELEPORT_OTHER_INVALID_COORDINATES.getMessage());
                            }
                        }else{
                            sender.sendMessage(Messenger.NOT_A_PLAYER.getMessage().replace("%p", args[0]));
                        }
                    }
                }
            }else if(sender instanceof ConsoleCommandSender){
                if(args.length <= 1){
                    sender.sendMessage(Messenger.NO_CONSOLE_COMMAND_WITHOUT_ARGS.getMessage().replace("%cmd%", command.getName()));
                }
                if(args.length == 2){
                    Player target = Bukkit.getPlayerExact(args[0]);
                    Player target2 = Bukkit.getPlayerExact(args[1]);
                    if(args[0].equalsIgnoreCase(target.getName()) && args[1].equalsIgnoreCase(target2.getName())){
                        if(target2 != null){
                            if(target != null){
                                target.teleport(target2.getLocation());
                                target.sendMessage(Messenger.TELEPORT_OTHER_TO_OTHER.getMessage().replace("%p", target2.getName()));
                                sender.sendMessage(Messenger.TELEPORT_OTHER_TO_OTHER_SENDER.getMessage().replace("%p1", target.getName()).replace("%p2", target2.getName()));
                            }else{
                                sender.sendMessage(Messenger.NOT_A_PLAYER.getMessage().replace("%p", args[0]));
                            }
                        }else{
                            sender.sendMessage(Messenger.NOT_A_PLAYER.getMessage().replace("%p", args[1]));
                        }
                    }else{
                        sender.sendMessage(Messenger.TELEPORT_ERROR.getMessage());
                    }
                }
                if(args.length == 3){
                    try{
                        int x = Integer.parseInt(args[0]);
                        int y = Integer.parseInt(args[1]);
                        int z = Integer.parseInt(args[2]);
                        ((Player) sender).teleport(new Location(((Player) sender).getWorld(), x, y, z));
                        sender.sendMessage(Messenger.TELEPORT_SELF_TO_COORDINATES.getMessage().replace("%loc%", "X "+x+" Y "+y+" Z"+z));
                    }catch(NumberFormatException ex){
                        sender.sendMessage(Messenger.TELEPORT_INVALID_COORDINATES.getMessage());
                    }
                }
                if(args.length == 4){
                    Player target = Bukkit.getPlayerExact(args[0]);
                    if(target != null){
                        try{
                            int x = Integer.parseInt(args[1]);
                            int y = Integer.parseInt(args[2]);
                            int z = Integer.parseInt(args[3]);
                            target.teleport(new Location(target.getWorld(), x, y, z));
                            target.sendMessage(Messenger.TELEPORT_OTHER_TO_COORDINATES.getMessage().replace("%loc%", "X "+x+" Y "+y+" Z"+z));
                            sender.sendMessage(Messenger.TELEPORT_OTHER_TO_COORDINATES_SENDER.getMessage().replace("%p", target.getName()).replace("%loc%", "X "+x+" Y "+y+" Z"+z));
                        }catch(NumberFormatException ex){
                            sender.sendMessage(Messenger.TELEPORT_OTHER_INVALID_COORDINATES.getMessage());
                        }
                    }else{
                        sender.sendMessage(Messenger.NOT_A_PLAYER.getMessage().replace("%p", args[0]));
                    }
                }
            }
        }
        return false;
    }
}