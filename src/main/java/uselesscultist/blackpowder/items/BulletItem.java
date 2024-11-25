package uselesscultist.blackpowder.items;

import uselesscultist.blackpowder.guns.BulletEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.Level;

public class BulletItem extends ArrowItem {
    public BulletItem(Item.Properties properties) {
        super(properties);
    }

    public Arrow createBullet(Level world, ItemStack stack, LivingEntity shooter, double damage, int punch, SoundEvent sound, String damageType) {
        return new BulletEntity(world, shooter, damage, punch, sound, damageType);
    }
}
