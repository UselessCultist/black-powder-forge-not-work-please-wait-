package uselesscultist.blackpowder;

import com.mojang.logging.LogUtils;

import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import uselesscultist.blackpowder.blocks.BlackPowderBlocks;
import uselesscultist.blackpowder.client.BlackPowderClient;
import uselesscultist.blackpowder.guns.BlackPowderGuns;
import uselesscultist.blackpowder.guns.GunSoundEvents;
import uselesscultist.blackpowder.items.BlackPowderCreativeTabs;
import uselesscultist.blackpowder.items.BlackPowderItems;

import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(BlackPowder.MOD_ID)
public class BlackPowder
{

    public static final String MOD_ID = "blackpowder";
    public static BlackPowderConfig config;
    
    
    private static final Logger LOGGER = LogUtils.getLogger();

    
    public BlackPowder()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        
        BlackPowderCreativeTabs.register(modEventBus);
        BlackPowderItems.register(modEventBus);
        GunSoundEvents.register(modEventBus);
        BlackPowderGuns.register(modEventBus);
        BlackPowderBlocks.register(modEventBus);

        
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }


    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {

    }


    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
        	BlackPowderClient.addCustomItemProperties();

        }
    }
}
