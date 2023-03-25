package me.capitainecat0.multicommands;

import me.capitainecat0.multicommands.utils.*;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.InputStream;
import java.util.logging.Logger;

import static me.capitainecat0.multicommands.utils.MessengerUtils.sendConsoleMessage;

public final class MultiCommands extends JavaPlugin {

    private static final Logger log = Logger.getLogger("Minecraft");
    public static Economy econ = null;
    private static Permission perms = null;
    private static Chat chat = null;
    private static MultiCommands instance;
    private BukkitAudiences adventure;
    private VaultHook vaultHook;


    @Override
    public void onEnable(){
        /*if(!setupEconomy()){
            log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        setupPermissions();
        setupChat();*/
        //implementer = new EconomyImplementer();
        //vaultHook = new VaultHook();
        //vaultHook.hook();
        this.adventure = BukkitAudiences.create(this);
        saveResourceAs("config.yml");
        saveResourceAs("warps.yml");
        instance = this;
        if(getConfig().getBoolean("console-setup")) {
            sendConsoleMessage("&a---------------+ &6MultiCommands v" + getDescription().getVersion() + "&a +---------------- ");
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
        }else{
            Commands.init();
            Events.init();
        }
        getServer().addRecipe(CustomCraft.saddle());
    }

    @Override
    public void onDisable(){
        log.info(String.format("[%s] Version %s Disabled", getDescription().getName(), getDescription().getVersion()));
        if(this.adventure != null) {
            this.adventure.close();
            this.adventure = null;
        }
        /*if(Bukkit.getServicesManager().getRegistration(Economy.class)!= null){
            vaultHook.unHook();
        }else{
            return;
        }*/
    }


    public static MultiCommands getInstance(){
        return instance;
    }

    public void registerCommand(CommandExecutor executor, String codeName) {
        PluginCommand command = this.getCommand(codeName);
        if (command != null) {
            command.setExecutor(executor);
        }
    }

    public static String colored(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public void registerEvent(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, this);
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

    public boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return true;
    }

    private void setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        assert rsp != null;
        chat = rsp.getProvider();
    }

    private void setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        assert rsp != null;
        perms = rsp.getProvider();
    }
}
