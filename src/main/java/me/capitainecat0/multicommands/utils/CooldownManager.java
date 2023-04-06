package me.capitainecat0.multicommands.utils;

import me.capitainecat0.multicommands.MultiCommands;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CooldownManager {
    private final Map<UUID, Integer> cooldownMap = new HashMap<>();

    /**
     *
     * @param plugin Main class
     */
    public CooldownManager(MultiCommands plugin){
        new BukkitRunnable(){

            @Override
            public void run() {
                for(UUID uuid : cooldownMap.keySet()){
                    if(cooldownMap.get(uuid) == 1){
                        cooldownMap.remove(uuid);
                        continue;
                    }
                    cooldownMap.put(uuid, cooldownMap.get(uuid)-1);
                }
            }
        }.runTaskTimer(plugin, 0, 20);
    }
    /**
     *
     * @param player
     * @param time in seconds
     * @return Add player to cooldown Map
     */
    public void addPlayerCooldown(@NotNull Player player, Integer time){
        cooldownMap.put(player.getUniqueId(), time);
    }
    /**
     *
     * @param player
     * @return Boolean value of cooldown
     */
    public boolean isPlayerCooldown(@NotNull Player player){
        return cooldownMap.containsKey(player.getUniqueId());
    }
    /**
     *
     * @param player
     * @return Time remaining of cooldown
     */
    public Integer getTimer(Player player){
        if(!isPlayerCooldown(player)){
            return 0;
        }else{
            return cooldownMap.get(player.getUniqueId());
        }
    }
}
