package com.felpslipe.cabela_mayhem.item;

import com.felpslipe.cabela_mayhem.CabelaMayhem;
import com.felpslipe.cabela_mayhem.entity.ModEntities;
import net.minecraft.world.item.Item;
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

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
