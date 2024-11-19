package amymialee.blackpowder;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.RedstoneTorchBlock;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

import java.util.Random;

public class TargetLampBlock extends Block {
    public static final BooleanProperty LIT;

    public TargetLampBlock(BlockBehaviour.Properties properties) {
    	super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(LIT, false));
    }

    public void onProjectileHit(Level world, BlockState state, BlockHitResult hit, Projectile projectile) {
        int i = trigger(world, state, hit, projectile);
        Entity entity = projectile.getOwner();
        if (entity instanceof ServerPlayer) {
        	ServerPlayer serverPlayerEntity = (ServerPlayer)entity;
            serverPlayerEntity.awardStat(Stats.TARGET_HIT);
            CriteriaTriggers.TARGET_BLOCK_HIT.trigger(serverPlayerEntity, projectile, hit.getLocation(), i);
        }
    }

    private static int trigger(LevelAccessor world, BlockState state, BlockHitResult blockHitResult, Entity entity) {
        int i = 15;
        int j = entity instanceof Projectile ? 20 : 8;
        if (!world.getBlockTicks().hasScheduledTick(blockHitResult.getBlockPos(), state.getBlock())) {
            setPower(world, state, i, blockHitResult.getBlockPos(), j);
        }

        return i;
    }

    private static void setPower(LevelAccessor world, BlockState state, int power, BlockPos pos, int delay) {
        world.setBlock(pos, state.cycle(LIT), 2);
        world.scheduleTick(pos, state.getBlock(), delay);
    }

    public void neighborUpdate(BlockState state, Level world, BlockPos pos, Block block, BlockPos fromPos, boolean notify) {
        if (!world.isClientSide) {
            boolean bl = state.getValue(LIT);
            if (bl != world.hasNeighborSignal(pos)) {
                if (bl) {
                	world.scheduleTick(pos, this, 8);
                } else {
                    world.setBlock(pos, state.cycle(LIT), 2);
                }
            }

        }
    }

    public void scheduledTick(BlockState state, ServerLevel world, BlockPos pos, Random random) {
        if (state.getValue(LIT) && !world.hasNeighborSignal(pos)) {
            world.setBlock(pos, state.cycle(LIT), 2);
        }

    }

    protected void appendProperties(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LIT);
    }

    static {
        LIT = RedstoneTorchBlock.LIT;
    }
}
