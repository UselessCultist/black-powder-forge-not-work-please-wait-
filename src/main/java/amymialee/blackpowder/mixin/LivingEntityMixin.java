package amymialee.blackpowder.mixin;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.damagesource.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import amymialee.blackpowder.guns.BulletDamageSource;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    public int timeUntilRegen;
    @Inject(method = "damage", at = @At("HEAD"))
    public void damageHead(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (source.is(BulletDamageSource.BULLET)) {
            this.timeUntilRegen = 0;
        }
    }
}
