package com.lost.simplearcheology;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.ModConfigSpec;

// MAde From Example Template
public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.BooleanValue ALLOW_AGEING_MEAT = BUILDER
            .comment("Whether to allow ageing Meat into rotten flesh.")
            .define("allowAgeingMeat", true);

            public static final ModConfigSpec.BooleanValue ALLOW_AGEING_SAND = BUILDER
            .comment("Whether to allow ageing Sand into Suspicious Sand.")
            .define("allowAgeingSand", true);

            public static final ModConfigSpec.BooleanValue ALLOW_AGEING_GRAVEL = BUILDER
            .comment("Whether to allow ageing Meat into Suspicious Gravel.")
            .define("allowAgeingGravel", true);


    static final ModConfigSpec SPEC = BUILDER.build();

    // Maybe Needed Later ...
    private static boolean validateItemName(final Object obj) {
        return obj instanceof String itemName && BuiltInRegistries.ITEM.containsKey(ResourceLocation.parse(itemName));
    }
}
