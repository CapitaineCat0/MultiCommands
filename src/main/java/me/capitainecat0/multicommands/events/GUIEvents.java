package me.capitainecat0.multicommands.events;

import me.capitainecat0.multicommands.utils.GUICreator;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;

import static me.capitainecat0.multicommands.utils.MessengerUtils.*;

public class GUIEvents implements Listener {
    @EventHandler
    private void invClick(InventoryClickEvent event){
        hideActiveBossBar();
        Player player = (Player) event.getWhoClicked();
        GUICreator gui = GUICreator.getGUI(player);
        if(gui != null){
            event.setCancelled(true);
            if(event.getInventory() != null){
                if(event.getRawSlot() > event.getInventory().getSize()){
                    if(gui.getGeneralInvClickAction() != null) gui.getGeneralInvClickAction().click(player, event);
                }else if(gui.getGeneralClickAction() != null) {
                    gui.getGeneralClickAction().click(player, event);
                }
            }
            GUICreator.GUIClick guiClick = gui.getAction(event.getRawSlot());
            if(guiClick != null) guiClick.click(player, event);
        }
    }

    @EventHandler
    private void invDrag(InventoryDragEvent event){
        Player player = (Player) event.getWhoClicked();
        GUICreator gui = GUICreator.getGUI(player);
        if(gui != null){
            event.setCancelled(true);
            if(gui.getGeneralDragAction() != null) gui.getGeneralDragAction().drag(player, event);
        }
    }

    @EventHandler
    private void invClose(InventoryCloseEvent event){
        Player player = (Player) event.getPlayer();
        GUICreator gui = GUICreator.getGUI(player);
        if(gui != null) gui.remove();
    }
}
