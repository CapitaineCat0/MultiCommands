package me.capitainecat0.multicommands.events;

import me.capitainecat0.multicommands.MultiCommands;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static me.capitainecat0.multicommands.utils.MessengerUtils.*;

public class BlockRegen implements Listener {

   /** @EventHandler
    public void onEntityExplode(EntityExplodeEvent event){
        hideActiveBossBar();
            for(Block block : event.blockList()){
                final BlockState state = block.getState();
                block.setType(Material.AIR);
                int delay = 20;
                if((block.getType() == Material.SAND) || (block.getType() == Material.GRAVEL)){
                    delay += 1;
                }
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(MultiCommands.getInstance(), () -> state.update(true, false), delay);
            }
    }*/
   @EventHandler
    public void onEntityExplode(@NotNull EntityExplodeEvent event){
       hideActiveBossBar();
       List<Block> blocks = event.blockList();
       new BlockRegenRunnable(blocks).runTaskTimer(MultiCommands.getInstance(), 1, 1);
       event.setYield(0);
    }

    @EventHandler
    public void onBlockExplode(@NotNull BlockExplodeEvent event){
        hideActiveBossBar();
        List<Block> blocks = event.blockList();
        new BlockRegenRunnable(blocks).runTaskTimer(MultiCommands.getInstance(), 1, 1);
        event.setYield(0);
    }
}
