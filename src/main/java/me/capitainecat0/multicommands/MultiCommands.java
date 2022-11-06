package me.capitainecat0.multicommands;

import me.capitainecat0.multicommands.utils.Commands;
import me.capitainecat0.multicommands.utils.Events;
import me.capitainecat0.multicommands.utils.PluginCore;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.event.Listener;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

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

    private static Properties language;
    public String lang(String key) {
        this.checkLangFiles();
        return language.getProperty(key);
    }

    public void reloadLang() {
        language = null;
        this.checkLangFiles();
    }

    private void checkLangFiles() {
        final File langFile = new File(this.getDataFolder(), "lang.properties");
        langFile.getParentFile().mkdirs();

        if (!langFile.exists())
            this.saveResourceAs("lang.properties");

        if (language == null) {
            final Properties defaultLang = new Properties();
            try (final InputStream is = this.getResource("lang.properties")) {
                defaultLang.load(is);
            } catch (final Exception e) {
                e.printStackTrace();
            }

            language = new Properties(defaultLang);
            try (final InputStream is = new FileInputStream(langFile)) {
                language.load(is);
            } catch (final Exception e) {
                e.printStackTrace();
            }
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

    @Override
    public void registerEvent(Listener listener) {
        super.registerEvent(listener);
    }
}
