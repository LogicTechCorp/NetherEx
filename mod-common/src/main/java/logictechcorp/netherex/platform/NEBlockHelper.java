package logictechcorp.netherex.platform;

import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public interface NEBlockHelper
{
    NEBlockHelper INSTANCE = Services.load(NEBlockHelper.class);

    StairBlock newStairBlock(BlockState originalBlockState, BlockBehaviour.Properties blockProperties);
}
