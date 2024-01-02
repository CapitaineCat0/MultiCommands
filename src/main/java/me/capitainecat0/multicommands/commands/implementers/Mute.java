package me.capitainecat0.multicommands.commands.implementers;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.CommandsImpl;
import me.capitainecat0.multicommands.utils.MuteHandler;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.getMsgSendConfig;
import static me.capitainecat0.multicommands.utils.MessengerUtils.playSoundIfEnabled;

public class Mute implements CommandsImpl {
    private final Player player;
    private final Player target;
    private final String[] args;
    private final MuteHandler handler = MuteHandler.getInstance();

    public Mute(Player player, Player target, String @NotNull [] args){
        this.player = player;
        this.target = target;
        this.args = args;
    }
    @Override
    public void execute(){
        StringBuilder bc = new StringBuilder();
        String reason = bc.toString().replace(target.getName(), "");
        for(String part : args) {
            bc.append(part).append(" ");
        }
        playSoundIfEnabled(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
        getMsgSendConfig(player, "Mute", CMD_NO_ARGS.getMessage().replace("<command>", "Mute").replace("{0}", "<player> [reason]"));
        if(args.length < 2){
                if(!handler.isMuted(target)){
                    MuteHandler.toggleMute(target);
                    playSoundIfEnabled(target, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                    playSoundIfEnabled(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                    getMsgSendConfig(player, "Mute", MUTE_ENABLED_ADMIN.getMessage().replace("{0}", target.getName()));
                    getMsgSendConfig(target, "Mute", MUTE_ENABLED.getMessage());
                }else if(handler.isMuted(target)){
                    MuteHandler.getMuted().remove(target);
                    MuteHandler.toggleMute(target);
                    playSoundIfEnabled(target, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                    playSoundIfEnabled(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                    getMsgSendConfig(player, "Mute", MUTE_DISABLED_ADMIN.getMessage().replace("{0}", target.getName()));
                    getMsgSendConfig(target, "Mute", MUTE_DISABLED.getMessage());
                }
            }else {
            if(!handler.isMuted(target)){
                handler.toggleMute(target, reason);
                playSoundIfEnabled(target, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                playSoundIfEnabled(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                getMsgSendConfig(player, "Mute", MUTE_ENABLED_REASON_ADMIN.getMessage().replace("{0}", target.getName()).replace("{1}", reason));
                getMsgSendConfig(target, "Mute", MUTE_ENABLED_REASON.getMessage().replace("{0}", reason));
            }else if(handler.isMuted(target)){
                handler.toggleMute(target, reason);
                playSoundIfEnabled(target, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                playSoundIfEnabled(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                getMsgSendConfig(player, "Mute", MUTE_DISABLED_ADMIN.getMessage().replace("{0}", target.getName()));
                getMsgSendConfig(target, "Mute", MUTE_DISABLED.getMessage());
            }
        }
    }
}
