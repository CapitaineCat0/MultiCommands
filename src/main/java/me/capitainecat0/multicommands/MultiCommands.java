package me.capitainecat0.multicommands;

import java.util.logging.Logger;

import me.capitainecat0.multicommands.utils.*;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;
import org.bukkit.event.EventException;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import static me.capitainecat0.multicommands.utils.MessengerUtils.*;


public final class MultiCommands extends JavaPlugin {
    //TODO évenement qui régénère les arbres coupés
    //TODO modifier BlockRegen pour l'appliquer à une zone définie
    //
    // Lecture des variables
    private static final Logger log = Logger.getLogger("Minecraft");
    static CooldownManager cooldownManager;
    public static Economy econ = null;
    private static Permission perms = null;
    private static Chat chat = null;
    private BukkitAudiences adventure;
    private VaultHook vaultHook;
    private static MultiCommands instance;

    // Activation du plugin
    public void onEnable() {
        // Initialisation des variables et configurations
        instance = this;
        cooldownManager = new CooldownManager(this);
        adventure = BukkitAudiences.create(this);
        saveResourceAs("config.yml");
        saveResourceAs("lang/fr.properties");
        saveResourceAs("lang/en.properties");
        /**
         * Recherche de la configuration 'console-setup' dans config.yml
         * Si la valeur est 'true', lecture de la méthode ci-dessous,
         * qui consiste à énumérer les fonctionnalités de MultiCommands
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
                sendEventExceptionMessage(e, "Commands.init()");
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
        } else {
            // Lecture des fonctionalités, si la valeur
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
        } catch (Exception e) {
            sendEventExceptionMessage(e, "Crafts.init()");
        }

    }
    // Désactivation du plugin
    public void onDisable() {
        // Méthodes lues lors de la désactivation de MultiCommands
        log.info(String.format("[%s] Version %s Disabled", this.getDescription().getName(), this.getDescription().getVersion()));
        if (this.adventure != null) {
            this.adventure.close();
            this.adventure = null;
        }

    }
    // Initialisation de l'économie (Vault)
    public boolean setupEconomy() {
        if (this.getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        } else {
            RegisteredServiceProvider<Economy> rsp = this.getServer().getServicesManager().getRegistration(Economy.class);
            if (rsp == null) {
                return false;
            } else {
                econ = (Economy)rsp.getProvider();
                return true;
            }
        }
    }

    // Initialisation du chat (Vault)
    private void setupChat() {
        RegisteredServiceProvider<Chat> rsp = this.getServer().getServicesManager().getRegistration(Chat.class);
        if(rsp == null) {
            throw new IllegalStateException("Chat service not found");
        }
        chat = (Chat)rsp.getProvider();
    }

    // Initialisation des permissions (Vault)
    private void setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = this.getServer().getServicesManager().getRegistration(Permission.class);
        if(rsp == null) {
            throw new IllegalStateException("Permission service not found");
        }
        perms = (Permission)rsp.getProvider();
    }
    // Obtenir l'instance du cooldown
    public static CooldownManager getCooldownManager() {
        return cooldownManager;
    }
    // Obtenir l'instance du plugin
    public static MultiCommands getInstance() {
        return instance;
    }
    // Obtenir l'instance de l'API Adventure
    public BukkitAudiences adventure() {
        if(adventure == null) {
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
    /*public void registerCommand(CommandExecutor executor, String command, TabCompleter tabCompleter, TabExecutor tabExecutor) {
        PluginCommand cmd = this.getCommand(command);
        if (cmd != null) {
            cmd.setExecutor(executor);
            cmd.setTabCompleter(tabCompleter);
            cmd.setTabExecutor(tabExecutor);
        }
    }*/

    /**
     *
     * @param listener Event called
     */
    public static void registerEvent(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, MultiCommands.getInstance());
    }
}