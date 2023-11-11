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


import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;
import static me.capitainecat0.multicommands.utils.Perms.ACTIONBAR_PERMS;
import static me.capitainecat0.multicommands.utils.Perms.ALL_PERMS;

public class ActionBar implements CommandExecutor {
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
        if(sender instanceof Player){
            try {
                if(!sender.hasPermission(ACTIONBAR_PERMS.getPermission()) || !sender.hasPermission(ALL_PERMS.getPermission())){
                    playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                    getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage());
                }else{
                    if(args.length == 0){
                        playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                        getMsgSendConfig(sender, command.getName(), CMD_NO_ARGS.getMessage().replace("<command>", command.getName()).replace("{0}", "<message>"));
                    }else if(args.length > 1){
                        Player target = Bukkit.getPlayerExact(args[0]);
                        if(target != null){
                            String joinedArgs = String.join(" ", args);
                            String bcString = joinedArgs.substring(args[0].length());
                            sendMessage(sender, ACTIONBAR_SENT_TO_OTHER.getMessage().replace("{0}", target.getName()));
                            playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            playSoundIfEnabled(target, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            sendActionBar(target, bcString);
                        }else{
                            String joinedArgs = String.join(" ", args);
                            String bcString = joinedArgs.substring(args[0].length());
                            sendMessage(sender, ACTIONBAR_SENT_TO_ALL.getMessage());
                            playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            playSoundIfEnabled(null, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            sendActionBar(bcString);
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
