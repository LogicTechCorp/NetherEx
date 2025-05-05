package logictechcorp.netherex.block;

import logictechcorp.netherex.registry.NetherExBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;

public class NEGlowingObsidianBlock extends Block
{
    public NEGlowingObsidianBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving)
    {
        level.scheduleTick(pos, this, 1);
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random)
    {
        if (!hasHeater(level, pos))
        {
            level.setBlockAndUpdate(pos, Blocks.OBSIDIAN.defaultBlockState());
            level.playSound(null, pos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 0.5f, 2.6f + (random.nextFloat() - random.nextFloat()) * 0.8f);
            level.sendParticles(ParticleTypes.LARGE_SMOKE, pos.getX() + 0.5d, pos.getY() + 0.25d, pos.getZ() + 0.5d, 8, 0.5d, 0.25d, 0.5d, 0.0d);
        }
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity)
    {
        boolean shouldHurt = entity instanceof LivingEntity livingEntity && !livingEntity.isSteppingCarefully();

        if (!shouldHurt && !entity.fireImmune() && !entity.isInWaterOrRain() && !entity.isInPowderSnow)
        {
            shouldHurt = true;
        }

        if (shouldHurt)
        {
            entity.hurt(level.damageSources().hotFloor(), 1.0f);
        }
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos)
    {
        if (!hasHeater(level, pos))
        {
            level.scheduleTick(pos, this, 0);
        }

        return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
    }

    public static boolean hasHeater(LevelReader levelReader, BlockPos pos)
    {
        boolean hasHeater = levelReader.dimensionType().ultraWarm();
        boolean hasCooler = false;

        if (!hasHeater)
        {
            for (Direction checkDirection : Direction.values())
            {
                BlockState checkState = levelReader.getBlockState(pos.relative(checkDirection));

                if (checkState.is(NetherExBlockTags.OBSIDIAN_HEATER))
                {
                    hasHeater = true;
                }
                else if (checkState.is(NetherExBlockTags.OBSIDIAN_COOLER))
                {
                    if (checkState.getFluidState().is(Fluids.FLOWING_WATER))
                    {
                        continue;
                    }

                    hasCooler = true;
                    break;
                }
            }
        }

        return hasHeater && !hasCooler;
    }
}
