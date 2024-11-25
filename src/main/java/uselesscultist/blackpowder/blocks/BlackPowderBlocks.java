package uselesscultist.blackpowder.blocks;

import java.util.function.ToIntFunction;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import uselesscultist.blackpowder.BlackPowder;
import uselesscultist.blackpowder.blocks.TargetLampBlock;

public class BlackPowderBlocks{
	public static final DeferredRegister<Block> BLOCKS = 
    		DeferredRegister.create(ForgeRegistries.BLOCKS, BlackPowder.MOD_ID);
	
	public static final RegistryObject<Block> TARGET_LAMP = BLOCKS.register("target_lamp", () -> 
	new TargetLampBlock(
			BlockBehaviour.Properties.of()
			.lightLevel((blockState) -> TargetLampBlock.getIsLightOn(blockState) ? 15 : 0)
			)
	);
	
    public static void register(IEventBus eventBus) {
    	BLOCKS.register(eventBus);
    }
}