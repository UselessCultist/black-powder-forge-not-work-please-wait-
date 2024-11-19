package amymialee.blackpowder.guns;

import net.minecraft.world.entity.Entity;
import net.minecraft.core.Holder;
import net.minecraft.core.Holder.Reference.Type;
import net.minecraft.core.HolderOwner;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.tags.DamageTypeTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.jetbrains.annotations.Nullable;

import com.mojang.datafixers.util.Either;

import amymialee.blackpowder.BlackPowder;

public class BulletDamageSource extends DamageSource {
	public static final ResourceKey<DamageType> EXAMPLE_DAMAGE = 
			ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.tryBuild(BlackPowder.MODID, "example"));
	
	DamageSource damageSource = new DamageSource(
	        // The damage type holder to use. Query from the registry. This is the only required parameter.
	        RegistryAccess.lookupOrThrow(Registries.DAMAGE_TYPE).getOrThrow(EXAMPLE_DAMAGE),
	        // The direct entity. For example, if a skeleton shot you, the skeleton would be the causing entity
	        // (= the parameter above), and the arrow would be the direct entity (= this parameter). Similar to
	        // the causing entity, this isn't always applicable and therefore nullable. Optional, defaults to null.
	        null,
	        // The entity causing the damage. This isn't always applicable (e.g. when falling out of the world)
	        // and may therefore be null. Optional, defaults to null.
	        null,
	        // The damage source position. This is rarely used, one example would be intentional game design
	        // (= nether beds exploding). Nullable and optional, defaulting to null.
	        null
	);
	
    public BulletDamageSource(String name, Entity projectile, @Nullable Entity attacker) {
    	
        super(name, projectile, attacker);
    }
	
    public BulletDamageSource(Holder<DamageTypes> type, Entity projectile, @Nullable Entity attacker) {
        super(type, projectile, attacker);
    }

    public static DamageSource bullet(Projectile projectile, @Nullable Entity attacker) {
    	DamageSource damageSource = new DamageSource(
    	        // The damage type holder to use. Query from the registry. This is the only required parameter.
    	        RegistryAccess.lookupOrThrow(Registries.DAMAGE_TYPE).getOrThrow(EXAMPLE_DAMAGE),
    	        // The direct entity. For example, if a skeleton shot you, the skeleton would be the causing entity
    	        // (= the parameter above), and the arrow would be the direct entity (= this parameter). Similar to
    	        // the causing entity, this isn't always applicable and therefore nullable. Optional, defaults to null.
    	        null,
    	        // The entity causing the damage. This isn't always applicable (e.g. when falling out of the world)
    	        // and may therefore be null. Optional, defaults to null.
    	        null,
    	        // The damage source position. This is rarely used, one example would be intentional game design
    	        // (= nether beds exploding). Nullable and optional, defaulting to null.
    	        null
    	
        return (new BulletDamageSource(, projectile, attacker)).setProjectile();
    }
    public static DamageSource shotgun_bullet(Projectile projectile, @Nullable Entity attacker) {
        return (new BulletDamageSource("shotgun_bullet", projectile, attacker)).setProjectile();
    }
    public static DamageSource pierce_bullet(Projectile projectile, @Nullable Entity attacker) {
        return (new BulletDamageSource("pierce_bullet", projectile, attacker)).setProjectile().setUsesMagic();
    }
    public static DamageSource strong_bullet(Projectile projectile, @Nullable Entity attacker) {
        return (new BulletDamageSource("strong_bullet", projectile, attacker)).setProjectile();
    }
    

    public static RegistryObject<SoundEvent> registeSoundEvents(String name) {
    	return GUN_SOUND_EVENTS.register(name, ()-> SoundEvent.createVariableRangeEvent(new ResourceLocation(BlackPowder.MODID,name)));
    }
    
    public static Holder<DamageType> createDamageTypeHolder(String name) {
    	Holder<DamageType>  holder;
    	holder.value() = new DamageType("arrow", 0.1F);
    	return new Holder<DamageType>(new DamageType("arrow", 0.1F));
    }
}