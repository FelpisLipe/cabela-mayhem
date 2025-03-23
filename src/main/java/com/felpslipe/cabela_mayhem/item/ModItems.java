package com.felpslipe.cabela_mayhem.item;

import com.felpslipe.cabela_mayhem.CabelaMayhem;
import com.felpslipe.cabela_mayhem.block.ModBlocks;
import com.felpslipe.cabela_mayhem.entity.ModEntities;
import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS =DeferredRegister.createItems(CabelaMayhem.MOD_ID);

    public static final DeferredItem<Item> FRANGO = ITEMS.register("frango",
            () -> new Item(new Item.Properties().food(ModFoodProperties.FRANGO)));

    public static final DeferredItem<Item> CABELA_SPAWN_EGG = ITEMS.register("cabela_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.CABELA, 0xf7eada, 0x2a0c06,
                    new Item.Properties()));
    public static final DeferredItem<Item> CABELA_HEAD = ITEMS.register("cabela_head",
            () -> new StandingAndWallBlockItem(ModBlocks.CABELA_HEAD.get(), ModBlocks.CABELA_WALL_HEAD.get(),
                    new Item.Properties().rarity(Rarity.UNCOMMON), Direction.DOWN));
    public static final DeferredItem<Item> CABELA_CRY_HEAD = ITEMS.register("cabela_cry_head",
            () -> new StandingAndWallBlockItem(ModBlocks.CABELA_CRY_HEAD.get(), ModBlocks.CABELA_CRY_WALL_HEAD.get(),
                    new Item.Properties().rarity(Rarity.UNCOMMON), Direction.DOWN));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
