package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.utils.Messenger;
import me.capitainecat0.multicommands.utils.Perms;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class God implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!sender.hasPermission(Perms.GOD_PERM.getPermission()) || !sender.hasPermission(Perms.ALL_PERMS.getPermission())){
            sender.sendMessage(Messenger.CMD_NO_PERM.getMessage().replace("%cmd%", command.getName()));
            return true;
        }
        else{
            if(sender instanceof Player){
                Player player = (Player) sender;
                if(args.length == 0){
                    if(player.isInvulnerable()){
                        player.setInvulnerable(false);
                        player.setGlowing(false);
                        player.sendMessage(Messenger.GOD_SELF_OFF.getMessage());
                    }else if(!player.isInvulnerable()){
                        player.setInvulnerable(true);
                        player.setGlowing(true);
                        player.sendMessage(Messenger.GOD_SELF_ON.getMessage());
                    }
                }
                if(args.length == 1){
                    Player target = Bukkit.getPlayerExact(args[0]);
                    if(target != null){
                        if(target.isInvulnerable()){
                            target.setInvulnerable(false);
                            target.setGlowing(false);
                            target.sendMessage(Messenger.GOD_OTHER_OFF.getMessage());
                            sender.sendMessage(Messenger.GOD_OTHER_ADMIN_OFF.getMessage().replace("%p", target.getName()));
                        }else if(!target.isInvulnerable()){
                            target.setInvulnerable(true);
                            target.setGlowing(true);
                            target.sendMessage(Messenger.GOD_OTHER_ON.getMessage());
                            sender.sendMessage(Messenger.GOD_OTHER_ADMIN_ON.getMessage().replace("%p", target.getName()));
                        }
                    }else{
                        sender.sendMessage(Messenger.NOT_A_PLAYER.getMessage().replace("%p", args[0]));
                    }
                }
            }else if(sender instanceof ConsoleCommandSender){
                if(args.length == 0){
                    sender.sendMessage(Messenger.NO_CONSOLE_COMMAND_WITHOUT_ARGS.getMessage().replace("%cmd%", command.getName()));
                }
                if(args.length == 1){
                    Player target = Bukkit.getPlayerExact(args[0]);
                    if(target != null){
                        if(target.isInvulnerable()){
                            target.setInvulnerable(false);
                            target.setGlowing(false);
                            target.sendMessage(Messenger.GOD_OTHER_OFF.getMessage());
                            sender.sendMessage(Messenger.GOD_OTHER_ADMIN_OFF.getMessage().replace("%p", target.getName()));
                        }else if(!target.isInvulnerable()){
                            target.setInvulnerable(true);
                            target.setGlowing(true);
                            target.sendMessage(Messenger.GOD_OTHER_ON.getMessage());
                            sender.sendMessage(Messenger.GOD_OTHER_ADMIN_ON.getMessage().replace("%p", target.getName()));
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
