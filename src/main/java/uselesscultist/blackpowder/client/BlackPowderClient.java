package uselesscultist.blackpowder.client;

import uselesscultist.blackpowder.BlackPowder;
import uselesscultist.blackpowder.guns.BlackPowderGuns;
import uselesscultist.blackpowder.guns.GunItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.item.ItemProperties;

public class BlackPowderClient {


	public static void addCustomItemProperties() 
	{
        ItemProperties.register(
        		BlackPowderGuns.FLINTLOCK_PISTOL.get(),
                new ResourceLocation(BlackPowder.MOD_ID, "charged"),
                (stack, level, player, seed) -> player != null && GunItem.isCharged(stack) ? 1.0F : 0.0F
            );
        ItemProperties.register(
        		BlackPowderGuns.BLUNDERBUSS.get(),
                new ResourceLocation(BlackPowder.MOD_ID, "charged"),
                (stack, level, player, seed) -> player != null && GunItem.isCharged(stack) ? 1.0F : 0.0F
            );
        ItemProperties.register(
        		BlackPowderGuns.RIFLE.get(),
                new ResourceLocation(BlackPowder.MOD_ID, "charged"),
                (stack, level, player, seed) -> player != null && GunItem.isCharged(stack) ? 1.0F : 0.0F
            );
        ItemProperties.register(
        		BlackPowderGuns.MUSKET.get(),
                new ResourceLocation(BlackPowder.MOD_ID, "charged"),
                (stack, level, player, seed) -> player != null && GunItem.isCharged(stack) ? 1.0F : 0.0F
            );
        
        
        ItemProperties.register(
        		BlackPowderGuns.FLINTLOCK_CARBINE.get(),
                new ResourceLocation(BlackPowder.MOD_ID, "charged"),
                (stack, level, player, seed) -> player != null && GunItem.isCharged(stack) ? 1.0F : 0.0F
            );
        ItemProperties.register(
        		BlackPowderGuns.BLUNDERBEHEMOTH.get(),
                new ResourceLocation(BlackPowder.MOD_ID, "charged"),
                (stack, level, player, seed) -> player != null && GunItem.isCharged(stack) ? 1.0F : 0.0F
            );
        ItemProperties.register(
        		BlackPowderGuns.RESOLUTE_RIFLE.get(),
                new ResourceLocation(BlackPowder.MOD_ID, "charged"),
                (stack, level, player, seed) -> player != null && GunItem.isCharged(stack) ? 1.0F : 0.0F
            );
        ItemProperties.register(
        		BlackPowderGuns.BOUNDLESS_MUSKET.get(),
                new ResourceLocation(BlackPowder.MOD_ID, "charged"),
                (stack, level, player, seed) -> player != null && GunItem.isCharged(stack) ? 1.0F : 0.0F
            );
	}
}
