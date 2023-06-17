package me.capitainecat0.multicommands.events;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.data.BalanceData;
import me.capitainecat0.multicommands.data.BannedData;
import me.capitainecat0.multicommands.data.FreezeData;
import me.capitainecat0.multicommands.utils.Perms;
import me.capitainecat0.multicommands.utils.VanishHandler;
import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Objects;
import java.util.function.Consumer;

import static me.capitainecat0.multicommands.MultiCommands.getInstance;
import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;
public class Join implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        hideActiveBossBar();
       Player player = event.getPlayer();
       /*if(MultiCommands.getInstance().setupEconomy()){
           Economy economy = MultiCommands.getImplementer();
           assert economy != null;
           if(!economy.hasAccount(player)){
               economy.createPlayerAccount(player);
           }
        }*/
        /*if(MultiCommands.setupEconomy()){
            if(!econ.hasAccount(player)){
                econ.createPlayerAccount(player);
            }
        }*/
      // new PlayerData(player);
       new FreezeData(player);
       new BalanceData(player);
       new BannedData(player);
       /*if(BannedData.isBanned()){
           Component kick = Component.text(colored(BAN_PREFIX.getMessage().replace("%reason%", new BannedData(player).getReason().replace(player.getName(), " "))));
           player.kick(kick);
       }*/
           if(Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")){
               String joinText = "&8{ &a+ &8} &e- &a>&f%luckperms_prefix% &3%player_name%";
               joinText = PlaceholderAPI.setPlaceholders(event.getPlayer(), joinText);
               //Component join = Component.text(joinText);
               event.setJoinMessage(joinText);
           }else {
               //Component join = Component.text(ONJOIN.getMessage().replace("{0}", player.getName()));
               event.setJoinMessage(colored(ONJOIN.getMessage().replace("{0}", player.getName())));
               if (player.hasPermission(Perms.VANISH_PERM_SELF.getPermission()) || player.hasPermission(Perms.VANISH_PERM_ALL.getPermission()) || player.hasPermission(Perms.ALL_PERMS.getPermission())) {
                   VanishHandler.getVanished().add(player);
               }
           }
           sendTablist(player, Component.text(colored(Objects.requireNonNull(getInstance().getConfig().getString("tablist.header")))), Component.text(colored(Objects.requireNonNull(getInstance().getConfig().getString("tablist.footer")))));
    }
}
