package amymialee.blackpowder.mixin;

import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ArrowRenderer.class)
public class ArrowEntityRendererMixin {
    private static final Identifier BULLET_TEXTURE = new Identifier("textures/entity/bullet.png");
    @Inject(method = "getTexture", at = @At("HEAD"), cancellable = true)
    public void getTexture(Arrow arrowEntity, CallbackInfoReturnable<Identifier> cir) {
        if (arrowEntity.getAirSupply() == 500) {
            cir.setReturnValue(BULLET_TEXTURE);
        }
    }
}
