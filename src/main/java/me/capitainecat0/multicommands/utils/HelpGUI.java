package me.capitainecat0.multicommands.utils;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.commands.Gamemode;
import me.capitainecat0.multicommands.utils.*;
import me.capitainecat0.multimaintenance.MultiMaintenance;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.Collections;
import java.util.Objects;
import java.util.UUID;

import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.Messenger.FEED_ALREADY;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;


public class HelpGUI extends GUICreator{
    public HelpGUI(){
        super(54, "&6&lMultiCommands &9V."+ MultiCommands.getInstance().getDescription().getVersion() + " &cHelp");
        /**
         * Exemple du menu:
         * ##.....##
         * #.@@@@@.#
         * ..@@@@@..
         * #.@@@@@.#
         * ##.....##
         *
         * # -> Vitres dans les angles
         * @ → Items au centre
         */
        setItem(0, ItemCreator.create(Material.BLACK_STAINED_GLASS_PANE, " "));
        setItem(1, ItemCreator.create(Material.BLACK_STAINED_GLASS_PANE, " "));
        setItem(9, ItemCreator.create(Material.BLACK_STAINED_GLASS_PANE, " "));

        setItem(7, ItemCreator.create(Material.BLACK_STAINED_GLASS_PANE, " "));
        setItem(8, ItemCreator.create(Material.BLACK_STAINED_GLASS_PANE, " "));
        setItem(17, ItemCreator.create(Material.BLACK_STAINED_GLASS_PANE, " "));

        setItem(36, ItemCreator.create(Material.BLACK_STAINED_GLASS_PANE, " "));
        setItem(45, ItemCreator.create(Material.BLACK_STAINED_GLASS_PANE, " "));
        setItem(46, ItemCreator.create(Material.BLACK_STAINED_GLASS_PANE, " "));

        setItem(44, ItemCreator.create(Material.BLACK_STAINED_GLASS_PANE, " "));
        setItem(52, ItemCreator.create(Material.BLACK_STAINED_GLASS_PANE, " "));
        setItem(53, ItemCreator.create(Material.BLACK_STAINED_GLASS_PANE, " "));

        setItem(10, ItemCreator.create(Material.RED_BED, "&3AFK", Collections.singletonList("&7Permet de gérer le mode AFK")), (player, event) -> {
            GUICreator gui = GUICreator.getGUI(player);
            ItemStack item = event.getCurrentItem();
            assert item != null;
            if(!player.hasPermission(Perms.AFK_PERM.getPermission()) && item.getType() == Material.RED_BED){
                if(soundEnabled()){
                    playSound(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                }
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&3AFK", Collections.singletonList(CMD_NO_PERM.getMessage())));
            }
            else{
                if(item.getType() != Material.BARRIER){
                    if(AFKHandler.getInstance().isAFK(player)) {
                        AFKHandler.getInstance().toggleAFK(player);
                    } else {
                        AFKHandler.getInstance().toggleAFK(player);
                    }
                    close(player);
                }
            }
        });

        setItem(11, ItemCreator.create(Material.BELL, "&3Alert", Collections.singletonList("&7Permet d'envoyer un message d'alerte")), (player, event) -> {
            GUICreator gui = GUICreator.getGUI(player);
            ItemStack item = event.getCurrentItem();
            assert item != null;
            if(!player.hasPermission(Perms.ALERT_PERM.getPermission()) && item.getType() == Material.BELL){
                if(soundEnabled()){
                    playSound(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                }
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&3Alert", Collections.singletonList(CMD_NO_PERM.getMessage())));
            }
            else {
                if(item.getType() != Material.BARRIER) {
                    if(soundEnabled()){
                        playSound(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                    }
                    getMsgSendConfig(player, "alert", CMD_NO_ARGS.getMessage().replace("%cmd%", "alert").replace("%args%", "<message>"));
                    close(player);
                }
            }
        });

        setItem(12, ItemCreator.create(Material.LECTERN, "&3Broadcast", Collections.singletonList("&7Permet d'envoyer un broadcast")), (player, event) -> {
            GUICreator gui = GUICreator.getGUI(player);
            ItemStack item = event.getCurrentItem();
            assert item != null;
            if(!player.hasPermission(Perms.BROADCAST_PERM.getPermission()) && item.getType() == Material.LECTERN){
                if(soundEnabled()){
                    playSound(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                }
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&3Broadcast", Collections.singletonList(CMD_NO_PERM.getMessage())));
            }
            else {
                if(item.getType() != Material.BARRIER) {
                    if(soundEnabled()){
                        playSound(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                    }
                    getMsgSendConfig(player, "broadcast", CMD_NO_ARGS.getMessage().replace("%cmd%", "broadcast").replace("%args%", "<message>"));
                    close(player);
                }
            }
        });

        setItem(13, ItemCreator.create(Material.LAVA_BUCKET, "&3Clear-Inventory", Collections.singletonList("&7Permet de vider votre inventaire")), (player, event) -> {
            GUICreator gui = GUICreator.getGUI(player);
            ItemStack item = event.getCurrentItem();
            assert item != null;
            if(!player.hasPermission(Perms.CLEARINVENTORY_PERM_SELF.getPermission()) && item.getType() == Material.LAVA_BUCKET){
                if(soundEnabled()){
                    playSound(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                }
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&3Clear-Inventory", Collections.singletonList(CMD_NO_PERM.getMessage())));
            }
            else {
                if(item.getType() != Material.BARRIER) {
                    if(soundEnabled()){
                        playSound(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                    }
                    getMsgSendConfig(player, "ClearInventory", CLEARINV_SELF_DONE.getMessage());
                    player.getInventory().clear();
                    close(player);
                }
            }
        });

        setItem(14, ItemCreator.create(Material.CRAFTING_TABLE, "&3Craft", Collections.singletonList("&7Permet d'ouvrir l'établi")), (player, event) -> {
            GUICreator gui = GUICreator.getGUI(player);
            ItemStack item = event.getCurrentItem();
            assert item != null;
            if(!player.hasPermission(Perms.CRAFT_PERM.getPermission()) && item.getType() == Material.CRAFTING_TABLE){
                if(soundEnabled()){
                    playSound(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                }
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&3Craft", Collections.singletonList(CMD_NO_PERM.getMessage())));
            }
            else {
                if(item.getType() != Material.BARRIER) {
                    if(soundEnabled()){
                        playSound(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                    }
                    player.openWorkbench(player.getLocation(), true);
                }
            }
        });

        setItem(15, ItemCreator.create(Material.ENDER_CHEST, "&3Ender-Chest", Collections.singletonList("&7Permet d'ouvrir votre enderchest")), (player, event) -> {
            GUICreator gui = GUICreator.getGUI(player);
            ItemStack item = event.getCurrentItem();
            assert item != null;
            if(!player.hasPermission(Perms.ENDERCHEST_PERM_SELF.getPermission()) && item.getType() == Material.ENDER_CHEST){
                if(soundEnabled()){
                    playSound(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                }
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&3Ender-Chest", Collections.singletonList(CMD_NO_PERM.getMessage())));
            }
            else {
                if(item.getType() != Material.BARRIER) {
                    if(soundEnabled()){
                        playSound(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                    }
                    player.openInventory(player.getEnderChest());
                }
            }
        });

        setItem(16, ItemCreator.create(Material.COOKED_CHICKEN, "&3Feed", Collections.singletonList("&7Permet de vous nourrir")), (player, event) -> {
            GUICreator gui = GUICreator.getGUI(player);
            ItemStack item = event.getCurrentItem();
            assert item != null;
            if(!player.hasPermission(Perms.FEED_PERM_SELF.getPermission()) && item.getType() == Material.COOKED_CHICKEN){
                if(soundEnabled()){
                    playSound(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                }
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&3Feed", Collections.singletonList(CMD_NO_PERM.getMessage())));
            }
            else {
                if(item.getType() != Material.BARRIER) {
                    if(player.getFoodLevel() != 20){
                        player.setFoodLevel(20);
                        if(soundFeedHealEnabled()){
                            playSound(player, Sound.ENTITY_GENERIC_EAT, 1f, 1f);
                        }
                        getMsgSendConfig(player, "feed", FEED_SELF.getMessage());
                    }else{
                        if(soundEnabled()){
                            playSound(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                        }
                        getMsgSendConfig(player, "feed", FEED_ALREADY.getMessage());
                    }
                    close(player);
                }
            }
        });

        setItem(19, ItemCreator.create(Material.FEATHER, "&3Fly", Collections.singletonList("&7Permet de gérer votre Fly-mode")), (player, event) -> {
            GUICreator gui = GUICreator.getGUI(player);
            ItemStack item = event.getCurrentItem();
            assert item != null;
            if(!player.hasPermission(Perms.FLY_PERM_SELF.getPermission()) && item.getType() == Material.FEATHER){
                if(soundEnabled()){
                    playSound(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                }
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&3Feed", Collections.singletonList(CMD_NO_PERM.getMessage())));
            }
            else {
                if(item.getType() != Material.BARRIER) {
                    if(!player.getAllowFlight()){
                        player.setAllowFlight(true);
                        if(soundEnabled()){
                            playSound(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                        }
                        getMsgSendConfig(player, "fly", FLY_TOGGLE_ON.getMessage());
                    }else if(player.getAllowFlight()){
                        player.setAllowFlight(false);
                        if(soundEnabled()){
                            playSound(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                        }
                        getMsgSendConfig(player, "fly", FLY_TOGGLE_OFF.getMessage());
                    }
                    close(player);
                }
            }
        });

        setItem(20, ItemCreator.create(Material.BLUE_ICE, "&3Freeze", Collections.singletonList("&7Permet de freeze un joueur")), (player, event) -> {
            GUICreator gui = GUICreator.getGUI(player);
            ItemStack item = event.getCurrentItem();
            assert item != null;
            if(!player.hasPermission(Perms.FREEZE_PERM.getPermission()) && item.getType() == Material.BLUE_ICE){
                if(soundEnabled()){
                    playSound(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                }
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&3Freeze", Collections.singletonList(CMD_NO_PERM.getMessage())));
            }
            else {
                if(item.getType() != Material.BARRIER) {
                    if(soundEnabled()){
                        playSound(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                    }
                    getMsgSendConfig(player, "freeze", CMD_NO_ARGS.getMessage().replace("%cmd%", "freeze").replace("%args%", "<joueur>"));
                    close(player);
                }
            }
        });

        setItem(21, ItemCreator.create(Material.BOOKSHELF, "&3Gamemode", Collections.singletonList("&7Permet de gérer votre gamemode")), (player, event) -> {
            GUICreator gui = GUICreator.getGUI(player);
            ItemStack item = event.getCurrentItem();
            assert item != null;
            if(!player.hasPermission(Perms.GAMEMODE_PERM_ALL.getPermission()) && item.getType() == Material.BOOKSHELF){
                if(soundEnabled()){
                    playSound(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                }
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&3Gamemode", Collections.singletonList(CMD_NO_PERM.getMessage())));
            }else{
                new GamemodeGUI().open(player);
            }
        });

        setItem(22, ItemCreator.create(Material.BEDROCK, "&3God", Collections.singletonList("&7Permet de gérer votre invulnérabilité")), (player, event) -> {
            GUICreator gui = GUICreator.getGUI(player);
            ItemStack item = event.getCurrentItem();
            assert item != null;
            if(!player.hasPermission(Perms.GOD_PERM_SELF.getPermission()) && item.getType() == Material.BEDROCK){
                if(soundEnabled()){
                    playSound(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                }
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&3God", Collections.singletonList(CMD_NO_PERM.getMessage())));
            }
            else {
                if(item.getType() != Material.BARRIER) {
                    if(player.isInvulnerable()){
                        player.setInvulnerable(false);
                        player.setGlowing(false);
                        if(soundEnabled()){
                            playSound(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                        }
                        getMsgSendConfig(player, "god", GOD_SELF_OFF.getMessage());
                    }else if(!player.isInvulnerable()){
                        player.setInvulnerable(true);
                        player.setGlowing(true);
                        if(soundEnabled()){
                            playSound(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                        }
                        getMsgSendConfig(player, "god", GOD_SELF_ON.getMessage());
                    }
                    close(player);
                }
            }
        });

        setItem(23, ItemCreator.create(Material.POTION, "&3Heal", Collections.singletonList("&7Permet de vous soigner"), true), (player, event) -> {
            GUICreator gui = GUICreator.getGUI(player);
            ItemStack item = event.getCurrentItem();
            assert item != null;
            if(!player.hasPermission(Perms.CLEARINVENTORY_PERM_SELF.getPermission()) && item.getType() == Material.POTION){
                if(soundEnabled()){
                    playSound(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                }
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&3Heal", Collections.singletonList(CMD_NO_PERM.getMessage())));
            }
            else {
                if(item.getType() != Material.BARRIER) {
                    if(player.getHealth() != 20){
                        player.setHealth(20);
                        if(soundEnabled()){
                            playSound(player, Sound.ENTITY_GENERIC_DRINK, 1f, 1f);
                        }
                        getMsgSendConfig(player, "heal", HEAL_SELF.getMessage());
                    }else{
                        if(soundEnabled()){
                            playSound(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                        }
                        getMsgSendConfig(player, "heal", HEAL_ALREADY.getMessage());
                    }
                    close(player);
                }
            }
        });

        setItem(24, ItemCreator.create(Material.BOOK, "&3Invsee", Collections.singletonList("&7Permet d'ouvrir l'inventaire d'un autre joueur")), (player, event) -> {
            GUICreator gui = GUICreator.getGUI(player);
            ItemStack item = event.getCurrentItem();
            assert item != null;
            if(!player.hasPermission(Perms.INVSEE_PERM.getPermission()) && item.getType() == Material.BOOK){
                if(soundEnabled()){
                    playSound(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                }
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&3Invsee", Collections.singletonList(CMD_NO_PERM.getMessage())));
            }
            else {
                if(item.getType() != Material.BARRIER) {
                    if(soundEnabled()){
                        playSound(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                    }
                    getMsgSendConfig(player, "invsee", CMD_NO_ARGS.getMessage().replace("%cmd%", "invsee").replace("%args%", "<joueur>"));
                    close(player);
                }
            }
        });

        setItem(25, ItemCreator.create(Material.PAPER, "&3List", Collections.singletonList("&7Permet d'afficher la liste des joueurs")), (player, event) -> {
            ItemStack item = event.getCurrentItem();
            assert item != null;
                if(item.getType() != Material.BARRIER) {
                    if(soundEnabled()){
                        playSound(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                    }
                    sendMessage(player, "&aVous avez &e" + Bukkit.getOnlinePlayers().size() + " &ajoueurs sur &c" + Bukkit.getServer().getMaxPlayers() + " &aconnectés:");
                    sendMessage(player, "&7Les crochets &8[] &7vous affichent le pseudo modifié avec &e/nick&7.");
                    sendMessage(player, "&7Cliquer sur le pseudo vous téléportera au joueur.");
                    sendMessage(player,"");
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        if(Objects.equals(p.getCustomName(), p.getName())){
                            TextComponent playerTP = new TextComponent("  §e- §b" + p.getName() + " §c-> §8[]");
                            if (player != null) {
                                playerTP.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tp " + p.getName()));
                                player.spigot().sendMessage(playerTP);
                            }
                            //sender.sendMessage("  §e- §b" + p.getName() + " §c-> §8[]");
                        }else{
                            TextComponent playerTP = new TextComponent("  §e- §b" + p.getName() + " §c-> §8[ "+p.getCustomName()+" §8]");
                            if (player != null) {
                                playerTP.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tp " + p.getName()));
                                player.spigot().sendMessage(playerTP);
                            }
                            //sender.sendMessage("  §e- §b" + p.getName() + " §c-> §8[ "+p.getCustomName()+" §8]");
                        }
                    }
                    assert player != null;
                    close(player);
                }
        });

        setItem(28, ItemCreator.create(Material.NAME_TAG, "&3Nick", Collections.singletonList("&7Permet de changer votre pseudo")), (player, event) -> {
            GUICreator gui = GUICreator.getGUI(player);
            ItemStack item = event.getCurrentItem();
            assert item != null;
            if(!player.hasPermission(Perms.NICKNAME_PERMS.getPermission()) && item.getType() == Material.NAME_TAG){
                if(soundEnabled()){
                    playSound(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                }
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&3Nick", Collections.singletonList(CMD_NO_PERM.getMessage())));
            }
            else {
                if(item.getType() != Material.BARRIER) {
                    if(soundEnabled()){
                        playSound(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                    }
                    getMsgSendConfig(player, "nick", CMD_NO_ARGS.getMessage().replace("%cmd%", "nick").replace("%args%", "<pseudo>"));
                    close(player);
                }
            }
        });

        setItem(29, ItemCreator.create(Material.STONE, "&3Ping", Collections.singletonList("&7Permet d'afficher votre latence")), (player, event) -> {
            GUICreator gui = GUICreator.getGUI(player);
            ItemStack item = event.getCurrentItem();
            assert item != null;
            if(!player.hasPermission(Perms.PLAYERPINGER_PERM.getPermission()) && item.getType() == Material.STONE){
                if(soundEnabled()){
                    playSound(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                }
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&3Ping", Collections.singletonList(CMD_NO_PERM.getMessage())));
            }
            else {
                if(item.getType() != Material.BARRIER) {
                    if(soundEnabled()){
                        playSound(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                    }
                    int ping = player.getPing();
                    if(ping < 50){
                        sendMessage(player, PING_SELF_MSG.getMessage().replace("%ping%", MultiCommands.colored("&a" + ping + " ms")));
                    }
                    if(ping > 50){
                        sendMessage(player, PING_SELF_MSG.getMessage().replace("%ping%", MultiCommands.colored("&e" + ping + " ms")));
                    }
                    if(ping > 300){
                        sendMessage(player, PING_SELF_MSG.getMessage().replace("%ping%", MultiCommands.colored("&c" + ping + " ms")));
                    }
                    close(player);
                }
            }
        });

        setItem(30, ItemCreator.create(Material.PAPER, "&3Plugins", Collections.singletonList("&7Permet d'afficher la liste des plugins")), (player, event) -> {
            GUICreator gui = GUICreator.getGUI(player);
            ItemStack item = event.getCurrentItem();
            assert item != null;
            if(!player.isOp() && item.getType() == Material.STONE){
                if(soundEnabled()){
                    playSound(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                }
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&3Plugins", Collections.singletonList(CMD_NO_PERM.getMessage())));
            }
            else {
                if (item.getType() != Material.BARRIER) {
                    if (soundEnabled()) {
                        playSound(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                    }
                    sendMessage(player, "&6Plugins chargés &7(&c" + Bukkit.getPluginManager().getPlugins().length + "&7)&8:");
                    Plugin[] pm = Bukkit.getPluginManager().getPlugins();
                    for (Plugin p : pm) {
                        try {
                            if (p.isEnabled()) {
                                sendMessage(player, "  &3- &b" + p.getName() + " &8" + p.getDescription().getVersion() + " &a(enabled)");
                            } else {
                                sendMessage(player, "  &3- &b" + p.getName() + " &8" + p.getDescription().getVersion() + " &c(disabled)");
                            }
                        } catch (Error e) {
                            if(soundEnabled()){
                                playSound(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                            }
                            sendMessage(player, "&cUne erreur est survenue :&e" + e);
                        }
                    }
                    close(player);
                }
            }
        });

        setItem(31, ItemCreator.create(Material.PAPER, "&3Server-Infos", Collections.singletonList("&7Permet d'afficher les infos du serveur")), (player, event) -> {
            GUICreator gui = GUICreator.getGUI(player);
            ItemStack item = event.getCurrentItem();
            assert item != null;
            if(!player.hasPermission(Perms.SERVERINFO_PERM.getPermission()) && item.getType() == Material.PAPER){
                if(soundEnabled()){
                    playSound(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                }
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&3Server-Infos", Collections.singletonList(CMD_NO_PERM.getMessage())));
            }
            else {
                if(item.getType() != Material.BARRIER) {
                    if(soundEnabled()){
                        playSound(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                    }
                    sendMessage(player, "&a&m-+---------------+- &7 - &e&l{ &cServer Informations &e&l} &7- &a&m-+---------------+-");
                    sendMessage(player, "&6IP &c/&6 Port: &c" + Bukkit.getServer().getIp() + " &e:&c " + Bukkit.getServer().getPort());
                    sendMessage(player, "&6Jar: &c" + Bukkit.getServer().getVersion());
                    sendMessage(player, "&6Version: &c" + Bukkit.getServer().getBukkitVersion());
                    sendMessage(player, "&6Joueurs connectés: &c" + Bukkit.getServer().getOnlinePlayers().size() + "&e / &c" + Bukkit.getServer().getMaxPlayers());
                    sendMessage(player, "&6Opérateurs: &7(&c" + Bukkit.getServer().getOperators().size() + "&7)");
                    for (OfflinePlayer op : Bukkit.getOperators()) {
                        sendMessage(player, "&c- &7" + op.getName());
                    }
                    sendMessage(player, "&6Whitelistés: &7(&c" + Bukkit.getServer().getWhitelistedPlayers().size() + "&7)");
                    for(OfflinePlayer wl : Bukkit.getWhitelistedPlayers()){
                        sendMessage(player, "&c- &7" + wl.getName());
                    }
                    sendMessage(player, "&6Bannis: &7(&c" + Bukkit.getServer().getBannedPlayers().size() + "&7) ");
                    for(OfflinePlayer ban : Bukkit.getBannedPlayers()){
                        sendMessage(player, "&c- &7" + ban.getName());
                    }
                    sendMessage(player, "&6Vanish: &7(&c" + VanishHandler.getVanished().size() + "&7)");
                    for(OfflinePlayer vanished : VanishHandler.getVanished()){
                        sendMessage(player, "&c- &7" + vanished.getName());
                    }
                    sendMessage(player, "&6AFK: &7(&c" + AFKHandler.getAFK().size() + "&7)");
                    for(OfflinePlayer afk : AFKHandler.getAFK()){
                        sendMessage(player, "&c- &7" + afk.getName());
                    }
                    try{
                        Class.forName("me.capitainecat0.multimaintenance.MultiMaintenance");

                        sendMessage(player, "&6Joueurs whitelistés &8(multimaintenance) &7(&c" + MultiMaintenance.getAUTHORIZED().size() + "&7)");
                        for (UUID uuid : MultiMaintenance.getAUTHORIZED()) {
                            OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(uuid);
                            sendMessage(player, "&e-&a " + offlinePlayer.getName());
                        }
                        if (MultiMaintenance.ENABLED) {
                            sendMessage(player, me.capitainecat0.multimaintenance.utils.Messenger.MAINTENANCE_STATUS.getMessage().replace("%status", "&aactive"));
                        }else {
                            sendMessage(player, me.capitainecat0.multimaintenance.utils.Messenger.MAINTENANCE_STATUS.getMessage().replace("%status", "&cdésactivée"));
                        }
                    }catch(final Exception ex) {
                        sendMessage(player, "&7Installez &cMulti§&Maintenance &7pour afficher plus d'informations!");
                    }
                    sendMessage(player, "&a&m-+----------------------------------------------------------------+-");
                    close(player);
                }
            }
        });

        setItem(32, ItemCreator.create(Material.ENDER_PEARL, "&3Téléport", Collections.singletonList("&7Permet de vous téléporter")), (player, event) -> {
            GUICreator gui = GUICreator.getGUI(player);
            ItemStack item = event.getCurrentItem();
            assert item != null;
            if(!player.hasPermission(Perms.TELEPORT_PERM.getPermission()) && item.getType() == Material.ENDER_PEARL){
                if(soundEnabled()){
                    playSound(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                }
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&3Ping", Collections.singletonList(CMD_NO_PERM.getMessage())));
            }
            else {
                if(item.getType() != Material.BARRIER) {
                    if(soundEnabled()){
                        playSound(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                    }
                    getMsgSendConfig(player, "teleport", CMD_NO_ARGS.getMessage().replace("%cmd%", "teleport").replace("%args%", "<joueur | coordonées>"));
                    close(player);
                }
            }
        });

        setItem(33, ItemCreator.create(Material.ENDER_EYE, "&3Vanish", Collections.singletonList("&7Permet de vous vanish")), (player, event) -> {
            GUICreator gui = GUICreator.getGUI(player);
            ItemStack item = event.getCurrentItem();
            assert item != null;
            if(!player.hasPermission(Perms.TELEPORT_PERM.getPermission()) && item.getType() == Material.ENDER_EYE){
                if(soundEnabled()){
                    playSound(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                }
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&3Vanish", Collections.singletonList(CMD_NO_PERM.getMessage())));
            }
            else {
                if(item.getType() != Material.BARRIER) {
                    VanishHandler handler = VanishHandler.getInstance();
                    if(!handler.isVanished(player)){
                        handler.toggleVanish(player);
                        if(soundEnabled()){
                            playSound(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                        }
                        getMsgSendConfig(player, "vanish", VANISH_ENABLED_SELF.getMessage());
                    }else if(handler.isVanished(player)){
                        handler.toggleVanish(player);
                        if(soundEnabled()){
                            playSound(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                        }
                        getMsgSendConfig(player, "vanish", VANISH_DISABLED_SELF.getMessage());
                    }
                    close(player);
                }
            }
        });

        setItem(34, ItemCreator.create(Material.PAPER, "&3Whois", Collections.singletonList("&7Permet d'afficher les informations d'un joueur")), (player, event) -> {
            GUICreator gui = GUICreator.getGUI(player);
            ItemStack item = event.getCurrentItem();
            assert item != null;
            if(!player.hasPermission(Perms.WHOIS_PERM.getPermission()) && item.getType() == Material.PAPER){
                if(soundEnabled()){
                    playSound(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                }
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&3Bois", Collections.singletonList(CMD_NO_PERM.getMessage())));
            }
            else {
                if(item.getType() != Material.BARRIER) {
                    if(soundEnabled()){
                        playSound(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                    }
                    getMsgSendConfig(player, "whois", CMD_NO_ARGS.getMessage().replace("%cmd%", "whois").replace("%args%", "<player>"));
                    close(player);
                }
            }
        });
    }
}
