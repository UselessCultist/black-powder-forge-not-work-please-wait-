package amymialee.blackpowder.guns;

import amymialee.blackpowder.BlackPowder;
import net.minecraftforge.common.util.ForgeSoundType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public final class GunSoundEvents {
    public static final DeferredRegister<SoundEvent> GUN_SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, BlackPowder.MODID);

    private GunSoundEvents() {}

    public static final RegistryObject<SoundEvent> ITEM_FLINTLOCK_PISTOL_LOADING_END = registerSoundEvents("flintlock_loading_end");
    public static final RegistryObject<SoundEvent> ITEM_FLINTLOCK_PISTOL_LOADING_MIDDLE = registerSoundEvents("flintlock_loading_middle");
    public static final RegistryObject<SoundEvent> ITEM_FLINTLOCK_PISTOL_LOADING_START = registerSoundEvents("flintlock_loading_start");
    public static final RegistryObject<SoundEvent> ITEM_FLINTLOCK_PISTOL_SHOOT = registerSoundEvents("flintlock_shoot");

    public static final RegistryObject<SoundEvent> ITEM_BLUNDERBUSS_PISTOL_LOADING_END = registerSoundEvents("blunderbuss_loading_end");
    public static final RegistryObject<SoundEvent> ITEM_BLUNDERBUSS_PISTOL_LOADING_MIDDLE = registerSoundEvents("blunderbuss_loading_middle");
    public static final RegistryObject<SoundEvent> ITEM_BLUNDERBUSS_PISTOL_LOADING_START = registerSoundEvents("blunderbuss_loading_start");
    public static final RegistryObject<SoundEvent> ITEM_BLUNDERBUSS_PISTOL_SHOOT = registerSoundEvents("blunderbuss_shoot");

    public static final RegistryObject<SoundEvent> ITEM_MUSKET_PISTOL_LOADING_END = registerSoundEvents("musket_loading_end");
    public static final RegistryObject<SoundEvent> ITEM_MUSKET_PISTOL_LOADING_MIDDLE = registerSoundEvents("musket_loading_middle");
    public static final RegistryObject<SoundEvent> ITEM_MUSKET_PISTOL_LOADING_START = registerSoundEvents("musket_loading_start");
    public static final RegistryObject<SoundEvent> ITEM_MUSKET_PISTOL_SHOOT = registerSoundEvents("musket_shoot");

    public static final RegistryObject<SoundEvent> ITEM_RIFLE_PISTOL_LOADING_END = registerSoundEvents("rifle_loading_end");
    public static final RegistryObject<SoundEvent> ITEM_RIFLE_PISTOL_LOADING_MIDDLE = registerSoundEvents("rifle_loading_middle");
    public static final RegistryObject<SoundEvent> ITEM_RIFLE_PISTOL_LOADING_START = registerSoundEvents("rifle_loading_start");
    public static final RegistryObject<SoundEvent> ITEM_RIFLE_PISTOL_SHOOT = registerSoundEvents("rifle_shoot");

    public static final RegistryObject<SoundEvent> ENTITY_BULLET_IMPACT = registerSoundEvents("bullet_impact");

    
    public static RegistryObject<SoundEvent> registerSoundEvents(String name) {
    	return GUN_SOUND_EVENTS.register(name, ()-> SoundEvent.createVariableRangeEvent(new ResourceLocation(BlackPowder.MODID,name)));
    }
    
    public static void register(IEventBus eventBus) {
    	GUN_SOUND_EVENTS.register(eventBus);
    }
}
