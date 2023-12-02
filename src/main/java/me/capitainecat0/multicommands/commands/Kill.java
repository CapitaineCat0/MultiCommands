package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static me.capitainecat0.multicommands.MultiCommands.getInstance;
import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.Messenger.PLUGIN_PREFIX;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;
import static me.capitainecat0.multicommands.utils.Perms.*;

public class Kill implements CommandExecutor {

    /**
     *
     * The Kill command can kill player
     * <br>If args isn't null, it will kill targeted player
     * <br>If args is null, it will kill command sender
     */
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        hideActiveBossBar();
            if(sender instanceof Player){
                try{
                    if(!MultiCommands.getPermissions().has(sender, KILL_PERM.getPermission()) || !MultiCommands.getPermissions().has(sender, ALL_PERMS.getPermission())){
                        playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                        getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage());
                        return true;
                }else{
                    if(args.length <= 1){
                        playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                        ((Player) sender).setHealth(0.0);
                        getMsgSendConfig(sender, command.getName(), KILL_DONE.getMessage().replace("{0}", sender.getName()));
                    /*final EntityDamageEvent event = new EntityDamageEvent(((Player)sender), EntityDamageEvent.DamageCause.SUICIDE, Float.MAX_VALUE);
                    getInstance().getServer().getPluginManager().callEvent(event);
                    if (event.isCancelled()) {
                        ((Player) sender).setHealth(0.0);
                    }*/
                    }
                    else if(args.length == 2){
                        playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                        Player target = Objects.requireNonNull(Bukkit.getPlayerExact(args[0]));
                        target.setHealth(0.0);
                        getMsgSendConfig(sender, command.getName(), KILL_DONE.getMessage().replace("{0}", target.getName()));
                    /*final EntityDamageEvent event = new EntityDamageEvent(target, EntityDamageEvent.DamageCause.SUICIDE, Float.MAX_VALUE);
                    getInstance().getServer().getPluginManager().callEvent(event);
                    if (event.isCancelled()) {
                        target.setHealth(0.0);
                    }*/
                    }
                }
            } catch(Exception e){
                    sendCommandExceptionMessage(e, command.getName());
                    sendMessage(sender, CMD_ERROR.getMessage().replace("<command>", command.getName()).replace("{e}", e.getMessage()));
                    sendSuggestCommandMessage(sender, CMD_ERROR_SUGGEST.getMessage(), "helpop" + CMD_ERROR_ASSISTANCE.getMessage().replace("<command>", command.getName()).replace("{e}", e.getMessage()));
                }
        }else if(sender instanceof ConsoleCommandSender) {
                try{
                    if(args.length <= 1){
                        sendConsoleMessage(NO_CONSOLE_COMMAND_WITHOUT_ARGS.getMessage().replace("<command>", command.getName()).replace("{0}", "<player>"));
                    }
                    else if(args.length == 2){
                        Player target = Objects.requireNonNull(Bukkit.getPlayerExact(args[0]));
                        target.setHealth(0.0);
                        sendConsoleMessage(KILL_DONE.getMessage().replace("{0}", target.getName()));
                    /*final EntityDamageEvent event = new EntityDamageEvent(target, EntityDamageEvent.DamageCause.SUICIDE, Float.MAX_VALUE);
                    getInstance().getServer().getPluginManager().callEvent(event);
                    if (event.isCancelled()) {
                        target.setHealth(0.0);
                    }*/
                    }
                }catch(Exception e){
                    sendCommandExceptionMessage(e, command.getName());
                    sendMessage(sender, CMD_ERROR.getMessage().replace("<command>", command.getName()).replace("{e}", e.getMessage()));
                }
            }
        return false;
    }
}
