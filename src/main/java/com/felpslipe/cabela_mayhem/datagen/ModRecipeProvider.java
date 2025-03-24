package com.felpslipe.cabela_mayhem.datagen;


import com.felpslipe.cabela_mayhem.CabelaMayhem;
import com.felpslipe.cabela_mayhem.block.ModBlocks;
import com.felpslipe.cabela_mayhem.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.FRANGO_SEEDS.get())
                .pattern("CCC")
                .pattern("CBC")
                .pattern("CCC")
                .define('B', Items.COOKED_CHICKEN)
                .define('C', Items.WHEAT_SEEDS)
                .unlockedBy("has_chicken", has(Items.COOKED_CHICKEN))
                .unlockedBy("has_seed", has(Items.WHEAT_SEEDS)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.TROPHY.get())
                .pattern("C C")
                .pattern("CCC")
                .pattern(" C ")
                .define('C', Items.GOLD_INGOT)
                .unlockedBy("has_ingot", has(Items.GOLD_INGOT)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.CABELA_TROPHY.get())
                .requires(ModBlocks.TROPHY)
                .requires(ModBlocks.CABELA_HEAD)
                .unlockedBy("has_trophy", has(ModBlocks.TROPHY))
                .unlockedBy("has_cabela_head", has(ModBlocks.CABELA_HEAD)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.CABELA_CRY_TROPHY.get())
                .requires(ModBlocks.TROPHY)
                .requires(ModBlocks.CABELA_CRY_HEAD)
                .unlockedBy("has_trophy", has(ModBlocks.TROPHY))
                .unlockedBy("has_cabela_cry_head", has(ModBlocks.CABELA_CRY_HEAD)).save(recipeOutput);

    }

}

