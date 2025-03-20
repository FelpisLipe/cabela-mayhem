package com.felpslipe.cabela_mayhem.datagen;

import com.felpslipe.cabela_mayhem.CabelaMayhem;
import com.felpslipe.cabela_mayhem.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.LinkedHashMap;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, CabelaMayhem.MOD_ID, existingFileHelper);
    }
        // Just for items
    @Override
    protected void registerModels() {
        basicItem(ModItems.FRANGO.get());
        withExistingParent(ModItems.CABELA_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
    }


    public void skullItem(DeferredItem<?> item) {
        this.withExistingParent(item.getId().getPath(), mcLoc("item/template_skull"));
    }

}
