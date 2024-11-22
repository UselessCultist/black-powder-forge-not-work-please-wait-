package amymialee.blackpowder.guns;

import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.network.protocol.game.ClientboundGameEventPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.Level;

import java.util.List;

public class BulletEntity extends Arrow {
    private final String damageType;

    public BulletEntity(Level world, LivingEntity owner, double damage, int punch, SoundEvent sound, String damageType) {
        super(world, owner);
        this.damage = damage;
        this.punch = punch;
        this.sound = sound;
        this.setAirSupply(500);
        this.damageType = damageType;
    }

    public void tick() {
        super.tick();
        if (this.inGround) {
        	this.kill();
        }
    }

    private final double damage;
    private final int punch;
    private final SoundEvent sound;
    private IntOpenHashSet piercedEntities;
    private List<Entity> piercingKilledEntities;

    @Override
    protected void onHitEntity(EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        if (this.getPierceLevel() > 0) {
            if (this.piercedEntities == null) {
                this.piercedEntities = new IntOpenHashSet(5);
            }
            if (this.piercingKilledEntities == null) {
                this.piercingKilledEntities = Lists.newArrayListWithCapacity(5);
            }
            if (this.piercedEntities.size() >= this.getPierceLevel() + 1) {
                this.discard();
                return;
            }
            this.piercedEntities.add(entity.getId());
        }
        Entity entity2 = this.getOwner();
        DamageSource damageSource2 = null;
        if (entity2 == null) {
            switch (damageType) {
                case "bullet":
                    damageSource2 = BulletDamageSource.bullet(this.level(),this, this);
                    break;
                case "shotgun_bullet":
                    damageSource2 = BulletDamageSource.shotgun_bullet(this.level(),this, this);
                    break;
                case "pierce_bullet":
                    damageSource2 = BulletDamageSource.pierce_bullet(this.level(),this, this);
                    break;
                case "strong_bullet":
                    damageSource2 = BulletDamageSource.strong_bullet(this.level(),this, this);
                    break;
            }
        } else {
            switch (damageType) {
                case "bullet":
                    damageSource2 = BulletDamageSource.bullet(this.level(),this, entity2);
                    break;
                case "shotgun_bullet":
                    damageSource2 = BulletDamageSource.shotgun_bullet(this.level(),this, entity2);
                    break;
                case "pierce_bullet":
                    damageSource2 = BulletDamageSource.pierce_bullet(this.level(),this, entity2);
                    break;
                case "strong_bullet":
                    damageSource2 = BulletDamageSource.strong_bullet(this.level(),this, entity2);
                    break;
            }
            if (entity2 instanceof LivingEntity) {

                ((LivingEntity) entity2).setLastHurtMob(entity);// .onAttacking(entity);
            }
        }

        boolean bl = entity.getType() == EntityType.ENDERMAN;
        int j = entity.getRemainingFireTicks();
        if (this.isOnFire() && !bl) {
            entity.setSecondsOnFire(5);
        }
        if (entity instanceof LivingEntity) {
            //entity.timeUntilRegen = 0;
            ((LivingEntity) entity).hurtTime = 0;
        }
        if (entity.hurt(damageSource2,(float) damage)) {
            if (bl) {
                return;
            }

            if (entity instanceof LivingEntity) {
                LivingEntity livingEntity = (LivingEntity) entity;
                if (this.punch > 0) {
                    double d0 = Math.max(0.0D, 1.0D - livingEntity.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE));
                    Vec3 vec3d = this.getDeltaMovement().multiply(1.0D, 0.0D, 1.0D).normalize().scale((double)this.punch * 0.6D * d0);
                    if (vec3d.lengthSqr() > 0.0D) {
                        livingEntity.push(vec3d.x * 1.5, 0.15D, vec3d.z * 1.5);
                    }
                }

                if (!this.level().isClientSide() && entity2 instanceof LivingEntity) {
                    EnchantmentHelper.doPostHurtEffects(livingEntity, entity2);
                    EnchantmentHelper.doPostDamageEffects((LivingEntity) entity2, livingEntity);
                }

                this.doPostHurtEffects(livingEntity);
                if (livingEntity != entity2 && livingEntity instanceof Player && entity2 instanceof ServerPlayer && !this.isSilent()) {
                	((ServerPlayer)entity2).connection.send(new ClientboundGameEventPacket(ClientboundGameEventPacket.ARROW_HIT_PLAYER, 0.0F));
                }

                if (!entity.isAlive() && this.piercingKilledEntities != null) {
                    this.piercingKilledEntities.add(livingEntity);
                }

                /*if (!this.world.isClient && entity2 instanceof ServerPlayerEntity) {
                    ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity) entity2;
                    if (this.piercingKilledEntities != null && this.isShotFromCrossbow()) {
                        Criteria.KILLED_BY_CROSSBOW.trigger(serverPlayerEntity, this.piercingKilledEntities);
                    } else if (!entity.isAlive() && this.isShotFromCrossbow()) {
                        Criteria.KILLED_BY_CROSSBOW.trigger(serverPlayerEntity, Arrays.asList(entity));
                    }
                }*/
            }

            this.playSound(this.sound, 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
            if (this.getPierceLevel() <= 0) {
                this.discard();
            }
        } else {
            entity.setRemainingFireTicks(j);
            this.setDeltaMovement(this.getDeltaMovement().scale(-0.1D));
            this.setYRot(this.getYRot() + 180.0F);
            this.yRotO += 180.0F;
            if (!this.level().isClientSide && this.getDeltaMovement().lengthSqr() < 1.0E-7D) {
               if (this.pickup == AbstractArrow.Pickup.ALLOWED) {
                  this.spawnAtLocation(this.getPickupItem(), 0.1F);
               }

                this.discard();
            }
        }
    }
}