package me.capitainecat0.multicommands.utils;

import me.capitainecat0.multicommands.MultiCommands;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.inventory.Book;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.sound.SoundStop;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.intellij.lang.annotations.Subst;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.Properties;

import static me.capitainecat0.multicommands.utils.Messenger.PLUGIN_PREFIX;

public class MessengerUtils {

    private static final MultiCommands instance = MultiCommands.getInstance();
    //Define the bossbar name
    private static BossBar finalBossbar;
    //Define the language name
    private static Properties language;


    /**
     * La fonction colored prend une chaîne de messages et remplace toutes les instances du caractère '&amp;' avec le caractère ChatColor.COLOR_CHAR,
     * <br>permettant une utilisation facile des codes de couleur intégrés de Bukkit.
     * <br>
     * <br>Documentation Bukkit: <a href="https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/ChatColor.html">ChatColor</a>
     * <br>
     * <br>Codes couleurs minecraft: <a href="https://minecraftcolorcodes.org/">Minecraft color codes</a>
     * <br>
     *
     * @param text Texte à colorer
     * @return Texte coloré
     * @deprecated Utilisez <a href="https://docs.advntr.dev/minimessage/format.html#color">l'API Adventure</a> à la place
     **/
 @Deprecated
 public static @NotNull String colored(String text) {
     return ChatColor.translateAlternateColorCodes('&', text);
    }
    
    /**
     * La fonction lang est utilisée pour obtenir une chaîne du fichier de langue.
     * <br>La fonction prend un paramètre: key.
     * <br>Key est une chaîne qui représente le chemin de la chaîne dans le fichier de langue.
     * <br>
     * @param key Chemin de la chaîne dans le fichier de langue
     *            <br>Exemple: &quot;lang.test&quot;
     *            <br>Le chemin de la chaîne doit être au format &quot;lang.<chemin>&quot;
     *            <br>
     * @return Valeur de la chaîne dans le fichier de langue
     */
    public static String lang(String key) {
        checkLangFiles();
        return language.getProperty(key);
    }

    /**
     * La fonction reloadlang est utilisée pour recharger le fichier de langue.
     * Cette fonction est appelée lorsque l'utilisateur change de langue dans le jeu,
     * et elle chargera un nouveau fichier lang.properties à partir du disque en fonction de leur sélection.
     */
    public static void reloadLang() {
        language = null;
        checkLangFiles();
    }

    /**
     * La fonction checkLangFiles est utilisée pour vérifier si les fichiers de langue existent dans le dossier de données du plugin.
     * Si le(s) fichier(s) n'existe pas, il le(s) créera et copiera les fichiers de langue par défaut à partir du fichier jar.
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
                e.fillInStackTrace();
            }

            language = new Properties(defaultLang);
            try (final InputStream is = new FileInputStream(langFile)) {
                language.load(is);
            } catch (final IOException e) {
                e.fillInStackTrace();
            }
        }
    }

    /**
     * La fonction saveRessourceAs permet de sauvegarder un fichier de ressource dans le dossier du plugin.
     * <br>
     * @param inPath Chemin de la ressource
     *               <br>Exemple: &quot;config.yml&quot;
     *               <br>
     */
    public static void saveResourceAs(String inPath) {
        try{
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
        }catch (Exception e){
            sendConsoleMessage("&cAn internal error was come when i try to save resource! Please contact an administrator !");
            sendEventExceptionMessage(e, "Save resource");
        }
    }

    public static void loadConfig(String inPath) {
        try{
            File inFile = new File(MultiCommands.getInstance().getDataFolder(), inPath);
            if (inFile.exists()) {
                MultiCommands.getInstance().getConfig().load(inFile);
            }
        }catch (Exception e){
            sendConsoleMessage("&cAn internal error was come when i try to load config! Please contact an administrator !");
            sendEventExceptionMessage(e, "Load config");
        }
    }
    /**
     * La fonction getMsgSendConfig est utilisée pour envoyer un message au joueur de la manière spécifiée par le fichier config.yml.
     * La fonction prend trois paramètres: sender, commandName et message.
     * <br>Sender est un objet CommandSender qui représente qui a envoyé la commande (le joueur).
     * <br>CommandName est une chaîne représentant la commande qui a été exécutée (par exemple, /help).
     * <br>Message est également une chaîne qui contient ce que vous voulez envoyer comme message (par exemple, &quot;Hello World!&quot;)
     * <br>
     * <br>Cette fonction vérifiera ensuite s'il existe une entrée pour &quot;send-message-on&quot; dans config.yml
     *
     * @param sender Joueur qui exécute la commande/fonction
     *               <br>Exemple: &quot;CapitaineCat0&quot;
     *               <br>Le nom du joueur doit être valide.
     *               <br>
     * @param commandName Envoie le nom de la commande/fonction (uniquement en titre)
     *                    <br>Exemple: &quot;/help&quot;
     *                    <br>
     * @param message Message à envoyer
     *                <br>Exemple: &quot;Hello World!&quot;
     *
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
                sendBossBar(sender, message);
            }
        }else{
            sendMessage(sender, message.replace("{prefix}", PLUGIN_PREFIX.getMessage()));
        }

    }

    /**
     * La fonction hideActiveBossBar est utilisée pour cacher la barre de boss active.
     */
    public static void hideActiveBossBar() {
        instance.adventure().players().hideBossBar(finalBossbar);
        finalBossbar = null;
    }

    /**
     * La fonction sendConsoleMessage est utilisée pour envoyer un message à la console.
     * @param message Message à envoyer
     *                <br>Exemple: &quot;Hello World!&quot;
     */
    public static void sendConsoleMessage(@NotNull String message){
        Bukkit.getConsoleSender().sendMessage(colored(message.replace("{prefix}", PLUGIN_PREFIX.getMessage())));
    }

    /**
     * La fonction sendBroadcastMessage envoie un message à tous les joueurs du serveur.
     * @param message Message à envoyer
     *                <br>Exemple: &quot;Hello World!&quot;
     *
     */
    public static void sendBroadcastMessage(@NotNull String message){
        //Bukkit.getServer().broadcastMessage(colored(message.replace("{prefix}", PLUGIN_PREFIX.getMessage())));
        instance.adventure().players().sendMessage(Component.text(message.replace("{prefix}", PLUGIN_PREFIX.getMessage())));
    }

    /**
     * La fonction sendMessage envoie un message à tous les joueurs du serveur.
     * @param message Message à envoyer
     *                <br>Exemple: &quot;Hello World!&quot;
     *                <br>
     * @deprecated  Utilisez <var>sendBroadcastMessage</var> à la place
     * @see #sendBroadcastMessage(String)
     */
    @Deprecated public static void sendMessage(@NotNull String message){
        final Component component = MiniMessage.miniMessage().deserialize(message.replace("{prefix}", PLUGIN_PREFIX.getMessage()));
        instance.adventure().players().sendMessage(component);
    }

