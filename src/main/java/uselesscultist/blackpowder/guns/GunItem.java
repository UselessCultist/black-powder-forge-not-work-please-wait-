package uselesscultist.blackpowder.guns;

import uselesscultist.blackpowder.BlackPowder;
import uselesscultist.blackpowder.items.BlackPowderItems;
import uselesscultist.blackpowder.items.BulletItem;
import com.google.common.collect.Lists;

import net.minecraft.ChatFormatting;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.CrossbowAttackMob;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.*;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.RegistryObject;

import org.jetbrains.annotations.Nullable;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.util.List;
import java.util.function.Predicate;

public class GunItem extends CrossbowItem {
    public int bulletCount;
    public float inaccuracy;
    public int chargeTime;
    public int quickChargeChange;
    public RegistryObject<SoundEvent> START;
    public RegistryObject<SoundEvent> MIDDLE;
    public RegistryObject<SoundEvent> END;
    public RegistryObject<SoundEvent> SHOOT;
    public RegistryObject<SoundEvent> HIT;
    public float velocity;
    public RegistryObject<Item> ammo;
    public int damage;
    public int piercing;
    public String damageType;

    public GunItem(int bulletCount, float inaccuracy, int chargeTime, int quickChargeChange, RegistryObject<SoundEvent> start, RegistryObject<SoundEvent> middle, RegistryObject<SoundEvent> end, RegistryObject<SoundEvent> shoot, RegistryObject<SoundEvent> hit,
                   float velocity, RegistryObject<Item> ammo, int damage, int piercing, String damageType) {
        super(new Item.Properties().stacksTo(1)); //.group(ItemGroup.COMBAT).maxCount(1).fireproof()
        this.bulletCount = bulletCount;
        this.inaccuracy = inaccuracy;
        this.chargeTime = chargeTime;
        this.quickChargeChange = quickChargeChange;
        this.START = start;
        this.MIDDLE = middle;
        this.END = end;
        this.SHOOT = shoot;
        this.HIT = hit;
        this.velocity = velocity;
        this.ammo = ammo;
        this.damage = damage;
        this.piercing = piercing;
        this.damageType = damageType;
    }

    public static int getChargeTime(GunItem item) {
        return item.chargeTime;
    }

    public static int getQuickChargeChange(GunItem item) {
        return item.quickChargeChange;
    }

    public static int getBulletCount(GunItem item) {
        return item.bulletCount;
    }

    public static SoundEvent getShootSound(GunItem item) {
        return item.SHOOT.get();
    }

    public static SoundEvent getHitSound(GunItem item) {
        return item.HIT.get();
    }

    private boolean charged = false;
    private boolean loaded = false;

