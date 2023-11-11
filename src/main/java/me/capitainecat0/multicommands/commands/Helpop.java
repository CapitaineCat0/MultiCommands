package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;
import static me.capitainecat0.multicommands.utils.Perms.ALL_PERMS;
import static me.capitainecat0.multicommands.utils.Perms.HELPOP_PERM;

public class Helpop implements CommandExecutor {

    /**
     *
     * The Helpop command provides to send messages to operators
     */
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        hideActiveBossBar();
        try{
            if(sender instanceof Player){
                if(args.length < 1){
                    playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                    getMsgSendConfig(sender, command.getName(), CMD_NO_ARGS.getMessage().replace("<command>", command.getName()).replace("{0}", "<message>").replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                    return true;
                }
                StringBuilder bc = new StringBuilder();
                for(String part : args) {
                    bc.append(part).append(" ");
                }
                for (Player operators : Bukkit.getOnlinePlayers()) {
                    if(Bukkit.getOnlinePlayers().contains(operators)){
                        if(Objects.requireNonNull(operators.getPlayer()).hasPermission(ALL_PERMS.getPermission()) || operators.getPlayer().hasPermission(HELPOP_PERM.getPermission())){
                            playSoundIfEnabled(operators.getPlayer(), Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            sendConsoleMessage(HELPOP_FORMAT.getMessage().replace("{0}", sender.getName()).replace("{1}", bc));
                            sendMessage(operators.getPlayer(),HELPOP_FORMAT.getMessage().replace("{0}", sender.getName()).replace("{1}", bc));
                        }

                        playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                        getMsgSendConfig(sender, command.getName(), HELPOP_DONE.getMessage());
                        sendMessage(sender," <dark_gray>- <gray>" + bc);
                    }else{
                        sendMessage(sender, HELPOP_NO_ADMINS.getMessage());
                    }
                }
            }else if(sender instanceof ConsoleCommandSender){
                sendConsoleMessage(NO_CONSOLE_COMMAND.getMessage().replace("<command>", command.getName()));
            }
        }catch (Exception e){
            sendMessage(sender, CMD_ERROR.getMessage().replace("<command>", command.getName()).replace("{e}", e.getMessage()));
            sendCommandExceptionMessage(e, command.getName());

        }
        return false;
    }
}
