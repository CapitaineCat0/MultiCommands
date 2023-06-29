package me.capitainecat0.multicommands.utils;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;

import static me.capitainecat0.multicommands.MultiCommands.econ;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;

public class VaultHook {

    public void hook(){
        /*if(getInstance().setupEconomy()){
            Bukkit.getServicesManager().register(Economy.class, econ, MultiCommands.getInstance(), ServicePriority.Normal);
            sendConsoleMessage("&aVaultAPI found! Hooking on it...");
        }*/
    }

    public void unHook(){
        Bukkit.getServicesManager().unregister(Economy.class, econ);
        sendConsoleMessage("&cEconomy system disabled.");
    }
}