    /**
     * La fonction sendMessage envoie un message à un joueur spécifique.
     * @param player Joueur qui recevra le message
     *               <br>Exemple: &quot;CapitaineCat0&quot;
     *               <br>Le nom du joueur doit être valide.
     *               <br>
     * @param message Message à envoyer
     *                <br>Exemple: &quot;Hello World!&quot;
     *
     */
    public static void sendMessage(Player player, @NotNull String message){
        final Component component = MiniMessage.miniMessage().deserialize(message.replace("{prefix}", PLUGIN_PREFIX.getMessage()));
        instance.adventure().player(player).sendMessage(component);
    }

    /**
     * La fonction sendMessage envoie un message au joueur qui exécute la commande.
     * @param sender Joueur qui exécute la commande/fonction
     *               <br>Exemple: &quot;CapitaineCat0&quot;
     *               <br>Le nom du joueur doit être valide.
     *               <br>
     * @param message Message à envoyer
     *                <br>Exemple: &quot;Hello World!&quot;
     *
     */
    public static void sendMessage(CommandSender sender, @NotNull String message){
        final Component component = MiniMessage.miniMessage().deserialize(message.replace("{prefix}", PLUGIN_PREFIX.getMessage()));
        instance.adventure().sender(sender).sendMessage(component);
    }

    /**
     * La fonction sendMessage envoie un message au joueur qui exécute la commande.
     * @param sender Joueur qui exécute la commande/fonction
     *               <br>Exemple: &quot;CapitaineCat0&quot;
     *               <br>Le nom du joueur doit être valide.
     *               <br>
     * @param commandName Nom de la commande/fonction (uniquement en titre)
     *                    <br>Exemple: &quot;/help&quot;
     *                    <br>
     * @param message Message à envoyer
     *                <br>Exemple: &quot;Hello World!&quot;
     */
    public static void sendMessage(CommandSender sender, String commandName, @NotNull String message){
        final Component component = Component.text("<dark_aqua>"+commandName+": "+message.replace("{prefix}", PLUGIN_PREFIX.getMessage()));
        instance.adventure().sender(sender).sendMessage(component);
    }

    /**
     * La fonction sendHoverMessage envoie un message à tous les joueurs du serveur avec un message de survol.
     * @param message Message à envoyer
     *                <br>Exemple: &quot;Hello World!&quot;
     *                <br>
     * @param hover Message affiché lorsque vous survolez le message
     *              <br>Exemple: &quot;Hover message&quot;
     */
    public static void sendHoverMessage(@NotNull String message, @NotNull String hover){
        Component finalMessage = Component.text(message+MiniMessage.miniMessage().deserialize("<hover:show_text:"+hover+">"));
        instance.adventure().players().sendMessage(finalMessage);
    }

    /**
     * La fonction sendHoverMessage envoie un message à un joueur spécifique avec un message de survol.
     * @param player Joueur qui recevra le message
     *               <br>Exemple: &quot;CapitaineCat0&quot;
     *               <br>Le nom du joueur doit être valide.
     *               <br>
     * @param message Message à envoyer
     *                <br>Exemple: &quot;Hello World!&quot;
     *                <br>
     * @param hover Message affiché lorsque vous survolez le message
     *              <br>Exemple: &quot;Hover message&quot;
     *              <br>
     */
    public static void sendHoverMessage(Player player, @NotNull String message, @NotNull String hover){
        Component finalMessage = Component.text(message+MiniMessage.miniMessage().deserialize("<hover:show_text:"+hover+">"));
        instance.adventure().player(player).sendMessage(finalMessage);
    }

    /**
     * La fonction sendHoverMessage envoie un message au joueur qui exécute la commande avec un message de survol.
     * @param sender Joueur qui exécute la commande/fonction
     *               <br>Exemple: &quot;CapitaineCat0&quot;
     *               <br>Le nom du joueur doit être valide.
     *               <br>
     * @param message Message à envoyer
     *                <br>Exemple: &quot;Hello World!&quot;
     *                <br>
     * @param hover Message affiché lorsque vous survolez le message
     *              <br>Exemple: &quot;Hover message&quot;
     *              <br>
     */
    public static void sendHoverMessage(CommandSender sender, String message, String hover){
        Component finalMessage = Component.text(message+MiniMessage.miniMessage().deserialize("<hover:show_text:"+hover+">"));
        instance.adventure().sender(sender).sendMessage(finalMessage);
    }

    /**
     * La fonction sendCommandMessage envoie un message à tous les joueurs du serveur
     * avec une commande cliquable.
     * @param message Message à envoyer
     *                <br>Exemple: &quot;Hello World!&quot;
     *                <br>
     * @param command Commande à exécuter lorsque le joueur clique sur le message
     *                <br>Exemple: &quot;/help&quot;
     *                <br>
     */
    public static void sendCommandMessage(String message, String command){
        String input = "<click:run_command:/"+command+">"+message+"</click>";
        Component component = MiniMessage.miniMessage().deserialize(input);
        instance.adventure().players().sendMessage(component);
    }

    /**
     * La fonction sendCommandMessage envoie un message à un joueur spécifique avec une commande cliquable.
     * @param player Joueur qui recevra le message
     *               <br>Exemple: &quot;CapitaineCat0&quot;
     *               <br>Le nom du joueur doit être valide.
     *               <br>
     * @param message Message à envoyer
     *                <br>Exemple: &quot;Hello World!&quot;
     *                <br>
     * @param command Commande à exécuter lorsque le joueur clique sur le message
     *                <br>Exemple: &quot;/help&quot;
     *                <br>
     */
    public static void sendCommandMessage(Player player, String message, String command){
        String input = "<click:run_command:/"+command+">"+message+"</click>";
        Component component = MiniMessage.miniMessage().deserialize(input);
        instance.adventure().player(player).sendMessage(component);
    }

    /**
     * La fonction sendCommandMessage envoie un message au joueur qui exécute la commande avec une commande cliquable.
     * @param sender Joueur qui exécute la commande/fonction
     *               <br>Exemple: &quot;CapitaineCat0&quot;
     *               <br>Le nom du joueur doit être valide.
     *               <br>
     * @param message Message à envoyer
     *                <br>Exemple: &quot;Hello World!&quot;
     *                <br>
     * @param command Commande à exécuter lorsque le joueur clique sur le message
     *                <br>Exemple: &quot;/help&quot;
     *                <br>
     */
    public static void sendCommandMessage(CommandSender sender, String message, String command){
        String input = "<click:run_command:/"+command+">"+message+"</click>";
        Component component = MiniMessage.miniMessage().deserialize(input);
        instance.adventure().sender(sender).sendMessage(component);
    }

    /**
     * La fonction sendHoverCommandMessage envoie un message à tous les joueurs du serveur avec un message de survol et une commande cliquable.
     * @param message Message à envoyer
     *                <br>Exemple: &quot;Hello World!&quot;
     *                <br>
     * @param hover Message affiché lorsque vous survolez le message
     *              <br>Exemple: &quot;/help command&quot;
     *              <br>
     * @param command Commande à exécuter lorsque le joueur clique sur le message
     *                <br>Exemple: &quot;/help&quot;
     *                <br>
     */
    public static void sendHoverCommandMessage(String message, String hover, String command){
        String input = "<click:run_command:/"+command+">"+message+"</click>";
        Component hoverMessage = Component.text(message+MiniMessage.miniMessage().deserialize("<hover:show_text:"+hover+">"));
        Component commandMessage = MiniMessage.miniMessage().deserialize(input);
        instance.adventure().players().sendMessage(hoverMessage);
        instance.adventure().players().sendMessage(commandMessage);
    }

