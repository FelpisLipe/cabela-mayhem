package com.felpslipe.cabela_mayhem.datagen;

import com.felpslipe.cabela_mayhem.CabelaMayhem;
import com.felpslipe.cabela_mayhem.block.ModBlocks;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.AbstractSkullBlock;

import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;

import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.function.Function;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, CabelaMayhem.MOD_ID, exFileHelper);
    }
        // Block shit
    @Override
    protected void registerStatesAndModels() {

        for (DeferredBlock<? extends AbstractSkullBlock> skull : ModBlocks.SKULLS) {
            skullBlock(skull);
        }
    }

    private void skullBlock(DeferredBlock<?> deferredBlock) {
        getVariantBuilder(deferredBlock.get())
                .partialState()
                .setModels(new ConfiguredModel(models().getExistingFile(
                        ResourceLocation.withDefaultNamespace("block/skull"))));
    }

}
