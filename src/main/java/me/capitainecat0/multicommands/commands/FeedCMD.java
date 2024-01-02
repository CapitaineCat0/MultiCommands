package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.commands.implementers.Feed;
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
import static me.capitainecat0.multicommands.utils.permissions.Perms.*;

public class FeedCMD implements CommandExecutor {

    /**
     *
     * The Feed command can feed player
     * <br>If args isn't null, it will feed targeted player
     * <br>If args is null, it will feed command sender
     */
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        hideActiveBossBar();
        PermissionManager perms = MultiCommands.getPermissionManager();
        try {
            if(sender instanceof Player player){
                if(args.length == 0){
                    if(perms.hasPermission(player, FEED_PERM_SELF.getPermission())
                            || perms.hasPermission(player, FEED_PERM_ALL.getPermission())
                            || perms.hasPermission(player, ALL_PERMS.getPermission())){
                        if(player.getFoodLevel() != 20){
                            new Feed(player, player).execute();
                            playSoundIfEnabled(player, Sound.ENTITY_GENERIC_EAT);
                            getMsgSendConfig(player, command.getName(), FEED_SELF.getMessage().replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                        }else{
                            playSoundIfEnabled(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")));
                            getMsgSendConfig(player, command.getName(), FEED_ALREADY.getMessage().replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                        }
                    }else{
                        playSoundIfEnabled(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")));
                        getMsgSendConfig(player, command.getName(), CMD_NO_PERM.getMessage().replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                    }
                } else if(args.length == 1){
                    if(perms.hasPermission(player, FEED_PERM_OTHER.getPermission())
                            || perms.hasPermission(player, FEED_PERM_ALL.getPermission())
                            || perms.hasPermission(player, ALL_PERMS.getPermission())){
                        Player target = Bukkit.getPlayerExact(args[0]);
                        if(target != null){
                            new Feed(player, target).execute();
                        }else{
                            playSoundIfEnabled(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")));
                            getMsgSendConfig(player, command.getName(), NOT_A_PLAYER.getMessage().replace("{0}", args[0]));
                        }
                    }
                }
            } else if(sender instanceof ConsoleCommandSender){
                if(args.length == 1){
                    Player target = Bukkit.getPlayerExact(args[0]);
                    if(target != null){
                        if(target.getFoodLevel() != 20){
                            target.setFoodLevel(20);
                            sendConsoleMessage(FEED_OTHER_SENDER.getMessage().replace("{0}", target.getName()));
                            playSoundIfEnabled(target, Sound.ENTITY_GENERIC_EAT);
                            getMsgSendConfig(target, command.getName(), FEED_SELF.getMessage().replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                        }else{
                            sendConsoleMessage(FEED_ALREADY_SENDER.getMessage().replace("{0}", target.getName()));
                        }
                    }else{
                        sendConsoleMessage(NOT_A_PLAYER.getMessage().replace("{0}", args[0]));
                    }
                } else {
                    sendConsoleMessage(NO_CONSOLE_COMMAND_WITHOUT_ARGS.getMessage().replace("<command>", command.getName()));
                }
            }
        }catch (Exception e){
            sendCommandExceptionMessage(e, command.getName());
            sendMessage(sender, CMD_ERROR.getMessage().replace("<command>", command.getName()).replace("{e}", e.getMessage()));
            sendSuggestCommandMessage(sender, CMD_ERROR_SUGGEST.getMessage(), "helpop" + CMD_ERROR_ASSISTANCE.getMessage().replace("<command>", command.getName()).replace("{e}", e.getMessage()));        }
        return false;
    }
}