package me.capitainecat0.multicommands.utils.storage;

import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static me.capitainecat0.multicommands.utils.MessengerUtils.sendEventExceptionMessage;

public class BannedData {
    public static HashMap<UUID, Boolean> bannedData = new HashMap<>();
    public static List<String[]> bannedDataList;

    public void add(OfflinePlayer player){
        try{
            if(!bannedData.containsKey(player.getUniqueId())){
                bannedData.put(player.getUniqueId(), true);
                bannedDataList.add(new String[]{player.getUniqueId().toString(), "true"});
               // store(player);
            }
        }catch (Exception e){
            sendEventExceptionMessage(e, "BannedData.add");
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
            bannedDataList.remove(new String[]{player.getUniqueId().toString(), null});
            //updateExcelFile("banned", "banned players", new String[]{"UUID", "Banned"}, bannedDataList);
        }catch (Exception e){
            sendEventExceptionMessage(e, "BannedData.remove");
        }
    }

    public void clearBanned() {
        try{
            bannedData.clear();
            bannedDataList.clear();
        }catch (Exception e){
            sendEventExceptionMessage(e, "BannedData.clearBanned");
        }
    }

    /*public void store(OfflinePlayer player){
        try{
            if(bannedData.containsKey(player.getUniqueId()) && findExcelFile("banned") && !findPlayerInExcelFile("banned", "banned players", player.getUniqueId().toString())){
                updateExcelFile("banned", "banned players", new String[]{"UUID", "Banned"}, bannedDataList);
            }
            createExcelFile("banned", "banned players", new String[]{"UUID", "Banned"}, bannedDataList);
    }
        catch (Exception e){
            sendEventExceptionMessage(e, "BannedData.store");
        }
    }*/
}
