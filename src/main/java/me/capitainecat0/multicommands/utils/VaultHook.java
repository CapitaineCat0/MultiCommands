package me.capitainecat0.multicommands.utils;

import me.capitainecat0.multicommands.MultiCommands;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.ServicePriority;

import static me.capitainecat0.multicommands.MultiCommands.econ;
import static me.capitainecat0.multicommands.MultiCommands.getInstance;
import static me.capitainecat0.multicommands.utils.MessengerUtils.sendConsoleMessage;

public class VaultHook {

    public void hook(){
        if(getInstance().setupEconomy()){
            Bukkit.getServicesManager().register(Economy.class, econ, MultiCommands.getInstance(), ServicePriority.Normal);
            sendConsoleMessage("&aVaultAPI found! Hooking on it...");
        }
    }

    public void unHook(){
        Bukkit.getServicesManager().unregister(Economy.class, econ);
        sendConsoleMessage("&cEconomy system disabled.");
    }
}
