package me.capitainecat0.multicommands.events;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class BlockRegenRunnable extends BukkitRunnable {

    ArrayList<BlockState> blocks = new ArrayList<>();

    public BlockRegenRunnable(@NotNull List<Block> blocks) {
        for (Block block : blocks) {
            this.blocks.add(block.getState());
        }
    }

    @Override
    public void run() {
        int max = blocks.size() - 1;
        if(max > -1) {
            if(!blocks.get(max).getType().equals(Material.TNT)) {
                blocks.get(max).update(true, false);
                blocks.remove(max);
            } else {
                blocks.remove(max);
            }
        }
    }
}
