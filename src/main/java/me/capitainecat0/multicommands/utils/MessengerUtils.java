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
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
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
     * The colored function takes a string and replaces all instances of the '&amp;' character with the ChatColor.COLOR_CHAR
     * character, allowing for easy use of Bukkit's built-in color codes.
     * <br>
     * <br>ColorCodes website: <br><a href="https://minecraftcolorcodes.org/">https://minecraftcolorcodes.org/</a>
     * @param text Get the text that you want to be colored
     *
     * @return The text with the color codes replaced

     */
    @Contract("_ -> new")
    public static @NotNull String colored(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
    
    /**    
     * The lang function is used to get a string from the language file.
     * 
     *
     * @param key Get the value of a key from the language file
     *
     * @return The value of the key in the language file
     */
    public static String lang(String key) {
        checkLangFiles();
        return language.getProperty(colored(key));
    }

    /**    
     * The reloadLang function is used to reload the language file.
     * This function is called when the user changes their language in-game,
     * and it will load a new lang.properties file from disk based on their selection.
     */
    public static void reloadLang() {
        language = null;
        checkLangFiles();
    }

    /**
     *
     * Check if language config file exists

     * The checkLangFiles function is used to check if the language files exist in the plugin's data folder.
     * If they do not, it will create them and copy over the default language files from inside the jar file.
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
     * The getMsgSendConfig function is used to send a message to the player in the way specified by
     * the config.yml file. The function takes three parameters: sender, commandName, and message.
     * Sender is a CommandSender object that represents who sent the command (the player).
     * CommandName is a String representing what command was run (e.g., /help).
     * Message is also a String that contains what you want to send as your message (e.g., &quot;Hello World!&quot;)
     * This function will then check if there's an entry for &quot;send-message-on&quot; in config.yml
     *
     * @param sender Send the message to a specific player
     * @param commandName Send the command name (only in a title)
     * @param message Send the message to the player
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
     * The hideActiveBossBar function hides the active boss bar.
     *
     *
     *
     * @return Kill the boss bar
     */
    public static void hideActiveBossBar() {
        instance.adventure().players().hideBossBar(finalBossbar);
        finalBossbar = null;
    }

    /**
     * The sendConsoleMessage function sends a message to the console.
     *
     *
     * @param message Send a message to the console
     *
     */
    public static void sendConsoleMessage(@NotNull String message){
        Bukkit.getConsoleSender().sendMessage(colored(message.replace("{prefix}", PLUGIN_PREFIX.getMessage())));
    }

    /**
     * The sendBroadcastMessage function sends a message to all players on the server.
     *
     *
     * @param message Send a message to the entire server
     *
     */
    public static void sendBroadcastMessage(@NotNull String message){
        Bukkit.getServer().broadcastMessage(colored(message.replace("{prefix}", PLUGIN_PREFIX.getMessage())));
    }

    /**
     * The sendMessage function sends a message to all players on the server.
     *
     *
     * @param message Send a message to all players
     * @deprecated   Use <var>sendBroadcastMessage</var> instead
     *
     */
    public static void sendMessage(@NotNull String message){
        final TextComponent component = Component.text(colored(message.replace("{prefix}", PLUGIN_PREFIX.getMessage())));
        instance.adventure().players().sendMessage(component);
    }


    /**
     * The sendMessage function is used to send a message to the player.
     *
     *
     * @param player Player that execute command/feature
     * @param message Message to send
     *
     */
    public static void sendMessage(Player player, @NotNull String message){
        final TextComponent component = Component.text(colored(message.replace("{prefix}", PLUGIN_PREFIX.getMessage())));
        instance.adventure().player(player).sendMessage(component);
    }

    /**
     * The sendMessage function is used to send a message to the command sender.
     *
     *
     * @param sender Player that execute command/feature
     * @param message Message to send
     *
     */
    public static void sendMessage(CommandSender sender, @NotNull String message){
        final TextComponent component = Component.text(colored(message.replace("{prefix}", PLUGIN_PREFIX.getMessage())));
        instance.adventure().sender(sender).sendMessage(component);
    }

    /**
     * The sendMessage function is a function that sends a message to command sender.
     *
     *
     * @param sender Player that execute command/feature
     * @param commandName Display the name of the command that is sending a message
     * @param message Message to send
     */
    public static void sendMessage(CommandSender sender, String commandName, @NotNull String message){
        final TextComponent component = Component.text(colored("&3"+commandName+": "+message.replace("{prefix}", PLUGIN_PREFIX.getMessage())));
        instance.adventure().sender(sender).sendMessage(component);
    }

    /**
     * The sendHoverMessage function sends a message to all players on the server with a hover message.
     *
     *
     * @param message Send a message to the player
     * @param hover Message displayed when you hover the message
     */
    public static void sendHoverMessage(@NotNull String message, @NotNull String hover){
        Component finalMessage = Component.text(message+MiniMessage.miniMessage().deserialize("<hover:show_text:"+hover+">"));
        instance.adventure().players().sendMessage(finalMessage);
    }

    /**
     * The sendHoverMessage function sends a message to the player with a hover message.
     *
     *
     * @param player Send the message to a specific player
     * @param message Send a message to the player
     * @param hover Message displayed when you hover over the message
     */
    public static void sendHoverMessage(Player player, @NotNull String message, @NotNull String hover){
        Component finalMessage = Component.text(message+MiniMessage.miniMessage().deserialize("<hover:show_text:"+hover+">"));
        instance.adventure().player(player).sendMessage(finalMessage);
    }

    /**
     * The sendHoverMessage function sends a message to command sender with a hover message.
     *
     *
     * @param sender Send the message to a player
     * @param message Send the message to the player
     * @param hover Display the text that will be shown when you hover over the message
     */
    public static void sendHoverMessage(CommandSender sender, String message, String hover){
        Component finalMessage = Component.text(message+MiniMessage.miniMessage().deserialize("<hover:show_text:"+hover+">"));
        instance.adventure().sender(sender).sendMessage(finalMessage);
    }

    /**
     * The sendCommandMessage function sends a message to all players on the server.
     * The message has a clickable command embedded in it.
     *
     *
     * @param message Send a message to the player
     * @param command Run a command when the player clicks on the message
     */
    public static void sendCommandMessage(String message, String command){
        String input = "<click:run_command:/"+command+">"+colored(message)+"</click>";
        Component component = MiniMessage.miniMessage().deserialize(input);
        instance.adventure().players().sendMessage(component);
    }

    /**
     * The sendCommandMessage function sends a message to the player with a clickable command.
     *
     *
     * @param player Send the message to a specific player
     * @param message Send the message to the player
     * @param command Set the command that will be executed when the player clicks on the message
     */
    public static void sendCommandMessage(Player player, String message, String command){
        String input = "<click:run_command:/"+command+">"+colored(message)+"</click>";
        Component component = MiniMessage.miniMessage().deserialize(input);
        instance.adventure().player(player).sendMessage(component);
    }

    /**
     * The sendCommandMessage function sends a message to the specified CommandSender, with the specified text and command.
     *
     *
     * @param sender Player that execute command/feature

    ```
     * @param message Send a message to the player
     * @param command Set the command that will be executed when the player clicks on the message
     */
    public static void sendCommandMessage(CommandSender sender, String message, String command){
        String input = "<click:run_command:/"+command+">"+message+"</click>";
        Component component = MiniMessage.miniMessage().deserialize(input);
        instance.adventure().sender(sender).sendMessage(component);
    }

    /**
     * The sendHoverCommandMessage function sends a message to all players with a hover message.
     *
     *
     * @param message Send the message to the player
     * @param hover Display the text that will be shown when you hover over the message
     * @param command Run a command when the player clicks on the message
     */
    public static void sendHoverCommandMessage(String message, String hover, String command){
        String input = "<click:run_command:/"+command+">"+colored(message)+"</click>";
        Component hoverMessage = Component.text(message+MiniMessage.miniMessage().deserialize("<hover:show_text:"+hover+">"));
        Component commandMessage = MiniMessage.miniMessage().deserialize(input);
        instance.adventure().players().sendMessage(hoverMessage);
        instance.adventure().players().sendMessage(commandMessage);
    }

    /**
     * The sendHoverCommandMessage function sends a message to a specified player with a hover message.
     *
     *
     * @param player Send the message to a player
     * @param message Send the message to the player
     * @param hover Display the text that will be shown when you hover over the message
     * @param command Run a command when the player clicks on the message
     */
    public static void sendHoverCommandMessage(Player player, String message, String hover, String command){
        String input = "<click:run_command:/"+command+">"+colored(message)+"</click>";
        Component hoverMessage = Component.text(message+MiniMessage.miniMessage().deserialize("<hover:show_text:"+hover+">"));
        Component commandMessage = MiniMessage.miniMessage().deserialize(input);
        instance.adventure().player(player).sendMessage(hoverMessage);
        instance.adventure().player(player).sendMessage(commandMessage);
    }

    /**
     * The sendHoverCommandMessage function sends a message to command sender with a hover message.
     *
     *
     * @param sender Send the message to a player
     * @param message Send the message to the player
     * @param hover Display the text that will be shown when you hover over the message
     * @param command Run a command when the player clicks on the message
     */
    public static void sendHoverCommandMessage(CommandSender sender, String message, String hover, String command){
        String input = "<click:run_command:/"+command+">"+colored(message)+"</click>";
        Component hoverMessage = Component.text(message+MiniMessage.miniMessage().deserialize("<hover:show_text:"+hover+">"));
        Component commandMessage = MiniMessage.miniMessage().deserialize(input);
        instance.adventure().sender(sender).sendMessage(hoverMessage);
        instance.adventure().sender(sender).sendMessage(commandMessage);
    }

    /**
     * The sendURLMessage function sends a message to all players with a clickable URL.
     *
     *
     * @param message Mmessage that will be sent to the player
     * @param url Url that will be opened when the player clicks on the message
     *
     * @return A clickable message with the text &quot;message&quot; and when clicked, it opens the url &quot;url&quot;
     */
    public static void sendURLMessage(String message, String url){
        String input = "<click:open_url:/"+url+">"+message+"</click>";
        Component component = MiniMessage.miniMessage().deserialize(input);
        instance.adventure().players().sendMessage(component);
    }

    /**
     * The sendURLMessage function sends a message to the player with a clickable URL.
     *
     *
     * @param player Send the message to a specific player
     * @param message Mmessage that will be sent to the player
     * @param url Url that will be opened when the player clicks on the message
     *
     * @return A clickable message with the text &quot;message&quot; and when clicked, it opens the url &quot;url&quot;
     */
    public static void sendURLMessage(Player player, String message, String url){
        String input = "<click:open_url:/"+url+">"+message+"</click>";
        Component component = MiniMessage.miniMessage().deserialize(input);
        instance.adventure().player(player).sendMessage(component);
    }

    /**
     * The sendURLMessage function sends a message to the player with a clickable URL.
     *
     *
     * @param sender Player that execute the command/feature
     * @param message Message that will be sent to the player
     * @param url Set the url that will be opened when the player clicks on the message
     *
     * @return A clickable message with the text &quot;message&quot; and when clicked, it opens the url &quot;url&quot;
     */
    public static void sendURLMessage(CommandSender sender, String message, String url){
        String input = "<click:open_url:/"+url+">"+message+"</click>";
        Component component = MiniMessage.miniMessage().deserialize(input);
        instance.adventure().sender(sender).sendMessage(component);
    }

    /**
     * The sendSuggestCommandMessage function sends a message to the player with a clickable link that suggests
     * the command in chat. This is useful for sending commands to players without them having to type it out themselves.
     *
     *
     * @param message Message sent to the player
     * @param command Command that will be suggested when the player clicks on the message
     */
    public static void sendSuggestCommandMessage(String message, String command){
        String input = "<click:suggest_command:/"+command+">"+colored(message)+"</click>";
        Component component = MiniMessage.miniMessage().deserialize(input);
        instance.adventure().players().sendMessage(component);
    }

    /**
     * The sendSuggestCommandMessage function sends a message to the player with a clickable link that suggests
     * the command in chat. This is useful for sending commands to players without them having to type it out themselves.
     *
     *
     * @param player Send the message to a specific player
     * @param message Message sent to the player
     * @param command Command that will be suggested when the player clicks on the message
     */
    public static void sendSuggestCommandMessage(Player player, String message, String command){
        String input = "<click:suggest_command:/"+command+">"+colored(message)+"</click>";
        Component component = MiniMessage.miniMessage().deserialize(input);
        instance.adventure().player(player).sendMessage(component);
    }

    /**
     * The sendSuggestCommandMessage function sends a message to the player with a clickable link that suggests
     * the command in chat. This is useful for sending commands to players without them having to type it out themselves.
     *
     * @param sender Player that execute command/feature
     * @param message Message sent to the player
     * @param command Command that will be suggested when the player clicks on the message
     */
    public static void sendSuggestCommandMessage(CommandSender sender, String message, String command){
        String input = "<click:suggest_command:/"+command+">"+colored(message)+"</click>";
        Component component = MiniMessage.miniMessage().deserialize(input);
        instance.adventure().sender(sender).sendMessage(component);
    }

    /**
     * The openBook function opens a book for all the players.
     *
     *
     * @param title Title of the book
     * @param author Author of the book
     * @param message Message that will be displayed in the book
     */
    public static void openBook(String title, String author, Collection<Component> message) {
        Component bookTitle = Component.text(title);
        Component bookAuthor = Component.text(author);
        Book book = Book.book(bookTitle, bookAuthor, message);
        instance.adventure().players().openBook(book);
    }

    /**
     * The openBook function opens a book for the player.
     *
     *
     * @param player Get the player that is opening the book
     * @param title Title of the book
     * @param author Author of the book
     * @param message Message that will be displayed in the book
     */
    public static void openBook(Player player, String title, String author, Collection<Component> message) {
        Component bookTitle = Component.text(title);
        Component bookAuthor = Component.text(author);
        Book book = Book.book(bookTitle, bookAuthor, message);
        instance.adventure().player(player).openBook(book);
    }

    /**
     * The openBook function opens a book for the specified player.
     *
     *
     * @param sender Player that execute command/feature
     * @param title Title of the book
     * @param author Author of the book
     * @param message Message that will be displayed in the book
     */
    public static void openBook(CommandSender sender, String title, String author, Collection<Component> message) {
        Component bookTitle = Component.text(title);
        Component bookAuthor = Component.text(author);
        Book book = Book.book(bookTitle, bookAuthor, message);
        instance.adventure().sender(sender).openBook(book);
    }

    /**
     * The playAdventureSound function plays a sound to the specified player from Adventure.
     * <br>
     * <br>Adventure documentation:
     * <br><a href="https://docs.advntr.dev/sound.html">https://docs.advntr.dev/sound.html</a>
     * <br>
     * @param player Send the sound to a specific player
     * @param sound Sound you want to play
     * @param volume Volume of the sound
     * @param pitch Pitch of the sound

     *
     */
    public static void playAdventureSound(Player player, String sound, float volume, float pitch){
        try{
            Sound musicDisc = Sound.sound(Key.key(sound), Sound.Source.MUSIC, volume, pitch);
            instance.adventure().player(player).playSound(musicDisc);
        }catch(Exception e){
            sendMessage(player, "&cAn internal error was come when i try to play sound! Please contact an administrator !");
            sendEventExceptionMessage(e, "Adventure playSound");
        }
    }

    /**
     * The playAdventureSound function plays a sound to the specified player from Adventure.
     * <br>
     * <br>Adventure documentation:
     * <br><a href="https://docs.advntr.dev/sound.html">https://docs.advntr.dev/sound.html</a>
     * <br>
     * @param sender Player that execute command/feature
     * @param sound Sound you want to play
     * @param volume Volume of the sound
     * @param pitch Pitch of the sound
     *
     */
    public static void playAdventureSound(CommandSender sender, String sound, float volume, float pitch){
        try{
            Sound musicDisc = Sound.sound(Key.key(sound), Sound.Source.MUSIC, volume, pitch);
            instance.adventure().sender(sender).playSound(musicDisc);
        }catch(Exception e){
            sendMessage(sender, "&cAn internal error was come when i try to play sound! Please contact an administrator !");
            sendEventExceptionMessage(e, "Adventure playSound");
        }
    }

    /**
     * The playBukkitSound function plays a sound to the player.
     *
     * @param player Player who received the sound
     * @param sound Sound you want to play
     * @param volume Volume of the sound
     * @param pitch Pitch of the sound
     */
    private static void playBukkitSound(@NotNull Player player, org.bukkit.Sound sound, float volume, float pitch){
        try{
            player.getWorld().playSound(player.getLocation(),sound,volume, pitch);
        }catch(Exception e){
            sendMessage(player, "&cAn internal error was come when i try to play sound! Please contact an administrator !");
            sendEventExceptionMessage(e, "Adventure playSound");
        }
    }

    /**
     * The playBukkitSound function plays a sound to the player.
     * @param sender Player that execute command/feature
     * @param sound Sound you want to play
     * @param volume Volume of the sound
     * @param pitch Pitch of the sound
     */
    private static void playBukkitSound(CommandSender sender, org.bukkit.Sound sound, float volume, float pitch){
        Player player = (Player) sender;
        try{
            player.getWorld().playSound(player.getLocation(),sound,volume, pitch);
        }catch(Exception e){
            sendMessage(player, "&cAn internal error was come when i try to play sound! Please contact an administrator !");
            sendEventExceptionMessage(e, "Adventure playSound");
        }
    }

    /**
     * The playSoundIfEnabled function plays a sound to the player
     * <br>if &quot;soundEnabled()&quot; return true.
     * <br>
     * <br>Bukkit sounds:
     * <br><a href="https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/Sound.html">https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/Sound.html</a>
     *
     * @param player Play the sound to a specific player
     * @param sound Sound you want to play
     */
    public static void playSoundIfEnabled(Player player, org.bukkit.Sound sound){
        if(soundEnabled()){
            playBukkitSound(player, sound, 1f, 1f);
        }
    }

    /**
     * The playSoundIfEnabled function plays a sound to the player
     * <br>if &quot;soundEnabled()&quot; return true.
     * <br>
     * <br>Bukkit sounds:
     * <br><a href="https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/Sound.html">https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/Sound.html</a>
     *
     * @param player Play the sound to a specific player
     * @param sound Sound you want to play
     * @param volume Volume of the sound
     */
    public static void playSoundIfEnabled(Player player, org.bukkit.Sound sound, float volume){
        if(soundEnabled()){
            playBukkitSound(player, sound, volume, 1f);
        }
    }

    /**
     * The playSoundIfEnabled function plays a sound to the player
     * <br>if &quot;soundEnabled()&quot; return true.
     * <br>
     * <br>Bukkit sounds:
     * <br><a href="https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/Sound.html">https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/Sound.html</a>
     *
     * @param player Play the sound to a specific player
     * @param sound Sound you want to play
     * @param volume Volume of the sound
     * @param pitch Pitch of the sound
     */
    public static void playSoundIfEnabled(Player player, org.bukkit.Sound sound, float volume, float pitch){
        if(soundEnabled()){
            playBukkitSound(player, sound, volume, pitch);
        }
    }

    /**
     * The playSoundIfEnabled function plays a sound to the player
     * <br>if &quot;soundEnabled()&quot; return true.
     * <br>
     * <br>Bukkit sounds:
     * <br><a href="https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/Sound.html">https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/Sound.html</a>
     *
     * @param sender Player that execute command/feature
     * @param sound Sound you want to play
     */
    public static void playSoundIfEnabled(CommandSender sender, org.bukkit.Sound sound){
        if(soundEnabled()){
            playBukkitSound(sender, sound, 1f, 1f);
        }
    }

    /**
     * The playSoundIfEnabled function plays a sound to the player
     * <br>if &quot;soundEnabled()&quot; return true.
     * <br>
     * <br>Bukkit sounds:
     * <br><a href="https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/Sound.html">https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/Sound.html</a>
     *
     * @param sender Player that execute command/feature
     * @param sound Sound you want to play
     * @param volume Volume of the sound
     */
    public static void playSoundIfEnabled(CommandSender sender, org.bukkit.Sound sound, float volume){
        if(soundEnabled()){
            playBukkitSound(sender, sound, volume, 1f);
        }
    }

    /**
     * The playSoundIfEnabled function plays a sound to the player
     * <br>if &quot;soundEnabled()&quot; return true.
     * <br>
     * <br>Bukkit sounds:
     * <br><a href="https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/Sound.html">https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/Sound.html</a>
     *
     * @param sender Player that execute command/feature
     * @param sound Sound you want to play
     * @param volume Volume of the sound
     * @param pitch Pitch of the sound
     */
    public static void playSoundIfEnabled(CommandSender sender, org.bukkit.Sound sound, float volume, float pitch){
        if(soundEnabled()){
            playBukkitSound(sender, sound, volume, pitch);
        }
    }

    /**
     * The soundEnabled function checks the config.yml file for a boolean value
     * under the key &quot;enable-command-sounds&quot;. If it is true, then soundEnabled()
     * returns true. If it is false, then soundEnabled() returns false. If there
     * is no such key in the config file, or if its value isn't a boolean (true/false),
     * then soundEnabled() will return false by default. This function can be used to check whether sounds should be played when commands are executed in game! :)
     *
     *
     * @return Boolean value defined on config.yml
     */
    private static boolean soundEnabled(){
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
     * The stopSound function stops all sounds that are currently playing for a player.
     *
     *
     * @param player Specify the player who will hear the sound
     */
    public static void stopSound(Player player){
        instance.adventure().player(player).stopSound(SoundStop.all());
    }

    /**
     * The stopSound function stops all sounds that are currently playing for all players.
     */
    public static void stopSound(){
        instance.adventure().players().stopSound(SoundStop.all());
    }

    /**
     * The sendTitle function is a function that sends a title to the player.
     *
     *
     * @param sender Player that execute command/feature
     * @param commandName Display the command name in the title
     * @param message Message sent to the player
     */
    private static void sendTitle(CommandSender sender, String commandName, @NotNull String message){
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
     * The sendTitle function sends a title to all players on the server.
     *
     *
     * @param title Title
     * @param message Message sent to the player
     */
    public static void sendTitle(String title, @NotNull String message) {
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
     * The sendTitle function sends a title to the player.
     *
     *
     * @param player Send the title to a specific player
     * @param title Title
     * @param message Message sent to the player
     */
    public static void sendTitle(Player player, String title, @NotNull String message) {
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
     * The sendBossBar function is used to send a boss bar message to the player.
     *
     * <br>Documentation page: <br><a href="https://docs.adventure.kyori.net/bossbar.html">https://docs.adventure.kyori.net/bossbar.html</a>
     *
     * @param sender Player that execute command/feature
     * @param message Message sent to the player
     */
    private static void sendBossBar(CommandSender sender, @NotNull String message) {
        final Component name = Component.text(colored(message.replace("{prefix}", PLUGIN_PREFIX.getMessage())));
        finalBossbar = BossBar.bossBar(name, (float) 1, BossBar.Color.GREEN, BossBar.Overlay.NOTCHED_20);
        instance.adventure().sender(sender).showBossBar(finalBossbar);
    }

    /**
     * The sendBossBar function is used to send a boss bar to all players on the server.
     *
     * <br>Documentation page: <br><a href="https://docs.adventure.kyori.net/bossbar.html">https://docs.adventure.kyori.net/bossbar.html</a>
     *
     * @param progress Set the progress of the boss bar
     * @param color Set the color of the bossbar
     * @param overlay Determine what kind of overlay the boss bar has
     * @param message Set the message of the boss bar
     */
    public static void sendBossBar(float progress, BossBar.Color color, BossBar.Overlay overlay, @NotNull String message) {
        final Component name = Component.text(colored(message.replace("{prefix}", PLUGIN_PREFIX.getMessage())));
        finalBossbar = BossBar.bossBar(name, progress, color, overlay);
        instance.adventure().players().showBossBar(finalBossbar);
    }

    /**
     * The sendBossBar function sends a boss bar to the player.
     *
     * <br>Documentation page: <br><a href="https://docs.adventure.kyori.net/bossbar.html">https://docs.adventure.kyori.net/bossbar.html</a>
     *
     * @param player Send the bossbar to a specific player
     * @param progress Set the progress of the bossbar
     * @param color Set the color of the boss bar
     * @param overlay Set the overlay of the boss bar
     * @param message Set the message of the boss bar
     *
     * @return A bossbar
     *
     * @docauthor Trelent
     */
    public static void sendBossBar(Player player, float progress, BossBar.Color color, BossBar.Overlay overlay, @NotNull String message) {
        final Component name = Component.text(colored(message.replace("{prefix}", PLUGIN_PREFIX.getMessage())));
        finalBossbar = BossBar.bossBar(name, progress, color, overlay);
        instance.adventure().player(player).showBossBar(finalBossbar);
    }

    /**
     * The sendActionBar function sends a message to all players in the form of an action bar.
     *
     *
     * @param message Send a message to the player
     */
    public static void sendActionBar(@NotNull String message){
        final TextComponent component = Component.text(colored(message.replace("{prefix}", "")));
        instance.adventure().players().sendActionBar(component);
    }

    /**
     * The sendActionBar function sends a message to the player's action bar.
     *
     *
     * @param player Send the action bar to a specific player
     * @param message Send the message to the player
     */
    public static void sendActionBar(Player player, @NotNull String message){
        final TextComponent component = Component.text(colored(message.replace("{prefix}", "")));
        instance.adventure().player(player).sendActionBar(component);
    }

    /**
     * The sendActionBar function sends a message to the player in the form of an action bar.
     *
     *
     * @param sender Player that execute command/feature
     * @param message Send the message to the player
     */
    public static void sendActionBar(CommandSender sender, @NotNull String message){
        final TextComponent component = Component.text(colored(message.replace(PLUGIN_PREFIX.getMessage(), "")));
        instance.adventure().sender(sender).sendActionBar(component);
    }

    /**
     * The sendTablist function sends a tablist to the player.
     *
     *
     * @param player Send the tablist to a specific player
     * @param header Set the header of the tablist
     * @param footer Set the footer of the tablist
     */
    public static void sendTablist(@NotNull Player player, Component header, Component footer){
        player.sendPlayerListHeaderAndFooter(header, footer);
    }

    /**
     * The saveResourceAs function is used to save a resource file from the plugin's jar into the plugin's data folder.
     *
     *
     * @param inPath Get the file from the jar, and then save it in your plugin folder
     */
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

    /**
     * The sendCommandExceptionMessage function is used to send a message to the console when an exception occurs in a command.
     *
     *
     * @param exception Get the error message and line
     * @param command Display the command that caused the error
     *
     * @return The detailed command error message
     */
    public static void sendCommandExceptionMessage(@NotNull Exception exception, String command){
        // Obtenir la ligne de l'erreur
        StackTraceElement errorLine = exception.getStackTrace()[0];
        sendConsoleMessage("&e----{prefix} &cERROR&e----");
        sendConsoleMessage("&cCaused by: &e/"+command);
        sendConsoleMessage("&cJava Class: &e"+exception.getClass());
        sendConsoleMessage("&cError: &e"+exception.getMessage());
        sendConsoleMessage("&cLine: &e"+errorLine.toString().replace("MultiCommands.jar//", ""));
        sendConsoleMessage("&e----{prefix} &cERROR&e----");
    }

    /**
     * The sendEventExceptionMessage function is used to send a message to the console when an exception occurs in an event.
     *
     *
     * @param exception Get the error message and line
     * @param event Display the name of the event that caused this error
     *
     * @return The detailed event error message
     */
    public static void sendEventExceptionMessage(@NotNull Exception exception, String event){
        // Obtenir la ligne de l'erreur
        StackTraceElement errorLine = exception.getStackTrace()[0];
        sendConsoleMessage("&e----{prefix} &cERROR&e----");
        sendConsoleMessage("&cCaused by: &e"+event);
        sendConsoleMessage("&cJava Class: &e"+exception.getClass());
        sendConsoleMessage("&cError: &e"+exception.getMessage());
        sendConsoleMessage("&cLine: &e"+errorLine.toString().replace("MultiCommands.jar//", ""));
        sendConsoleMessage("&e----{prefix} &cERROR&e----");
    }
}