    /**
     * La fonction sendHoverCommandMessage envoie un message à un joueur spécifique avec un message de survol et une commande cliquable.
     * @param player Joueur qui recevra le message
     *               <br>Exemple: &quot;CapitaineCat0&quot;
     *               <br>Le nom du joueur doit être valide.
     *               <br>
     * @param message Message à envoyer
     *                <br>Exemple: &quot;Hello World!&quot;
     *                <br>
     * @param hover Message affiché lorsque vous survolez le message
     *              <br>Exemple: &quot;/help command&quot;
     *              <br>
     * @param command Commande à exécuter lorsque le joueur clique sur le message
     *                <br>Exemple: &quot;/help&quot;
     *                <br>
     */
    public static void sendHoverCommandMessage(Player player, String message, String hover, String command){
        String input = "<click:run_command:/"+command+">"+message+"</click>";
        Component hoverMessage = Component.text(message+MiniMessage.miniMessage().deserialize("<hover:show_text:"+hover+">"));
        Component commandMessage = MiniMessage.miniMessage().deserialize(input);
        instance.adventure().player(player).sendMessage(hoverMessage);
        instance.adventure().player(player).sendMessage(commandMessage);
    }

    /**
     * La fonction sendHoverCommandMessage envoie un message au joueur qui exécute la commande avec un message de survol et une commande cliquable.
     * @param sender Joueur qui exécute la commande/fonction
     *               <br>Exemple: &quot;CapitaineCat0&quot;
     *               <br>Le nom du joueur doit être valide.
     *               <br>
     * @param message Message à envoyer
     *                <br>Exemple: &quot;Hello World!&quot;
     *                <br>
     * @param hover Message affiché lorsque vous survolez le message
     *              <br>Exemple: &quot;/help command&quot;
     *              <br>
     * @param command Commande à exécuter lorsque le joueur clique sur le message
     *                <br>Exemple: &quot;/help&quot;
     *                <br>
     */
    public static void sendHoverCommandMessage(CommandSender sender, String message, String hover, String command){
        String input = "<click:run_command:/"+command+">"+message+"</click>";
        Component hoverMessage = Component.text(message+MiniMessage.miniMessage().deserialize("<hover:show_text:"+hover+">"));
        Component commandMessage = MiniMessage.miniMessage().deserialize(input);
        instance.adventure().sender(sender).sendMessage(hoverMessage);
        instance.adventure().sender(sender).sendMessage(commandMessage);
    }

    /**
     * La fonction sendURLMessage envoie un message à tous les joueurs du serveur avec un lien cliquable.
     * @param message Message à envoyer
     *                <br>Exemple: &quot;Hello world!&quot;
     *                <br>
     * @param url Lien à ouvrir lorsque le joueur clique sur le message
     *            <br>Exemple: <a href="https://www.youtube.com/watch?v=dQw4w9WgXcQ">...</a>
     *            <br>Le lien doit être au format &quot;https://www.youtube.com/watch?v=W3q8Od5qJio&quot;
     *            <br>
     */
    public static void sendURLMessage(String message, String url){
        String input = "<click:open_url:/"+url+">"+message+"</click>";
        Component component = MiniMessage.miniMessage().deserialize(input);
        instance.adventure().players().sendMessage(component);
    }

    /**
     * La fonction sendURLMessage envoie un message à un joueur spécifique avec un lien cliquable.
     * @param player Joueur qui recevra le message
     *               <br>Exemple: &quot;CapitaineCat0&quot;
     *               <br>Le nom du joueur doit être valide.
     *               <br>
     * @param message Message à envoyer
     *                <br>Exemple: &quot;Hello world!&quot;
     *                <br>
     * @param url Lien à ouvrir lorsque le joueur clique sur le message
     *            <br>Exemple: <a href="https://www.youtube.com/watch?v=dQw4w9WgXcQ">...</a>
     *            <br>Le lien doit être au format &quot;https://www.youtube.com/watch?v=W3q8Od5qJio&quot;
     *
     */
    public static void sendURLMessage(Player player, String message, String url){
        String input = "<click:open_url:/"+url+">"+message+"</click>";
        Component component = MiniMessage.miniMessage().deserialize(input);
        instance.adventure().player(player).sendMessage(component);
    }

    /**
     * La fonction sendURLMessage envoie un message au joueur qui exécute la commande avec un lien cliquable.
     * @param sender Joueur qui exécute la commande/fonction
     *               <br>Exemple: &quot;CapitaineCat0&quot;
     *               <br>Le nom du joueur doit être valide.
     *               <br>
     * @param message Message à envoyer
     *                <br>Exemple: &quot;Hello world!&quot;
     *                <br>
     * @param url Lien à ouvrir lorsque le joueur clique sur le message
     *            <br>Exemple: <a href="https://www.youtube.com/watch?v=dQw4w9WgXcQ">...</a>
     *            <br>Le lien doit être au format &quot;https://www.youtube.com/watch?v=W3q8Od5qJio&quot;
     *
     * @return Un message cliquable avec le texte &quot;Hello world!&quot; et lorsqu'il est cliqué, il ouvre le lien &quot;url&quot;
     */
    public static void sendURLMessage(CommandSender sender, String message, String url){
        String input = "<click:open_url:/"+url+">"+message+"</click>";
        Component component = MiniMessage.miniMessage().deserialize(input);
        instance.adventure().sender(sender).sendMessage(component);
    }

    /**
     * La fonction sendSuggestCommandMessage envoie un message à tous les joueurs du serveur avec un lien cliquable qui suggère une commande dans le chat.
     * @param message Message à envoyer
     *                <br>Exemple: &quot;Hello world!&quot;
     *                <br>
     * @param command Commande qui sera suggérée lorsque le joueur clique sur le message
     *                <br>Exemple: &quot;/help&quot;
     *                <br>
     */
    public static void sendSuggestCommandMessage(String message, String command){
        String input = "<click:suggest_command:/"+command+">"+message+"</click>";
        Component component = MiniMessage.miniMessage().deserialize(input);
        instance.adventure().players().sendMessage(component);
    }

    /**
     * La fonction sendSuggestCommandMessage envoie un message à un joueur spécifique avec un lien cliquable qui suggère une commande dans le chat.
     * @param player Joueur qui recevra le message
     *               <br>Exemple: &quot;CapitaineCat0&quot;
     *               <br>Le nom du joueur doit être valide.
     *               <br>
     * @param message Message à envoyer
     *                <br>Exemple: &quot;Hello world!&quot;
     *                <br>
     * @param command Commande qui sera suggérée lorsque le joueur clique sur le message
     *                <br>Exemple: &quot;/help&quot;
     *                <br>
     */
    public static void sendSuggestCommandMessage(Player player, String message, String command){
        String input = "<click:suggest_command:/"+command+">"+message+"</click>";
        Component component = MiniMessage.miniMessage().deserialize(input);
        instance.adventure().player(player).sendMessage(component);
    }