    public Predicate<ItemStack> getSupportedHeldProjectiles() {
        return getAllSupportedProjectiles();
    }

    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return (stack) -> stack.getItem() == ammo.get();
    }

    public InteractionResultHolder<ItemStack> use(Level world, Player user, InteractionHand hand) {
        ItemStack itemStack = user.getItemInHand(hand);
        if (isCharged(itemStack)) {
            shootAll(world, user, itemStack, getSpeed(), inaccuracy);
            setCharged(itemStack, false);
            return InteractionResultHolder.consume(itemStack);
        } else if (!user.getProjectile(itemStack).isEmpty()) {
            if (!isCharged(itemStack)) {
                this.charged = false;
                this.loaded = false;
                user.startUsingItem(hand);
            }

            return InteractionResultHolder.consume(itemStack);
        } else {
            return InteractionResultHolder.fail(itemStack);
        }
    }

    public void onStoppedUsing(ItemStack stack, Level world, LivingEntity user, int remainingUseTicks) {
        int i = this.getMaxUseTime(stack) - remainingUseTicks;
        float f = getPullProgress(i, stack);
        if (f >= 1.0F && !isCharged(stack) && loadProjectiles(user, stack)) {
            setCharged(stack, true);
            SoundSource soundCategory = user instanceof Player ? SoundSource.PLAYERS : SoundSource.HOSTILE;
            world.playSound(null, user.getX(), user.getY(), user.getZ(), END.get(), soundCategory, 1.0F, 1.0F / (world.getRandom().nextFloat() * 0.5F + 1.0F) + 0.2F);
        }

    }

    private static boolean loadProjectiles(LivingEntity shooter, ItemStack projectile) {
        int i = EnchantmentHelper.getTagEnchantmentLevel(Enchantments.MULTISHOT, projectile);
        int j = i == 0 ? 1 : 3;
        boolean bl = shooter instanceof Player && ((Player)shooter).isCreative();
        ItemStack itemStack = shooter.getProjectile(projectile);
        ItemStack itemStack2 = itemStack.copy();
        for(int k = 0; k < j; ++k) {
            if (bl) {
                itemStack = new ItemStack(((GunItem)projectile.getItem()).ammo.get());
            }
            if (k > 0) {
                itemStack = itemStack2.copy();
            }
            if (itemStack.isEmpty() && bl) {
                itemStack = new ItemStack(((GunItem)projectile.getItem()).ammo.get());
                itemStack2 = itemStack.copy();
            }
            if (!loadProjectile(shooter, projectile, itemStack, k > 0, bl)) {
                return false;
            }
        }
        return true;
    }

    private static boolean loadProjectile(LivingEntity shooter, ItemStack crossbow, ItemStack projectile, boolean simulated, boolean creative) {
        if (projectile.isEmpty()) {
            return false;
        } else {
            boolean bl = creative && projectile.getItem() instanceof BulletItem; //projectile.is(null)
            ItemStack itemStack2;
            if (!bl && !creative && !simulated) {
                itemStack2 = projectile.split(1);
                if (projectile.isEmpty() && shooter instanceof Player) {
                    ((Player)shooter).getInventory().removeItem(projectile);
                }
            } else {
                itemStack2 = projectile.copy();
            }

            putProjectile(crossbow, itemStack2);
            return true;
        }
    }

    public static boolean isCharged(ItemStack stack) {
        CompoundTag compoundTag = stack.getTag();
        return compoundTag != null && compoundTag.getBoolean("Charged");
    }

    public static void setCharged(ItemStack stack, boolean charged) {
        CompoundTag compoundTag = stack.getOrCreateTag();
        compoundTag.putBoolean("Charged", charged);
    }

    private static void putProjectile(ItemStack gun, ItemStack projectile) {
        CompoundTag compoundTag = gun.getOrCreateTag();
        ListTag listTag2;
        if (compoundTag.contains("ChargedProjectiles", 9)) {
            listTag2 = compoundTag.getList("ChargedProjectiles", 10);
        } else {
            listTag2 = new ListTag();
        }

        CompoundTag compoundTag2 = new CompoundTag();
        compoundTag2 = projectile.getOrCreateTag();
        listTag2.add(compoundTag2);
        compoundTag.put("ChargedProjectiles", listTag2);
    }

    private static List<ItemStack> getProjectiles(ItemStack gun) {
        List<ItemStack> list = Lists.newArrayList();
        CompoundTag compoundTag = gun.getTag();
        if (compoundTag != null && compoundTag.contains("ChargedProjectiles", 9)) {
            ListTag listTag = compoundTag.getList("ChargedProjectiles", 10);
            if (listTag != null) {
                for(int i = 0; i < listTag.size(); ++i) {
                    CompoundTag compoundTag2 = listTag.getCompound(i);
                    list.add(ItemStack.of(compoundTag2));
                }
            }
        }

        return list;
    }

    private static void clearProjectiles(ItemStack gun) {
        CompoundTag compoundTag = gun.getTag();
        if (compoundTag != null) {
            ListTag listTag = compoundTag.getList("ChargedProjectiles", 9);
            listTag.clear();
            compoundTag.put("ChargedProjectiles", listTag);
        }
    }

    private static void shoot(Level world, LivingEntity shooter, ItemStack gun, ItemStack projectile, float soundPitch, float speed, float divergence, float simulated) {
        if (!world.isClientSide) {
            for(int b = 0; b < GunItem.getBulletCount((GunItem) gun.getItem()); b++) {
            	AbstractArrow projectileEntity2 = createBullet(world, shooter, gun, projectile);
                if (projectileEntity2 != null) {
                    (projectileEntity2).pickup = AbstractArrow.Pickup.CREATIVE_ONLY;

                    if (shooter instanceof CrossbowAttackMob) {
                    	CrossbowAttackMob crossbowUser = (CrossbowAttackMob) shooter;
                    	crossbowUser.shootCrossbowProjectile(crossbowUser.getTarget(), gun, projectileEntity2, simulated);
                    } else {
                        Vec3 vec3d = shooter.getUpVector(1.0F);
                        Quaternionf quaternionf = (new Quaternionf()).setAngleAxis((double)(simulated * ((float)Math.PI / 180F)), vec3d.x, vec3d.y, vec3d.z);
                        Vec3 vec3d2 = shooter.getViewVector(1.0F);
                        Vector3f vector3f = vec3d2.toVector3f().rotate(quaternionf);
                        vector3f.rotate(quaternionf);
                        projectileEntity2.shoot((double)vector3f.x(), (double)vector3f.y(), (double)vector3f.z(), speed, divergence);
                    }
                	System.out.println(projectileEntity2.getX()+"|  |"+projectileEntity2.getY()+"|  |"+projectileEntity2.getZ());
                    world.addFreshEntity(projectileEntity2);
                }
            }
            world.playSound((Player)null, shooter.getX(), shooter.getY(), shooter.getZ(), GunItem.getShootSound((GunItem) gun.getItem()), SoundSource.PLAYERS, 1.0F, soundPitch);
        }
    }

    private static AbstractArrow createBullet(Level world, LivingEntity entity, ItemStack gun, ItemStack bullet) {
    	BulletItem bulletitem = (BulletItem)(bullet.getItem() instanceof BulletItem ? bullet.getItem() : BlackPowderItems.MUSKET_BALL.get());
    	BulletEntity persistentProjectileEntity = bulletitem.createBullet(world, bullet, entity,
                ((GunItem)gun.getItem()).damage, 0, ((GunItem)gun.getItem()).HIT.get(), ((GunItem)gun.getItem()).damageType);

        if (entity instanceof Player && persistentProjectileEntity != null) {
            persistentProjectileEntity.setCritArrow(true);
        }
        if (persistentProjectileEntity != null) {
            persistentProjectileEntity.setSoundEvent(GunItem.getHitSound((GunItem) gun.getItem()));
            persistentProjectileEntity.setShotFromCrossbow(true);
            int i = EnchantmentHelper.getTagEnchantmentLevel(Enchantments.PIERCING, gun) + ((GunItem) gun.getItem()).piercing;
            if (i > 0) {
                persistentProjectileEntity.setPierceLevel((byte) i);
            }
            persistentProjectileEntity.setBaseDamage(2);
        }
        return persistentProjectileEntity;
    }

    public static void shootAll(Level world, LivingEntity entity, ItemStack stack, float speed, float divergence) {
    	if (entity instanceof Player player && net.minecraftforge.event.ForgeEventFactory.onArrowLoose(stack, entity.level(), player, 1, true) < 0) return;
        List<ItemStack> list = getProjectiles(stack);
        float[] fs = getSoundPitches(entity.getRandom());

        if (!entity.getCommandSenderWorld().isClientSide) {
            ((ServerLevel) entity.getCommandSenderWorld()).sendParticles(ParticleTypes.POOF,
                    entity.getX(),
                    entity.getY() + 1.4,
                    entity.getZ(),
                    24,
                    0.2,
                    0.2,
                    0.2,
                    0.01);
        }

        for (int i = 0; i < list.size(); ++i) {
            ItemStack itemStack = list.get(i);
            if (!itemStack.isEmpty()) {
                if (i == 0) {
                    shoot(world, entity, stack, itemStack, fs[i], speed, divergence, 0.0F);
                } else if (i == 1) {
                    shoot(world, entity, stack, itemStack, fs[i], speed, divergence, -10.0F);
                } else if (i == 2) {
                    shoot(world, entity, stack, itemStack, fs[i], speed, divergence, 10.0F);
                }
            }
        }
        postShoot(world, entity, stack);
        if (/*BlackPowder.config.FUN_MODE.get()*/ false && world.getRandom().nextInt(1001) == 1000) {
            List<Entity> nearbyEntities = world.getEntities(null, new AABB(entity.getX() - 1000, entity.getY() - 1000, entity.getZ() - 1000,
                    entity.getX() + 1000, entity.getY() + 1000, entity.getZ() + 1000));
            for (Entity entity2 : nearbyEntities) {
                if (entity2 instanceof Player) {
                    entity2.damageSources().explosion(new Explosion(world, entity, entity2.getX(), entity2.getY(), entity2.getZ(), 5000, null));
                }
            }
        }
    }

    private static float[] getSoundPitches(RandomSource randomSource) {
        boolean bl = randomSource.nextBoolean();
        return new float[]{1.0F, getSoundPitch(bl, randomSource), getSoundPitch(!bl, randomSource)};
    }

    private static float getSoundPitch(boolean flag, RandomSource random) {
        float f = flag ? 0.63F : 0.43F;
        return 1.0F / (random.nextFloat() * 0.5F + 1.8F) + f;
    }

    private static void postShoot(Level world, LivingEntity entity, ItemStack stack) {
        if (entity instanceof ServerPlayer) {
        	ServerPlayer serverPlayerEntity = (ServerPlayer)entity;
            if (!world.isClientSide) {
            	CriteriaTriggers.SHOT_CROSSBOW.trigger(serverPlayerEntity, stack);
            }
            serverPlayerEntity.awardStat(Stats.ITEM_USED.get(stack.getItem()));
        }

        clearProjectiles(stack);
    }

    @Override
    public void onUseTick(Level world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        if (!world.isClientSide) {
            SoundEvent soundEvent = this.getQuickChargeSound();
            SoundEvent soundEvent2 = MIDDLE.get();
            float f = (float)(stack.getMaxStackSize() - remainingUseTicks) / (float)getPullTime(stack);
            if (f < 0.2F) {
                this.charged = false;
                this.loaded = false;
            }

            if (f >= 0.2F && !this.charged) {
                this.charged = true;
                world.playSound(null, user.getX(), user.getY(), user.getZ(), soundEvent, SoundSource.PLAYERS, 0.5F, 1.0F);
            }

            if (f >= 0.5F && soundEvent2 != null && !this.loaded) {
                this.loaded = true;
                world.playSound(null, user.getX(), user.getY(), user.getZ(), soundEvent2, SoundSource.PLAYERS, 0.5F, 1.0F);
            }
        }

    }

    public int getMaxUseTime(ItemStack stack) {
        return getPullTime(stack) + 3;
    }

    public static int getPullTime(ItemStack stack) {
        int i = EnchantmentHelper.getTagEnchantmentLevel(Enchantments.QUICK_CHARGE, stack);
        return i == 0 ? getChargeTime((GunItem) stack.getItem()) : getChargeTime((GunItem) stack.getItem()) - getQuickChargeChange((GunItem) stack.getItem()) * i;
    }

    public UseAnim getUseAction(ItemStack stack) {
        return UseAnim.CROSSBOW;
    }

    private SoundEvent getQuickChargeSound() {
        return START.get();
    }

    private static float getPullProgress(int useTicks, ItemStack stack) {
        float f = (float)useTicks / (float)getPullTime(stack);
        if (f > 1.0F) {
            f = 1.0F;
        }

        return f;
    }

    public void appendTooltip(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag context) {
        List<ItemStack> list = getProjectiles(stack);
        if (isCharged(stack) && !list.isEmpty()) {
            ItemStack itemStack = list.get(0);
            tooltip.add(Component.translatable("item.minecraft.crossbow.projectile").append(CommonComponents.SPACE).append(itemStack.getDisplayName()));
            if (context.isAdvanced() && itemStack.getItem() == Items.FIREWORK_ROCKET) {
                List<Component> list2 = Lists.newArrayList();
                Items.FIREWORK_ROCKET.appendHoverText(itemStack, world, list2, context);
                if (!list2.isEmpty()) {
                    for(int i = 0; i < list2.size(); ++i) {
                        list2.set(i, Component.literal("  ").append(list2.get(i)).withStyle(ChatFormatting.GRAY));
                    }

                    tooltip.addAll(list2);
                }
            }

        }
    }

    private float getSpeed() {
        return velocity;
    }

    public int getRange() {
        return 8;
    }

    @Override
    public boolean useOnRelease(ItemStack stack) {
        return stack.getItem() instanceof GunItem;
    }
}
