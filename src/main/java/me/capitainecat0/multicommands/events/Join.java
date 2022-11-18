package me.capitainecat0.multicommands.events;

import me.capitainecat0.multicommands.data.BannedData;
import me.capitainecat0.multicommands.data.FreezeData;
import me.capitainecat0.multicommands.data.PlayerData;
import me.capitainecat0.multicommands.utils.Perms;
import me.capitainecat0.multicommands.utils.VanishHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.hideActiveBossBar;
import static me.capitainecat0.multicommands.utils.MessengerUtils.sendBroadcastMessage;
import static me.capitainecat0.multicommands.utils.PluginCore.colored;

public class Join implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        hideActiveBossBar();
       Player player = event.getPlayer();
       new FreezeData(player);
       if(BannedData.isBanned()){
           player.kickPlayer(BAN_PREFIX.getMessage().replace("%reason%", colored(new BannedData(player).getReason())));
       }
           /*if(Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")){
               String joinText = "&8{ &a+ &8} &e- &a>&f%luckperms_prefix% &3%player_name%";
               joinText = PlaceholderAPI.setPlaceholders(event.getPlayer(), joinText);
               event.setJoinMessage(MultiCommands.colored(joinText));
           }else{*/
        event.setJoinMessage(ONJOIN.getMessage().replace("%player%", player.getName()));
           //}
           if(player.hasPermission(Perms.VANISH_PERM_SELF.getPermission()) || player.hasPermission(Perms.VANISH_PERM_ALL.getPermission()) || player.hasPermission(Perms.ALL_PERMS.getPermission())){
           VanishHandler.getVanished().add(player);
           }
    }
}
