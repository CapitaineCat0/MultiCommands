package me.capitainecat0.multicommands;

import me.capitainecat0.multicommands.utils.Commands;
import me.capitainecat0.multicommands.utils.CustomCraft;
import me.capitainecat0.multicommands.utils.Events;
import me.capitainecat0.multicommands.utils.PluginCore;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.io.File;
import java.io.InputStream;

import static me.capitainecat0.multicommands.utils.MessengerUtils.sendConsoleMessage;

public final class MultiCommands extends PluginCore<MultiCommands> {

    private Economy vaultEconomy;
    private Permission vaultPermission;
    private Chat vaultChat;
    private static MultiCommands instance;
    private BukkitAudiences adventure;

    @Override
    protected boolean start(MultiCommands main) {
        if(!setupEconomy()){
            sendConsoleMessage("&cEconomy system doesn't hook to VaultAPI! Hooking to local Economy.");
        }else{
            sendConsoleMessage("&aVaultAPI found! Hooking into it...");
        }
        this.adventure = BukkitAudiences.create(this);
        saveResourceAs("config.yml");
        instance = main;
        sendConsoleMessage("&a---------------+ &6MultiCommands v"+getDescription().getVersion()+"&a +---------------- ");
        sendConsoleMessage(" ");
        sendConsoleMessage("&5Enabling commands:");
        sendConsoleMessage(" ");
        Commands.init();
        sendConsoleMessage(" ");
        sendConsoleMessage(" ");
        sendConsoleMessage("&5Enabling events:");
        sendConsoleMessage(" ");
        Events.init();
        sendConsoleMessage(" ");
        sendConsoleMessage("&a--------------------------------------------------------- ");
        getServer().addRecipe(CustomCraft.saddle());
        return true;
    }

    @Override
    protected void stop() {
        if(this.adventure != null) {
            this.adventure.close();
            this.adventure = null;
        }
    }

    public static MultiCommands getInstance(){
        return instance;
    }
    public void saveResourceAs(String inPath) {
        if (inPath != null && !inPath.isEmpty()) {
            InputStream in = this.getResource(inPath);
            if (in == null) {
                throw new IllegalArgumentException("Le fichier " + inPath + " est introuvable dans le dossier du plugin");
            } else {
                if (!this.getDataFolder().exists() && !this.getDataFolder().mkdir()) {
                    sendConsoleMessage("&cImpossible de creer le dossier !");
                }

                File inFile = new File(this.getDataFolder(), inPath);
                if (!inFile.exists()) {
                    sendConsoleMessage("&cLe fichier &e" + inFile.getName() + "&c est introuvable, creation en cours ...");
                    this.saveResource(inPath, false);
                    if (!inFile.exists()) {
                        sendConsoleMessage("&cImpossible de copier le fichier !");
                    } else {
                        sendConsoleMessage("&aLe fichier &e" + inFile.getName() + "&a a ete cree !");
                    }
                }

            }
        } else {
            throw new IllegalArgumentException("Le dossier ne doit pas etre vide/null !");
        }
    }

    public BukkitAudiences adventure() {
        if(this.adventure == null) {
            throw new IllegalStateException("Tried to access Adventure when the plugin was disabled!");
        }
        return this.adventure;
    }

    @Override
    public void registerEvent(Listener listener) {
        super.registerEvent(listener);
    }
    public boolean setupEconomy() {
        if (Bukkit.getPluginManager().getPlugin("Vault") == null) {
            return false;
        }

        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        vaultEconomy = rsp.getProvider();
        return vaultEconomy != null;
    }

    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        vaultChat = rsp.getProvider();
        return vaultChat != null;
    }

    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        vaultPermission = rsp.getProvider();
        return vaultPermission != null;
    }

    public Economy getEconomy() {
        return vaultEconomy;
    }

    public Permission getPermissions() {
        return vaultPermission;
    }

    public Chat getChat() {
        return vaultChat;
    }
}
