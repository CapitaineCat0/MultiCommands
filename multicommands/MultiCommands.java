package me.capitainecat0.multicommands;

import io.github.theluca98.textapi.ActionBar;
import io.github.theluca98.textapi.Title;
import me.capitainecat0.multicommands.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.file.YamlConfigurationOptions;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public final class MultiCommands extends PluginCore<MultiCommands> {
    private static MultiCommands instance;
    public boolean HELP_BOOK_ENABLED = true;

    @Override
    protected boolean start(MultiCommands main) {
        saveResourceAs("config.yml");
        instance = main;
        Commands.init();
        Events.init();
        return true;
    }

    @Override
    protected void stop() {

    }

    public static MultiCommands getInstance(){
        return instance;
    }

    public void setHelpBook(boolean enabled) {
        HELP_BOOK_ENABLED = enabled;
    }

    public void getMsgSendConfig(CommandSender player, String command, String msg){
        if(this.getConfig().get("send-message-on").equals("CHAT")){
            player.sendMessage("§e"+command+"§e: "+msg);
        }else if(this.getConfig().get("send-message-on").equals("ACTIONBAR")){
            ActionBar actionBar = new ActionBar(msg);
            actionBar.send((Player) player);
        }else if(this.getConfig().get("send-message-on").equals("TITLE")){
            Title title = new Title("§3"+command, msg, 10, 80, 10);
            title.send((Player) player);
        }
    }
    private void saveResourceAs(String inPath) {
        if (inPath != null && !inPath.isEmpty()) {
            InputStream in = this.getResource(inPath);
            if (in == null) {
                throw new IllegalArgumentException("Le fichier " + inPath + " est introuvable dans le dossier du plugin");
            } else {
                if (!this.getDataFolder().exists() && !this.getDataFolder().mkdir()) {
                    this.getLogger().severe("Impossible de creer le dossier !");
                }

                File inFile = new File(this.getDataFolder(), inPath);
                if (!inFile.exists()) {
                    Bukkit.getConsoleSender().sendMessage("§cLe fichier " + inFile.getName() + " est introuvable, creation en cours ...");
                    this.saveResource(inPath, false);
                    if (!inFile.exists()) {
                        this.getLogger().severe("Impossible de copier le fichier !");
                    } else {
                        Bukkit.getConsoleSender().sendMessage("§aLe fichier " + inFile.getName() + " a ete cree !");
                    }
                }

            }
        } else {
            throw new IllegalArgumentException("Le dossier ne doit pas etre vide/null !");
        }
    }

    public void reloadConfiguration() {
        //YamlConfiguration.loadConfiguration(new File(getDataFolder(), "config.yml"));
        YamlConfiguration.loadConfiguration(new File("config.yml"));
    }
}
