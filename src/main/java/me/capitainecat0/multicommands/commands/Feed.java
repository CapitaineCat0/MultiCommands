package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.MessengerUtils;
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
import static me.capitainecat0.multicommands.utils.Perms.*;

public class Feed implements CommandExecutor {

    /**
     *
     * The Feed command can feed player
     * <br>If args isn't null, it will feed targeted player
     * <br>If args is null, it will feed command sender
     */
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        hideActiveBossBar();
        try {
            if(sender instanceof Player){
                Player player = (Player) sender;
                if(args.length == 0){
                    handleSelfFeed(player, command);
                } else if(args.length == 1){
                    handleOtherFeed(player, command, args[0]);
                }
            } else if(sender instanceof ConsoleCommandSender){
                handleConsoleFeed(command, args);
            }
        }catch (Exception e){
            sendCommandExceptionMessage(e, command.getName());
            sendMessage(sender, CMD_ERROR.getMessage().replace("<command>", command.getName()).replace("{e}", e.getMessage()));
            sendSuggestCommandMessage(sender, CMD_ERROR_SUGGEST.getMessage(), "helpop" + CMD_ERROR_ASSISTANCE.getMessage().replace("<command>", command.getName()).replace("{e}", e.getMessage()));        }
        return false;
    }
    
    
     /**     
      * The handleSelfFeed function is used to handle the /feed command when it is executed by a player.
      * It checks if the player has permission to use this command, and then feeds them if they are not already full.
      * If they are already full, it sends them a message saying so.
     
      *
      * @param player Get the player who executed the command
      * @param command Get the command name
      */
     private void handleSelfFeed(Player player, Command command){
         try {
             if(MultiCommands.getPermissions().has(player, FEED_PERM_SELF.getPermission()) || MultiCommands.getPermissions().has(player, FEED_PERM_ALL.getPermission()) || MultiCommands.getPermissions().has(player, ALL_PERMS.getPermission())){
                 if(player.getFoodLevel() != 20){
                     player.setFoodLevel(20);
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
         }catch (Exception e){
             sendCommandExceptionMessage(e, command.getName());
             sendMessage(player, CMD_ERROR.getMessage().replace("<command>", command.getName()).replace("{e}", e.getMessage()));
         }
    }

     /**
      * The handleOtherFeed function is used to feed another player.
      *
      *
      * @param player Get the player who used the command
      * @param command Get the command name
      * @param targetName Get the target player's name
      */
     private void handleOtherFeed(Player player, Command command, String targetName){
         try {
             if(MultiCommands.getPermissions().has(player, FEED_PERM_OTHER.getPermission()) || MultiCommands.getPermissions().has(player, FEED_PERM_ALL.getPermission()) || MultiCommands.getPermissions().has(player, ALL_PERMS.getPermission())){
                 Player target = Bukkit.getPlayerExact(targetName);
                 if(target != null){
                     if(target.getFoodLevel() != 20){
                         target.setFoodLevel(20);
                         playSoundIfEnabled(target, Sound.ENTITY_GENERIC_EAT);
                         playSoundIfEnabled(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")));
                         getMsgSendConfig(player, command.getName(), FEED_OTHER_SENDER.getMessage().replace("{0}", target.getName()));
                         getMsgSendConfig(target, command.getName(), FEED_OTHER.getMessage());
                     }else{
                         playSoundIfEnabled(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")));
                         getMsgSendConfig(player, command.getName(), FEED_ALREADY_SENDER.getMessage().replace("{0}", target.getName()));
                     }
                 }else{
                     playSoundIfEnabled(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")));
                     getMsgSendConfig(player, command.getName(), NOT_A_PLAYER.getMessage().replace("{0}", targetName));
                 }
             }
         }catch (Exception e){
             sendCommandExceptionMessage(e, command.getName());
             sendMessage(player, CMD_ERROR.getMessage().replace("<command>", command.getName()).replace("{e}", e.getMessage()));
             sendSuggestCommandMessage(player, CMD_ERROR_SUGGEST.getMessage(), "helpop" + CMD_ERROR_ASSISTANCE.getMessage().replace("<command>", command.getName()).replace("{e}", e.getMessage()));
         }
    }

     /**
      * The handleConsoleFeed function is used to handle the feed command when it is executed by a console.
      *
      *
      * @param command Get the name of the command that is being executed
      * @param args Get the arguments from the command
      */
     private void handleConsoleFeed(Command command, String[] args){
         try {
             if(args.length == 1){
                 Player target = Bukkit.getPlayerExact(args[0]);
                 if(target != null){
                     if(target.getFoodLevel() != 20){
                         target.setFoodLevel(20);
                         playSoundIfEnabled(target, Sound.ENTITY_GENERIC_EAT);
                         sendConsoleMessage(FEED_OTHER_SENDER.getMessage().replace("{0}", target.getName()));
                         getMsgSendConfig(target, command.getName(), FEED_OTHER.getMessage());
                     }else{
                         sendConsoleMessage(FEED_ALREADY_SENDER.getMessage().replace("{0}", target.getName()));
                     }
                 }else{
                     sendConsoleMessage(NOT_A_PLAYER.getMessage().replace("{0}", args[0]));
                 }
             } else {
                 sendConsoleMessage(NO_CONSOLE_COMMAND_WITHOUT_ARGS.getMessage().replace("<command>", command.getName()));
             }
         }catch (Exception e){
             sendCommandExceptionMessage(e, command.getName());
             sendConsoleMessage(CMD_ERROR.getMessage().replace("{0}", command.getName()).replace("{e}", e.getMessage()));
         }
    }
}