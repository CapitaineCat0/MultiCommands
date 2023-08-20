package me.capitainecat0.multicommands.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

import static me.capitainecat0.multicommands.utils.MessengerUtils.*;

public class ItemCreator {

    /**    
     * The create function is a helper function that creates an ItemStack object
     * with the given material.
     * This is useful for creating items in your plugin,
     * as it saves you from having to write new ItemStack(Material.MATERIAL_NAME)
     * every time you want to create an item.
     * Instead, you can just call this 
     * function and pass in the material name as a parameter!
     * For example:&lt;br&gt; 
     * &lt;code&gt;ItemStack myItem = ItemsAPI.create(Material.DIAMOND);&lt;/code&gt;&lt;br&gt; 
    
     *
     * @param mat Set the material of the item
     */
    @Contract("_ -> new")
    public static @NotNull ItemStack create(Material mat){ return new ItemStack(mat); }

    /**
     * The create function creates an ItemStack with a given Material and name.
     * 
     *
     * @param mat Set the material of the item
     * @param name Set the display name of the item
     */
    public static @NotNull ItemStack create(Material mat, String name){
        ItemStack item = create(mat);
        ItemMeta meta = item.getItemMeta();
        if(meta == null) return item;
        meta.setDisplayName(colored(name));
        item.setItemMeta(meta);
        return item;
    }

    /**
     * The create function is a function that creates an ItemStack with the given material, name, and lore.
     *
     *
     * @param mat Set the material of the item
     * @param name Set the name of the item
     * @param lore Set the lore of an item
     */
    public static @NotNull ItemStack create(Material mat, String name, List<String> lore){
        ItemStack item = create(mat, colored(name));
        ItemMeta meta = item.getItemMeta();
        if(meta == null) return item;
        meta.setLore(Collections.singletonList(colored(String.valueOf(lore))));
        item.setItemMeta(meta);
        return item;
    }

    /**
     * The create function is a simple function that creates an ItemStack with the given material, name, and lore.
     *
     *
     * @param mat Set the material of the item
     * @param name Set the name of the item
     * @param lore Set the lore of the item
     * @param hideFlags Hide the item flags
     */
    public static @NotNull ItemStack create(Material mat, String name, List<String> lore, boolean hideFlags){
        ItemStack item = create(mat, colored(name), Collections.singletonList(colored(String.valueOf(lore))));
        ItemMeta meta = item.getItemMeta();
        if(meta == null) return item;
        if(hideFlags) meta.addItemFlags(ItemFlag.values());
        else meta.removeItemFlags(ItemFlag.values());
        item.setItemMeta(meta);
        return item;
    }

    /**
     * The create function is a function that creates an ItemStack with the given parameters.
     *
     *
     * @param mat Set the material of the item
     * @param name Set the name of the item
     * @param lore Add lore to the item
     * @param hideFlags Hide the enchanting on the item
     * @param isGlowing Determine if the item should be glowing or not
     */
    public static ItemStack create(Material mat, String name, List<String> lore, boolean hideFlags, boolean isGlowing){
        ItemStack item = create(mat, colored(name), Collections.singletonList(colored(String.valueOf(lore))), hideFlags);
        ItemMeta meta = item.getItemMeta();
        if(meta == null) return item;
        if(isGlowing){
            item.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        return item;
    }
}
