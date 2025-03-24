package com.felpslipe.cabela_mayhem;

import com.felpslipe.cabela_mayhem.block.ModBlocks;
import com.felpslipe.cabela_mayhem.block.client.CabelaSkullModel;
import com.felpslipe.cabela_mayhem.entity.CabelaVariant;
import com.felpslipe.cabela_mayhem.entity.ModEntities;
import com.felpslipe.cabela_mayhem.entity.client.CabelaRenderer;
import com.felpslipe.cabela_mayhem.item.ModItems;
import com.felpslipe.cabela_mayhem.sound.ModSounds;
import com.google.common.collect.ImmutableMap;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.blockentity.SkullBlockRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(CabelaMayhem.MOD_ID)
public class CabelaMayhem {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "cabela_mayhem";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public CabelaMayhem(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (ExampleMod) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);


        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModSounds.register(modEventBus);
        ModEntities.register(modEventBus);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
            event.accept(ModItems.CABELA_SPAWN_EGG);
        }
        if(event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
            event.accept(ModItems.FRANGO);
        }
        if(event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            event.accept(ModItems.CABELA_HEAD);
            event.accept(ModItems.CABELA_CRY_HEAD);
            event.accept(ModBlocks.CABELA_TROPHY);
        }

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            EntityRenderers.register(ModEntities.CABELA.get(), CabelaRenderer::new);

            event.enqueueWork(() -> {
                ImmutableMap.Builder<SkullBlock.Type, ResourceLocation> builder = ImmutableMap.builder();
                builder.put(CabelaVariant.NORMAL, CabelaVariant.NORMAL.getResourceLocation());
                builder.put(CabelaVariant.CRY, CabelaVariant.CRY.getResourceLocation());
                SkullBlockRenderer.SKIN_BY_TYPE.putAll(builder.build());
            });

        }

        @SubscribeEvent
        public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
            event.registerLayerDefinition(CabelaSkullModel.CABELA_HEAD, CabelaSkullModel::createCabelaHeadLayer);
        }

        @SubscribeEvent
        public static void onCreateSkullModel(EntityRenderersEvent.CreateSkullModels event) {
            event.registerSkullModel(CabelaVariant.NORMAL, new CabelaSkullModel(event.getEntityModelSet().bakeLayer(CabelaSkullModel.CABELA_HEAD)));
            event.registerSkullModel(CabelaVariant.CRY, new CabelaSkullModel(event.getEntityModelSet().bakeLayer(CabelaSkullModel.CABELA_HEAD)));
        }
    }
}
