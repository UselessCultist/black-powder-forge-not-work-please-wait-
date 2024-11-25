package uselesscultist.blackpowder.guns;

import net.minecraft.world.entity.Entity;
import net.minecraft.core.Holder;
import net.minecraft.core.Holder.Reference;
import net.minecraft.core.Holder.Reference.Type;
import net.minecraft.core.HolderOwner;
import net.minecraft.world.level.Level;
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

import uselesscultist.blackpowder.BlackPowder;

public class BulletDamageSource extends DamageSource {
	static final DeferredRegister<SoundEvent> GUN_SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, BlackPowder.MOD_ID);
	

	public static final ResourceKey<DamageType> BULLET = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(BlackPowder.MOD_ID,"bullet"));
	static final ResourceKey<DamageType> SHOTGUN_BULLET = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(BlackPowder.MOD_ID,"bullet"));
	static final ResourceKey<DamageType> PIERCE_BULLET = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(BlackPowder.MOD_ID,"bullet"));
	static final ResourceKey<DamageType> STRONG_BULLET = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(BlackPowder.MOD_ID,"bullet"));

	static final TagKey<DamageType> BULLETS = TagKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(BlackPowder.MOD_ID,"bullet_tag"));
	

    public BulletDamageSource(Reference <DamageType> holder, Entity projectile, @Nullable Entity attacker) {
    	super(holder,projectile,attacker);
    }
	
    public static DamageSource weapon_bullet(Level world, Projectile projectile, @Nullable Entity attacker, ResourceKey<DamageType> bullet) {
    	Reference <DamageType> holder = world.registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE).getOrThrow(bullet);
        return new BulletDamageSource(holder, (Entity)projectile, attacker);
    }
    public static DamageSource bullet(Level world, Projectile projectile, @Nullable Entity attacker) {
        return (weapon_bullet(world,projectile,attacker,BULLET));
    }
    public static DamageSource shotgun_bullet(Level world, Projectile projectile, @Nullable Entity attacker) {
        return (weapon_bullet(world,projectile,attacker,SHOTGUN_BULLET));
    }
    public static DamageSource pierce_bullet(Level world, Projectile projectile, @Nullable Entity attacker) {
        return (weapon_bullet(world,projectile,attacker,PIERCE_BULLET));
    }
    public static DamageSource strong_bullet(Level world, Projectile projectile, @Nullable Entity attacker) {
        return (weapon_bullet(world,projectile,attacker,STRONG_BULLET));
    }
    

    public static RegistryObject<SoundEvent> registeSoundEvents(String name) {
    	return GUN_SOUND_EVENTS.register(name, ()-> SoundEvent.createVariableRangeEvent(new ResourceLocation(BlackPowder.MOD_ID,name)));
    }
}