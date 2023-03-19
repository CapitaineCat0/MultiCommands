package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multimaintenance.MultiMaintenance;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static me.capitainecat0.multicommands.utils.Messenger.CMD_NO_PERM;
import static me.capitainecat0.multicommands.utils.Messenger.MULTIINFOS_MSG;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;
import static me.capitainecat0.multicommands.utils.Perms.ALL_PERMS;
import static me.capitainecat0.multicommands.utils.Perms.MULTIINFOS_PERM;

public class MultiInfos implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        hideActiveBossBar();
        if(!sender.hasPermission(MULTIINFOS_PERM.getPermission()) || !sender.hasPermission(ALL_PERMS.getPermission())){
            if(soundEnabled()){
                playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
            }
            getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage());
            return true;
        }
        else{
            if(sender instanceof Player){
                if(soundEnabled()){
                    playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                }
            }
            try{
                Class.forName("me.capitainecat0.multimaintenance.MultiMaintenance");
                assert MultiCommands.getInstance().getDescription().getDescription() != null;
                sendMessage(sender, MULTIINFOS_MSG.getMessage()
                        .replace("%plname%", MultiCommands.getInstance().getDescription().getName())
                        .replace("%author%", "CapitaineCat0")
                        .replace("%version%", MultiCommands.getInstance().getDescription().getVersion())
                        .replace("%description%", MultiCommands.getInstance().getDescription().getDescription()));
                assert MultiMaintenance.instance().getDescription().getDescription() != null;
                sendMessage(sender, MULTIINFOS_MSG.getMessage()
                        .replace("%plname%", MultiMaintenance.instance().getDescription().getName())
                        .replace("%author%", "CapitaineCat0")
                        .replace("%version%", MultiMaintenance.instance().getDescription().getVersion())
                        .replace("%description%", MultiMaintenance.instance().getDescription().getDescription()));
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
