package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.Messenger;
import me.capitainecat0.multicommands.utils.Perms;
import me.capitainecat0.multimaintenance.MultiMaintenance;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class MultiInfos implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!sender.hasPermission(Perms.MULTIINFOS_PERM.getPermission()) || !sender.hasPermission(Perms.ALL_PERMS.getPermission())){
            sender.sendMessage(Messenger.CMD_NO_PERM.getMessage().replace("%cmd%", command.getName()));
            return true;
        }
        else{
            final boolean installed;
            boolean installed1;
            try{
                Class.forName("me.capitainecat0.multimaintenance.MultiMaintenance");
                installed1 = true;
                assert MultiCommands.instance().getDescription().getDescription() != null;
                sender.sendMessage(Messenger.MULTIINFOS_MSG.getMessage()
                        .replace("%plname%", MultiCommands.instance().getDescription().getName())
                        .replace("%author%", "CapitaineCat0")
                        .replace("%version%", MultiCommands.instance().getDescription().getVersion())
                        .replace("%description%", MultiCommands.instance().getDescription().getDescription()));
                assert MultiMaintenance.instance().getDescription().getDescription() != null;
                sender.sendMessage(Messenger.MULTIINFOS_MSG.getMessage()
                        .replace("%plname%", MultiMaintenance.instance().getDescription().getName())
                        .replace("%author%", "CapitaineCat0")
                        .replace("%version%", MultiMaintenance.instance().getDescription().getVersion())
                        .replace("%description%", MultiMaintenance.instance().getDescription().getDescription()));
            }catch(final Exception ex) {
                installed1 = false;
                assert MultiCommands.instance().getDescription().getDescription() != null;
                sender.sendMessage(me.capitainecat0.multicommands.utils.Messenger.MULTIINFOS_MSG.getMessage()
                        .replace("%plname%", MultiCommands.instance().getDescription().getName())
                        .replace("%author%", "CapitaineCat0")
                        .replace("%version%", MultiCommands.instance().getDescription().getVersion())
                        .replace("%description%", MultiCommands.instance().getDescription().getDescription()));
            }
            installed = installed1;
        }
        return false;
    }
}
