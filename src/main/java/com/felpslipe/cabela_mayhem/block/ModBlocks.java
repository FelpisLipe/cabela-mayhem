package com.felpslipe.cabela_mayhem.block;

import com.felpslipe.cabela_mayhem.CabelaMayhem;
import com.felpslipe.cabela_mayhem.block.custom.CabelaSkullBlock;
import com.felpslipe.cabela_mayhem.block.custom.CabelaWallSkullBlock;
import com.felpslipe.cabela_mayhem.entity.CabelaVariant;
import com.felpslipe.cabela_mayhem.item.ModItems;
import com.google.common.collect.ImmutableSet;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.AbstractSkullBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(CabelaMayhem.MOD_ID);
    public static final List<DeferredBlock<? extends AbstractSkullBlock>> SKULLS = new ArrayList<>();

    public static final DeferredBlock<CabelaSkullBlock> CABELA_HEAD = registerSkullBlock("cabela_head",
            () -> new CabelaSkullBlock(CabelaVariant.NORMAL, BlockBehaviour.Properties.of().strength(1.0F).pushReaction(PushReaction.DESTROY))
    );
    public static final DeferredBlock<CabelaWallSkullBlock> CABELA_WALL_HEAD = registerSkullBlock("cabela_wall_head",
            () -> new CabelaWallSkullBlock(CabelaVariant.NORMAL, BlockBehaviour.Properties.of().strength(1.0F).pushReaction(PushReaction.DESTROY))
    );

    public static final DeferredBlock<CabelaSkullBlock> CABELA_CRY_HEAD = registerSkullBlock("cabela_cry_head",
            () -> new CabelaSkullBlock(CabelaVariant.CRY, BlockBehaviour.Properties.of().strength(1.0F).pushReaction(PushReaction.DESTROY))
    );
    public static final DeferredBlock<CabelaWallSkullBlock> CABELA_CRY_WALL_HEAD = registerSkullBlock("cabela_cry_wall_head",
            () -> new CabelaWallSkullBlock(CabelaVariant.CRY, BlockBehaviour.Properties.of().strength(1.0F).pushReaction(PushReaction.DESTROY))
    );



    private static <T extends AbstractSkullBlock> DeferredBlock<T> registerSkullBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        SKULLS.add(toReturn);
        return toReturn;
    }

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    // Registering blocks
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);

        eventBus.addListener(ModBlocks::onCommonSetup);
    }

    private static void onCommonSetup(final FMLCommonSetupEvent event) {
        setValidSkullBlocks();
    }

    private static void setValidSkullBlocks() {
        try {
            Field validBlocks = BlockEntityType.class.getDeclaredField("validBlocks");
            validBlocks.setAccessible(true);
            @SuppressWarnings("unchecked")
            Set<Block> skullValidBlocks = new ObjectOpenHashSet<>((Set<Block>) validBlocks.get(BlockEntityType.SKULL));
            skullValidBlocks.addAll(SKULLS.stream().map(DeferredBlock::get).collect(ImmutableSet.toImmutableSet()));
            validBlocks.set(BlockEntityType.SKULL, skullValidBlocks);
        } catch (Exception e) {
            throw new RuntimeException("Failed to set validBlocks for Skull block entity type", e);
        }
    }
}
