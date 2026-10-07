package com.lost.simplearcheology;

import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

// This class will not load on dedicated servers. Accessing client side code from here is safe.
@Mod(value = CreateSimpleArcheology.MODID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = CreateSimpleArcheology.MODID, value = Dist.CLIENT)
public class CreateSimpleArcheologyClient {
    public CreateSimpleArcheologyClient(ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        // Some client setup code
        if (Config.LOG_ALL.getAsBoolean()) {

            CreateSimpleArcheology.LOGGER.info("HELLO FROM CLIENT SETUP");
            CreateSimpleArcheology.LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }
}
