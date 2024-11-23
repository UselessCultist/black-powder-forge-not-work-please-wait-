package amymialee.blackpowder;

import amymialee.blackpowder.guns.BlackPowderGuns;
import amymialee.blackpowder.items.BlackPowderItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.ToIntFunction;

@Mod(BlackPowder.MODID)
public class BlackPowder{
    public static final String MODID = "blackpowder";

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    
    public static final RegistryObject<Block> TARGET_LAMP_BLOCK = BLOCKS.register("target_lamp_block", () -> new TargetLampBlock(BlockBehaviour.Properties.of().strength(1.0f).lightLevel(fullLight())));
    public static final RegistryObject<Item> TARGET_LAMP_BLOCK_ITEM = ITEMS.register("target_lamp_block", () -> new BlockItem(TARGET_LAMP_BLOCK.get(), new Item.Properties()));
    
    private static ToIntFunction<BlockState> fullLight() {
        return (blockState) -> (Boolean)blockState.getValue(TargetLampBlock.LIT) ? 15 : 0;
    }
    
    public static BlackPowderConfig config;

    public BlackPowder() 
    {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    	
    	ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, BlackPowder.config.SPEC, "common.toml");

        BlackPowderGuns.register();
        //BlackPowderItems.register();
    }
}
