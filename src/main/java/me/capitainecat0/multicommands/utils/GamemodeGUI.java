package me.capitainecat0.multicommands.utils;

import me.capitainecat0.multicommands.MultiCommands;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;

import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;
import static me.capitainecat0.multicommands.utils.Perms.*;

public class GamemodeGUI extends GUICreator{
    public GamemodeGUI() {
        super(9, "&6&lMultiHelp &9V."+ MultiCommands.getInstance().getDescription().getVersion()+" &aGamemode");
        setItem(0, ItemCreator.create(Material.BLACK_STAINED_GLASS_PANE, " "));
        setItem(1, ItemCreator.create(Material.BLACK_STAINED_GLASS_PANE, " "));
        setItem(2, ItemCreator.create(Material.BLACK_STAINED_GLASS_PANE, " "));

        setItem(3, ItemCreator.create(Material.IRON_SWORD, "&6Gamemode survie"), (player, event) -> {
            GUICreator gui = GUICreator.getGUI(player);
            ItemStack item = event.getCurrentItem();
            assert item != null;
            if(!player.hasPermission(GAMEMODE_PERM_ALL.getPermission()) || !player.hasPermission(GAMEMODE_CREATIVE_PERM_SELF.getPermission()) || !player.hasPermission(ALL_PERMS.getPermission()) && item.getType() == Material.IRON_SWORD){
                if(soundEnabled()){
                    playSound(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                }
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&3Gamemode survival", Collections.singletonList(CMD_NO_PERM.getMessage())));
            }
            else{
                if(item.getType() != Material.BARRIER){
                    player.setGameMode(GameMode.SURVIVAL);
                    if(soundEnabled()){
                        playSound(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                    }
                    getMsgSendConfig(player, "gamemode survival", GAMEMODE_SELF.getMessage().replace("%gamemode%", "survival"));
                    close(player);
                }
            }
        });
        setItem(4, ItemCreator.create(Material.GRASS_BLOCK, "&6Gamemode creative"), (player, event) -> {
            GUICreator gui = GUICreator.getGUI(player);
            ItemStack item = event.getCurrentItem();
            assert item != null;
            if(!player.hasPermission(GAMEMODE_PERM_ALL.getPermission()) || !player.hasPermission(GAMEMODE_CREATIVE_PERM_SELF.getPermission()) || !player.hasPermission(ALL_PERMS.getPermission()) && item.getType() == Material.GRASS_BLOCK){
                if(soundEnabled()){
                    playSound(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                }
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&3Gamemode creative", Collections.singletonList(CMD_NO_ARGS.getMessage())));
            }
            else{
                if(item.getType() != Material.BARRIER){
                    player.setGameMode(GameMode.CREATIVE);
                    if(soundEnabled()){
                        playSound(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                    }
                    getMsgSendConfig(player, "gamemode creative", GAMEMODE_SELF.getMessage().replace("%gamemode%", "creative"));
                    close(player);
                }
            }
        });
        setItem(5, ItemCreator.create(Material.MAP, "&6Gamemode adventure"), (player, event) -> {
            GUICreator gui = GUICreator.getGUI(player);
            ItemStack item = event.getCurrentItem();
            assert item != null;
            if(!player.hasPermission(GAMEMODE_PERM_ALL.getPermission()) || !player.hasPermission(GAMEMODE_ADVENTURE_PERM_SELF.getPermission()) || !player.hasPermission(ALL_PERMS.getPermission()) && item.getType() == Material.MAP){
                if(soundEnabled()){
                    playSound(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                }
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&3Gamemode adventure", Collections.singletonList(CMD_NO_PERM.getMessage())));
            }
            else{
                if(item.getType() != Material.BARRIER){
                    player.setGameMode(GameMode.ADVENTURE);
                    if(soundEnabled()){
                        playSound(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                    }
                    getMsgSendConfig(player, "gamemode adventure", GAMEMODE_SELF.getMessage().replace("%gamemode%", "adventure"));
                    close(player);
                }
            }
        });
        setItem(6, ItemCreator.create(Material.ENDER_EYE, "&6Gamemode spectator"), (player, event) -> {
            GUICreator gui = GUICreator.getGUI(player);
            ItemStack item = event.getCurrentItem();
            assert item != null;
            if(!player.hasPermission(GAMEMODE_PERM_ALL.getPermission()) || !player.hasPermission(GAMEMODE_SPECTATOR_PERM_SELF.getPermission()) || !player.hasPermission(ALL_PERMS.getPermission()) && item.getType() == Material.ENDER_EYE){
                if(soundEnabled()){
                    playSound(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                }
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&3Gamemode spectateur", Collections.singletonList("&cVous n'avez pas la permission d'utiliser cette commande!")));
            }
            else{
                if(item.getType() != Material.BARRIER){
                    player.setGameMode(GameMode.SPECTATOR);
                    if(soundEnabled()){
                        playSound(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                    }
                    getMsgSendConfig(player, "gamemode spectator", GAMEMODE_SELF.getMessage().replace("%gamemode%", "spectator"));
                    close(player);
                }
            }
        });

        setItem(7, ItemCreator.create(Material.BLACK_STAINED_GLASS_PANE, " "));
        setItem(8, ItemCreator.create(Material.BLACK_STAINED_GLASS_PANE, " "));
        setItem(9, ItemCreator.create(Material.BLACK_STAINED_GLASS_PANE, " "));

    }
}