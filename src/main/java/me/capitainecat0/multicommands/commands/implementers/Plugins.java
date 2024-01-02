package me.capitainecat0.multicommands.commands.implementers;

import me.capitainecat0.multicommands.utils.CommandsImpl;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import static me.capitainecat0.multicommands.MultiCommands.getInstance;
import static me.capitainecat0.multicommands.utils.MessengerUtils.playSoundIfEnabled;
import static me.capitainecat0.multicommands.utils.MessengerUtils.sendMessage;

public class Plugins implements CommandsImpl {

    private final Player player;

    public Plugins(Player player) {
        this.player = player;
    }
    @Override
    public void execute() {
        playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("cmd-done-sound")), 1.0F, 1.0F);
        sendMessage(player, "<gold>Plugins loaded <gray>(<red>" + Bukkit.getPluginManager().getPlugins().length + "<gray>)<dark_gray>:");
        Plugin[] pm = Bukkit.getPluginManager().getPlugins();
        for (Plugin p : pm) {
            try{
                if(p.isEnabled()){
                    sendMessage(player, "  <blue>- <aqua>" + p.getName() + " <dark_gray>" + p.getDescription().getVersion() + " <green>(enabled)");
                }else{
                    sendMessage(player, "  <blue>- <aqua>" + p.getName() + " <dark_gray>" + p.getDescription().getVersion() + " <red>(disabled)");
                }
            }catch(Error e){
                sendMessage(player, "<red>An error occurred :<yellow>" + e);
            }
        }
    }
}
