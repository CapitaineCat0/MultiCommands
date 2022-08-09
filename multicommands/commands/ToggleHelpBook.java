package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.Messenger;
import me.capitainecat0.multicommands.utils.Perms;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class ToggleHelpBook implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!sender.hasPermission(Perms.ALL_PERMS.getPermission())){
            MultiCommands.getInstance().getMsgSendConfig(sender, command.getName(), Messenger.CMD_NO_PERM.getMessage());
            return true;
        }
        else{
            if(args.length < 1){
                if(MultiCommands.getInstance().HELP_BOOK_ENABLED){
                    MultiCommands.getInstance().setHelpBook(false);
                    MultiCommands.getInstance().getMsgSendConfig(sender, command.getName(), Messenger.TOGGLE_HELP_DISABLED.getMessage());
                }else{
                    MultiCommands.getInstance().setHelpBook(true);
                    MultiCommands.getInstance().getMsgSendConfig(sender, command.getName(), Messenger.TOGGLE_HELP_ENABLED.getMessage());
                }
            }
            else if(args[0].equalsIgnoreCase("true") || args[0].equalsIgnoreCase("on")){
                if(!MultiCommands.getInstance().HELP_BOOK_ENABLED){
                    MultiCommands.getInstance().setHelpBook(true);
                    MultiCommands.getInstance().getMsgSendConfig(sender, command.getName(), Messenger.TOGGLE_HELP_ENABLED.getMessage());
                }else{
                    MultiCommands.getInstance().getMsgSendConfig(sender, command.getName(), Messenger.TOGGLE_HELP_ALREADY_ENABLED.getMessage());
                }
            }
            else if(args[0].equalsIgnoreCase("false") || args[0].equalsIgnoreCase("off")){
                if(MultiCommands.getInstance().HELP_BOOK_ENABLED){
                    MultiCommands.getInstance().setHelpBook(false);
                    MultiCommands.getInstance().getMsgSendConfig(sender, command.getName(), Messenger.TOGGLE_HELP_DISABLED.getMessage());
                }else{
                    MultiCommands.getInstance().getMsgSendConfig(sender, command.getName(), Messenger.TOGGLE_HELP_ALREADY_DISABLED.getMessage());
                }
            }
        }
        return false;
    }
}
