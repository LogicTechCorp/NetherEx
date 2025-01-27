package logictechcorp.netherex.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;

public class NEThornstalkBlock extends DoublePlantBlock
{
    public static final MapCodec<DoublePlantBlock> CODEC = simpleCodec(NEThornstalkBlock::new);

    public NEThornstalkBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos)
    {
        return state.is(BlockTags.SOUL_FIRE_BASE_BLOCKS);
    }

    @Override
    public MapCodec<? extends DoublePlantBlock> codec()
    {
        return CODEC;
    }
}
