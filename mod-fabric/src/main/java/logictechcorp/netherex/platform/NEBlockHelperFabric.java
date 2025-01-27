package logictechcorp.netherex.platform;

import com.google.auto.service.AutoService;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

@AutoService(NEBlockHelper.class)
public class NEBlockHelperFabric implements NEBlockHelper
{
    @Override
    public StairBlock newStairBlock(BlockState originalBlockState, BlockBehaviour.Properties blockProperties)
    {
        return new StairBlock(originalBlockState, blockProperties);
    }
}