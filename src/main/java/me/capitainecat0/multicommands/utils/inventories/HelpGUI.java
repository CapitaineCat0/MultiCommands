package me.capitainecat0.multicommands.utils.inventories;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.commands.implementers.*;
import me.capitainecat0.multicommands.utils.*;
import me.capitainecat0.multicommands.utils.permissions.PermissionManager;
import me.capitainecat0.multicommands.utils.permissions.Perms;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.inventory.ItemStack;

import static me.capitainecat0.multicommands.MultiCommands.getInstance;
import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;

public class HelpGUI extends GUICreator {
    public HelpGUI() {
        super(6, "&6&l"+getInstance().getDescription().getName()+" &9V." + getInstance().getDescription().getVersion()+ " &cHelp");
        PermissionManager perms = MultiCommands.getPermissionManager();
        this.setItem(0, ItemCreator.create(Material.BLACK_STAINED_GLASS_PANE, " "));
        this.setItem(1, ItemCreator.create(Material.BLACK_STAINED_GLASS_PANE, " "));
        this.setItem(9, ItemCreator.create(Material.BLACK_STAINED_GLASS_PANE, " "));
        this.setItem(7, ItemCreator.create(Material.BLACK_STAINED_GLASS_PANE, " "));
        this.setItem(8, ItemCreator.create(Material.BLACK_STAINED_GLASS_PANE, " "));
        this.setItem(17, ItemCreator.create(Material.BLACK_STAINED_GLASS_PANE, " "));
        this.setItem(36, ItemCreator.create(Material.BLACK_STAINED_GLASS_PANE, " "));
        this.setItem(45, ItemCreator.create(Material.BLACK_STAINED_GLASS_PANE, " "));
        this.setItem(46, ItemCreator.create(Material.BLACK_STAINED_GLASS_PANE, " "));
        this.setItem(44, ItemCreator.create(Material.BLACK_STAINED_GLASS_PANE, " "));
        this.setItem(52, ItemCreator.create(Material.BLACK_STAINED_GLASS_PANE, " "));
        this.setItem(53, ItemCreator.create(Material.BLACK_STAINED_GLASS_PANE, " "));

        this.setItem(10, ItemCreator.create(Material.RED_BED, "&3AFK", HELP_AFK.getMessage()), (player, event) -> {
            GUICreator gui = GUICreator.getGUI(player);
            ItemStack item = event.getCurrentItem();

            assert item != null;

            if (!perms.hasPermission(player, Perms.AFK_PERM.getPermission())
                    || !perms.hasPermission(player, Perms.ALL_PERMS.getPermission()) && item.getType() == Material.RED_BED) {
                playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("no-perm-sound")), 1.0F, 1.0F);
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&3AFK", CMD_NO_PERM.getMessage()));
            } else if (item.getType() != Material.BARRIER) {
                new AFK(player, player).execute();
                this.close(player);
            }

        });

        this.setItem(11, ItemCreator.create(Material.STONE, "&3Alert", HELP_ALERT.getMessage()), (player, event) -> {
            GUICreator gui = GUICreator.getGUI(player);
            ItemStack item = event.getCurrentItem();

            assert item != null;

            if (!perms.hasPermission(player, Perms.ALERT_PERM.getPermission())
                    || !perms.hasPermission(player, Perms.ALL_PERMS.getPermission()) && item.getType() == Material.STONE) {
                playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("no-perm-sound")), 1.0F, 1.0F);
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&3Alert", CMD_NO_PERM.getMessage()));
            } else if (item.getType() != Material.BARRIER) {
                playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("no-perm-sound")), 1.0F, 1.0F);
                getMsgSendConfig(player, "alert", Messenger.CMD_NO_ARGS.getMessage().replace("<command>", "alert").replace("{0}", "<message>"));
                this.close(player);
            }
        });

        this.setItem(12, ItemCreator.create(Material.STONE, "&3Ban", HELP_BAN.getMessage()), (player, event) -> {
            GUICreator gui = GUICreator.getGUI(player);
            ItemStack item = event.getCurrentItem();

            assert item != null;

            if (!perms.hasPermission(player, Perms.BAN_PERM.getPermission())
                    || !perms.hasPermission(player, Perms.ALL_PERMS.getPermission()) && item.getType() == Material.STONE) {
                playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("no-perm-sound")), 1.0F, 1.0F);
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&3Ban", CMD_NO_PERM.getMessage()));
            } else if (item.getType() != Material.BARRIER) {
                playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("no-perm-sound")), 1.0F, 1.0F);
                getMsgSendConfig(player, "ban", Messenger.CMD_NO_ARGS.getMessage().replace("<command>", "ban").replace("{0}", "[reason]"));
                this.close(player);
            }

        });

        this.setItem(13, ItemCreator.create(Material.STONE, "&3Broadcast", HELP_BROADCAST.getMessage()), (player, event) -> {
            GUICreator gui = GUICreator.getGUI(player);
            ItemStack item = event.getCurrentItem();

            assert item != null;

            if (!player.hasPermission(Perms.BROADCAST_PERM.getPermission()) && item.getType() == Material.STONE) {
                playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("no-perm-sound")), 1.0F, 1.0F);
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&3Broadcast", CMD_NO_PERM.getMessage()));
            } else if (item.getType() != Material.BARRIER) {
                playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("no-perm-sound")), 1.0F, 1.0F);
                getMsgSendConfig(player, "broadcast", Messenger.CMD_NO_ARGS.getMessage().replace("<command>", "broadcast").replace("{0}", "<message>"));
                this.close(player);
            }
        });

        this.setItem(14, ItemCreator.create(Material.LAVA_BUCKET, "&3Clear-Inventory", HELP_CLEARINV.getMessage()), (player, event) -> {
            GUICreator gui = GUICreator.getGUI(player);
            ItemStack item = event.getCurrentItem();

            assert item != null;

            if (!perms.hasPermission(player, Perms.CLEARINVENTORY_PERM_SELF.getPermission())
                    || !perms.hasPermission(player, Perms.ALL_PERMS.getPermission()) && item.getType() == Material.LAVA_BUCKET) {
                playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("no-perm-sound")), 1.0F, 1.0F);
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&3Clear-Inventory", CMD_NO_PERM.getMessage()));
            } else if (item.getType() != Material.BARRIER) {
                playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("no-perm-sound")), 1.0F, 1.0F);
                getMsgSendConfig(player, "ClearInventory", Messenger.CLEARINV_SELF_DONE.getMessage());
                new ClearInventory(player).execute();
                this.close(player);
            }
        });

        this.setItem(15, ItemCreator.create(Material.CRAFTING_TABLE, "&3Craft", HELP_CRAFT.getMessage()), (player, event) -> {
            GUICreator gui = GUICreator.getGUI(player);
            ItemStack item = event.getCurrentItem();

            assert item != null;

            if (!perms.hasPermission(player, Perms.CRAFT_PERM.getPermission())
                    || !perms.hasPermission(player, Perms.ALL_PERMS.getPermission()) && item.getType() == Material.CRAFTING_TABLE) {
                playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("no-perm-sound")), 1.0F, 1.0F);
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&3Craft", CMD_NO_PERM.getMessage()));
            } else if (item.getType() != Material.BARRIER) {
                playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("cmd-done-sound")), 1.0F, 1.0F);
                new Craft(player).execute();
            }
        });

        this.setItem(16, ItemCreator.create(Material.ENDER_CHEST, "&3Ender-Chest", HELP_ENDERCHEST.getMessage()), (player, event) -> {
            GUICreator gui = GUICreator.getGUI(player);
            ItemStack item = event.getCurrentItem();

            assert item != null;

            if (!perms.hasPermission(player, Perms.ENDERCHEST_PERM_SELF.getPermission())
                    || !perms.hasPermission(player, Perms.ALL_PERMS.getPermission()) && item.getType() == Material.ENDER_CHEST) {
                playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("no-perm-sound")), 1.0F, 1.0F);
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&3Ender-Chest", CMD_NO_PERM.getMessage()));
            } else if (item.getType() != Material.BARRIER) {
                playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("cmd-done-sound")), 1.0F, 1.0F);
                new EnderChest(player, player).execute();
            }
        });

        this.setItem(19, ItemCreator.create(Material.COOKED_CHICKEN, "&3Feed", HELP_FEED.getMessage()), (player, event) -> {
            GUICreator gui = GUICreator.getGUI(player);
            ItemStack item = event.getCurrentItem();

            assert item != null;

            if (!perms.hasPermission(player, Perms.FEED_PERM_SELF.getPermission())
                    || !perms.hasPermission(player, Perms.ALL_PERMS.getPermission()) && item.getType() == Material.COOKED_CHICKEN) {
                playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("no-perm-sound")), 1.0F, 1.0F);
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&3Feed", CMD_NO_PERM.getMessage()));
            } else if (item.getType() != Material.BARRIER) {
                if (player.getFoodLevel() != 20) {
                    new Feed(player, player).execute();
                    playSoundIfEnabled(player, Sound.ENTITY_GENERIC_EAT, 1.0F, 1.0F);
                    getMsgSendConfig(player, "feed", Messenger.FEED_SELF.getMessage());
                } else {
                    playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("no-perm-sound")), 1.0F, 1.0F);
                    getMsgSendConfig(player, "feed", Messenger.FEED_ALREADY.getMessage());
                }
                this.close(player);
            }
        });

        this.setItem(20, ItemCreator.create(Material.FEATHER, "&3Fly", HELP_FLY.getMessage()), (player, event) -> {
            GUICreator gui = GUICreator.getGUI(player);
            ItemStack item = event.getCurrentItem();

            assert item != null;

            if (!perms.hasPermission(player, Perms.FLY_PERM_SELF.getPermission())
                    || !perms.hasPermission(player, Perms.ALL_PERMS.getPermission()) && item.getType() == Material.FEATHER) {
                playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("no-perm-sound")), 1.0F, 1.0F);
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&3Fly", CMD_NO_PERM.getMessage()));
            } else if (item.getType() != Material.BARRIER) {
                if (!player.getAllowFlight()) {
                    new Fly(player, null).execute();
                    playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("cmd-done-sound")), 1.0F, 1.0F);
                    getMsgSendConfig(player, "fly", Messenger.FLY_TOGGLE_ON.getMessage());
                } else if (player.getAllowFlight()) {
                    player.setAllowFlight(false);
                    playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("cmd-done-sound")), 1.0F, 1.0F);
                    getMsgSendConfig(player, "fly", Messenger.FLY_TOGGLE_OFF.getMessage());
                }
                this.close(player);
            }
        });

        this.setItem(21, ItemCreator.create(Material.BLUE_ICE, "&3Freeze", HELP_FREEZE.getMessage()), (player, event) -> {
            GUICreator gui = GUICreator.getGUI(player);
            ItemStack item = event.getCurrentItem();

            assert item != null;

            if (!perms.hasPermission(player, Perms.FREEZE_PERM.getPermission())
                    || !perms.hasPermission(player, Perms.ALL_PERMS.getPermission()) && item.getType() == Material.BLUE_ICE) {
                playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("no-perm-sound")), 1.0F, 1.0F);
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&3Freeze", CMD_NO_PERM.getMessage()));
            } else if (item.getType() != Material.BARRIER) {
                playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("no-perm-sound")), 1.0F, 1.0F);
                getMsgSendConfig(player, "freeze", Messenger.CMD_NO_ARGS.getMessage().replace("<command>", "freeze").replace("{0}", "<player>"));
                this.close(player);
            }
        });

        this.setItem(22, ItemCreator.create(Material.BOOKSHELF, "&3Gamemode", HELP_GAMEMODE.getMessage()), (player, event) -> {
            GUICreator gui = GUICreator.getGUI(player);
            ItemStack item = event.getCurrentItem();

            assert item != null;

            if (!perms.hasPermission(player, Perms.GAMEMODE_PERM_ALL.getPermission())
                    || !perms.hasPermission(player, Perms.ALL_PERMS.getPermission()) && item.getType() == Material.BOOKSHELF) {
                playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("no-perm-sound")), 1.0F, 1.0F);
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&3Gamemode", CMD_NO_PERM.getMessage()));
            } else {
                new GamemodeGUI(player).open(player);
            }
        });

        this.setItem(23, ItemCreator.create(Material.BEDROCK, "&3God", HELP_GOD.getMessage()), (player, event) -> {
            GUICreator gui = GUICreator.getGUI(player);
            ItemStack item = event.getCurrentItem();

            assert item != null;

            if (!perms.hasPermission(player, Perms.GOD_PERM_SELF.getPermission())
                    || !perms.hasPermission(player, Perms.ALL_PERMS.getPermission()) && item.getType() == Material.BEDROCK) {
                playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("no-perm-sound")), 1.0F, 1.0F);
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&3God", CMD_NO_PERM.getMessage()));
            } else if (item.getType() != Material.BARRIER) {
                new God(player, null).execute();
                this.close(player);
            }
        });

        this.setItem(24, ItemCreator.create(Material.POTION, "&3Heal", HELP_HEAL.getMessage(), true), (player, event) -> {
            GUICreator gui = GUICreator.getGUI(player);
            ItemStack item = event.getCurrentItem();

            assert item != null;

            if (!perms.hasPermission(player, Perms.CLEARINVENTORY_PERM_SELF.getPermission())
                    || !perms.hasPermission(player, Perms.ALL_PERMS.getPermission()) && item.getType() == Material.POTION) {
                playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("no-perm-sound")), 1.0F, 1.0F);
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&3Heal", CMD_NO_PERM.getMessage()));
            } else if (item.getType() != Material.BARRIER) {
                new Heal(player, null).execute();
                this.close(player);
            }
        });

        this.setItem(25, ItemCreator.create(Material.CHEST, "&3Invsee", HELP_INVSEE.getMessage()), (player, event) -> {
            GUICreator gui = GUICreator.getGUI(player);
            ItemStack item = event.getCurrentItem();

            assert item != null;

            if (!perms.hasPermission(player, Perms.INVSEE_PERM.getPermission())
                    || !perms.hasPermission(player, Perms.ALL_PERMS.getPermission()) && item.getType() == Material.CHEST) {
                playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("no-perm-sound")), 1.0F, 1.0F);
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&3Invsee", CMD_NO_PERM.getMessage()));
            } else if (item.getType() != Material.BARRIER) {
                playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("no-perm-sound")), 1.0F, 1.0F);
                getMsgSendConfig(player, "invsee", Messenger.CMD_NO_ARGS.getMessage().replace("<command>", "invsee").replace("{0}", "<player>"));
                this.close(player);
            }
        });

        this.setItem(28, ItemCreator.create(Material.PAPER, "&3List", HELP_LIST.getMessage()), (player, event) -> {
            ItemStack item = event.getCurrentItem();

            assert item != null;

            if (item.getType() != Material.BARRIER) {
                new List(player).execute();

                this.close(player);
            }
        });

        this.setItem(29, ItemCreator.create(Material.NAME_TAG, "&3Nick", HELP_NICK.getMessage()), (player, event) -> {
            GUICreator gui = GUICreator.getGUI(player);
            ItemStack item = event.getCurrentItem();

            assert item != null;

            if (!perms.hasPermission(player, Perms.NICKNAME_PERMS.getPermission())
                    || !perms.hasPermission(player, Perms.ALL_PERMS.getPermission()) && item.getType() == Material.NAME_TAG) {
                playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("no-perm-sound")), 1.0F, 1.0F);
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&3Nick", CMD_NO_PERM.getMessage()));
            } else if (item.getType() != Material.BARRIER) {
                playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("no-perm-sound")), 1.0F, 1.0F);
                getMsgSendConfig(player, "nick", Messenger.CMD_NO_ARGS.getMessage().replace("<command>", "nick").replace("{0}", "<nick>"));
                this.close(player);
            }
        });

        this.setItem(30, ItemCreator.create(Material.EXPERIENCE_BOTTLE, "&3Ping", HELP_PING.getMessage()), (player, event) -> {
            GUICreator gui = GUICreator.getGUI(player);
            ItemStack item = event.getCurrentItem();

            assert item != null;

            if (!perms.hasPermission(player, Perms.PLAYERPINGER_PERM.getPermission())
                    || !perms.hasPermission(player, Perms.ALL_PERMS.getPermission()) && item.getType() == Material.EXPERIENCE_BOTTLE) {
                playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("no-perm-sound")), 1.0F, 1.0F);
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&3Ping", CMD_NO_PERM.getMessage()));
            } else if (item.getType() != Material.BARRIER) {
                new PlayerPinger(player, null).execute();
                this.close(player);
            }
        });

        this.setItem(31, ItemCreator.create(Material.PAPER, "&3Plugins", HELP_PLUGINS.getMessage()), (player, event) -> {
            GUICreator gui = GUICreator.getGUI(player);
            ItemStack item = event.getCurrentItem();

            assert item != null;

            if (!perms.hasPermission(player, Perms.PLUGIN_PERM.getPermission())
                    || !perms.hasPermission(player, Perms.ALL_PERMS.getPermission()) && item.getType() == Material.STONE) {
                playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("no-perm-sound")), 1.0F, 1.0F);
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&3Plugins", CMD_NO_PERM.getMessage()));
            } else if (item.getType() != Material.BARRIER) {
                new Plugins(player).execute();
                this.close(player);
            }
        });

        this.setItem(32, ItemCreator.create(Material.PAPER, "&3Server-Infos", HELP_SERVERINFO.getMessage()), (player, event) -> {
            GUICreator gui = GUICreator.getGUI(player);
            ItemStack item = event.getCurrentItem();

            assert item != null;

            if (!perms.hasPermission(player, Perms.SERVERINFO_PERM.getPermission())
                    || !perms.hasPermission(player, Perms.ALL_PERMS.getPermission()) && item.getType() == Material.PAPER) {
                playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("no-perm-sound")), 1.0F, 1.0F);
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&3Server-Infos", CMD_NO_PERM.getMessage()));
            } else if (item.getType() != Material.BARRIER) {
                new ServerInfo(player).execute();
                this.close(player);
            }
        });

        this.setItem(33, ItemCreator.create(Material.ENDER_PEARL, "&3Teleport", HELP_TELEPORT.getMessage()), (player, event) -> {
            GUICreator gui = GUICreator.getGUI(player);
            ItemStack item = event.getCurrentItem();

            assert item != null;

            if (!perms.hasPermission(player, Perms.TELEPORT_PERM.getPermission())
                    || !perms.hasPermission(player, Perms.ALL_PERMS.getPermission()) && item.getType() == Material.ENDER_PEARL) {
                playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("no-perm-sound")), 1.0F, 1.0F);
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&3Ping", CMD_NO_PERM.getMessage()));
            } else if (item.getType() != Material.BARRIER) {
                playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("no-perm-sound")), 1.0F, 1.0F);
                getMsgSendConfig(player, "teleport", Messenger.CMD_NO_ARGS.getMessage().replace("<command>", "teleport").replace("{0}", "<player | coordinates>"));
                this.close(player);
            }
        });

        this.setItem(34, ItemCreator.create(Material.ENDER_EYE, "&3Vanish", HELP_VANISH.getMessage()), (player, event) -> {
            GUICreator gui = GUICreator.getGUI(player);
            ItemStack item = event.getCurrentItem();

            assert item != null;

            if (!perms.hasPermission(player, Perms.TELEPORT_PERM.getPermission())
                    || !perms.hasPermission(player, Perms.ALL_PERMS.getPermission()) && item.getType() == Material.ENDER_EYE) {
                playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("no-perm-sound")), 1.0F, 1.0F);
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&3Vanish", CMD_NO_PERM.getMessage()));
            } else if (item.getType() != Material.BARRIER) {
                new Vanish(player, null).execute();
                this.close(player);
            }
        });

        this.setItem(37, ItemCreator.create(Material.PAPER, "&3Whois", HELP_WHOIS.getMessage()), (player, event) -> {
            GUICreator gui = GUICreator.getGUI(player);
            ItemStack item = event.getCurrentItem();

            assert item != null;

            if (!perms.hasPermission(player, Perms.WHOIS_PERM.getPermission())
                    || !perms.hasPermission(player, Perms.ALL_PERMS.getPermission()) && item.getType() == Material.PAPER) {
                playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("no-perm-sound")), 1.0F, 1.0F);
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&3Bois", CMD_NO_PERM.getMessage()));
            } else if (item.getType() != Material.BARRIER) {
                playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("no-perm-sound")), 1.0F, 1.0F);
                getMsgSendConfig(player, "whois", Messenger.CMD_NO_ARGS.getMessage().replace("<command>", "whois").replace("{0}", "<player>"));
                this.close(player);
            }
        });

        this.setItem(38, ItemCreator.create(Material.ENDER_PEARL, "&3Setspawn", HELP_SETSPAWN.getMessage()), (player, event) -> {
            GUICreator gui = GUICreator.getGUI(player);
            ItemStack item = event.getCurrentItem();

            assert item != null;

            if (!perms.hasPermission(player, Perms.SETSPAWN_PERM.getPermission())
                    || !perms.hasPermission(player, Perms.ALL_PERMS.getPermission()) && item.getType() == Material.ENDER_PEARL) {
                playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("no-perm-sound")), 1.0F, 1.0F);
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&3Setspawn", CMD_NO_PERM.getMessage()));
            } else if (item.getType() != Material.BARRIER) {
                new SetSpawn(player).execute();
                this.close(player);
            }
        });

        this.setItem(39, ItemCreator.create(Material.ENDER_PEARL, "&3Spawn", HELP_SPAWN.getMessage()), (player, event) -> {
            GUICreator gui = GUICreator.getGUI(player);
            ItemStack item = event.getCurrentItem();

            assert item != null;

            if (!perms.hasPermission(player, Perms.SPAWN_PERM.getPermission())
                    || !perms.hasPermission(player, Perms.ALL_PERMS.getPermission()) && item.getType() == Material.ENDER_PEARL) {
                playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("no-perm-sound")), 1.0F, 1.0F);
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&3Spawn", CMD_NO_PERM.getMessage()));
            } else if (item.getType() != Material.BARRIER) {
                playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("cmd-done-sound")), 1.0F, 1.0F);
                if (getInstance().getConfig().get("spawn.name") != null) {
                    new Spawn(player).execute();
                    this.close(player);
                }
            }
        });
    }
}
