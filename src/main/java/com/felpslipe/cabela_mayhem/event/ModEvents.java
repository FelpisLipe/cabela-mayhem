package com.felpslipe.cabela_mayhem.event;

import com.felpslipe.cabela_mayhem.CabelaMayhem;
import com.felpslipe.cabela_mayhem.entity.CabelaVariant;
import com.felpslipe.cabela_mayhem.entity.custom.CabelaEntity;
import com.felpslipe.cabela_mayhem.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;

import java.util.HashSet;
import java.util.Set;

@EventBusSubscriber(modid = CabelaMayhem.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class ModEvents {

    @SubscribeEvent
    public static void onLivingDead(LivingDeathEvent event) {
        LivingEntity entity = event.getEntity();
        if(event.getEntity() instanceof CabelaEntity cabela  && !cabela.isBaby() && event.getSource().getDirectEntity() instanceof Creeper creeper && creeper.isPowered()) {
            if(cabela.getVariant() == CabelaVariant.NORMAL) {
                ItemStack headItem = new ItemStack(ModItems.CABELA_HEAD.get());
                ItemEntity itemEntity = new ItemEntity(
                        entity.level(),
                        entity.getX(), entity.getY(), entity.getZ(),
                        headItem);
                entity.level().addFreshEntity(itemEntity);
            }
            if(cabela.getVariant() == CabelaVariant.CRY) {
                ItemStack headItem = new ItemStack(ModItems.CABELA_CRY_HEAD.get());
                ItemEntity itemEntity = new ItemEntity(
                        entity.level(),
                        entity.getX(), entity.getY(), entity.getZ(),
                        headItem);
                entity.level().addFreshEntity(itemEntity);
            }
        }
    }


}
