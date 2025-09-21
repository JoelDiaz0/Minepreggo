package dev.dixmk.minepreggo;

import org.apache.logging.log4j.Logger;

import dev.dixmk.minepreggo.init.MinepreggoModBlocks;
import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.init.MinepreggoModEntityDataSerializers;
import dev.dixmk.minepreggo.init.MinepreggoModItems;
import dev.dixmk.minepreggo.init.MinepreggoModMenus;
import dev.dixmk.minepreggo.init.MinepreggoModSounds;
import dev.dixmk.minepreggo.init.MinepreggoModTabs;

import org.apache.logging.log4j.LogManager;

import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.client.Minecraft;


@Mod(MinepreggoMod.MODID)
public class MinepreggoMod {
	public static final Logger LOGGER = LogManager.getLogger(MinepreggoMod.class);
	
	public static final String MODID = "minepreggo";

	public MinepreggoMod(FMLJavaModLoadingContext context) {
		MinecraftForge.EVENT_BUS.register(this);
	
		IEventBus modEventBus = context.getModEventBus();	
		MinepreggoModItems.REGISTRY.register(modEventBus);
		MinepreggoModEntities.REGISTRY.register(modEventBus);
		MinepreggoModBlocks.REGISTRY.register(modEventBus);
		MinepreggoModTabs.REGISTRY.register(modEventBus);
		MinepreggoModSounds.REGISTRY.register(modEventBus);
		MinepreggoModTabs.REGISTRY.register(modEventBus);
		MinepreggoModMenus.REGISTRY.register(modEventBus);
		MinepreggoModEntityDataSerializers.register();
       	
		context.registerConfig(ModConfig.Type.CLIENT, MinepreggoModConfig.CLIENT_SPEC);
		context.registerConfig(ModConfig.Type.COMMON, MinepreggoModConfig.COMMON_SPEC);
		context.registerConfig(ModConfig.Type.SERVER, MinepreggoModConfig.SERVER_SPEC);
	}


	
/*
    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");

        if (Config.logDirtBlock)
            LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));

        LOGGER.info(Config.magicNumberIntroduction + Config.magicNumber);

        Config.items.forEach((item) -> LOGGER.info("ITEM >> {}", item.toString()));
    }
*/
    
	
	
	
	
	
	
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }
    
}
