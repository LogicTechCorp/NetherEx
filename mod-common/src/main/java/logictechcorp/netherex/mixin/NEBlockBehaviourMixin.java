package logictechcorp.netherex.mixin;

import logictechcorp.netherex.registry.NetherExBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.redstone.Orientation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BlockBehaviour.class)
public class NEBlockBehaviourMixin
{
    @Inject(
            method = "neighborChanged",
            at = @At("HEAD")
    )
    private void netherex$neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, Orientation orientation, boolean causedByPiston, CallbackInfo callback)
    {
        Block block = state.getBlock();

        if (block == Blocks.OBSIDIAN)
        {
            if (neighborBlock.defaultBlockState().is(NetherExBlockTags.OBSIDIAN_COOLER))
            {
                for (Direction checkDirection : Direction.values())
                {
                    BlockPos checkPos = pos.relative(checkDirection);
                    BlockState checkState = level.getBlockState(checkPos);

                    if (checkState.is(NetherExBlockTags.OBSIDIAN_HEATER))
                    {
                        level.neighborChanged(checkState, checkPos, block, null, causedByPiston);
                    }
                }
            }
        }
    }
}