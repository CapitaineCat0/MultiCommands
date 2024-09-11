package me.capitainecat0.multicommands;

import me.capitainecat0.multicommands.commands.chatchannels.ChatChannels;
import me.capitainecat0.multicommands.utils.Commands;
import me.capitainecat0.multicommands.utils.CustomCraft;
import me.capitainecat0.multicommands.utils.Events;
import me.capitainecat0.multicommands.utils.permissions.BukkitPermissionManager;
import me.capitainecat0.multicommands.utils.permissions.PermissionManager;
import me.capitainecat0.multicommands.utils.permissions.VaultPermissionManager;
import me.capitainecat0.multicommands.utils.storage.FreezeData;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

import static me.capitainecat0.multicommands.utils.MessengerUtils.*;


public final class MultiCommands extends JavaPlugin {
    //TODO évenement qui régénère les arbres coupés
    //TODO modifier BlockRegen pour l'appliquer à une zone définie
    //TODO faire un systeme de chat comme KingdomsX
    //TODO utiliser Vault complètement
    //TODO faire une commande de coffre personnalisés (comme le /enderchest)
    //TODO faire une commande de craft personnalisés (comme le /craft)
    //TODO faire une commande de chat personnalisés (comme le /chatchannels)

    // Lecture des variables
    private static final Logger log = Logger.getLogger("Minecraft");
    private BukkitAudiences adventure;
    private static MultiCommands instance;
    private static Economy economy = null;
    private static Permission perms = null;
    private static PermissionManager permissionManager;
    private static Chat chat = null;
    private static final FreezeData freezeData = new FreezeData();

    //Activation du plugin
    public void onEnable() {
        // Initialisation des variables et configurations
        instance = this;
        adventure = BukkitAudiences.create(this);
        if (isVaultInstalled()) {
            permissionManager = new VaultPermissionManager();
            setupEconomy();
            setupChat();
            setupPermissions();
        } else {
            permissionManager = new BukkitPermissionManager();
        }
        saveResourceAs("config.yml");
        saveResourceAs("lang/fr.properties");
        saveResourceAs("lang/en.properties");
        /*
         * Recherche de la configuration 'console-setup' dans le fichier config.yml
         * Si la valeur est 'true', lecture de la méthode ci-dessous,
         * qui consiste à énumérer les fonctionnalités de MultiCommands
         * dans la console.
         **/
        if (this.getConfig().getBoolean("console-setup")) {
            // Envoi des informations au chargement du plugin
            sendConsoleMessage("&a---------------+ &6MultiCommands v" + this.getDescription().getVersion() + "&a +---------------- ");
            sendConsoleMessage(" ");
            sendConsoleMessage("&5Enabling commands:");
            sendConsoleMessage(" ");
            // Chargement des commandes en les contrôlant
            try {
                Commands.init();
            } catch (Exception e) {
                sendCommandExceptionMessage(e, "Commands.init()");
            }

            sendConsoleMessage(" ");
            sendConsoleMessage(" ");
            sendConsoleMessage("&5Enabling events:");
            sendConsoleMessage(" ");
            // Chargement des évenements en les contrôlant
            try {
                Events.init();
            } catch (Exception e) {
                sendEventExceptionMessage(e, "Events.init()");
            }

            sendConsoleMessage(" ");
            sendConsoleMessage("&a--------------------------------------------------------- ");
            loadConfig("/lang/"+getConfig().getString("lang")+".properties");
        } else {
            // Lecture des fonctionnalités, si la valeur
            // 'console-setup' de config.yml, est false
            try {
                // Chargement des commandes en les contrôlant
                Commands.init();
            } catch (Exception e) {
                sendEventExceptionMessage(e, "Commands.init()");
            }

            try {
                // Chargement des évenements en les contrôlant
                Events.init();
            } catch (Exception e) {
                sendEventExceptionMessage(e, "Events.init()");
            }
        }

        try {
            // Chargement des craft custom en les contrôlant
            this.getServer().addRecipe(CustomCraft.saddle());
            this.getServer().addRecipe(CustomCraft.nametag());
            this.getServer().addRecipe(CustomCraft.chainmailHelmet());
            this.getServer().addRecipe(CustomCraft.uncraftChainmailHelmet());
            this.getServer().addRecipe(CustomCraft.chainmailChestplate());
            this.getServer().addRecipe(CustomCraft.uncraftChainmailChestplate());
            this.getServer().addRecipe(CustomCraft.chainmailLeggings());
            this.getServer().addRecipe(CustomCraft.uncraftChainmailLeggings());
            this.getServer().addRecipe(CustomCraft.chainmailBoots());
            this.getServer().addRecipe(CustomCraft.uncraftChainmailBoots());
            this.getServer().addRecipe(CustomCraft.enchantedApple());
        } catch (Exception e) {
            sendEventExceptionMessage(e, "Crafts.init()");
        }

    }

    // Désactivation du plugin
    public void onDisable() {
        getServer().getServicesManager().unregisterAll(this);
        // Méthodes lues lors de la désactivation de MultiCommands
        freezeData.clearFrozen();
        log.info(String.format("[%s] Version %s Disabled", this.getDescription().getName(), this.getDescription().getVersion()));
        if (this.adventure != null) {
            this.adventure.close();
            this.adventure = null;
        }

    }

    // Obtenir l'instance du plugin
    public static MultiCommands getInstance() {
        return instance;
    }

    // Obtenir l'instance de l'API Adventure
    public BukkitAudiences adventure() {
        if (adventure == null) {
            throw new IllegalStateException("Tried to access Adventure when the plugin was disabled!");
        }
        return adventure;
    }