    /**
     * La fonction sendSuggestCommandMessage envoie un message à un joueur spécifique avec un lien cliquable qui suggère une commande dans le chat.
     * @param sender Joueur qui exécute la commande/fonction
     *               <br>Exemple: &quot;CapitaineCat0&quot;
     *               <br>Le nom du joueur doit être valide.
     *               <br>
     * @param message Message à envoyer
     *                <br>Exemple: &quot;Hello world!&quot;
     *                <br>
     * @param command Commande qui sera suggérée lorsque le joueur clique sur le message
     *                <br>Exemple: &quot;/help&quot;
     *                <br>
     */
    public static void sendSuggestCommandMessage(CommandSender sender, String message, String command){
        String input = "<click:suggest_command:/"+command+">"+message+"</click>";
        Component component = MiniMessage.miniMessage().deserialize(input);
        instance.adventure().sender(sender).sendMessage(component);
    }

    /**
     * La fonction openBook ouvre un livre à tous les joueurs du serveur.
     * @param title Titre du livre
     *              <br>Exemple: &quot;Mon livre&quot;
     *              <br>
     * @param author Auteur du livre
     *               <br>Exemple: &quot;CapitaineCat0&quot;
     *               <br>Le nom de l'auteur doit être un nom de joueur valide.
     *               <br>
     * @param message Message qui sera affiché dans le livre
     *                <br>Exemple: &quot;Bonjour, je suis un livre !&quot;
     *                <br>Le message peut être une chaîne ou une liste de chaînes.
     */
    public static void openBook(String title, String author, Collection<Component> message) {
        Component bookTitle = Component.text(title);
        Component bookAuthor = Component.text(author);
        Book book = Book.book(bookTitle, bookAuthor, message);
        instance.adventure().players().openBook(book);
    }

    /**
     * La fonction openBook ouvre un livre à un joueur spécifique.
     * @param player Joueur qui recevra le livre
     *               <br>Exemple: &quot;CapitaineCat0&quot;
     *               <br>Le nom du joueur doit être valide.
     *               <br>Si le joueur n'est pas en ligne, le livre ne sera pas ouvert.
     *               <br>
     * @param title Titre du livre
     *              <br>Exemple: &quot;Mon livre&quot;
     *              <br>
     * @param author Auteur du livre
     *               <br>Exemple: &quot;CapitaineCat0&quot;
     *               <br>Le nom de l'auteur doit être un nom de joueur valide.
     *               <br>
     * @param message Message qui sera affiché dans le livre
     *                <br>Exemple: &quot;Bonjour, je suis un livre !&quot;
     *                <br>Le message peut être une chaîne ou une liste de chaînes.
     */
    public static void openBook(Player player, String title, String author, Collection<Component> message) {
        Component bookTitle = Component.text(title);
        Component bookAuthor = Component.text(author);
        Book book = Book.book(bookTitle, bookAuthor, message);
        instance.adventure().player(player).openBook(book);
    }

    /**
     * La fonction openBook ouvre un livre au joueur qui exécute la commande.
     * @param sender Joueur qui exécute la commande/fonction
     *               <br>Exemple: &quot;CapitaineCat0&quot;
     *               <br>Le nom du joueur doit être valide.
     *               <br>
     * @param title Titre du livre
     *              <br>Exemple: &quot;Mon livre&quot;
     *              <br>
     * @param author Auteur du livre
     *               <br>Exemple: &quot;CapitaineCat0&quot;
     *               <br>Le nom de l'auteur doit être un nom de joueur valide.
     *               <br>
     * @param message Message qui sera affiché dans le livre
     *                <br>Exemple: &quot;Bonjour, je suis un livre !&quot;
     *                <br>Le message peut être une chaîne ou une liste de chaînes.
     */
    public static void openBook(CommandSender sender, String title, String author, Collection<Component> message) {
        Component bookTitle = Component.text(title);
        Component bookAuthor = Component.text(author);
        Book book = Book.book(bookTitle, bookAuthor, message);
        instance.adventure().sender(sender).openBook(book);
    }

    /**
     * La fonction playAdventureSound joue un son au joueur spécifié à partir de l'API d'Adventure.
     * <br>
     * <br>Documentation d'Adventure:
     * <br><a href="https://docs.advntr.dev/sound.html">https://docs.advntr.dev/sound.html</a>
     * <br>
     * @param player Envoyer le son à un joueur spécifique
     *               <br>Exemple: &quot;CapitaineCat0&quot;
     *               <br>Le nom du joueur doit être valide.
     *               <br>
     * @param sound Son que vous voulez jouer
     *              <br>Exemple: &quot;minecraft:entity.player.levelup&quot;
     *              <br>Le son doit être un son valide.
     *              <br>
     * @param volume Volume du son
     *               <br>Exemple: &quot;1f&quot;
     *               <br>Le volume doit être un nombre valide.
     *               <br>
     * @param pitch Hauteur du son
     *              <br>Exemple: &quot;1f&quot;
     *              <br>Le pitch doit être un nombre valide.
     *              <br>
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
     * La fonction playAdventureSound joue un son au joueur qui exécute la commande à partir de l'API d'Adventure.
     * <br>
     * <br>Documentation d'Adventure:
     * <br><a href="https://docs.advntr.dev/sound.html">https://docs.advntr.dev/sound.html</a>
     * <br>
     * @param sender Joueur qui exécute la commande/fonction
     *               <br>Exemple: &quot;CapitaineCat0&quot;
     *               <br>Le nom du joueur doit être valide.
     *               <br>
     * @param sound Son que vous voulez jouer
     *              <br>Exemple: &quot;minecraft:entity.player.levelup&quot;
     *              <br>Le son doit être un son valide.
     *              <br>
     * @param volume Volume du son
     *               <br>Exemple: &quot;1f&quot;
     *               <br>Le volume doit être un nombre valide.
     *               <br>
     * @param pitch Hauteur du son
     *              <br>Exemple: &quot;1f&quot;
     *              <br>Le pitch doit être un nombre valide.
     *              <br>
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
     * La fonction playBukkitSound joue un son au joueur spécifié à partir de l'API Bukkit.
     * <br>
     * <br>Documentation Bukkit:
     * <br><a href="https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/Sound.html">https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/Sound.html</a>
     * <br>
     * @param player Envoyer le son à un joueur spécifique
     *               <br>Exemple: &quot;CapitaineCat0&quot;
     *               <br>Le nom du joueur doit être valide.
     *               <br>
     * @param sound Son que vous voulez jouer
     *              <br>Exemple: &quot;ENTITY_PLAYER_LEVELUP&quot;
     *              <br>Le son doit être un son valide.
     *              <br>
     * @param volume Volume du son
     *               <br>Exemple: &quot;1f&quot;
     *               <br>Le volume doit être un nombre valide.
     *               <br>
     * @param pitch Hauteur du son
     *              <br>Exemple: &quot;1f&quot;
     *              <br>Le pitch doit être un nombre valide.
     *              <br>
     */

