package logictechcorp.netherex.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Supplier;

public class NEHollowShroomlightBlock extends Block
{
    private final Supplier<Block> originalBlock;

    public NEHollowShroomlightBlock(Supplier<Block> inOriginalBlock, Properties properties)
    {
        super(properties);
        originalBlock = inOriginalBlock;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random)
    {
        if (random.nextInt(15) != 0)
        {
            return;
        }

        level.setBlock(pos, originalBlock.get().defaultBlockState(), 3);
    }
}
