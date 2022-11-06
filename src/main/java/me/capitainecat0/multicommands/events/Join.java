package me.capitainecat0.multicommands.events;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.data.ConfigData;
import me.capitainecat0.multicommands.data.FreezeData;
import me.capitainecat0.multicommands.data.PlayerData;
import me.capitainecat0.multicommands.utils.MessengerUtils;
import me.capitainecat0.multicommands.utils.Perms;
import me.capitainecat0.multicommands.utils.VanishHandler;
import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.PluginManager;

import java.awt.*;

import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.hideActiveBossBar;

public class Join implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        hideActiveBossBar();
       Player player = event.getPlayer();
       if(!PlayerData.existsPlayerData(player)){
           PlayerData.createPlayerData(player);
       }
           if(Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")){
               String joinText = "&8{ &a+ &8} &e- &a>&f%luckperms_prefix% &3%player_name%";
               joinText = PlaceholderAPI.setPlaceholders(event.getPlayer(), joinText);
               Component joinAPIComponent = Component.text(joinText);
               event.joinMessage(joinAPIComponent);
           }else{
               Component joinComponent = Component.text("&8{ &a+ &8} &e- &a>&3"+event.getPlayer().getName());
               event.joinMessage(joinComponent);
           }
           if(player.hasPermission(Perms.VANISH_PERM_SELF.getPermission()) || player.hasPermission(Perms.VANISH_PERM_ALL.getPermission()) || player.hasPermission(Perms.ALL_PERMS.getPermission())){
           VanishHandler.getVanished().add(player);
           }
    }
}
