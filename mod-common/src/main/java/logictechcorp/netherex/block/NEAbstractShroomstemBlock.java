package logictechcorp.netherex.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Supplier;

public abstract class NEAbstractShroomstemBlock extends BushBlock
{
    private final Supplier<BlockState> originalBlockState;

    public NEAbstractShroomstemBlock(Supplier<BlockState> inOriginalBlockState, Properties properties)
    {
        super(properties);
        originalBlockState = inOriginalBlockState;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random)
    {
        if (random.nextInt(15) != 0)
        {
            return;
        }

        level.setBlock(pos, originalBlockState.get(), 3);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader levelReader, BlockPos pos)
    {
        BlockPos abovePos = pos.above();
        return mayPlaceOn(levelReader.getBlockState(abovePos), levelReader, abovePos);
    }

    @Override
    protected abstract boolean mayPlaceOn(BlockState state, BlockGetter blockGetter, BlockPos pos);
}
