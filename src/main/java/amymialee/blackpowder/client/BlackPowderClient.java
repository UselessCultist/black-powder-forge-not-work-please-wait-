package amymialee.blackpowder.client;

import amymialee.blackpowder.BlackPowder;
import amymialee.blackpowder.guns.GunItem;
import net.minecraft.data.models.ModelProvider;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.data.PackOutput;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.ModelProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.client.event.ModelEvent.RegisterGeometryLoaders;
import net.minecraftforge.client.model.generators.CustomLoaderBuilder;
import net.minecraftforge.client.model.generators.ModelBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;

import static amymialee.blackpowder.guns.BlackPowderGuns.*;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

import com.google.gson.JsonElement;

public class BlackPowderClient<T extends ModelBuilder<T>> extends CustomLoaderBuilder<T> {
	

	public static <T extends ModelBuilder<T>> BlackPowderClient<T> begin(T parent, ExistingFileHelper existingFileHelper) {
		return new BlackPowderClient<>(parent, existingFileHelper);
	}

	protected BlackPowderClient(T parent, ExistingFileHelper existingFileHelper) {
		super(new ResourceLocation(BlackPowder.MODID, "black_powder_loader"), parent, existingFileHelper);
	}
	

	@Override
    public void run() {
		super.run();
		this.generateLayeredItem();
		gun_flintlock_0;
		this.generateFlatItem(FLINTLOCK_PISTOL, "charged", (gun_flintlock_0, gun_flintlock_1, gun_flintlock_2)
                -> gun_flintlock_2 != null && GunItem.isCharged(gun_flintlock_0) ? 1.0F : 0.0F);
    }
	
    static {
        ModelPredicateProviderRegistry.register(FLINTLOCK_PISTOL, new Identifier("charged"), (gun_flintlock_0, gun_flintlock_1, gun_flintlock_2)
                -> gun_flintlock_2 != null && GunItem.isCharged(gun_flintlock_0) ? 1.0F : 0.0F);
        FabricModelPredicateProviderRegistry.register(BLUNDERBUSS, new Identifier("charged"), (gun_blunderbuss_0, gun_blunderbuss_1, gun_blunderbuss_2)
                -> gun_blunderbuss_2 != null && GunItem.isCharged(gun_blunderbuss_0) ? 1.0F : 0.0F);
        FabricModelPredicateProviderRegistry.register(RIFLE, new Identifier("charged"), (gun_rifle_0, gun_rifle_1, gun_rifle_2)
                -> gun_rifle_2 != null && GunItem.isCharged(gun_rifle_0) ? 1.0F : 0.0F);
        FabricModelPredicateProviderRegistry.register(MUSKET, new Identifier("charged"), (gun_musket_0, gun_musket_1, gun_musket_2)
                -> gun_musket_2 != null && GunItem.isCharged(gun_musket_0) ? 1.0F : 0.0F);

        FabricModelPredicateProviderRegistry.register(FLINTLOCK_CARBINE, new Identifier("charged"), (gun_flintlock_0, gun_flintlock_1, gun_flintlock_2)
                -> gun_flintlock_2 != null && GunItem.isCharged(gun_flintlock_0) ? 1.0F : 0.0F);
        FabricModelPredicateProviderRegistry.register(BLUNDERBEHEMOTH, new Identifier("charged"), (gun_blunderbuss_0, gun_blunderbuss_1, gun_blunderbuss_2)
                -> gun_blunderbuss_2 != null && GunItem.isCharged(gun_blunderbuss_0) ? 1.0F : 0.0F);
        FabricModelPredicateProviderRegistry.register(RESOLUTE_RIFLE, new Identifier("charged"), (gun_rifle_0, gun_rifle_1, gun_rifle_2)
                -> gun_rifle_2 != null && GunItem.isCharged(gun_rifle_0) ? 1.0F : 0.0F);
        FabricModelPredicateProviderRegistry.register(BOUNDLESS_MUSKET, new Identifier("charged"), (gun_musket_0, gun_musket_1, gun_musket_2)
                -> gun_musket_2 != null && GunItem.isCharged(gun_musket_0) ? 1.0F : 0.0F);
    }
}
