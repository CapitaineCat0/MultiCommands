package me.capitainecat0.multicommands.utils;

import me.capitainecat0.multicommands.MultiCommands;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class CustomCraft {

    //Création de recettes personnalisées

    public static ShapedRecipe saddle(){
        //Création de la recette de la selle
        //Clé obligatoire pour la recette (depuis spigot 1.12)
        NamespacedKey key = new NamespacedKey(MultiCommands.getPlugin(MultiCommands.class), "saddle");
        //Création de la recette
        ShapedRecipe saddle = new ShapedRecipe(key, new ItemStack(Material.SADDLE));
        saddle.shape(
                "   ",
                "LLL",
                "H H");
        //Définition des ingrédients
        saddle.setIngredient('L', Material.LEATHER);
        saddle.setIngredient('H', Material.TRIPWIRE_HOOK);
        return saddle;
    }
}
