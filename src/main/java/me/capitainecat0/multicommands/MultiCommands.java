package me.capitainecat0.multicommands;

import me.capitainecat0.multicommands.data.ConfigData;
import me.capitainecat0.multicommands.utils.*;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;

import java.io.File;
import java.io.InputStream;

import static me.capitainecat0.multicommands.utils.MessengerUtils.sendConsoleMessage;

public final class MultiCommands extends PluginCore<MultiCommands> {
    private static MultiCommands instance;
    private BukkitAudiences adventure;
    @Override
    protected boolean start(MultiCommands main) {

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
    private void saveResourceAs(String inPath) {
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


}
