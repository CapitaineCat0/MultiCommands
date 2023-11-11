package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.Messenger;
import me.capitainecat0.multicommands.utils.Perms;
import me.capitainecat0.multicommands.utils.TPAManager;
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

public class TPA implements CommandExecutor {
    private TPAManager tpaManager = new TPAManager();

    public TPA() {
        tpaManager = tpaManager;
    }

    /**
     * La commande &quot;/tpa&quot; requiert la permission &quot;multicommands.tpa&quot; pour fonctionner.
     * <br>Cette commande permet d'envoyer une demande de téléportation à d'autres joueurs.
     */
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            if (!sender.hasPermission(Perms.TPA_PERM.getPermission()) || !sender.hasPermission(Perms.TPA_ALL_PERM.getPermission()) || !sender.hasPermission(Perms.ALL_PERMS.getPermission())) {
                playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                getMsgSendConfig(sender, command.getName(), Messenger.CMD_NO_PERM.getMessage());
            }else{
                try{
                    if (args.length < 1) {
                        playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                        getMsgSendConfig(sender, command.getName(), Messenger.CMD_NO_ARGS.getMessage().replace("<command>", command.getName()).replace("{0}", "<player> | accept | deny"));
                    } else if (args.length > 1) {
                        Player target = Bukkit.getPlayerExact(args[0]);
                        if (target != null) {
                            playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            getMsgSendConfig(target, command.getName(), Messenger.TPA_RECEIVED_REQUEST.getMessage().replace("{0}", sender.getName()));
                            sendHoverCommandMessage(target, "&a&lACCEPT", Messenger.TPA_ACCEPT_HOVER.getMessage(), command.getName()+" accept");
                            sendHoverCommandMessage(target, "&c&lDENY", Messenger.TPA_DENY_HOVER.getMessage(), command.getName()+" deny");
                            if (args[1].equalsIgnoreCase("accept")) {
                                if(!sender.hasPermission(Perms.TPA_ACCEPT_PERM.getPermission())){
                                    playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                                    getMsgSendConfig(sender, command.getName(), Messenger.CMD_NO_PERM.getMessage());
                                }else {
                                    tpaManager.acceptTPA(target);
                                    playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                                    playSoundIfEnabled(target, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                                    getMsgSendConfig(sender, command.getName(), Messenger.TPA_REQUEST_ACCEPT.getMessage().replace("{0}", target.getName()));
                                    getMsgSendConfig(target, command.getName(), Messenger.TPA_ACCEPT.getMessage().replace("{0}", sender.getName()));
                                }
                            } else if (args[1].equalsIgnoreCase("deny")) {
                                if(!target.hasPermission(Perms.TPA_DENY_PERM.getPermission())){
                                    playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                                    getMsgSendConfig(target, command.getName(), Messenger.CMD_NO_PERM.getMessage());
                                }
                                tpaManager.denyTPA(target);
                                playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                                playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                                getMsgSendConfig(sender, command.getName(), Messenger.TPA_REQUEST_DENY.getMessage().replace("{0}", target.getName()));
                                getMsgSendConfig(target, command.getName(), Messenger.TPA_DENY.getMessage().replace("{0}", sender.getName()));
                            }
                        } else {
                            playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                            getMsgSendConfig(sender, command.getName(), Messenger.NOT_A_PLAYER.getMessage().replace("<player>", args[0]));
                        }
                    }
                }catch(Exception e){
                    sendCommandExceptionMessage(e, command.getName());
                    sendMessage(sender, CMD_ERROR.getMessage().replace("<command>", command.getName()).replace("{e}", e.getMessage()));
                    sendSuggestCommandMessage(sender, CMD_ERROR_SUGGEST.getMessage(), "helpop" + CMD_ERROR_ASSISTANCE.getMessage().replace("<command>", command.getName()).replace("{e}", e.getMessage()));
                }
            }
        }else if (sender instanceof ConsoleCommandSender) {
            sendConsoleMessage(Messenger.NO_CONSOLE_COMMAND.getMessage());
        }
        return false;
    }
}
