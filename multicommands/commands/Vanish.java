package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.Messenger;
import me.capitainecat0.multicommands.utils.Perms;
import me.capitainecat0.multicommands.utils.VanishHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Vanish implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!sender.hasPermission(Perms.VANISH_PERM.getPermission()) || !sender.hasPermission(Perms.ALL_PERMS.getPermission())){
            MultiCommands.getInstance().getMsgSendConfig(sender, command.getName(), Messenger.CMD_NO_PERM.getMessage());
            return true;
        }
        else{
            if(sender instanceof Player){
                Player player = (Player) sender;
                VanishHandler handler = VanishHandler.getInstance();
                if(!handler.isVanished(player)){
                    handler.toggleVanish(player);
                    MultiCommands.getInstance().getMsgSendConfig(sender, command.getName(), Messenger.VANISH_TOGGLE.getMessage().replace("%status%", "§a§lactivé"));
                }else if(handler.isVanished(player)){
                    handler.toggleVanish(player);
                    MultiCommands.getInstance().getMsgSendConfig(sender, command.getName(), Messenger.VANISH_TOGGLE.getMessage().replace("%status%", "§c§ldésactivé"));
                }
            }else if(sender instanceof ConsoleCommandSender){
                sender.sendMessage(Messenger.NO_CONSOLE_COMMAND.getMessage().replace("%cmd%", command.getName()));
            }
        }
        return false;
    }
}
