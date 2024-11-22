package amymialee.blackpowder.items;

import static amymialee.blackpowder.BlackPowder.MODID;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlackPowderItems {
    public static final DeferredRegister<Item> BULLET_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
	
    public static Item MUSKET_BALL = new BulletItem(new Item.Properties());
    public static Item BLUNDER_BALL = new BulletItem(new Item.Properties());

    public static Item GRIP = new Item(new Item.Properties());
    public static Item FIRING_MECHANISM = new Item(new Item.Properties());
    public static Item BARREL = new Item(new Item.Properties());
    public static Item RIFLED_BARREL = new Item(new Item.Properties());

    public static void register() {
    	BULLET_ITEMS.register("musket_ball", ()->MUSKET_BALL);
    	BULLET_ITEMS.register("blunder_ball", ()->BLUNDER_BALL);
    	
    	BULLET_ITEMS.register("grip", ()->GRIP);
    	BULLET_ITEMS.register("firing_mechanism", ()->FIRING_MECHANISM);
    	BULLET_ITEMS.register("barrel", ()->BARREL);
    	BULLET_ITEMS.register("rifled_barrel", ()->RIFLED_BARREL);
    }
}
