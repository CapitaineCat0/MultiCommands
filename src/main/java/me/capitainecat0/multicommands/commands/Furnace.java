package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.Objects;

import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;
import static me.capitainecat0.multicommands.utils.Perms.*;

public class Furnace implements CommandExecutor {
    private FurnaceRecipe furnaceRecipe;
    private ItemStack result;

    /**
     *
     * The Furnace command can open furnace GUI without any furnace
     */
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        hideActiveBossBar();
        try {
            if (sender instanceof Player) {
                ItemStack baseItem;
                Iterator i;
                if (!sender.hasPermission(FURNACE_PERM.getPermission()) || !sender.hasPermission(ALL_PERMS.getPermission())) {
                    playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                    getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage());
                    return true;
                }else{
                    result = null;
                    baseItem = ((Player) sender).getItemInHand();
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
                        sendMessage(sender, FURNACE_DONE.getMessage().replace("{0}", String.valueOf(baseItem.getAmount())).replace("{1}", baseItem.getType().name()));
                        result.setAmount(baseItem.getAmount());
                        float level = furnaceRecipe.getExperience();
                        ((Player) sender).setItemInHand(result);
                        ((Player) sender).setLevel((int) (((Player) sender).getLevel() + level / 2));
                        playSoundIfEnabled(sender, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
                    } else{
                        playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                        sendMessage(sender, FURNACE_ERROR.getMessage().replace("{0}", baseItem.getType().name()));
                    }
                }
            }else if(sender instanceof ConsoleCommandSender){
                sendConsoleMessage(NO_CONSOLE_COMMAND.getMessage().replace("<command>", command.getName()));
            }
        }catch (Exception e) {
            sendCommandExceptionMessage(e, command.getName());
            sendMessage(sender, CMD_ERROR.getMessage().replace("<command>", command.getName()).replace("{e}", e.getMessage()));
        }
        return false;
    }
}
