package me.capitainecat0.multicommands.utils;

import me.capitainecat0.multicommands.MultiCommands;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.inventory.Book;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.sound.SoundStop;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.Tag;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Collection;
import java.util.Objects;
import java.util.Properties;

import static me.capitainecat0.multicommands.utils.Messenger.PLUGIN_PREFIX;

public class MessengerUtils {

    private static MultiCommands instance = MultiCommands.getInstance();
    //Define the bossbar name
    private static BossBar finalBossbar;
    //Define the language name
    private static Properties language;


    /**
     * @param text <br>Simple ChatColor translation
     */
    public static String colored(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }


    /**
     *
     * @param key Message key
     * @return The message config
     */
    public static String lang(String key) {
        checkLangFiles();
        return language.getProperty(colored(key));
    }

    /**
     *
     * Reload language config file
     */
    public static void reloadLang() {
        language = null;
        checkLangFiles();
    }

    /**
     *
     * Check if language config file exists
     */
    private static void checkLangFiles() {
        String langFormat = (String) MultiCommands.getInstance().getConfig().get("lang");
        final File langFile = new File(MultiCommands.getInstance().getDataFolder(), "lang/"+langFormat+".properties");
        langFile.getParentFile().mkdirs();

        if (!langFile.exists()) {
            saveResourceAs("lang/fr.properties");
            saveResourceAs("lang/en.properties");
        }

        if (language == null) {
            final Properties defaultLang = new Properties();
            try (final InputStream is = instance.getResource("lang/"+langFormat+".properties")) {
                defaultLang.load(is);
            } catch (final IOException e) {
                e.printStackTrace();
            }

            language = new Properties(defaultLang);
            try (final InputStream is = new FileInputStream(langFile)) {
                language.load(is);
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * @param sender Command sender
     * @param commandName Command name
     * @param message Message that be sent to command sender
     * <br>Message will be sent on different format channel.
     * Change format on config file
     * <br>(need to use AdventureAPI)
     */
    public static void getMsgSendConfig(CommandSender sender, String commandName, String message){
        if(instance.getConfig().get("send-message-on") != null){
            if(Objects.equals(instance.getConfig().get("send-message-on"), "CHAT") ||
                    Objects.equals(instance.getConfig().get("send-message-on"), "chat")){
                sendMessage(sender, message);
            }else if(Objects.equals(instance.getConfig().get("send-message-on"), "ACTIONBAR") ||
                    Objects.equals(instance.getConfig().get("send-message-on"), "actionbar")){
                sendActionBar(sender, message);
            }else if(Objects.equals(instance.getConfig().get("send-message-on"), "TITLE") ||
                    Objects.equals(instance.getConfig().get("send-message-on"), "title")){
                sendTitle(sender, commandName, message);
            }else if(Objects.equals(instance.getConfig().get("send-message-on"), "BOSSBAR") ||
                    Objects.equals(instance.getConfig().get("send-message-on"), "bossbar")){
                sendBossBar(sender, colored(message));
            }
        }else{
            sendMessage(sender, message.replace("{prefix}", PLUGIN_PREFIX.getMessage()));
        }

    }

    /**
     *
     * Hide BossBar for player that execute plugin's feature
     **/
    public static void hideActiveBossBar() {
        instance.adventure().players().hideBossBar(finalBossbar);
        finalBossbar = null;
    }

    /**
     *
     * @param message Message sent
     * <br>Send console message
     **/
    public static void sendConsoleMessage(String message){
        Bukkit.getConsoleSender().sendMessage(colored(message.replace("{prefix}", MultiCommands.getInstance().getDescription().getName())));
    }

    /**
     *
     * @param message
     * <br>Send broadcastMessage
     **/
    public static void sendBroadcastMessage(String message){
        Bukkit.getServer().broadcastMessage(colored(message.replace("{prefix}", PLUGIN_PREFIX.getMessage())));
    }

    /**
     *
     * @param message
     * <br>Send message to online players
     */
    public static void sendMessage(String message){
        final TextComponent component = Component.text(colored(message.replace("{prefix}", PLUGIN_PREFIX.getMessage())));
        instance.adventure().players().sendMessage(component);
    }

    /**
     *
     * @param player command executor
     * @param message Message sent
     * <br>Send message to player
     */
    public static void sendMessage(Player player, String message){
        final TextComponent component = Component.text(colored(message.replace("{prefix}", PLUGIN_PREFIX.getMessage())));
        instance.adventure().player(player).sendMessage(component);
    }

    /**
     *
     * @param sender Command sender
     * @param message Message sent
     * <br>Send message to sender
     */
    public static void sendMessage(CommandSender sender, String message){
        final TextComponent component = Component.text(colored(message.replace("{prefix}", PLUGIN_PREFIX.getMessage())));
        instance.adventure().sender(sender).sendMessage(component);
    }

    /**
     *
     * @param sender Command sender
     * @param commandName Command name
     * @param message Message sent
     * <br>Send message to command sender, with command name
     */
    private static void sendMessage(CommandSender sender, String commandName, String message){
        final TextComponent component = Component.text(colored("&3"+commandName+": "+message.replace("{prefix}", PLUGIN_PREFIX.getMessage())));
        instance.adventure().sender(sender).sendMessage(component);
    }

    /**
     *
     * @param message
     * @param hover Hover text
     * @param text Text revile
     * <br>Send message to all online players
     * <br>with custom over text
     */
    public static void sendHoverMessage(String message, String hover, String text){
        final TextComponent component = (TextComponent) MiniMessage.miniMessage().deserialize(colored(message.replace("{prefix}", PLUGIN_PREFIX.getMessage())), TagResolver.resolver(hover, Tag.styling(HoverEvent.showText(Component.text(colored(text.replace("{prefix}", PLUGIN_PREFIX.getMessage())))))));
        instance.adventure().players().sendMessage(component);
    }

    /**
     *
     * @param player
     * @param message
     * @param hover Hover text
     * @param text Text revile
     * <br>Send message to specific player
     * <br>with custom over text
     */
    public static void sendHoverMessage(Player player, String message, String hover, String text){
        final TextComponent component = (TextComponent) MiniMessage.miniMessage().deserialize(colored(message.replace("{prefix}", PLUGIN_PREFIX.getMessage())), TagResolver.resolver(hover.replace("{prefix}", PLUGIN_PREFIX.getMessage()), Tag.styling(HoverEvent.showText(Component.text(colored(text.replace("{prefix}", PLUGIN_PREFIX.getMessage())))))));
        instance.adventure().player(player).sendMessage(component);
    }

    /**
     *
     * @param sender
     * @param message
     * @param hover Hover text
     * @param text Text revile
     * <br>Send message to command sender
     * <br>with custom over text
     */
    public static void sendHoverMessage(CommandSender sender, String message, String hover, String text){
        String input = "<hover:show_text:"+message+"</hover>";
        Component component = MiniMessage.miniMessage().deserialize(input);
        instance.adventure().sender(sender).sendMessage(component);
    }

    /**
     *
     * @param message
     * @param command
     * <br>Send message to all online players
     * <br>with clickable text command
     */
    public static void sendCommandMessage(String message, String command){
        final TextComponent component = (TextComponent) MiniMessage.miniMessage().deserialize(colored(message.replace("{prefix}", PLUGIN_PREFIX.getMessage())), TagResolver.resolver(command, Tag.styling(ClickEvent.runCommand(command))));
        instance.adventure().players().sendMessage(component);
    }

    /**
     *
     * @param player
     * @param message
     * @param command
     * <br>Send message to specific player
     * <br>with clickable text command
     */
    public static void sendCommandMessage(Player player, String message, String command){
        String input = "<click:run_command:/"+command+">"+colored(message)+"</click>";
        Component component = MiniMessage.miniMessage().deserialize(input);
        instance.adventure().player(player).sendMessage(component);
    }

    /**
     *
     * @param sender
     * @param message
     * @param command
     * <br>Send message to command sender
     * <br>with clickable text command
     */
    public static void sendCommandMessage(CommandSender sender, String message, String command){
        String input = "<click:run_command:/"+command+">"+message+"</click>";
        Component component = MiniMessage.miniMessage().deserialize(input);
        instance.adventure().sender(sender).sendMessage(component);
    }

    /**
     *
     * @param message
     * @param url
     * <br>Send message to all online players
     * <br>with clickable url
     */
    public static void sendURLMessage(String message, String url){
        final TextComponent component = (TextComponent) MiniMessage.miniMessage().deserialize(colored(message.replace("{prefix}", PLUGIN_PREFIX.getMessage())), TagResolver.resolver(url, Tag.styling(ClickEvent.openUrl(url))));
        instance.adventure().players().sendMessage(component);
    }

    /**
     *
     * @param player
     * @param message
     * @param url
     * <br>Send message to specific player
     * <br>with clickable url
     */
    public static void sendURLMessage(Player player, String message, String url){
        final TextComponent component = (TextComponent) MiniMessage.miniMessage().deserialize(colored(message.replace("{prefix}", PLUGIN_PREFIX.getMessage())), TagResolver.resolver(url, Tag.styling(ClickEvent.openUrl(url))));
        instance.adventure().player(player).sendMessage(component);
    }

    /**
     *
     * @param sender
     * @param message
     * @param url
     * <br>Send message to command sender
     * <br>with clickable url
     */
    public static void sendURLMessage(CommandSender sender, String message, String url){
        final TextComponent component = (TextComponent) MiniMessage.miniMessage().deserialize(colored(message.replace("{prefix}", PLUGIN_PREFIX.getMessage())), TagResolver.resolver(url, Tag.styling(ClickEvent.openUrl(url))));
        instance.adventure().sender(sender).sendMessage(component);
    }

    /**
     *
     * @param message
     * @param command
     * <br>Send message to all online players
     * <br>with clickable text command
     */
    public static void sendSuggestCommandMessage(String message, String command){
        final TextComponent component = (TextComponent) MiniMessage.miniMessage().deserialize(colored(message.replace("{prefix}", PLUGIN_PREFIX.getMessage())), TagResolver.resolver(command, Tag.styling(ClickEvent.suggestCommand(command))));
        instance.adventure().players().sendMessage(component);
    }

    /**
     *
     * @param player
     * @param message
     * @param command
     * <br>Send message to specific player
     * <br>with clickable text command
     */
    public static void sendSuggestCommandMessage(Player player, String message, String command){
        final TextComponent component = (TextComponent) MiniMessage.miniMessage().deserialize(colored(message.replace("{prefix}", PLUGIN_PREFIX.getMessage())), TagResolver.resolver(command, Tag.styling(ClickEvent.suggestCommand(command))));

        instance.adventure().player(player).sendMessage(component);
    }

    /**
     *
     * @param sender
     * @param message
     * @param command
     * <br>Send message to command sender
     * <br>with clickable text command
     */
    public static void sendSuggestCommandMessage(CommandSender sender, String message, String command){
        final TextComponent component = (TextComponent) MiniMessage.miniMessage().deserialize(colored(message.replace("{prefix}", PLUGIN_PREFIX.getMessage())), TagResolver.resolver(command, Tag.styling(ClickEvent.suggestCommand(command))));
        MultiCommands.getInstance().adventure().sender(sender).sendMessage(component);
    }

    /**
     *
     * @param sender Command sender
     * @param title Book title
     * @param author Book author
     * @param message Message wrote
     * <br>Open custom book to player
     */
    public static void openBook(CommandSender sender, String title, String author, Collection<Component> message) {
        Component bookTitle = Component.text(title);
        Component bookAuthor = Component.text(author);
        Book book = Book.book(bookTitle, bookAuthor, message);
        instance.adventure().sender(sender).openBook(book);
    }

    /**
     *
     * @param sender Command sender
     * @param sound Adventure's sounds
     * @param volume Sound volume
     * @param pitch Sound pitch
     * <br>Play Adventure API sounds to player
     */
    public static void playSound(CommandSender sender, String sound, float volume, float pitch){
        try{
            Sound musicDisc = Sound.sound(Key.key(sound), Sound.Source.MUSIC, volume, pitch);
            instance.adventure().sender(sender).playSound(musicDisc);
        }catch(Error error){
            sendMessage(sender, "&cAn internal error was come when i try to play sound! Please contact an administrator !");
            sendConsoleMessage("&cAn internal error was come !&e"+error);
        }
    }

    /**
     *
     * @param sender Command sender
     * @param sound Bukkit sounds
     * @param volume Sound volume
     * @param pitch Sound pitch
     * <br>Play Bukkit sounds to player
     */
    public static void playSound(CommandSender sender, org.bukkit.Sound sound, float volume, float pitch){
        Player player = (Player) sender;
        try{
            player.getWorld().playSound(player.getLocation(),sound,volume, pitch);
        }catch(Error error){
            sendMessage(player, "&cAn internal error was come when i try to play sound! Please contact an administrator !");
            sendConsoleMessage("&cAn internal error was come !&e"+error);
        }
    }
    /**
     *
     * @param player
     * @param sound Adventure's sounds
     * @param volume Sound volume
     * @param pitch Sound pitch
     * <br>Play Adventure API sounds to player
     */
    public static void playSound(Player player, String sound, float volume, float pitch){
        try{
            Sound musicDisc = Sound.sound(Key.key(sound), Sound.Source.MUSIC, volume, pitch);
            instance.adventure().player(player.getUniqueId()).playSound(musicDisc);
        }catch(Error error){
            sendMessage(player, "&cAn internal error was come when i try to play sound! Please contact an administrator !");
            sendConsoleMessage("&cAn internal error was come !&e"+error);
        }
    }

    /**
     *
     * @param player
     * @param sound Bukkit sounds
     * @param volume Sound volume
     * @param pitch Sound pitch
     * <br>Play Bukkit sounds to player
     */
    public static void playSound(Player player, org.bukkit.Sound sound, float volume, float pitch){
        try{
            player.getWorld().playSound(player.getLocation(),sound,volume, pitch);
        }catch(Error error){
            sendMessage(player, "&cAn internal error was come when i try to play sound! Please contact an administrator !");
            sendConsoleMessage("&cAn internal error was come !&e"+error);
        }
    }

    /**
     *
     * @return boolean value
     */
    public static boolean soundEnabled(){
        if(instance.getConfig().get("enable-command-sounds") != null){
            if(instance.getConfig().getBoolean("enable-command-sounds")){
                return true;
            }else if(!instance.getConfig().getBoolean("enable-command-sounds")){
                return false;
            }
        }
        return false;
    }

    /**
     *
     * @return "feed-heal-sound" value
     */
    public static boolean soundFeedHealEnabled(){
        if(instance.getConfig().get("feed-heal-sounds") != null){
            if(instance.getConfig().getBoolean("feed-heal-sounds")){
                return true;
            }else if(!instance.getConfig().getBoolean("feed-heal-sounds")){
                return false;
            }
        }
        return false;
    }

    /**
     *
     * @param player
     * <br>Stop sounds to player
     */
    public static void stopSound(Player player){
        instance.adventure().player(player).stopSound(SoundStop.all());
    }

    /**
     *
     * Stop sounds for every online players
     */
    public static void stopSound(){
        instance.adventure().players().stopSound(SoundStop.all());
    }

    /**
     *
     * @param sender Command sender
     * @param commandName Command name
     * @param message Message sent
     * <br>Send title message to command sender
     */
    private static void sendTitle(CommandSender sender, String commandName, String message){
        final Title.Times times = new Title.Times() {
            @Override
            public @NotNull Duration fadeIn() {
                return Duration.ofSeconds(1);
            }

            @Override
            public @NotNull Duration stay() {
                return Duration.ofSeconds(3);
            }

            @Override
            public @NotNull Duration fadeOut() {
                return Duration.ofSeconds(1);
            }
        };
        final Component mainTitle = Component.text("§3"+commandName);
        final Component subtitle = Component.text(colored(message.replace("{prefix}", PLUGIN_PREFIX.getMessage())));
        final Title title = Title.title(mainTitle, subtitle, times);
        instance.adventure().sender(sender).showTitle(title);
    }

    /**
     *
     * @param title Title name
     * @param message Message sent
     * <br>Send title message for every online players
     */
    public static void sendTitle(String title, String message) {
        final Title.Times times = new Title.Times() {
            @Override
            public @NotNull Duration fadeIn() {
                return Duration.ofSeconds(1);
            }

            @Override
            public @NotNull Duration stay() {
                return Duration.ofSeconds(3);
            }

            @Override
            public @NotNull Duration fadeOut() {
                return Duration.ofSeconds(1);
            }
        };
        final Component mainTitle = Component.text(colored(title));
        final Component subtitle = Component.text(colored(message.replace("{prefix}", PLUGIN_PREFIX.getMessage())));
        final Title finalTitle = Title.title(mainTitle, subtitle, times);
        instance.adventure().players().showTitle(finalTitle);
    }

    /**
     *
     * @param player Message receiver
     * @param title Title name
     * @param message Message sent
     * <br>Send title message to player
     */
    public static void sendTitle(Player player, String title, String message) {
        final Title.Times times = new Title.Times() {
            @Override
            public @NotNull Duration fadeIn() {
                return Duration.ofSeconds(1);
            }

            @Override
            public @NotNull Duration stay() {
                return Duration.ofSeconds(3);
            }

            @Override
            public @NotNull Duration fadeOut() {
                return Duration.ofSeconds(1);
            }
        };
        final Component mainTitle = Component.text(colored(title));
        final Component subtitle = Component.text(colored(message.replace("{prefix}", PLUGIN_PREFIX.getMessage())));
        final Title finalTitle = Title.title(mainTitle, subtitle, times);
        instance.adventure().player(player).showTitle(finalTitle);
    }

    /**
     *
     * @param sender Command sender
     * @param message Message sent
     * <br>Send BossBar message to command sender
     * <br>Documentation page: <br><a href="https://docs.adventure.kyori.net/bossbar.html">https://docs.adventure.kyori.net/bossbar.html</a>
     */
    private static void sendBossBar(CommandSender sender, String message) {
        final Component name = Component.text(colored(message.replace("{prefix}", PLUGIN_PREFIX.getMessage())));
        finalBossbar = BossBar.bossBar(name, (float) 1, BossBar.Color.GREEN, BossBar.Overlay.NOTCHED_20);
        instance.adventure().sender(sender).showBossBar(finalBossbar);
    }

    /**
     *
     * @param progress In percent
     * @param color BossBar color
     * @param overlay BossBar overlay
     * @param message Message sent
     * <br>Send BossBar message for every online players
     * <br>Documentation page: <br><a href="https://docs.adventure.kyori.net/bossbar.html">https://docs.adventure.kyori.net/bossbar.html</a>
     */
    public static void sendBossBar(float progress, BossBar.Color color, BossBar.Overlay overlay, String message) {
        final Component name = Component.text(colored(message.replace("{prefix}", PLUGIN_PREFIX.getMessage())));
        finalBossbar = BossBar.bossBar(name, progress, color, overlay);
        instance.adventure().players().showBossBar(finalBossbar);
    }

    /**
     *
     * @param player Receiver
     * @param progress In percent
     * @param color BossBar color
     * @param overlay BossBar overlay
     * @param message Message sent
     * <br>Send BossBar message to player
     * <br>Documentation page: <br><a href="https://docs.adventure.kyori.net/bossbar.html">https://docs.adventure.kyori.net/bossbar.html</a>
     */
    public static void sendBossBar(Player player, float progress, BossBar.Color color, BossBar.Overlay overlay, String message) {
        final Component name = Component.text(colored(message.replace("{prefix}", PLUGIN_PREFIX.getMessage())));
        finalBossbar = BossBar.bossBar(name, progress, color, overlay);
        instance.adventure().player(player).showBossBar(finalBossbar);
    }

    /**
     *
     * @param sender Command sender
     * @param commandName Command name
     * @param message Message sent
     * <br>Send ActionBar message to command sender
     */
    private static void sendActionBar(CommandSender sender, String commandName, String message){
        final TextComponent component = Component.text(colored("&3"+commandName+": "+message.replace("{prefix}", "")));
        instance.adventure().sender(sender).sendActionBar(component);
    }

    /**
     *
     * @param message Message sent
     * <br>Send ActionBar message to every online players
     */
    public static void sendActionBar(String message){
        final TextComponent component = Component.text(colored(message.replace("{prefix}", " ")));
        instance.adventure().players().sendActionBar(component);
    }

    /**
     *
     * @param player Receiver
     * @param message Message sent
     * <br>Send ActionBar message to player
     */
    public static void sendActionBar(Player player, String message){
        final TextComponent component = Component.text(colored(message.replace("{prefix}", "")));
        instance.adventure().player(player).sendActionBar(component);
    }

    /**
     *
     * @param sender Command sender
     * @param message Message sent
     * <br>Send ActionBar message to player
     */
    public static void sendActionBar(CommandSender sender, String message){
        final TextComponent component = Component.text(colored(message.replace("{prefix}", "")));
        instance.adventure().sender(sender).sendActionBar(component);
    }

    /**
     *
     * @param player Receiver
     * @param header Tablist Header
     * @param footer Tablist footer
     *  <br>Send Tablist to player
     */
    public static void sendTablist(Player player, Component header, Component footer){
        player.sendPlayerListHeaderAndFooter(header, footer);
    }

    public static void saveResourceAs(String inPath) {
        if (inPath != null && !inPath.isEmpty()) {
            InputStream in = MultiCommands.getInstance().getResource(inPath);
            if (in == null) {
                throw new IllegalArgumentException(inPath + " unreachable!");
            } else {
                if (!MultiCommands.getInstance().getDataFolder().exists() && !MultiCommands.getInstance().getDataFolder().mkdir()) {
                    sendConsoleMessage("&cUnable to build folder!");
                }

                File inFile = new File(MultiCommands.getInstance().getDataFolder(), inPath);
                if (!inFile.exists()) {
                    sendConsoleMessage(inFile.getName() + "&c are unreachable, creating it ...");
                    MultiCommands.getInstance().saveResource(inPath, false);
                    if (!inFile.exists()) {
                        sendConsoleMessage("&cUnable to copy &e"+inFile.getName()+"&c file!");
                    } else {
                        sendConsoleMessage("&e"+inFile.getName() + "&a successfully created!");
                    }
                }

            }
        } else {
            throw new IllegalArgumentException("Folder cannot be empty/null !");
        }
    }
}
