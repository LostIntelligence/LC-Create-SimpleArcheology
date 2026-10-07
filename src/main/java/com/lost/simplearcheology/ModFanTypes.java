package com.lost.simplearcheology;

import com.lost.simplearcheology.recipe.AgeingFanType;
import com.lost.simplearcheology.recipe.BrushingFanType;
import com.simibubi.create.api.registry.CreateBuiltInRegistries;
import com.simibubi.create.content.kinetics.fan.processing.FanProcessingType;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.registries.RegisterEvent;

public class ModFanTypes {

        public static final FanProcessingType BRUSHING = new BrushingFanType();

        public static final FanProcessingType AGEING = new AgeingFanType();

        public static void register(RegisterEvent event) {
                event.register(
                                CreateBuiltInRegistries.FAN_PROCESSING_TYPE.key(),
                                helper -> {
                                        helper.register(
                                                        ResourceLocation.fromNamespaceAndPath(
                                                                        CreateSimpleArcheology.MODID,
                                                                        "brushing"),
                                                        BRUSHING);

                                        helper.register(
                                                        ResourceLocation.fromNamespaceAndPath(
                                                                        CreateSimpleArcheology.MODID,
                                                                        "ageing"),
                                                        AGEING);
                                });
        }
}
