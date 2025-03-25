package logictechcorp.netherex.platform;

import com.google.auto.service.AutoService;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.BiFunction;

@AutoService(NEBlockEntityHelper.class)
public class NEBlockEntityHelperFabric implements NEBlockEntityHelper
{
    public <E extends BlockEntity> BlockEntityType<E> createBlockEntityType(BiFunction<BlockPos, BlockState, E> createBlockEntityFunc, Block... blocks)
    {
        return FabricBlockEntityTypeBuilder.create((createBlockEntityFunc::apply), blocks).build();
    }
}
