package logictechcorp.netherex.platform;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.BiFunction;

public interface NEBlockEntityHelper
{
    NEBlockEntityHelper INSTANCE = Services.load(NEBlockEntityHelper.class);

    <E extends BlockEntity> BlockEntityType<E> createBlockEntityType(BiFunction<BlockPos, BlockState, E> createBlockEntityFunc, Block... blocks);
}
