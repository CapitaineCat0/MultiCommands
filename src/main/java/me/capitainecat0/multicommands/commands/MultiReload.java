package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.data.ConfigData;
import me.capitainecat0.multicommands.utils.MessengerUtils;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOError;
import java.io.IOException;

import static me.capitainecat0.multicommands.utils.Messenger.CMD_NO_PERM;
import static me.capitainecat0.multicommands.utils.Messenger.PLUGIN_RELOADED;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;
import static me.capitainecat0.multicommands.utils.Perms.ALL_PERMS;

public class MultiReload implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        hideActiveBossBar();
        if(!sender.hasPermission(ALL_PERMS.getPermission())){
            if(soundEnabled()){
                playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
            }
            sendMessage(sender, CMD_NO_PERM.getMessage());
            return true;
        }else {
            if(sender instanceof Player){
                if(soundEnabled()){
                    playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                }
                sendMessage(sender, PLUGIN_RELOADED.getMessage());
                MessengerUtils.reloadLang();
                MultiCommands.getInstance().reloadConfig();
            }else if(sender instanceof ConsoleCommandSender){
                sendConsoleMessage(PLUGIN_RELOADED.getMessage());
                MessengerUtils.reloadLang();
                MultiCommands.getInstance().reloadConfig();
            }

        }
        return false;
    }
}
