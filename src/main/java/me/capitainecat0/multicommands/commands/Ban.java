package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.data.BannedData;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.UUID;

import static me.capitainecat0.multicommands.MultiCommands.getInstance;
import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;
import static me.capitainecat0.multicommands.utils.Perms.*;

public class Ban implements CommandExecutor {
    /**
     *
     * The Ban command ban players that operators targeting
     * <br>If args isn't null, it will be used to ban reason
     * <br>If args is null, the ban reason will be null
     */
    @Override
public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
    hideActiveBossBar();
        try {
            if(sender instanceof Player){
                if(!sender.hasPermission(BAN_PERM.getPermission()) || !sender.hasPermission(ALL_PERMS.getPermission())){
                    playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                    getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage().replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                    return true;
                }
                else{
                    processCommand(sender, command, args);
                }
            }else if(sender instanceof ConsoleCommandSender){
                processConsoleCommand(sender, command, args);
            }
        }catch (Exception e){
            sendCommandExceptionMessage(e, command.getName());
            sendMessage(sender, CMD_ERROR.getMessage().replace("<command>", command.getName()).replace("{e}", e.getMessage()));
        }
    return false;
}

    /**
     * The processCommand function is the main function of this class. It takes in a CommandSender, a Command, and an array of Strings (args)
     * as parameters. The first thing it does is check if there are less than or equal to 1 argument passed into the command by checking
     * args.length &lt;= 1; if that's true, then it plays a sound for the sender using playSoundIfEnabled() with Sound being whatever sound is set in config under &quot;no-perm-sound&quot;
     * and volume/pitch both being 1f; after that it sends them a message from config using getMsgSendConfig() with commandName
     *
     * @param sender Send messages to the player who executed the command
     * @param command Get the command name
     * @param args Get the arguments from the command
     */
 private void processCommand(CommandSender sender, Command command, String[] args){
     try {
         if(args.length <= 1){
             playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
             getMsgSendConfig(sender, command.getName(), CMD_NO_ARGS.getMessage().replace("<command>", command.getName()).replace("{0}", "<player> <reason>"));
         }
         else if(args.length == 2){
             UUID uuid = Objects.requireNonNull(Bukkit.getPlayerExact(args[0])).getUniqueId();
             OfflinePlayer target = Bukkit.getOfflinePlayer(uuid);
             String banReason = getBanReason(args);
             BannedData bannedData = new BannedData(target);
             if(!target.isBanned()){
                 bannedData.setBanned(target, true);
                 bannedData.setReason(target, banReason);
                 getInstance().getServer().getBanList(BanList.Type.NAME).addBan(Objects.requireNonNull(target.getName()), BAN_PREFIX.getMessage().replace("{0}", banReason), null, sender.getName());
                 playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                 sendMessage(sender, BAN_DONE.getMessage().replace("{0}", target.getName()));
                 notifyOperators(target, sender, banReason);
                 sendBroadcastMessage(BAN_ALERT.getMessage());
             }else{
                 playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                 sendMessage(sender, BAN_ERROR.getMessage().replace("{0}", Objects.requireNonNull(target.getName())));
             }
         }
     }catch (Exception e){
         sendCommandExceptionMessage(e, command.getName());
         sendMessage(sender, CMD_ERROR.getMessage().replace("<command>", command.getName()).replace("{e}", e.getMessage()));
     }
}

 /**
  * The processConsoleCommand function is used to process the console command.
  *
  *
  * @param sender Send messages to the player who executed the command
  * @param command Get the command name
  * @param args Get the arguments from the command
  */
 private void processConsoleCommand(CommandSender sender, Command command, String[] args){
     try {
         if(args.length <= 1){
             sendConsoleMessage(NO_CONSOLE_COMMAND_WITHOUT_ARGS.getMessage().replace("<command>", command.getName()).replace("{0}", "<player> <reason>"));
         }
         else if(args.length == 2) {
             UUID uuid = Objects.requireNonNull(Bukkit.getPlayerExact(args[0])).getUniqueId();
             OfflinePlayer target = Bukkit.getOfflinePlayer(uuid);
             String banReason = getBanReason(args);
             BannedData bannedData = new BannedData(target);
             if (!target.isBanned()) {
                 bannedData.setBanned(target, true);
                 bannedData.setReason(target, banReason);
                 getInstance().getServer().getBanList(BanList.Type.NAME).addBan(Objects.requireNonNull(target.getName()), BAN_PREFIX.getMessage().replace("{0}", banReason), null, sender.getName());
                 sendConsoleMessage(BAN_DONE.getMessage().replace("{0}", target.getName()));
                 notifyOperators(target, sender, banReason);
                 sendConsoleMessage(BAN_ALERT.getMessage());
             } else {
                 sendConsoleMessage(BAN_ERROR.getMessage().replace("{0}", Objects.requireNonNull(target.getName())));
             }
         }
     }catch (Exception e){
         sendCommandExceptionMessage(e, command.getName());
         sendMessage(sender, CMD_ERROR.getMessage().replace("{0}", command.getName()).replace("{e}", e.getMessage()));}
}

 /**
  * The getBanReason function takes in a String array of arguments and returns the reason for banning.
  *
  *
  * @param args Get the arguments passed to the command and add it to ban reason
  *
  * @return The ban reason
  */
 private @NotNull String getBanReason(String @NotNull [] args){
    StringBuilder bc = new StringBuilder();
    for (String part : args) {
        bc.append(part).append(" ");
    }
    return bc.toString().replace(args[0] + " ", "");
}

 /**
  * The notifyOperators function is used to notify all online operators of a ban.
  *
  *
  * @param target Get the name of the player who was banned
  * @param sender Get the name of the command sender
  * @param banReason Replace the {2} placeholder in the ban_admin message by ban reason
  */
 private void notifyOperators(OfflinePlayer target, CommandSender sender, String banReason){
    for (Player operators : Bukkit.getOnlinePlayers()) {
        if (Objects.requireNonNull(operators.getPlayer()).hasPermission(ALL_PERMS.getPermission()) || operators.getPlayer().hasPermission(BAN_PERM.getPermission())) {
            playSoundIfEnabled(operators.getPlayer(), Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
            sendMessage(operators, BAN_ADMIN.getMessage().replace("{0}", target.getName()).replace("{1}", sender.getName()).replace("{2}", banReason));
        }
    }
    }
}
