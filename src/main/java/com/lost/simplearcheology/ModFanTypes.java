package com.lost.simplearcheology;

import com.simibubi.create.api.registry.CreateBuiltInRegistries;
import com.simibubi.create.content.kinetics.fan.processing.FanProcessingType;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.registries.RegisterEvent;

public class ModFanTypes {

    public static final FanProcessingType ARCHAEOLOGY =
            new ArchaeologyFanType();

    public static final FanProcessingType AGEING =
            new AgeingFanType();

    public static void register(RegisterEvent event) {
        event.register(
                CreateBuiltInRegistries.FAN_PROCESSING_TYPE.key(),
                helper -> {
                    helper.register(
                            ResourceLocation.fromNamespaceAndPath(
                                    CreateSimpleArcheology.MODID,
                                    "archaeology"
                            ),
                            ARCHAEOLOGY
                    );

                    helper.register(
                            ResourceLocation.fromNamespaceAndPath(
                                    CreateSimpleArcheology.MODID,
                                    "ageing"
                            ),
                            AGEING
                    );
                }
        );
    }
}
