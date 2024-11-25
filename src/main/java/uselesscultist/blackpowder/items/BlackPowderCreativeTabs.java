package uselesscultist.blackpowder.items;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import uselesscultist.blackpowder.BlackPowder;
import uselesscultist.blackpowder.guns.BlackPowderGuns;
import uselesscultist.blackpowder.blocks.BlackPowderBlocks;

public class BlackPowderCreativeTabs{
	public static final DeferredRegister<CreativeModeTab> CREATIVE_BLACK_POWDER_TABS = 
			DeferredRegister.create(Registries.CREATIVE_MODE_TAB, BlackPowder.MOD_ID);
	
	public static final RegistryObject<CreativeModeTab> BLACK_POWDER_TAB = CREATIVE_BLACK_POWDER_TABS.register("black_powder_tab", 
				()->CreativeModeTab.builder()
				  // Set name of tab to display
				  .title(Component.translatable(BlackPowder.MOD_ID + ".black_powder_tab"))
				  // Set icon of creative tab
				  .icon(() -> new ItemStack( BlackPowderGuns.MUSKET.get() ))
				  // Add default items to tab
				  .displayItems((params, output) -> {
					// guns
				    output.accept(BlackPowderGuns.BLUNDERBEHEMOTH.get());
				    output.accept(BlackPowderGuns.BLUNDERBUSS.get());
				    output.accept(BlackPowderGuns.BOUNDLESS_MUSKET.get());
				    output.accept(BlackPowderGuns.FLINTLOCK_CARBINE.get());
				    output.accept(BlackPowderGuns.FLINTLOCK_PISTOL.get());
				    output.accept(BlackPowderGuns.MUSKET.get());
				    output.accept(BlackPowderGuns.RESOLUTE_RIFLE.get());
				    // bullets
				    output.accept(BlackPowderItems.MUSKET_BALL.get());
				    output.accept(BlackPowderItems.BLUNDER_BALL.get());
				    // parts of guns
				    output.accept(BlackPowderItems.BARREL.get());
				    output.accept(BlackPowderItems.RIFLED_BARREL.get());
				    output.accept(BlackPowderItems.FIRING_MECHANISM.get());
				    output.accept(BlackPowderItems.GRIP.get());
				  })
				  .build()
			  );
	
    public static void register(IEventBus eventBus) {
    	CREATIVE_BLACK_POWDER_TABS.register(eventBus);
    }
}