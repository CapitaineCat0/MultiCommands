package me.capitainecat0.multicommands.utils;

import me.capitainecat0.multicommands.MultiCommands;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.ServicePriority;

import static me.capitainecat0.multicommands.utils.MessengerUtils.sendConsoleMessage;

public class VaultHook {

    Economy economy = MultiCommands.getImplementer();
    public void hook(){
        Bukkit.getServicesManager().register(Economy.class, economy, MultiCommands.getInstance(), ServicePriority.Normal);
        sendConsoleMessage("&aVaultAPI found! Hooking into it...");
    }

    public void unHook(){
        Bukkit.getServicesManager().unregister(Economy.class, economy);
        sendConsoleMessage("&cEconomy system doesn't hook to VaultAPI! Hooking to local Economy.");
    }
}
