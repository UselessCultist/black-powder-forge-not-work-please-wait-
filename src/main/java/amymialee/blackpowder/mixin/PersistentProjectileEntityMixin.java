package amymialee.blackpowder.mixin;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.phys.EntityHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import amymialee.blackpowder.guns.BulletDamageSource;

@Mixin(AbstractArrow.class)
public class PersistentProjectileEntityMixin {
    @Shadow private double damage;
    @Inject(method = "onEntityHit", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;damage(Lnet/minecraft/entity/damage/DamageSource;F)Z"))
    public void redirectDamage(EntityHitResult entityHitResult, CallbackInfo ci) {
    	AbstractArrow arrow = (AbstractArrow)((Object)this);
        DamageSource damageSource2 = arrow.damageSources().arrow(arrow, arrow);
        if (damageSource2.is(BulletDamageSource.BULLET)) {
            int i = (int) damage;
        }
    }
}
