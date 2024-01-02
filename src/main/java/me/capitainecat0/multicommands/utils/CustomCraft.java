package me.capitainecat0.multicommands.utils;

import me.capitainecat0.multicommands.MultiCommands;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.jetbrains.annotations.NotNull;

public class CustomCraft {

    //Création de recettes personnalisées

    public static @NotNull ShapedRecipe saddle(){
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
    public static @NotNull ShapedRecipe nametag(){
        //Création de la recette du nametag
        //Clé obligatoire pour la recette (depuis spigot 1.12)
        NamespacedKey key = new NamespacedKey(MultiCommands.getPlugin(MultiCommands.class), "nametag");
        //Création de la recette
        ShapedRecipe nametag = new ShapedRecipe(key, new ItemStack(Material.NAME_TAG));
        nametag.shape(
                "PPP",
                "PPP",
                " S ");
        //Définition des ingrédients
        nametag.setIngredient('P', Material.PAPER);
        nametag.setIngredient('S', Material.STRING);
        return nametag;
    }
    public static @NotNull ShapedRecipe chainmailHelmet(){
        //Création de la recette du casque en mailles
        //Clé obligatoire pour la recette (depuis spigot 1.12)
        NamespacedKey key = new NamespacedKey(MultiCommands.getPlugin(MultiCommands.class), "chainmail_helmet");
        //Création de la recette
        ShapedRecipe chainmailHelmet = new ShapedRecipe(key, new ItemStack(Material.CHAINMAIL_HELMET));
        chainmailHelmet.shape(
                "III",
                "I I");
        //Définition des ingrédients
        chainmailHelmet.setIngredient('I', Material.IRON_NUGGET);
        return chainmailHelmet;
    }
    public static @NotNull ShapelessRecipe uncraftChainmailHelmet(){
        NamespacedKey key = new NamespacedKey(MultiCommands.getPlugin(MultiCommands.class), "uncraft_chainmail_helmet");
        ShapelessRecipe uncraftChainmailHelmet = new ShapelessRecipe(key, new ItemStack(Material.IRON_NUGGET, 5));
        uncraftChainmailHelmet.addIngredient(Material.CHAINMAIL_HELMET);
        return uncraftChainmailHelmet;
    }
    public static @NotNull ShapedRecipe chainmailChestplate(){
        //Création de la recette du plastron en mailles
        //Clé obligatoire pour la recette (depuis spigot 1.12)
        NamespacedKey key = new NamespacedKey(MultiCommands.getPlugin(MultiCommands.class), "chainmail_chestplate");
        //Création de la recette
        ShapedRecipe chainmailChestplate = new ShapedRecipe(key, new ItemStack(Material.CHAINMAIL_CHESTPLATE));
        chainmailChestplate.shape(
                "I I",
                "III",
                "III");
        //Définition des ingrédients
        chainmailChestplate.setIngredient('I', Material.IRON_NUGGET);
        return chainmailChestplate;
    }
    public static @NotNull ShapelessRecipe uncraftChainmailChestplate(){
        NamespacedKey key = new NamespacedKey(MultiCommands.getPlugin(MultiCommands.class), "uncraft_chainmail_chestplate");
        ShapelessRecipe uncraftChainmailChestplate = new ShapelessRecipe(key, new ItemStack(Material.IRON_NUGGET, 8));
        uncraftChainmailChestplate.addIngredient(Material.CHAINMAIL_CHESTPLATE);
        return uncraftChainmailChestplate;
    }
    public static @NotNull ShapedRecipe chainmailLeggings(){
        //Création de la recette du pantalon en mailles
        //Clé obligatoire pour la recette (depuis spigot 1.12)
        NamespacedKey key = new NamespacedKey(MultiCommands.getPlugin(MultiCommands.class), "chainmail_leggings");
        //Création de la recette
        ShapedRecipe chainmailLeggings = new ShapedRecipe(key, new ItemStack(Material.CHAINMAIL_LEGGINGS));
        chainmailLeggings.shape(
                "III",
                "I I",
                "I I");
        //Définition des ingrédients
        chainmailLeggings.setIngredient('I', Material.IRON_NUGGET);
        return chainmailLeggings;
    }
    public static @NotNull ShapelessRecipe uncraftChainmailLeggings(){
        NamespacedKey key = new NamespacedKey(MultiCommands.getPlugin(MultiCommands.class), "uncraft_chainmail_leggings");
        ShapelessRecipe uncraftChainmailLeggings = new ShapelessRecipe(key, new ItemStack(Material.IRON_NUGGET, 7));
        uncraftChainmailLeggings.addIngredient(Material.CHAINMAIL_LEGGINGS);
        return uncraftChainmailLeggings;
    }
    public static @NotNull ShapedRecipe chainmailBoots(){
        //Création de la recette des bottes en mailles
        //Clé obligatoire pour la recette (depuis spigot 1.12)
        NamespacedKey key = new NamespacedKey(MultiCommands.getPlugin(MultiCommands.class), "chainmail_boots");
        //Création de la recette
        ShapedRecipe chainmailBoots = new ShapedRecipe(key, new ItemStack(Material.CHAINMAIL_BOOTS));
        chainmailBoots.shape(
                "   ",
                "I I",
                "I I");
        //Définition des ingrédients
        chainmailBoots.setIngredient('I', Material.IRON_NUGGET);
        return chainmailBoots;
    }
    public static @NotNull ShapelessRecipe uncraftChainmailBoots(){
        NamespacedKey key = new NamespacedKey(MultiCommands.getPlugin(MultiCommands.class), "uncraft_chainmail_boots");
        ShapelessRecipe uncraftChainmailBoots = new ShapelessRecipe(key, new ItemStack(Material.IRON_NUGGET, 4));
        uncraftChainmailBoots.addIngredient(Material.CHAINMAIL_BOOTS);
        return uncraftChainmailBoots;
    }
    public static @NotNull ShapedRecipe enchantedApple(){
        //Création de la recette de la pomme enchantée
        //Clé obligatoire pour la recette (depuis spigot 1.12)
        NamespacedKey key = new NamespacedKey(MultiCommands.getPlugin(MultiCommands.class), "enchanted_apple");
        //Création de la recette
        ShapedRecipe enchantedApple = new ShapedRecipe(key, new ItemStack(Material.ENCHANTED_GOLDEN_APPLE));
        enchantedApple.shape(
                "GGG",
                "GAG",
                "GGG");
        //Définition des ingrédients
        enchantedApple.setIngredient('G', Material.GOLD_BLOCK);
        enchantedApple.setIngredient('A', Material.GOLDEN_APPLE);
        return enchantedApple;
    }
}