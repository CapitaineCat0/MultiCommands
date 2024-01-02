package me.capitainecat0.multicommands.utils.inventories;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.commands.implementers.*;
import me.capitainecat0.multicommands.utils.permissions.PermissionManager;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;
import static me.capitainecat0.multicommands.utils.permissions.Perms.*;

public class ManagePlayerGUI extends GUICreator{
    public ManagePlayerGUI(@NotNull Player player) {
        super(5, "&6Manage &3" + player.getName());
        PermissionManager perms = MultiCommands.getPermissionManager();
        setItem(0, ItemCreator.create(Material.BLACK_STAINED_GLASS_PANE, " "));
        setItem(1, ItemCreator.create(Material.BLACK_STAINED_GLASS_PANE, " "));
        setItem(9, ItemCreator.create(Material.BLACK_STAINED_GLASS_PANE, " "));
        setItem(7, ItemCreator.create(Material.BLACK_STAINED_GLASS_PANE, " "));
        setItem(8, ItemCreator.create(Material.BLACK_STAINED_GLASS_PANE, " "));
        setItem(17, ItemCreator.create(Material.BLACK_STAINED_GLASS_PANE, " "));
        setItem(27, ItemCreator.create(Material.BLACK_STAINED_GLASS_PANE, " "));

        setItem(36, ItemCreator.create(Material.RED_STAINED_GLASS_PANE, "&c&lExit", "<gray>Go back to the previous menu"), (sender, event) -> {
            ItemStack item = event.getCurrentItem();
            assert item != null;
            if(item.getType() != Material.BARRIER){
                new PlayerGUI().open(sender);
            }
        });
        setItem(37, ItemCreator.create(Material.BLACK_STAINED_GLASS_PANE, " "));
        setItem(35, ItemCreator.create(Material.BLACK_STAINED_GLASS_PANE, " "));
        setItem(43, ItemCreator.create(Material.BLACK_STAINED_GLASS_PANE, " "));
        setItem(44, ItemCreator.create(Material.BLACK_STAINED_GLASS_PANE, " "));

        setItem(10, ItemCreator.create(Material.RED_BED, "&6AFK", "<gray>Toggle AFK for <aqua>"+player.getName()), (sender, event) -> {
            GUICreator gui = GUICreator.getGUI(sender);
            ItemStack item = event.getCurrentItem();
            assert item != null;
            if(!perms.hasPermission(sender, AFK_PERM.getPermission())
                    || !perms.hasPermission(sender, ALL_PERMS.getPermission()) && item.getType() == Material.RED_BED){
                playSoundIfEnabled(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&6AFK", CMD_NO_PERM.getMessage()));
            }
            else{
                if(item.getType() != Material.BARRIER){
                    new AFK(sender, player).execute();
                    gui.close(sender);
                }
            }
        });
        setItem(11, ItemCreator.create(Material.NAME_TAG, "&6Ban", "<gray>Ban <aqua>"+player.getName()), (sender, event) -> {
            GUICreator gui = GUICreator.getGUI(sender);
            ItemStack item = event.getCurrentItem();
            assert item != null;
            if(!perms.hasPermission(sender, BAN_PERM.getPermission())
                    || !perms.hasPermission(sender, ALL_PERMS.getPermission()) && item.getType() == Material.NAME_TAG){
                playSoundIfEnabled(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&6Ban", CMD_NO_PERM.getMessage()));
            }
            else{
                if(item.getType() != Material.BARRIER){
                    new Ban(sender, player, new String[]{"Banned by " + sender.getName()}).execute();
                }
            }
        });
        setItem(12, ItemCreator.create(Material.NAME_TAG, "&6BanIP", "<gray>BanIP <aqua>"+player.getName()), (sender, event) -> {
            GUICreator gui = GUICreator.getGUI(sender);
            ItemStack item = event.getCurrentItem();
            assert item != null;
            if(!perms.hasPermission(sender, BANIP_PERM.getPermission())
                    || !perms.hasPermission(sender, ALL_PERMS.getPermission()) && item.getType() == Material.NAME_TAG){
                playSoundIfEnabled(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&6BanIP", CMD_NO_PERM.getMessage()));
            }
            else{
                if(item.getType() != Material.BARRIER){
                    new BanIP(sender, player, null).execute();
                }
            }
        });
        setItem(13, ItemCreator.create(Material.LAVA_BUCKET, "&6Clear-Inventory", "<gray>Clear <aqua>"+player.getName()+"<gray>'s inventory"), (sender, event) -> {
            GUICreator gui = GUICreator.getGUI(sender);
            ItemStack item = event.getCurrentItem();
            assert item != null;
            if(!perms.hasPermission(sender, CLEARINVENTORY_PERM_OTHER.getPermission())
                    || !perms.hasPermission(sender, ALL_PERMS.getPermission()) && item.getType() == Material.LAVA_BUCKET){
                playSoundIfEnabled(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&6Clear-Inventory", CMD_NO_PERM.getMessage()));
            }
            else{
                if(item.getType() != Material.BARRIER){
                    new ClearInventory(player).execute();
                    playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                    getMsgSendConfig(sender, "Clear-Inventory", CLEARINV_ADMIN.getMessage().replace("{0}", player.getName()));
                }
            }
        });
        setItem(14, ItemCreator.create(Material.ENDER_CHEST, "&6EnderChest", "<gray>Open <aqua>"+player.getName()+"<gray>'s EnderChest"), (sender, event) -> {
            GUICreator gui = GUICreator.getGUI(sender);
            ItemStack item = event.getCurrentItem();
            assert item != null;
            if(!perms.hasPermission(sender, ENDERCHEST_PERM_OTHER.getPermission())
                    || !perms.hasPermission(sender, ALL_PERMS.getPermission()) && item.getType() == Material.ENDER_CHEST){
                playSoundIfEnabled(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&6EnderChest", CMD_NO_PERM.getMessage()));
            }
            else{
                if(item.getType() != Material.BARRIER){
                    new EnderChest(sender, player).execute();
                }
            }
        });
        setItem(15, ItemCreator.create(Material.COOKED_BEEF, "&6Feed", "<gray>Feed <aqua>"+player.getName()), (sender, event) -> {
            GUICreator gui = GUICreator.getGUI(sender);
            ItemStack item = event.getCurrentItem();
            assert item != null;
            if(!perms.hasPermission(sender, FEED_PERM_OTHER.getPermission())
                    || !perms.hasPermission(sender, ALL_PERMS.getPermission()) && item.getType() == Material.COOKED_BEEF){
                playSoundIfEnabled(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&6Feed", CMD_NO_PERM.getMessage()));
            }
            else{
                if(item.getType() != Material.BARRIER){
                    new Feed(sender, player).execute();
                }
            }
        });
        setItem(16, ItemCreator.create(Material.FEATHER, "&6Fly", "<gray>Toggle fly for <aqua>"+player.getName()), (sender, event) -> {
            GUICreator gui = GUICreator.getGUI(sender);
            ItemStack item = event.getCurrentItem();
            assert item != null;
            if(!perms.hasPermission(sender, FLY_PERM_OTHER.getPermission())
                    || !perms.hasPermission(sender, ALL_PERMS.getPermission()) && item.getType() == Material.FEATHER){
                playSoundIfEnabled(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&6Fly", CMD_NO_PERM.getMessage()));
            }
            else{
                if(item.getType() != Material.BARRIER){
                    new Fly(sender, player).execute();
                }
            }
        });
        setItem(19, ItemCreator.create(Material.BLUE_ICE, "&6Freeze", "<gray>Freeze <aqua>"+player.getName()), (sender, event) -> {
            GUICreator gui = GUICreator.getGUI(sender);
            ItemStack item = event.getCurrentItem();
            assert item != null;
            if(!perms.hasPermission(sender, FREEZE_PERM.getPermission())
                    || !perms.hasPermission(sender, ALL_PERMS.getPermission()) && item.getType() == Material.BLUE_ICE){
                playSoundIfEnabled(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&6Freeze", CMD_NO_PERM.getMessage()));
            }
            else{
                if(item.getType() != Material.BARRIER){
                    new Freeze(sender, player).execute();
                }
            }
        });
        setItem(20, ItemCreator.create(Material.BOOKSHELF, "&6Gamemode", "<gray>Change <aqua>"+player.getName()+"<gray>'s gamemode"), (sender, event) -> {
            GUICreator gui = GUICreator.getGUI(sender);
            ItemStack item = event.getCurrentItem();
            assert item != null;

            if(!perms.hasPermission(sender, GAMEMODE_PERM_OTHER_ALL.getPermission())
                    || !perms.hasPermission(sender, ALL_PERMS.getPermission()) && item.getType() == Material.BOOKSHELF){
                playSoundIfEnabled(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&6Gamemode", CMD_NO_PERM.getMessage()));
            }
            else{
                if(item.getType() != Material.BARRIER){
                    new GamemodeGUI(player).open(sender);
                }
            }
        });
        setItem(21, ItemCreator.create(Material.BEDROCK, "&6God", "<gray>Toggle <aqua>"+player.getName()+"<gray>'s god mode"), (sender, event) -> {
            GUICreator gui = GUICreator.getGUI(sender);
            ItemStack item = event.getCurrentItem();

            assert item != null;
            if(!perms.hasPermission(sender, GOD_PERM_OTHER.getPermission())
                    || !perms.hasPermission(sender, ALL_PERMS.getPermission()) && item.getType() == Material.BEDROCK){
                playSoundIfEnabled(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&6God", CMD_NO_PERM.getMessage()));
            }
            else{
                if(item.getType() != Material.BARRIER){
                    new God(sender, player).execute();
                }
            }
        });
        setItem(22, ItemCreator.create(Material.POTION, "&6Heal", "<gray>Heal <aqua>"+player.getName(), true), (sender, event) -> {
            GUICreator gui = GUICreator.getGUI(sender);
            ItemStack item = event.getCurrentItem();

            assert item != null;
            if(!perms.hasPermission(sender, HEAL_PERM_OTHER.getPermission())
                    || !perms.hasPermission(sender, ALL_PERMS.getPermission()) && item.getType() == Material.POTION){
                playSoundIfEnabled(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&6Heal", CMD_NO_PERM.getMessage()));
            }
            else{
                if(item.getType() != Material.BARRIER){
                    new Heal(sender, player).execute();
                }
            }
        });
        setItem(23, ItemCreator.create(Material.CHEST, "&6Invsee", "<gray>Open <aqua>"+player.getName()+"<gray>'s inventory"), (sender, event) -> {
            GUICreator gui = GUICreator.getGUI(sender);
            ItemStack item = event.getCurrentItem();

            assert item != null;
            if(!perms.hasPermission(sender, INVSEE_PERM.getPermission())
                    || !perms.hasPermission(sender, ALL_PERMS.getPermission()) && item.getType() == Material.CHEST){
                playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&6Invsee", CMD_NO_PERM.getMessage()));
            }
            else{
                if(item.getType() != Material.BARRIER){
                    new Invsee(sender, player).execute();
                }
            }
        });
        setItem(24, ItemCreator.create(Material.NAME_TAG, "&6Kick", "<gray>Kick <aqua>"+player.getName()), (sender, event) -> {
            GUICreator gui = GUICreator.getGUI(sender);
            ItemStack item = event.getCurrentItem();

            assert item != null;
            if(!perms.hasPermission(sender, KICK_PERM.getPermission())
                    || !perms.hasPermission(sender, ALL_PERMS.getPermission()) && item.getType() == Material.NAME_TAG){
                playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&6Kick", CMD_NO_PERM.getMessage()));
            }
            else{
                if(item.getType() != Material.BARRIER){
                    new Kick(player, null).execute();
                }
            }
        });
        setItem(25, ItemCreator.create(Material.SPLASH_POTION, "&6Kill", "<gray>Kill <aqua>"+player.getName(), true), (sender, event) -> {
            GUICreator gui = GUICreator.getGUI(sender);
            ItemStack item = event.getCurrentItem();

            assert item != null;
            if(!perms.hasPermission(sender, KILL_PERM.getPermission())
                    || !perms.hasPermission(sender, ALL_PERMS.getPermission()) && item.getType() == Material.SPLASH_POTION){
                playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&6Kill", CMD_NO_PERM.getMessage()));
            }
            else{
                if(item.getType() != Material.BARRIER){
                    new Kill(player).execute();
                    playSoundIfEnabled(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                    sendMessage(player, KILL_DONE.getMessage().replace("{0}", Objects.requireNonNull(player.getName())));
                }
            }
        });
        setItem(28, ItemCreator.create(Material.NAME_TAG, "&6Mute", "<gray>Mute <aqua>"+player.getName()), (sender, event) -> {
            GUICreator gui = GUICreator.getGUI(sender);
            ItemStack item = event.getCurrentItem();

            assert item != null;
            if(!perms.hasPermission(sender, MUTE_PERM.getPermission())
                    || !perms.hasPermission(sender, ALL_PERMS.getPermission()) && item.getType() == Material.NAME_TAG){
                playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&6Mute", CMD_NO_PERM.getMessage()));
            }
            else{
                if(item.getType() != Material.BARRIER){
                    new Mute(sender, player, new String[]{"Muted using GUI"}).execute();
                }
            }
        });
        setItem(29, ItemCreator.create(Material.ENDER_PEARL, "&6Teleport", "<gray>Teleport you to <aqua>"+player.getName()), (sender, event) -> {
            GUICreator gui = GUICreator.getGUI(sender);
            ItemStack item = event.getCurrentItem();

            assert item != null;
            if(!perms.hasPermission(sender, TELEPORT_PERM.getPermission())
                    || !perms.hasPermission(sender, ALL_PERMS.getPermission()) && item.getType() == Material.ENDER_PEARL){
                playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&6Teleport", CMD_NO_PERM.getMessage()));
            }
            else{
                if(item.getType() != Material.BARRIER){
                    new Teleport(sender, player, null).execute();
                }
            }
        });
        setItem(30, ItemCreator.create(Material.ENDER_PEARL, "&6Teleport", "<gray>Teleport <aqua>"+player.getName()+"<gray> to you"), (sender, event) -> {
            GUICreator gui = GUICreator.getGUI(sender);
            ItemStack item = event.getCurrentItem();

            assert item != null;
            if(!perms.hasPermission(sender, TELEPORT_PERM.getPermission())
                    || !perms.hasPermission(sender, ALL_PERMS.getPermission()) && item.getType() == Material.ENDER_PEARL){
                playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&6Teleport", CMD_NO_PERM.getMessage()));
            }
            else{
                if(item.getType() != Material.BARRIER){
                    new Teleport(sender, player, sender).execute();
                }
            }
        });
        setItem(31, ItemCreator.create(Material.NAME_TAG, "&6Unban", "<gray>Unban <aqua>"+player.getName()+"\n<dark_gray>(Feature in development)"), (sender, event) -> {
            GUICreator gui = GUICreator.getGUI(sender);
            ItemStack item = event.getCurrentItem();

            assert item != null;
            if(!perms.hasPermission(sender, UNBAN_PERM.getPermission())
                    || !perms.hasPermission(sender, ALL_PERMS.getPermission()) && item.getType() == Material.NAME_TAG){
                playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&6Unban", CMD_NO_PERM.getMessage()));
            }
            else{
                if(item.getType() != Material.BARRIER){
                    //new Unban(sender, player).execute();
                }
            }
        });
        setItem(32, ItemCreator.create(Material.NAME_TAG, "&6UnbanIP", "<gray>UnbanIP <aqua>"+player.getName()+"\n<dark_gray>(Feature in development)"), (sender, event) -> {
            GUICreator gui = GUICreator.getGUI(sender);
            ItemStack item = event.getCurrentItem();

            assert item != null;
            if(!perms.hasPermission(sender, UNBANIP_PERM.getPermission())
                    || !perms.hasPermission(sender, ALL_PERMS.getPermission()) && item.getType() == Material.NAME_TAG){
                playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                gui.setItem(18, ItemCreator.create(Material.BARRIER, "&6UnbanIP", CMD_NO_PERM.getMessage()));
            }
            else{
                if(item.getType() != Material.BARRIER){
                    //new UnbanIP(sender, player).execute();
                }
            }
        });
        setItem(33, ItemCreator.create(Material.PAPER, "&6Whois", "<gray>Who is <aqua>"+player.getName()+"<gray> ?"), (sender, event) -> {
            GUICreator gui = GUICreator.getGUI(sender);
            ItemStack item = event.getCurrentItem();

            assert item != null;

            if(!perms.hasPermission(sender, WHOIS_PERM.getPermission())
                    || !perms.hasPermission(sender, ALL_PERMS.getPermission()) && item.getType() == Material.PAPER){
                playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);

                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&6Whois", CMD_NO_PERM.getMessage()));
            }
            else{
                if(item.getType() != Material.BARRIER){
                    new Whois(sender, player).execute();
                }
            }
        });
    }
}
