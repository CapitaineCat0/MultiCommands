package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;
import java.util.Map;

import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.sendMessage;
import static me.capitainecat0.multicommands.utils.Perms.*;

public class Give implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player) {
            if (!sender.hasPermission(GIVE_PERM.getPermission()) || !sender.hasPermission(ALL_PERMS.getPermission())) {
                sendMessage(sender, CMD_NO_PERM.getMessage().replace("{prefix}", PLUGIN_PREFIX.getMessage()));
            }
            if (args.length < 2) {
                sendMessage(sender, CMD_NO_ARGS.getMessage().replace("<command>", command.getName()).replace("{0}", "<item> <amount>").replace("{prefix}", PLUGIN_PREFIX.getMessage()));
            }

            ItemStack stack = new ItemStack(Material.valueOf(args[1]));
            final String itemname = stack.getType().toString().toLowerCase(Locale.ENGLISH).replace("_", "");

            try {
                if (args.length > 3) {
                    stack.setAmount(Integer.parseInt(args[2]));
                    stack.setDurability(Short.parseShort(args[3]));
                } else if (args.length > 2 && Integer.parseInt(args[2]) > 0) {
                    stack.setAmount(Integer.parseInt(args[2]));
                } else if (MultiCommands.getInstance().getConfig().getInt("default_stack_size") > 0) {
                    stack.setAmount(MultiCommands.getInstance().getConfig().getInt("default_stack_size"));
                } else if (MultiCommands.getInstance().getConfig().getInt("oversize_stack_size") > 0 && sender.hasPermission(GIVE_OVERSIZE_PERM.getPermission())) {
                    stack.setAmount(MultiCommands.getInstance().getConfig().getInt("oversize_stack_size"));
                }
            } catch (final NumberFormatException e) {
                sendMessage(sender, e.getMessage());
            }

            final ItemMeta metaStack = new ItemStack(stack).getItemMeta();
            if (!metaStack.isUnbreakable()) {
                sendMessage(sender, GIVE_ERROR.getMessage().replace("{0}", itemname).replace("{prefix}", PLUGIN_PREFIX.getMessage()));
            }

            if (args.length > 3) {
                boolean allowUnsafe = MultiCommands.getInstance().getConfig().getBoolean("allow_unsafe_enchantment");
                if (allowUnsafe && !sender.hasPermission(GIVE_UNSAFE_ENCHANTMENT.getPermission())) {
                }

                /*final int metaStart = Boolean.getBoolean(args[3]) ? 4 : 3;

                if (args.length > metaStart) {
                    metaStack.parseStringMeta(sender, allowUnsafe, args, metaStart, ess);
                }

                stack = metaStack.getItemStack();*/
            }

            if (stack.getType() == Material.AIR) {
                sendMessage(sender, GIVE_ERROR.getMessage().replace("{0}", "air").replace("{prefix}", PLUGIN_PREFIX.getMessage()));
            }

            final String itemName = stack.getType().toString().toLowerCase(Locale.ENGLISH).replace('_', ' ');
            final boolean isDropItemsIfFull = MultiCommands.getInstance().getConfig().getBoolean("drop_inventory_full");
            final ItemStack finalStack = stack;
            for (Player player : Bukkit.getOnlinePlayers()) {

            }
            Inventory inventory = ((Player) sender).getInventory();
            if (sender.hasPermission(GIVE_OVERSIZE_PERM.getPermission())) {
                int amount = MultiCommands.getInstance().getConfig().getInt("oversize_stack_size");
            }
            final Map<Integer, ItemStack> leftovers = inventory.addItem(finalStack);

            for (final ItemStack item : leftovers.values()) {
                if (isDropItemsIfFull) {
                    final World w = ((Player) sender).getWorld();
                    w.dropItemNaturally(((Player) sender).getLocation(), item);
                } else {
                    sendMessage(sender, GIVE_ERROR.getMessage().replace("{0}", (CharSequence) item).replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                }
            }
            ((Player) sender).updateInventory();
            sendMessage(sender, GIVE_DONE.getMessage().replace("{0}", finalStack.getAmount() + "").replace("{1}", itemname).replace("{prefix}", PLUGIN_PREFIX.getMessage()));

        }else if(sender instanceof ConsoleCommandSender) {

        }
        return false;
    }
}
