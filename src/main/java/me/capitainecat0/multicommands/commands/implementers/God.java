package me.capitainecat0.multicommands.commands.implementers;

import me.capitainecat0.multicommands.utils.CommandsImpl;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import static me.capitainecat0.multicommands.MultiCommands.getInstance;
import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.getMsgSendConfig;
import static me.capitainecat0.multicommands.utils.MessengerUtils.playSoundIfEnabled;

public class God implements CommandsImpl {

    private final Player player;
    private final Player target;

    public God(Player player, Player target) {
        this.player = player;
        this.target = target;
    }
    @Override
    public void execute() {
        if(target!= null){
            if(target.isInvulnerable()){
                target.setInvulnerable(false);
                target.setGlowing(false);
                getMsgSendConfig(target, "God", GOD_OTHER_OFF.getMessage().replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                getMsgSendConfig(player, "God", GOD_OTHER_ADMIN_OFF.getMessage().replace("{prefix}", PLUGIN_PREFIX.getMessage()).replace("{0}", target.getName()));
            }else{
                target.setInvulnerable(true);
                target.setGlowing(true);
                getMsgSendConfig(target, "God", GOD_OTHER_ON.getMessage().replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                getMsgSendConfig(player, "God", GOD_OTHER_ADMIN_ON.getMessage().replace("{prefix}", PLUGIN_PREFIX.getMessage()).replace("{0}", target.getName()));
            }
            playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("cmd-done-sound")), 1.0F, 1.0F);
            playSoundIfEnabled(target, Sound.valueOf(getInstance().getConfig().getString("cmd-done-sound")), 1.0F, 1.0F);
        }else{
            if(player.isInvulnerable()){
                player.setInvulnerable(false);
                player.setGlowing(false);
            }else{
                player.setInvulnerable(true);
                player.setGlowing(true);
            }
        }
        getMsgSendConfig(player, "God", player.isInvulnerable() ? GOD_SELF_OFF.getMessage() : GOD_SELF_ON.getMessage());
        playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("cmd-done-sound")), 1.0F, 1.0F);
    }
}
