package com.felpslipe.cabela_mayhem.datagen;

import com.felpslipe.cabela_mayhem.CabelaMayhem;
import com.felpslipe.cabela_mayhem.block.ModBlocks;

import com.felpslipe.cabela_mayhem.block.custom.FrangoCropBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.AbstractSkullBlock;

import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
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

        makeCrop(((CropBlock) ModBlocks.FRANGO_CROP.get()), "frango_crop_stage", "frango_crop_stage");

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

    public void makeCrop(CropBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> states(state, block, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] states(BlockState state, CropBlock block, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().crop(modelName + state.getValue(((FrangoCropBlock) block).getAgeProperty()),
                ResourceLocation.fromNamespaceAndPath(CabelaMayhem.MOD_ID, "block/" + textureName + state.getValue(((FrangoCropBlock) block).getAgeProperty()))).renderType("cutout"));

        return models;
    }

}
