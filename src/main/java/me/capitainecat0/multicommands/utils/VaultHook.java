package me.capitainecat0.multicommands.utils;

import me.capitainecat0.multicommands.MultiCommands;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.ServicePriority;

import static me.capitainecat0.multicommands.MultiCommands.econ;
import static me.capitainecat0.multicommands.MultiCommands.getInstance;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;

public class VaultHook {

    /**
     * The hook function is used to hook into the VaultAPI.
     * It checks if the plugin has been found, and then hooks onto it.

     *
     *
     * @return A boolean, so we can check if it was successful
     */
    public void hook(){
        if(getInstance().setupEconomy()){
            Bukkit.getServicesManager().register(Economy.class, econ, MultiCommands.getInstance(), ServicePriority.Normal);
            sendConsoleMessage("&aVaultAPI found! Hooking on it...");
        }
    }

    /**
     * The unHook function is used to unregister the Economy service from Bukkit.
     * This function should be called when the plugin is disabled, or if you want to disable
     * Vault's economy system for some reason.
     */
    public void unHook(){
        Bukkit.getServicesManager().unregister(Economy.class, econ);
        sendConsoleMessage("&cEconomy system disabled.");
    }
}
