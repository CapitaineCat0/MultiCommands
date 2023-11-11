package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.VanishHandler;
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

public class Vanish implements CommandExecutor {

    /**
     * La commande &quot;/vanish&quot; requiert la permission &quot;multicommands.vanish&quot; pour fonctionner.
     * <br>Cette commande permet de gérer votre vanish.
     */
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        hideActiveBossBar();
        try{
            if(sender instanceof Player){
                boolean hasVanishPerm = sender.hasPermission(VANISH_PERM_SELF.getPermission()) || sender.hasPermission(VANISH_PERM_ALL.getPermission()) || sender.hasPermission(ALL_PERMS.getPermission());
                boolean hasOtherVanishPerm = sender.hasPermission(VANISH_PERM_OTHER.getPermission()) || sender.hasPermission(VANISH_PERM_ALL.getPermission()) || sender.hasPermission(ALL_PERMS.getPermission());
                if (args.length == 0) {
                    if (hasVanishPerm) {
                        Player player = (Player) sender;
                        VanishHandler handler = VanishHandler.getInstance();
                        boolean isVanished = handler.isVanished(player);
                        if (!isVanished) {
                            handler.toggleVanish(player);
                            playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            getMsgSendConfig(sender, command.getName(), VANISH_ENABLED_SELF.getMessage());
                        } else {
                            handler.toggleVanish(player);
                            playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            getMsgSendConfig(sender, command.getName(), VANISH_DISABLED_SELF.getMessage());
                        }
                    } else {
                        playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                        getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage());
                        return true;
                    }
                } else if (args.length == 1) {
                    if (hasOtherVanishPerm) {
                        Player target = Bukkit.getPlayerExact(args[0]);
                        VanishHandler handler = VanishHandler.getInstance();
                        if (target != null) {
                            boolean isVanished = handler.isVanished(target);
                            if (!isVanished) {
                                handler.toggleVanish(target);
                                String doneSound = MultiCommands.getInstance().getConfig().getString("cmd-done-sound");
                                playSoundIfEnabled(target, Sound.valueOf(doneSound), 1f, 1f);
                                playSoundIfEnabled(sender, Sound.valueOf(doneSound), 1f, 1f);
                                getMsgSendConfig(target, command.getName(), VANISH_ENABLED_OTHER.getMessage());
                                getMsgSendConfig(sender, command.getName(), VANISH_ENABLED_ADMIN.getMessage().replace("{0}", target.getName()));
                            } else {
                                handler.toggleVanish(target);
                                String doneSound = MultiCommands.getInstance().getConfig().getString("cmd-done-sound");
                                playSoundIfEnabled(target, Sound.valueOf(doneSound), 1f, 1f);
                                playSoundIfEnabled(sender, Sound.valueOf(doneSound), 1f, 1f);
                                getMsgSendConfig(target, command.getName(), VANISH_DISABLED_OTHER.getMessage());
                                getMsgSendConfig(sender, command.getName(), VANISH_DISABLED_ADMIN.getMessage().replace("{0}", target.getName()));
                            }
                        } else {
                            getMsgSendConfig(sender, command.getName(), NOT_A_PLAYER.getMessage().replace("{0}", args[0]));
                        }
                    }
                }
            }else if(sender instanceof ConsoleCommandSender){
                sendConsoleMessage(NO_CONSOLE_COMMAND.getMessage().replace("<command>", command.getName()));
            }
        }catch(Exception e){
            sendCommandExceptionMessage(e, command.getName());
            sendMessage(sender, CMD_ERROR.getMessage().replace("<command>", command.getName()).replace("{e}", e.getMessage()));
            sendSuggestCommandMessage(sender, CMD_ERROR_SUGGEST.getMessage(), "helpop" + CMD_ERROR_ASSISTANCE.getMessage().replace("<command>", command.getName()).replace("{e}", e.getMessage()));
        }
        return false;
    }
}
