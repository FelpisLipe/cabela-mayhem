package com.felpslipe.cabela_mayhem.sound;

import com.felpslipe.cabela_mayhem.CabelaMayhem;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, CabelaMayhem.MOD_ID);

    public static final Supplier<SoundEvent> CABELA_AMBIENT = registerSoundEvent("cabela_ambient");
    public static final Supplier<SoundEvent> CABELA_CRY_AMBIENT = registerSoundEvent("cabela_cry_ambient");
    public static final Supplier<SoundEvent> CABELA_HURT = registerSoundEvent("cabela_hurt");
    public static final Supplier<SoundEvent> CABELA_DEATH = registerSoundEvent("cabela_death");

    private static Supplier<SoundEvent> registerSoundEvent(String name) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(CabelaMayhem.MOD_ID, name);
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
