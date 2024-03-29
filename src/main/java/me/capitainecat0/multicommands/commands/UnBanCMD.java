package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;

import me.capitainecat0.multicommands.utils.permissions.PermissionManager;
import me.capitainecat0.multicommands.utils.storage.BannedData;
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
import static me.capitainecat0.multicommands.utils.permissions.Perms.*;

public class UnBanCMD implements CommandExecutor {

    /**
     * La commande &quot;/unban&quot; requiert la permission &quot;multicommands.unban&quot; pour fonctionner.
     * <br>Cette commande permet de débannir un joueur banni.
     */
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        hideActiveBossBar();
        PermissionManager perms = MultiCommands.getPermissionManager();
        try{
            if(sender instanceof Player){
                if(!perms.hasPermission((Player) sender, UNBAN_PERM.getPermission())
                        || !perms.hasPermission((Player) sender, ALL_PERMS.getPermission())){
                    playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                    getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage());
                    return true;
                }
                else{
                    UUID uuid = Objects.requireNonNull(Objects.requireNonNull(Bukkit.getPlayerExact(args[0])).getUniqueId());
                    OfflinePlayer target = Bukkit.getOfflinePlayer(uuid);
                    BannedData data = new BannedData();
                    if(target.isBanned()){
                        data.remove(target);
                        getInstance().getServer().getBanList(BanList.Type.PROFILE).pardon(Objects.requireNonNull(target.getName()));
                        playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                        sendMessage(sender, UNBAN_DONE.getMessage().replace("{0}", target.getName()));
                        sendBroadcastMessage(UNBAN_BROADCAST.getMessage().replace("{0}", target.getName()));
                    }else{
                        playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                        sendMessage(sender, UNBAN_ERROR.getMessage().replace("{0}", Objects.requireNonNull(target.getName())));
                    }
                }
            }else if(sender instanceof ConsoleCommandSender){
                if(args.length < 1){
                    sendConsoleMessage(NO_CONSOLE_COMMAND_WITHOUT_ARGS.getMessage().replace("<command>", command.getName()).replace("{0}", "<player>"));
                }
                else if(args.length == 1){
                    UUID uuid = Objects.requireNonNull(Objects.requireNonNull(Bukkit.getPlayerExact(args[0])).getUniqueId());
                    OfflinePlayer target = Bukkit.getOfflinePlayer(uuid);
                    BannedData data = new BannedData();
                    if(target.isBanned()){
                        data.remove(target);
                        getInstance().getServer().getBanList(BanList.Type.PROFILE).pardon(Objects.requireNonNull(target.getName()));
                        sendConsoleMessage(UNBAN_DONE.getMessage().replace("{0}", target.getName()));
                        sendBroadcastMessage(UNBAN_BROADCAST.getMessage().replace("{prefix}", PLUGIN_PREFIX.getMessage()).replace("{0}", target.getName()));
                    }else{
                        sendConsoleMessage(UNBAN_ERROR.getMessage().replace("{0}", Objects.requireNonNull(target.getName())));
                    }
                }
            }
        } catch (Exception e) {
            sendCommandExceptionMessage(e, command.getName());
            sendMessage(sender, CMD_ERROR.getMessage().replace("<command>", command.getName()).replace("{e}", e.getMessage()));
        }
        return false;
    }
}
