package me.capitainecat0.multicommands.commands.implementers;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.CommandsImpl;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.Recipe;

import java.util.Iterator;

import static me.capitainecat0.multicommands.utils.Messenger.FURNACE_DONE;
import static me.capitainecat0.multicommands.utils.Messenger.FURNACE_ERROR;
import static me.capitainecat0.multicommands.utils.MessengerUtils.playSoundIfEnabled;
import static me.capitainecat0.multicommands.utils.MessengerUtils.sendMessage;

public class Furnace implements CommandsImpl {

    private final Player player;
    private FurnaceRecipe furnaceRecipe;

    public Furnace(Player player) {
        this.player = player;
    }
    @Override
    public void execute() {
        PlayerInventory inv = player.getInventory();
        ItemStack baseItem;
        Iterator i;
        ItemStack result = null;
        baseItem = inv.getItemInMainHand();
        i = Bukkit.recipeIterator();
        while(i.hasNext()) {
            Recipe recipe = (Recipe) i.next();
            if (recipe instanceof FurnaceRecipe) {
                furnaceRecipe = (FurnaceRecipe) recipe;
                if (furnaceRecipe.getInput().getType() == baseItem.getType()) {
                    result = furnaceRecipe.getResult();
                    break;
                }
            }
        }
        if (result != null) {
            sendMessage(player, FURNACE_DONE.getMessage().replace("{0}", String.valueOf(baseItem.getAmount())).replace("{1}", baseItem.getType().name()));
            result.setAmount(baseItem.getAmount());
            float level = furnaceRecipe.getExperience();
            inv.setItemInMainHand(result);
            player.giveExp((int)level);
            playSoundIfEnabled(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
        } else{
            playSoundIfEnabled(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
            sendMessage(player, FURNACE_ERROR.getMessage().replace("{0}", baseItem.getType().name()));
        }
    }
}
