package amymialee.blackpowder.mixin;

import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.projectile.Arrow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ArrowRenderer.class)
public class ArrowEntityRendererMixin  {
    private static final ResourceLocation BULLET_TEXTURE = new ResourceLocation("textures/entity/bullet.png");
    @Inject(method = "getTextureLocation", at = @At("HEAD"), cancellable = true)
    public void getTexture(Arrow arrowEntity, CallbackInfoReturnable<ResourceLocation> cir) {
        if (arrowEntity.getAirSupply() == 500) {
            cir.setReturnValue(BULLET_TEXTURE);
        }
    }
}
