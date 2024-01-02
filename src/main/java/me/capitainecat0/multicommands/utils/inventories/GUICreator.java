package me.capitainecat0.multicommands.utils.inventories;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;
import static org.bukkit.Bukkit.createInventory;

public class GUICreator {
    private static final Map<UUID, GUICreator> openGUIS = new HashMap<>();
    private static final Map<String, Set<UUID>> viewers = new HashMap<>();
    private final Map<Integer, GUIClick> guiClickActions = new HashMap<>();
    private GUIClick generalClickAction;
    private GUIClick generalInvClickAction;
    private GUIDrag generalDragAction;
    private GUIOpen openAction;
    private GUIClose closeAction;
    public UUID uuid;
    private final Inventory inventory;
    private final String viewerID;

    public static GUICreator getGUI(Player player){
        return openGUIS.getOrDefault(player.getUniqueId(), null);
    }

    public GUICreator(int size, String name){
        Component title = Component.text(colored(name));
        uuid = UUID.randomUUID();
        inventory = createInventory(null, size * 9, title);
        viewerID = null;
    }
    public GUICreator(Player owner, int size, String name){
        Component title = Component.text(colored(name));
        uuid = UUID.randomUUID();
        inventory = createInventory(owner, size*9, title);
        viewerID = null;
    }

    public GUICreator(int size, String name, String viewerID){
        Component title = Component.text(colored(name));
        uuid = UUID.randomUUID();
        inventory = createInventory(null, size*9, title);
        this.viewerID = viewerID;
    }

    public void open(@NotNull Player player){
        player.openInventory(inventory);
        openGUIS.put(player.getUniqueId(), this);
        if(viewerID != null) addViewer(player);
        if(openAction != null) openAction.open(player);

    }

    public void close(@NotNull Player player){
        player.closeInventory();
        openGUIS.entrySet().removeIf(entry ->{
            if(entry.getKey().equals(player.getUniqueId())){
                if(viewerID != null) removeViewer(player);
                if(closeAction != null) closeAction.close(player);
                return true;
            }
            return false;
        });
    }

    public void remove(){
        openGUIS.entrySet().removeIf(entry ->{
            if(entry.getValue().getUuid().equals(uuid)){
                Player player = Bukkit.getPlayer(entry.getKey());
                if(player != null){
                    if(viewerID != null) removeViewer(player);
                    if(closeAction != null) closeAction.close(player);
                }
                return true;
            }
            return false;
        });
    }

    private UUID getUuid(){
        return uuid;
    }

    private void addViewer(Player player){
        if(viewerID == null) return;
        Set<UUID> list = viewers.getOrDefault(viewerID, new HashSet<>());
        list.add(player.getUniqueId());
        viewers.put(viewerID, list);
    }

    private void removeViewer(Player player){
        if(viewerID == null) return;
        Set<UUID> list = viewers.getOrDefault(viewerID, null);
        if(list == null) return;
        list.remove(player.getUniqueId());
        if(list.isEmpty()) viewers.remove(viewerID);
        else viewers.put(viewerID, list);
    }

    public Set<Player> getViewers(){
        if(viewerID == null) return new HashSet<>();
        Set<Player> viewerList = new HashSet<>();
        for(UUID uuid : viewers.getOrDefault(viewerID, new HashSet<>())){
            Player player = Bukkit.getPlayer(uuid);
            if(player == null) continue;
            viewerList.add(player);
        }
        return viewerList;
    }

    public GUIClick getAction(int index){ return guiClickActions.getOrDefault(index, null); }
    public GUIClick getGeneralClickAction() {
        return generalClickAction;
    }
    protected void setGeneralClickAction(GUIClick generalClickAction) {
        this.generalClickAction = generalClickAction;
    }
    public GUIClick getGeneralInvClickAction() {
        return generalInvClickAction;
    }
    protected void setGeneralInvClickAction(GUIClick generalInvClickAction) {
        this.generalInvClickAction = generalInvClickAction;
    }
    public GUIDrag getGeneralDragAction() {
        return generalDragAction;
    }
    protected void setGeneralDragAction(GUIDrag generalDragAction) {
        this.generalDragAction = generalDragAction;
    }
    protected void setOpenAction(GUIOpen openAction) {
        this.openAction = openAction;
    }
    protected void setCloseAction(GUIClose closeAction) {
        this.closeAction = closeAction;
    }
    public interface GUIClick{
        void click(Player player, InventoryClickEvent event);
    }
    public interface GUIDrag{
        void drag(Player player, InventoryDragEvent event);
    }
    public interface GUIOpen{
        void open(Player player);
    }
    public interface GUIClose{
        void close(Player player);
    }
    public void setItem(int index, ItemStack itemStack){
        inventory.setItem(index, itemStack);
    }
    public void setItem(int index, ItemStack itemStack, GUIClick action){
        inventory.setItem(index, itemStack);
        if(action == null) guiClickActions.remove(index);
        else guiClickActions.put(index, action);
    }
}
