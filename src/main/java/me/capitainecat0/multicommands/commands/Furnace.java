package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
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
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        hideActiveBossBar();
        ItemStack result;
        ItemStack baseItem;
        Iterator i;
        Recipe recipe;
        FurnaceRecipe furnaceRecipe;
            if (sender instanceof Player) {
                if (!sender.hasPermission(FURNACE_PERM.getPermission()) || !sender.hasPermission(ALL_PERMS.getPermission())) {
                    if(soundEnabled()){
                        playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                    }
                    getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage().replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                    return true;
                }else{
                result = null;
                baseItem = ((Player) sender).getItemInHand();
                i = Bukkit.recipeIterator();

                while(i.hasNext()) {
                    recipe = (Recipe)i.next();
                    if (recipe instanceof FurnaceRecipe) {
                        furnaceRecipe = (FurnaceRecipe)recipe;
                        if (furnaceRecipe.getInput().getType() == baseItem.getType()) {
                            result = furnaceRecipe.getResult();
                            break;
                        }
                    }
                }
                if (result != null) {
                    sendMessage(sender, FURNACE_DONE.getMessage().replace("{0}", String.valueOf(baseItem.getAmount())).replace("{1}", baseItem.getType().name()));
                    result.setAmount(baseItem.getAmount());
                    ((Player) sender).setItemInHand(result);
                    } else {
                        sendMessage(sender, FURNACE_ERROR.getMessage().replace("{0}", baseItem.getType().name()).replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                    }
            }
        }else if(sender instanceof ConsoleCommandSender){
                sendConsoleMessage(NO_CONSOLE_COMMAND.getMessage().replace("<command>", command.getName()).replace("{prefix}", PLUGIN_PREFIX.getMessage()));
            }
        return false;
    }
}
