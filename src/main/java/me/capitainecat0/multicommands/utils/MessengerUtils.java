package me.capitainecat0.multicommands.utils;

import me.capitainecat0.multicommands.MultiCommands;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.inventory.Book;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.sound.SoundStop;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.time.Duration;
import java.util.Collection;
import java.util.Objects;
import java.util.Properties;

public class MessengerUtils {

    private static BossBar finalBossbar;
    private static Properties language;
    public static String lang(String key) {
        checkLangFiles();
        return language.getProperty(MultiCommands.colored(key));
    }
    public static void reloadLang() {
        language = null;
        checkLangFiles();
    }
    private static void checkLangFiles() {
        final File langFile = new File(MultiCommands.getInstance().getDataFolder(), "lang.properties");
        langFile.getParentFile().mkdirs();

        if (!langFile.exists())
            MultiCommands.getInstance().saveResourceAs("lang.properties");

        if (language == null) {
            final Properties defaultLang = new Properties();
            try (final InputStream is = MultiCommands.getInstance().getResource("lang.properties")) {
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
    public static void getMsgSendConfig(CommandSender sender, String commandName, String message){
        if(MultiCommands.getInstance().getConfig().get("send-message-on") != null){
            if(Objects.equals(MultiCommands.getInstance().getConfig().get("send-message-on"), "CHAT") || Objects.equals(MultiCommands.getInstance().getConfig().get("send-message-on"), "chat")){
                sendMessage(sender, commandName, message);
            }else if(Objects.equals(MultiCommands.getInstance().getConfig().get("send-message-on"), "ACTIONBAR") || Objects.equals(MultiCommands.getInstance().getConfig().get("send-message-on"), "actionbar")){
                sendActionBar(sender, commandName, message);
            }else if(Objects.equals(MultiCommands.getInstance().getConfig().get("send-message-on"), "TITLE") || Objects.equals(MultiCommands.getInstance().getConfig().get("send-message-on"), "title")){
                sendTitle(sender, commandName, message, Duration.ofSeconds(1),Duration.ofSeconds(3),Duration.ofSeconds(1));
            }else if(Objects.equals(MultiCommands.getInstance().getConfig().get("send-message-on"), "BOSSBAR") || Objects.equals(MultiCommands.getInstance().getConfig().get("send-message-on"), "bossbar")){
                //Documentation: http://localhost:63342/MultiCommands/adventure-api-4.11.0-javadoc.jar/net/kyori/adventure/bossbar/package-summary.html?_ijt=ovsm8m1392pvrl2p085r2ceba8&_ij_reload=RELOAD_ON_SAVE
                //progress 0 bare à 0%
                //progress 0.5 bare à 50%
                //progress 1 bare à 100%
                sendBossBar(sender, 1, BossBar.Color.GREEN, BossBar.Overlay.NOTCHED_20, MultiCommands.colored(message));
            }
        }else{
            sendMessage(sender, commandName, message);
        }

    }

    public static void hideActiveBossBar() {
        MultiCommands.getInstance().adventure().players().hideBossBar(finalBossbar);
        finalBossbar = null;
    }
    public static void sendConsoleMessage(String message){
        Bukkit.getConsoleSender().sendMessage(MultiCommands.colored(message));
    }

    public static void sendBroadcastMessage(String message){
        Bukkit.getServer().broadcastMessage(MultiCommands.colored(message));
    }

    public static void sendMessage(String message){
        final TextComponent component = Component.text(MultiCommands.colored(message));
        MultiCommands.getInstance().adventure().players().sendMessage(component);
    }
    public static void sendMessage(CommandSender sender, String message){
        final TextComponent component = Component.text(MultiCommands.colored(message));
        MultiCommands.getInstance().adventure().sender(sender).sendMessage(component);
    }

    private static void sendMessage(CommandSender sender, String commandName, String message){
        final TextComponent component = Component.text(MultiCommands.colored("&3"+commandName+": "+message));
        MultiCommands.getInstance().adventure().sender(sender).sendMessage(component);
    }
    public static void openBook(CommandSender sender, String title, String author, Collection<Component> message) {
        Component bookTitle = Component.text(title);
        Component bookAuthor = Component.text(author);
        Book book = Book.book(bookTitle, bookAuthor, message);
        MultiCommands.getInstance().adventure().sender(sender).openBook(book);
    }
    public static void playSound(CommandSender sender, String sound, float volume, float pitch){
        try{
            Sound musicDisc = Sound.sound(Key.key(sound), Sound.Source.MUSIC, volume, pitch);
            MultiCommands.getInstance().adventure().sender(sender).playSound(musicDisc);
        }catch(Error error){
            sendConsoleMessage("&cL'erreur &e"+error+" &cest survenue l'ors de le lecture du son!");
        }
    }
    public static void playSound(CommandSender sender, org.bukkit.Sound sound, float volume, float pitch){
        try{
            Player player = (Player) sender;
            player.getWorld().playSound(player.getLocation(),sound,volume, pitch);
        }catch(Error error){
            sendConsoleMessage("&cL'erreur &e"+error+" &cest survenue l'ors de le lecture du son!");
        }
    }
    public static boolean soundEnabled(){
        if(MultiCommands.getInstance().getConfig().get("enable-command-sounds") != null){
            if(MultiCommands.getInstance().getConfig().getBoolean("enable-command-sounds")){
                return true;
            }else if(!MultiCommands.getInstance().getConfig().getBoolean("enable-command-sounds")){
                return false;
            }
        }
        return false;
    }
    public static boolean soundFeedHealEnabled(){
        if(MultiCommands.getInstance().getConfig().get("feed-heal-sounds") != null){
            if(MultiCommands.getInstance().getConfig().getBoolean("feed-heal-sounds")){
                return true;
            }else if(!MultiCommands.getInstance().getConfig().getBoolean("feed-heal-sounds")){
                return false;
            }
        }
        return false;
    }
    public static void stopSound(CommandSender sender){
        MultiCommands.getInstance().adventure().sender(sender).stopSound(SoundStop.all());
    }
    private static void sendTitle(CommandSender sender, String commandName, String message, Duration fadeIn, Duration stay, Duration fadeOut){
        final Title.Times times = new Title.Times() {
            @Override
            public @NotNull Duration fadeIn() {
                return fadeIn;
            }

            @Override
            public @NotNull Duration stay() {
                return stay;
            }

            @Override
            public @NotNull Duration fadeOut() {
                return fadeOut;
            }
        };
        final Component mainTitle = Component.text("§3"+commandName);
        final Component subtitle = Component.text(MultiCommands.colored(message));
        final Title title = Title.title(mainTitle, subtitle, times);
        MultiCommands.getInstance().adventure().sender(sender).showTitle(title);
    }

    public static void sendTitle(String title, String message, Duration fadeIn, Duration stay, Duration fadeOut) {
        final Title.Times times = new Title.Times() {
            @Override
            public @NotNull Duration fadeIn() {
                return fadeIn;
            }

            @Override
            public @NotNull Duration stay() {
                return stay;
            }

            @Override
            public @NotNull Duration fadeOut() {
                return fadeOut;
            }
        };
        final Component mainTitle = Component.text(MultiCommands.colored(title));
        final Component subtitle = Component.text(MultiCommands.colored(message));
        final Title finalTitle = Title.title(mainTitle, subtitle, times);
        MultiCommands.getInstance().adventure().players().showTitle(finalTitle);
    }

    public static void sendTitle(Player player, String title, String message, Duration fadeIn, Duration stay, Duration fadeOut) {
        final Title.Times times = new Title.Times() {
            @Override
            public @NotNull Duration fadeIn() {
                return fadeIn;
            }

            @Override
            public @NotNull Duration stay() {
                return stay;
            }

            @Override
            public @NotNull Duration fadeOut() {
                return fadeOut;
            }
        };
        final Component mainTitle = Component.text(MultiCommands.colored(title));
        final Component subtitle = Component.text(MultiCommands.colored(message));
        final Title finalTitle = Title.title(mainTitle, subtitle, times);
        MultiCommands.getInstance().adventure().player(player).showTitle(finalTitle);
    }
    private static void sendBossBar(CommandSender sender, float progress, BossBar.Color color, BossBar.Overlay overlay, String message) {
        final Component name = Component.text(MultiCommands.colored(message));
        finalBossbar = BossBar.bossBar(name, progress, color, overlay);
        MultiCommands.getInstance().adventure().sender(sender).showBossBar(finalBossbar);
    }

    public static void sendBossBar(float progress, BossBar.Color color, BossBar.Overlay overlay, String message) {
        final Component name = Component.text(MultiCommands.colored(message));
        finalBossbar = BossBar.bossBar(name, progress, color, overlay);
        MultiCommands.getInstance().adventure().players().showBossBar(finalBossbar);
    }

    public static void sendBossBar(Player player, float progress, BossBar.Color color, BossBar.Overlay overlay, String message) {
        final Component name = Component.text(MultiCommands.colored(message));
        finalBossbar = BossBar.bossBar(name, progress, color, overlay);
        MultiCommands.getInstance().adventure().player(player).showBossBar(finalBossbar);
    }
    private static void sendActionBar(CommandSender sender, String commandName, String message){
        final TextComponent component = Component.text(MultiCommands.colored("&3"+commandName+": "+message));
        MultiCommands.getInstance().adventure().sender(sender).sendActionBar(component);
    }

    public static void sendActionBar(String message){
        final TextComponent component = Component.text(MultiCommands.colored(message));
        MultiCommands.getInstance().adventure().players().sendActionBar(component);
    }

    public static void sendActionBar(Player player, String message){
        final TextComponent component = Component.text(MultiCommands.colored(message));
        MultiCommands.getInstance().adventure().player(player).sendActionBar(component);
    }



}
