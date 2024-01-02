package me.capitainecat0.multicommands.utils.inventories;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.permissions.PermissionManager;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;
import static me.capitainecat0.multicommands.utils.permissions.Perms.*;

public class GamemodeGUI extends GUICreator{

    /**
     * The GamemodeGUI function is a GUI that allows the player to change their game mode.
     * The function has 9 slots, and each slot has an item in it.
     * The first 5 slots are black stained-glass panes, which are just there for decoration purposes.
     * <br>Slot 1 contains an iron sword, which when clicked will set the player's gamemode to survival mode if they have permission to do so (gamemode_survival_self). If not, a barrier will appear instead of the iron sword and no action will be taken by clicking on it.
     * <br>Slot 3 contains a grass block, which when clicked will set the player's gamemode to creative mode if they have permission to do so (gamemode_creative_self). If not, a barrier will appear instead of the grass block and no action will be taken by clicking on it.
     * <br>Slot 5 contains a map item, which when clicked will set the player's gamemode to adventure mode if they have permission to do so (gamemode_adventure_self). If not, a barrier will appear instead of the map item and no action will be taken by clicking on it.
     * <br>Slot 7 contains an ender eye, which when clicked will set the player's gamemode to spectator mode if they have permission to do so (gamemode_spectator_self). If not, a barrier will appear instead of the ender eye and no action will be taken by clicking on it.
     */
    public GamemodeGUI(Player target){
        super(1, "&6&lMultiCommands &9V."+ MultiCommands.getInstance().getDescription().getVersion()+" &aGamemode");
        PermissionManager perms = MultiCommands.getPermissionManager();
        setItem(0, ItemCreator.create(Material.BLACK_STAINED_GLASS_PANE, " "));
        setItem(2, ItemCreator.create(Material.BLACK_STAINED_GLASS_PANE, " "));
        setItem(4, ItemCreator.create(Material.BLACK_STAINED_GLASS_PANE, " "));
        setItem(6, ItemCreator.create(Material.BLACK_STAINED_GLASS_PANE, " "));
        setItem(8, ItemCreator.create(Material.BLACK_STAINED_GLASS_PANE, " "));

        setItem(1, ItemCreator.create(Material.IRON_SWORD, "&aSurvival", "<gray>Change to <green>Survival <gray>mode" ,true), (player, event) -> {
            GUICreator gui = GUICreator.getGUI(player);
            ItemStack item = event.getCurrentItem();
            assert item != null;
            if(!perms.hasPermission(player, GAMEMODE_PERM_ALL.getPermission())
                    || !perms.hasPermission(player, GAMEMODE_SURVIVAL_PERM_SELF.getPermission())
                    || !perms.hasPermission(player, ALL_PERMS.getPermission()) && item.getType() == Material.IRON_SWORD){
                playSoundIfEnabled(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "<aqua>Gamemode survival", CMD_NO_PERM.getMessage()));
            }
            else{
                if(item.getType() != Material.BARRIER){
                    target.setGameMode(GameMode.SURVIVAL);
                    playSoundIfEnabled(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                    getMsgSendConfig(player, "gamemode survival", GAMEMODE_SELF.getMessage().replace("{0}", "survival"));
                    close(player);
                }
            }
        });

        setItem(3, ItemCreator.create(Material.GRASS_BLOCK, "&cCreative", "<gray>Change to <red>Creative <gray>mode" ), (player, event) -> {
            GUICreator gui = GUICreator.getGUI(player);
            ItemStack item = event.getCurrentItem();
            assert item != null;
            if(!perms.hasPermission(player, GAMEMODE_PERM_ALL.getPermission())
                    || !perms.hasPermission(player, GAMEMODE_CREATIVE_PERM_SELF.getPermission())
                    || !perms.hasPermission(player, ALL_PERMS.getPermission()) && item.getType() == Material.GRASS_BLOCK){
               playSoundIfEnabled(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
               gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "<aqua>Gamemode creative", CMD_NO_PERM.getMessage()));
            }
            else{
                if(item.getType() != Material.BARRIER){
                    target.setGameMode(GameMode.CREATIVE);
                    playSoundIfEnabled(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                    getMsgSendConfig(player, "gamemode creative", GAMEMODE_SELF.getMessage().replace("{0}", "creative"));
                    close(player);
                }
            }
        });

        setItem(5, ItemCreator.create(Material.MAP, "&2Adventure", "<gray>Change to <dark_green>Adventure <gray>mode" ), (player, event) -> {
            GUICreator gui = GUICreator.getGUI(player);
            ItemStack item = event.getCurrentItem();
            assert item != null;
            if(!perms.hasPermission(player, GAMEMODE_PERM_ALL.getPermission())
                    || !perms.hasPermission(player, GAMEMODE_ADVENTURE_PERM_SELF.getPermission())
                    || !perms.hasPermission(player, ALL_PERMS.getPermission()) && item.getType() == Material.MAP){
                playSoundIfEnabled(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "<aqua>Gamemode adventure", CMD_NO_PERM.getMessage()));
            }
            else{
                if(item.getType() != Material.BARRIER){
                    target.setGameMode(GameMode.ADVENTURE);
                    playSoundIfEnabled(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                    getMsgSendConfig(player, "gamemode adventure", GAMEMODE_SELF.getMessage().replace("{0}", "adventure"));
                    close(player);
                }
            }
        });

        setItem(7, ItemCreator.create(Material.ENDER_EYE, "&8Spectator", "<gray>Change to <dark_gray>Spectator <gray>mode" ), (player, event) -> {
            GUICreator gui = GUICreator.getGUI(player);
            ItemStack item = event.getCurrentItem();
            assert item != null;
            if(!perms.hasPermission(player, GAMEMODE_PERM_ALL.getPermission())
                    || !perms.hasPermission(player, GAMEMODE_SPECTATOR_PERM_SELF.getPermission())
                    || !perms.hasPermission(player, ALL_PERMS.getPermission()) && item.getType() == Material.ENDER_EYE){
                playSoundIfEnabled(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "<aqua>Gamemode spectator", CMD_NO_PERM.getMessage()));
            }
            else{
                if(item.getType() != Material.BARRIER){
                    target.setGameMode(GameMode.SPECTATOR);
                    playSoundIfEnabled(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                    getMsgSendConfig(player, "gamemode spectator", GAMEMODE_SELF.getMessage().replace("{0}", "spectator"));
                    close(player);
                }
            }
        });
    }
}
