package com.lost.simplearcheology;

import java.util.function.Supplier;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;
import com.simibubi.create.api.registry.CreateBuiltInRegistries;
import com.simibubi.create.foundation.data.CreateRegistrate;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(CreateSimpleArcheology.MODID)
public class CreateSimpleArcheology {
    public static final String MODID = "createsimplearcheology";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final CreateRegistrate REG = CreateRegistrate.create(MODID);
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister
            .create(Registries.CREATIVE_MODE_TAB, MODID);

    // Creates a new Block
    public static final DeferredBlock<Block> BULK_AGEING_CATALYST = BLOCKS.registerSimpleBlock(
            "bulk_ageing_catalyst",
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .sound(SoundType.METAL).noOcclusion());

    public static final DeferredBlock<Block> BULK_BRUSHING_CATALYST = BLOCKS.registerSimpleBlock(
            "bulk_brushing_catalyst",
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .sound(SoundType.METAL).noOcclusion());

    // Creates a new BlockItem
    public static final DeferredItem<BlockItem> BULK_AGEING_CATALYST_ITEM = ITEMS
            .registerSimpleBlockItem("bulk_ageing_catalyst", BULK_AGEING_CATALYST);
    public static final DeferredItem<BlockItem> BULK_BRUSHING_CATALYST_ITEM = ITEMS
            .registerSimpleBlockItem("bulk_brushing_catalyst", BULK_BRUSHING_CATALYST);

    // Creates a creative tab with the id "createsimplearcheology:example_tab" for
    // the example item, that is placed after the combat tab
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MAIN_TAB = CREATIVE_MODE_TABS
            .register("main_tab", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.createsimplearcheology")) // The language key for the title
                    .withTabsBefore(CreativeModeTabs.COMBAT)
                    .icon(() -> BULK_BRUSHING_CATALYST_ITEM.get().getDefaultInstance())
                    .displayItems((parameters, output) -> {
                        output.accept(BULK_AGEING_CATALYST_ITEM.get());
                        output.accept(BULK_BRUSHING_CATALYST_ITEM.get());

                    }).build());

    // The constructor for the mod class is the first code that is run when your mod
    // is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and
    // pass them in automatically.
    public CreateSimpleArcheology(IEventBus modEventBus, ModContainer modContainer) {
        ModRecipeTypes.register(modEventBus);

        modEventBus.addListener(ModFanTypes::register);

        modEventBus.addListener(this::commonSetup);

        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);

        NeoForge.EVENT_BUS.register(this);

        modContainer.registerConfig(
                ModConfig.Type.COMMON,
                Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        if (Config.LOG_ALL.getAsBoolean()) {
            CreateSimpleArcheology.LOGGER.info("[CreateSimpleArcheology] Common Setup Active");
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        if (Config.LOG_ALL.getAsBoolean()) {
            CreateSimpleArcheology.LOGGER.info("[CreateSimpleArcheology] Server Setup Active");
        }
    }

    public static final TagKey<Block> FAN_PROCESSING_CATALYSTS_AGEING = TagKey.create(
            Registries.BLOCK,
            ResourceLocation.fromNamespaceAndPath(
                    MODID,
                    "fan_processing_catalysts/ageing"));

}
