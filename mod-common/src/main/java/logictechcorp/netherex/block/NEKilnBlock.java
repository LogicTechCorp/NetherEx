package logictechcorp.netherex.block;

import com.mojang.serialization.MapCodec;
import logictechcorp.netherex.block.entity.NEKilnBlockEntity;
import logictechcorp.netherex.registry.NetherExBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class NEKilnBlock extends AbstractFurnaceBlock
{
    public static final MapCodec<NEKilnBlock> CODEC = simpleCodec(NEKilnBlock::new);

    public NEKilnBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    protected MapCodec<? extends AbstractFurnaceBlock> codec()
    {
        return CODEC;
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random)
    {
        if (state.getValue(LIT))
        {
            double x = pos.getX() + 0.5d;
            double y = pos.getY();
            double z = pos.getZ() + 0.5d;

            if (random.nextDouble() < 0.1d)
            {
                level.playLocalSound(x, y, z, SoundEvents.FURNACE_FIRE_CRACKLE, SoundSource.BLOCKS, 1.0f, 1.0f, false);
            }

            Direction direction = state.getValue(FACING);
            Direction.Axis directionAxis = direction.getAxis();
            double randomOffset = random.nextDouble() * 0.6d - 0.3d;
            double xOffset = directionAxis == Direction.Axis.X ? (double) direction.getStepX() * 0.52d : randomOffset;
            double yOffset = random.nextDouble() * (double) 6.0F / (double) 16.0F;
            double zOffset = directionAxis == Direction.Axis.Z ? (double) direction.getStepZ() * 0.52d : randomOffset;
            level.addParticle(ParticleTypes.SMOKE, x + xOffset, y + yOffset, z + zOffset, 0.0d, 0.0d, 0.0d);
            level.addParticle(ParticleTypes.FLAME, x + xOffset, y + yOffset, z + zOffset, 0.0d, 0.0d, 0.0d);
        }
    }


    @Override
    protected void openContainer(Level level, BlockPos pos, Player player)
    {
        BlockEntity blockEntity = level.getBlockEntity(pos);

        if (blockEntity instanceof NEKilnBlockEntity kilnBlockEntity)
        {
            player.openMenu(kilnBlockEntity);
        }
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state)
    {
        return NetherExBlockEntityTypes.KILN.get().create(pos, state);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType)
    {
        return createFurnaceTicker(level, blockEntityType, NetherExBlockEntityTypes.KILN.get());
    }
}
