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
import static me.capitainecat0.multicommands.utils.Perms.PLAYERPINGER_PERM;

public class PlayerPinger implements CommandExecutor {
    /**
     * La commande &quot;/playerpinger&quot; requiert la permission &quot;multicommands.playerpinger&quot; pour fonctionner.
     * <br>Cette commande permet d'afficher le ping d'un joueur ou le vôtre.
     */
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        hideActiveBossBar();
        try{
            if(!sender.hasPermission(PLAYERPINGER_PERM.getPermission()) || !sender.hasPermission(ALL_PERMS.getPermission())){
                playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage());
                return true;
            }
            else{
                if(args.length == 0){
                    if(sender instanceof Player){
                        playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                        int ping = 0;//((Player) sender).getPing();
                        if(ping < 50){
                            sendMessage(sender, PING_SELF_MSG.getMessage().replace("{0}", "<green>" + ping + " ms"));
                        }
                        if(ping > 50){
                            sendMessage(sender, PING_SELF_MSG.getMessage().replace("{0}", "<yellow>" + ping + " ms"));
                        }
                        if(ping > 300){
                            sendMessage(sender, PING_SELF_MSG.getMessage().replace("{0}", "<red>" + ping + " ms"));
                        }
                    }else if(sender instanceof ConsoleCommandSender){
                        sendConsoleMessage(NO_CONSOLE_COMMAND_WITHOUT_ARGS.getMessage());
                    }
                }else if(args.length == 1) {
                    Player target = Bukkit.getPlayerExact(args[0]);
                    int ping = 0;
                    if (sender instanceof Player) {
                        playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                        assert target != null;
                        ping = 0;//target.getPing();
                        if (ping < 50) {
                            sendMessage(sender, PING_OTHER_MSG.getMessage().replace("{0}", target.getName()).replace("{1}", "<green>" + ping + " ms"));
                        }
                        if (ping > 50) {
                            sendMessage(sender, PING_OTHER_MSG.getMessage().replace("{0}", target.getName()).replace("{1}", "<yellow>" + ping + " ms"));
                        }
                        if (ping > 300) {
                            sendMessage(sender, PING_OTHER_MSG.getMessage().replace("{0}", target.getName()).replace("{1}", "<red>" + ping + " ms"));
                        }
                    } else if (sender instanceof ConsoleCommandSender) {
                        if (ping < 50) {
                            sendConsoleMessage(PING_OTHER_MSG.getMessage().replace("{0}", target.getName()).replace("{1}", "&a" + ping + " ms"));
                        }
                        if (ping > 50) {
                            sendConsoleMessage(PING_OTHER_MSG.getMessage().replace("{0}", target.getName()).replace("{1}", "&e" + ping + " ms"));
                        }
                        if (ping > 300) {
                            sendConsoleMessage(PING_OTHER_MSG.getMessage().replace("{0}", target.getName()).replace("{1}", "&c" + ping + " ms"));
                        }
                    }
                }
            }
        }catch(Exception e){
            sendCommandExceptionMessage(e, command.getName());
            sendMessage(sender, CMD_ERROR.getMessage().replace("<command>", command.getName()).replace("{e}", e.getMessage()));
            sendSuggestCommandMessage(sender, CMD_ERROR_SUGGEST.getMessage(), "helpop" + CMD_ERROR_ASSISTANCE.getMessage().replace("<command>", command.getName()).replace("{e}", e.getMessage()));
        }
        return false;
    }
}
