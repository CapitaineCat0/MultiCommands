package me.capitainecat0.multicommands.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Recipe;
import org.bukkit.plugin.SimplePluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class PluginCore<T extends PluginCore<?>> extends JavaPlugin {
    private static PluginCore<?> INSTANCE;
    private boolean DEBUG_ENABLED = false;
    private static String NAME;
    private static List<Recipe> RECIPES = null;

    public PluginCore() {
    }

    private static void setName(String name) {
        NAME = name;
    }

    protected abstract boolean start(T var1);

    protected abstract void stop();

    protected List<Listener> getPluginListeners() {
        return new ArrayList();
    }

    public void onEnable() {
        INSTANCE = this;
        boolean shouldContinue = this.start((T) this);
        if (!shouldContinue) {
            this.getServer().getPluginManager().disablePlugin(this);
        }

        NAME = this.getName();
        Iterator var2 = this.getPluginListeners().iterator();

        while(var2.hasNext()) {
            Listener listener = (Listener)var2.next();
            this.registerEvent(listener);
        }

    }

    public void registerCommand(CommandExecutor executor, String codeName) {
        PluginCommand command = this.getCommand(codeName);
        if (command != null) {
            command.setExecutor(executor);
        }
    }

    private static SimpleCommandMap injectCommandMap() {
        try {
            Field commandMapField = SimplePluginManager.class.getDeclaredField("commandMap");
            commandMapField.setAccessible(true);
            SimpleCommandMap map = (SimpleCommandMap)commandMapField.get(Bukkit.getPluginManager());
            return map;
        } catch (Exception var2) {
            return null;
        }
    }

    protected boolean injectCommand(String name, final CommandExecutor executor) {
        SimpleCommandMap map = injectCommandMap();
        if (map == null) {
            return false;
        } else {
            map.register(name, new Command(name) {
                public boolean execute(CommandSender commandSender, String s, String[] strings) {
                    return executor.onCommand(commandSender, this, s, strings);
                }
            });
            return true;
        }
    }

    protected void registerEvent(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, this);
    }

    public void onDisable() {
        this.stop();
        INSTANCE = null;
    }

    public static PluginCore<?> instance() {
        return INSTANCE;
    }

    public static String colored(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    private static void send(String message, String first, String second, String third) {
        instance().getServer().getConsoleSender().sendMessage(colored(first + "[" + second + NAME + first + "] " + third + message));
    }

    public static void log(String message) {
        send(message, "&9", "&3", "&b");
    }

    public static void info(String message) {
        send(message, "&9", "&3", "&b");
    }

    public static void warn(String message) {
        send(message, "&e", "&6", "&e");
    }

    public static void error(String message) {
        send(message, "&c", "&4", "&c");
    }

    public static void success(String message) {
        send(message, "&a", "&2", "&a");
    }

    protected void setDebugEnabled(boolean enabled) {
        this.DEBUG_ENABLED = enabled;
    }

    protected void enableDebug() {
        this.setDebugEnabled(true);
    }

    protected void disableDebug() {
        this.setDebugEnabled(false);
    }

    public static void debug(String message) {
        if (instance().DEBUG_ENABLED) {
            send(message, "&8", "&7", "&f");
        }

    }

    public static void refreshRegisteredRecipes() {
        RECIPES = new ArrayList();
        Iterator<Recipe> iterator = Bukkit.recipeIterator();

        while(iterator.hasNext()) {
            RECIPES.add((Recipe)iterator.next());
        }

    }

    public static List<Recipe> getRegisteredRecipes() {
        if (RECIPES == null) {
            refreshRegisteredRecipes();
        }

        return RECIPES;
    }

    public static List<Recipe> getRegisteredRecipes(Predicate<Recipe> verificator) {
        if (RECIPES == null) {
            refreshRegisteredRecipes();
        }

        return (List)RECIPES.stream().filter(verificator).collect(Collectors.toList());
    }
}