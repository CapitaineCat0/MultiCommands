package me.capitainecat0.multicommands.utils;

import java.util.Collections;
import java.util.Iterator;
import java.util.Objects;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.ClickEvent.Action;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import static me.capitainecat0.multicommands.MultiCommands.getInstance;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;
import static me.capitainecat0.multicommands.utils.Messenger.HELP_LIST;

public class HelpGUI extends GUICreator {
    public HelpGUI() {
        super(54, "&6&lMultiCommands &9V." + getInstance().getDescription().getVersion() + " &cHelp");
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
        this.setItem(10, ItemCreator.create(Material.RED_BED, "&3AFK", Collections.singletonList(Messenger.HELP_AFK.getMessage())), (player, event) -> {
            GUICreator gui = GUICreator.getGUI(player);
            ItemStack item = event.getCurrentItem();

            assert item != null;

            if (!player.hasPermission(Perms.AFK_PERM.getPermission()) && item.getType() == Material.RED_BED) {
                playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("no-perm-sound")), 1.0F, 1.0F);
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&3AFK", Collections.singletonList(Messenger.CMD_NO_PERM.getMessage())));
            } else if (item.getType() != Material.BARRIER) {
                if (AFKHandler.getInstance().isAFK(player)) {
                    AFKHandler.getInstance().toggleAFK(player);
                } else {
                    AFKHandler.getInstance().toggleAFK(player);
                }

                this.close(player);
            }

        });
        this.setItem(11, ItemCreator.create(Material.STONE, "&3Alert", Collections.singletonList(Messenger.HELP_ALERT.getMessage())), (player, event) -> {
            GUICreator gui = GUICreator.getGUI(player);
            ItemStack item = event.getCurrentItem();

            assert item != null;

            if (!player.hasPermission(Perms.ALERT_PERM.getPermission()) && item.getType() == Material.STONE) {
                playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("no-perm-sound")), 1.0F, 1.0F);
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&3Alert", Collections.singletonList(Messenger.CMD_NO_PERM.getMessage())));
            } else if (item.getType() != Material.BARRIER) {
                playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("no-perm-sound")), 1.0F, 1.0F);
                getMsgSendConfig(player, "alert", Messenger.CMD_NO_ARGS.getMessage().replace("<command>", "alert").replace("{0}", "<message>"));
                this.close(player);
            }
        });
        this.setItem(12, ItemCreator.create(Material.STONE, "&3Ban", Collections.singletonList(Messenger.HELP_BAN.getMessage())), (player, event) -> {
            GUICreator gui = GUICreator.getGUI(player);
            ItemStack item = event.getCurrentItem();

            assert item != null;

            if (!player.hasPermission(Perms.BAN_PERM.getPermission()) && item.getType() == Material.STONE) {
                playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("no-perm-sound")), 1.0F, 1.0F);
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&3Ban", Collections.singletonList(Messenger.CMD_NO_PERM.getMessage())));
            } else if (item.getType() != Material.BARRIER) {
                playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("no-perm-sound")), 1.0F, 1.0F);
                getMsgSendConfig(player, "ban", Messenger.CMD_NO_ARGS.getMessage().replace("<command>", "ban").replace("{0}", "[reason]"));
                this.close(player);
            }

        });
        this.setItem(13, ItemCreator.create(Material.STONE, "&3Broadcast", Collections.singletonList(Messenger.HELP_BROADCAST.getMessage())), (player, event) -> {
            GUICreator gui = GUICreator.getGUI(player);
            ItemStack item = event.getCurrentItem();

            assert item != null;

            if (!player.hasPermission(Perms.BROADCAST_PERM.getPermission()) && item.getType() == Material.STONE) {
                playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("no-perm-sound")), 1.0F, 1.0F);
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&3Broadcast", Collections.singletonList(Messenger.CMD_NO_PERM.getMessage())));
            } else if (item.getType() != Material.BARRIER) {
                playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("no-perm-sound")), 1.0F, 1.0F);
                getMsgSendConfig(player, "broadcast", Messenger.CMD_NO_ARGS.getMessage().replace("<command>", "broadcast").replace("{0}", "<message>"));
                this.close(player);
            }

        });
        this.setItem(14, ItemCreator.create(Material.LAVA_BUCKET, "&3Clear-Inventory", Collections.singletonList(Messenger.HELP_CLEARINV.getMessage())), (player, event) -> {
            GUICreator gui = GUICreator.getGUI(player);
            ItemStack item = event.getCurrentItem();

            assert item != null;

            if (!player.hasPermission(Perms.CLEARINVENTORY_PERM_SELF.getPermission()) && item.getType() == Material.LAVA_BUCKET) {
                playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("no-perm-sound")), 1.0F, 1.0F);
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&3Clear-Inventory", Collections.singletonList(Messenger.CMD_NO_PERM.getMessage())));
            } else if (item.getType() != Material.BARRIER) {
                playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("no-perm-sound")), 1.0F, 1.0F);
                getMsgSendConfig(player, "ClearInventory", Messenger.CLEARINV_SELF_DONE.getMessage());
                player.getInventory().clear();
                this.close(player);
            }

        });
        this.setItem(15, ItemCreator.create(Material.CRAFTING_TABLE, "&3Craft", Collections.singletonList(Messenger.HELP_CRAFT.getMessage())), (player, event) -> {
            GUICreator gui = GUICreator.getGUI(player);
            ItemStack item = event.getCurrentItem();

            assert item != null;

            if (!player.hasPermission(Perms.CRAFT_PERM.getPermission()) && item.getType() == Material.CRAFTING_TABLE) {
                playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("no-perm-sound")), 1.0F, 1.0F);
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&3Craft", Collections.singletonList(Messenger.CMD_NO_PERM.getMessage())));
            } else if (item.getType() != Material.BARRIER) {
                playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("no-perm-sound")), 1.0F, 1.0F);
                player.openWorkbench(player.getLocation(), true);
            }

        });
        this.setItem(16, ItemCreator.create(Material.ENDER_CHEST, "&3Ender-Chest", Collections.singletonList(Messenger.HELP_ENDERCHEST.getMessage())), (player, event) -> {
            GUICreator gui = GUICreator.getGUI(player);
            ItemStack item = event.getCurrentItem();

            assert item != null;

            if (!player.hasPermission(Perms.ENDERCHEST_PERM_SELF.getPermission()) && item.getType() == Material.ENDER_CHEST) {
                playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("no-perm-sound")), 1.0F, 1.0F);
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&3Ender-Chest", Collections.singletonList(Messenger.CMD_NO_PERM.getMessage())));
            } else if (item.getType() != Material.BARRIER) {
                playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("no-perm-sound")), 1.0F, 1.0F);
                player.openInventory(player.getEnderChest());
            }

        });
        this.setItem(19, ItemCreator.create(Material.COOKED_CHICKEN, "&3Feed", Collections.singletonList(Messenger.HELP_FEED.getMessage())), (player, event) -> {
            GUICreator gui = GUICreator.getGUI(player);
            ItemStack item = event.getCurrentItem();

            assert item != null;

            if (!player.hasPermission(Perms.FEED_PERM_SELF.getPermission()) && item.getType() == Material.COOKED_CHICKEN) {
                playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("no-perm-sound")), 1.0F, 1.0F);
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&3Feed", Collections.singletonList(Messenger.CMD_NO_PERM.getMessage())));
            } else if (item.getType() != Material.BARRIER) {
                if (player.getFoodLevel() != 20) {
                    player.setFoodLevel(20);
                    playSoundIfEnabled(player, Sound.ENTITY_GENERIC_EAT, 1.0F, 1.0F);
                    getMsgSendConfig(player, "feed", Messenger.FEED_SELF.getMessage());
                } else {
                    playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("no-perm-sound")), 1.0F, 1.0F);
                    getMsgSendConfig(player, "feed", Messenger.FEED_ALREADY.getMessage());
                }

                this.close(player);
            }

        });
        this.setItem(20, ItemCreator.create(Material.FEATHER, "&3Fly", Collections.singletonList(Messenger.HELP_FLY.getMessage())), (player, event) -> {
            GUICreator gui = GUICreator.getGUI(player);
            ItemStack item = event.getCurrentItem();

            assert item != null;

            if (!player.hasPermission(Perms.FLY_PERM_SELF.getPermission()) && item.getType() == Material.FEATHER) {
                playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("no-perm-sound")), 1.0F, 1.0F);
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&3Feed", Collections.singletonList(Messenger.CMD_NO_PERM.getMessage())));
            } else if (item.getType() != Material.BARRIER) {
                if (!player.getAllowFlight()) {
                    player.setAllowFlight(true);
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
        this.setItem(21, ItemCreator.create(Material.BLUE_ICE, "&3Freeze", Collections.singletonList(Messenger.HELP_FREEZE.getMessage())), (player, event) -> {
            GUICreator gui = GUICreator.getGUI(player);
            ItemStack item = event.getCurrentItem();

            assert item != null;

            if (!player.hasPermission(Perms.FREEZE_PERM.getPermission()) && item.getType() == Material.BLUE_ICE) {
                playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("no-perm-sound")), 1.0F, 1.0F);
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&3Freeze", Collections.singletonList(Messenger.CMD_NO_PERM.getMessage())));
            } else if (item.getType() != Material.BARRIER) {
                playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("no-perm-sound")), 1.0F, 1.0F);
                getMsgSendConfig(player, "freeze", Messenger.CMD_NO_ARGS.getMessage().replace("<command>", "freeze").replace("{0}", "<player>"));
                this.close(player);
            }

        });
        this.setItem(22, ItemCreator.create(Material.BOOKSHELF, "&3Gamemode", Collections.singletonList(Messenger.HELP_GAMEMODE.getMessage())), (player, event) -> {
            GUICreator gui = GUICreator.getGUI(player);
            ItemStack item = event.getCurrentItem();

            assert item != null;

            if (!player.hasPermission(Perms.GAMEMODE_PERM_ALL.getPermission()) && item.getType() == Material.BOOKSHELF) {
                playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("no-perm-sound")), 1.0F, 1.0F);
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&3Gamemode", Collections.singletonList(Messenger.CMD_NO_PERM.getMessage())));
            } else {
                (new GamemodeGUI()).open(player);
            }
        });
        this.setItem(23, ItemCreator.create(Material.BEDROCK, "&3God", Collections.singletonList(Messenger.HELP_GOD.getMessage())), (player, event) -> {
            GUICreator gui = GUICreator.getGUI(player);
            ItemStack item = event.getCurrentItem();

            assert item != null;

            if (!player.hasPermission(Perms.GOD_PERM_SELF.getPermission()) && item.getType() == Material.BEDROCK) {
                playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("no-perm-sound")), 1.0F, 1.0F);
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&3God", Collections.singletonList(Messenger.CMD_NO_PERM.getMessage())));
            } else if (item.getType() != Material.BARRIER) {
                if (player.isInvulnerable()) {
                    player.setInvulnerable(false);
                    player.setGlowing(false);
                    playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("cmd-done-sound")), 1.0F, 1.0F);
                    getMsgSendConfig(player, "god", Messenger.GOD_SELF_OFF.getMessage());
                } else if (!player.isInvulnerable()) {
                    player.setInvulnerable(true);
                    player.setGlowing(true);
                    playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("cmd-done-sound")), 1.0F, 1.0F);
                    getMsgSendConfig(player, "god", Messenger.GOD_SELF_ON.getMessage());
                }
                this.close(player);
            }
        });
        this.setItem(24, ItemCreator.create(Material.POTION, "&3Heal", Collections.singletonList(Messenger.HELP_HEAL.getMessage()), true), (player, event) -> {
            GUICreator gui = GUICreator.getGUI(player);
            ItemStack item = event.getCurrentItem();

            assert item != null;

            if (!player.hasPermission(Perms.CLEARINVENTORY_PERM_SELF.getPermission()) && item.getType() == Material.POTION) {
                playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("no-perm-sound")), 1.0F, 1.0F);
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&3Heal", Collections.singletonList(Messenger.CMD_NO_PERM.getMessage())));
            } else if (item.getType() != Material.BARRIER) {
                if (player.getHealth() != 20.0) {
                    player.setHealth(20.0);
                    playSoundIfEnabled(player, Sound.ENTITY_GENERIC_DRINK, 1.0F, 1.0F);
                    getMsgSendConfig(player, "heal", Messenger.HEAL_SELF.getMessage());
                } else {
                    playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("no-perm-sound")), 1.0F, 1.0F);
                    getMsgSendConfig(player, "heal", Messenger.HEAL_ALREADY.getMessage());
                }
                this.close(player);
            }
        });
        this.setItem(25, ItemCreator.create(Material.BOOK, "&3Invsee", Collections.singletonList(Messenger.HELP_INVSEE.getMessage())), (player, event) -> {
            GUICreator gui = GUICreator.getGUI(player);
            ItemStack item = event.getCurrentItem();

            assert item != null;

            if (!player.hasPermission(Perms.INVSEE_PERM.getPermission()) && item.getType() == Material.BOOK) {
                playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("no-perm-sound")), 1.0F, 1.0F);
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&3Invsee", Collections.singletonList(Messenger.CMD_NO_PERM.getMessage())));
            } else if (item.getType() != Material.BARRIER) {
                playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("no-perm-sound")), 1.0F, 1.0F);
                getMsgSendConfig(player, "invsee", Messenger.CMD_NO_ARGS.getMessage().replace("<command>", "invsee").replace("{0}", "<player>"));
                this.close(player);
            }
        });
        this.setItem(28, ItemCreator.create(Material.PAPER, "&3List", Collections.singletonList(HELP_LIST.getMessage())), (player, event) -> {
            ItemStack item = event.getCurrentItem();

            assert item != null;

            if (item.getType() != Material.BARRIER) {
                playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("cmd-done-sound")), 1.0F, 1.0F);
                sendMessage(player, HELP_LIST.getMessage().replace("{0}", Bukkit.getOnlinePlayers().size()+"").replace("{1}", Bukkit.getServer().getMaxPlayers()+""));
                sendMessage(player, "");
                Iterator i = Bukkit.getOnlinePlayers().iterator();

                while(i.hasNext()) {
                    Player p = (Player)i.next();
                    TextComponent playerTP;
                    if (Objects.equals(p.getCustomName(), p.getName())) {
                        playerTP = new TextComponent("  §e- §b" + p.getName() + " §c-> §8[]");
                        if (player != null) {
                            playerTP.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/tp " + p.getName()));
                            sendMessage(player, playerTP.getFont());
                        }
                    } else {
                        playerTP = new TextComponent("  §e- §b" + p.getName() + " §c-> §8[ " + p.getCustomName() + " §8]");
                        if (player != null) {
                            playerTP.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/tp " + p.getName()));
                            sendMessage(player, playerTP.getFont());
                        }
                    }
                }

                assert player != null;

                this.close(player);
            }

        });
        this.setItem(29, ItemCreator.create(Material.NAME_TAG, "&3Nick", Collections.singletonList(Messenger.HELP_NICK.getMessage())), (player, event) -> {
            GUICreator gui = GUICreator.getGUI(player);
            ItemStack item = event.getCurrentItem();

            assert item != null;

            if (!player.hasPermission(Perms.NICKNAME_PERMS.getPermission()) && item.getType() == Material.NAME_TAG) {
                playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("no-perm-sound")), 1.0F, 1.0F);
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&3Nick", Collections.singletonList(Messenger.CMD_NO_PERM.getMessage())));
            } else if (item.getType() != Material.BARRIER) {
                playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("no-perm-sound")), 1.0F, 1.0F);
                getMsgSendConfig(player, "nick", Messenger.CMD_NO_ARGS.getMessage().replace("<command>", "nick").replace("{0}", "<nick>"));
                this.close(player);
            }
        });
        this.setItem(30, ItemCreator.create(Material.EXPERIENCE_BOTTLE, "&3Ping", Collections.singletonList(Messenger.HELP_PING.getMessage())), (player, event) -> {
            GUICreator gui = GUICreator.getGUI(player);
            ItemStack item = event.getCurrentItem();

            assert item != null;

            if (!player.hasPermission(Perms.PLAYERPINGER_PERM.getPermission()) && item.getType() == Material.EXPERIENCE_BOTTLE) {
                playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("no-perm-sound")), 1.0F, 1.0F);
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&3Ping", Collections.singletonList(Messenger.CMD_NO_PERM.getMessage())));
            } else if (item.getType() != Material.BARRIER) {
                playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("cmd-done-sound")), 1.0F, 1.0F);
                int ping = 0;
                if (ping < 50) {
                    sendMessage(player, Messenger.PING_SELF_MSG.getMessage().replace("{0}", colored("&a" + ping + " ms")));
                }

                if (ping > 50) {
                    sendMessage(player, Messenger.PING_SELF_MSG.getMessage().replace("{0}", colored("&e" + ping + " ms")));
                }

                if (ping > 300) {
                    sendMessage(player, Messenger.PING_SELF_MSG.getMessage().replace("{0}", colored("&c" + ping + " ms")));
                }

                this.close(player);
            }

        });
        this.setItem(31, ItemCreator.create(Material.PAPER, "&3Plugins", Collections.singletonList(Messenger.HELP_PLUGINS.getMessage())), (player, event) -> {
            GUICreator gui = GUICreator.getGUI(player);
            ItemStack item = event.getCurrentItem();

            assert item != null;

            if (!player.isOp() && item.getType() == Material.STONE) {
                playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("no-perm-sound")), 1.0F, 1.0F);
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&3Plugins", Collections.singletonList(Messenger.CMD_NO_PERM.getMessage())));
            } else if (item.getType() != Material.BARRIER) {
                playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("cmd-done-sound")), 1.0F, 1.0F);
                sendMessage(player, "&6Plugins loaded &7(&c" + Bukkit.getPluginManager().getPlugins().length + "&7)&8:");
                Plugin[] pm = Bukkit.getPluginManager().getPlugins();
                for (Plugin p : pm) {
                    try{
                        if(p.isEnabled()){
                            sendMessage(player, "  &3- &b" + p.getName() + " &8" + p.getDescription().getVersion() + " &a(enabled)");
                        }else{
                            sendMessage(player, "  &3- &b" + p.getName() + " &8" + p.getDescription().getVersion() + " &c(disabled)");
                        }
                    }catch(Error e){
                        sendMessage(player, "&cAn error occurred :&e" + e);
                    }
                }
                this.close(player);
            }
        });
        this.setItem(32, ItemCreator.create(Material.PAPER, "&3Server-Infos", Collections.singletonList(Messenger.HELP_SERVERINFO.getMessage())), (player, event) -> {
            GUICreator gui = GUICreator.getGUI(player);
            ItemStack item = event.getCurrentItem();

            assert item != null;

            if (!player.hasPermission(Perms.SERVERINFO_PERM.getPermission()) && item.getType() == Material.PAPER) {
                playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("no-perm-sound")), 1.0F, 1.0F);
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&3Server-Infos", Collections.singletonList(Messenger.CMD_NO_PERM.getMessage())));
            } else if (item.getType() != Material.BARRIER) {
                playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("cmd-done-sound")), 1.0F, 1.0F);
                sendMessage(player, "&a&m-+---------------+- &7 - &e&l{ &cServer Informations &e&l} &7- &a&m-+---------------+-");
                sendMessage(player, "&6IP &c/&6 Port: &c" + Bukkit.getServer().getIp() + " &e:&c " + Bukkit.getServer().getPort());
                sendMessage(player, "&6Jar: &c" + Bukkit.getServer().getVersion());
                sendMessage(player, "&6Version: &c" + Bukkit.getServer().getBukkitVersion());
                sendMessage(player, "&6Players: &c" + Bukkit.getServer().getOnlinePlayers().size() + "&e / &c" + Bukkit.getServer().getMaxPlayers());
                sendMessage(player, "&6Operators: &7(&c" + Bukkit.getServer().getOperators().size() + "&7)");
                Iterator i = Bukkit.getOperators().iterator();

                OfflinePlayer afk;
                while(i.hasNext()) {
                    afk = (OfflinePlayer)i.next();
                    sendMessage(player, "&c- &7" + afk.getName());
                }

                sendMessage(player, "&6Whitelisted: &7(&c" + Bukkit.getServer().getWhitelistedPlayers().size() + "&7)");
                i = Bukkit.getWhitelistedPlayers().iterator();

                while(i.hasNext()) {
                    afk = (OfflinePlayer)i.next();
                    sendMessage(player, "&c- &7" + afk.getName());
                }

                sendMessage(player, "&6Banned: &7(&c" + Bukkit.getServer().getBannedPlayers().size() + "&7) ");
                i = Bukkit.getBannedPlayers().iterator();

                while(i.hasNext()) {
                    afk = (OfflinePlayer)i.next();
                    sendMessage(player, "&c- &7" + afk.getName());
                }

                sendMessage(player, "&6Vanished: &7(&c" + VanishHandler.getVanished().size() + "&7)");
                i = VanishHandler.getVanished().iterator();

                while(i.hasNext()) {
                    afk = (OfflinePlayer)i.next();
                    sendMessage(player, "&c- &7" + afk.getName());
                }

                sendMessage(player, "&6AFK: &7(&c" + AFKHandler.getAFK().size() + "&7)");
                i = AFKHandler.getAFK().iterator();

                while(i.hasNext()) {
                    afk = (OfflinePlayer)i.next();
                    sendMessage(player, "&c- &7" + afk.getName());
                }

                /*try {
                    Class.forName("me.capitainecat0.multimaintenance.MultiMaintenance");
                    MessengerUtils.sendMessage(player, "&6Whitelisted players &8(multimaintenance) &7(&c" + MultiMaintenance.getAUTHORIZED().size() + "&7)");
                    Iterator var9 = MultiMaintenance.getAUTHORIZED().iterator();

                    while(var9.hasNext()) {
                        UUID uuid = (UUID)var9.next();
                        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(uuid);
                        sendMessage(player, "&e-&a " + player.getName());
                    }

                    if (MultiMaintenance.ENABLED) {
                        MessengerUtils.sendMessage(player, me.capitainecat0.multimaintenance.utils.Messenger.MAINTENANCE_STATUS.getMessage().replace("%status", "&aactive"));
                    } else {
                        MessengerUtils.sendMessage(player, me.capitainecat0.multimaintenance.utils.Messenger.MAINTENANCE_STATUS.getMessage().replace("%status", "&cinactive"));
                    }
                } catch (Exception var7) {
                    sendMessage(player, "&7Install &cMulti§&Maintenance &7to show more informations!");
                }*/

                sendMessage(player, "&a&m-+----------------------------------------------------------------+-");
            }

        });
        this.setItem(33, ItemCreator.create(Material.ENDER_PEARL, "&3Teleport", Collections.singletonList(Messenger.HELP_TELEPORT.getMessage())), (player, event) -> {
            GUICreator gui = GUICreator.getGUI(player);
            ItemStack item = event.getCurrentItem();

            assert item != null;

            if (!player.hasPermission(Perms.TELEPORT_PERM.getPermission()) && item.getType() == Material.ENDER_PEARL) {
                playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("no-perm-sound")), 1.0F, 1.0F);
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&3Ping", Collections.singletonList(Messenger.CMD_NO_PERM.getMessage())));
            } else if (item.getType() != Material.BARRIER) {
                playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("no-perm-sound")), 1.0F, 1.0F);
                getMsgSendConfig(player, "teleport", Messenger.CMD_NO_ARGS.getMessage().replace("<command>", "teleport").replace("{0}", "<player | coordinates>"));
                this.close(player);
            }
        });
        this.setItem(34, ItemCreator.create(Material.ENDER_EYE, "&3Vanish", Collections.singletonList(Messenger.HELP_VANISH.getMessage())), (player, event) -> {
            GUICreator gui = GUICreator.getGUI(player);
            ItemStack item = event.getCurrentItem();

            assert item != null;

            if (!player.hasPermission(Perms.TELEPORT_PERM.getPermission()) && item.getType() == Material.ENDER_EYE) {
                playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("no-perm-sound")), 1.0F, 1.0F);
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&3Vanish", Collections.singletonList(Messenger.CMD_NO_PERM.getMessage())));
            } else if (item.getType() != Material.BARRIER) {
                VanishHandler handler = VanishHandler.getInstance();
                if (!handler.isVanished(player)) {
                    handler.toggleVanish(player);
                    playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("cmd-done-sound")), 1.0F, 1.0F);
                    getMsgSendConfig(player, "vanish", Messenger.VANISH_ENABLED_SELF.getMessage());
                } else if (handler.isVanished(player)) {
                    handler.toggleVanish(player);
                    playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("cmd-done-sound")), 1.0F, 1.0F);
                    getMsgSendConfig(player, "vanish", Messenger.VANISH_DISABLED_SELF.getMessage());
                }

                this.close(player);
            }

        });
        this.setItem(37, ItemCreator.create(Material.PAPER, "&3Whois", Collections.singletonList(Messenger.HELP_WHOIS.getMessage())), (player, event) -> {
            GUICreator gui = GUICreator.getGUI(player);
            ItemStack item = event.getCurrentItem();

            assert item != null;

            if (!player.hasPermission(Perms.WHOIS_PERM.getPermission()) && item.getType() == Material.PAPER) {
                playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("no-perm-sound")), 1.0F, 1.0F);
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&3Bois", Collections.singletonList(Messenger.CMD_NO_PERM.getMessage())));
            } else if (item.getType() != Material.BARRIER) {
                playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("no-perm-sound")), 1.0F, 1.0F);
                getMsgSendConfig(player, "whois", Messenger.CMD_NO_ARGS.getMessage().replace("<command>", "whois").replace("{0}", "<player>"));
                this.close(player);
            }
        });
        this.setItem(38, ItemCreator.create(Material.ENDER_PEARL, "&3Setspawn", Collections.singletonList(Messenger.HELP_SETSPAWN.getMessage())), (player, event) -> {
            GUICreator gui = GUICreator.getGUI(player);
            ItemStack item = event.getCurrentItem();

            assert item != null;

            if (!player.hasPermission(Perms.SETSPAWN_PERM.getPermission()) && item.getType() == Material.ENDER_PEARL) {
                playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("no-perm-sound")), 1.0F, 1.0F);
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&3Setspawn", Collections.singletonList(Messenger.CMD_NO_PERM.getMessage())));
            } else if (item.getType() != Material.BARRIER) {
                playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("cmd-done-sound")), 1.0F, 1.0F);
                Location location = player.getLocation();
                getInstance().getConfig().set("spawn.name", location.getWorld().getName());
                getInstance().getConfig().set("spawn.x", location.getX());
                getInstance().getConfig().set("spawn.y", location.getY());
                getInstance().getConfig().set("spawn.z", location.getZ());
                getInstance().getConfig().set("spawn.yaw", location.getYaw());
                getInstance().getConfig().set("spawn.pitch", location.getPitch());
                getInstance().saveConfig();
                getMsgSendConfig(player, "setspawn", Messenger.CMD_NO_PERM.getMessage().replace("<command>", "setspawn"));
                this.close(player);
            }
        });
        this.setItem(39, ItemCreator.create(Material.ENDER_PEARL, "&3Spawn", Collections.singletonList(Messenger.HELP_SPAWN.getMessage())), (player, event) -> {
            GUICreator gui = GUICreator.getGUI(player);
            ItemStack item = event.getCurrentItem();

            assert item != null;

            if (!player.hasPermission(Perms.SPAWN_PERM.getPermission()) && item.getType() == Material.ENDER_PEARL) {
                playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("no-perm-sound")), 1.0F, 1.0F);
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&3Spawn", Collections.singletonList(Messenger.CMD_NO_PERM.getMessage())));
            } else if (item.getType() != Material.BARRIER) {
                playSoundIfEnabled(player, Sound.valueOf(getInstance().getConfig().getString("cmd-done-sound")), 1.0F, 1.0F);
                if (getInstance().getConfig().get("spawn.name") != null) {
                    Location location = new Location(Bukkit.getWorld((String)Objects.requireNonNull(getInstance().getConfig().getString("spawn.name"))), getInstance().getConfig().getDouble("spawn.x"), getInstance().getConfig().getDouble("spawn.y"), getInstance().getConfig().getDouble("spawn.z"), (float) getInstance().getConfig().getInt("spawn.yaw"), (float) getInstance().getConfig().getInt("spawn.pitch"));
                    player.teleport(location);
                    getMsgSendConfig(player, "spawn", Messenger.CMD_NO_PERM.getMessage().replace("<command>", "spawn"));
                    this.close(player);
                }
            }
        });
    }
}
