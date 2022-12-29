package me.capitainecat0.multicommands.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class CustomCraft {

    public static ShapedRecipe saddle(){
        ShapedRecipe saddle = new ShapedRecipe(new ItemStack(Material.SADDLE));
        saddle.shape(
                "   ",
                "LLL",
                "H H");
        saddle.setIngredient('L', Material.LEATHER);
        saddle.setIngredient('H', Material.TRIPWIRE_HOOK);
        return saddle;
    }
}