    private static void playBukkitSound(@NotNull Player player, org.bukkit.Sound sound, float volume, float pitch){
        try{
            player.getWorld().playSound(player.getLocation(),sound,volume, pitch);
        }catch(Exception e){
            sendMessage(player, "&cAn internal error was come when i try to play sound! Please contact an administrator !");
            sendEventExceptionMessage(e, "Bukkit playSound");
        }
    }

    /**
     * La fonction playBukkitSound joue un son au joueur qui exécute la commande à partir de l'API Bukkit.
     * <br>
     * <br>Documentation Bukkit:
     * <br><a href="https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/Sound.html">https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/Sound.html</a>
     * <br>
     * @param sender Joueur qui exécute la commande/fonction
     *               <br>Exemple: &quot;CapitaineCat0&quot;
     *               <br>Le nom du joueur doit être valide.
     *               <br>
     * @param sound Son que vous voulez jouer
     *              <br>Exemple: &quot;ENTITY_PLAYER_LEVELUP&quot;
     *              <br>Le son doit être un son valide.
     *              <br>
     * @param volume Volume du son
     *               <br>Exemple: &quot;1f&quot;
     *               <br>Le volume doit être un nombre valide.
     *               <br>
     * @param pitch Hauteur du son
     *              <br>Exemple: &quot;1f&quot;
     *              <br>Le pitch doit être un nombre valide.
     *              <br>
     */
    private static void playBukkitSound(CommandSender sender, Sound sound, float volume, float pitch){
        Player player = (Player) sender;
        try{
            player.getWorld().playSound(player.getLocation(), String.valueOf(sound),volume, pitch);
        }catch(Exception e){
            sendMessage(player, "&cAn internal error was come when i try to play sound! Please contact an administrator !");
            sendEventExceptionMessage(e, "Bukkit playSound");
        }
    }

    /**
     * La fonction playSoundIfEnabled joue un son au joueur spécifié
     * <br>si &quot;soundEnabled()&quot; retourne true.
     * <br>
     * <br>Adventure sounds:
     * <br><a href="https://docs.advntr.dev/sound.html">https://docs.advntr.dev/sound.html</a>
     *
     * @param player Envoyer le son à un joueur spécifique
     *               <br>Exemple: &quot;CapitaineCat0&quot;
     *               <br>Le nom du joueur doit être valide.
     *               <br>
     * @param sound Son que vous voulez jouer
     *              <br>Exemple: &quot;minecraft:entity.player.levelup&quot;
     *              <br>Le son doit être un son valide.
     */
    public static void playSoundIfEnabled(Player player, Sound sound){
        if(soundEnabled()){
            playBukkitSound(player, sound, 1f, 1f);
        }
    }

    /**
     * La fonction playSoundIfEnabled joue un son au joueur spécifié
     * <br>si &quot;soundEnabled()&quot; retourne true.
     * <br>
     * <br>Adventure sounds:
     * <br><a href="https://docs.advntr.dev/sound.html">https://docs.advntr.dev/sound.html</a>
     *
     * @param player Envoyer le son à un joueur spécifique
     *               <br>Exemple: &quot;CapitaineCat0&quot;
     *               <br>Le nom du joueur doit être valide.
     *               <br>
     * @param sound Son que vous voulez jouer
     *              <br>Exemple: &quot;minecraft:entity.player.levelup&quot;
     *              <br>Le son doit être un son valide.
     *              <br>
     * @param volume Volume du son
     *               <br>Exemple: &quot;1f&quot;
     *               <br>Le volume doit être un nombre valide.
     */
    public static void playSoundIfEnabled(Player player, Sound sound, float volume){
        if(soundEnabled()){
            playBukkitSound(player, sound, volume, 1f);
        }
    }

    /**
     * La fonction playSoundIfEnabled joue un son au joueur spécifié
     * <br>si &quot;soundEnabled()&quot; retourne true.
     * <br>
     * <br>Adventure sounds:
     * <br><a href="https://docs.advntr.dev/sound.html">https://docs.advntr.dev/sound.html</a>
     * @param player Envoyer le son à un joueur spécifique
     *               <br>Exemple: &quot;CapitaineCat0&quot;
     *               <br>Le nom du joueur doit être valide.
     *               <br>
     * @param sound Son que vous voulez jouer
     *              <br>Exemple: &quot;minecraft:entity.player.levelup&quot;
     *              <br>Le son doit être un son valide.
     *              <br>
     * @param volume Volume du son
     *               <br>Exemple: &quot;1f&quot;
     *               <br>Le volume doit être un nombre valide.
     *               <br>
     * @param pitch Hauteur du son
     *              <br>Exemple: &quot;1f&quot;
     *              <br>Le pitch doit être un nombre valide.
     *              <br>
     */
    public static void playSoundIfEnabled(Player player, Sound sound, float volume, float pitch){
        if(soundEnabled()){
            playBukkitSound(player, sound, volume, pitch);
        }
    }

    /**
     * La fonction playSoundIfEnabled joue un son au joueur qui exécute la commande
     * <br>si &quot;soundEnabled()&quot; retourne true.
     * <br>
     * <br>Adventure sounds:
     * <br><a href="https://docs.advntr.dev/sound.html">https://docs.advntr.dev/sound.html</a>
     *
     * @param sender Joueur qui exécute la commande/fonction
     *               <br>Exemple: &quot;CapitaineCat0&quot;
     *               <br>Le nom du joueur doit être valide.
     *               <br>
     * @param sound Son que vous voulez jouer
     *              <br>Exemple: &quot;minecraft:entity.player.levelup&quot;
     *              <br>Le son doit être un son valide.
     */
    public static void playSoundIfEnabled(CommandSender sender, Sound sound){
        if(soundEnabled()){
            playBukkitSound(sender, sound, 1f, 1f);
        }
    }

    /**
     * La fonction playSoundIfEnabled joue un son au joueur qui exécute la commande
     * <br>si &quot;soundEnabled()&quot; retourne true.
     * <br>
     * <br>Adventure sounds:
     * <br><a href="https://docs.advntr.dev/sound.html">https://docs.advntr.dev/sound.html</a>
     *
     * @param sender Joueur qui exécute la commande/fonction
     *               <br>Exemple: &quot;CapitaineCat0&quot;
     *               <br>Le nom du joueur doit être valide.
     *               <br>
     * @param sound Son que vous voulez jouer
     *              <br>Exemple: &quot;minecraft:entity.player.levelup&quot;
     *              <br>Le son doit être un son valide.
     *              <br>
     * @param volume Volume du son
     *               <br>Exemple: &quot;1f&quot;
     *               <br>Le volume doit être un nombre valide.
     */
    public static void playSoundIfEnabled(CommandSender sender, Sound sound, float volume){
        if(soundEnabled()){
            playBukkitSound(sender, sound, volume, 1f);
        }
    }