    // Enregistrement de commande
    public void registerCommand(CommandExecutor executor, String command) {
        PluginCommand cmd = this.getCommand(command);
        if (cmd != null) {
            cmd.setExecutor(executor);
        }
    }

    // Enregistrement de commande avec une completion de tab
    public void registerCommand(CommandExecutor executor, String command, TabCompleter tabCompleter) {
        PluginCommand cmd = this.getCommand(command);
        if (cmd != null) {
            cmd.setExecutor(executor);
            cmd.setTabCompleter(tabCompleter);
        }
    }

    public static void registerEvent(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, MultiCommands.getInstance());
    }

    public boolean isVaultInstalled() {
        Plugin vault = Bukkit.getPluginManager().getPlugin("Vault");
        return vault != null && vault.isEnabled();
    }

    private void setupEconomy() {
        if (!isVaultInstalled()) {
            return;
        }
        RegisteredServiceProvider<Economy> rsp = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return;
        }
        economy = rsp.getProvider();
    }

    private void setupChat() {
        if (!isVaultInstalled()) {
            return;
        }
        RegisteredServiceProvider<Chat> rsp = Bukkit.getServer().getServicesManager().getRegistration(Chat.class);
        if (rsp == null) {
            return;
        }
        chat = rsp.getProvider();
    }

    private void setupPermissions() {
        if (!isVaultInstalled()) {
            return;
        }
        RegisteredServiceProvider<Permission> rsp = Bukkit.getServer().getServicesManager().getRegistration(Permission.class);
        if (rsp == null) {
            return;
        }
        perms = rsp.getProvider();
    }

    public static PermissionManager getPermissionManager() {
        return permissionManager;
    }

    public static Chat getChat() {
        return chat;
    }

    public void reload() {
        getServer().getServicesManager().unregisterAll(this);
        freezeData.clearFrozen();
        if (this.adventure != null) {
            this.adventure.close();
            this.adventure = null;
        }
        // Arrêtez tout ce que votre plugin doit arrêter
        // (exemples : listeners, tasks, connexions, etc.)

        // Puis rechargez les configurations :
        this.reloadConfig(); // Ceci est pour Bukkit#JavaPlugin, personnalisez si nécessaire
        reloadLang();
        log.info(String.format("[%s] Version %s Reloaded", this.getDescription().getName(), this.getDescription().getVersion()));
        // Recréez ensuite les services, listeners, tâches, connexions, etc.
        // Vous devrez faire cela en fonction de la manière dont vous avez conçu votre plugin.
        adventure = BukkitAudiences.create(this);
        if (isVaultInstalled()) {
            permissionManager = new VaultPermissionManager();
            setupEconomy();
            setupChat();
            setupPermissions();
        } else {
            permissionManager = new BukkitPermissionManager();
        }
        saveResourceAs("config.yml");
        saveResourceAs("lang/fr.properties");
        saveResourceAs("lang/en.properties");
        ChatChannels.updatePlayerList();

        /*
         * Recherche de la configuration 'console-setup' dans le fichier config.yml
         * Si la valeur est 'true', lecture de la méthode ci-dessous,
         * qui consiste à énumérer les fonctionnalités de MultiCommands
         * dans la console.
         **/
        if (this.getConfig().getBoolean("console-setup")) {
            // Envoi des informations au chargement du plugin
            sendConsoleMessage("&a---------------+ &6MultiCommands v" + this.getDescription().getVersion() + "&a +---------------- ");
            sendConsoleMessage(" ");
            sendConsoleMessage("&5Enabling commands:");
            sendConsoleMessage(" ");
            // Chargement des commandes en les contrôlant
            try {
                Commands.init();
            } catch (Exception e) {
                sendCommandExceptionMessage(e, "Commands.init()");
            }

            sendConsoleMessage(" ");
            sendConsoleMessage(" ");
            sendConsoleMessage("&5Enabling events:");
            sendConsoleMessage(" ");
            // Chargement des évenements en les contrôlant
            try {
                Events.init();
            } catch (Exception e) {
                sendEventExceptionMessage(e, "Events.init()");
            }

            sendConsoleMessage(" ");
            sendConsoleMessage("&a--------------------------------------------------------- ");
            loadConfig("/lang/"+getConfig().getString("lang")+".properties");
        } else {
            // Lecture des fonctionnalités, si la valeur
            // 'console-setup' de config.yml, est false
            try {
                // Chargement des commandes en les contrôlant
                Commands.init();
            } catch (Exception e) {
                sendEventExceptionMessage(e, "Commands.init()");
            }

            try {
                // Chargement des évenements en les contrôlant
                Events.init();
            } catch (Exception e) {
                sendEventExceptionMessage(e, "Events.init()");
            }
            loadConfig("/lang/"+getConfig().getString("lang")+".properties");
        }

        try {
            // Chargement des craft custom en les contrôlant
            this.getServer().addRecipe(CustomCraft.saddle());
            this.getServer().addRecipe(CustomCraft.nametag());
            this.getServer().addRecipe(CustomCraft.chainmailHelmet());
            this.getServer().addRecipe(CustomCraft.uncraftChainmailHelmet());
            this.getServer().addRecipe(CustomCraft.chainmailChestplate());
            this.getServer().addRecipe(CustomCraft.uncraftChainmailChestplate());
            this.getServer().addRecipe(CustomCraft.chainmailLeggings());
            this.getServer().addRecipe(CustomCraft.uncraftChainmailLeggings());
            this.getServer().addRecipe(CustomCraft.chainmailBoots());
            this.getServer().addRecipe(CustomCraft.uncraftChainmailBoots());
            this.getServer().addRecipe(CustomCraft.enchantedApple());
        } catch (Exception e) {
            sendEventExceptionMessage(e, "Crafts.init()");
        }
    }
}