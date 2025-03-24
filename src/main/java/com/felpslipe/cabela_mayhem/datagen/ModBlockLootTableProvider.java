package com.felpslipe.cabela_mayhem.datagen;

import com.felpslipe.cabela_mayhem.block.ModBlocks;
import com.felpslipe.cabela_mayhem.block.custom.FrangoCropBlock;
import com.felpslipe.cabela_mayhem.item.ModItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {

        dropOther(ModBlocks.CABELA_HEAD.get(), ModItems.CABELA_HEAD.get());
        dropOther(ModBlocks.CABELA_WALL_HEAD.get(), ModItems.CABELA_HEAD.get());
        dropOther(ModBlocks.CABELA_CRY_HEAD.get(), ModItems.CABELA_CRY_HEAD.get());
        dropOther(ModBlocks.CABELA_CRY_WALL_HEAD.get(), ModItems.CABELA_CRY_HEAD.get());
        dropSelf(ModBlocks.CABELA_TROPHY.get());
        dropSelf(ModBlocks.CABELA_CRY_TROPHY.get());
        dropSelf(ModBlocks.TROPHY.get());

        LootItemCondition.Builder lootItemConditionBuilder = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.FRANGO_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(FrangoCropBlock.AGE, 3));
        add(ModBlocks.FRANGO_CROP.get(), this.createCropDrops(ModBlocks.FRANGO_CROP.get(),
                ModItems.FRANGO.get(), ModItems.FRANGO_SEEDS.get(), lootItemConditionBuilder));
    }



    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
