package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.commands.implementers.ActionBar;
import me.capitainecat0.multicommands.utils.permissions.PermissionManager;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;
import static me.capitainecat0.multicommands.utils.permissions.Perms.ACTIONBAR_PERMS;
import static me.capitainecat0.multicommands.utils.permissions.Perms.ALL_PERMS;

public class ActionBarCMD implements CommandExecutor {

    /**
     * La commande &quot;/actionbar&quot; requiert la permission &quot;multicommands.actionbar&quot; pour fonctionner.
     * <br>Cette commande permet d'envoyer un message dans la barre d'action des joueurs.
     * <br>*
     * <br>Si l'argument &quot;0&quot; correspond à &quot;@everyone&quot;, le message est envoyé à tous les joueurs.
     * <br>Si l'argument &quot;0&quot; correspond au nom d'un joueur connecté, le message lui sera envoyé.
     */
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        hideActiveBossBar();
        PermissionManager perms = MultiCommands.getPermissionManager();
        if(sender instanceof Player){
            try {
                if(!perms.hasPermission((Player) sender, ACTIONBAR_PERMS.getPermission())
                        || !perms.hasPermission((Player) sender, ALL_PERMS.getPermission())){
                    playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                    getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage());
                }else{
                    if(args.length == 0){
                        playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                        getMsgSendConfig(sender, command.getName(), CMD_NO_ARGS.getMessage().replace("<command>", command.getName()).replace("{0}", "<message>"));
                    }else if(args.length > 1){
                        Player target = Bukkit.getPlayerExact(args[0]);
                        if(target != null){
                            new ActionBar(target, args);
                            sendMessage(sender, ACTIONBAR_SENT_TO_OTHER.getMessage().replace("{0}", target.getName()));
                            playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            playSoundIfEnabled(target, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                        }else{
                            new ActionBar(null, args);
                            sendMessage(sender, ACTIONBAR_SENT_TO_ALL.getMessage());
                            playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            playSoundIfEnabled(null, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                        }
                    }
                }
            }catch (Exception e){
                sendCommandExceptionMessage(e, command.getName());
                sendMessage(sender, CMD_ERROR.getMessage().replace("<command>", command.getName()).replace("{e}", e.getMessage()));
            }
        }else if(sender instanceof ConsoleCommandSender){
            sendConsoleMessage(NO_CONSOLE_COMMAND.getMessage().replace("<command>", command.getName()));
        }
        return true;
    }
}
