package me.capitainecat0.multicommands;

import me.capitainecat0.multicommands.utils.Commands;
import me.capitainecat0.multicommands.utils.Events;
import me.capitainecat0.multicommands.utils.PluginCore;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.InputStream;

public final class MultiCommands extends PluginCore<MultiCommands> {
    private static MultiCommands instance;
    public boolean HELP_BOOK_ENABLED = true;
    public boolean DEBUG_ENABLED = false;

    @Override
    protected boolean start(MultiCommands main) {
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

    public void setDebug(boolean enabled){ DEBUG_ENABLED = enabled;}

    public void saveResourceAs(String inPath) {
        if (inPath != null && !inPath.isEmpty()) {
            InputStream in = this.getResource(inPath);
            if (in == null) {
                throw new IllegalArgumentException("Le fichier " + inPath + " est introuvable dans le dossier du plugin");
            } else {
                if (!this.getDataFolder().exists() && !this.getDataFolder().mkdir()) {
                    this.getLogger().severe("Impossible de créer le dossier !");
                }

                File inFile = new File(this.getDataFolder(), inPath);
                if (!inFile.exists()) {
                    Bukkit.getConsoleSender().sendMessage("§cLe fichier " + inFile.getName() + " est introuvable, création en cours ...");
                    this.saveResource(inPath, false);
                    if (!inFile.exists()) {
                        this.getLogger().severe("Impossible de copier le fichier !");
                    } else {
                        Bukkit.getConsoleSender().sendMessage("§aLe fichier " + inFile.getName() + " à été créé !");
                    }
                }

            }
        } else {
            throw new IllegalArgumentException("Le dossier ne doit pas être vide/null !");
        }
    }
}
