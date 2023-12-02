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
import static me.capitainecat0.multicommands.utils.Perms.ALL_PERMS;
import static me.capitainecat0.multicommands.utils.Perms.TITLE_PERM;

public class Title implements CommandExecutor {

    /**
     * La commande &quot;/title&quot; requiert la permission &quot;multicommands.title&quot; pour fonctionner.
     * <br>Cette commande permet d'envoyer un message en titre aux joueurs.
     * <br>*
     * <br>Si l'argument &quot;0&quot; correspond à &quot;@everyone&quot;, le message est envoyé à tous les joueurs.
     * <br>Si l'argument &quot;0&quot; correspond au nom d'un joueur connecté, le message lui sera envoyé.
     */
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        hideActiveBossBar();
        try{
            if(sender instanceof Player){
                if(!MultiCommands.getPermissions().has(sender, TITLE_PERM.getPermission()) || !MultiCommands.getPermissions().has(sender, ALL_PERMS.getPermission())){
                    playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                    getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage());
                }else{
                    if(args.length == 0){
                        playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                        getMsgSendConfig(sender, command.getName(), CMD_NO_ARGS.getMessage().replace("<command>", command.getName()).replace("{0}", "<message>"));
                    }else if(args.length > 1){
                        Player target = Bukkit.getPlayerExact(args[0]);
                        if(target != null){
                            StringBuilder bc = new StringBuilder();
                            for(String part : args) {
                                bc.append(part).append(" ");
                            }
                            sendMessage(sender, ACTIONBAR_SENT_TO_OTHER.getMessage().replace("{0}", target.getName()));
                            sendTitle(target, args[1], bc.toString().replace(args[0], "").replace(args[1], ""));
                        }else{
                            StringBuilder bc = new StringBuilder();
                            for(String part : args) {
                                bc.append(part).append(" ");
                            }
                            sendMessage(sender, ACTIONBAR_SENT_TO_ALL.getMessage());
                            sendTitle(args[0], bc.toString().replace(args[0], ""));
                        }
                    }
                }
            }else if(sender instanceof ConsoleCommandSender){
                sendConsoleMessage(NO_CONSOLE_COMMAND.getMessage().replace("<command>", command.getName()));
            }
        }catch (Exception e){
            sendCommandExceptionMessage(e, command.getName());
            sendMessage(sender, CMD_ERROR.getMessage().replace("<command>", command.getName()).replace("{e}", e.getMessage()));
            sendSuggestCommandMessage(sender, CMD_ERROR_SUGGEST.getMessage(), "helpop" + CMD_ERROR_ASSISTANCE.getMessage().replace("<command>", command.getName()).replace("{e}", e.getMessage()));
        }
        return true;
    }
}
