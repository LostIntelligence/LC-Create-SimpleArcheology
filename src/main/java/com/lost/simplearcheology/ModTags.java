package com.lost.simplearcheology;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModTags {

    public static class Blocks {

        public static final TagKey<Block> FAN_PROCESSING_CATALYSTS_AGEING =
                TagKey.create(
                        Registries.BLOCK,
                        ResourceLocation.fromNamespaceAndPath(
                                CreateSimpleArcheology.MODID,
                                "fan_processing_catalysts/ageing"
                        )
                );
    }
}
