package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;
import static me.capitainecat0.multicommands.utils.Perms.ALL_PERMS;
import static me.capitainecat0.multicommands.utils.Perms.SPAWN_PERM;

public class Spawn implements CommandExecutor {

    /**
     *
     * La commande &quot;/spawn&quot; requiert la permission &quot;multicommands.spawn&quot; pour fonctionner.
     * <br>Cette commande permet de vous téléporter au spawn du monde.
     */
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        hideActiveBossBar();
        try{
            if(!MultiCommands.getPermissions().has(sender, SPAWN_PERM.getPermission()) || !MultiCommands.getPermissions().has(sender, ALL_PERMS.getPermission())){
                playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage());
            }else{
                if(sender instanceof Player){
                    if(MultiCommands.getInstance().getConfig().get("spawn.name") != null){
                        Location location = new Location(
                                Bukkit.getWorld(Objects.requireNonNull(MultiCommands.getInstance().getConfig().getString("spawn.name"))),
                                MultiCommands.getInstance().getConfig().getDouble("spawn.x"),
                                MultiCommands.getInstance().getConfig().getDouble("spawn.y"),
                                MultiCommands.getInstance().getConfig().getDouble("spawn.z"),
                                MultiCommands.getInstance().getConfig().getInt("spawn.yaw"),
                                MultiCommands.getInstance().getConfig().getInt("spawn.pitch"));
                        ((Player) sender).teleport(location);
                        playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                        getMsgSendConfig(sender, command.getName(), SPAWN_DONE.getMessage());
                    }else{
                        playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                        getMsgSendConfig(sender, command.getName(), SPAWN_ERROR.getMessage());
                    }
                }else if(sender instanceof ConsoleCommandSender){
                    sendConsoleMessage(NO_CONSOLE_COMMAND.getMessage().replace("<command>", command.getName()));
                }
            }
        }catch (Exception e){
            sendCommandExceptionMessage(e, command.getName());
            sendMessage(sender, CMD_ERROR.getMessage().replace("<command>", command.getName()).replace("{e}", e.getMessage()));
            sendSuggestCommandMessage(sender, CMD_ERROR_SUGGEST.getMessage(), "helpop" + CMD_ERROR_ASSISTANCE.getMessage().replace("<command>", command.getName()).replace("{e}", e.getMessage()));
        }
        return false;
    }
}
