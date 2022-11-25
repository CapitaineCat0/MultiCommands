package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.Objects;

import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;
import static me.capitainecat0.multicommands.utils.Perms.ALL_PERMS;
import static me.capitainecat0.multicommands.utils.Perms.FURNACE;

public class Furnace implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        hideActiveBossBar();
        if(!sender.hasPermission(FURNACE.getPermission()) || !sender.hasPermission(ALL_PERMS.getPermission())){
            if(soundEnabled()){
                playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
            }
            getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage());
            return true;
        }else{
            if(sender instanceof ConsoleCommandSender){
                sendConsoleMessage(NO_CONSOLE_COMMAND.getMessage().replace("%cmd%", command.getName()));
                return true;
            }else{
                ItemStack result = null;
                final ItemStack baseItem = ((Player)sender).getItemInHand();
                final Iterator<Recipe> i = Bukkit.recipeIterator();
                while(i.hasNext()){
                    Recipe recipe = i.next();
                    if(recipe == null) continue;
                    if(recipe.getResult().getType() != baseItem.getType()) continue;
                    result = recipe.getResult();
                    break;
                }
                if(result != null){
                    if(soundEnabled()){
                        playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                    }
                    getMsgSendConfig(sender, command.getName(), FURNACE_DONE.getMessage().replace("%items%", baseItem.getAmount()+" "+baseItem.getType().name()));

                    result.setAmount(baseItem.getAmount());
                    ((Player)sender).setItemInHand(result);
                }else{
                    ItemStack block = Objects.requireNonNull(((Player) sender).getEquipment()).getItemInMainHand();
                    if(soundEnabled()){
                        playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                    }
                    getMsgSendConfig(sender, command.getName(), FURNACE_ERROR.getMessage().replace("%item%", block.getType()+""));
                }
            }
        }
        return false;
    }
}
