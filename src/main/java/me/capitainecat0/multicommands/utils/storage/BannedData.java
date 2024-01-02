package me.capitainecat0.multicommands.utils.storage;

import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.UUID;

import static me.capitainecat0.multicommands.utils.MessengerUtils.sendEventExceptionMessage;

public class BannedData {
    public static HashMap<UUID, Boolean> bannedData = new HashMap<>();

    public void add(OfflinePlayer player){
        try{
            if(!bannedData.containsKey(player.getUniqueId())){
                bannedData.put(player.getUniqueId(), true);
            }
        }catch (Exception e){
            sendEventExceptionMessage(e, "FreezeData.add");
        }

    }

    public static @NotNull Boolean isBanned(OfflinePlayer player) {
        try {
            if (bannedData.containsKey(player.getUniqueId())) {
                return true;
            }
        }catch(Exception e){
            sendEventExceptionMessage(e, "FreezeData.isFrozen");
        }
        return false;
    }

    public void remove(OfflinePlayer player) {
        try{
            bannedData.remove(player.getUniqueId());
        }catch (Exception e){
            sendEventExceptionMessage(e, "FreezeData.remove");
        }
    }

    public void clearBanned() {
        try{
            bannedData.clear();
        }catch (Exception e){
            sendEventExceptionMessage(e, "FreezeData.clearFrozen");
        }
    }
}
