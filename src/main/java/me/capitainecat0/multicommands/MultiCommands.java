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
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class MultiCommands extends JavaPlugin {
    private static final Logger log = Logger.getLogger("Minecraft");
    static CooldownManager cooldownManager;
    public static Economy econ = null;
    private static Permission perms = null;
    private static Chat chat = null;

    private BukkitAudiences adventure;
    private VaultHook vaultHook;
    private static MultiCommands instance;


    public void onEnable() {
        instance = this;
        cooldownManager = new CooldownManager(this);
        adventure = BukkitAudiences.create(this);
        MessengerUtils.saveResourceAs("config.yml");
        MessengerUtils.saveResourceAs("lang/fr.properties");
        MessengerUtils.saveResourceAs("lang/en.properties");
        if (this.getConfig().getBoolean("console-setup")) {
            MessengerUtils.sendConsoleMessage("&a---------------+ &6MultiCommands v" + this.getDescription().getVersion() + "&a +---------------- ");
            MessengerUtils.sendConsoleMessage(" ");
            MessengerUtils.sendConsoleMessage("&5Enabling commands:");
            MessengerUtils.sendConsoleMessage(" ");

            try {
                Commands.init();
            } catch (Error error) {
                MessengerUtils.sendConsoleMessage(error.getMessage());
            }

            MessengerUtils.sendConsoleMessage(" ");
            MessengerUtils.sendConsoleMessage(" ");
            MessengerUtils.sendConsoleMessage("&5Enabling events:");
            MessengerUtils.sendConsoleMessage(" ");

            try {
                Events.init();
            } catch (Error error) {
                MessengerUtils.sendConsoleMessage(error.getMessage());
            }

            MessengerUtils.sendConsoleMessage(" ");
            MessengerUtils.sendConsoleMessage("&a--------------------------------------------------------- ");
        } else {
            try {
                Commands.init();
            } catch (Error error) {
                MessengerUtils.sendConsoleMessage(error.getMessage());
            }

            try {
                Events.init();
            } catch (Error error) {
                MessengerUtils.sendConsoleMessage(error.getMessage());
            }
        }

        try {
            this.getServer().addRecipe(CustomCraft.saddle());
        } catch (Error error) {
            MessengerUtils.sendConsoleMessage(error.getMessage());
        }

    }

    public void onDisable() {
        log.info(String.format("[%s] Version %s Disabled", this.getDescription().getName(), this.getDescription().getVersion()));
        if (this.adventure != null) {
            this.adventure.close();
            this.adventure = null;
        }

    }

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

    private void setupChat() {
        RegisteredServiceProvider<Chat> rsp = this.getServer().getServicesManager().getRegistration(Chat.class);

        assert rsp != null;

        chat = (Chat)rsp.getProvider();
    }

    private void setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = this.getServer().getServicesManager().getRegistration(Permission.class);

        assert rsp != null;

        perms = (Permission)rsp.getProvider();
    }

    public static CooldownManager getCooldownManager() {
        return cooldownManager;
    }
    public static MultiCommands getInstance() {
        return instance;
    }

    public BukkitAudiences adventure() {
        if(adventure == null) {
            throw new IllegalStateException("Tried to access Adventure when the plugin was disabled!");
        }
        return adventure;
    }

    public void registerCommand(CommandExecutor executor, String codeName) {
        PluginCommand command = this.getCommand(codeName);
        if (command != null) {
            command.setExecutor(executor);
        }
    }
    public void registerEvent(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, this);
    }

}
