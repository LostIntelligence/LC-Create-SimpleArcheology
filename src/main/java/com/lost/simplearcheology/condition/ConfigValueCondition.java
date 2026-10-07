package com.lost.simplearcheology.condition;

import com.lost.simplearcheology.Config;
import com.lost.simplearcheology.CreateSimpleArcheology;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public record ConfigValueCondition(String value) implements ICondition {
    public static final MapCodec<ConfigValueCondition> CODEC = RecordCodecBuilder.mapCodec(builder -> builder
            .group(com.mojang.serialization.Codec.STRING.fieldOf("value").forGetter(ConfigValueCondition::value))
            .apply(builder, ConfigValueCondition::new));

    private static final DeferredRegister<MapCodec<? extends ICondition>> CONDITION_CODECS = DeferredRegister.create(
            NeoForgeRegistries.Keys.CONDITION_CODECS,
            CreateSimpleArcheology.MODID);

    static {
        CONDITION_CODECS.register("config_value", () -> CODEC);
    }

    public static void register(IEventBus eventBus) {
        CONDITION_CODECS.register(eventBus);
    }

    @Override
    public boolean test(IContext context) {
        return Config.isRecipeEnabled(value);
    }

    @Override
    public MapCodec<? extends ICondition> codec() {
        return CODEC;
    }
}