    /**
     * La fonction playSoundIfEnabled joue un son au joueur qui exécute la commande
     * <br>si &quot;soundEnabled()&quot; retourne true.
     * <br>
     * <br>Adventure sounds:
     * <br><a href="https://docs.advntr.dev/sound.html">https://docs.advntr.dev/sound.html</a>
     *
     * @param sender Joueur qui exécute la commande/fonction
     *               <br>Exemple: &quot;CapitaineCat0&quot;
     *               <br>Le nom du joueur doit être valide.
     *               <br>
     * @param sound Son que vous voulez jouer
     *              <br>Exemple: &quot;minecraft:entity.player.levelup&quot;
     *              <br>Le son doit être un son valide.
     *              <br>
     * @param volume Volume du son
     *               <br>Exemple: &quot;1f&quot;
     *               <br>Le volume doit être un nombre valide.
     *               <br>
     * @param pitch Hauteur du son
     *              <br>Exemple: &quot;1f&quot;
     *              <br>Le pitch doit être un nombre valide.
     *              <br>
     */
    public static void playSoundIfEnabled(CommandSender sender, Sound sound, float volume, float pitch){
        if(soundEnabled()){
            playBukkitSound(sender, sound, volume, pitch);
        }
    }

    /**
     * La fonction playSoundIfEnabled joue un son au joueur spécifié
     * <br>si &quot;soundEnabled()&quot; retourne true.
     * <br>
     * <br>Bukkit Sounds:
     * <br><a href="https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/Sound.html">https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/Sound.html</a>
     * @param player Envoyer le son à un joueur spécifique
     *               <br>Exemple: &quot;CapitaineCat0&quot;
     *               <br>Le nom du joueur doit être valide.
     *               <br>
     * @param sound Son que vous voulez jouer
     *              <br>Exemple: &quot;ENTITY_PLAYER_LEVELUP&quot;
     *              <br>Le son doit être un son valide.
     *              <br>
     */
    public static void playSoundIfEnabled(Player player, org.bukkit.Sound sound){
        if(soundEnabled()){
            playBukkitSound(player, sound, 1f, 1f);
        }
    }

    /**
     * La fonction playSoundIfEnabled joue un son au joueur spécifié
     * <br>si &quot;soundEnabled()&quot; retourne true.
     * <br>
     * <br>Bukkit Sounds:
     * <br><a href="https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/Sound.html">https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/Sound.html</a>
     * @param player Envoyer le son à un joueur spécifique
     *               <br>Exemple: &quot;CapitaineCat0&quot;
     *               <br>Le nom du joueur doit être valide.
     *               <br>
     * @param sound Son que vous voulez jouer
     *              <br>Exemple: &quot;ENTITY_PLAYER_LEVELUP&quot;
     *              <br>Le son doit être un son valide.
     *              <br>
     * @param volume Volume du son
     *               <br>Exemple: &quot;1f&quot;
     *               <br>Le volume doit être un nombre valide.
     */
    public static void playSoundIfEnabled(Player player, org.bukkit.Sound sound, float volume){
        if(soundEnabled()){
            playBukkitSound(player, sound, volume, 1f);
        }
    }

    /**
     * La fonction playSoundIfEnabled joue un son au joueur spécifié
     * <br>si &quot;soundEnabled()&quot; retourne true.
     * <br>
     * <br>Bukkit Sounds:
     * <br><a href="https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/Sound.html">https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/Sound.html</a>
     * @param player Envoyer le son à un joueur spécifique
     *               <br>Exemple: &quot;CapitaineCat0&quot;
     *               <br>Le nom du joueur doit être valide.
     *               <br>
     * @param sound Son que vous voulez jouer
     *              <br>Exemple: &quot;ENTITY_PLAYER_LEVELUP&quot;
     *              <br>Le son doit être un son valide.
     *              <br>
     * @param volume Volume du son
     *               <br>Exemple: &quot;1f&quot;
     *               <br>Le volume doit être un nombre valide.
     *               <br>
     * @param pitch Hauteur du son
     *              <br>Exemple: &quot;1f&quot;
     *              <br>Le pitch doit être un nombre valide.
     *              <br>
     */
    public static void playSoundIfEnabled(Player player, org.bukkit.Sound sound, float volume, float pitch){
        if(soundEnabled()){
            playBukkitSound(player, sound, volume, pitch);
        }
    }

    /**
     * La fonction playSoundIfEnabled joue un son au joueur qui exécute la commande
     * <br>si &quot;soundEnabled()&quot; retourne true.
     * <br>
     * <br>Bukkit Sounds:
     * <br><a href="https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/Sound.html">https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/Sound.html</a>
     * @param sender Joueur qui exécute la commande/fonction
     *               <br>Exemple: &quot;CapitaineCat0&quot;
     *               <br>Le nom du joueur doit être valide.
     *               <br>
     * @param sound Son que vous voulez jouer
     *              <br>Exemple: &quot;ENTITY_PLAYER_LEVELUP&quot;
     *              <br>Le son doit être un son valide.
     */
    public static void playSoundIfEnabled(CommandSender sender, org.bukkit.Sound sound){
        if(soundEnabled()){
            playBukkitSound((Player) sender, sound, 1f, 1f);
        }
    }

    /**
     * La fonction playSoundIfEnabled joue un son au joueur qui exécute la commande
     * <br>si &quot;soundEnabled()&quot; retourne true.
     * <br>
     * <br>Bukkit Sounds:
     * <br><a href="https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/Sound.html">https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/Sound.html</a>
     * @param sender Joueur qui exécute la commande/fonction
     *               <br>Exemple: &quot;CapitaineCat0&quot;
     *               <br>Le nom du joueur doit être valide.
     *               <br>
     * @param sound Son que vous voulez jouer
     *              <br>Exemple: &quot;ENTITY_PLAYER_LEVELUP&quot;
     *              <br>Le son doit être un son valide.
     *              <br>
     * @param volume Volume du son
     *               <br>Exemple: &quot;1f&quot;
     *               <br>Le volume doit être un nombre valide.
     */
    public static void playSoundIfEnabled(CommandSender sender, org.bukkit.Sound sound, float volume){
        if(soundEnabled()){
            playBukkitSound((Player) sender, sound, volume, 1f);
        }
    }

    /**
     * La fonction playSoundIfEnabled joue un son au joueur qui exécute la commande
     * <br>si &quot;soundEnabled()&quot; retourne true.
     * <br>
     * <br>Bukkit Sounds:
     * <br><a href="https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/Sound.html">https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/Sound.html</a>
     * @param sender Joueur qui exécute la commande/fonction
     *               <br>Exemple: &quot;CapitaineCat0&quot;
     *               <br>Le nom du joueur doit être valide.
     *               <br>
     * @param sound Son que vous voulez jouer
     *              <br>Exemple: &quot;ENTITY_PLAYER_LEVELUP&quot;
     *              <br>Le son doit être un son valide.
     *              <br>
     * @param volume Volume du son
     *               <br>Exemple: &quot;1f&quot;
     *               <br>Le volume doit être un nombre valide.
     *               <br>
     * @param pitch Hauteur du son
     *              <br>Exemple: &quot;1f&quot;
     *              <br>Le pitch doit être un nombre valide.
     *              <br>
     */
    public static void playSoundIfEnabled(CommandSender sender, org.bukkit.Sound sound, float volume, float pitch){
        if(soundEnabled()){
            playBukkitSound((Player) sender, sound, volume, pitch);
        }
    }

