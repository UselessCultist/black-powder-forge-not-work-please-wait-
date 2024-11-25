package uselesscultist.blackpowder.guns;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.sounds.SoundEvent;

import static uselesscultist.blackpowder.items.BlackPowderItems.*;
import static uselesscultist.blackpowder.BlackPowder.*;

public class BlackPowderGuns {
    public static final DeferredRegister<Item> GUN_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);
    
    public static final RegistryObject<Item> FLINTLOCK_PISTOL = GUN_ITEMS.register("flintlock_pistol", 
    		() -> new GunItem(1, 7f, 3, 1,
    				GunSoundEvents.ITEM_FLINTLOCK_PISTOL_LOADING_START,
    				GunSoundEvents.ITEM_FLINTLOCK_PISTOL_LOADING_MIDDLE,
    				GunSoundEvents.ITEM_FLINTLOCK_PISTOL_LOADING_END,
    				GunSoundEvents.ITEM_FLINTLOCK_PISTOL_SHOOT,
    				GunSoundEvents.ENTITY_BULLET_IMPACT,
    				12F, MUSKET_BALL, 12, 0, "bullet"));

    public static final RegistryObject<Item> BLUNDERBUSS = GUN_ITEMS.register("blunderbuss", 
    		() -> new GunItem(8, 14f, 160, 20,
    				GunSoundEvents.ITEM_BLUNDERBUSS_PISTOL_LOADING_START,
    				GunSoundEvents.ITEM_BLUNDERBUSS_PISTOL_LOADING_MIDDLE,
    				GunSoundEvents.ITEM_BLUNDERBUSS_PISTOL_LOADING_END,
    				GunSoundEvents.ITEM_BLUNDERBUSS_PISTOL_SHOOT,
    				GunSoundEvents.ENTITY_BULLET_IMPACT,
    				8F, BLUNDER_BALL, 4, 0, "shotgun_bullet"));

    public static final RegistryObject<Item> RIFLE = GUN_ITEMS.register("rifle", 
    		() -> new GunItem(1, 2f, 160, 20,
    	            GunSoundEvents.ITEM_RIFLE_PISTOL_LOADING_START,
    	            GunSoundEvents.ITEM_RIFLE_PISTOL_LOADING_MIDDLE,
    	            GunSoundEvents.ITEM_RIFLE_PISTOL_LOADING_END,
    	            GunSoundEvents.ITEM_RIFLE_PISTOL_SHOOT,
    	            GunSoundEvents.ENTITY_BULLET_IMPACT,
    	            22F, MUSKET_BALL, 22, 0, "pierce_bullet"));
    
    public static final RegistryObject<Item> MUSKET = GUN_ITEMS.register("musket", 
    		() -> new GunItem(1, 4f, 100, 10,
    	            GunSoundEvents.ITEM_MUSKET_PISTOL_LOADING_START,
    	            GunSoundEvents.ITEM_MUSKET_PISTOL_LOADING_MIDDLE,
    	            GunSoundEvents.ITEM_MUSKET_PISTOL_LOADING_END,
    	            GunSoundEvents.ITEM_MUSKET_PISTOL_SHOOT,
    	            GunSoundEvents.ENTITY_BULLET_IMPACT,
    	            16F, MUSKET_BALL, 26, 0, "strong_bullet"));
    
    public static final RegistryObject<Item> FLINTLOCK_CARBINE = GUN_ITEMS.register("flintlock_carbine", 
    		() -> new GunItem(1, 7f, 3, 1,
    				GunSoundEvents.ITEM_FLINTLOCK_PISTOL_LOADING_START,
    				GunSoundEvents.ITEM_FLINTLOCK_PISTOL_LOADING_MIDDLE,
    				GunSoundEvents.ITEM_FLINTLOCK_PISTOL_LOADING_END,
    				GunSoundEvents.ITEM_FLINTLOCK_PISTOL_SHOOT,
    				GunSoundEvents.ENTITY_BULLET_IMPACT,
    	            12F, MUSKET_BALL, 12, 0, "bullet"));
    
    public static final RegistryObject<Item> BLUNDERBEHEMOTH = GUN_ITEMS.register("blunderbehemoth", 
    		() -> new GunItem(800, 28f, 320, 40,
    	            GunSoundEvents.ITEM_BLUNDERBUSS_PISTOL_LOADING_START,
    	            GunSoundEvents.ITEM_BLUNDERBUSS_PISTOL_LOADING_MIDDLE,
    	            GunSoundEvents.ITEM_BLUNDERBUSS_PISTOL_LOADING_END,
    	            GunSoundEvents.ITEM_BLUNDERBUSS_PISTOL_SHOOT,
    	            GunSoundEvents.ENTITY_BULLET_IMPACT,
    	            8F, BLUNDER_BALL, 4, 0, "shotgun_bullet"));
    
    public static final RegistryObject<Item> RESOLUTE_RIFLE = GUN_ITEMS.register("resolute_rifle", 
    		() -> new GunItem(1, 0f, 240, 40,
    	            GunSoundEvents.ITEM_RIFLE_PISTOL_LOADING_START,
    	            GunSoundEvents.ITEM_RIFLE_PISTOL_LOADING_MIDDLE,
    	            GunSoundEvents.ITEM_RIFLE_PISTOL_LOADING_END,
    	            GunSoundEvents.ITEM_RIFLE_PISTOL_SHOOT,
    	            GunSoundEvents.ENTITY_BULLET_IMPACT,
    	            198F, MUSKET_BALL, 22, 100, "pierce_bullet"));
    
    public static final RegistryObject<Item> BOUNDLESS_MUSKET = GUN_ITEMS.register("boundless_musket", 
    		() -> new GunItem(1, 4f, 200, 20,
    	            GunSoundEvents.ITEM_MUSKET_PISTOL_LOADING_START,
    	            GunSoundEvents.ITEM_MUSKET_PISTOL_LOADING_MIDDLE,
    	            GunSoundEvents.ITEM_MUSKET_PISTOL_LOADING_END,
    	            GunSoundEvents.ITEM_MUSKET_PISTOL_SHOOT,
    	            GunSoundEvents.ENTITY_BULLET_IMPACT,
    	            16F, MUSKET_BALL, 318, 0, "strong_bullet"));

    public static void register(IEventBus eventBus) {
    	GUN_ITEMS.register(eventBus);
    }
}
