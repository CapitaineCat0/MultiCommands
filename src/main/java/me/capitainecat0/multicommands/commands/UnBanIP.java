package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.data.BannedData;
import me.capitainecat0.multicommands.data.BannedDataAdmin;
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

public class UnBanIP implements CommandExecutor {

    /**
     * La commande &quot;/unbanip&quot; requiert la permission &quot;multicommands.unbanip&quot; pour fonctionner.
     * <br>Cette commande permet de débannir un joueur banni par ip.
     */
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        hideActiveBossBar();
        try{
            if(sender instanceof Player){
                if(!sender.hasPermission(UNBANIP_PERM.getPermission()) || !sender.hasPermission(ALL_PERMS.getPermission())){
                    playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                    getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage());
                    return true;
                }
                else{
                    UUID uuid = Objects.requireNonNull(Bukkit.getPlayerExact(args[0]).getUniqueId());
                    Player target = (Player) Bukkit.getOfflinePlayer(uuid);
                    new BannedData(target);
                    new BannedDataAdmin("banned", target).removeBanned(target);
                    if(target.isBanned()){
                        new BannedData(target).setBanned(target, false);
                        new BannedData(target).setReason(target, "[]");
                        getInstance().getServer().getBanList(BanList.Type.IP).pardon(target.getAddress().toString());
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
                    UUID uuid = Objects.requireNonNull(Bukkit.getPlayerExact(args[0]).getUniqueId());
                    Player target = (Player) Bukkit.getOfflinePlayer(uuid);
                    new BannedData(target);
                    new BannedDataAdmin("banned", target).removeBanned(target);
                    if(target.isBanned()){
                        new BannedData(target).setBanned(target, false);
                        new BannedData(target).setReason(target, "[]");
                        getInstance().getServer().getBanList(BanList.Type.IP).pardon(target.getAddress().toString());
                        sendConsoleMessage(UNBAN_DONE.getMessage().replace("{0}", target.getName()));
                        sendBroadcastMessage(UNBAN_BROADCAST.getMessage().replace("{0}", target.getName()));
                    }else{
                        sendConsoleMessage(UNBAN_ERROR.getMessage().replace("{0}", Objects.requireNonNull(target.getName())));
                    }
                }
            }
        }catch(Exception e){
            sendCommandExceptionMessage(e, command.getName());
        }
        return false;
    }
}
