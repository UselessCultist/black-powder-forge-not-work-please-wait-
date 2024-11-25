package uselesscultist.blackpowder.items;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import uselesscultist.blackpowder.BlackPowder;

public class BlackPowderItems {
    public static final DeferredRegister<Item> ITEMS = 
    		DeferredRegister.create(ForgeRegistries.ITEMS, BlackPowder.MOD_ID);
    
    public static final RegistryObject<Item> MUSKET_BALL = ITEMS.register("musket_ball", () -> new BulletItem(new Item.Properties()));
    public static final RegistryObject<Item> BLUNDER_BALL = ITEMS.register("blunder_ball", () -> new BulletItem(new Item.Properties()));
    
    public static final RegistryObject<Item> GRIP = ITEMS.register("grip", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> FIRING_MECHANISM = ITEMS.register("firing_mechanism", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BARREL = ITEMS.register("barrel", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RIFLED_BARREL = ITEMS.register("rifled_barrel", () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
    	ITEMS.register(eventBus);
    }
}