    /**
     * La fonction soundEnabled retourne une valeur boolean si les sons sont activés dans le fichier de configuration.
     * <br>Si les sons sont activés, la fonction retourne true.
     * <br>Si les sons sont désactivés, la fonction retourne false.
     * <br>
     * <br>Par défaut, les sons sont activés.
     * <br>
     * <br>Vous pouvez modifier cette valeur dans le fichier de configuration.
     * <br>
     * <br>Défaut:
     * <br>enable-command-sounds: true
     * <br>
     * <br>Si vous voulez désactiver les sons, vous devez mettre la valeur sur false.
     * <br>
     * <br>Exemple:
     * <br>enable-command-sounds: false
     * <br>
     *
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
     * La fonction stopSound arrête tous les sons qui sont actuellement en cours de lecture pour un joueur spécifique.
     * @param player Envoyer le son à un joueur spécifique
     *               <br>Exemple: &quot;CapitaineCat0&quot;
     *               <br>Le nom du joueur doit être valide.
     */
    public static void stopSound(Player player){
        instance.adventure().player(player).stopSound(SoundStop.all());
    }

    /**
     * La fonction stopSound arrête tous les sons qui sont actuellement en cours de lecture pour tous les joueurs.
     */
    public static void stopSound(){
        instance.adventure().players().stopSound(SoundStop.all());
    }

    /** La fonction sendTitle envoie un titre au joueur qui exécute la commande.
     *
     * @param sender Joueur qui exécute la commande/fonction
     *               <br>Exemple: &quot;CapitaineCat0&quot;
     *               <br>
     * @param commandName Nom de la commande
     *                    <br>Exemple: &quot;help&quot;
     *                    <br>
     * @param message Message envoyé au joueur
     *                <br>Exemple: &quot;Voici la liste des commandes&quot;
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
        final Component subtitle = Component.text(message.replace("{prefix}", PLUGIN_PREFIX.getMessage()));
        final Title title = Title.title(mainTitle, subtitle, times);
        instance.adventure().sender(sender).showTitle(title);
    }

    /**
     * La fonction sendTitle envoie un titre à tous les joueurs du serveur.
     * @param title Titre
     *              <br>Exemple: &quot;Mon titre&quot;
     *              <br>
     * @param message Message envoyé au joueur
     *                <br>Exemple: &quot;Voici la liste des commandes&quot;
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
        final Component mainTitle = Component.text(title);
        final Component subtitle = Component.text(message.replace("{prefix}", PLUGIN_PREFIX.getMessage()));
        final Title finalTitle = Title.title(mainTitle, subtitle, times);
        instance.adventure().players().showTitle(finalTitle);
    }

    /**
     * La fonction sendTitle envoie un titre au joueur spécifié.
     * @param player Envoyer le titre à un joueur spécifique
     *               <br>Exemple: &quot;CapitaineCat0&quot;
     *               <br>
     * @param title Titre
     *              <br>Exemple: &quot;Mon titre&quot;
     *              <br>
     * @param message Message envoyé au joueur
     *                <br>Exemple: &quot;Voici la liste des commandes&quot;
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
        final Component mainTitle = Component.text(title);
        final Component subtitle = Component.text(message.replace("{prefix}", PLUGIN_PREFIX.getMessage()));
        final Title finalTitle = Title.title(mainTitle, subtitle, times);
        instance.adventure().player(player).showTitle(finalTitle);
    }

    /**
     * La fonction sendBossBar envoie une barre de boss au joueur qui exécute la commande.
     * <br>
     * <br>Documentation:
     * <br><a href="https://docs.adventure.kyori.net/bossbar.html">https://docs.adventure.kyori.net/bossbar.html</a>
     * <br>
     * @param sender Joueur qui exécute la commande/fonction
     *               <br>Exemple: &quot;CapitaineCat0&quot;
     *               <br>
     * @param message Message envoyé au joueur
     *                <br>Exemple: &quot;Voici la liste des commandes&quot;
     *                <br>
     */
    private static void sendBossBar(CommandSender sender, @NotNull String message) {
        final Component name = Component.text(message.replace("{prefix}", PLUGIN_PREFIX.getMessage()));
        finalBossbar = BossBar.bossBar(name, (float) 1, BossBar.Color.GREEN, BossBar.Overlay.NOTCHED_20);
        instance.adventure().sender(sender).showBossBar(finalBossbar);
    }

    /**
     * La fonction sendBossBar envoie une barre de boss à tous les joueurs du serveur.
     * <br>
     * <br>Documentation:
     * <br><a href="https://docs.adventure.kyori.net/bossbar.html">https://docs.adventure.kyori.net/bossbar.html</a>
     * <br>
     * @param progress Définir la progression de la barre de boss
     *                 <br>Exemple: &quot;1f&quot;
     *                 <br>La progression doit être un nombre valide.
     *                 <br>
     * @param color Définir la couleur de la barre de boss
     *              <br>Exemple: &quot;BossBar.Color.GREEN&quot;
     *              <br>La couleur doit être une couleur valide.
     *              <br>
     * @param overlay Définir l'overlay de la barre de boss
     *                <br>Exemple: &quot;BossBar.Overlay.NOTCHED_20&quot;
     *                <br>L'overlay doit être un overlay valide.
     *                <br>
     * @param message Message envoyé au joueur
     *                <br>Exemple: &quot;Voici la liste des commandes&quot;
     *                <br>
     */
    public static void sendBossBar(float progress, BossBar.Color color, BossBar.Overlay overlay, @NotNull String message) {
        final Component name = Component.text(message.replace("{prefix}", PLUGIN_PREFIX.getMessage()));
        finalBossbar = BossBar.bossBar(name, progress, color, overlay);
        instance.adventure().players().showBossBar(finalBossbar);
    }

    /**
     * La fonction sendBossBar envoie une barre de boss au joueur spécifié.
     * <br>
     * <br>Documentation:
     * <br><a href="https://docs.adventure.kyori.net/bossbar.html">https://docs.adventure.kyori.net/bossbar.html</a>
     * <br>
     * @param player Envoyer la barre de boss à un joueur spécifique
     *               <br>Exemple: &quot;CapitaineCat0&quot;
     *               <br>
     * @param progress Définir la progression de la barre de boss
     *                 <br>Exemple: &quot;1f&quot;
     *                 <br>La progression doit être un nombre valide.
     *                 <br>
     * @param color Définir la couleur de la barre de boss
     *              <br>Exemple: &quot;BossBar.Color.GREEN&quot;
     *              <br>La couleur doit être une couleur valide.
     *              <br>
     * @param overlay Définir l'overlay de la barre de boss
     *                <br>Exemple: &quot;BossBar.Overlay.NOTCHED_20&quot;
     *                <br>L'overlay doit être un overlay valide.
     *                <br>
     * @param message Message envoyé au joueur
     *                <br>Exemple: &quot;Voici la liste des commandes&quot;
     *                <br>
     */
    public static void sendBossBar(Player player, float progress, BossBar.Color color, BossBar.Overlay overlay, @NotNull String message) {
        final Component name = Component.text(message.replace("{prefix}", PLUGIN_PREFIX.getMessage()));
        finalBossbar = BossBar.bossBar(name, progress, color, overlay);
        instance.adventure().player(player).showBossBar(finalBossbar);
    }

