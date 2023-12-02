package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;
import static me.capitainecat0.multicommands.utils.Perms.ALL_PERMS;
import static me.capitainecat0.multicommands.utils.Perms.MULTIINFOS_PERM;

public class MultiInfos implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        hideActiveBossBar();
        if(!MultiCommands.getPermissions().has(sender, MULTIINFOS_PERM.getPermission()) || !MultiCommands.getPermissions().has(sender, ALL_PERMS.getPermission())){
            playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
            getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage());
            return true;
        }
        else{
            if(sender instanceof Player){
                playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
            }
            try{
                Class.forName("me.capitainecat0.multimaintenance.MultiMaintenance");
                assert MultiCommands.getInstance().getDescription().getDescription() != null;
                sendMessage(sender, MULTIINFOS_MSG.getMessage()
                        .replace("%plname%", MultiCommands.getInstance().getDescription().getName())
                        .replace("%author%", "CapitaineCat0")
                        .replace("%version%", MultiCommands.getInstance().getDescription().getVersion())
                        .replace("%description%", MultiCommands.getInstance().getDescription().getDescription()));
                /*assert MultiMaintenance.instance().getDescription().getDescription() != null;
                sendMessage(sender, MULTIINFOS_MSG.getMessage()
                        .replace("%plname%", MultiMaintenance.instance().getDescription().getName())
                        .replace("%author%", "CapitaineCat0")
                        .replace("%version%", MultiMaintenance.instance().getDescription().getVersion())
                        .replace("%description%", MultiMaintenance.instance().getDescription().getDescription()));*/
            }catch(final Exception ex) {
                assert MultiCommands.getInstance().getDescription().getDescription() != null;
                sendMessage(sender, MULTIINFOS_MSG.getMessage()
                        .replace("%plname%", MultiCommands.getInstance().getDescription().getName())
                        .replace("%author%", "CapitaineCat0")
                        .replace("%version%", MultiCommands.getInstance().getDescription().getVersion())
                        .replace("%description%", MultiCommands.getInstance().getDescription().getDescription()));
            }
        }
        return false;
    }
}
