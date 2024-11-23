package amymialee.blackpowder.client;

import amymialee.blackpowder.guns.BulletEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class BulletEntityRenderer extends ArrowRenderer<BulletEntity> {
    public static final ResourceLocation TEXTURE = new ResourceLocation("textures/entity/arrowd.png");

    public BulletEntityRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

	@Override
	public ResourceLocation getTextureLocation(BulletEntity pEntity) {
		return TEXTURE;
	}
}