    /**
     * La fonction sendActionBar envoie un message dans la barre d'action des joueurs.
     * <br>
     * <br>Documentation:
     * <br><a href="https://docs.adventure.kyori.net/text.html">https://docs.adventure.kyori.net/text.html</a>
     * <br>
     * @param message Message envoyé au joueur
     *                <br>Exemple: &quot;Voici la liste des commandes&quot;
     */
    public static void sendActionBar(@NotNull String message){
        final TextComponent component = Component.text(message.replace("{prefix}", ""));
        instance.adventure().players().sendActionBar(component);
    }

    /**
     * La fonction sendActionBar envoie un message dans la barre d'action du joueur.
     * <br>
     * <br>Documentation:
     * <br><a href="https://docs.adventure.kyori.net/text.html">https://docs.adventure.kyori.net/text.html</a>
     * <br>
     * @param player Envoyer le message à un joueur spécifique
     *               <br>Exemple: &quot;CapitaineCat0&quot;
     *               <br>
     * @param message Message envoyé au joueur
     *                <br>Exemple: &quot;Voici la liste des commandes&quot;
     */
    public static void sendActionBar(Player player, @NotNull String message){
        final TextComponent component = Component.text(message.replace("{prefix}", ""));
        instance.adventure().player(player).sendActionBar(component);
    }

    /**
     * La fonction sendActionBar envoie un message dans la barre d'action du joueur qui exécute la commande.
     * <br>
     * <br>Documentation:
     * <br><a href="https://docs.adventure.kyori.net/text.html">https://docs.adventure.kyori.net/text.html</a>
     * <br>
     * @param sender Joueur qui exécute la commande/fonction
     *               <br>Exemple: &quot;CapitaineCat0&quot;
     *               <br>
     * @param message Message envoyé au joueur
     *                <br>Exemple: &quot;Voici la liste des commandes&quot;
     */
    public static void sendActionBar(CommandSender sender, @NotNull String message){
        final TextComponent component = Component.text(message.replace(PLUGIN_PREFIX.getMessage(), ""));
        instance.adventure().sender(sender).sendActionBar(component);
    }

    /**
     * La fonction sendTablist affiche la liste des joueurs connectés à un joueur.
     * <br>
     * <br>Documentation:
     * <br><a href="https://docs.adventure.kyori.net/text.html">https://docs.adventure.kyori.net/text.html</a>
     * <br>
     * @param player Envoyer la liste des joueurs à un joueur spécifique
     *               <br>Exemple: &quot;CapitaineCat0&quot;
     *               <br>
     * @param header En-tête de la liste des joueurs
     *               <br>Exemple: &quot;Mon serveur&quot;
     *               <br>
     * @param footer Pied de la liste des joueurs
     *               <br>Exemple: &quot;Serveur de CapitaineCat0&quot;
     *               <br>
     */
    public static void sendTablist(@NotNull Player player, Component header, Component footer){
        player.sendPlayerListHeaderAndFooter(header, footer);
    }



    /**
     * La fonction sendCommandExceptionMessage envoie un message à la console lorsqu'une exception se produit dans une commande.
     * <br>
     * <br>Exemple:
     * <br>Caused by: /help
     * <br>Java Class: java.lang.NullPointerException
     * <br>Error: null
     * <br>Line: fr.capitainecat.MultiCommands.commands.HelpCommand.onCommand(HelpCommand.java:30)
     * <br>
     * @param exception Obtenir le message d'erreur et la ligne
     *                  <br>
     * @param command Afficher le nom de la commande qui a causé cette erreur
     *                <br>Exemple: &quot;help&quot;
     */
    public static void sendCommandExceptionMessage(@NotNull Exception exception, String command){
        // Obtenir l'erreur
        String error = exception.getMessage();
        String errorClass = exception.getClass().toString();
        //StackTraceElement[] errorLine = exception.getStackTrace();
        String stackTrace = Arrays.toString(exception.getStackTrace());
        sendConsoleMessage("&e----&6Multi&5Commands &cERROR&e----");
        sendConsoleMessage("&cCaused by: &e/"+command);
        sendConsoleMessage("&cJava Class: &e"+errorClass);
        sendConsoleMessage("&cError: &e"+error);
        //sendConsoleMessage("&cLine: &e"+errorLine.replace("MultiCommands.jar//", ""));
        sendConsoleMessage("&cStackTrace: &e"+stackTrace.replace("MultiCommands.jar//", ""));
        sendConsoleMessage("&e----&6Multi&5Commands &cERROR&e----");
    }

    /**
     * La fonction sendEventExceptionMessage envoie un message à la console lorsqu'une exception se produit dans un événement.
     * <br>
     * <br>Exemple:
     * <br>Caused by: Bukkit playSound
     * <br>Java Class: java.lang.NullPointerException
     * <br>Error: null
     * <br>Line: fr.capitainecat.MultiCommands.commands.HelpCommand.onCommand(HelpCommand.java:30)
     * <br>
     * @param exception Obtenir le message d'erreur et la ligne
     *                  <br>
     * @param event Afficher le nom de l'événement qui a causé cette erreur
     *              <br>Exemple: &quot;Bukkit playSound&quot;
     */
    public static void sendEventExceptionMessage(@NotNull Exception exception, String event){
        // Obtenir la ligne de l'erreur
        StackTraceElement errorLine = exception.getStackTrace()[0];
        sendConsoleMessage("&e----&6Multi&5Commands &cERROR&e----");
        sendConsoleMessage("&cCaused by: &e"+event);
        sendConsoleMessage("&cJava Class: &e"+exception.getClass());
        sendConsoleMessage("&cError: &e"+exception.getMessage());
        sendConsoleMessage("&cLine: &e"+errorLine.toString().replace("MultiCommands.jar//", ""));
        sendConsoleMessage("&e----&6Multi&5Commands &cERROR&e----");
    }

    /**
     * La fonction sendEventExceptionMessage envoie un message à la console lorsqu'une exception se produit dans un événement.
     * <br>
     * <br>Exemple:
     * <br>Caused by: Bukkit playSound
     * <br>Java Class: java.lang.NullPointerException
     * <br>Error: null
     * <br>Line: fr.capitainecat.MultiCommands.commands.HelpCommand.onCommand(HelpCommand.java:30)
     * <br>
     * @param exception Obtenir le message d'erreur et la ligne
     *                  <br>
     * @param event Afficher le nom de l'événement qui a causé cette erreur
     *              <br>Exemple: &quot;Bukkit playSound&quot;
     */
    public static void sendErrorExceptionMessage(@NotNull Exception exception, String event){
        sendConsoleMessage("&e----&6Multi&5Commands &cERROR&e----");
        sendConsoleMessage("&cCaused by: &e"+event);
        sendConsoleMessage("&cJava Class: &e"+exception.getClass());
        sendConsoleMessage("&cError: &e"+exception.getMessage());
        sendConsoleMessage("&e----&6Multi&5Commands &cERROR&e----");
    }
}
