package amymialee.blackpowder.guns;

import amymialee.blackpowder.BlackPowder;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.sounds.SoundEvent;

import static amymialee.blackpowder.items.BlackPowderItems.*;
import static amymialee.blackpowder.BlackPowder.*;

public class BlackPowderGuns {
    static SoundEvent[] blunderbussSounds = {
            GunSoundEvents.ITEM_BLUNDERBUSS_PISTOL_LOADING_START.get(),
            GunSoundEvents.ITEM_BLUNDERBUSS_PISTOL_LOADING_MIDDLE.get(),
            GunSoundEvents.ITEM_BLUNDERBUSS_PISTOL_LOADING_END.get(),
            GunSoundEvents.ITEM_BLUNDERBUSS_PISTOL_SHOOT.get(),
            GunSoundEvents.ENTITY_BULLET_IMPACT.get()
    };
    static SoundEvent[] flintlockSounds = {
            GunSoundEvents.ITEM_FLINTLOCK_PISTOL_LOADING_START.get(),
            GunSoundEvents.ITEM_FLINTLOCK_PISTOL_LOADING_MIDDLE.get(),
            GunSoundEvents.ITEM_FLINTLOCK_PISTOL_LOADING_END.get(),
            GunSoundEvents.ITEM_FLINTLOCK_PISTOL_SHOOT.get(),
            GunSoundEvents.ENTITY_BULLET_IMPACT.get()
    };
    static SoundEvent[] musketSounds = {
            GunSoundEvents.ITEM_MUSKET_PISTOL_LOADING_START.get(),
            GunSoundEvents.ITEM_MUSKET_PISTOL_LOADING_MIDDLE.get(),
            GunSoundEvents.ITEM_MUSKET_PISTOL_LOADING_END.get(),
            GunSoundEvents.ITEM_MUSKET_PISTOL_SHOOT.get(),
            GunSoundEvents.ENTITY_BULLET_IMPACT.get()
    };
    static SoundEvent[] rifleSounds = {
            GunSoundEvents.ITEM_RIFLE_PISTOL_LOADING_START.get(),
            GunSoundEvents.ITEM_RIFLE_PISTOL_LOADING_MIDDLE.get(),
            GunSoundEvents.ITEM_RIFLE_PISTOL_LOADING_END.get(),
            GunSoundEvents.ITEM_RIFLE_PISTOL_SHOOT.get(),
            GunSoundEvents.ENTITY_BULLET_IMPACT.get()
    };

    public static final DeferredRegister<Item> GUN_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    
    public static Item FLINTLOCK_PISTOL = new GunItem(1, config.FLINTLOCK_CARBINE_INACCURACY.get(), config.FLINTLOCK_RELOAD_TIME.get(), config.FLINTLOCK_QUICK_CHARGE_TIME.get(),
            flintlockSounds, 12F, MUSKET_BALL, config.FLINTLOCK_DAMAGE.get(), 0, "bullet");

    public static Item BLUNDERBUSS = new GunItem(8, config.BLUNDERBUSS_INACCURACY.get(), config.BLUNDERBUSS_RELOAD_TIME.get(), config.BLUNDERBUSS_QUICK_CHARGE_TIME.get(),
            blunderbussSounds, 8F, BLUNDER_BALL, config.BLUNDERBUSS_DAMAGE.get(), 0, "shotgun_bullet");

    public static Item RIFLE = new GunItem(1, config.RIFLE_INACCURACY.get(), config.RIFLE_RELOAD_TIME.get(), config.RIFLE_QUICK_CHARGE_TIME.get(),
            rifleSounds, 22F, MUSKET_BALL, config.RIFLE_DAMAGE.get(), 0, "pierce_bullet");

    public static Item MUSKET = new GunItem(1, config.MUSKET_INACCURACY.get(), config.MUSKET_RELOAD_TIME.get(), config.MUSKET_QUICK_CHARGE_TIME.get(),
            musketSounds, 16F, MUSKET_BALL, config.MUSKET_DAMAGE.get(), 0, "strong_bullet");


    public static Item FLINTLOCK_CARBINE = new GunItem(1, config.FLINTLOCK_CARBINE_INACCURACY.get(), config.FLINTLOCK_CARBINE_RELOAD_TIME.get(), config.FLINTLOCK_CARBINE_QUICK_CHARGE_TIME.get(),
            flintlockSounds, 12F, MUSKET_BALL, config.FLINTLOCK_CARBINE_DAMAGE.get(), 0, "bullet");

    public static Item BLUNDERBEHEMOTH = new GunItem(800, config.BLUNDERBEHEMOTH_INACCURACY.get(), config.BLUNDERBEHEMOTH_RELOAD_TIME.get(), config.BLUNDERBEHEMOTH_QUICK_CHARGE_TIME.get(),
            blunderbussSounds, 8F, BLUNDER_BALL, config.BLUNDERBEHEMOTH_DAMAGE.get(), 0, "shotgun_bullet");

    public static Item RESOLUTE_RIFLE = new GunItem(1, config.RESOLUTERIFLE_INACCURACY.get(), config.RESOLUTERIFLE_RELOAD_TIME.get(), config.RESOLUTERIFLE_QUICK_CHARGE_TIME.get(),
            rifleSounds, 198F, MUSKET_BALL, config.RESOLUTE_RIFLE_DAMAGE.get(), 100, "pierce_bullet");

    public static Item BOUNDLESS_MUSKET = new GunItem(1, config.BOUNDLESSMUSKET_INACCURACY.get(), config.BOUNDLESSMUSKET_RELOAD_TIME.get(), config.BOUNDLESSMUSKET_QUICK_CHARGE_TIME.get(),
            musketSounds, 16F, MUSKET_BALL, config.BOUNDLESSMUSKET_DAMAGE.get(), 0, "strong_bullet");

    public static void register() {
    	
    	GUN_ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    	
    	GUN_ITEMS.register("flintlock_pistol",()->FLINTLOCK_PISTOL);
    	GUN_ITEMS.register("blunderbuss",()->BLUNDERBUSS);
    	GUN_ITEMS.register("rifle",()->RIFLE);
    	GUN_ITEMS.register("musket",()->MUSKET);
    	
    	GUN_ITEMS.register("flintlock_carbine",()->FLINTLOCK_CARBINE);
    	GUN_ITEMS.register("blunderbehemoth",()->BLUNDERBEHEMOTH);
    	GUN_ITEMS.register("resolute_rifle",()->RESOLUTE_RIFLE);
    	GUN_ITEMS.register("boundless_musket",()->BOUNDLESS_MUSKET);

    	/*UniqueItemRegistry.CROSSBOW.addItemToRegistry(FLINTLOCK_PISTOL);
        UniqueItemRegistry.CROSSBOW.addItemToRegistry(BLUNDERBUSS);
        UniqueItemRegistry.CROSSBOW.addItemToRegistry(RIFLE);
        UniqueItemRegistry.CROSSBOW.addItemToRegistry(MUSKET);

        UniqueItemRegistry.CROSSBOW.addItemToRegistry(FLINTLOCK_CARBINE);
        UniqueItemRegistry.CROSSBOW.addItemToRegistry(BLUNDERBEHEMOTH);
        UniqueItemRegistry.CROSSBOW.addItemToRegistry(RESOLUTE_RIFLE);
        UniqueItemRegistry.CROSSBOW.addItemToRegistry(BOUNDLESS_MUSKET);*/
    }
}
