package me.capitainecat0.multicommands.utils.inventories;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.permissions.PermissionManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;
import static me.capitainecat0.multicommands.utils.permissions.Perms.*;

public class PlayerGUI extends GUICreator {
    private static int online = Bukkit.getOnlinePlayers().size();
    private static int size = online;

    public PlayerGUI() {
        super(size, "&6&lMultiCommands &9" + Bukkit.getOnlinePlayers().size() + " &aPlayers");
            if (Bukkit.getServer().getOnlinePlayers().size() <= 9) {
                size = 1;
                if(online == Bukkit.getOnlinePlayers().size()){
                    online = 9;
                }
            } else if (Bukkit.getServer().getOnlinePlayers().size() <= 18) {
                size = 2;
                if(online != Bukkit.getOnlinePlayers().size()){
                    online = 18;
                }
            } else if (Bukkit.getServer().getOnlinePlayers().size() <= 27) {
                size = 3;
                if(online != Bukkit.getOnlinePlayers().size()){
                    online = 27;
                }
            } else if (Bukkit.getServer().getOnlinePlayers().size() <= 36) {
                size = 4;
                if(online != Bukkit.getOnlinePlayers().size()){
                    online = 36;
                }
            } else if (Bukkit.getServer().getOnlinePlayers().size() <= 45) {
                size = 5;
                if(online != Bukkit.getOnlinePlayers().size()){
                    online = 45;
                }
            } else if (Bukkit.getServer().getOnlinePlayers().size() <= 54) {
                size = 6;
                if(online != Bukkit.getOnlinePlayers().size()){
                    online = 54;
                }
            }
        updatePlayerHeads();
    }
    public void updatePlayerHeads() {
        // Obtention de tous les joueurs en ligne
        Player[] players = Bukkit.getOnlinePlayers().toArray(new Player[0]);
        for(int i=0; i < players.length; i++) {
            Player player = players[i];
            ItemStack playerHead = getPlayerHead(player.getName());
            // On place l'item (la tête du joueur) à un index spécifique dans l'inventaire
            setItem(i, playerHead, (sender, event) -> {
                GUICreator gui = GUICreator.getGUI(sender);
                ItemStack item = event.getCurrentItem();
                assert item != null;
                PermissionManager perms = MultiCommands.getPermissionManager();
                if(!perms.hasPermission(sender, GAMEMODE_PERM_ALL.getPermission())
                        || !perms.hasPermission(sender, ALL_PERMS.getPermission())){
                    playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                    gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "<aqua>Gamemode survival", CMD_NO_PERM.getMessage()));
                }
                else{
                    if(item.getType() != Material.BARRIER){
                        new ManagePlayerGUI(player).open(sender);
                    }
                }
            });
        }
    }

    private @NotNull ItemStack getPlayerHead(String name) {
        ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) playerHead.getItemMeta();
        Component playerName = Component.text(name);

        // On définit le nom du joueur
        meta.displayName(playerName);
        Component desc = MiniMessage.miniMessage().deserialize("<gray>Manage <aqua>"+name+"</aqua>");
        meta.lore(List.of(desc));
        // On définit la texture de la tête du joueur
        meta.setOwningPlayer(Bukkit.getOfflinePlayer(name));

        playerHead.setItemMeta(meta);

        return playerHead;
    }
}
