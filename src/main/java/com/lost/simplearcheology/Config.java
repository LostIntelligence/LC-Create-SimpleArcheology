package com.lost.simplearcheology;

import java.util.Map;

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

        public static final ModConfigSpec.BooleanValue LOG_ALL = BUILDER
                        .comment("Allows Logging (may cause a lot of log spam)")
                        .define("logAll", false);

        private static final Map<String, ModConfigSpec.BooleanValue> RECIPE_TOGGLES = Map.of(
                        "allowAgeingMeat", ALLOW_AGEING_MEAT, "allowAgeingSand", ALLOW_AGEING_SAND, "allowAgeingGravel",
                        ALLOW_AGEING_GRAVEL);
        static final ModConfigSpec SPEC = BUILDER.build();

        public static boolean isRecipeEnabled(String configKey) {
                ModConfigSpec.BooleanValue configValue = RECIPE_TOGGLES.get(configKey);
                return configValue != null && configValue.getAsBoolean();
        }

        // Maybe Needed Later ...
        @SuppressWarnings("unused")
        private static boolean validateItemName(final Object obj) {
                return obj instanceof String itemName
                                && BuiltInRegistries.ITEM.containsKey(ResourceLocation.parse(itemName));
        }
}
