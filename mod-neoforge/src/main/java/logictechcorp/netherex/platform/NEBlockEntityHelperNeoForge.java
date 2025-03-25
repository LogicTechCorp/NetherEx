package logictechcorp.netherex.platform;

import com.google.auto.service.AutoService;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.BiFunction;

@AutoService(NEBlockEntityHelper.class)
public class NEBlockEntityHelperNeoForge implements NEBlockEntityHelper
{
    @Override
    public <E extends BlockEntity> BlockEntityType<E> createBlockEntityType(BiFunction<BlockPos, BlockState, E> createBlockEntityFunc, Block... blocks)
    {
        return new BlockEntityType<>(createBlockEntityFunc::apply, blocks);
    }
}
