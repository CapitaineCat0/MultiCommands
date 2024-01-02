package me.capitainecat0.multicommands.utils.storage;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.UUID;

import static me.capitainecat0.multicommands.utils.MessengerUtils.sendEventExceptionMessage;

public class FreezeData {
    public static HashMap<UUID, Boolean> frozenData = new HashMap<>();

    public void add(Player player) {
        try{
            if(!frozenData.containsKey(player.getUniqueId())){
                frozenData.put(player.getUniqueId(), true);
            }
        }catch (Exception e){
            sendEventExceptionMessage(e, "FreezeData.add");
        }

    }

    public static Boolean isFrozen(@NotNull Player player) {
        try {
            if (frozenData.containsKey(player.getUniqueId())) {
                return frozenData.get(player.getUniqueId());
            }
        }catch(Exception e){
            sendEventExceptionMessage(e, "FreezeData.isFrozen");
        }
           return false;
    }

    public void remove(Player player) {
        try{
            frozenData.remove(player.getUniqueId());
        }catch (Exception e){
            sendEventExceptionMessage(e, "FreezeData.remove");
        }
    }

    public void clearFrozen() {
        try{
            frozenData.clear();
        }catch (Exception e){
            sendEventExceptionMessage(e, "FreezeData.clearFrozen");
        }
    }
}
